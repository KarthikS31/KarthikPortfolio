package com.example.karthikportfolio.utils

import Hobbies
import Home
import Skills
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.karthikportfolio.views.Contact
import com.example.karthikportfolio.views.Education
import com.example.karthikportfolio.views.Experience


@Composable
fun NavigationHost(navController: NavController, pd: PaddingValues) {

    NavHost(
        navController as NavHostController,
        startDestination = Screen.DrawerScreen.home.route,
        modifier = Modifier.padding(paddingValues = pd).fillMaxSize()
    ) {
        composable(Screen.DrawerScreen.home.route) {
            Home()
        }
        composable(Screen.DrawerScreen.hobbies.route) {
            Hobbies()
        }
        composable(Screen.DrawerScreen.skills.route) {
            Skills()
        }

        composable(Screen.BottomScreen.contact.route) {
            Contact()
        }
        composable(Screen.BottomScreen.education.route) {
            Education()
        }
        composable(Screen.BottomScreen.experience.route) {
            Experience()
        }

    }

}