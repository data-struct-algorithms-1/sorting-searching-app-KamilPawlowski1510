import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import utilities.insertionSort
import utilities.selectionSort

class SortingTest {

    @Nested
    inner class SelectionSort {

        @Test
        fun `selectionSort should work on an empty array`() {
            // Arrange
            val array = arrayOf<Int>()

            // Act
            selectionSort(array)
        }

        @Test
        fun `selectionSort should work on an array of one`() {
            // Arrange
            val array = arrayOf<Int>(5)

            // Act
            selectionSort(array)
        }

        @Test
        fun `selectionSort should work on a sorted array`() {
            // Arrange
            val array = arrayOf<Int>(-2, -1, 0, 1, 2, 3)

            // Act
            selectionSort(array)

            // Assert
            assertEquals(-2, array[0])
            assertEquals(-1, array[1])
            assertEquals(0, array[2])
            assertEquals(1, array[3])
            assertEquals(2, array[4])
            assertEquals(3, array[5])
        }

        @Test
        fun `selectionSort should work on a completely random array`() {
            // Arrange
            val array = arrayOf<Int>(7, 15, 460, -19, 2, -5)

            // Act
            selectionSort(array)

            // Assert
            assertEquals(-19, array[0])
            assertEquals(-5, array[1])
            assertEquals(2, array[2])
            assertEquals(7, array[3])
            assertEquals(15, array[4])
            assertEquals(460, array[5])
        }

        @Test
        fun `selectionSort should work on an array sorted in reverse order`() {
            // Arrange
            val array = arrayOf<Int>(3, 2, 1, 0, -1, -2)

            // Act
            selectionSort(array)

            // Assert
            assertEquals(-2, array[0])
            assertEquals(-1, array[1])
            assertEquals(0, array[2])
            assertEquals(1, array[3])
            assertEquals(2, array[4])
            assertEquals(3, array[5])
        }

        @Test
        fun `selectionSort should work on an array with multiple of the same value`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 3, 3, 1)

            // Act
            selectionSort(array)

            // Assert
            assertEquals(1, array[0])
            assertEquals(1, array[1])
            assertEquals(2, array[2])
            assertEquals(3, array[3])
            assertEquals(3, array[4])
            assertEquals(3, array[5])
        }

        @Test
        fun `selectionSort should work on an array of strings`() {
            // Arrange
            val array = arrayOf<String>("z", "b", "q", "f", "i", "a")

            // Act
            selectionSort(array)

            // Assert
            assertEquals("a", array[0])
            assertEquals("b", array[1])
            assertEquals("f", array[2])
            assertEquals("i", array[3])
            assertEquals("q", array[4])
            assertEquals("z", array[5])
        }
    }

    @Nested
    inner class InsertionSort {

        @Test
        fun `insertionSort should work on an empty array`() {
            // Arrange
            val array = arrayOf<Int>()

            // Act
            insertionSort(array)
        }

        @Test
        fun `insertionSort should work on an array of one`() {
            // Arrange
            val array = arrayOf<Int>(5)

            // Act
            insertionSort(array)
        }

        @Test
        fun `insertionSort should work on a sorted array`() {
            // Arrange
            val array = arrayOf<Int>(-2, -1, 0, 1, 2, 3)

            // Act
            insertionSort(array)

            // Assert
            assertEquals(-2, array[0])
            assertEquals(-1, array[1])
            assertEquals(0, array[2])
            assertEquals(1, array[3])
            assertEquals(2, array[4])
            assertEquals(3, array[5])
        }

        @Test
        fun `insertionSort should work on a completely random array`() {
            // Arrange
            val array = arrayOf<Int>(7, 15, 460, -19, 2, -5)

            // Act
            insertionSort(array)

            // Assert
            assertEquals(-19, array[0])
            assertEquals(-5, array[1])
            assertEquals(2, array[2])
            assertEquals(7, array[3])
            assertEquals(15, array[4])
            assertEquals(460, array[5])
        }

        @Test
        fun `insertionSort should work on an array sorted in reverse order`() {
            // Arrange
            val array = arrayOf<Int>(3, 2, 1, 0, -1, -2)

            // Act
            insertionSort(array)

            // Assert
            assertEquals(-2, array[0])
            assertEquals(-1, array[1])
            assertEquals(0, array[2])
            assertEquals(1, array[3])
            assertEquals(2, array[4])
            assertEquals(3, array[5])
        }

        @Test
        fun `insertionSort should work on an array with multiple of the same value`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 3, 3, 1)

            // Act
            insertionSort(array)

            // Assert
            assertEquals(1, array[0])
            assertEquals(1, array[1])
            assertEquals(2, array[2])
            assertEquals(3, array[3])
            assertEquals(3, array[4])
            assertEquals(3, array[5])
        }

        @Test
        fun `insertionSort should work on an array of strings`() {
            // Arrange
            val array = arrayOf<String>("z", "b", "q", "f", "i", "a")

            // Act
            insertionSort(array)

            // Assert
            assertEquals("a", array[0])
            assertEquals("b", array[1])
            assertEquals("f", array[2])
            assertEquals("i", array[3])
            assertEquals("q", array[4])
            assertEquals("z", array[5])
        }
    }
}