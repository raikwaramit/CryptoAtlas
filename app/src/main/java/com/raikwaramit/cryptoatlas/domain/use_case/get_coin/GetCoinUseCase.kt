package com.raikwaramit.cryptoatlas.domain.use_case.get_coin

import com.raikwaramit.cryptoatlas.common.Resource
import com.raikwaramit.cryptoatlas.data.remote.dto.coin_details_dto.toCoinDetails
import com.raikwaramit.cryptoatlas.domain.model.CoinDetail
import com.raikwaramit.cryptoatlas.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(id: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(id).toCoinDetails()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ""))

        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: ""))
        }
    }
}