package com.pkmn.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pkmn.app.ui.home.HomeScreen
import com.pkmn.app.ui.profile.ProfileScreen
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

        navigation(
            startDestination = AppRoute.HomeRoute.id,
            route = AppRoute.MainRoute.id
        ) {
            composable(AppRoute.HomeRoute.id) { HomeScreen(navController) }
            composable(AppRoute.ProfileRoute.id) { ProfileScreen(navController) }
        }
    }
}
