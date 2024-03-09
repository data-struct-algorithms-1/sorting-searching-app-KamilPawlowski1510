package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.components.SubmitTextField
import ui.components.listColumn
import utilities.*
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

    // A variable to start the sorting when a button is pressed
    val sort = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(0.4f)
        ) {

            //For entering the number of items to generate or 100 by default
            SubmitTextField("Generate Numbers (100 Default)", numberItems, true){
                //Generate a random list of items of type Int based on number of items entered
                var numItems = numberItems.value.toIntOrNull()
                if (numItems == null) {
                    numItems = 100
                }

                itemsList.clear()
                repeat(numItems) {
                    itemsList.add(Random.nextInt(0, 1000))
                }

                itemsListSorted.clear()
            }

            // Buttons to select the sort algorithm
                SortAlgorithm.values().forEach { algorithm ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                    Button(
                        enabled = itemsList.isNotEmpty(),
                        onClick = {
                            if(selectedSortAlgorithm != algorithm){
                                selectedSortAlgorithm = algorithm
                                //itemsList.clear()
                                //itemsListSorted.clear()
                            }
                            sort.value = true
                        }
                    ){
                        Text(algorithm.name)
                    }
                }
            }
        }

        // Sorts the itemsList
        if (sort.value) {
            //Run the selected sort algorithm over the itemsList
            val typedArray = itemsList.map { it as Int }.toTypedArray()

            when (selectedSortAlgorithm) {
                SortAlgorithm.Selection -> selectionSort(typedArray)
                SortAlgorithm.Insertion -> insertionSort(typedArray)
                SortAlgorithm.Bubble -> bubbleSort(typedArray)
                SortAlgorithm.Merge -> mergeSort(typedArray)
            }

            // Update itemsState with the sorted array
            itemsListSorted.clear()
            itemsListSorted.addAll(typedArray.asList())

            // Stop sorting from restarting
            sort.value = false
        }

        //Second column to display the unsorted list.
        listColumn(itemsList, "Unsorted List", modifier = Modifier.weight(0.3f))
        //Third column to display sorted list.
        listColumn(itemsListSorted, "Sorted List", modifier = Modifier.weight(0.3f))
    }
}