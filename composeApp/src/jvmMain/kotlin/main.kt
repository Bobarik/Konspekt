import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.bobarik.konspekt.App
import com.bobarik.konspekt.di.AppModule
import com.bobarik.konspekt.root.component.RootComponentImpl
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin
import java.awt.Dimension
import javax.swing.SwingUtilities

fun main() {
    startKoin {
        modules(AppModule)
    }

    Napier.base(DebugAntilog())

    val rootComponent = invokeOnAwtSync {
        val lifecycle = LifecycleRegistry()

        val rootComponent = RootComponentImpl(componentContext = DefaultComponentContext(lifecycle = lifecycle))

        lifecycle.resume()

        rootComponent
    }

    application {
        Window(
            title = "konspekt",
            state = rememberWindowState(width = 800.dp, height = 600.dp),
            onCloseRequest = ::exitApplication,
        ) {
            window.minimumSize = Dimension(350, 600)
            App(rootComponent)
        }
    }
}

fun <T> invokeOnAwtSync(block: () -> T): T {
    var result: T? = null
    SwingUtilities.invokeAndWait { result = block() }

    @Suppress("UNCHECKED_CAST")
    return result as T
}
