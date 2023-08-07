package com.raikwaramit.cryptoatlas.presentation.graph.screen

/**
 * Sealed class for holding the routes for home coin list screens.
 */
sealed class HomeScreenRoute(val route: String) {
    object CoinListScreen : HomeScreenRoute("coin_list_screen")
    object AboutScreen : HomeScreenRoute("about_screen")
    object ContactUsScreen : HomeScreenRoute("contact_us_screen")
}