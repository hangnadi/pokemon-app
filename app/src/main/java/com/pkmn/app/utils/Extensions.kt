package com.pkmn.app.utils

import com.pkmn.app.data.database.PokemonEntity
import com.pkmn.app.domain.model.Pokemon

fun PokemonEntity.toDomain() = Pokemon(name = name, abilities = emptyList())
fun Pokemon.toEntity() = PokemonEntity(name = name)