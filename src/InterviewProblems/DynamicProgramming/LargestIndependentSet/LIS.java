package InterviewProblems.DynamicProgramming.LargestIndependentSet;

import InterviewProblems.Utils.Nodes.TreeNode;
import InterviewProblems.Utils.Utils;

// This problem deals with the largest independent
//   set from a binary tree.
public class LIS
{
    // The way to do the save states version of this program
    //   is to make another field on the TreeNode so that
    //   as you traverse, you can check to see if this field
    //   is set, much like the other dp problems with arrays
    //   storing results.

    public static int getIdpSetRec(TreeNode root)
    {
        // Check if root is invalid/null.
        if(root == null)
        {
            return -1;
        }

        // Recursively check tree.
        return getIdpSetRecHelper(root);
    }

    // Summary: This problem traverses the entire tree. Only two
    //            traversal paths from each node are important.
    //            One is if this node should be included and checking
    //            the grandchildren, skipping the parents.
    //            Two is that this node should not be included, so
    //            go straight to checking children.
    public static int getIdpSetRecHelper(TreeNode node)
    {
        // Check if node is null.
        if(node == null)
        {
            return 0;
        }

        // Don't need to set anything. Just traverse further
        //   down, it naturally will work. If traverse one, then
        //   it doesn't need to add one. If traverse to grandkids,
        //   then add one to reflect current node.
        int ch = getIdpSetRecHelper(node.left) + getIdpSetRecHelper(node.right);

        // Check if the child exists to get the grandchild numbers.
        int gch = 1;
        if(node.left != null)
        {
            gch += getIdpSetRecHelper(node.left.left);
            gch += getIdpSetRecHelper(node.left.right);
        }

        if(node.right != null)
        {
            gch += getIdpSetRecHelper(node.right.left);
            gch += getIdpSetRecHelper(node.right.right);
        }

        // Compare the child set amount vs the grandchild set amount.
        return Utils.max(ch, gch);
    }
}
