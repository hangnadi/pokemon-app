package com.pkmn.app.domain.repository

interface UserRepository {
    suspend fun hasLoggedInUser(): Boolean
}
