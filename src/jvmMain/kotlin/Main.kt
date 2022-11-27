import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        var offset by remember { mutableStateOf(DpOffset.Zero) }

        LaunchedEffect(Unit) {
            repeat(200) {
                delay(10)
                offset -= DpOffset(10.dp, 0.dp)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
                .offset(offset.x, offset.y)
                .background(Color.Blue)
        ) {
            repeat(20) {
                Text(
                    text = "$it",
                    modifier = Modifier
                        .size(50.dp)
                        .offset(100.dp * it, 0.dp)
                        .background(Color.Green),
                )
            }
        }
    }
}
