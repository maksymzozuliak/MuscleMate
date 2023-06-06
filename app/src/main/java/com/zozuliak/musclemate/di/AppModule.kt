package com.zozuliak.musclemate.di

import com.zozuliak.musclemate.BASE_URL
import com.zozuliak.musclemate.data.internet.ServerApi
import com.zozuliak.musclemate.data.internet.ServerRepository
import com.zozuliak.musclemate.data.internet.ServerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // ServerApi

    @Singleton
    @Provides
    fun provideServerApi(): ServerApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServerApi::class.java)

    @Singleton
    @Provides
    fun provideServerRepository(api: ServerApi): ServerRepository =
        ServerRepositoryImpl(api)


}