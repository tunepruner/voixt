package com.tunepruner.voixt.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        HomeScreen(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        HomeScreenNavigationButtonsArea(navController = navController)
    }
}

@Composable
fun TopBar(
    screen: Screen = Screen.HomeScreen,
    currentIconSize: Float,
    currentTextSize: TextUnit,
    currentHeight: Float
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(currentHeight.dp)
    ) {
        Row {
            if (screen is Screen.HomeScreen) Spacer(modifier = Modifier.weight(1f))
            val iconModifier = Modifier
            Image(
                painter = painterResource(id = R.drawable.home_icon),
                contentDescription = stringResource(R.string.home_screen_content_description),
                modifier = iconModifier.then(
                    when (screen) {
                        Screen.HomeScreen -> iconModifier
                            .wrapContentSize()
                            .size(currentIconSize.dp)
                            .weight(1.5f)
                            .align(Alignment.CenterVertically)
                        Screen.EditorScreen -> iconModifier
                            .padding(10.dp)
                        else -> iconModifier
                            .align(Alignment.CenterVertically)
                            .padding(10.dp)
                    }
                ),
            )
            val textModifier = Modifier
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = textModifier.then(
                    when (screen) {
                        Screen.HomeScreen ->
                            textModifier
                                .fillMaxSize()
                                .wrapContentSize()
                                .weight(3f)
                                .align(Alignment.CenterVertically)
                        Screen.EditorScreen ->
                            textModifier
                                .padding(10.dp)
                        else -> textModifier
                            .padding(10.dp)
                            .align(Alignment.CenterVertically)

                    }
                ),
                textAlign = TextAlign.Left,
                fontSize = currentTextSize,
                fontWeight = FontWeight(400)
            )
            if (screen is Screen.HomeScreen) Spacer(modifier = Modifier.weight(1f))
        }
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
            displayString = displayString,
            fontSize = 30.sp,
            textAlign = TextAlign.Left
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
            displayString = stringResource(id = R.string.voixt_drafts_screen),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        HomeScreenNavigationButton(
            modifier = Modifier.weight(1f),
            navController = navController,
            navToUri = Screen.ArchivedVoixts.route,
            displayString = stringResource(id = R.string.archived_voixts_screen),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RowScope.HomeScreenNavigationButton(
    modifier: Modifier = Modifier,
    navController: NavController,
    navToUri: String,
    displayString: String,
    fontSize: TextUnit,
    textAlign: TextAlign,
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
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
    ) {
        val painterResource = when (navToUri) {
            Screen.EditorScreen.route -> painterResource(R.drawable.new_voixt_button_icon)
            Screen.SavedVoixts.route -> painterResource(R.drawable.saved_voixts_button_icon)
            Screen.ArchivedVoixts.route -> painterResource(R.drawable.archived_voixts_button_icon_svg)
            else -> painterResource(R.drawable.voixt_drafts_button_icon)
        }

        Row(modifier = Modifier.align(Alignment.CenterVertically)) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                painter = painterResource,
                contentDescription = ""
            )
            Text(
                text = displayString,
                modifier = Modifier
                    .weight(1.5f)
                    .align(Alignment.CenterVertically),
                fontSize = fontSize,
                fontWeight = FontWeight(250),
                textAlign = textAlign
            )
        }
    }
}