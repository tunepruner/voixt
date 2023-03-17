package com.tunepruner.voixt.editor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tunepruner.voixt.ui.theme.VoixtTheme

@Composable
fun EditorScreen(
    withSelection: Boolean, navController: NavController, modifier: Modifier = Modifier
) {
    val viewModel = null//TODO()

    val strings: MutableState<List<String>> = remember {
        mutableStateOf(arrayListOf("These", "are", "the", /*"initial", */"strings!"))
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp), modifier = modifier.fillMaxSize()
    ) {
        ContentLevel(withSelection, strings.value)
        ControlPanel(withSelection)
        ContentLevel(withSelection, strings.value)
    }
}

@Composable
fun ContentLevel(withSelection: Boolean, stringContent: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 110.dp, width = 0.dp)
            .background(color = MaterialTheme.colorScheme.tertiary)
    ) {
        AudioRow(withSelection = withSelection)
        TextRow(withSelection = withSelection, stringContent = stringContent)
    }
}

@Preview
@Composable
fun EditorScreenPreview() {
    VoixtTheme {
        EditorScreen(withSelection = true, navController = rememberNavController())
    }
}