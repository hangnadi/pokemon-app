package com.pkmn.app.navigation

import com.pkmn.app.ui.splash.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute(val id: String) {

    @Serializable
    object SplashRoute : AppRoute("splash_screen")

    @Serializable
    object HomeRoute : AppRoute("home_screen")

}