package com.raikwaramit.cryptoatlas.presentation.coin_detail

import com.raikwaramit.cryptoatlas.domain.model.CoinDetail

data class CoinDetailsState(
    val isLoading: Boolean = false,
    val coinDetails: CoinDetail? = null,
    val error: String = "",
)