package com.tunepruner.voixt.editor.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tunepruner.voixt.ui.theme.VoixtTheme
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VoixtTheme {
                MainScreen(true)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        VoixtTheme {
            MainScreen(false)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(withSelection: Boolean) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            EditScreen(true)
        }
    }

    @Composable
    fun EditScreen(withSelection: Boolean) {
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
}