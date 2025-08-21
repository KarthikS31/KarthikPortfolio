package com.example.karthikportfolio.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.karthikportfolio.views.Contact
import com.example.karthikportfolio.views.Eduaction
import com.example.karthikportfolio.views.Experience
import com.example.karthikportfolio.views.Home


@Composable
fun NavigationHost(navController: NavController, pd: PaddingValues) {

    NavHost(
        navController as NavHostController,
        startDestination = Screen.DrawerScreen.home.route,
        modifier = Modifier.padding(paddingValues = pd)
    ) {
        composable(Screen.DrawerScreen.home.route) {
            Home()
        }
        composable(Screen.DrawerScreen.contact.route) {
            Contact()
        }
        composable(Screen.DrawerScreen.education.route) {
            Eduaction()
        }
        composable(Screen.DrawerScreen.experience.route) {
            Experience()
        }
    }

}