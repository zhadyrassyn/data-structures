import java.util.Queue;
import java.util.LinkedList;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(10);
    }
}

class BinarySearchTree<E extends Comparable<? super E>> {
    TreeNode<E> root;

    public BinarySearchTree() {}

    public BinarySearchTree(E data) {
        setRoot(data);
    }

    public TreeNode<E> getRoot() {
        return this.root;
    }

    public void setRoot(E data) {
        if(root == null) {
            TreeNode<E> newNode = new TreeNode<E>(data, null);
            this.root = newNode; 
        } else {
            root.setValue(data);
        }
    }

    public boolean remove(E data) {
        TreeNode<E> temp = this.root;
        boolean left = false;
        while(temp != null && temp.getValue().compareTo(data) != 0) {
            if(data.compareTo(temp.getValue()) < 0) {
                temp = temp.getLeftChild();
                left = true;
            } else {
                temp = temp.getRightChild();
                left = false;
            }
        }
        if(temp == null) {
            return false;
        } else {
            if(temp.isLeaf()) { 
                removeLeaf(left, temp);
            } else if(!temp.isLeaf() && !temp.hasBothChild()) {
                removeNodeWithOneChild(left, temp);
            } else if(temp.hasBothChild()){
                TreeNode<E> curr = temp;
                temp = temp.getRightChild();
                left = false;
                while(temp.getLeftChild() != null) {
                    temp = temp.getLeftChild();
                    left = true;
                }
                curr.setValue(temp.getValue());
                if(temp.isLeaf()) {
                    removeLeaf(left, temp);
                } else {
                    removeNodeWithOneChild(left, temp);
                }
            }
        }
        return true;
    }

    private void removeLeaf(boolean left, TreeNode<E> temp) {
        if(temp.getParent() == null) {
            temp = null;
            root = null;
        } else {
            if(left) {
                temp.getParent().setLeftChild(null);
            } else {
                temp.getParent().setRightChild(null);
            }
        }
        
    }

    private void removeNodeWithOneChild(boolean left, TreeNode<E> temp) {
        if(temp.getParent() == null) {
            if(temp.getLeftChild() != null) {
                this.root = temp.getLeftChild();
            } else {
                this.root = temp.getRightChild();
            }
        } else {
            if(left) {
                temp.getParent().setLeftChild(temp.getOneChild());
            } else {
                temp.getParent().setRightChild(temp.getOneChild());
            }
        }
        
    }

    public void insert(E data) {
        if(root == null) {
            setRoot(data);
        } else {
            TreeNode<E> temp = root;
            while(temp.getLeftChild() != null || temp.getRightChild() != null) {
                int compareResult = data.compareTo(temp.getValue());
                if(compareResult < 0) {
                    if(temp.getLeftChild() == null) {
                        break;
                    } else {
                        temp = temp.getLeftChild();
                    }
                } else {
                    if(temp.getRightChild() == null) {
                        break;
                    } else {
                        temp = temp.getRightChild();
                    }
                }
            }
            int compareRes = data.compareTo(temp.getValue());
            if(compareRes < 0) {
                temp.addLeftChild(data);
            } else {
                temp.addRightChild(data);
            }
        }
    }

    public boolean contains(E data) {
        TreeNode<E> temp = this.root;
        while(temp != null) {
            if(temp.getValue().compareTo(data) == 0) {
                return true;
            } else if(data.compareTo(temp.getValue()) < 0) {
                temp = temp.getLeftChild();
            } else {
                temp = temp.getRightChild();
            }
        }
        return false;
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
        System.out.println();
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
        System.out.println();
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
        System.out.println();
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
        System.out.println();
    }
}
class TreeNode<E> {
    private E value;
    private TreeNode<E> parent;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    public TreeNode(E val) {
        this.value = val;
    }

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

    public TreeNode<E> getOneChild() {
        if(getLeftChild() != null) {
            return getLeftChild();
        }
        return getRightChild();
    }

    public boolean isLeaf() {
        if(getLeftChild() == null && getRightChild() == null) {
            return true;
        }
        return false;
    }

    public boolean hasBothChild() { 
        if(getLeftChild() != null && getRightChild() != null) {
            return true;
        }
        return false;
    }
}

