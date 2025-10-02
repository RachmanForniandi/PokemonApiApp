package rachman.forniandi.pokemonapiapp.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import rachman.forniandi.pokemonapiapp.Screen
import rachman.forniandi.pokemonapiapp.data.local.SessionManager
import rachman.forniandi.pokemonapiapp.presentation.home.HomeScreen

@Composable
fun MainScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Home", "Profile")
    val context = LocalContext.current

    val sessionManager = remember { SessionManager(context) }
    val coroutineScope = rememberCoroutineScope()
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Keluar Aplikasi") },
            text = { Text("Apakah kamu yakin ingin keluar dari aplikasi?") },
            confirmButton = {
                TextButton(onClick = { android.os.Process.killProcess(android.os.Process.myPid()) }) {
                    Text("Ya")
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

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
                sessionManager = sessionManager,
                onLogout = {
                    coroutineScope.launch {
                        sessionManager.clearSession()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Main.route) { inclusive = true }
                        }
                    }

                }
            )
        }
    }
}