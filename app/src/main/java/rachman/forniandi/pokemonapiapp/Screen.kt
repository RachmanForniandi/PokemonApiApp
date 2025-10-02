package rachman.forniandi.pokemonapiapp

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")
    object Detail : Screen("detail")
}