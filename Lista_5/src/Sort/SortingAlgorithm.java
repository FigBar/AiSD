package Sort;

import java.util.Stack;

public class SortingAlgorithm {

    private static void merge(int[] array, int left, int middle, int right) {

        int nLeft = middle - left + 1;
        int nRight = right - middle;

        int[] leftArray = new int[nLeft];
        int[] rightArray = new int[nRight];

        //Copying data into temporary arrays;

        for (int i = 0; i < nLeft; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < nRight; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        //Merging arrays
        int i = 0;
        int j = 0;
        int k = left;

        while (i < nLeft && j < nRight) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        //adding remaining elements of leftArray if there are any left
        while (i < nLeft) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        //adding remaining elements of rightArray if there are any left;
        while (j < nRight) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static int[] mergeSort(int[] arrayToSort) {

        int currentSize;

        int leftStart;

        for (currentSize = 1; currentSize <= arrayToSort.length - 1; currentSize = 2 * currentSize) {

            for (leftStart = 0; leftStart < arrayToSort.length - 1; leftStart += 2 * currentSize) {
                int middle = leftStart + currentSize - 1;
                if (middle > arrayToSort.length - 1) {
                    leftStart = arrayToSort.length - 1;
                } else {
                    int rightEnd = Math.min(leftStart + (2 * currentSize - 1), arrayToSort.length - 1);

                    merge(arrayToSort, leftStart, middle, rightEnd);
                }
            }
        }

        return arrayToSort;
    }

    private static void swap(int[] array, int i, int j) {
        int temporary = array[i];

        array[i] = array[j];
        array[j] = temporary;
    }

    private static int partition(int[] array, int position, int low, int high) {

        int l = low;
        int h = high - 2;

        //taking the middle as a pivot
        int pivot = array[position];

        //temporarily placing pivot at the end of sorted subarray
        swap(array, position, high - 1);

        //moving elements on the proper side of pivot
        while (l < h) {
            if (array[l] < pivot) {
                l++;
            } else if (array[h] >= pivot) {
                h--;
            } else {
                swap(array, l, h);
            }
        }

        int index = h;
        if (array[h] < pivot) {
            index++;
        }

        //placing pivot on its right position
        swap(array, high - 1, index);

        return index;
    }

    public static int[] quickSort(int[] arrayToSort) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(arrayToSort.length);

        while (!stack.isEmpty()) {
            int end = stack.pop();
            int start = stack.pop();

            if (end - start < 2) {
                continue;
            }

            int p = start + ((end - start) / 2);

             p = partition(arrayToSort, p, start, end);

            stack.push(p + 1);
            stack.push(end);

            stack.push(start);
            stack.push(p);
        }
        return arrayToSort;
    }
}
