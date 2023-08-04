package com.raikwaramit.cryptoatlas.presentation.coin_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.raikwaramit.cryptoatlas.presentation.Screen
import com.raikwaramit.cryptoatlas.presentation.coin_list.components.CoinListItem
import com.raikwaramit.cryptoatlas.presentation.coin_list.components.NavigationDrawerContent
import com.raikwaramit.cryptoatlas.presentation.coin_list.components.MainWelcomeHeading
import com.raikwaramit.cryptoatlas.presentation.coin_list.components.TopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = { NavigationDrawerContent() },
        drawerState = drawerState,
    ) {
        val scrollState = TopAppBarDefaults.pinnedScrollBehavior()
        val lazyListState = rememberLazyListState()
        Scaffold(
            topBar = {
                AnimatedVisibility(
                    visible = !lazyListState.isScrolledForVisibility,
                    enter = slideInVertically(),
                    exit = slideOutVertically()
                ) {
                    TopAppBar(
                        titleVisibility = lazyListState.isScrolled,
                        scrollBehavior = scrollState,
                        onNavigationIconClick = {
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
                    )
                }
            },
            modifier = Modifier.nestedScroll(scrollState.nestedScrollConnection)
        ) {
            val state = viewModel.state.value
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize(), state = lazyListState) {
                    item {
                        MainWelcomeHeading()
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    items(state.coins) { coin ->
                        Spacer(modifier = Modifier.height(5.dp))
                        CoinListItem(coin = coin, onItemClick = {
                            navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                        })
                    }
                }
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0

val LazyListState.isScrolledForVisibility: Boolean
    get() = firstVisibleItemIndex > 4