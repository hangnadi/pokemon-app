package com.pkmn.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pkmn.app.navigation.AppNavGraph
import com.pkmn.app.navigation.AppRoute
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

@Composable
fun MainCanvas() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    val currentRoute by remember(currentBackStack) {
        derivedStateOf { currentBackStack?.destination?.route }
    }

    val canNavigateBack by remember(currentBackStack) {
        derivedStateOf {
            currentBackStack != null &&
                    currentRoute != null &&
                    currentRoute != AppRoute.HomeRoute.id
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        AppNavGraph(navController, modifier = Modifier.padding(innerPadding))
    }
}
