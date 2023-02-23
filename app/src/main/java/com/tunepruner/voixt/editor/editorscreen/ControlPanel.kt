package com.tunepruner.voixt.editor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/** This is the top level Composable of the ControlPanel, which is
 * home to all the recording controls and format buttons.*/
@Composable
fun ControlPanel(withSelection: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 300.dp, width = 0.dp)
            .background(color = Color.Blue)
    ) {
        if (withSelection) SelectionEditButtonRow()
        UndoRedoButtonRow()
        FirstFormatButtonRow()
        SecondFormatButtonRow()
        AdditionalFormatButtonsAndControllerRow()
    }
}

@Composable
fun ColumnScope.SelectionEditButtonRow() {
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
    }
}

@Composable
fun ColumnScope.UndoRedoButtonRow() {
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
    }
}

@Composable
fun ColumnScope.FirstFormatButtonRow() {
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
}

@Composable
fun ColumnScope.SecondFormatButtonRow() {
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
}

@Composable
fun ColumnScope.AdditionalFormatButtonsAndControllerRow() {
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