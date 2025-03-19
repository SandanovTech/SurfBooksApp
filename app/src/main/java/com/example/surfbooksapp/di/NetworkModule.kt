package com.example.surfbooksapp.di

import com.example.surfbooksapp.data.RepositoryImpl
import com.example.surfbooksapp.data.network.api.GoogleApi
import com.example.surfbooksapp.data.network.repository.RemoteDataSource
import com.example.surfbooksapp.data.network.repository.RemoteDataSourceImpl
import com.example.surfbooksapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(api: GoogleApi) : RemoteDataSource = RemoteDataSourceImpl(api)


    @Provides
    @Singleton
    fun provideRepositoryImpl(remoteDataSource: RemoteDataSourceImpl) : Repository = RepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGoogleApi(retrofit: Retrofit): GoogleApi {
        return retrofit.create(GoogleApi::class.java)
    }
}