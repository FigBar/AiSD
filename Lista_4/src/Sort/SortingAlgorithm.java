package Sort;

public class SortingAlgorithm {

    public int[] doSelectionSort(int[] array) {

        if (array == null)
            throw new NullPointerException();

        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }

    public int[] doBucketSort(int[] arrayToSort) {
        if (arrayToSort == null)
            throw new NullPointerException();

        int maxNumber = maxNumber(arrayToSort);
        int minNumber = minNumber(arrayToSort);

        int[] positiveBucket = new int[maxNumber + 1];
        int[] negativeBucket = new int[minNumber + 1];
        int[] sortedArray = new int[arrayToSort.length];

        for (int i = 0; i < arrayToSort.length; i++) {
            if (arrayToSort[i] >= 0)
                positiveBucket[arrayToSort[i]]++;
            else
                negativeBucket[-1 * (arrayToSort[i])]++;

        }

        int position = 0;
        for (int i = negativeBucket.length - 1; i >= 0; i--) {
            for (int j = 0; j < negativeBucket[i]; j++) {
                sortedArray[position++] = -i;
            }
        }
        for (int i = 0; i < positiveBucket.length; i++) {
            for (int j = 0; j < positiveBucket[i]; j++) {
                sortedArray[position++] = i;
            }
        }
        return sortedArray;
    }

    private int maxNumber(int[] array) {

        int maxNumber = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxNumber)
                maxNumber = array[i];
        }
        return maxNumber;
    }

    private int minNumber(int[] array) {
        int minNumber = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < minNumber)
                minNumber = array[i];
        }
        return -minNumber;
    }
}
