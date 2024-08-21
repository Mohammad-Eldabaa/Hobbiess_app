package com.example.hobbiess.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import eu.tutorials.hobbiess.Data.Wish
import kotlinx.coroutines.flow.Flow


@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addWish(wishEntity: Wish)

    @Query("SELECT * FROM wish_table")
    abstract fun GetallWishs(): Flow<List<Wish>>

    @Delete
    abstract fun deleteWish(wishEntity: Wish)

    @Query("SELECT * FROM wish_table WHERE id = :id")
    abstract fun getWishById(id: Long): Flow<Wish>

    @Query("DELETE FROM wish_table WHERE id = :id")
    abstract fun deleteWishById(id: Long)

    @Update
    abstract fun updateWish(wishEntity: Wish)


}