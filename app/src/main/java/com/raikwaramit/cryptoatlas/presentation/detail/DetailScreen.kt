package com.raikwaramit.cryptoatlas.presentation.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.raikwaramit.cryptoatlas.presentation.detail.coin_detail.BottomBarScreen
import com.raikwaramit.cryptoatlas.presentation.graph.DetailNavGraph
import com.raikwaramit.cryptoatlas.presentation.graph.screen.DetailScreenRoute

/**
 * Main coin details flow screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String?,
) {
    val backStackEntry = navHostController.currentBackStackEntryAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (DetailScreenRoute.FavoriteScreen.route == backStackEntry.value?.destination?.route) "Favorite coins" else "Coin Details",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(),
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.TwoTone.ArrowBack,
                            contentDescription = "Back to main screen button."
                        )
                    }
                })
        }, bottomBar = { BottomBar(navHostController) }) {
        Box(modifier = Modifier.padding(it)) {
            DetailNavGraph(navHostController, startDestination = startDestination)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Favorite,
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        elevation = 5.dp
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                backStackEntry = backStackEntry
            ) {
                navController.popBackStack()
                navController.navigate(it.route)
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    backStackEntry: State<NavBackStackEntry?>,
    onItemClick: (BottomBarScreen) -> Unit,
) {
    var selected = screen.route == backStackEntry.value?.destination?.route
    BottomNavigationItem(
        label = {
            if (selected) {
                Text(text = screen.title, style = MaterialTheme.typography.titleSmall)
            }
        },
        icon = {
            if (!selected) {
                Icon(imageVector = screen.icon, contentDescription = "Navigation Icon")
            }
            AnimatedVisibility(selected) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(
                        screen.animationResId
                    )
                )
                LottieAnimation(
                    composition = composition,
                    iterations = Int.MAX_VALUE,
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        selectedContentColor = MaterialTheme.colorScheme.secondary,
        selected = selected,
        onClick = { onItemClick(screen) }
    )
}