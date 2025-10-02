package rachman.forniandi.pokemonapiapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import rachman.forniandi.pokemonapiapp.Screen
import rachman.forniandi.pokemonapiapp.presentation.auth.LoginScreen
import rachman.forniandi.pokemonapiapp.presentation.auth.RegisterScreen
import rachman.forniandi.pokemonapiapp.presentation.detail.DetailScreen

@Composable
fun PokeAppNav(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        //modifier = modifier
    ) {

        //splash
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        //login
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController
            )
        }

        //register
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }

        //main
        composable(Screen.Main.route) {
            MainScreen(navController = navController)
        }

        //detail
        composable(Screen.Detail.route + "/{pokemonName}") { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
            DetailScreen(
                navController = navController,
                name = pokemonName
            )
        }

    }
}