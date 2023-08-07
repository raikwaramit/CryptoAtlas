package com.raikwaramit.cryptoatlas.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raikwaramit.cryptoatlas.common.Constants.COIN_ID
import com.raikwaramit.cryptoatlas.common.Resource
import com.raikwaramit.cryptoatlas.data.local.FavoriteCoin
import com.raikwaramit.cryptoatlas.data.local.FavoriteDao
import com.raikwaramit.cryptoatlas.domain.use_case.get_coin.GetCoinUseCase
import com.raikwaramit.cryptoatlas.presentation.detail.coin_detail.CoinDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Viewmodel for the Details screens.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val favoriteDao: FavoriteDao,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state

    private val _favoriteState = mutableStateOf<List<FavoriteCoin>>(emptyList())
    val favoriteState: State<List<FavoriteCoin>> = _favoriteState

    init {
        savedStateHandle.get<String>(COIN_ID)?.let { coinId ->
            getCoin(coinId = coinId)
        }
    }

    fun getFavoriteCoinFromDataBase() {
        viewModelScope.launch {
            _favoriteState.value = favoriteDao.getAll()
        }
    }

    fun deleteCoinFromDataBase(coin: FavoriteCoin) {
        viewModelScope.launch {
            favoriteDao.delete(coin)
            getFavoriteCoinFromDataBase()
        }
    }

    fun getCoin(coinId: String) {
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