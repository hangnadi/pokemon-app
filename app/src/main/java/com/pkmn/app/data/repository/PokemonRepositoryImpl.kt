package com.pkmn.app.data.repository

import com.pkmn.app.data.remote.api.PokemonApiService
import com.pkmn.app.data.remote.model.Ability
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
