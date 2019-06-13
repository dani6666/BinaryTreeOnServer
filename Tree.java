/**
 * Class representing a binary tree
 */
public class Tree<T extends Comparable<T>>
{
    /**
     * Root of the tree
     */
    public TreeNode treeRoot;

    /**
     * Method that searches the tree for given element
     * @param valueObject object in the right type that the tree is going to be searched for
     * @return True if item was found
     */
    public boolean search(Object valueObject)
	{
        T value = (T)valueObject;

		TreeNode<T> currentNode = treeRoot;

		while (currentNode != null)
		{
			if (currentNode.value.compareTo(value) > 0)
			{
				currentNode = currentNode.left;
			}
			else if (currentNode.value.compareTo(value) < 0)
			{
				currentNode = currentNode.right;
			}
			else
			{
				return true;
			}
		}
		
		return false;
	}

    /**
     * Method that inserts the given element to the tree
     * @param valueObject object in the right type that is going to be inserted
     * @return True if insertion was successful
     */
    public boolean insert(Object valueObject)
    {
        T value = (T)valueObject;
        boolean wasLastTurnright = false;
        TreeNode<T> currentNode = treeRoot, lastNode = null;

        if(treeRoot == null)
        {
            treeRoot = new TreeNode<T>(value);
            return true;
        }

        do
        {
            if(currentNode.value.compareTo(value) > 0)
            {
                lastNode = currentNode;
                currentNode = currentNode.left;
                wasLastTurnright = false;            
            }
            else if(currentNode.value.compareTo(value) < 0)
            {
                lastNode = currentNode;
                currentNode = currentNode.right;
                wasLastTurnright = true;
            }
            else
            {
                return false;
            }
        } while(currentNode != null);

        currentNode = new TreeNode<T>(value);
        if(lastNode != null)
        {
            if(wasLastTurnright)
            {
                lastNode.right = currentNode;
            }
            else
            {
                lastNode.left = currentNode;
            }
        }
        return true;
    }

    /**
     * Method that deletes given element from the tree
     * @param valueObject object in the right type that is going to be deleted
     * @return True if item was deleted
     */
    public boolean delete(Object valueObject)
    {
        T value = (T)valueObject;
        return delete(value, treeRoot, null, false);
    }

    private boolean delete(T value, TreeNode<T> startingNode, TreeNode<T> lastNode, boolean wasLastTurnright)
    {
        if(startingNode == null)
        {
            return false;
        }
        else if(startingNode.value.compareTo(value) == 0)
        {
            if(startingNode.right == null)
            {
                if(lastNode == null)
                {
                    treeRoot = startingNode.right;
                }
                else
                {
                    if(wasLastTurnright)
                    {
                        lastNode.right = startingNode.right;
                    }
                    else
                    {
                        lastNode.left = startingNode.left;
                    }
                }
            }
            else
            {
		if(startingNode.right.left == null)
        	{
        	    startingNode.value = startingNode.right.value;
            		startingNode.right= startingNode.right.right;
            		return true;
        	}
		else
		{
		startingNode.value = findAndDeleteMinValue(startingNode.right, startingNode);
		}
              
            }
            return true;
        }
        else if(startingNode.value.compareTo(value) > 0)
        {
            return delete(value, startingNode.left, startingNode, false);
        }
        else
        {
            return delete(value, startingNode.right, startingNode, true);
        }
    }

    private T findAndDeleteMinValue(TreeNode<T> startingNode, TreeNode<T> lastNode)
    {
        if(startingNode.left == null)
        {
            T result = startingNode.value;
            lastNode.left= startingNode.right;
            return result;
        }
        else
        {
            return findAndDeleteMinValue(startingNode.left, startingNode);
        }
    }

    /**
     * Method that draws the tree
     * @return String showing the whole build of the tree
     */
    public String draw()
    {
        return draw(treeRoot);
    }

    private String draw(TreeNode<T> currentNode)
    {
        if(currentNode == null)
        {
            return "-";
        }
        else
        {
            return currentNode.value.toString() + "( " + draw(currentNode.left) + " , " + draw(currentNode.right) + " )";
        }
    }
    
}
