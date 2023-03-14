package com.tunepruner.voixt.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tunepruner.voixt.R
import com.tunepruner.voixt.Screen
import com.tunepruner.voixt.ui.theme.VoixtTheme

val paddingBetweenRowsAndButtons = 2.dp
val buttonShape = RoundedCornerShape(5.dp)

val gradientColorForButtons = Brush.verticalGradient(
    listOf(
        Color(0x536F6F6F),
        Color(0x80404040),
    )
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    VoixtTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { TopBar() }, content = {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background,
        ) {
            HomeScreenNavigationButtonsArea(navController = navController)
        }
    })
}

@Composable
fun TopBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {

    }
}

@Composable
fun HomeScreenNavigationButtonsArea(navController: NavController) {
    Column(modifier = Modifier.padding(paddingBetweenRowsAndButtons)) {
        HomeScreenSingleButtonRow(
            modifier = Modifier.weight(1f),
            navController = navController,
            navToUri = Screen.EditorScreen.route,
            displayString = stringResource(
                id = R.string.new_voixt
            )
        )
        HomeScreenSingleButtonRow(
            modifier = Modifier.weight(1f),
            navController = navController,
            navToUri = Screen.SavedVoixts.route,
            displayString = stringResource(
                id = R.string.saved_voixts_screen
            )
        )
        HomeScreenTwoButtonRow(modifier = Modifier.weight(1f), navController = navController)
    }
}

@Composable
fun ColumnScope.HomeScreenSingleButtonRow(
    modifier: Modifier = Modifier,
    navController: NavController,
    navToUri: String,
    displayString: String
) {
    Row(modifier = modifier) {
        HomeScreenNavigationButton(
            modifier = Modifier.weight(1f),
            navController = navController,
            navToUri = navToUri,
            displayString = displayString
        )
    }
}

@Composable
fun ColumnScope.HomeScreenTwoButtonRow(
    modifier: Modifier = Modifier, navController: NavController
) {
    Row(modifier = modifier) {
        HomeScreenNavigationButton(
            modifier = Modifier.weight(1f),
            navController = navController,
            navToUri = Screen.VoixtDrafts.route,
            displayString = stringResource(id = R.string.voixt_drafts_screen)
        )
        HomeScreenNavigationButton(
            modifier = Modifier.weight(1f),
            navController = navController,
            navToUri = Screen.ArchivedVoixts.route,
            displayString = stringResource(id = R.string.archived_voixts_screen)
        )
    }
}

@Composable
fun RowScope.HomeScreenNavigationButton(
    modifier: Modifier = Modifier,
    navController: NavController,
    navToUri: String,
    displayString: String,
) {
    Button(
        modifier = modifier
            .weight(1f)
            .fillMaxSize()
            .padding(paddingBetweenRowsAndButtons)
            .background(shape = buttonShape, brush = gradientColorForButtons),
        onClick = {
            navController.navigate(navToUri)
        },
        shape = buttonShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Text(
            text = displayString, modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}