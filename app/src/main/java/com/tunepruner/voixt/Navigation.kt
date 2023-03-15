package com.tunepruner.voixt

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.tunepruner.voixt.editor.ui.EditScreen
import com.tunepruner.voixt.editor.voixtlist.VoixtListScreen
import com.tunepruner.voixt.editor.voixtlist.VoixtListType
import com.tunepruner.voixt.ui.HomeScreen
import com.tunepruner.voixt.ui.TopBar

const val TAG = "12345"

val targetTopBarHeights = mapOf(
    Screen.HomeScreen to 200.dp,
    Screen.SavedVoixts to 100.dp,
    Screen.ArchivedVoixts to 100.dp,
    Screen.VoixtDrafts to 100.dp,
    Screen.EditorScreen to 210.dp
)

val targetTopBarIconSizes = mapOf(
    Screen.HomeScreen to 80.dp,
    Screen.SavedVoixts to 40.dp,
    Screen.ArchivedVoixts to 40.dp,
    Screen.VoixtDrafts to 40.dp,
    Screen.EditorScreen to 30.dp
)

val targetTopBarTextSizes = mapOf(
    Screen.HomeScreen to 70.sp,
    Screen.SavedVoixts to 40.sp,
    Screen.ArchivedVoixts to 40.sp,
    Screen.VoixtDrafts to 40.sp,
    Screen.EditorScreen to 40.sp
)

val topBarAnimationDuration = 200

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    navController: NavHostController,
    navState: Pair<Screen?, Screen?>,
    setNavStateTo: (Screen) -> Unit
) {
    Log.d("12345", "Navigation() called")


    val currentTopBarHeight = getAnimatedTopBarHeight(navState)
    val currentTopBarIconSize = getAnimatedTopBarIconSize(navState)
    val currentTopBarTextSize = getAnimatedTopBarTextSize(navState)

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            Scaffold(topBar = {
                MyAnimatedVisibility(visible = navState.second is Screen.HomeScreen, content = {
                    TopBar(
                        screen = navState.second ?: Screen.HomeScreen,
                        currentHeight = currentTopBarHeight,
                        currentIconSize = currentTopBarIconSize,
                        currentTextSize = currentTopBarTextSize.sp
                    )
                })
            }, content = {
                LaunchedEffect(key1 = true, block = {
                    setNavStateTo(Screen.HomeScreen)
                })
                MyAnimatedVisibility(visible = navState.second is Screen.HomeScreen, content = {
                    HomeScreen(navController = navController, modifier = Modifier.padding(it))
                })
            })
        }
        composable(route = Screen.EditorScreen.route) {
            Scaffold(topBar = {
                MyAnimatedVisibility(visible = navState.second is Screen.EditorScreen, content = {
                    TopBar(
                        screen = navState.second ?: Screen.EditorScreen,
                        currentHeight = currentTopBarHeight,
                        currentIconSize = currentTopBarIconSize,
                        currentTextSize = currentTopBarTextSize.sp
                    )
                })
            }, content = {
                LaunchedEffect(key1 = true, block = {
                    setNavStateTo(Screen.EditorScreen)
                })
                MyAnimatedVisibility(visible = navState.second is Screen.EditorScreen, content = {
                    EditScreen(
                        false,
                        navController = navController,
                        modifier = Modifier.padding(it)
                    )
                })
            })
        }
        composable(route = Screen.SavedVoixts.route) {
            Scaffold(topBar = {
                TopBar(
                    screen = navState.second ?: Screen.HomeScreen,
                    currentHeight = currentTopBarHeight,
                    currentIconSize = currentTopBarIconSize,
                    currentTextSize = currentTopBarTextSize.sp
                )
            }, content = {
                LaunchedEffect(key1 = true, block = {
                    setNavStateTo(Screen.SavedVoixts)
                })
                VoixtListScreen(
                    navController = navController,
                    type = VoixtListType.SavedVoixts,
                    modifier = Modifier.padding(it)
                )
            })
        }
        composable(route = Screen.VoixtDrafts.route) {
            Scaffold(topBar = {
                TopBar(
                    screen = navState.second ?: Screen.HomeScreen,
                    currentHeight = currentTopBarHeight,
                    currentIconSize = currentTopBarIconSize,
                    currentTextSize = currentTopBarTextSize.sp
                )
            }, content = {
                LaunchedEffect(key1 = true, block = {
                    setNavStateTo(Screen.VoixtDrafts)

                })
                VoixtListScreen(
                    navController, type = VoixtListType.VoixtDrafts, Modifier.padding(it)
                )
            })
        }
        composable(route = Screen.ArchivedVoixts.route) {
            Scaffold(topBar = {
                TopBar(
                    screen = navState.second ?: Screen.HomeScreen,
                    currentHeight = currentTopBarHeight,
                    currentIconSize = currentTopBarIconSize,
                    currentTextSize = currentTopBarTextSize.sp
                )
            }, content = {
                LaunchedEffect(key1 = true, block = {
                    setNavStateTo(Screen.ArchivedVoixts)
                })
                VoixtListScreen(
                    navController, type = VoixtListType.ArchivedVoixts, Modifier.padding(it)
                )
            })
        }
    }
}

