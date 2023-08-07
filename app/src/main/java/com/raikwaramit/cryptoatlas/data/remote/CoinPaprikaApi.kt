package com.raikwaramit.cryptoatlas.data.remote

import com.raikwaramit.cryptoatlas.data.remote.dto.CoinDto
import com.raikwaramit.cryptoatlas.data.remote.dto.coin_details_dto.CoinDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface for making the retrofit api calls.
 */
interface CoinPaprikaApi {

    /**
     * Method the fetch the crypto coin list.
     */
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    /**
     * Method to fetch the specific crypto coin details.
     *
     * @param coinId Id of the coin for fetching the details.
     */
    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailsDto
}