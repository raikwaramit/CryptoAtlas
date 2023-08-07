package com.raikwaramit.cryptoatlas.presentation.coin_list_home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.raikwaramit.cryptoatlas.presentation.coin_list_home.components.CoinListItem
import com.raikwaramit.cryptoatlas.presentation.coin_list_home.components.FavoriteAddedSnackBar
import com.raikwaramit.cryptoatlas.presentation.coin_list_home.components.FilterChipBar
import com.raikwaramit.cryptoatlas.presentation.coin_list_home.components.FilterChips
import com.raikwaramit.cryptoatlas.presentation.coin_list_home.components.MainWelcomeHeading
import com.raikwaramit.cryptoatlas.presentation.coin_list_home.components.NavigationDrawerContent
import com.raikwaramit.cryptoatlas.presentation.coin_list_home.components.TopAppBar
import com.raikwaramit.cryptoatlas.presentation.common_component.ErrorAnimation
import com.raikwaramit.cryptoatlas.presentation.common_component.LoadingAnimation
import com.raikwaramit.cryptoatlas.presentation.extension.launchShareIntent
import kotlinx.coroutines.launch

/**
 * Screen for showing the coin list.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(
    onCoinListItemClick: (String?) -> Unit,
    onFavoriteClick: () -> Unit,
    onAboutClick: () -> Unit,
    onContactUsClick: () -> Unit,
    viewModel: CoinListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    // States for opening and closing navigation drawer.
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerAction: () -> Unit = {
        if (drawerState.isClosed) {
            coroutineScope.launch {
                drawerState.open()
            }
        } else {
            coroutineScope.launch {
                drawerState.close()
            }
        }
    }
    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawerContent(
                onAboutClick = onAboutClick,
                onContactUsClick = onContactUsClick,
                onShareClick = context::launchShareIntent
            )
        },
        drawerState = drawerState,
    ) {
        // State for changing the color of TopAppBar when screen is scrolled.
        val scrollState = TopAppBarDefaults.pinnedScrollBehavior()
        val lazyListState = rememberLazyListState() // State for remembering the list scroll state.
        // State to represent whether user is searching or not.
        var searchState by rememberSaveable { mutableStateOf(false) }
        Scaffold(
            topBar = {
                TopAppBar(
                    searchState = searchState,
                    titleVisibility = lazyListState.isScrolled,
                    scrollBehavior = scrollState,
                    onSearchStateChanged = {
                        searchState = !searchState
                    },
                    onSearch = { text ->
                        viewModel.showListBySearch(text)
                    },
                    onNavigationIconClick = drawerAction,
                    onFavoriteClick = onFavoriteClick,
                    onAboutMenuClick = onAboutClick,
                    onShareMenuClick = context::launchShareIntent
                )
            },
            modifier = Modifier.nestedScroll(scrollState.nestedScrollConnection)
        ) {
            val coinListState = viewModel.state.value
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                val snackBarHostState = remember { SnackbarHostState() }
                // Snack bar host for showing snack bar.
                FavoriteAddedSnackBar(
                    snackBarHostState = snackBarHostState,
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .align(Alignment.BottomCenter)
                        .zIndex(10f)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(), state = lazyListState
                ) {
                    item {
                        AnimatedVisibility(visible = !searchState) {
                            MainWelcomeHeading()
                            HorizontalDivider()
                            Spacer(modifier = Modifier.height(10.dp))
                            LaunchedEffect(searchState) {
                                if (searchState) {
                                    lazyListState.scrollToItem(0)
                                }
                            }
                        }
                    }
                    // Showing filter chip to sort the coin data list.
                    item {
                        var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
                        FilterChipBar(selectedIndex = selectedIndex) { chip ->
                            selectedIndex = chip.ordinal
                            when (chip) {
                                FilterChips.RANK -> viewModel.showListByRank()
                                FilterChips.INACTIVE -> viewModel.showListByActiveState()
                            }
                        }
                    }
                    // Coin details list.
                    items(coinListState.coins) { coin ->
                        Spacer(modifier = Modifier.height(5.dp))
                        CoinListItem(
                            coin = coin, onItemClick = {
                                onCoinListItemClick(coin.id)
                            }
                        ) {
                            viewModel.addToFavorite(coin)
                            coroutineScope.launch {
                                snackBarHostState.showSnackbar(
                                    message = "",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }
                }

                // Showing error animation when some error is occurred while loading coins data.
                if (coinListState.error.isNotBlank()) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .align(Alignment.BottomCenter),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Button(onClick = { viewModel.refresh() }) {
                            Icon(
                                imageVector = Icons.TwoTone.Refresh,
                                contentDescription = "RefreshButton",
                            )
                        }
                        ErrorAnimation(
                            modifier = Modifier
                                .size(400.dp)
                        )
                    }
                }

                // To show the animation when coin data is loading.
                if (coinListState.isLoading) {
                    LoadingAnimation(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxHeight(0.5f)
                    )
                }
            }
        }
    }
}

// State for scroll animation of the top app bar.
val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0
