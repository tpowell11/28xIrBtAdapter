package dev.tompowell.irbt.a28xirbt

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "a28xirbt",
    ) {
        App()
    }
}