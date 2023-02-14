package com.tunepruner.voixt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.tunepruner.voixt.ui.theme.VoixtTheme
import androidx.compose.runtime.livedata.observeAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VoixtTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    EditScreen(false)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VoixtTheme {
        EditScreen(false)
    }
}

@Composable
fun ControlPanel(withSelection: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 300.dp, width = 0.dp)
            .background(color = Color.Blue)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(height = 75.dp, width = 0.dp)
                .background(color = Color.DarkGray)
                .weight(weight = 1.0f)
        ) {
            val topLevelButtonModifier = Modifier
                .weight(1.0f)
                .fillMaxHeight()
                .padding(2.dp)
                .align(alignment = Alignment.CenterVertically)
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(height = 75.dp, width = 0.dp)
                .background(color = Color.DarkGray)
                .weight(weight = 1.0f)
        ) {
            val topLevelButtonModifier = Modifier
                .weight(1.0f)
                .fillMaxHeight()
                .padding(2.dp)
                .align(alignment = Alignment.CenterVertically)

            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = topLevelButtonModifier,
            ) {
                Text("1")
            }
        }

        // start with controller

        Row(modifier = Modifier.weight(4.0f)) {
            Column(
                modifier = Modifier
                    .weight(1.0f)
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1.0f)
                        .padding(2.dp)
                ) {
                    Text("1")
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1.0f)
                        .padding(2.dp)
                ) {
                    Text("1")
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1.0f)
                        .padding(2.dp)
                ) {
                    Text("1")
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1.0f)
                        .padding(2.dp)
                ) {
                    Text("1")
                }
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(6.0f)
                    .fillMaxHeight()
                    .padding(2.dp)
                    .align(alignment = Alignment.CenterVertically),
            ) {
                Text("1")
            }
        }
    }

}

@Composable
fun EditScreen(withSelection: Boolean) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Level(withSelection)
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
            .size(height = 150.dp, width = 0.dp)
            .background(color = Color.DarkGray)
            .padding(5.dp),
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
            .size(height = 75.dp, width = 0.dp)
            .background(color = Color.Green)
    ) {

    }
}

@Composable
fun AudioRow(withSelection: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 75.dp, width = 0.dp)
            .background(color = Color.Gray)
    ) {

    }
}