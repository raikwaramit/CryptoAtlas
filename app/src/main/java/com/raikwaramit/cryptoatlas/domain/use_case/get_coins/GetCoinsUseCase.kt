package com.raikwaramit.cryptoatlas.domain.use_case.get_coins

import com.raikwaramit.cryptoatlas.common.Resource
import com.raikwaramit.cryptoatlas.data.remote.dto.toCoin
import com.raikwaramit.cryptoatlas.domain.model.Coin
import com.raikwaramit.cryptoatlas.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ""))

        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: ""))
        }
    }
}