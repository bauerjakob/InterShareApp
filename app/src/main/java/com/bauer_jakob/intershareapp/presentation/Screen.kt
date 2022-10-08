package com.bauer_jakob.intershareapp.presentation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
}