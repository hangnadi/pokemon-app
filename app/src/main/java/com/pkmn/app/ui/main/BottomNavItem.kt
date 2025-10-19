package com.pkmn.app.ui.main

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val icon: ImageVector,
    val route: String,
)
