package com.pkmn.app.data.repository

import com.pkmn.app.data.remote.database.UserDao
import com.pkmn.app.data.remote.database.UserEntity
import com.pkmn.app.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun hasLoggedInUser(): Boolean {
        /**
         * @TODO
         * Implement with room
         */
        return false
    }

    override suspend fun registerUser(userEntity: UserEntity): Boolean {
        val result = userDao.insertUser(userEntity)
        return result != -1L
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }


}