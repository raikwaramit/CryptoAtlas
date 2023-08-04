package com.raikwaramit.cryptoatlas.presentation.coin_list

import com.raikwaramit.cryptoatlas.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)