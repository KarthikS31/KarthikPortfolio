package com.example.karthikportfolio.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.karthikportfolio.utils.Screen

class homePageViewModel : ViewModel() {

    var _currentScreen: MutableState<Screen> = mutableStateOf(Screen.DrawerScreen.home)

    val currentScreen = _currentScreen

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}