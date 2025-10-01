package rachman.forniandi.pokemonapiapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun PokeAppNav(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = modifier
    ) {
        //check session
        /*composable(Screen.CheckSession.route) {
            CheckSession(navController = navController)
        }*/

        //login
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true } // hapus stack login
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        //register
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = { navController.popBackStack() } // kembali ke login
            )
        }

        //main
        composable(Screen.Main.route) {
            MainScreen(navController = navController)
        }

        //detail
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            DetailScreen(name = name)
        }
    }
}