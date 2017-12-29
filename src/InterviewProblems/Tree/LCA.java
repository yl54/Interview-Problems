package InterviewProblems.Tree;

import InterviewProblems.Utils.Nodes.TreeNode;
import InterviewProblems.Utils.TreeUtils;

// This class deals with the lowest common ancestor.
public class LCA
{
    public static void main(String[] args)
    {
        TreeNode root = TreeUtils.makeLCATree();
        TreeNode lca = LCA.findLCA(root, 5, 4);
        System.out.println("Common ancestor: " + lca.data);
    }

    // Pre: root is the root of the tree.
    //      num_1 is one element to look for.
    //      num_2 is another element to look for.
    // Assume: root contains n_1 and n_2.
    //         n_1 and n_2 appear only once in the tree, but
    //           it should work fine
    // Post: returns the number of the lowest common ancestor.
    public static TreeNode findLCA(TreeNode root, int n_1, int n_2)
    {
        // Check null/empty cases.
        if(root == null)
        {
            return null;
        }

        // Do recursion to go find the lowest common ancestor.
        return LCA.findLCAHelper(root, n_1, n_2);
    }

    // Pre: node is the current node it is traversing.
    //      n_1 is the first number to look for.
    //      n_2 is the second number to look for.
    // Post: returns the TreeNode that is the LCA.
    public static TreeNode findLCAHelper(TreeNode node, int n_1, int n_2)
    {
        // Check if node is null.
        if(node == null)
        {
            return null;
        }

        // Check if the current node is one of the elements.
        // If so, return node, as there is no need to recurse further.
        // It is either the LCA or location of one of the nodes.
        // If the search on the other side turns up as null,
        //   then this node is the LCA. Otherwise, it is just
        //   a node in the path.
        if(node.data == n_1 || node.data == n_2)
        {
            return node;
        }

        // Check if the left and right nodes contain one of the two.
        TreeNode left_lca = LCA.findLCAHelper(node.left, n_1, n_2);
        TreeNode right_lca = LCA.findLCAHelper(node.right, n_1, n_2);

        // If both are returned, then that means this node is the LCS
        if(left_lca != null && right_lca != null)
        {
            return node;
        }

        // Return which node is not null, because this will
        //   be the subtree that contains either n_1 or n_2.
        // If both are null, then it means neither node is
        //   in this node's subtree.
        return (left_lca != null)? left_lca : right_lca;
    }
}
