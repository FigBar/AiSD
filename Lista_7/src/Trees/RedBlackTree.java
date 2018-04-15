package Trees;

import Trees.Tools.Color;

import static Trees.Tools.Color.BLACK;
import static Trees.Tools.Color.DOUBLE_BLACK;
import static Trees.Tools.Color.RED;


public class RedBlackTree extends Tree {

    Node guardian = null;

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

        delete(this.root, value);

        if (guardian != null) {
            deleteRegulateTree(guardian);
        } else {
            Node tempNode = findDoubleBlack(this.root);
            if (tempNode != null) {
                deleteRegulateTree(tempNode);
            }
        }
    }


    private Node delete(Node root, int value) {
        if (root == null) {
            return null;
        } else if (root.value > value) {
            root.left = delete(root.left, value);
        } else if (root.value < value) {
            root.right = delete(root.right, value);
        } else {

            if (root == this.root && root.right == null && root.left == null) {
                this.root = null;
                return null;
            }

            if (root.left == null && root.right == null) {
                if (root.color == BLACK) {
                    guardian = new NodeRB(null, null, root.parent, 0, BLACK);
                    root.left = guardian;
                }
            }

            if (root.left == null) {
                if (root.right != null) {
                    if (root.right.color == RED || root.color == RED) {
                        root.right.color = BLACK;
                    } else {
                        root.right.color = DOUBLE_BLACK;
                    }
                    root.right.parent = root.parent;
                }
                return root.right;
            } else if (root.right == null) {
                if (root.left != null) {
                    if (root.left.color == RED || root.color == RED) {
                        root.left.color = BLACK;
                    } else {
                        root.left.color = DOUBLE_BLACK;
                    }
                    root.left.parent = root.parent;
                }
                return root.left;
            }

            root.value = maxValue(root.left);

            if (root.left.value == root.value) {
                if (root.left.right != null) {
                    root.left = root.left.right;
                } else {
                    root.left = root.left.left;
                }

                if (root.left != null) {
                    root.left.parent = root;
                    if (root.left.color == RED) {
                        root.left.color = BLACK;
                    }
                    //TODO fix this case
                /*} else {
                    guardian = new NodeRB(null,null,root, 0 , BLACK);
                    root.left = guardian;*/
                }
            } else {
                delete(root.left, root.value);
            }

            if (guardian != null) {
                deleteRegulateTree(guardian);
            } else {
                Node tempNode = findDoubleBlack(root);
                if (tempNode != null) {
                    deleteRegulateTree(tempNode);
                }
            }
        }
        return root;
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

    private void deleteRegulateTree(Node root) {

        if (root == null) {
            return;
        }

        if (root == this.root) {
            this.root.color = BLACK;
        }

        Node sibling;
        Node siblingsRightChild;
        Node siblingsLeftChild;
        Node parent = root.parent;

        if (parent == null) {
            return;
        }

        if (parent.left == root) {
            sibling = parent.right;
        } else {
            sibling = parent.left;
        }

        if (sibling == null) {
            sibling = new NodeRB(null, null, parent, 0, BLACK);
        }

        if (root == guardian) {
            if (parent.left == guardian) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            guardian = null;
        }

        if (sibling.left != null) {
            siblingsLeftChild = sibling.left;
        } else {
            siblingsLeftChild = new NodeRB(null, null, sibling, 0, BLACK);
        }

        if (sibling.right != null) {
            siblingsRightChild = sibling.right;
        } else {
            siblingsRightChild = new NodeRB(null, null, sibling, 0, BLACK);
        }

        if (sibling.color == RED) {// red sibling case
            sibling.color = BLACK;
            parent.color = RED;

            if (parent.left == root) {
                leftRotation(parent);
            } else {
                rightRotation(parent);
            }
            deleteRegulateTree(root);

        } else { // black sibling cases
            root.color = BLACK;

            if (siblingsLeftChild.color == BLACK && siblingsRightChild.color == BLACK) { //both children of sibling are black
                sibling.color = RED;
                if (parent.color == RED) {
                    parent.color = BLACK;
                } else {
                    parent.color = DOUBLE_BLACK;
                    deleteRegulateTree(parent);
                }
            } else if (parent.left == sibling) {
                if (siblingsRightChild.color == RED && siblingsLeftChild.color != RED) { //left - right & left-left cases
                    siblingsRightChild.color = BLACK;
                    leftRotation(sibling);
                    rightRotation(parent);
                } else {
                    siblingsLeftChild.color = RED;
                    rightRotation(parent);
                }
            } else {// right-left & right-right cases
                if (siblingsLeftChild.color == RED && siblingsRightChild.color != RED) {
                    siblingsLeftChild.color = BLACK;
                    rightRotation(sibling);
                    leftRotation(parent);
                } else {
                    siblingsRightChild.color = BLACK;
                    leftRotation(parent);
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

    private Node findDoubleBlack(Node root) {

        if (root != null) {
            if (root.color == DOUBLE_BLACK)
                return root;

            Node leftChild = findDoubleBlack(root.left);

            if (leftChild == null) {
                return findDoubleBlack(root.right);
            } else
                return leftChild;
        }
        return null;
    }

    private void modifyLevel(Node root) {
        if (root != null) {
            root.level = root.parent.level + 1;
            modifyLevel(root.left);
            modifyLevel(root.right);
        }
    }

    private void modifyLevelDelete(Node root) {
        if (root != null) {
            root.level = root.parent.level;
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
