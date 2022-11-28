import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.common.ReproduceOffsetBug


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        ReproduceOffsetBug()
    }
}
