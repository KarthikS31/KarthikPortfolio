package com.example.karthikportfolio.utils

import com.example.karthikportfolio.R
import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {

    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screen(dTitle, dRoute) {

        object home : DrawerScreen(
            "Home", "home", R.drawable.ic_home
        )

        object contact : DrawerScreen(
            "Contact", "contact", R.drawable.ic_contact
        )

        object education : DrawerScreen(
            "Education", "education", R.drawable.ic_education
        )

        object experience : DrawerScreen(
            "Experience", "experience", R.drawable.ic_experience
        )
    }

}

val DrawerScreenList = listOf(
    Screen.DrawerScreen.home,
    Screen.DrawerScreen.contact,
    Screen.DrawerScreen.education,
    Screen.DrawerScreen.experience
)