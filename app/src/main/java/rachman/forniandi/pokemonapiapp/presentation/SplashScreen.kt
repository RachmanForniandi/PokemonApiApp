package rachman.forniandi.pokemonapiapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import rachman.forniandi.pokemonapiapp.R
import rachman.forniandi.pokemonapiapp.Screen
import rachman.forniandi.pokemonapiapp.data.local.SessionManager

@Composable
fun SplashScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    val isLoggedIn by sessionManager.isLoggedIn.collectAsState(initial = false)


    LaunchedEffect(Unit) {
        delay(3000)

        if (isLoggedIn){
            navController.navigate(Screen.Main.route) {
                popUpTo("splash") { inclusive = true }  // supaya splash tidak kembali
            }
        }else{
            navController.navigate(Screen.Login.route) {
                popUpTo("splash") { inclusive = true }  // supaya splash tidak kembali
            }
        }

    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.poke_ball))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .size(200.dp)
            )
        }
    }
}