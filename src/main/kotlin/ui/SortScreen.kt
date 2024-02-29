package ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import utilities.SortAlgorithm
import utilities.insertionSort
import utilities.selectionSort
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortScreen(){

    //For generating the list of items to sort
    val numberItems = remember { mutableStateOf("") }
    val itemsList = remember { mutableStateListOf<Any>() }

    // A variable to store the selected sort algorithm
    var selectedSortAlgorithm by remember { mutableStateOf(SortAlgorithm.Selection) }

    //A variable to store the sorted list
    val itemsListSorted = remember { mutableStateListOf<Any>() }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            //For entering the number of items to generate
            OutlinedTextField(
                value = numberItems.value,
                onValueChange = { numberItems.value = it },
                label = { Text("Number of Items") }
            )

            // Radio buttons to select the sort algorithm
            SortAlgorithm.values().forEach { algorithm ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedSortAlgorithm == algorithm,
                        onClick = { selectedSortAlgorithm = algorithm }
                    )
                    Text(algorithm.name)
                }
            }

            Button(
                onClick = {
                    //Generate a random list of items of type Int based on number of items entered
                    val numItems = numberItems.value.toIntOrNull()
                    if (numItems != null) {
                        itemsList.clear()
                        repeat(numItems) {
                            itemsList.add(Random.nextInt(0, 1000))
                        }
                    }
                    //Run the selected sort algorithm over the itemsList
                    val typedArray = itemsList.map { it as Int }.toTypedArray()

                    when (selectedSortAlgorithm) {
                        SortAlgorithm.Selection -> selectionSort(typedArray)
                        SortAlgorithm.Insertion -> insertionSort(typedArray)
                        SortAlgorithm.Bubble -> TODO()
                        SortAlgorithm.Merge -> TODO()
                    }

                    // Update itemsState with the sorted array
                    itemsListSorted.clear()
                    itemsListSorted.addAll(typedArray.asList())
                },
                enabled = numberItems.value.isNotBlank()
            ) {
                Text("Sort")
            }
        }
        //Second column to display the unsorted list.
        Column(modifier = Modifier.weight(0.3f)){
            if (itemsList.isNotEmpty()) {
                // Display the generated (unsorted) list in a column
                Text("Unsorted list:")
                Box(
                    modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                ) {
                    LazyColumn(
                        Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(itemsList) { index, item ->
                            Text("$index: ${item}")
                        }
                    }
                }
            }
        }
        //Third column to display sorted list.
        Column(modifier = Modifier.weight(0.3f)){
            if (itemsListSorted.isNotEmpty()) {
                // Display the generated (unsorted) list in a column
                Text("Sorted list:")
                Box(
                    modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                ) {
                    LazyColumn(
                        Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(itemsListSorted) { index, item ->
                            Text("$index: ${item}")
                        }
                    }
                }
            }
        }
    }
}