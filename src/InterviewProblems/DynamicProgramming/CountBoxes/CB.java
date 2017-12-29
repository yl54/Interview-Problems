package InterviewProblems.DynamicProgramming.CountBoxes;

import InterviewProblems.Utils.Utils;

import java.util.ArrayList;
import java.util.Set;

// This problem deals with figuring out the largest
//   stack of boxes that can be made.
public class CB
{
    // This stores all of the combinations that have been found.
    public static ArrayList<ArrayList<Integer>> boxes_final;

    // This stores the current order of the boxes stacked,
    //   from largest to smallest.
    public static ArrayList<Integer> boxes_stacked_order;

    // This stores the boxes that have been ruled valid.
    public static Set<Integer> boxes_stacked_no_order;

    // This stores the boxes that have been passed over.
    public static Set<Integer> boxes_not_stacked;

    // This stores the current box dimensions for convenience.
    public static int[][] box_dims;

    // This funtion recursively solves the problem of
    //   the largest possible stack of boxes.
    // dims is the dimensions for the ith box. Assume that
    //   it is a n x 3 matrix if its not null or empty.
    public static int findLargestStackRec(int[][] dims)
    {
        // Check if the dimensions array is null/empty.
        if(Utils.isArrNullEmpty(dims))
        {
            return -1;
        }

        box_dims = dims;

        // Do for loop recursion.
        for(int i = 0; i < box_dims.length; i++)
        {
            boxes_stacked_order.add(i);
            boxes_stacked_no_order.add(i);
            executeAllCombos(i);
            boxes_stacked_order.remove(i);
            boxes_stacked_order.remove(i);
        }

        return 0;
    }

    public static void executeAllCombos(int i)
    {
        findLargestStackRecHelper(box_dims[i][0], box_dims[i][1]);
        findLargestStackRecHelper(box_dims[i][1], box_dims[i][2]);
        findLargestStackRecHelper(box_dims[i][0], box_dims[i][2]);
    }

    // This function recursively finds every combination
//   of boxes that can be stacked.
// If a combination exists, then immediately record
//   it somewhere.
// Maybe what i can pass in is the dimensions used for the
//   thext box that got put on top.
    public static void findLargestStackRecHelper(int m_1, int m_2)
    {
        // Check the status of the stacked/not stacked boxes.
        if(boxes_stacked_no_order.size() + boxes_not_stacked.size()
                >= box_dims.length)
        {
            // Add to complete set
            //ArrayList<Integer> box = Utils.copyArrayList();
            //boxes_final.add(box);
            return;
        }

        // Do a for loop across all the boxes.
        for(int i = 0; i < box_dims.length; i++)
        {
            // Check if this current box is already included/excluded.
            if(boxes_stacked_no_order.contains(i)  || boxes_not_stacked.contains(i))
            {
                continue;
            }

            // Very crude, start with something.
            // Check if this current box can fit above the other box.
            // To do this, check every combination of h/w/l fits
            //   if it fits, then try it and add to included
            //   after the recursive call, remove from both lists
            boolean found = false;

            int d_0 = box_dims[i][0];
            int d_1 = box_dims[i][1];
            int d_2 = box_dims[i][2];

            found = isIncluded(i, d_0, d_1, m_1, m_2);
            found = isIncluded(i, d_1, d_2, m_1, m_2);
            found = isIncluded(i, d_0, d_2, m_1, m_2);

            // else if no combination fits, then add to excluded list.
            if(!found)
            {
                boxes_not_stacked.add(i);
            }
        }
    }

    public static boolean isIncluded(int i, int d_1, int d_2, int m_1, int m_2)
    {
        if(isInRange(d_1, d_2, m_1, m_2))
        {
            boxes_stacked_order.add(i);
            boxes_stacked_no_order.add(i);
            findLargestStackRecHelper(d_1, d_2);
            boxes_stacked_order.remove(i);
            boxes_stacked_order.remove(i);
            return true;
        }

        return false;
    }

    // This function checks to see if one box fits on
    //   top of another box. The goal is to check if the
    //   provided surface will fit on top of the surface
    public static boolean isInRange(int d_1, int d_2,
                                    int m_1, int m_2)
    {
        boolean f_1 = d_1 <= m_1;
        boolean f_2 = d_1 <= m_2;
        boolean f_3 = d_2 <= m_1;
        boolean f_4 = d_2 <= m_2;
        return (f_1 && f_4) || (f_2 && f_3);
    }
}
