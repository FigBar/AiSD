package Sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithmTest {

    SortingAlgorithm sort1 = new SortingAlgorithm();
    int[] arrayToSort = {2, 2, 7, 5, 9, 12, -2, -3, 9, 8, 10,};

    @BeforeEach
    void setUp() {
    }

    @Test
    void doSelectionSort() {
        int[] sortedArray = {-3, -2, 2, 2, 5, 7, 8, 9, 9, 10, 12};

        arrayToSort = sort1.doSelectionSort(arrayToSort);

        assertArrayEquals(sortedArray, arrayToSort);

        assertThrows(NullPointerException.class, ()-> sort1.doSelectionSort(null));

    }

    @Test
    void doBucketSort() {
        int[] sortedArray = {-3, -2, 2, 2, 5, 7, 8, 9, 9, 10, 12};

        arrayToSort = sort1.doBucketSort(arrayToSort);

        assertArrayEquals(sortedArray, arrayToSort);
        assertThrows(NullPointerException.class, ()-> sort1.doBucketSort(null));
    }
}