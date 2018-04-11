package Runner;

import Trees.BinarySearchTree;
import Trees.RedBlackTree;

public class Run {
    public static void main(String[] args) {
        RedBlackTree tree1 = new RedBlackTree();

        tree1.insert(7);
        tree1.insert(5);
        tree1.insert(10);
        tree1.insert(-5);
        tree1.insert(35);
        tree1.insert(20);
        tree1.insert(38);
        tree1.insert(36);
        tree1.insert(-6);

        tree1.drawTree();

        tree1.insert(9);
        System.out.println("\n");
        tree1.drawTree();

        BinarySearchTree tree2 = new BinarySearchTree();

        tree2.insert(35);
        tree2.insert(5);
        tree2.insert(10);
        tree2.insert(-5);
        tree2.insert(7);
        tree2.insert(20);
        tree2.insert(38);
        tree2.insert(36);
        tree2.insert(-6);

        System.out.println("\n");
        tree2.drawTree();

        tree2.insert(9);
        System.out.println("\n");
        tree2.drawTree();

    }
}
