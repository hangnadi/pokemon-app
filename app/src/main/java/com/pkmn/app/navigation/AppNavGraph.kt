package com.pkmn.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pkmn.app.ui.home.HomeScreen
import com.pkmn.app.ui.login.LoginScreen
import com.pkmn.app.ui.profile.ProfileScreen
import com.pkmn.app.ui.register.RegisterScreen
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

        composable(AppRoute.SplashRoute.id) {
            SplashScreen(
                onNavigateToAuth = {
                    navController.navigate("auth") {
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

        navigation(
            startDestination = AppRoute.LoginRoute.id,
            route = AppRoute.AuthRoute.id
        ) {
            composable(AppRoute.LoginRoute.id) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(AppRoute.MainRoute.id) {
                            popUpTo(AppRoute.AuthRoute.id) {
                                inclusive = true
                            }
                        }
                    },
                    onRegister = { navController.navigate(AppRoute.RegisterRoute.id) }
                )
            }
            composable(AppRoute.RegisterRoute.id) {
                RegisterScreen(onRegisterSuccess = { navController.popBackStack() })
            }
        }
    }
}
