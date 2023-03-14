package com.tunepruner.voixt.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
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
            .height(200.dp), color = Color.Gray
    ) {

    }
}

@Composable
fun HomeScreenNavigationButtonsArea() {
    Column {
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
            .padding(5.dp), onClick = {
            println("clicked")
        }, shape = RoundedCornerShape(5.dp)
    ) {
        Text("My button")
    }
}