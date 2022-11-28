import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        ReproduceOffsetBug()
    }
}

@Composable
fun ReproduceOffsetBug() {
    var offsetGood by remember { mutableStateOf(DpOffset.Zero) }
    var offsetBad by remember { mutableStateOf(IntOffset.Zero) }

    LaunchedEffect(Unit) {
        repeat(200) {
            delay(10)
            offsetGood -= DpOffset(10.dp, 0.dp)
            offsetBad -= IntOffset(20, 0)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
                .offset(offsetGood.x, offsetGood.y)
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
                .offset { offsetBad }
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
