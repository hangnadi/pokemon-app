package com.pkmn.app.data.remote.model

data class PokemonDetailResponse(
    val name: String,
    val abilities: List<Ability>,
)

data class Ability(
    val name: String,
    val url: String,
)
