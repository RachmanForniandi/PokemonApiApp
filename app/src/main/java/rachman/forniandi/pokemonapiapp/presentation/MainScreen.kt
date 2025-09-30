package rachman.forniandi.pokemonapiapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import rachman.forniandi.pokemonapiapp.Screen
import rachman.forniandi.pokemonapiapp.presentation.home.HomeScreen

@Composable
fun MainScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Home", "Profile")

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        label = { Text(title) },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) }
                    )
                }
            }
        }
    ) { padding ->
        when (selectedTab) {
            0 -> HomeScreen(navController, Modifier.padding(padding))
            1 -> ProfileScreen(modifier = Modifier.padding(padding),
                onLogout = { navController.navigate(Screen.Login.route){
                    popUpTo(Screen.Main.route) { inclusive = true }
                }
                }
            )
        }
    }
}