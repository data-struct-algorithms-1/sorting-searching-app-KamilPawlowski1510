package ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubmitTextField(label: String, value: MutableState<String>, enabled: Boolean, submit: () -> Unit) {
    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(label) },
        trailingIcon = {
            IconButton(
                enabled = enabled,
                onClick = { submit() }
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Submit Button",
                )
            }
        }
    )
}

