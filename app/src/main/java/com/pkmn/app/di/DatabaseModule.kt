package com.pkmn.app.di

import android.content.Context
import androidx.room.Room
import com.pkmn.app.data.database.AppDatabase
import com.pkmn.app.data.database.PokemonDao
import com.pkmn.app.data.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pokemon_db"
        ).build()

    @Provides
    fun providePokemonDao(db: AppDatabase): PokemonDao = db.pokemonDao()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

}
