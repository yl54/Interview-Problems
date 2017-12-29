package InterviewProblems.Array;

import java.util.ArrayList;
import java.util.LinkedList;

// This program deals with the problem of finding a pair
//    of integers that is closest for a given sum.
public class FindClosest
{
    // Another strategy is for each value, store its
    //   respective value that brings it closest to
    //   the desired sum, then pick the combination
    //   that provides the smallest difference.

    // Test cases: null array
    //             two value array
    //             three value array
    //             all values are below sum
    //             all values above sum
    //             array combo with 2 combos to sum

    // Pre: arr is an array that contains a sorted
    //        set of integers
    //      sum is an integer that we want to find
    //        the closest solution for.
    // Post: returns the two numbers that sum the
    //         closest to the given sum
    public static int[] findClosest(int[] arr, int sum)
    {
        // Check if the array is an invalid value.
        if(arr == null || arr.length == 0)
        {
            return null;
        }

        // Init beginning and end index, and the diff.
        int b = 0;
        int e = arr.length - 1;
        int diff = arr[e] - arr[b];

        int num_1 = b;
        int num_2 = e;

        // Note: Need to check if previous result is closer to
        //         the sum than the current result.
        // Loop through to find a pair of numbers that
        //   closest to the sum.
        while(b < e)
        {
            // If they are equal, then return those two numbers.
            int t_sum = arr[e] + arr[b];
            // Check if the numbers equal the sum.
            if(t_sum == sum)
            {
                return new int[]{arr[b], arr[e]};
            }

            // Check if the numbers are less than the current diff.
            int t_diff = Math.abs(t_sum - sum);
            if(t_diff < diff)
            {
                diff = t_diff;
                num_1 = b;
                num_2 = e;
            }
            // Check if this temp sum is greater than the sum.
            else if(t_sum > sum)
            {
                e--;
            }
            else
            {
                b++;
            }
        }

        // Return whats left.
        return new int[]{num_1, num_2};
    }
}
