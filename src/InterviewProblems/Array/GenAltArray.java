package InterviewProblems.Array;

import InterviewProblems.Utils.Nodes.LLAdjNode;
import InterviewProblems.Utils.Utils;
import java.util.ArrayList;

// This problem deals with generating all possible
//   sorted arrays from alternating from two arrays
//   that are already sorted.
// Just do the shitty solution first.
public class GenAltArray
{
    // Test cases:
    public static void main(String[] args)
    {
        int[] a = {10, 20, 30};
        int[] b = {15, 25, 35};

        getAltArraysStored(a, b);
    }

    public static ArrayList<Integer> current_state;

    // This function gets the various arrays in
    //   alternate sorted fashion.
    // Pre: arr_1 and arr_2 are sorted arrays.
    public static void getAltArrays(int[] arr_1, int[] arr_2)
    {
        // Check if arr_1/arr_2 is null.
        if(arr_1 == null || arr_2 == null)
        {
            return;
        }

        current_state = new ArrayList<Integer>();

        // Gather sorted combinations starting from arr_1.
        getAltArrays(arr_1, arr_2, 0, 0);
        System.out.println("Finished arr_1 to arr_2");
        // Gather sorted combinations starting from arr_2.
        getAltArrays(arr_2, arr_1, 0, 0);
        System.out.println("Finished arr_2 to arr_1");
    }

    // This function recursively goes and finds the valid
    //   alternate arrays.
    // Pre: arr_src is the source array, the one currently
    //        in queue for use
    //      arr_dest is the destination array to look for
    //      i_src is the index it is at for the src array.
    //      i_dest is the index it is at for the dest array.
    public static void getAltArrays(int[] arr_src, int[] arr_dest,
                                    int i_src, int i_dest)
    {

        // Check if indicies are below/above bounds.
        if(i_src < 0 || i_dest < 0 || i_src >= arr_src.length || i_dest > arr_dest.length)
        {
            return;
        }

        current_state.add(arr_src[i_src]);

        // Loop through array to find the next possibility.
        int i_curr = i_dest;
        while(i_curr < arr_dest.length)
        {
            if(arr_src[i_src] < arr_dest[i_curr])
            {
                break;
            }
            i_curr++;
        }

        // Check if no next possibility exists.
        if(i_curr == arr_dest.length)
        {
            Utils.printArrayList(current_state);
            current_state.remove(current_state.size() - 1);
            return;
        }

        for(int i = i_curr; i < arr_dest.length; i++)
        {
//            current_state.add(arr_dest[i]);
            // Do i_src + 1, can omit the current index
            getAltArrays(arr_dest, arr_src, i, i_src + 1);
//            current_state.remove(current_state.size() - 1);
//            System.out.println("i_src: " + i_src);
//            System.out.println("i_dest: " + i_dest);
//            System.out.println();
        }

        current_state.remove(current_state.size() - 1);
    }

    // These two arrays store traversals of alternating
    //   arrays. At the end, either filled with a node of
    //   -1, which means nothing exists, or filled with
    //   nodes that represent traversals.
    public static LLAdjNode[] arr_1_nodes;
    public static LLAdjNode[] arr_2_nodes;

    public static int[] arr_1_i;
    public static int[] arr_2_i;

    // These arrays store the initial values.
    public static int[] arr_1;
    public static int[] arr_2;

    // This process will use the solution that stores results
    //   along the way to get the combinations of alternating arrays.
    public static void getAltArraysStored(int[] arr_1, int[] arr_2)
    {
        GenAltArray.arr_1_nodes = new LLAdjNode[arr_1.length];
        GenAltArray.arr_2_nodes = new LLAdjNode[arr_2.length];
        GenAltArray.arr_1_i = GenAltArray.findNextIndex(arr_1, arr_2);
        GenAltArray.arr_2_i = GenAltArray.findNextIndex(arr_2, arr_1);
        GenAltArray.arr_1 = arr_1;
        GenAltArray.arr_2 = arr_2;

        prepareNodes(0, 1);
        prepareNodes(0, 2);

        GenAltArray.arr_1_nodes[0].printNode();
    }


