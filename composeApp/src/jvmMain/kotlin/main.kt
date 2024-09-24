import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.bobarik.konspekt.App
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import java.awt.Dimension

fun main() {
    Napier.base(DebugAntilog())

    application {
        Window(
            title = "konspekt",
            state = rememberWindowState(width = 800.dp, height = 600.dp),
            onCloseRequest = ::exitApplication,
        ) {
            window.minimumSize = Dimension(350, 600)
            App()
        }
    }
}
