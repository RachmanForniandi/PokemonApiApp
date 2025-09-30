package rachman.forniandi.pokemonapiapp

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")
    object Detail : Screen("detail/{name}") {
        fun createRoute(name: String) = "detail/$name"
    }
}