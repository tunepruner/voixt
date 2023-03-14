package com.tunepruner.voixt

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tunepruner.voixt.editor.ui.EditScreen
import com.tunepruner.voixt.ui.HomeScreen


@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.EditorScreen.route) {
            EditScreen(false, navController)
        }
//        composable(route = Screen.SelectionScreen) {
//            EditScreen(true, navController)
//        }
    }
}

@Preview
@Composable
fun NavPreview() {
    Navigation()
}