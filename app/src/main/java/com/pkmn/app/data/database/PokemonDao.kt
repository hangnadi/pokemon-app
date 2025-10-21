package com.pkmn.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getPokemons(limit: Int, offset: Int): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<PokemonEntity>)

    @Query("DELETE FROM pokemon_table")
    suspend fun clearPokemons()
}
