package com.pkmn.app.domain.repository

import com.pkmn.app.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<List<Pokemon>>
    suspend fun getPokemonDetail(name: String): Pokemon

}