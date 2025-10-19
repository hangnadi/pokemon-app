package com.pkmn.app.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pkmn.app.ui.theme.ColorWhite

@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    currentRoute: String?,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = Color.Transparent,
        contentColor = ColorWhite,
        tonalElevation = 5.dp,

        ) {
        items.forEachIndexed { index, item ->
            val selected = currentRoute == item.route

            NavigationBarItem(
                modifier = modifier.padding(top = 20.dp),
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                selected = selected,
                onClick = { onItemSelected(item.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ColorWhite,
                    selectedTextColor = ColorWhite,
                    unselectedIconColor = ColorWhite.copy(alpha = 0.4f),
                    unselectedTextColor = ColorWhite.copy(alpha = 0.4f),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

