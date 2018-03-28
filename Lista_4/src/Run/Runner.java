package Run;

import Binary_Search.BinarySearch;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] arrayToSearch = new int[1000000];

        for (int i = 0; i < arrayToSearch.length; i++) {
            arrayToSearch[i] = (int) (-1000000 + Math.random() * 2000001);
        }

        BinarySearch search1 = new BinarySearch(arrayToSearch);

        System.out.println("This program will generate an array of 1000000 integers from -1000000 to 1000000" + "\n"
                + "Write a integer and if it is included in the generated array, you will find out on which position it is." + "\n"
                + "if the generated array doesn't contain a specific integer you will get a -1" + "\n");
        System.out.println("Give your key number between - 1000000 and 1000000 please: ");

        int key = sc.nextInt();

        search1.add(key);

        System.out.println("\n" + "The key number position is: " + search1.doBinarySearch(key));

    }
}
