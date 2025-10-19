package com.pkmn.app.di

import com.pkmn.app.data.remote.api.PokemonApiService
import com.pkmn.app.data.repository.PokemonRepositoryImpl
import com.pkmn.app.data.repository.UserRepositoryImpl
import com.pkmn.app.domain.repository.PokemonRepository
import com.pkmn.app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonApiService =
        retrofit.create(PokemonApiService::class.java)

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApiService): PokemonRepository =
        PokemonRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository =
        UserRepositoryImpl()

}