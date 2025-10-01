package rachman.forniandi.pokemonapiapp.presentation

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import rachman.forniandi.pokemonapiapp.Screen
import rachman.forniandi.pokemonapiapp.data.local.SessionManager
import rachman.forniandi.pokemonapiapp.presentation.home.HomeScreen

@Composable
fun MainScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Home", "Profile")
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    Scaffold(
        modifier = Modifier.background(Color.White),
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
            0 -> HomeScreen(
                navController = navController,
                modifier = Modifier
                    .padding(padding)
                    .background(Color.White)
            )

            1 -> ProfileScreen(
                modifier = Modifier
                    .padding(padding)
                    .background(Color.White),
                navController = navController,
                sessionManager = SessionManager(LocalContext.current),
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                }
            )
        }
    }
}