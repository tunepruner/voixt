package com.tunepruner.voixt.editor.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun EditScreen(withSelection: Boolean, navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Level(withSelection)
        ControlPanel(withSelection)
        Level(withSelection)
    }
}

@Composable
fun Level(withSelection: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 110.dp, width = 0.dp)
            .background(color = Color(0x00000000))
    ) {
        AudioRow(withSelection = withSelection)
        TextRow(withSelection = withSelection)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun TextRow(withSelection: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 50.dp, width = 0.dp)
            .background(color = Color(0x00000000))
    ) {
        val strings: MutableState<List<String>> = mutableStateOf(ArrayList())

        strings.value =
            strings.value.plus(arrayListOf("These", "are", "the", "initial", "strings!"))

        for (item in strings.value) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "$item ",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}

@Composable
fun AudioRow(withSelection: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 50.dp, width = 0.dp)
            .background(color = MaterialTheme.colorScheme.secondary)
    ) {

    }
}