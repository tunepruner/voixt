package com.tunepruner.voixt

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tunepruner.voixt.editor.ui.EditScreen
import com.tunepruner.voixt.editor.voixtlist.VoixtListScreen
import com.tunepruner.voixt.editor.voixtlist.VoixtListType
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
        composable(route = Screen.SavedVoixts.route) {
            VoixtListScreen(navController, type = VoixtListType.SavedVoixts)
        }
        composable(route = Screen.VoixtDrafts.route) {
            VoixtListScreen(navController, type = VoixtListType.VoixtDrafts)
        }
        composable(route = Screen.ArchivedVoixts.route) {
            VoixtListScreen(navController, type = VoixtListType.ArchivedVoixts)
        }
    }
}