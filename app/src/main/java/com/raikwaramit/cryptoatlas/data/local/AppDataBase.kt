package com.raikwaramit.cryptoatlas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room database abstract class for creating the data base.
 */
@Database(entities = [FavoriteCoin::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}

