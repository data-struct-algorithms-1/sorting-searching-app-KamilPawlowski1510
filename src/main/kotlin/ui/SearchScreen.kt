package ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import utilities.SearchAlgorithm
import utilities.binarySearch
import utilities.linearSearch
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(){

    //For generating the list of items to search in
    val numberItems = remember { mutableStateOf("") }
    val itemsList = remember { mutableStateListOf<Any>() }

    //For storing the search item
    val searchText = remember { mutableStateOf("") }

    //For storing the first index if the item is found in the list
    val foundItemIndex = remember { mutableStateOf(-2) }

    //For supporting scrolling and scrolling to an item, if found
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // A variable to store the selected search algorithm
    var selectedSearchAlgorithm by remember { mutableStateOf(SearchAlgorithm.Linear) }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            //For entering the number of items to generate
            OutlinedTextField(
                value = numberItems.value,
                onValueChange = { numberItems.value = it },
                label = { Text("Number of Items") }
            )

            //For generating a random list of items of type Int based on number of items entered
            Button(
                onClick = {
                    foundItemIndex.value = -2
                    //Generate a random list of items of type Int based on number of items entered
                    val numItems = numberItems.value.toIntOrNull()
                    if (numItems != null) {
                        itemsList.clear()
                        repeat(numItems) {
                            itemsList.add(Random.nextInt(0, 100))
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = numberItems.value.isNotBlank()
            ) {
                Text("Generate Numbers")
            }

            // Radio buttons to select the search algorithm
            SearchAlgorithm.values().forEach { algorithm ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedSearchAlgorithm == algorithm,
                        onClick = { selectedSearchAlgorithm = algorithm }
                    )
                    Text(algorithm.name)
                }
            }

            //The item to search for in the above data
            OutlinedTextField(
                value = searchText.value,
                onValueChange = { searchText.value = it },
                label = { Text("Search") }
            )

            Button(
                onClick = {
                    val typedArray = itemsList.map { it as Int }.toTypedArray()

                    foundItemIndex.value = when (selectedSearchAlgorithm) {
                        SearchAlgorithm.Linear -> linearSearch(typedArray, searchText.value.toInt())
                        SearchAlgorithm.Binary -> {
                            typedArray.sort()  //Binary only works on sorted list
                            itemsList.sortBy { it as Int} //sort the itemList for display
                            binarySearch(typedArray, searchText.value.toInt())
                        }
                    }

                    //Scroll to the item, if found
                    if (foundItemIndex.value != -1) {
                        coroutineScope.launch {
                            listState.scrollToItem(foundItemIndex.value)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = searchText.value.isNotBlank()
            ) {
                Text("Search")
            }

            Text(
                text =
                when {
                    foundItemIndex.value > -1 -> "Found index: ${foundItemIndex.value}"
                    foundItemIndex.value == -1 -> "NOT FOUND"
                    else -> ""
                }
            )
        }

        // Add a LazyColumn to display the items
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(itemsList) { index, item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(2.dp),
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Text(
                        text = "$index: $item",
                        modifier = Modifier.padding(start = 8.dp),
                        //Highlight the found item
                        color = if (index == foundItemIndex.value)
                            MaterialTheme.colorScheme.error
                        else MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

    }
}