package com.tunepruner.voixt.editor.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tunepruner.voixt.R
import com.tunepruner.voixt.ui.theme.VoixtTheme

@Composable
fun TextRow(withSelection: Boolean, stringContent: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 50.dp)
    ) {
        val firstHalf = stringContent.subList(0, stringContent.size / 2)
        val secondHalf = stringContent.subList(stringContent.size / 2, stringContent.size)
        TextSubRow(stringContent = firstHalf, modifier = Modifier.weight(1f))
        Cursor(modifier = Modifier.weight(1f))
        TextSubRow(stringContent = secondHalf, modifier = Modifier.weight(1f))
    }
}

@Composable
fun RowScope.Cursor(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Surface(
            modifier = Modifier
                .background(color = Color.Blue)
                .width(10.dp)
                .align(Alignment.Center)
                .width(10.dp)
                .fillMaxHeight(),
            color = Color.Black
        ) {

        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun RowScope.TextSubRow(stringContent: List<String>, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        for (item in stringContent) {
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(5.dp)),
                color = MaterialTheme.colorScheme.secondary,
                shadowElevation = 10.dp
            ) {
                Text(
                    text = "$item ",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.courier_prime_regular))
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(50.dp)
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}

@Preview
@Composable
fun TextRowPreview() {
    VoixtTheme {
        TextRow(withSelection = true, listOf("These", "are", "the", "strings"))
    }
}
