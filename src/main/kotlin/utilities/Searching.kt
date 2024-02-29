package utilities

fun <T> linearSearch(array: Array<T>, target: T): Int {
    for (index in array.indices) {
        if (array[index] == target) {
            return index
        }
    }
    return -1
}

fun <T : Comparable<T>>  binarySearch(array: Array<T>, target: T): Int {
    var left = 0
    var right = array.size - 1

    while (left <= right) {
        val mid = (left + right) / 2
        val midValue = array[mid]

        when {
            midValue == target -> return mid
            midValue < target  -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return -1
}