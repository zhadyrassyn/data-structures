import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree<String> bt = new BinaryTree<String>();
        bt.addRoot("Zhadyrassyn");
        TreeNode<String> root = bt.getNode("Zhadyrassyn");
        if(root != null) {
            bt.getRoot().addLeftChild("Temirbek");
            bt.getRoot().addRightChild("Aya");
            bt.getRoot().getLeftChild().addLeftChild("Daniyar");
            bt.getNode("Temirbek").addRightChild("Zarina");
            bt.getNode("Aya").addLeftChild("Kairat");
            bt.getNode("Aya").addRightChild("Kanat");
            bt.getNode("Kanat").addLeftChild("Daniyal");
            bt.getNode("Kairat").addLeftChild("Azamat");
            bt.getNode("Kairat").addRightChild("Adilbek");
            bt.levelOrder();
        }
    }
}

class BinaryTree<E> {
    TreeNode<E> root;

    public BinaryTree() {} 

    public BinaryTree(TreeNode<E> root) {
        this.root = root;
    }

    public boolean addRoot(E data) {
        if(root == null) {
            TreeNode<E> newNode = new TreeNode<E>(data, null);
            root = newNode;
            return true;
        }
        return false;
    }

    public TreeNode<E> getRoot() {
        return this.root;
    }

    private TreeNode<E> getNode(E data, TreeNode<E> node) {
        if(node != null) {
            if(node.getValue().equals(data)) {
                return node;
            } else {
                TreeNode<E> leftChild = getNode(data, node.getLeftChild());
                if(leftChild != null && leftChild.getValue().equals(data)) {
                    return leftChild;
                }
                TreeNode<E> rightChild = getNode(data, node.getRightChild());
                if(rightChild != null && rightChild.getValue().equals(data)) {
                    return rightChild;
                }
            }
        } 
        return null; 
    } 

    public TreeNode<E> getNode(E data) {
        TreeNode<E> temp = root;
        return this.getNode(data, temp);
    }
    
    private void preOrder(TreeNode<E> node) {
        if(node != null) {
            System.out.print(node + " ");
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }

    public void preOrder() {
        this.preOrder(root);
    }

    private void postOrder(TreeNode<E> node) {
        if(node != null) {
            postOrder(node.getLeftChild());
            postOrder(node.getRightChild());
            System.out.print(node + " ");
        }
    }

    public void postOrder() {
        this.postOrder(root);
    }

    private void inOrder(TreeNode<E> node) {
        if(node != null) {
            inOrder(node.getLeftChild());
            System.out.print(node + " ");
            inOrder(node.getRightChild());
        }
    }

    public void inOrder() {
        this.inOrder(root);
    }

    public void levelOrder() {
        Queue<TreeNode<E>> q = new LinkedList<TreeNode<E>>();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode<E> curr = q.remove();
            if(curr != null) {
                System.out.print(curr + " ");
                if(curr.getLeftChild() != null) {
                    q.add(curr.getLeftChild());
                }
                if(curr.getRightChild() != null) {
                    q.add(curr.getRightChild());
                }
            }
        }
    }
}

class TreeNode<E> {
    private E value;
    private TreeNode<E> parent;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    public TreeNode(E val, TreeNode<E> parent) {
        this.value = val;
        this.parent = parent;
    }

    public E getValue() {
        return this.value;
    }

    public void setValue(E val) {
        this.value = val;
    }

    public TreeNode<E> getParent() {
        return this.parent;
    }

    public void setParent(TreeNode<E> parent) {
        this.parent = parent;
    }

    public TreeNode<E> getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(TreeNode<E> node) {
        this.leftChild = node;
    }

    public TreeNode<E> getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(TreeNode<E> node) {
        this.rightChild = node;
    }

    public TreeNode<E> addLeftChild(E value) {
        this.leftChild = new TreeNode<E>(value, this);
        return this.leftChild;
    }

    public TreeNode<E> addRightChild(E value) {
        this.rightChild = new TreeNode<E>(value, this);
        return this.rightChild;
    }

    @Override
    public String toString() {
        return this.value + "";
    }
}
