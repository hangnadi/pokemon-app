package com.pkmn.app.domain.repository

import com.pkmn.app.data.remote.database.UserEntity

interface UserRepository {
    suspend fun setUserSession(session: String)
    suspend fun getUserSession():String?
    suspend fun clearUserSession()
    suspend fun hasUserSession(): Boolean
    suspend fun registerUser(userEntity: UserEntity): Boolean
    suspend fun loginUser(email: String, password:String): Boolean
    suspend fun getUserByEmail(email: String): UserEntity?

}
