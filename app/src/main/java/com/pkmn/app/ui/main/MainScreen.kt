package com.pkmn.app.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pkmn.app.navigation.AppRoute
import com.pkmn.app.ui.home.HomeScreen
import com.pkmn.app.ui.profile.ProfileScreen

@Composable
fun MainScreen(
    rootNavHostController: NavHostController
) {
    val navController = rememberNavController()
    val currentBackStack by rootNavHostController.currentBackStackEntryAsState()
    val currentRoute by remember(currentBackStack) {
        derivedStateOf { currentBackStack?.destination?.route }
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = getNavigationItems(),
                currentRoute = currentRoute,
                onItemSelected = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.HomeRoute.id,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoute.HomeRoute.id) { HomeScreen(navController) }
            composable(AppRoute.ProfileRoute.id) { ProfileScreen(navController) }
        }
    }
}

fun getNavigationItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            icon = Icons.Filled.Home,
            route = AppRoute.HomeRoute.id
        ),
        BottomNavItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            icon = Icons.Filled.Person,
            route = AppRoute.ProfileRoute.id
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewBasicNavigationBar() {
    MainScreen(rememberNavController())
}

