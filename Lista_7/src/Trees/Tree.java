package Trees;

import Trees.Tools.Color;

import java.util.concurrent.BlockingDeque;

import static Trees.Tools.Color.BLACK;
import static Trees.Tools.Color.RED;

public abstract class Tree {

    protected Node root;
    protected String[] treeGraph;

    public Tree() {
        root = null;
    }

    abstract public void insert(int value);

    abstract public void delete(int value);

    public Node search(int value) {
        return search(value, root);
    }

    private Node search(int value, Node root) {

        if (root == null || root.value == value) {
            return root;
        } else if (value < root.value) {
            return search(value, root.left);
        } else {
            return search(value, root.right);
        }
    }

    public int treeHeight() {
        return treeHeight(0, root);
    }

    private int treeHeight(int height, Node root) {
        if (root != null) {
            if (root.level > height) {
                height = root.level;
            }
            height = treeHeight(height, root.left);
            height = treeHeight(height, root.right);
        }
        return height;
    }

    public int nOfNodes() {
        return nOfNodes(0, root);
    }

    private int nOfNodes(int nodeCount, Node root) {
        if (root != null) {
            nodeCount++;
            nodeCount = nOfNodes(nodeCount, root.left);
            nodeCount = nOfNodes(nodeCount, root.right);
        }
        return nodeCount;
    }

    public int nOfInnerNodes() {
        return nOfInnerNodes(0, root);
    }

    private int nOfInnerNodes(int innerNodeCount, Node root) {
        if (root != null) {
            if (root.left != null || root.right != null) {
                innerNodeCount++;
                innerNodeCount = nOfInnerNodes(innerNodeCount, root.left);
                innerNodeCount = nOfInnerNodes(innerNodeCount, root.right);
            }
        }
        return innerNodeCount;
    }

    public int nOfLeafs() {
        return nOfLeafs(0, root);
    }

    private int nOfLeafs(int leafCount, Node root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                leafCount++;
            } else {
                leafCount = nOfLeafs(leafCount, root.left);
                leafCount = nOfLeafs(leafCount, root.right);
            }
        }
        return leafCount;
    }

    public int maxValue() {
        return maxValue(root);
    }

    int maxValue(Node root) {
        if (this.root == null) {
            throw new NullPointerException();
        }

        if (root.right != null) {
            return maxValue(root.right);
        } else {
            return root.value;
        }
    }

    public int minValue() {
        return minValue(root);
    }

    int minValue(Node root) {
        if (this.root == null) {
            throw new NullPointerException();
        }

        if (root.left != null) {
            return minValue(root.left);
        } else {
            return root.value;
        }
    }

    public void drawTree() {
        int height = treeHeight(0, root);

        if (height == 0 && root != null)
            System.out.println(root.getValue());
        else {

            treeGraph = new String[treeHeight() + 1];

            for (int i = 0; i < treeGraph.length; i++) {
                treeGraph[i] = "";
            }

            drawTree(root, 0, height);

            for (String s : treeGraph) {
                System.out.println(s);
            }
        }
    }

    private void drawTree(Node root, int level, int height) {

        if (treeGraph[level].equals("")) {
            for (int i = 0; i < Math.pow(2, (height - level)); i++)
                treeGraph[level] += "\t";
        } else {
            for (int i = 0; i < Math.pow(2, (height - level + 1)); i++)
                treeGraph[level] += "\t";
        }

        if (root != null) {
            if(root.color == null)
                treeGraph[level] += String.valueOf(root.value);
            if(root.color == RED)
                treeGraph[level] += ("\u001B[31m" + String.valueOf(root.value) + "\u001B[0m");
            if(root.color == BLACK)
                treeGraph[level] += (String.valueOf(root.value));
        }

        if (level < height) {
            if (root == null) {
                drawTree(null, level + 1, height);
                drawTree(null, level + 1, height);
            } else {
                drawTree(root.left, level + 1, height);
                drawTree(root.right, level + 1, height);
            }
        }
    }

    static abstract class Node {

        Node left;
        Node right;
        int value;
        int level;
        Node parent;
        Color color;

        Node(Node left, Node right, int value) {

            this.left = left;
            this.right = right;
            this.value = value;
            this.level = 0;
        }

        Node(Node left, Node right, Node parent, int value) {
            this(left,right,value);
            this.parent = parent;
            this.color = RED;
        }

        Node(Node left, Node right, Node parent, int value, Color color) {
            this(left,right,value);
            this.parent = parent;
            this.color = color;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
