package com.example.hobbiess.Data

import android.content.Context
import androidx.room.Room
import eu.tutorials.hobbiess.WishRepository

object Graph {

    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(database.wishDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context,WishDatabase::class.java,"wish.db").build()
    }
}