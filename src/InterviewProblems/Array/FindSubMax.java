package InterviewProblems.Array;

// This problem deals with finding the continuous subarray
//   that contains the max sum.
public class FindSubMax {
    // This will store the results for the max subarrays
    //   for each i - j index.
    public static Info[][] results;

    // This function checks to see if an array contains
    //   a continuous subarray that adds up to a given
    //   number. This solution takes a recursive approach.
    public void isSubSumRec(int[] arr)
    {
        // Check if arr is empty or null.
        if (arr == null || arr.length == 0) {
            return;
        }

        // Initialize the traversed array. It will tell one
        //   if the array has been checked from i to j index
        //   in the [i][j] index.
        results = new Info[arr.length][arr.length];

        // Check what the initial sum of the array is.
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        // Do recursion on the array.
        Info r = isSubSumRecHelper(arr, sum, 0, arr.length);

        // Print out the result.
        System.out.println("Max sum: " + r.data + ".");
        System.out.println("Begin index: " + r.b_index + ".");
        System.out.println("End index: " + r.e_index + ".");
    }

    public Info isSubSumRecHelper
            (int[] arr, int sum, int b, int e) {
        // Check if b > e. This is the base condition.
        if (b > e) {
            return null;
        }

        // Check if this subarray has already been searched.
        if (results[b][e] != null) {
            return results[b][e];
        }

        // Compare and gather sums from results of subarrays.
        // Check to see the array excluding the first and last variables.
        Info result_info = new Info(sum, b, e);
        Info r_info = isSubSumRecHelper(arr, sum - arr[b], b + 1, e);
        Info l_info = isSubSumRecHelper(arr, sum - arr[e], b, e - 1);

        if (r_info != null && r_info.data > result_info.data) {
            result_info = r_info;
        }

        if (l_info != null && l_info.data > result_info.data) {
            result_info = l_info;
        }

        // Store this Info object in the [b][e] index.
        results[b][e] = result_info;
        return results[b][e];
    }

    // This object will hold data pertaining to the maximum
    //   subarray for a particular array.
    public class Info {
        public int data;
        public int b_index;
        public int e_index;

        public Info(int data, int b, int e) {
            this.data = data;
            this.b_index = b;
            this.e_index = e;
        }
    }

}
