package Trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    Tree BST;
    Tree emptyBST;

    @BeforeEach
    void setUp() {
        BST = new BinarySearchTree();
        emptyBST = new BinarySearchTree();

        BST.insert(10);
        BST.insert(20);
        BST.insert(-5);
        BST.insert(35);
        BST.insert(20);
        BST.insert(14);
        BST.insert(36);
        BST.insert(-6);

    }

    @Test
    void insert() {

        emptyBST.insert(10);
        emptyBST.insert(20);
        emptyBST.insert(-5);
        emptyBST.insert(35);
        emptyBST.insert(20);

        assertEquals(4, emptyBST.nOfNodes());

        assertEquals(20, emptyBST.search(20).getValue());
        assertEquals(10, emptyBST.search(10).getValue());
        assertEquals(-5, emptyBST.search(-5).getValue());
        assertEquals(35, emptyBST.search(35).getValue());
    }

    @Test
    void delete() {

        assertEquals(7, BST.nOfNodes());

        BST.delete(20);
        assertNull(BST.search(20));
        BST.delete(35);
        assertNull(BST.search(35));

        assertEquals(5, BST.nOfNodes());

    }

    @Test
    void search() {

        assertEquals(20, BST.search(20).getValue());
        assertEquals(10, BST.search(10).getValue());
        assertEquals(-5, BST.search(-5).getValue());
        assertEquals(35, BST.search(35).getValue());

        assertNull(BST.search(100));
        assertNull(emptyBST.search(23));
    }

    @Test
    void treeHeight() {

        assertEquals(3, BST.treeHeight());
        assertEquals(0, emptyBST.treeHeight());

        BST.insert(40);

        assertEquals(4, BST.treeHeight());

        BST.delete(40);
        BST.delete(36);

        assertEquals(2, BST.treeHeight());
    }

    @Test
    void nOfLeafs() {
        assertEquals(3, BST.nOfLeafs());

        assertEquals(0, emptyBST.nOfLeafs());

        BST.insert(24);
        assertEquals(4, BST.nOfLeafs());

        BST.delete(14);
        BST.delete(24);
        assertEquals(2, BST.nOfLeafs());
    }

    @Test
    void nOfNodes() {
        assertEquals(7, BST.nOfNodes());
        assertEquals(0, emptyBST.nOfNodes());

        BST.insert(20);
        assertEquals(7, BST.nOfNodes());

        BST.insert(100);
        assertEquals(8, BST.nOfNodes());

        BST.delete(100);
        BST.delete(10);
        assertEquals(6, BST.nOfNodes());
    }

    @Test
    void nOfInnerNodes() {
        assertEquals(0, emptyBST.nOfInnerNodes());
        assertEquals(4, BST.nOfInnerNodes());

        BST.insert(15);
        BST.insert(4);

        assertEquals(5, BST.nOfInnerNodes());

        BST.delete(20);
        BST.delete(35);

        assertEquals(3, BST.nOfInnerNodes());
    }

    @Test
    void maxValue() {
        assertEquals(36, BST.maxValue());
        assertThrows(NullPointerException.class, () -> emptyBST.maxValue());

        BST.insert(100);
        assertEquals(100, BST.maxValue());
    }

    @Test
    void minValue() {

        assertEquals(-6, BST.minValue());
        assertThrows(NullPointerException.class, () -> emptyBST.maxValue());

        BST.insert(-100);
        assertEquals(-100, BST.minValue());

    }
}