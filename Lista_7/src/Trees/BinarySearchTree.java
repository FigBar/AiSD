package Trees;

public class BinarySearchTree extends Tree {


    public BinarySearchTree() {
        super();
    }

    @Override
    public void insert(int value) {
        insert(value, root);
    }

    private Node insert(int value, Node root) {

        if (this.root == null) {
            this.root = new NodeBST(null, null, value);
            return this.root;
        }

        if (root == null) {
            root = new NodeBST(null, null, value);
            return root;
        } else if (value < root.value) {
            root.left = insert(value, root.left);

            if (root.left != null) {
                root.left.level = root.level + 1;
            }

        } else if (value > root.value) {
            root.right = insert(value, root.right);

            if (root.right != null) {
                root.right.level = root.level + 1;
            }
        }
        return root;
    }

    @Override
    public void delete(int value) {
        delete(value, root);
    }

    private Node delete(int value, Node root) {

        if (root == null) {
            return null;
        } else if (root.value > value) {
            root.left = delete(value, root.left);
        } else if (root.value < value) {
            root.right = delete(value, root.right);
        } else {

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.value = minValue(root.right);
            root.right = delete(root.value, root.right);
        }
        return root;
    }

    static class NodeBST extends Tree.Node{

        NodeBST(NodeBST left, NodeBST right, int value){
            super(left,right,value);
        }
    }
}
