import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.compose.AppTheme
import ui.AnimatedSortScreen
import ui.SearchScreen
import ui.SortScreen
import ui.components.TabButton
import utilities.MenuItem


@Composable
fun App(currentScreen: MutableState<MenuItem>) {

    Column {
        TabRow(selectedTabIndex = currentScreen.value.ordinal) {

            TabButton(text = "Search",
                selected = currentScreen.value == MenuItem.Search,
                onClick = { currentScreen.value = MenuItem.Search })

            TabButton(text = "Sort",
                selected = currentScreen.value == MenuItem.Sort,
                onClick = { currentScreen.value = MenuItem.Sort })

            TabButton(text = "Animated Sort",
                selected = currentScreen.value == MenuItem.AnimatedSort,
                onClick = { currentScreen.value = MenuItem.AnimatedSort })
        }

        Box {
            when (currentScreen.value) {
                MenuItem.Search -> SearchScreen()
                MenuItem.Sort -> SortScreen()
                MenuItem.AnimatedSort -> AnimatedSortScreen()
            }
        }
    }
}

fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "Searching and Sorting App"
    ) {
        AppTheme() {
            val currentScreen = remember { mutableStateOf(MenuItem.Search) }
            App(currentScreen)
        }
    }
}
