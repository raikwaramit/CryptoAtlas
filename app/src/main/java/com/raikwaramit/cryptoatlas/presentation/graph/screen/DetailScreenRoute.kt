package com.raikwaramit.cryptoatlas.presentation.graph.screen

/**
 * Sealed class for holding the routes for detail screens.
 */
sealed class DetailScreenRoute(val route: String) {
    object CoinDetailScreen : DetailScreenRoute("coin_detail_screen")
    object FavoriteScreen : DetailScreenRoute("favorite_screen")
}
