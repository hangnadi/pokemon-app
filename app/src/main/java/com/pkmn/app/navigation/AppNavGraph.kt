package com.pkmn.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.pkmn.app.ui.detail.DetailScreen
import com.pkmn.app.ui.home.HomeScreen
import com.pkmn.app.ui.login.LoginScreen
import com.pkmn.app.ui.profile.ProfileScreen
import com.pkmn.app.ui.register.RegisterScreen
import com.pkmn.app.ui.search.SearchScreen
import com.pkmn.app.ui.splash.SplashScreen


@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController,
        startDestination = AppRoute.SplashRoute.id,
        modifier = modifier
    ) {
        composable(AppRoute.HomeRoute.id) { HomeScreen(navController) }
        composable(AppRoute.ProfileRoute.id) { ProfileScreen(navController) }
        composable(AppRoute.SearchRoute.id) { SearchScreen(navController) }
        composable(
            route = AppRoute.DetailRoute.id,
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) {
            DetailScreen()
        }

        composable(AppRoute.SplashRoute.id) {
            SplashScreen(
                onNavigateToAuth = {
                    navController.navigate(AppRoute.LoginRoute.id) {
                        popUpTo(AppRoute.SplashRoute.id) {
                            inclusive = true
                        }
                    } },
                onNavigateToMain = {
                    navController.navigate(AppRoute.HomeRoute.id) {
                        popUpTo(AppRoute.SplashRoute.id) {
                            inclusive = true
                        }
                    } },
            )
        }

        composable(AppRoute.LoginRoute.id) { LoginScreen(navController) }

        composable(AppRoute.RegisterRoute.id) { RegisterScreen(navController) }
    }
}
