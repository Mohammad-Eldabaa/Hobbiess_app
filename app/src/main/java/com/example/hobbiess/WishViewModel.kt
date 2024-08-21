package eu.tutorials.hobbiess

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hobbiess.Data.Graph
import eu.tutorials.hobbiess.Data.Wish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
):ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")
    var IsEdit = false


    fun onWishTitleChanged(newString:String){
        wishTitleState = newString
    }

    fun onWishDescriptionChanged(newString: String){
        wishDescriptionState = newString
    }

    lateinit var getAllWishs: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishs = wishRepository.getAllWishes()
        }
    }

    fun addWish(wish: Wish) = viewModelScope.launch(Dispatchers.IO) {
        wishRepository.addWish(wish)
    }

    fun deleteWish(wish: Wish) = viewModelScope.launch (Dispatchers.IO) {
        wishRepository.deleteWish(wish)
    }

    fun updateWish(wish: Wish) = viewModelScope.launch(Dispatchers.IO) {
        wishRepository.updateWish(wish)
    }

    fun getWishById(id: Long): Flow<Wish> {
        return wishRepository.getWishById(id)
    }

    fun deleteWishbyId(id:Long) = viewModelScope.launch (Dispatchers.IO) {
        wishRepository.deleteWishById(id)
    }
}