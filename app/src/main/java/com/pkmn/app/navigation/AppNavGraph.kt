package com.pkmn.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pkmn.app.ui.splash.SplashScreen


@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController,
        startDestination = AppRoute.SplashRoute.id
    ) {
        composable(AppRoute.SplashRoute.id) { SplashScreen(navController, modifier) }
    }
}
