package com.raikwaramit.cryptoatlas.data.repository

import com.raikwaramit.cryptoatlas.data.remote.CoinPaprikaApi
import com.raikwaramit.cryptoatlas.data.remote.dto.CoinDto
import com.raikwaramit.cryptoatlas.data.remote.dto.coin_details_dto.CoinDetailsDto
import com.raikwaramit.cryptoatlas.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return api.getCoinById(coinId)
    }
}