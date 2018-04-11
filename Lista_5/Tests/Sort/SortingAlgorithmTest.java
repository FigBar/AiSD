package Sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithmTest {

    private int[] array = {-25, -30, 96, 1, 4, 76, 55, 24, 17, 32, 1, 76};
    private int[] array2 = {2, 4, 7, 6, 6, -10, 5, -100, -21};
    private int[] array3 = {6,6,6};
    private int[] array4 = {10};
    int[] arrayToSort = new int[12];
    int[] arrayToSort2 = new int[9];
    int[] arrayToSort3 = new int[3];
    int[] arrayToSort4 = new int[1];

    @BeforeEach
    void setUp() {

        arrayToSort = array;
        arrayToSort2 = array2;
        arrayToSort3 = array3;
        arrayToSort4 = array4;
    }

    @Test
    void mergeSortTest() {
        int[] sortedArray = {-30, -25, 1, 1, 4, 17, 24, 32, 55, 76, 76, 96};
        assertArrayEquals(sortedArray, SortingAlgorithm.mergeSort(arrayToSort));

        int[] sortedArray2 = {-100, -21, -10, 2, 4, 5, 6, 6, 7};
        assertArrayEquals(sortedArray2, SortingAlgorithm.mergeSort(arrayToSort2));

        int[] sortedArray3 = {6,6,6};
        assertArrayEquals(sortedArray3,SortingAlgorithm.mergeSort(arrayToSort3));

        int[] sortedArray4 = {10};
        assertArrayEquals(sortedArray4, SortingAlgorithm.mergeSort(arrayToSort4));
    }

    @Test
    void quickSortTest(){
        int[] sortedArray = {-30, -25, 1, 1, 4, 17, 24, 32, 55, 76, 76, 96};
        assertArrayEquals(sortedArray, SortingAlgorithm.quickSort(arrayToSort));

        int[] sortedArray2 = {-100, -21, -10, 2, 4, 5, 6, 6, 7};
        assertArrayEquals(sortedArray2, SortingAlgorithm.quickSort(arrayToSort2));

        int[] sortedArray3 = {6,6,6};
        assertArrayEquals(sortedArray3,SortingAlgorithm.quickSort(arrayToSort3));

        int[] sortedArray4 = {10};
        assertArrayEquals(sortedArray4, SortingAlgorithm.quickSort(arrayToSort4));

    }
}