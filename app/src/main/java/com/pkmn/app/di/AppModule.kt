package com.pkmn.app.di

import android.content.Context
import android.content.SharedPreferences
import com.pkmn.app.data.remote.api.PokemonApiService
import com.pkmn.app.data.remote.database.UserDao
import com.pkmn.app.data.repository.PokemonRepositoryImpl
import com.pkmn.app.data.repository.UserRepositoryImpl
import com.pkmn.app.domain.repository.PokemonRepository
import com.pkmn.app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApiService): PokemonRepository =
        PokemonRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao, sp: SharedPreferences): UserRepository =
        UserRepositoryImpl(userDao, sp)

}