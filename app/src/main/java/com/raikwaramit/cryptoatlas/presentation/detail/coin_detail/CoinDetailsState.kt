package com.raikwaramit.cryptoatlas.presentation.detail.coin_detail

import com.raikwaramit.cryptoatlas.domain.model.CoinDetail

/**
 * State for fetching and showing the coin related details.
 */
data class CoinDetailsState(
    val isLoading: Boolean = false,
    val coinDetails: CoinDetail? = null,
    val error: String = "",
)