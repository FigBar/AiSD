package Sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithmTest {

    private int[] array = {-25, -30, 96, 1, 4, 76, 55, 24, 17, 32, 1, 76};
    private int[] array2 = {2, 4, 7, 6, 6, -10, 5, -100, -21};
    int[] arrayToSort = new int[12];
    int[] arrayToSort2 = new int[9];

    @BeforeEach
    void setUp() {

        arrayToSort = array;
        arrayToSort2 = array2;
    }

    @Test
    void mergeSortTest() {
        int[] sortedArray = {-30, -25, 1, 1, 4, 17, 24, 32, 55, 76, 76, 96};
        assertArrayEquals(sortedArray, SortingAlgorithm.mergeSort(arrayToSort));

        int[] sortedArray2 = {-100, -21, -10, 2, 4, 5, 6, 6, 7};
        assertArrayEquals(sortedArray2, SortingAlgorithm.mergeSort(arrayToSort2));
    }

    @Test
    void quickSortTest(){
        int[] sortedArray = {-30, -25, 1, 1, 4, 17, 24, 32, 55, 76, 76, 96};
        assertArrayEquals(sortedArray, SortingAlgorithm.quickSort(arrayToSort));

        int[] sortedArray2 = {-100, -21, -10, 2, 4, 5, 6, 6, 7};
        assertArrayEquals(sortedArray2, SortingAlgorithm.quickSort(arrayToSort2));

    }
}