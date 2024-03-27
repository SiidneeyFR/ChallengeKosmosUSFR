package com.example.challengekosmosusfr.di

import com.example.challengekosmosusfr.data.RickAndMortyRepository
import com.example.challengekosmosusfr.data.remote.RemoteDataSource
import com.example.challengekosmosusfr.data.remote.RickAndMortyServices
import com.example.challengekosmosusfr.utils.ConstantsChallangeKosmos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Singleton
    @Provides
    fun provideConstantsChallangeKosmos() : ConstantsChallangeKosmos.Companion =
        ConstantsChallangeKosmos.Companion

    @Singleton
    @Provides
    fun provideRickAndMortyServices(
        constantsChallangeKosmos: ConstantsChallangeKosmos.Companion
    ) : RickAndMortyServices =
        Retrofit.Builder()
            .baseUrl(constantsChallangeKosmos.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyServices::class.java)

    @Singleton
    @Provides
    fun provideRickAndMortyRepository(
        rickAndMortyServices: RickAndMortyServices
    ) : RickAndMortyRepository =
        RickAndMortyRepository(
            remoteDataSource = RemoteDataSource(
                rickAndMortyServices = rickAndMortyServices
            )
        )
}