package com.raikwaramit.cryptoatlas.domain.model

import com.raikwaramit.cryptoatlas.data.remote.dto.coin_details_dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>,
)