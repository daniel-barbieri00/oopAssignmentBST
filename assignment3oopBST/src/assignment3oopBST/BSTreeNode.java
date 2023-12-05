// BSTreeNode.java

package assignment3oopBST;

/**
 * Node in a Binary Search Tree.
 * @param <E> Type of elements stored in the node.
 */
public class BSTreeNode<E> {
    private E data;             // Stored data.
    private BSTreeNode<E> left;  // Left child.
    private BSTreeNode<E> right; // Right child.

    /**
     * Creates a node with specified data.
     * @param data The data to be stored.
     */
    public BSTreeNode(E data) { this.data = data; }

    /**
     * Gets the stored data.
     * @return The stored data.
     */
    public E getData() { return data; }

    /**
     * Gets the left child.
     * @return The left child.
     */
    public BSTreeNode<E> getLeft() { return left; }

    /**
     * Gets the right child.
     * @return The right child.
     */
    public BSTreeNode<E> getRight() { return right; }

    /**
     * Sets the left child.
     * @param left The new left child.
     */
    public void setLeft(BSTreeNode<E> left) { this.left = left; }

    /**
     * Sets the right child.
     * @param right The new right child.
     */
    public void setRight(BSTreeNode<E> right) { this.right = right; }
}
