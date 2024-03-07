package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TabButton(text: String, selected: Boolean, onClick: () -> Unit){

    val backgroundColour = if (selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.primaryContainer
    val foregroundColour = if (selected) MaterialTheme.colorScheme.onPrimary
    else MaterialTheme.colorScheme.onPrimaryContainer

    Tab(selected = selected,
        onClick = onClick,
        modifier = Modifier.background(backgroundColour)
    ){
        Text(text = text,
             color = foregroundColour,
             modifier = Modifier.padding(16.dp))
    }
}