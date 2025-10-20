package com.pkmn.app.domain.repository

import com.pkmn.app.data.remote.database.UserEntity

interface UserRepository {
    suspend fun hasLoggedInUser(): Boolean
    suspend fun registerUser(userEntity: UserEntity): Boolean

    suspend fun getUserByEmail(email: String): UserEntity?

}
