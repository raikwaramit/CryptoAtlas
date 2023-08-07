package com.raikwaramit.cryptoatlas.presentation.coin_list_home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raikwaramit.cryptoatlas.common.Resource
import com.raikwaramit.cryptoatlas.data.local.FavoriteCoin
import com.raikwaramit.cryptoatlas.data.local.FavoriteDao
import com.raikwaramit.cryptoatlas.domain.model.Coin
import com.raikwaramit.cryptoatlas.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Viewmodel class for showing the Coin list.
 *
 * @property getCoinsUseCase For getting the details of coin.
 * @property favoriteDao For saving the favorite coins.
 */
@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val favoriteDao: FavoriteDao,
) :
    ViewModel() {

    private val _resultState = mutableStateOf(CoinListState())
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    /**
     * Method to refresh the list.
     */
    fun refresh() {
        getCoins()
    }

    fun addToFavorite(coin: Coin) {
        viewModelScope.launch {
            favoriteDao.insert(FavoriteCoin(coin.id!!, coin.name, coin.symbol, coin.isActive))
        }
    }

    fun showListBySearch(text: String) {
        val list = buildList {
            for (coin in _resultState.value.coins) {
                if (coin.name?.startsWith(text, ignoreCase = true) == true)
                    add(coin)
            }
        }
        _state.value = CoinListState(coins = list)
    }

    fun showListByActiveState() {
        val list = buildList {
            for (coin in _resultState.value.coins) {
                if (coin.isActive == false)
                    add(coin)
            }
        }
        _state.value = CoinListState(coins = list)
    }

    fun showListByRank() {
        _state.value = _resultState.value
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val coinListResult = CoinListState(coins = result.data ?: emptyList())
                    _resultState.value = coinListResult
                    _state.value = coinListResult
                }

                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value =
                        CoinListState(error = result.message ?: "Unexpected error occurs.")
                }
            }
        }.launchIn(viewModelScope)
    }
}