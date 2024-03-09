package ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.components.listColumn
import utilities.*
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedSortScreen(){

    //For generating the list of items to sort
    val numberItems = remember { mutableStateOf("") }
    val itemsList = remember { mutableStateListOf<Any>() }

    // A variable to store the selected sort algorithm
    var selectedSortAlgorithm by remember { mutableStateOf(SortAlgorithm.Selection) }

    //A variable to store the sorted list
    val itemsListSorted = remember { mutableStateListOf<Any>() }

    // A variable to store the highlighted index for sorting
    var currentIndex by remember { mutableStateOf(-1) }

    // A variable to check if we should run a sorting algorithm
    val sort = remember { mutableStateOf(false) }

    @Composable
    fun <T : Comparable<T>> rehighlight(num: Int, array: Array<T>) {
        currentIndex = num
        itemsListSorted.clear()
        itemsListSorted.addAll(array.asList())
        println(currentIndex)
        println(itemsListSorted.toList())
        Thread.sleep(100)
    }

    @Composable
    fun <T : Comparable<T>> selectionSortAnim(array: Array<T>) {

        val n = array.size

        for (i in 0 until n - 1) {
            rehighlight(i, array)
            var minIndex = i

            // Find the index of the smallest element in the unsorted part of the array
            for (j in i + 1 until n) {
                rehighlight(j, array)
                if (array[j] < array[minIndex]) {
                    minIndex = j
                }
            }

            // Swap the smallest element with the current element, if necessary
            if (minIndex != i) {
                val temp = array[i]
                array[i] = array[minIndex]
                array[minIndex] = temp
            }
        }
    }

    @Composable
    fun listColumnAnim(list: SnapshotStateList<Any>, text: String, modifier: Modifier) {
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
                            Text(
                                text = "$index: ${item}",
                                color = if (index == currentIndex)
                                    MaterialTheme.colorScheme.error
                                else MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            Text("Please note that this doesn't work as intended and is incomplete")
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
                        onClick = {
                            if(selectedSortAlgorithm != algorithm){
                                selectedSortAlgorithm = algorithm
                                itemsList.clear()
                                itemsListSorted.clear()
                            }
                        }
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
                    itemsListSorted.clear()
                },
                enabled = numberItems.value.isNotBlank()
            ) {
                Text("Generate Random Numbers")
            }

            Button(
                onClick = {
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
                },
                enabled = itemsList.isNotEmpty()
            ) {
                Text("Sort")
            }

            Button(
                onClick = { sort.value = true },
                enabled = itemsList.isNotEmpty()
            ) {
                Text("New Sort")
            }
        }

        //Second column to display the unsorted list.
        listColumn(itemsList, "Unsorted List", modifier = Modifier.weight(0.3f))
        //Third column to display sorted list.
        listColumnAnim(itemsListSorted, "Sorted List", modifier = Modifier.weight(0.3f))

        @Composable
        if(sort.value) {
            //Run the selected sort algorithm over the itemsList
            val typedArray = itemsList.map { it as Int }.toTypedArray()
            itemsListSorted.clear()
            itemsListSorted.addAll(typedArray.asList())

            when (selectedSortAlgorithm) {
                SortAlgorithm.Selection -> selectionSortAnim(typedArray)
                SortAlgorithm.Insertion -> insertionSort(typedArray)
                SortAlgorithm.Bubble -> bubbleSort(typedArray)
                SortAlgorithm.Merge -> mergeSort(typedArray)
            }
            sort.value = false
        }
    }
}