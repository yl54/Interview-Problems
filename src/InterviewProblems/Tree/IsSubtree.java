package InterviewProblems.Tree;

import InterviewProblems.Utils.Nodes.TreeNode;
import InterviewProblems.Utils.TreeUtils;
import InterviewProblems.Utils.Utils;

// This program determines whether or not
//   a tree is a subtree of another tree.
public class IsSubtree
{
    public static void main(String[] args)
    {
        TreeNode root_1 = TreeUtils.makeSubTree();
        TreeNode root_2 = TreeUtils.makeTree();
        boolean isSubtree = IsSubtree.isSubTreePath(root_1, root_2);
        boolean isSubtreeNull = IsSubtree.isSubTree(null, null);
        System.out.println("isSubtree = " + isSubtree);
    }

    // General idea:
    // - look for a node in the big tree thats equal to
    //     the root of the small tree.
    // - if not found, then not a subtree
    // - if found, then check every node below for both trees
    //     to see if they are exactly the same or not.
    // This function determines whether or not the first
    //   tree is a subtree of the second tree via recursion.
    public static boolean isSubTree(TreeNode root_1, TreeNode root_2)
    {
        // Check if root_1 or root_2 is null.
        if(root_1 == null || root_2 == null)
        {
            return false;
        }

        // Do recursion to check.
        return isSubTreeHelper(root_1, root_2, false);
    }

    public static boolean isSubTreeHelper(TreeNode root_1, TreeNode root_2, boolean start)
    {
        // Check if both nodes are null.
        if(root_1 == null && root_2 == null)
        {
            // Return whether or not it was marked
            //   as a potential tree.
            return start;
        }

        // Check if one or the other is null.
        if(root_1 == null || root_2 == null)
        {
            return false;
        }

        boolean isSameData = (root_1.data == root_2.data);

        // Check if the nodes are not the same and
        //   the subtree is marked as a subtree
        if(!isSameData && start)
        {
            return false;
        }

        // Check if the nodes are the same.
        if(isSameData)
        {
            boolean found = isSubTreeHelper(root_1.left, root_2.left, true) && isSubTreeHelper(root_1.right, root_2.right, true);

            // Check if both are the same and marked as a potential subtree.
            if(found)
            {
                return true;
            }
        }

        // Continue to recurse through the left and right trees.
        return isSubTreeHelper(root_1, root_2.left, false) || isSubTreeHelper(root_1, root_2.right, false);
    }

    // This function deals with the situation when a potential
    //   subtree has not yet been found.
    public static boolean isSubtreeNotFound(TreeNode root_1, TreeNode node_2)
    {
        // Check if node_2 is null.
        if(node_2 == null)
        {
            return false;
        }

        // Check if the data is equal to each other.
        if(root_1.data == node_2.data)
        {
            // Check to see if this leads to a formation
            //   of node_1 being a subtree of node_2.
            if(isSubtreeFound(root_1, node_2))
            {
                return true;
            }
        }
        // Check both sides of the subtree.
        return IsSubtree.isSubtreeNotFound(root_1, node_2.left) ||
               IsSubtree.isSubtreeNotFound(root_1, node_2.right);
    }

    // This function deals with the situation where a potential
    //   subtree has been found.
    public static boolean isSubtreeFound(TreeNode node_1, TreeNode node_2)
    {
        // Check if both are null.
        if(node_1 == null && node_2 == null)
        {
            return true;
        }

        // Check if one is null and the other isn't.
        if(node_1 == null || node_2 == null)
        {
            return false;
        }

        // Check if data matches.
        if(node_1.data != node_2.data)
        {
            return false;
        }

        // Check if the remaining parts of the tree suggest that
        //   node_1 nad node_2 are the same tree.
        return isSubtreeFound(node_1.left, node_2.left) &&
               isSubtreeFound(node_1.right, node_2.right);
    }

    // This function stores the preorder and inorder paths for the
    //   two trees. The combination of preorder and inorder path
    //   uniquely identify a binary tree. (Need to do problem of
    //   build a binary tree from pre and in order paths).
    public static boolean isSubTreePath(TreeNode root_1, TreeNode root_2)
    {
        // Check if either root is null.
        if(root_1 == null || root_2 == null)
        {
            return false;
        }

        // Check the preorder and inorder paths of root_1 and root_2.
        int[] root_1_preorder = TreeUtils.getPreorderTraversal(root_1);
        int[] root_1_inorder = TreeUtils.getInorderTraversal(root_1);
        int[] root_2_preorder = TreeUtils.getPreorderTraversal(root_2);
        int[] root_2_inorder = TreeUtils.getInorderTraversal(root_2);

        // Check if root_1s paths are subpaths of root_2.
        return Utils.isSubArray(root_1_preorder, root_2_preorder) &&
               Utils.isSubArray(root_1_inorder, root_2_inorder);
    }
}
