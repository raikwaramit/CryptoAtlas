package com.raikwaramit.cryptoatlas.di

import android.content.Context
import androidx.room.Room
import com.raikwaramit.cryptoatlas.common.Constants
import com.raikwaramit.cryptoatlas.data.local.AppDatabase
import com.raikwaramit.cryptoatlas.data.local.FavoriteDao
import com.raikwaramit.cryptoatlas.data.remote.CoinPaprikaApi
import com.raikwaramit.cryptoatlas.data.repository.CoinRepositoryImpl
import com.raikwaramit.cryptoatlas.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module for providing the retrofit api and room database
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder().baseUrl(Constants.HTTP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "favorite").build()
    }

    @Provides
    @Singleton
    fun provideRoomDao(appDatabase: AppDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }
}