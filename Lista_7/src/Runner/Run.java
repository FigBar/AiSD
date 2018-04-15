package Runner;

import Trees.BinarySearchTree;
import Trees.RedBlackTree;

public class Run {
    public static void main(String[] args) {
        RedBlackTree tree1 = new RedBlackTree();

        tree1.insert(5);
        tree1.insert(100);
        tree1.insert(42);
        tree1.insert(49);
        tree1.insert(35);
        tree1.insert(36);
        tree1.insert(62);
        tree1.insert(3);
        tree1.insert(38);
        tree1.insert(43);
        tree1.insert(10);
        tree1.insert(77);
        tree1.insert(1);
        tree1.insert(41);
        tree1.insert(50);
        tree1.insert(11);
        tree1.insert(25);
        tree1.insert(59);
        tree1.insert(98);
        tree1.insert(44);

        tree1.drawTree();

        tree1.delete(1);
        tree1.delete(10);
        tree1.delete(25);
        tree1.delete(44);
        tree1.delete(59);


        /*tree1.delete(35);
        tree1.delete(25);
        tree1.delete(11);
        tree1.delete(10);
        tree1.delete(49); // O TU SIĘ ZJEBIE XDD
        */

        System.out.println("\n");

        tree1.drawTree();






        /*System.out.println("\n");
        System.out.println(tree1.nOfLeafs());*/


        /*tree1.insert(9);
        System.out.println("\n");
        tree1.drawTree();*/

        BinarySearchTree tree2 = new BinarySearchTree();

        tree2.insert(10);
        tree2.insert(20);
        tree2.insert(-5);
        tree2.insert(35);
        tree2.insert(20);
        tree2.insert(14);
        tree2.insert(36);
        tree2.insert(-6);

        System.out.println("\n");
        tree2.drawTree();

        tree2.delete(10);

        System.out.println("\n");
        tree2.drawTree();


       /* tree2.delete( -6);
        tree2.delete(36);
        tree2.delete(35);
        tree2.delete(20);
        tree2.delete(-5);
        tree2.delete(14);
        System.out.println("\n");
        tree2.drawTree();*/
    }
}
