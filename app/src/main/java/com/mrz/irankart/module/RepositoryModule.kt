package com.mrz.irankart.module


import com.mrz.irankart.repository.MovieRepository
import com.mrz.irankart.repository.services.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCattleRepository(apiService: APIService):MovieRepository{
        return MovieRepository(apiService)
    }

}