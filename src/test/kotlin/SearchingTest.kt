import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import utilities.binarySearch
import utilities.linearSearch

class SearchingTest {

    @Nested
    inner class LinearSearch {

        @Test
        fun `linearSearch should return -1 for an empty array`() {
            // Arrange
            val array = arrayOf<Int>()

            // Act
            val result = linearSearch(array, 1)

            // Assert
            assertEquals(-1, result)
        }

        @Test
        fun `linearSearch should return the target index for a single value array containing the target`() {
            // Arrange
            val array = arrayOf<Int>(1)

            // Act
            val result = linearSearch(array, 1)

            // Assert
            assertEquals(0, result)
        }

        @Test
        fun `linearSearch should return the target index for an array with multiple values that contains the target`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 4, 5, 6)

            // Act
            val result = linearSearch(array, 4)

            // Assert
            assertEquals(3, result)
        }

        @Test
        fun `linearSearch should return the target index for an array with multiple values that contains the target as the first value`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 4, 5, 6)

            // Act
            val result = linearSearch(array, 1)

            // Assert
            assertEquals(0, result)
        }

        @Test
        fun `linearSearch should return the target index for an array with multiple values that contains the target as the last value`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 4, 5, 6)

            // Act
            val result = linearSearch(array, 6)

            // Assert
            assertEquals(5, result)
        }

        @Test
        fun `linearSearch should return the first target index for an array with multiple instances of the target`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 4, 5, 6, 4)

            // Act
            val result = linearSearch(array, 4)

            // Assert
            assertEquals(3, result)
        }

        @Test
        fun `linearSearch should return -1 for an array without the target`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 4, 5, 6)

            // Act
            val result = linearSearch(array, 7)

            // Assert
            assertEquals(-1, result)
        }

        @Test
        fun `linearSearch should return the target index for an array of strings with multiple values that contains the target`() {
            // Arrange
            val array = arrayOf<String>("a", "b", "c", "d", "e", "f")

            // Act
            val result = linearSearch(array, "d")

            // Assert
            assertEquals(3, result)
        }
    }

    @Nested
    inner class BinarySearch {

        @Test
        fun `binarySearch should return -1 for an empty array`() {
            // Arrange
            val array = arrayOf<Int>()

            // Act
            val result = binarySearch(array, 1)

            // Assert
            assertEquals(-1, result)
        }

        @Test
        fun `binarySearch should return the target index for a single value array containing the target`() {
            // Arrange
            val array = arrayOf<Int>(1)

            // Act
            val result = binarySearch(array, 1)

            // Assert
            assertEquals(0, result)
        }

        @Test
        fun `binarySearch should return the target index for a sorted array with multiple values that contains the target`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 4, 5, 6)
            array.sort()

            // Act
            val result = binarySearch(array, 4)

            // Assert
            assertEquals(3, result)
        }

        @Test
        fun `binarySearch should return the target index for a sorted array with multiple values that contains the target as the first value`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 4, 5, 6)

            // Act
            val result = binarySearch(array, 1)

            // Assert
            assertEquals(0, result)
        }

        @Test
        fun `binarySearch should return the target index for a sorted array with multiple values that contains the target as the last value`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 4, 5, 6)

            // Act
            val result = binarySearch(array, 6)

            // Assert
            assertEquals(5, result)
        }

        @Test
        fun `binarySearch should return the first target index for a sorted array with multiple instances of the target`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 4, 5, 6, 4)
            array.sort()

            // Act
            val result = binarySearch(array, 4)

            // Assert
            assertEquals(3, result)
        }

        @Test
        fun `binarySearch should return -1 for a sorted array without the target`() {
            // Arrange
            val array = arrayOf<Int>(1, 2, 3, 4, 5, 6)
            array.sort()

            // Act
            val result = binarySearch(array, 7)

            // Assert
            assertEquals(-1, result)
        }

        @Test
        fun `binarySearch should return the target index for a sorted array of strings with multiple values that contains the target`() {
            // Arrange
            val array = arrayOf<String>("a", "b", "c", "d", "e", "f")
            array.sort()

            // Act
            val result = binarySearch(array, "d")

            // Assert
            assertEquals(3, result)
        }
    }
}