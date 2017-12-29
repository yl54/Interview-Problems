package InterviewProblems.Utils;

import InterviewProblems.Utils.Nodes.TreeNode;

public class TreeUtils
{
    public static TreeNode makeSmallTree()
    {
        TreeNode root = new TreeNode(1);
        return root;
    }

    public static TreeNode makeTwoTree()
    {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        return root;
    }

    public static TreeNode makeTree()
    {
        TreeNode root = new TreeNode(20);

        root.left = new TreeNode(2);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(1);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(4);

        root.right = new TreeNode(10);
        root.right.right = new TreeNode(-25);

        return root;
    }

    public static TreeNode makeSubTree()
    {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(3);
        root.right = new TreeNode(4);

        return root;

    }

    public static TreeNode makeLCATree()
    {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    // This function retrieves the inorder
    //   traversal for the tree.
    public static int[] getInorderTraversal(TreeNode node)
    {
        // Check if the node is null.
        if(node == null)
        {
            return null;
        }

        int total = 1;

        // Check if the left node has values.
        int[] left_values = TreeUtils.getInorderTraversal(node.left);
        boolean isLeftNull = left_values != null;
        if(isLeftNull)
        {
            total += left_values.length;
        }

        // Check if the right node has values.
        int[] right_values = TreeUtils.getInorderTraversal(node.right);
        boolean isRightNull = right_values != null;
        if(isRightNull)
        {
            total += right_values.length;
        }

        // Return whatever values appear.
        int index = 0;
        int[] path = new int[total];
        if(isLeftNull)
        {
            for(int i = 0; i < left_values.length; i++)
            {
                path[index] = left_values[i];
                index++;
            }
        }

        path[index] = node.data;
        index++;
        if(isRightNull)
        {
            for(int i = 0; i < right_values.length; i++)
            {
                path[index] = right_values[i];
                index++;
            }
        }

        return path;
    }

    // This function retrieves the preorder
    //   traversal for the tree.
    public static int[] getPreorderTraversal(TreeNode node)
    {
        // Check if the node is null.
        if(node == null)
        {
            return null;
        }

        int total = 1;

        // Check if the left node has values.
        int[] left_values = TreeUtils.getPreorderTraversal(node.left);
        boolean isLeftNull = left_values != null;
        if(isLeftNull)
        {
            total += left_values.length;
        }

        // Check if the right node has values.
        int[] right_values = TreeUtils.getPreorderTraversal(node.right);
        boolean isRightNull = right_values != null;
        if(isRightNull)
        {
            total += right_values.length;
        }

        // Return the values that appear in the middle, left, and right trees.
        int index = 0;
        int[] path = new int[total];
        path[index] = node.data;
        index++;
        if(isLeftNull)
        {
            for(int i = 0; i < left_values.length; i++)
            {
                path[index] = left_values[i];
                index++;
            }
        }

        if(isRightNull)
        {
            for(int i = 0; i < right_values.length; i++)
            {
                path[index] = right_values[i];
                index++;
            }
        }

        return path;
    }

    // This function makes a binary tree from a preorder
    //   path and an inorder path. This can also be done
    //   with a postorder and inorder path,
}
