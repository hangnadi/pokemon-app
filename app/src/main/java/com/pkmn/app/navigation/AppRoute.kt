package com.pkmn.app.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute(val id: String) {

    @Serializable
    object SplashRoute : AppRoute("splash_screen")

    @Serializable
    object HomeRoute : AppRoute("home_screen")

    @Serializable
    object ProfileRoute : AppRoute("profile_screen")

    @Serializable
    object MainRoute : AppRoute("main_screen")

    @Serializable
    object AuthRoute : AppRoute("auth_screen")

    @Serializable
    object LoginRoute : AppRoute("login_screen")

    @Serializable
    object RegisterRoute : AppRoute("register_screen")

    @Serializable
    object DetailRoute : AppRoute("detail_screen/{pokemonName}")

    @Serializable
    object SearchRoute : AppRoute("search_screen")

}