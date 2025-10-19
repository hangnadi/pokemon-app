package com.pkmn.app.di

import android.content.Context
import androidx.room.Room
import com.pkmn.app.data.remote.database.PokemonDao
import com.pkmn.app.data.remote.database.PokemonDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): PokemonDatabase =
        Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "pokemon_db"
        ).build()

    @Provides
    fun providePokemonDao(db: PokemonDatabase): PokemonDao = db.pokemonDao()
}
