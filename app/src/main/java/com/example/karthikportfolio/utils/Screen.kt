package com.example.karthikportfolio.utils

import com.example.karthikportfolio.R
import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {
    sealed class BottomScreen(val bTitle: String, val bRoute: String, @DrawableRes val icon: Int) :
        Screen(bTitle, bRoute) {

        object education : BottomScreen(
            "Education", "education", R.drawable.ic_education
        )
        object experience : BottomScreen(
            "Experience", "experience", R.drawable.ic_experience
        )
        object contact : BottomScreen(
            "Contact", "contact", R.drawable.ic_contact
        )
    }
    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screen(dTitle, dRoute) {

        object home : DrawerScreen(
            "Home", "home", R.drawable.ic_home
        )
        object hobbies: DrawerScreen("Hobbies","hobbies", R.drawable.ic_sports)
        object skills: DrawerScreen("Skills","skills", R.drawable.ic_abook)

    }

}

val DrawerScreenList = listOf(
    Screen.DrawerScreen.home,
    Screen.DrawerScreen.hobbies,
    Screen.DrawerScreen.skills
)

val BottomScreenList= listOf(
    Screen.BottomScreen.education,
    Screen.BottomScreen.experience,
    Screen.BottomScreen.contact
)