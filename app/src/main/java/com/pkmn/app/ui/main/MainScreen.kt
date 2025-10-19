package com.pkmn.app.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pkmn.app.R
import com.pkmn.app.navigation.AppRoute
import com.pkmn.app.ui.home.HomeScreen
import com.pkmn.app.ui.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    rootNavHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val currentBackStack by rootNavHostController.currentBackStackEntryAsState()
    val currentRoute by remember(currentBackStack) {
        derivedStateOf { currentBackStack?.destination?.route }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = { Text(
                    stringResource(id = R.string.app_name)
                ) },
                actions = {
                    IconButton(
                        onClick = { /* @TODO LOGOUT Feature */ }
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(
                        onClick = { /* @TODO LOGOUT Feature */ }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Log Out"
                        )
                    }
                }
            )
        },
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
fun PreviewMainScreen() {
    MainScreen(rememberNavController())
}

