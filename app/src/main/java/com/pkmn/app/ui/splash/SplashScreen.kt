package com.pkmn.app.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pkmn.app.navigation.AppRoute
import com.pkmn.app.ui.theme.ColorWhite
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(AppRoute.MainRoute.id) {
            popUpTo(AppRoute.SplashRoute.id) { inclusive = true }
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
            .background(ColorWhite),
        contentAlignment = Alignment.Center
    ) {
        /**
         * @TODO
         * Create Lottie Splash Loading
         */
    }
}



@Preview(showSystemUi = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen(rememberNavController())
}