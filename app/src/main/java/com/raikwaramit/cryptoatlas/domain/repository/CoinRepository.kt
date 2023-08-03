package com.raikwaramit.cryptoatlas.domain.repository

import com.raikwaramit.cryptoatlas.data.remote.dto.CoinDto
import com.raikwaramit.cryptoatlas.data.remote.dto.coin_details_dto.CoinDetailsDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailsDto
}