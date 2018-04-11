package Trees;

import Trees.Tools.Color;

import static Trees.Tools.Color.BLACK;
import static Trees.Tools.Color.RED;


public class RedBlackTree extends Tree {

    public RedBlackTree() {
        super();
    }

    @Override
    public void insert(int value) {
        insert(value, root, null);
        Node newVal = search(value);
        regulateTree(newVal);

    }

    private Node insert(int value, Node root, Node parent) {

        if (this.root == null) {
            this.root = new NodeRB(null, null, parent, value);
            return this.root;
        }

        if (root == null) {
            root = new NodeRB(null, null, parent, value);
            return root;
        } else if (value < root.value) {
            root.left = insert(value, root.left, root);

            if (root.left != null) {
                root.left.level = root.level + 1;
            }

        } else if (value > root.value) {
            root.right = insert(value, root.right, root);

            if (root.right != null) {
                root.right.level = root.level + 1;
            }
        }
        return root;
    }


    @Override
    public void delete(int value) {

    }

    private void regulateTree(Node root) {
        if (root != null) {
            if (root == this.root) {
                this.root.color = BLACK;
            }

            Node rootParent = root.parent;
            Node grandparent = null;

            if (rootParent != null) {
                if (rootParent.parent != null) {
                    grandparent = root.parent.parent;
                }
            }

            if (grandparent == null)
                return;

            Node uncle;

            if (rootParent == grandparent.left)
                uncle = grandparent.right;
            else
                uncle = grandparent.left;

            if (uncle == null) {
                uncle = new NodeRB(null, null, grandparent, 0);
                uncle.color = BLACK;
            }

            if (rootParent.color == RED) {
                if (uncle.color == RED) {
                    uncle.color = BLACK;
                    rootParent.color = BLACK;
                    grandparent.color = RED;
                    regulateTree(grandparent);
                } else {
                    if (rootParent == grandparent.left) {
                        boolean flag = false;
                        if (root == rootParent.right) {
                            leftRotation(rootParent);
                            flag = true;
                        }
                        rightRotation(grandparent);

                        if (flag) {
                            Color tempColor = grandparent.color;
                            grandparent.color = root.color;
                            root.color = tempColor;
                        } else {
                            Color tempColor = grandparent.color;
                            grandparent.color = rootParent.color;
                            rootParent.color = tempColor;
                        }

                    } else {
                        boolean flag = false;
                        if (root == rootParent.left) {
                            rightRotation(rootParent);
                            flag = true;
                        }
                        leftRotation(grandparent);

                        if (flag) {
                            Color tempColor = grandparent.color;
                            grandparent.color = root.color;
                            root.color = tempColor;

                        } else {
                            Color tempColor = grandparent.color;
                            grandparent.color = rootParent.color;
                            rootParent.color = tempColor;
                        }
                    }
                }
            }
        }
    }

    private void leftRotation(Node rotatedNode) {
        Node rightChild = rotatedNode.right;
        int i = rotatedNode.level;

        rotatedNode.level = rightChild.level;
        modifyLevel(rotatedNode.left);

        rotatedNode.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = rotatedNode;
        }
        rightChild.parent = rotatedNode.parent;

        if (rightChild.parent == null) {
            root = rightChild;
        } else if (rotatedNode == rotatedNode.parent.left) {
            rotatedNode.parent.left = rightChild;
        } else {
            rotatedNode.parent.right = rightChild;
        }

        rightChild.left = rotatedNode;
        rotatedNode.parent = rightChild;

        rightChild.level = i;
        modifyLevel(rightChild.right);
    }

    private void rightRotation(Node rotatedNode) {

        Node leftChild = rotatedNode.left;
        int i = rotatedNode.level;

        rotatedNode.level = leftChild.level;
        modifyLevel(rotatedNode.right);

        rotatedNode.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = rotatedNode;
        }
        leftChild.parent = rotatedNode.parent;

        if (leftChild.parent == null) {
            root = leftChild;
        } else if (rotatedNode == rotatedNode.parent.left) {
            rotatedNode.parent.left = leftChild;
        } else {
            rotatedNode.parent.right = leftChild;
        }

        leftChild.right = rotatedNode;
        rotatedNode.parent = leftChild;

        leftChild.level = i;
        modifyLevel(leftChild.left);

    }

    private void modifyLevel(Node root) {
        if (root != null) {
            root.level = root.parent.level + 1;
            modifyLevel(root.left);
            modifyLevel(root.right);
        }
    }


    static class NodeRB extends Tree.Node {

        NodeRB(Node left, Node right, Node parent, int value) {
            super(left, right, parent, value);
        }

        NodeRB(Node left, Node right, Node parent, int value, Color color) {
            super(left, right, parent, value, color);
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }


}
