package com.raikwaramit.cryptoatlas.domain.model

/**
 * Coin data class having coin details that will be shown.
 *
 * @property id Id of the coin.
 * @property name Name of the coin.
 * @property rank Word wide rank of the coin.
 * @property isActive Is currently in active state.
 * @property symbol Symbol of the coin.
 */
data class Coin(
    var id: String? = null,
    var name: String? = null,
    var rank: Int? = null,
    var isActive: Boolean? = null,
    var symbol: String? = null,
)
