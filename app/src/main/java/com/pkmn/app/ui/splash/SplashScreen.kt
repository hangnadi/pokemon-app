package com.pkmn.app.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pkmn.app.ui.theme.ColorWhite
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToAuth: () -> Unit,
    onNavigateToMain: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {

    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(Unit) {
        delay(3000)
        if (isLoggedIn == true) onNavigateToMain()
        else if (isLoggedIn == false) onNavigateToAuth()
    }

    Box(
        modifier = modifier.fillMaxSize()
            .background(ColorWhite),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello World!",
            modifier = modifier
        )
        /**
         * @TODO
         * Create Lottie Splash Loading
         */
    }
}



@Preview(showSystemUi = true)
@Composable
fun PreviewSplashScreen() {

}