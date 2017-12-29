package InterviewProblems.Tree;

import InterviewProblems.Utils.Nodes.TreeNode;
import InterviewProblems.Utils.TreeUtils;
import InterviewProblems.Utils.Utils;

public class MaxSum {
    // NOTE: if you want to store results for this
    //         problem, so that you can print the
    //         max path, then will probably end up storing
    //         the path in some form.
    public static void main(String[] args) {
        TreeNode root = TreeUtils.makeTree();
        int max = MaxSum.maxSum(root);
        System.out.println("Max: " + max);
    }

    // This represents the maximum cost of
    //   the path that exists in.
    public static int max_overall;
    public static boolean set_init;

    public static int maxSum(TreeNode root) {
        // Check null/empty case.
        if(root == null) {
            return 0;
        }

        // Do recursion to search for the sum.
        int sum = MaxSum.maxSumHelper(root);
        return MaxSum.max_overall;
    }

    // This function will serve to get the
    //   maximum sum by going to the bottom
    //   of the tree and getting the results.
    public static int maxSumHelper(TreeNode node) {
        // Check if reached a dead end.
        if(node == null) {
            return 0;
        }

        // Check the left and right subtrees.
        int leftSum = MaxSum.maxSumHelper(node.left);
        int rightSum = MaxSum.maxSumHelper(node.right);

        // Calculate the numbers to compare.
        int sum = node.data;
        leftSum = node.data + leftSum;
        rightSum = node.data + rightSum;

        // Compare sums with the left and right sub trees
        //   to see which path is the max.
        int compSum = Utils.max(leftSum, rightSum);
        int nodeSum = Utils.max(compSum, sum);
        if (MaxSum.set_init) {
            MaxSum.max_overall = Utils.max(nodeSum, MaxSum.max_overall);
        } else {
            MaxSum.max_overall = nodeSum;
            MaxSum.set_init = true;
        }

        // Compare the sums between the current and the max
        //   of the two possible paths.
        return nodeSum;
    }
}
