package com.tunepruner.voixt

/**Provides uris for navigation*/
sealed class Screen(
    val route: String
) {
    object HomeScreen: Screen("home_screen")
    object EditorScreen: Screen("editor_screen")
    object SavedVoixts: Screen("saved_voixts")
    object VoixtDrafts: Screen("voixt_drafts")
    object ArchivedVoixts: Screen("archived_voixts")
}