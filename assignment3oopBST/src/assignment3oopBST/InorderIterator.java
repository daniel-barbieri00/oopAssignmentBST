package assignment3oopBST;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * InorderIterator for a Binary Search Tree (BST).
 *
 * @param <E> The type of element this iterator returns.
 */
public class InorderIterator<E extends Comparable<? super E>> implements Iterator<E> {
    private BSTreeNode<E> current;
    private Stack<BSTreeNode<E>> stack;

    /**
     * Constructor for the InorderIterator.
     *
     * @param root The root of the Binary Search Tree.
     */
    public InorderIterator(BSTreeNode<E> root) {
        stack = new Stack<>();
        current = root;
        while (current != null) {
            stack.push(current);
            current = current.getLeft();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the iteration");
        }

        BSTreeNode<E> node = stack.pop();
        E result = node.getData();

        current = node.getRight();
        while (current != null) {
            stack.push(current);
            current = current.getLeft();
        }

        return result;
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

