package com.pkmn.app.domain.repository

import com.pkmn.app.data.api.PokemonApiService
import com.pkmn.app.domain.model.Pokemon
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
                abilities = emptyList()
            )
        }
    }

    override suspend fun getPokemonDetail(name: String): Pokemon {
        val response = api.getPokemonDetail(name)
        return Pokemon(
            name = response.name,
            abilities = response.abilities.map {
                it.ability.name
            }
        )
    }

}
