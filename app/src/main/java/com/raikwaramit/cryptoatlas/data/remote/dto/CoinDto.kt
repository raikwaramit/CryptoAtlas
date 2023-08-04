package com.raikwaramit.cryptoatlas.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.raikwaramit.cryptoatlas.domain.model.Coin

data class CoinDto(

    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("symbol") var symbol: String? = null,
    @SerializedName("rank") var rank: Int? = null,
    @SerializedName("is_new") var isNew: Boolean? = null,
    @SerializedName("is_active") var isActive: Boolean? = null,
    @SerializedName("type") var type: String? = null
)

fun CoinDto.toCoin(): Coin {
    return Coin(id, name, rank, isActive, symbol)
}