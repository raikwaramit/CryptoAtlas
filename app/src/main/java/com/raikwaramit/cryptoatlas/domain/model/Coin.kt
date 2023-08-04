package com.raikwaramit.cryptoatlas.domain.model

/**
 * Coin data class having coin details that will be shown.
 *
 * @property id
 * @property name
 * @property rank
 * @property isActive
 * @property symbol
 */
data class Coin(
    var id: String? = null,
    var name: String? = null,
    var rank: Int? = null,
    var isActive: Boolean? = null,
    var symbol: String? = null
)
