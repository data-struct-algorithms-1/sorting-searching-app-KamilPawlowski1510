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
import ui.components.SubmitTextField
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
            //For generating a random list of items of type Int based on number of items entered or 100 by default
            SubmitTextField("Generate Numbers (100 Default)", numberItems, true){
                foundItemIndex.value = -2
                //Generate a random list of items of type Int based on number of items entered
                var numItems = numberItems.value.toIntOrNull()
                if (numItems == null) {
                    numItems = 100
                }
                itemsList.clear()
                repeat(numItems) {
                    itemsList.add(Random.nextInt(0, 100))
                }
            }

            // Radio buttons to select the search algorithm
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchAlgorithm.values().forEach { algorithm ->
                    RadioButton(
                        selected = selectedSearchAlgorithm == algorithm,
                        onClick = { selectedSearchAlgorithm = algorithm }
                    )
                    Text(algorithm.name)
                }
            }

            // Search for a specified item or 5 by default
            SubmitTextField("Search (5 Default)", searchText, itemsList.isNotEmpty()){
                val typedArray = itemsList.map { it as Int }.toTypedArray()
                var search = searchText.value.toIntOrNull()

                if (search == null) {
                    search = 5
                }

                foundItemIndex.value = when (selectedSearchAlgorithm) {
                    SearchAlgorithm.Linear -> linearSearch(typedArray, search)
                    SearchAlgorithm.Binary -> {
                        typedArray.sort()  //Binary only works on sorted list
                        itemsList.sortBy { it as Int} //sort the itemList for display
                        binarySearch(typedArray, search)
                    }
                }

                //Scroll to the item, if found
                if (foundItemIndex.value != -1) {
                    coroutineScope.launch {
                        listState.scrollToItem(foundItemIndex.value)
                    }
                }
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