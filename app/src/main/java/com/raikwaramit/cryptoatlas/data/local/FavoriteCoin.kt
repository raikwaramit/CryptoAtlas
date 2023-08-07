package com.raikwaramit.cryptoatlas.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing the room data base table.
 *
 * @property coinId Id of the crypto coin.
 * @property coinName Name of the crypto coin.
 * @property symbol Symbol of the crypto coin.
 * @property isActive Tells if the coin is active or not.
 */
@Entity
data class FavoriteCoin(
    @PrimaryKey @ColumnInfo(name = "coin_id") val coinId: String,
    @ColumnInfo(name = "coin_name") val coinName: String?,
    @ColumnInfo(name = "coin_symbol") val symbol: String?,
    @ColumnInfo(name = "is_active") val isActive: Boolean?,
)