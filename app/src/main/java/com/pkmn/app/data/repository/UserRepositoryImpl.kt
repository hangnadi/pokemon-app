package com.pkmn.app.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.pkmn.app.data.remote.database.UserDao
import com.pkmn.app.data.remote.database.UserEntity
import com.pkmn.app.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sp: SharedPreferences,
) : UserRepository {

    companion object {
        private const val KEY_LOGGED_IN_USER = "logged_in_user"
    }

    override suspend fun setUserSession(session: String) {
        sp.edit { putString(KEY_LOGGED_IN_USER, session) }
    }

    override suspend fun getUserSession(): String? {
        return sp.getString(KEY_LOGGED_IN_USER, "")
    }

    override suspend fun clearUserSession() {
        sp.edit { remove(KEY_LOGGED_IN_USER) }
    }

    override suspend fun hasUserSession(): Boolean {
        return sp.contains(KEY_LOGGED_IN_USER)
    }

    override suspend fun registerUser(userEntity: UserEntity): Boolean {
        val result = userDao.insertUser(userEntity)
        return result != -1L
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        val result = userDao.loginUser(email, password)
        return result != null
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }

}