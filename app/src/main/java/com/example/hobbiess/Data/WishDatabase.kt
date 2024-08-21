package com.example.hobbiess.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import eu.tutorials.hobbiess.Data.Wish


@Database(entities = [Wish::class], version = 1, exportSchema = false)
abstract class WishDatabase: RoomDatabase() {
    abstract fun wishDao(): WishDao
}