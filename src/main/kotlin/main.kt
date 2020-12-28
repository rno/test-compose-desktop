import androidx.compose.desktop.AppWindow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        MainWindow()
    }
}

fun MainWindow() {

    val mainWindow = AppWindow(
        title = ""
    )

    val isMacOSX = isMacOSX()

    val topPadding: Dp
    if (isMacOSX) {
        topPadding = 16.dp
        val rootPane = mainWindow.window.rootPane
        rootPane.putClientProperty("apple.awt.fullWindowContent", true)
        rootPane.putClientProperty("apple.awt.transparentTitleBar", true)
    } else {
        topPadding = 0.dp
    }

    mainWindow.show {
        var text by remember { mutableStateOf("Hello, World!") }

        MaterialTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(16.dp)
                    .padding(top = topPadding)
            ) {
                Button(onClick = {
                    text = "Hello, Desktop!"
                }) {
                    Text(text)
                }
            }
        }
    }
}

private fun isMacOSX(): Boolean {
    val osName = System.getProperty("os.name")
    return "OS X" == osName || "Mac OS X" == osName
}
