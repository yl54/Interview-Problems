package InterviewProblems.Tree;

import InterviewProblems.Utils.Nodes.TreeNode;
import InterviewProblems.Utils.TreeUtils;
import InterviewProblems.Utils.Utils;

// This code deals with making binary trees
//   from a inorder array and a preorder array.
// This can be done if a postorder array is
//   replaced with the preorder array.
public class MakeTree
{
    public static void main(String[] args)
    {
        // Balanced tree case.
        int[] preorder_balanced = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder_balanced = {4, 2, 5, 1, 6, 3, 7};

        // Skewed tree
        int[] preorder_skew = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] inorder_skew = {4, 3, 5, 2, 1, 6, 9, 8, 7};

        TreeNode balanced_tree = MakeTree.makeTree(preorder_balanced, inorder_balanced);
        System.out.println();
        TreeNode skewed_tree = MakeTree.makeTree(preorder_skew, inorder_skew);

        int[] pre_bal = TreeUtils.getPreorderTraversal(balanced_tree);
        int[] pre_ske = TreeUtils.getPreorderTraversal(skewed_tree);

        System.out.println("Print the preorder traversal of the balanced tree:");
        Utils.printArray(pre_bal);
        System.out.println("Print the preorder traversal of the skewed tree:");
        Utils.printArray(pre_ske);
    }



    // pre_index stores the current traversal
    //   in the preorder array.
    public static int pre_index;

    // Pre: pre is a preorder traversal. Note: this preorder
    //        can be replaced with a postorder traversal as well.
    //      in is an inorder traversal.
    // Assume: pre and in have no duplicates and all of the
    //           same node ids.
    //         pre and in form a valid tree.
    // Post: return a tree that uniquely matches these traversals.
    public static TreeNode makeTree(int[] pre, int[] in)
    {
        // Check if pre or in is null or empty or different lengths.
        if(pre == null || in == null || pre.length != in.length)
        {
            return null;
        }

        // Initialize pre_index.
        MakeTree.pre_index = 0;

        // Recurse through the tree.
        return MakeTree.makeTree(pre, in, 0, pre.length - 1);
    }

    // Pre: b_index and e_index marks the boundary of travel.
    // Post: returns a TreeNode that reflects the travel
    //         paths from pre and in from b_index to e_index.
    public static TreeNode makeTree
                (int[] pre, int[] in, int b_index, int e_index)
    {
        System.out.println(b_index + " : " + e_index);

        // Check if b_index is greater than e_index.
        if(b_index > e_index || MakeTree.pre_index >= pre.length)
        {
            return null;
        }

        int pre_value = pre[MakeTree.pre_index];

        // Build a tree node at current index.
        // Pay attention to pre_index.
        TreeNode node = new TreeNode(pre_value);
        MakeTree.pre_index++;

        // Check if b_index is equal to e_index. Means it's a leaf.
        if(b_index == e_index)
        {
            return node;
        }

        // Check where the preorder value is in the inorder array.
        // This will suck if the everything is to the left.
        int middle_index = -1;
        for(int i = 0; i < pre.length; i++)
        {
            if(pre_value == in[i])
            {
                middle_index = i;
                break;
            }
        }

        // Make tree nodes on left and right side.
        node.left = MakeTree.makeTree(pre, in, b_index, middle_index - 1);
        node.right = MakeTree.makeTree(pre, in, middle_index + 1, e_index);

        // Return the full tree.
        return node;
    }
}
