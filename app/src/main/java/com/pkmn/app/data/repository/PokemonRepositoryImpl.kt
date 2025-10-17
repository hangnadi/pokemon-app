package com.pkmn.app.data.repository

import com.pkmn.app.data.remote.api.PokemonApiService
import com.pkmn.app.domain.model.Pokemon
import com.pkmn.app.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApiService
) : PokemonRepository {

    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): List<Pokemon> {
        val response = api.getPokemonList(limit, offset)
        return response.results.map {
            Pokemon(
                name = it.name,
                abilities = emptyList() // detail not loaded here
            )
        }
    }

    override suspend fun getPokemonDetail(name: String): Pokemon {
        val detail = api.getPokemonDetail(name)
        return Pokemon(
            name = detail.name,
            abilities = detail.abilities.map { it.ability.name }
        )
    }

}
