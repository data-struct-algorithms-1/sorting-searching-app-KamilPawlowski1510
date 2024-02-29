package utilities

fun <T : Comparable<T>> selectionSort(array: Array<T>) {

    val n = array.size

    for (i in 0 until n - 1) {
        var minIndex = i

        // Find the index of the smallest element in the unsorted part of the array
        for (j in i + 1 until n) {
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

fun <T : Comparable<T>> insertionSort(array: Array<T>) {
    val n = array.size

    // Iterate through each element starting from the second element
    for (i in 1 until n) {
        val current = array[i] // Store the current element
        var j = i - 1

        // Shift elements greater than the current element to the right
        while (j >= 0 && array[j] > current) {
            array[j + 1] = array[j]
            j--
        }

        array[j + 1] = current // Insert the current element into the correct position
    }
}