package com.pkmn.app.data.remote.model

data class PokemonDetailResponse(
    val name: String,
    val abilities: List<AbilityEntity>,
)

data class AbilityEntity(
    val ability: Ability,
    val isHidden: Boolean,
    val slot: Int
)
data class Ability(
    val name: String,
    val url: String,
)
