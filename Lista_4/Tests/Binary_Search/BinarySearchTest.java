package Binary_Search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    public BinarySearch search1;

    ArrayList<Integer> list1;


    @BeforeEach
    void setUp() {
        int[] array = {7, 70, 1, 44, 12, 23, 9, 56, 4};

        list1 = new ArrayList<>();
        list1.add(3);
        list1.add(7);
        list1.add(10);

        search1 = new BinarySearch(array);
    }

    @Test
    void doBinarySearch() {

        assertEquals(6, search1.doBinarySearch(44));
        assertEquals(2, search1.doBinarySearch(7));

        assertEquals(0, search1.doBinarySearch(1));
        assertEquals(8, search1.doBinarySearch(70));

        assertEquals(-1, search1.doBinarySearch(71));

        search1.setSearchArray(null);

        assertThrows(NullPointerException.class, ()-> search1.doBinarySearch(1));
    }

    @Test
    void addAll() {
        search1.addAll(list1);
        search1.doBinarySearch(2);

        int[] expectedArray = {1,3,4,7,7,9,10,12,23,44,56,70};

        assertArrayEquals(expectedArray, search1.getSearchArray());
    }

    @Test
    void add() {
        search1.add(-4);
        search1.add(45);
        search1.add(-4);

        search1.doBinarySearch(4);

        int[] expectedArray = {-4,-4,1,4,7,9,12,23,44,45,56,70};
        assertArrayEquals(expectedArray, search1.getSearchArray());

    }
}