package com.tunepruner.voixt

sealed class Screen(
    val route: String
) {
    object HomeScreen: Screen("home_screen")
    object EditorScreen: Screen("editor_screen")
}