package Trees;

import Trees.Tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeTest {
    Tree RBT;
    Tree emptyRBT;

    @BeforeEach
    void setUp() {
        RBT = new RedBlackTree();
        emptyRBT = new RedBlackTree();

        RBT.insert(5);
        RBT.insert(100);
        RBT.insert(42);
        RBT.insert(49);
        RBT.insert(35);
        RBT.insert(36);
        RBT.insert(62);
        RBT.insert(3);
        RBT.insert(38);
        RBT.insert(43);
        RBT.insert(10);
        RBT.insert(77);
        RBT.insert(1);
        RBT.insert(41);
        RBT.insert(50);
        RBT.insert(11);
        RBT.insert(25);
        RBT.insert(59);
        RBT.insert(98);
        RBT.insert(44);
    }

    @Test
    void insert() {

        checkWhetherBalanced(RBT.root, 0);

        assertEquals(20, RBT.nOfNodes());

    }

    @Test
    void delete() {

        assertEquals(20, RBT.nOfNodes());

        RBT.delete(42);
        checkWhetherBalanced(RBT.root, 0);
        RBT.delete(50);
        checkWhetherBalanced(RBT.root, 0);
        RBT.delete(5);
        checkWhetherBalanced(RBT.root, 0);

        assertEquals(17, RBT.nOfNodes());

    }

    @Test
    void search() {


        assertEquals(25, RBT.search(25).getValue());
        assertEquals(10, RBT.search(10).getValue());
        assertEquals(77, RBT.search(77).getValue());
        assertEquals(35, RBT.search(35).getValue());

        assertNull(RBT.search(-1));
        assertNull(emptyRBT.search(23));

    }

    @Test
    void treeHeight() {

        assertEquals(0, emptyRBT.treeHeight());

        assertEquals(4, RBT.treeHeight());

        RBT.delete(1);
        RBT.delete(10);
        RBT.delete(25);
        RBT.delete(44);
        RBT.delete(59);

        assertEquals(3, RBT.treeHeight());


    }

    @Test
    void nOfLeafs() {

        assertEquals(9, RBT.nOfLeafs());
        assertEquals(0, emptyRBT.nOfLeafs());

        RBT.delete(1);
        RBT.delete(10);
        RBT.delete(25);
        RBT.delete(44);
        RBT.delete(59);

        assertEquals(8, RBT.nOfLeafs());





    }

    @Test
    void nOfNodes() {

        assertEquals(0, emptyRBT.nOfNodes());
        assertEquals(20,RBT.nOfNodes());

        RBT.insert(-7);
        RBT.insert(-35);

        assertEquals(22,RBT.nOfNodes());

        RBT.delete(1);
        RBT.delete(10);
        RBT.delete(25);
        RBT.delete(44);
        RBT.delete(59);

        assertEquals(17, RBT.nOfNodes());

    }

    @Test
    void nOfInnerNodes() {

        assertEquals(0,emptyRBT.nOfInnerNodes());

        assertEquals(11, RBT.nOfInnerNodes());

        RBT.delete(1);
        RBT.delete(10);
        RBT.delete(25);
        RBT.delete(44);
        RBT.delete(59);

        assertEquals(7, RBT.nOfInnerNodes());



    }

    @Test
    void maxValue() {

        assertEquals(100, RBT.maxValue());
        assertThrows(NullPointerException.class, () -> emptyRBT.maxValue());

        RBT.delete(100);
        assertEquals(98, RBT.maxValue());

        RBT.insert(200);
        assertEquals(200, RBT.maxValue());

    }

    @Test
    void minValue() {

        assertEquals(1, RBT.minValue());
        assertThrows(NullPointerException.class, () -> emptyRBT.maxValue());

        RBT.delete(1);
        assertEquals(3, RBT.minValue());

        RBT.insert(-100);
        assertEquals(-100, RBT.minValue());

    }

    private static int checkWhetherBalanced(BinarySearchTree.Node node, int length) {

        int leftLength = 0;
        int rightLength = 0;

        if (node != null) {
            leftLength = checkWhetherBalanced(node.left, length);
            rightLength = checkWhetherBalanced(node.right, length);
            assertEquals(leftLength, rightLength);
        }

        if (node == null || node.color == Color.BLACK) {
            length++;
        }

        return length + rightLength;
    }
}