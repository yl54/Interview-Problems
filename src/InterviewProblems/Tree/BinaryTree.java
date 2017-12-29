package InterviewProblems.Tree;

// This class represents a binary search tree
//   and its operations. Assume that each data
//   node is distinct. Can always keep track
//   via a set.
// For each node, every value to its right is
//   greater than its own, while every value
//   to its left is less than its own.

import InterviewProblems.Utils.Nodes.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree
{
    // root is the root of the binary tree.
    public TreeNode root;

    public BinaryTree(int data)
    {
        this.root = new TreeNode(data);
    }

    // This function adds a node with data to the tree.
    public void addNode(int data)
    {
        this.root = this.addNode(this.root, data);
    }

    private TreeNode addNode(TreeNode node, int data)
    {
        // Check if node is null.
        if(node == null)
        {
            return new TreeNode(data);
        }

        // Check if data is greater than or less than
        //   the current node's data.
        if(node.data > data)
        {
            node.left = this.addNode(node.left, data);
        }
        else if(node.data < data)
        {
            node.right = this.addNode(node.right, data);
        }

        // Return node.
        return node;
    }

    // This function will delete a particular node
    //   from the tree, if it exists.
    public void deleteNode(int data)
    {
        this.root = deleteNode(this.root, data);
    }

    public TreeNode deleteNode(TreeNode node, int data)
    {
        // Check if node is null.
        if(node == null)
        {
            return null;
        }

        if(node.data > data)
        {
            node.left = this.deleteNode(node.left, data);
        }
        else if(node.data < data)
        {
            node.right = this.deleteNode(node.right, data);
        }
        else
        {
            // Check how many children are attached to this node.
            if(node.left != null && node.right != null)
            {
                // Find the successor inorder node. The inorder
                //   node is the node with the minimum value on
                //   right subtree.
                node.data = this.findMinValue(node.right);
                node.right = this.deleteNode(node.right, node.data);
            }
            else if(node.left != null)
            {
                node = node.left;
            }
            else if(node.right != null)
            {
                node = node.right;
            }
            else
            {
                node = null;
            }
        }

        // Return node.
        return node;
    }

    // Find the inorder successor of the provided node. The
    //   inorder successor also happens to be the minimum value
    //   in the right subtree of the node.
    // Assume node is not equal to null at this point.
    public int findMinValue(TreeNode node)
    {
        int min_value = node.data;

        // Check if data is greater than or less than node.
        // while(node.left != null) and min_value = node.left.data
        //   is acceptable as well.
        while(node != null)
        {
            min_value = node.data;
            node = node.left;
        }

        // Return id.
        return min_value;

    }

    // This function deals with checking whether or
    //   not a binary tree is a full tree or not.
    // A full tree is one where every node has zero or
    //   two nodes attached to it.
    public boolean isFullTree()
    {
        // Check if root is null.
        if(this.root == null)
        {
            return true;
        }
        // Loop through nodes via a queue. Check to see if
        //   there are 0, 1, or 2 nodes attached.
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(this.root);
        while(!nodes.isEmpty())
        {
            TreeNode nd = nodes.remove();
            boolean l_is_null = nd.left == null;
            boolean r_is_null = nd.right == null;
            // Check if both are not null.
            if(!l_is_null && !r_is_null)
            {
                nodes.add(nd.left);
                nodes.add(nd.right);
            }
            // Check if one is not null.
            else if(!l_is_null || !r_is_null)
            {
                return false;
            }

            // Continue if nothing to add.
        }

        // Return true since it was not invalidated anywhere.
        return true;
    }
}