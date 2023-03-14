package com.tunepruner.voixt.editor.voixtlist

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun VoixtListScreen(navController: NavController, type: VoixtListType) {
//    val viewModel = when (type) {
//        VoixtListType.SavedVoixts -> TODO()
//        VoixtListType.VoixtDrafts -> TODO()
//        VoixtListType.ArchivedVoixts -> TODO()
//    }

    Button(onClick = { navController.navigateUp() }) {
        Text("Placeholder")
    }
}

sealed interface VoixtListType {
    object SavedVoixts : VoixtListType
    object VoixtDrafts : VoixtListType
    object ArchivedVoixts : VoixtListType
}