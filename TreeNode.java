/**
 * Class that represending one node of the binary tree
 */
public class TreeNode<T extends Comparable<T>>
{
    /**
     * Value held in the node
     */
    public T value;
    /**
     * Pointer to left node
     */
    public TreeNode<T> left;
    /**
     * Pointer to right node
     */
    public TreeNode<T> right;

    /**
     * @param val Item that will be the vaule holding by the node
     */
    public TreeNode(T val)
    {
        value = val;
    }
}