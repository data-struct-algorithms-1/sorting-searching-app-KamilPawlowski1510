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

fun <T : Comparable<T>> bubbleSort(array: Array<T>) {
    val n = array.size

    // Perform n-1 passes
    for (i in 0 until n - 1) {
        // Each pass compares adjacent elements and swaps if necessary
        for (j in 0 until n - i - 1) {
            if (array[j] > array[j + 1]) {
                // Swap elements if they are in the wrong order
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
        }
    }
}

//merge sort is recursive, so build that into recursion section
fun <T : Comparable<T>> mergeSort(array: Array<T>) {
    if (array.size <= 1) {
        // Base case: Already sorted if array has 0 or 1 element
        return
    }

    // Split the array into two halves
    val mid = array.size / 2
    val leftArray = array.sliceArray(0 until mid)
    val rightArray = array.sliceArray(mid until array.size)

    // Recursively sort the two halves
    mergeSort(leftArray)
    mergeSort(rightArray)

    // Merge the sorted halves
    merge(array, leftArray, rightArray)
}

fun <T : Comparable<T>> merge(
    array: Array<T>,
    leftArray: Array<T>,
    rightArray: Array<T>
) {
    var leftIndex = 0
    var rightIndex = 0
    var mergedIndex = 0

    // Merge the two sorted arrays into a single sorted array
    while (leftIndex < leftArray.size && rightIndex < rightArray.size) {
        if (leftArray[leftIndex] <= rightArray[rightIndex]) {
            array[mergedIndex++] = leftArray[leftIndex++]
        } else {
            array[mergedIndex++] = rightArray[rightIndex++]
        }
    }

    // Copy the remaining elements from the leftArray, if any
    while (leftIndex < leftArray.size) {
        array[mergedIndex++] = leftArray[leftIndex++]
    }

    // Copy the remaining elements from the rightArray, if any
    while (rightIndex < rightArray.size) {
        array[mergedIndex++] = rightArray[rightIndex++]
    }
}