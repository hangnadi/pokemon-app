package com.pkmn.app.domain.model

import com.pkmn.app.data.remote.model.Ability

data class Pokemon(
    val name: String,
    val abilities: List<Ability>
)