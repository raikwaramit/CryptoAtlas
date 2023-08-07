package com.raikwaramit.cryptoatlas.presentation.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raikwaramit.cryptoatlas.presentation.detail.DetailViewModel
import com.raikwaramit.cryptoatlas.presentation.detail.coin_detail.CoinDetailScreen
import com.raikwaramit.cryptoatlas.presentation.detail.favorite_screen.FavoriteScreen
import com.raikwaramit.cryptoatlas.presentation.graph.screen.DetailScreenRoute

/**
 * Navigation graph for the details screen flow.
 */
@Composable
fun DetailNavGraph(
    navController: NavHostController,
    startDestination: String?,
) {
    val viewModel: DetailViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        route = Graph.DETAIL,
        startDestination = startDestination ?: DetailScreenRoute.CoinDetailScreen.route
    ) {
        composable(route = DetailScreenRoute.CoinDetailScreen.route) {
            CoinDetailScreen(viewModel)
        }
        composable(route = DetailScreenRoute.FavoriteScreen.route) {
            viewModel.getFavoriteCoinFromDataBase()
            FavoriteScreen(viewModel) {
                viewModel.getCoin(it)
                navController.navigate(DetailScreenRoute.CoinDetailScreen.route)
            }
        }
    }
}