// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("") }

    if (text != "") {
        try {
            result = calculate(text)
            isError = false
        } catch (e: Exception) {
            result = e.message ?: "error"
            isError = true
        }
    } else {
        isError = false
        result = ""
    }

    MaterialTheme {
       Column {
           TextField(
               value = text,
               onValueChange = {
                   text = it.uppercase()
               },
               isError = isError
           )
           TextField(
               value = result,
               onValueChange = {},
               label = {Text("result")},
               enabled = false,
               colors = TextFieldDefaults.textFieldColors(
                   disabledTextColor = Color.Black,
                   disabledLabelColor = Color.Gray,
               )
           )
       }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Calc",
        state = rememberWindowState(width = 290.dp, height = 300.dp)
    ) {
        App()
    }
}
