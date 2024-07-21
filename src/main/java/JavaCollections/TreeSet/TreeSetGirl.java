package JavaCollections.TreeSet;
import java.util.Objects;

public class TreeSetGirl{

    private Node root;
    private int size;

    public TreeSetGirl() {
        this.root = null;
        this.size = 0;
    }

    public boolean add(Girl girl) {
        if (root == null) {
            root = new Node(girl);
            size++;
            return true;
        } else {
            return add(girl, root);
        }
    }

    private boolean add(Girl girl, Node node) {
        if (girl.equals(node.value)) {
            return false; // Duplicate element
        } else if (girl.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node(girl);
                size++;
                return true;
            } else {
                return add(girl, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(girl);
                size++;
                return true;
            } else {
                return add(girl, node.right);
            }
        }
    }

    public boolean remove(Girl girl) {
        if (root == null) {
            return false;
        } else {
            boolean[] removed = new boolean[1];
            root = remove(girl, root, removed);
            if (removed[0]) {
                size--;
            }
            return removed[0];
        }
    }

    private Node remove(Girl girl, Node node, boolean[] removed) {
        if (node == null) {
            return null;
        }
        if (girl.equals(node.value)) {
            removed[0] = true;
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node minNode = findMin(node.right);
                node.value = minNode.value;
                node.right = remove(minNode.value, node.right, removed);
                return node;
            }
        } else if (girl.compareTo(node.value) < 0) {
            node.left = remove(girl, node.left, removed);
            return node;
        } else {
            node.right = remove(girl, node.right, removed);
            return node;
        }
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public boolean contains(Girl girl) {
        return contains(girl, root);
    }

    private boolean contains(Girl girl, Node node) {
        if (node == null) {
            return false;
        }
        if (girl.equals(node.value)) {
            return true;
        } else if (girl.compareTo(node.value) < 0) {
            return contains(girl, node.left);
        } else {
            return contains(girl, node.right);
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    private static class Node {
        Girl value;
        Node left, right;

        Node(Girl value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // Traversare in-order pentru testare
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.value);
            inOrderTraversal(node.right);
        }
    }
}
