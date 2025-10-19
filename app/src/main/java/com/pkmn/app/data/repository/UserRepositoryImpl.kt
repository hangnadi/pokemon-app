package com.pkmn.app.data.repository

import com.pkmn.app.domain.repository.UserRepository

class UserRepositoryImpl() : UserRepository {
    override suspend fun hasLoggedInUser(): Boolean {
        /**
         * @TODO
         * Implement with room
         */
        return true
    }
}