@Composable
private fun getAnimatedTopBarHeight(navState: Pair<Screen?, Screen?>): Float {
    val fallbackHeight = 1000f // TODO better solution please

    val topBarAnimation = keyframes {
        durationMillis = topBarAnimationDuration
        (targetTopBarHeights[navState.first]?.value ?: fallbackHeight) at 0 with LinearEasing
        (targetTopBarHeights[navState.second]?.value
            ?: fallbackHeight) at topBarAnimationDuration with LinearEasing
    }

    return animateFloatAsState(
        targetValue = targetTopBarHeights[navState.second]?.value ?: fallbackHeight,
        animationSpec = topBarAnimation
    ).value
}

@Composable
fun getAnimatedTopBarIconSize(navState: Pair<Screen?, Screen?>): Float {
    val fallbackHeight = 1000f // TODO better solution please

    val iconAnimation = keyframes {
        durationMillis = topBarAnimationDuration
        (targetTopBarIconSizes[navState.first]?.value ?: fallbackHeight) at 0 with LinearEasing
        (targetTopBarIconSizes[navState.second]?.value
            ?: fallbackHeight) at topBarAnimationDuration with LinearEasing
    }

    return animateFloatAsState(
        targetValue = targetTopBarIconSizes[navState.second]?.value ?: fallbackHeight,
        animationSpec = iconAnimation
    ).value
}

@Composable
fun getAnimatedTopBarTextSize(navState: Pair<Screen?, Screen?>): Float {
    val fallbackHeight = 1000f // TODO better solution please

    val iconAnimation = keyframes {
        durationMillis = topBarAnimationDuration
        (targetTopBarTextSizes[navState.first]?.value ?: fallbackHeight) at 0 with LinearEasing
        (targetTopBarTextSizes[navState.second]?.value
            ?: fallbackHeight) at topBarAnimationDuration with LinearEasing
    }

    return animateFloatAsState(
        targetValue = targetTopBarTextSizes[navState.second]?.value ?: fallbackHeight,
        animationSpec = iconAnimation
    ).value
}

@Composable
fun MyAnimatedVisibility(content: @Composable () -> Unit, visible: Boolean = false) {
    AnimatedVisibility(
        modifier = Modifier,
        enter = fadeIn(initialAlpha = 0.0f, animationSpec = animatedSpecSpec),
        visible = visible,
        exit = fadeOut(targetAlpha = 0.0f, animationSpec = animateOutSpecSpec),
    ) {
        content()
    }
}

val animatedSpecSpec = keyframes {
    durationMillis = 300
    0f at 0 with LinearEasing
    1f at durationMillis with LinearEasing
}

val animateOutSpecSpec = keyframes {
    durationMillis = 300
    1f at 0 with LinearEasing
    0f at durationMillis with LinearEasing
}