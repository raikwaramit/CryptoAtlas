package com.raikwaramit.cryptoatlas.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raikwaramit.cryptoatlas.common.Constants.COIN_ID
import com.raikwaramit.cryptoatlas.common.Resource
import com.raikwaramit.cryptoatlas.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state

    init {
        savedStateHandle.get<String>(COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailsState(coinDetails = result.data)
                }

                is Resource.Loading -> {
                    _state.value = CoinDetailsState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value =
                        CoinDetailsState(error = result.message ?: "Unexpected error occurs.")
                }
            }
        }.launchIn(viewModelScope)
    }
}