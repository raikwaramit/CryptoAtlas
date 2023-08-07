package com.raikwaramit.cryptoatlas.domain.model

import com.raikwaramit.cryptoatlas.data.remote.dto.coin_details_dto.TeamMember

/**
 * Data class for holding the details of the specific coin that will be shown in details screen.
 */
data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val logo: String?,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>,
)