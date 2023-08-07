package com.raikwaramit.cryptoatlas.presentation.detail.coin_detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.raikwaramit.cryptoatlas.R
import com.raikwaramit.cryptoatlas.presentation.graph.screen.DetailScreenRoute

/**
 * Sealed class holding the bottom navigation button details.
 */
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val animationResId: Int,
) {
    /**
     * For details screen.
     */
    object Home : BottomBarScreen(
        route = DetailScreenRoute.CoinDetailScreen.route,
        title = "Coin Details",
        icon = Icons.TwoTone.Menu,
        animationResId = R.raw.details
    )

    /**
     * For favorite screen.
     */
    object Favorite : BottomBarScreen(
        route = DetailScreenRoute.FavoriteScreen.route,
        title = "Favorite",
        icon = Icons.TwoTone.Favorite,
        animationResId = R.raw.fav_heart
    )
}