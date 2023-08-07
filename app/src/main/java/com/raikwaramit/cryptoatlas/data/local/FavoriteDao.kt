package com.raikwaramit.cryptoatlas.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favoritecoin")
    suspend fun getAll(): List<FavoriteCoin>

    @Upsert
    suspend fun insert(favoriteCoin: FavoriteCoin)

    @Delete
    suspend fun delete(favoriteCoin: FavoriteCoin)
}