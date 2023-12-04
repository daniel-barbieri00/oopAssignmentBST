//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
//^^ code for syncing

package assignment3oopBST;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Binary Search Tree implementation adhering to the BSTreeADT interface.
 *
 * @param <E> The type of elements stored in the tree, must implement Comparable
 */
public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {
    private BSTreeNode<E> root;

    public BSTree() {
        this.root = null;
    }

    public BSTreeNode<E> getRoot() {
        return root;
    }

    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = calculateHeight(node.getLeft());
            int rightHeight = calculateHeight(node.getRight());
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    public int size() {
        return countNodes(root);
    }

    private int countNodes(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public boolean contains(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException("Null entry not allowed");
        }
        return search(entry) != null;
    }

    public BSTreeNode<E> search(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException("Null entry not allowed");
        }
        return searchRecursive(root, entry);
    }

    private BSTreeNode<E> searchRecursive(BSTreeNode<E> node, E entry) {
        if (node == null || node.getData().equals(entry)) {
            return node;
        }

        if (entry.compareTo(node.getData()) < 0) {
            return searchRecursive(node.getLeft(), entry);
        } else {
            return searchRecursive(node.getRight(), entry);
        }
    }

    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null) {
            throw new NullPointerException("Null entry not allowed");
        }
        root = insertRecursive(root, newEntry);
        return true;
    }

    private BSTreeNode<E> insertRecursive(BSTreeNode<E> node, E newEntry) {
        if (node == null) {
            return new BSTreeNode<>(newEntry);
        }

        if (newEntry.compareTo(node.getData()) < 0) {
            node.setLeft(insertRecursive(node.getLeft(), newEntry));
        } else if (newEntry.compareTo(node.getData()) > 0) {
            node.setRight(insertRecursive(node.getRight(), newEntry));
        }

        return node;
    }

    public BSTreeNode<E> removeMin() {
        if (root == null) {
            return null;
        }

        BSTreeNode<E> minNode = findMin(root);
        root = removeMinRecursive(root);
        return minNode;
    }

    private BSTreeNode<E> findMin(BSTreeNode<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private BSTreeNode<E> removeMinRecursive(BSTreeNode<E> node) {
        if (node.getLeft() == null) {
            return node.getRight();
        }

        node.setLeft(removeMinRecursive(node.getLeft()));
        return node;
    }

    public BSTreeNode<E> removeMax() {
        if (root == null) {
            return null;
        }

        BSTreeNode<E> maxNode = findMax(root);
        root = removeMaxRecursive(root);
        return maxNode;
    }

    private BSTreeNode<E> findMax(BSTreeNode<E> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    private BSTreeNode<E> removeMaxRecursive(BSTreeNode<E> node) {
        if (node.getRight() == null) {
            return node.getLeft();
        }

        node.setRight(removeMaxRecursive(node.getRight()));
        return node;
    }

    public Iterator<E> inorderIterator() {
        return new InorderIterator(root);
    }

    public Iterator<E> preorderIterator() {
        return new PreorderIterator(root);
    }

    public Iterator<E> postorderIterator() {
        return new PostorderIterator(root);
    }

    // Inner class representing a node in the Binary Search Tree
    private static class BSTreeNode<E> {
        private E data;
        private BSTreeNode<E> left;
        private BSTreeNode<E> right;

        public BSTreeNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public E getData() {
            return data;
        }

        public BSTreeNode<E> getLeft() {
            return left;
        }

        public void setLeft(BSTreeNode<E> left) {
            this.left = left;
        }

        public BSTreeNode<E> getRight() {
            return right;
        }

        public void setRight(BSTreeNode<E> right) {
            this.right = right;
        }
    }
}
