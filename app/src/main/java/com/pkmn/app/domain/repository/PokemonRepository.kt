package com.pkmn.app.domain.repository

import com.pkmn.app.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon>
    suspend fun getPokemonDetail(name: String): Pokemon
}