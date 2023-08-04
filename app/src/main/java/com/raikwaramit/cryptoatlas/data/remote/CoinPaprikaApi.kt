package com.raikwaramit.cryptoatlas.data.remote

import com.raikwaramit.cryptoatlas.data.remote.dto.CoinDto
import com.raikwaramit.cryptoatlas.data.remote.dto.coin_details_dto.CoinDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailsDto
}