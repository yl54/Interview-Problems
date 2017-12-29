package InterviewProblems.Array;

import InterviewProblems.Utils.Utils;

// This problem deals with finding the maximum
//   product of three numbers in an array,
//   without the option to sort the array.
public class MaxTriplet
{
    /*
     * Test Cases
     * - null
     * - three elements
     * - 5 elements, 2 negative and 3 positive
     *   - one set where negative > positive and one the other way
     * - 4 elements, 1 is negative
     *
     **/
    public static void main(String[] args)
    {
        int[] n = null;
        int[] empty = new int[0];
        int[] all_positive = {4, 5, 1, 8, 3, 9, 10};
        int[] neg = {3, 5, 6, -8, -10, 7};

        int n_max = MaxTriplet.calcMaxProduct(n);
        int empty_max = MaxTriplet.calcMaxProduct(empty);
        int all_positive_max = MaxTriplet.calcMaxProduct(all_positive);
        int neg_max = MaxTriplet.calcMaxProduct(neg);

        System.out.println("Null: " + n_max);
        System.out.println("empty_max: " + empty_max);
        System.out.println("all_positive_max: " + all_positive_max);
        System.out.println("neg_max: " + neg_max);
    }

    public static int calcMaxProduct(int[] arr)
    {
        return MaxTriplet.maxProductTrack(arr);
    }

    // Pre: arr is an array filled with integers.
    // Post: return the integer that has the largest product.
    // This implementation will check the maximum and minimum
    //   numbers for each index to its left and right.
    // Versatile in every situation.
    // If top is 0, then rest of numbers >= 0, so 0 is
    //   the maximum product.
    // If top is positive and second is 0, then the two
    //   negatives and positive may be bigger.
    // If every integer is negative, then the three "largest"
    //   integers will form the smallest negative number.
    public static int maxProductLeftRight(int[] arr) {
        // Check if arr is null or less than 3 values long.
        if (arr == null || arr.length < 3) {
            return 0;
        }

        // Look for the max value to left and right for each value.
        int l = arr.length;
        int[] left_max_arr = new int[l];
        int[] left_min_arr = new int[l];
        int[] right_max_arr = new int[l];
        int[] right_min_arr = new int[l];

        int left_max = arr[0];
        int left_min = arr[0];
        for (int i = 1; i < l; i++)
        {
            left_max_arr[i] = left_max;
            if (left_max < arr[i])
            {
                left_max = arr[i];
            }

            left_min_arr[i] = left_min;
            if (left_min > arr[i])
            {
                left_min = arr[i];
            }
        }

        int right_max = arr[l - 1];
        int right_min = arr[l - 1];
        for (int i = l - 2; i >= 0; i--)
        {
            right_max_arr[i] = right_max;
            if (right_max < arr[i])
            {
                right_max = arr[i];
            }

            right_min_arr[i] = right_min;
            if (right_min > arr[i])
            {
                right_min = arr[i];
            }
        }

        // Loop through arr and get the max product from the
        //   possible combinations of these.
        int max = arr[1] * left_max_arr[1] * right_max_arr[1];
        for (int i = 2; i < l; i++) {
            int comp_1 = arr[i] * left_max_arr[i] * right_max_arr[i];
            int comp_2 = arr[i] * left_max_arr[i] * right_min_arr[i];
            int comp_3 = arr[i] * left_min_arr[i] * right_max_arr[i];
            int comp_4 = arr[i] * left_min_arr[i] * right_min_arr[i];
            int max_comp = Utils.max(Utils.max(comp_1, comp_2), Utils.max(comp_3, comp_4));
            max = Utils.max(max_comp, max);
        }

        // Return largest product.
        return max;
    }

    // This solution deals with keeping track of the
    //   three max products and two smallest.
    public static int maxProductTrack(int[] arr)
    {
        // Check if arr is null or less than 3 variables.
        if(arr == null || arr.length < 3)
        {
            return 0;
        }

        // Init the first, second, third biggest.
        int max_1 = arr[0];
        int max_2 = arr[0];
        int max_3 = arr[0];

        // Init the first, second smallest.
        int small_1 = arr[0];
        int small_2 = arr[0];

        // Loop through array to check if there are bigger/smaller
        //   numbers and adjust hierarchy accordingly.
        for(int i = 1; i < arr.length; i++)
        {
            // Check if larger than max.
            if(arr[i] > max_1)
            {
                max_3 = max_2;
                max_2 = max_1;
                max_1 = arr[i];
            }

            // Check if smaller than min.
            if(arr[i] < small_1)
            {
                small_2 = small_1;
                small_1 = arr[i];
            }
        }

        // Return the max of three max or two small * max
        int three_max = max_1 * max_2 * max_3;
        int two_small_one_max = small_2 * small_1 * max_1;
        return Utils.max(three_max, two_small_one_max);
    }
}
