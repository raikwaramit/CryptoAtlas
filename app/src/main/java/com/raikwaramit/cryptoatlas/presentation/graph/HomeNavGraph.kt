package com.raikwaramit.cryptoatlas.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.raikwaramit.cryptoatlas.presentation.coin_list_home.CoinListScreen
import com.raikwaramit.cryptoatlas.presentation.coin_list_home.about.AboutScreen
import com.raikwaramit.cryptoatlas.presentation.coin_list_home.contact_us.ContactUsScreen
import com.raikwaramit.cryptoatlas.presentation.graph.screen.HomeScreenRoute

/**
 * SubNavigation graph of the root nav graph having the home screens navigation.
 */
fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    onFavoriteScreenOpenRequest: () -> Unit,
    onDetailScreenOpenRequest: (String?) -> Unit,
) {
    navigation(route = Graph.HOME, startDestination = HomeScreenRoute.CoinListScreen.route) {
        composable(route = HomeScreenRoute.CoinListScreen.route) {
            CoinListScreen(
                onCoinListItemClick = onDetailScreenOpenRequest,
                onFavoriteClick = onFavoriteScreenOpenRequest,
                onAboutClick = {
                    navController.navigate(
                        route = HomeScreenRoute.AboutScreen.route
                    )
                },
                onContactUsClick = {
                    navController.navigate(
                        route = HomeScreenRoute.ContactUsScreen.route
                    )
                }
            )
        }
        composable(route = HomeScreenRoute.AboutScreen.route) {
            AboutScreen()
        }
        composable(route = HomeScreenRoute.ContactUsScreen.route) {
            ContactUsScreen()
        }
    }
}