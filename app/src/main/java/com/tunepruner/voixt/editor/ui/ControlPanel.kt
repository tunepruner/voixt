package com.tunepruner.voixt.editor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tunepruner.voixt.ui.theme.VoixtTheme

/** This is the top level Composable of the ControlPanel, which is
 * home to all the recording controls and format buttons.*/
@Composable
fun ControlPanel(withSelection: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 400.dp, width = 0.dp)
            .background(color = MaterialTheme.colorScheme.primary)
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
            .weight(weight = 1.0f)
    ) {
        RowButton("1", {})
        RowButton("1", {})
    }
}

@Composable
fun ColumnScope.UndoRedoButtonRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 75.dp, width = 0.dp)
            .weight(weight = 1.0f)
    ) {
        RowButton("1", {}, weight = 2f)
        RowButton("1", {}, weight = 3f)
        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
    }
}

@Composable
fun ColumnScope.FirstFormatButtonRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 75.dp, width = 0.dp)
            .weight(weight = 1.0f)
    ) {

        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
    }
}

@Composable
fun ColumnScope.SecondFormatButtonRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 75.dp, width = 0.dp)
            .weight(weight = 1.0f)
    ) {
        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
        RowButton("1", {})
    }
}

@Composable
fun ColumnScope.AdditionalFormatButtonsAndControllerRow() {
    Row(modifier = Modifier.weight(4.0f)) {
        Column(
            modifier = Modifier.weight(1.0f)
        ) {
            ColumnButton("1", {})
            ColumnButton("1", {})
            ColumnButton("1", {})
            ColumnButton("1", {})
        }
        Box(
            modifier = Modifier
                .weight(6.0f)
                .fillMaxHeight()
                .padding(2.dp)
                .align(alignment = Alignment.CenterVertically)
                .background(
                    color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(5.dp)
                ),
        ) {
            Text("1")
        }
    }
}

@Composable
fun ColumnScope.ColumnButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick },
        modifier = Modifier
            .weight(1.0f)
            .padding(2.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        elevation = ButtonDefaults.buttonElevation(5.dp),
    ) {
        Text(text)
    }
}

@Composable
fun RowScope.RowButton(text: String, onClick: () -> Unit, weight: Float = 1.0f) {
    Button(
        onClick = { onClick },
        modifier = Modifier
            .weight(weight)
            .fillMaxHeight()
            .padding(2.dp)
            .align(alignment = Alignment.CenterVertically),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        elevation = ButtonDefaults.buttonElevation(5.dp),
    ) {
        Text(text)
    }
}

@Preview
@Composable
fun ControlPanelPreview() {
    VoixtTheme {
        ControlPanel(withSelection = true)
    }
}