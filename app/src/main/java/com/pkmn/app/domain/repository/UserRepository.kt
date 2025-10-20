package com.pkmn.app.domain.repository

interface UserRepository {
    suspend fun hasLoggedInUser(): Boolean
    suspend fun userRegister(name: String, email: String, password: String): Boolean

}
