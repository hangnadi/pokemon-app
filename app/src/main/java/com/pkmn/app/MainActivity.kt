package com.pkmn.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pkmn.app.navigation.AppNavGraph
import com.pkmn.app.navigation.AppRoute
import com.pkmn.app.ui.main.BottomNavBar
import com.pkmn.app.ui.main.BottomNavItem
import com.pkmn.app.ui.theme.PokemonAPITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonAPITheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    MainCanvas()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCanvas() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    val currentRoute by remember(currentBackStack) {
        derivedStateOf { currentBackStack?.destination?.route }
    }

    val shouldShowBottomBar by remember(currentRoute) {
        derivedStateOf {
            isBottomNavRoute(currentRoute)
        }
    }

    val canNavigateBack by remember(currentBackStack) {
        derivedStateOf {
            currentBackStack != null &&
                    currentRoute != null &&
                    currentRoute != AppRoute.HomeRoute.id
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (shouldShowBottomBar) {
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
            }
        },
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomNavBar(
                    items = getNavigationItems(),
                    currentRoute = currentRoute,
                    onItemSelected = { route ->
                        navController.navigate(route) {
                            if (route != currentRoute) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        AppNavGraph(navController, modifier = Modifier.padding(innerPadding))
    }
}

fun isBottomNavRoute(route: String?): Boolean {
    return route == AppRoute.HomeRoute.id ||
            route == AppRoute.ProfileRoute.id
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
