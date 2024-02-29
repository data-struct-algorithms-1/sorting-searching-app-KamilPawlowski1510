package ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun listColumn(list: SnapshotStateList<Any>, text: String, modifier: Modifier) {
    Column(modifier = modifier) {
        if (list.isNotEmpty()) {
            // Display the generated (unsorted) list in a column
            Text(text)
            Box(
                modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
            ) {
                LazyColumn(
                    Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(list) { index, item ->
                        Text("$index: ${item}")
                    }
                }
            }
        }
    }
}