package assignment3oopBST;


import java.util.NoSuchElementException;
import java.util.Stack;

import utilities.BSTreeADT;
import utilities.Iterator;
import assignment3oopBST.BSTreeNode;

/**
 * Binary Search Tree implementation adhering to the BSTreeADT interface.
 *
 * @param <E> The type of elements stored in the tree, must implement Comparable
 */
public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {
    private BSTreeNode<E> root;

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

    public BSTreeNode<E> searchRecursive(BSTreeNode<E> node, E entry) {
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
    
    
    public utilities.Iterator<E> inorderIterator() {
        return new InorderIterator<>(root);
    }

    
    public utilities.Iterator<E> preorderIterator() {
        return new PreorderIterator<>(root);
    }

    
    public Iterator<E> postorderIterator() {
        return new PostorderIterator<>(root);
    }
    
 // Inorder Iterator
    public class InorderIterator<E extends Comparable<? super E>> implements Iterator<E> {

        private Stack<BSTreeNode<E>> stack;
        private BSTreeNode<E> current;

        public InorderIterator(BSTreeNode<E> root) {
            stack = new Stack<>();
            current = root;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || current != null;
        }

        @Override
        public E next() {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }

            current = stack.pop();
            BSTreeNode<E> node = current;
            current = current.getRight();

            return node.getData();
        }
    }

    // Preorder Iterator
    public class PreorderIterator<E extends Comparable<? super E>> implements Iterator<E> {

        private Stack<BSTreeNode<E>> stack;

        public PreorderIterator(BSTreeNode<E> root) {
            stack = new Stack<>();
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements in the iteration.");
            }

            BSTreeNode<E> current = stack.pop();
            if (current.getRight() != null) {
                stack.push(current.getRight());
            }
            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }

            return current.getData();
        }
    }

    // Postorder Iterator
    public class PostorderIterator<E extends Comparable<? super E>> implements Iterator<E> {

        private Stack<BSTreeNode<E>> stack;

        public PostorderIterator(BSTreeNode<E> root) {
            stack = new Stack<>();
            fillStack(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements in the iteration.");
            }

            return stack.pop().getData();
        }

        private void fillStack(BSTreeNode<E> node) {
            if (node == null) {
                return;
            }

            stack.push(node);

            fillStack(node.getRight());
            fillStack(node.getLeft());
        }
    }

}
