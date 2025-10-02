package rachman.forniandi.pokemonapiapp.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import rachman.forniandi.pokemonapiapp.Screen

@Composable
fun LoginScreen(navController: NavController,
    viewModel: AuthViewModel= hiltViewModel(),
                ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isLoggedIn by viewModel.loginState.collectAsState()

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate(Screen.Main.route) {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                viewModel.login(email, password)
                    }else{
                        scope.launch {
                            snackBarHostState.showSnackbar("Email dan password tidak boleh kosong")
                        }
                    }
                }
            )
            {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(onClick = {navController.navigate(Screen.Register.route)}) {
                Text("Belum punya akun? Register")
            }
        }
    }
}
