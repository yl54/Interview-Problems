package InterviewProblems.Tree;

import InterviewProblems.Utils.Nodes.TreeNode;

import java.util.TreeMap;

// This problem deals with printing different views
//   of a binary tree.

// Different view to print:
// - top
// - right
// - left
// - bottom
public class PrintViews
{
    public static void main(String[] args)
    {

    }

    // This treemap records the current values that
    //   are at the front of the viewpoint.
    // Using a treemap for sorting convenience.
    public static TreeMap<Integer, Integer> view;

    public static void printTopView(TreeNode root)
    {
        // Check if the node is null
        if(root == null)
        {
            return;
        }

        // Print top view in recursive fashion.
        PrintViews.printTopViewHelper(root, 0);
    }

    public static void printTopViewHelper(TreeNode node, int side)
    {
        // Check if node is null.
        if(node == null)
        {
            return;
        }

        // First record the stuff at bottom in the map, then
        //   record stuff in the top.
        PrintViews.printTopViewHelper(node.left, side - 1);
        PrintViews.printTopViewHelper(node.right, side + 1);
        PrintViews.view.put(side, node.data);
    }
}
