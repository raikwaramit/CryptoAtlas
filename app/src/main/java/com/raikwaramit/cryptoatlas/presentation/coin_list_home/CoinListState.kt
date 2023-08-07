package com.raikwaramit.cryptoatlas.presentation.coin_list_home

import com.raikwaramit.cryptoatlas.domain.model.Coin

/**
 * Possible states that screen can go while fetching the coin list.
 */
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = "",
)