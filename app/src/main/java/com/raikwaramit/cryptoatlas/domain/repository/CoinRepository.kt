package com.raikwaramit.cryptoatlas.domain.repository

import com.raikwaramit.cryptoatlas.data.remote.dto.CoinDto
import com.raikwaramit.cryptoatlas.data.remote.dto.coin_details_dto.CoinDetailsDto

/**
 * Interface for getting the coins related details from the network.
 */
interface CoinRepository {

    /**
     * Method to fetch the coin list and provide it to the view model.
     */
    suspend fun getCoins(): List<CoinDto>

    /**
     * Method to fetch the details of a specific coin.
     *
     * @param coinId Id of the coin.
     */
    suspend fun getCoinById(coinId: String): CoinDetailsDto
}