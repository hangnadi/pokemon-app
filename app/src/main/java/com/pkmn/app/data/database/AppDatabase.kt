package com.pkmn.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun userDao(): UserDao
}