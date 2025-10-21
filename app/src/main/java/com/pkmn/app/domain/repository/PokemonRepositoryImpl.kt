package com.pkmn.app.domain.repository

import com.pkmn.app.data.api.PokemonApiService
import com.pkmn.app.data.database.PokemonDao
import com.pkmn.app.domain.model.Pokemon
import com.pkmn.app.utils.NetworkHelper
import com.pkmn.app.utils.toDomain
import com.pkmn.app.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApiService,
    private val dao: PokemonDao,
    private val nh: NetworkHelper
) : PokemonRepository {

    override fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<List<Pokemon>> = flow {
        if (nh.isNetworkConnected()) {
            val remoteResponse = api.getPokemonList(limit, offset)
            val pokemons = remoteResponse.results.map {
                Pokemon(it.name, emptyList())
            }
            emit(pokemons)

            val entities = pokemons.map { it.toEntity() }
            dao.insertAll(entities)
        } else {
            val localData = dao.getPokemons(limit, offset)
            emit(localData.map { it.toDomain() })
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
