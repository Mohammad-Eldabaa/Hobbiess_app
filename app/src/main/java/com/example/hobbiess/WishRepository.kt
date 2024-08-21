package eu.tutorials.hobbiess

import com.example.hobbiess.Data.WishDao
import eu.tutorials.hobbiess.Data.Wish
import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun addWish(wish: Wish) {
        wishDao.addWish(wish)
    }

    fun getAllWishes(): Flow<List<Wish>> = wishDao.GetallWishs()

    fun getWishById(id: Long): Flow<Wish> = wishDao.getWishById(id)

    fun updateWish(wish: Wish) = wishDao.updateWish(wish)

    fun deleteWish(wish: Wish) = wishDao.deleteWish(wish)

    fun deleteWishById(id: Long) = wishDao.deleteWishById(id)
}