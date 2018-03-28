package Binary_Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Sort.SortingAlgorithm;

public class BinarySearch {

    private int[] searchArray;

    private ArrayList<Integer> sortList = new ArrayList<>();

    private SortingAlgorithm sorter = new SortingAlgorithm();

    public BinarySearch(int[] array) {
        this.searchArray = sorter.doBucketSort(array);
    }

    public void addAll(Collection<Integer> c) {
        sortList.addAll(c);
    }

    public void add(int number) {
        sortList.add(number);
    }

    public long doBinarySearch(int key) {
        if (searchArray == null)
            throw new NullPointerException();

        if (!sortList.isEmpty())
            addToArray();

        return binarySearch(key);
    }

    private long binarySearch(int key) {
        int start = 0;
        int end = searchArray.length - 1;


        while (start <= end) {
            int mid = (start + end) / 2;

            if (searchArray[mid] < key) {
                start = mid + 1;
            } else if (searchArray[mid] > key) {
                end = mid - 1;
            } else if (searchArray[mid] == key) {
                while (mid > 0 && searchArray[mid - 1] == key) {
                    mid--;
                }
                return mid;
            }
        }
        return -1;
    }

    private void addToArray() {
        int[] arrayToAdd = new int[sortList.size()];

        for (int i = 0; i < arrayToAdd.length; i++) {
            arrayToAdd[i] = sortList.get(i);
        }
        searchArray = Arrays.copyOf(searchArray, searchArray.length + arrayToAdd.length);
        System.arraycopy(arrayToAdd, 0, searchArray, searchArray.length - arrayToAdd.length, arrayToAdd.length);
        searchArray = sorter.doBucketSort(searchArray);
        sortList.clear();

    }

    public int[] getSearchArray() {
        return searchArray;
    }

    public void setSearchArray(int[] searchArray) {
        this.searchArray = searchArray;
    }

    public ArrayList<Integer> getSortList() {
        return sortList;
    }

    public void setSortList(ArrayList<Integer> sortList) {
        this.sortList = sortList;
    }
}
