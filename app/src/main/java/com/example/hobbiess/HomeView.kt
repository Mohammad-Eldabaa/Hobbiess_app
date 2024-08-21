package eu.tutorials.hobbiess

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import eu.tutorials.hobbiess.Data.DummyWish
import eu.tutorials.hobbiess.Data.Wish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
){
    val context = LocalContext.current
    Scaffold(
        topBar = {AppBarView(title= "WishList", {
         Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()

        })},
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                backgroundColor = Color.Black,
                onClick = {
                    val id = 0
                    navController.navigate(Screen.AddScreen.route + "/$id")
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

    ) {
        val wishList by viewModel.getAllWishs.collectAsState(emptyList())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            items(wishList, key = {wish ->wish.id}){
                wish ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )
                SwipeToDismiss(state = dismissState,
                    background = {
                        val color by animateColorAsState(if (dismissState.dismissDirection == DismissDirection.StartToEnd) Color.Red else Color.White, label ="")

                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterStart){
                            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                        }
                    } ,
                    directions = setOf(DismissDirection.StartToEnd),
                    dismissThresholds = { FractionalThreshold(0.5f) },
                    dismissContent = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    })




            }
        }
    }

}




@Composable
fun WishItem(wish: Wish, onClick: () -> Unit){
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .padding(top = 4.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onClick()
        },
        elevation = 10.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)){
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.heightIn(16.dp))
            Text(text = wish.description)
        }
    }
}