package com.raikwaramit.cryptoatlas.presentation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raikwaramit.cryptoatlas.common.Constants
import com.raikwaramit.cryptoatlas.presentation.detail.DetailScreen
import com.raikwaramit.cryptoatlas.presentation.graph.screen.DetailScreenRoute

/**
 * Root navigation graph for the app.
 */
@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        homeNavGraph(
            navController = navController,
            onDetailScreenOpenRequest = { coinId ->
                navController.navigate(Graph.DETAIL + "?${Constants.COIN_ID}=$coinId")
            },
            onFavoriteScreenOpenRequest = {
                navController.navigate(Graph.DETAIL + "?route=${DetailScreenRoute.FavoriteScreen.route}")
            }
        )
        // To open the screens of detail screen flow with different arguments.
        composable(route = Graph.DETAIL + "?${Constants.COIN_ID}={${Constants.COIN_ID}}&route={route}") { entry ->
            val route = entry.arguments?.getString("route")
            DetailScreen(startDestination = route)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val DETAIL = "detail_graph"
}