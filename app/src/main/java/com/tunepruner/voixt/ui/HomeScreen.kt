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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        HomeScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(topBar = { TopBar() }, content = {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background,
        ) {
            HomeScreenNavigationButtonsArea()
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
fun HomeScreenNavigationButtonsArea() {
    Column(modifier = Modifier.padding(paddingBetweenRowsAndButtons)) {
        HomeScreenSingleButtonRow(modifier = Modifier.weight(1f))
        HomeScreenSingleButtonRow(modifier = Modifier.weight(1f))
        HomeScreenTwoButtonRow(modifier = Modifier.weight(1f))
    }
}

@Composable
fun ColumnScope.HomeScreenSingleButtonRow(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        HomeScreenNavigationButton(modifier = Modifier.weight(1f))
    }
}


@Composable
fun ColumnScope.HomeScreenTwoButtonRow(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        repeat(2) { HomeScreenNavigationButton(modifier = Modifier.weight(1f)) }
    }
}

@Composable
fun RowScope.HomeScreenNavigationButton(modifier: Modifier = Modifier) {
    Button(
        modifier = modifier
            .weight(1f)
            .fillMaxSize()
            .padding(paddingBetweenRowsAndButtons)
            .background(shape = buttonShape, brush = gradientColorForButtons),
        onClick = {
            println("clicked")
        },
        shape = buttonShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Text(
            text = "My button",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}