    // Tip: Easier to use recurusion and store stuff this way.
    // This is a recursive version of this solution that stores
    //   results from looking through.
    // Pre: src_i stores the next greatest index in the src array.
    //      dest_i stores the next greatest index in dest array.
    // Reasoning: These two are good params because their roles
    //              need to be frequently flipped.
    // May need to return LLAdjNodes for this to work
    // Q: What if I just return a large node and just get every
    //      combination from that, with a traversed array instead?
    // A: No because still need references to certain parts
    //      of the array.
    public static LLAdjNode prepareNodes(int src_i, int src_arr_num)
    {
        // Check which turn it is.
        if(src_arr_num == 1)
        {
            GenAltArray.arr_1_nodes[src_i] =
                    GenAltArray.prepareNodesHelper(GenAltArray.arr_1_nodes, GenAltArray.arr_2_nodes,
                                                   GenAltArray.arr_1_i, GenAltArray.arr_2_i,
                                                   GenAltArray.arr_1, GenAltArray.arr_2,
                                                   src_i, 2);
            return GenAltArray.arr_1_nodes[src_i];
        }
        else
        {
            GenAltArray.arr_2_nodes[src_i] =
                    GenAltArray.prepareNodesHelper(GenAltArray.arr_2_nodes, GenAltArray.arr_1_nodes,
                                                   GenAltArray.arr_2_i, GenAltArray.arr_1_i,
                                                   GenAltArray.arr_1, GenAltArray.arr_2,
                                                   src_i, 1);
            return GenAltArray.arr_2_nodes[src_i];
        }
    }

    // Helper function to traverse array.
    public static LLAdjNode prepareNodesHelper(LLAdjNode[] src_nodes, LLAdjNode[] dest_nodes,
                                               int[] src_arr_i, int[] dest_arr_i,
                                               int[] src_arr, int[] dest_arr, int src_i,
                                               int dest_num)
    {
        // Check if src_i has already been traveled through.
        if(src_nodes[src_i] != null)
        {
            return src_nodes[src_i];
        }

        int dest_i = src_arr_i[src_i];
        LLAdjNode base = new LLAdjNode(-1);
        LLAdjNode current = base;

        // Check if there is a destination to go to or not.
        if(dest_i == -1)
        {
            return base;
        }

        // For each one it can travel to.
        for(int j = dest_i; j < dest_arr_i.length; j++)
        {
            LLAdjNode dest_node = prepareNodes(j, dest_num);

            // Check if this node is -1, or if there is a path.
            if(dest_node.data == -1)
            {
                current.next = new LLAdjNode(dest_arr[j]);
                current = current.next;
            }
            else
            {
                LLAdjNode dest_current = dest_node;
                while(dest_current != null)
                {
                    current.next = new LLAdjNode(dest_arr[j]);
                    current.next.adj = dest_current;
                    current = current.next;
                    dest_current = dest_current.next;
                }
            }
        }

        // Return the result.
        base = base.next;
        return base;
    }

    public static int[] findNextIndex(int[] arr_src, int[] arr_dest)
    {
        // Initialize variables for src index and dest index.
        int i_src = 0;
        int i_dest = 0;
        int[] src_ind = new int[arr_src.length];

        // Loop through one by one to look for greatest next index.
        while(i_src < arr_src.length && i_dest < arr_dest.length)
        {
            if(arr_src[i_src] < arr_dest[i_dest])
            {
                src_ind[i_src] = i_dest;
                i_src++;
            }
            else
            {
                i_dest++;
            }
        }

        // Loop through remainders and set them.
        while(i_src < arr_src.length)
        {
            src_ind[i_src] = -1;
            i_src++;
        }

        // Return the index array.
        return src_ind;
    }
}

