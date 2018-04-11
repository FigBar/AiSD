package Runner;

import Sort.SortingAlgorithm;

public class Run {
    public static void main(String[] args) {
        int[] array1 = new int[200];
        int[] array2 = new int[203];

        for (int i = 0; i < array1.length ; i++) {
            array1[i] = (int)(-1000 + Math.random()*2001);

        }

        for (int i = 0; i <array2.length ; i++) {
            array2[i] = (int)(-100 + Math.random()*201);
        }

        array1 = SortingAlgorithm.quickSort(array1);
        array2 = SortingAlgorithm.mergeSort(array2);

        for (int i = 0; i <array1.length ; i++) {
            System.out.print("[" + array1[i] + "], ");
        }

        System.out.println();

        for (int i = 0; i <array2.length ; i++) {
            System.out.print("[" + array2[i] + "], ");
        }
    }
}
