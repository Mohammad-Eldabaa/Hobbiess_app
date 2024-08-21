package eu.tutorials.hobbiess

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(viewModel: WishViewModel = viewModel(),
               navController: NavHostController = rememberNavController()){
    NavHost(
        navController= navController,
        startDestination = Screen.HomeScreen.route
    ){
        viewModel.IsEdit = false

        composable(Screen.HomeScreen.route){
            HomeView(navController, viewModel)
        }

        composable(Screen.AddScreen.route+"/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ){

            val id =if (it.arguments?.getLong("id") != null) it.arguments!!.getLong("id") else 0L
           AddEditDetailView(id = id, viewModel = viewModel , navController = navController)
        }
    }
}