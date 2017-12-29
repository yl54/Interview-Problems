package InterviewProblems.DynamicProgramming;

public class IsSubsetSum
{
    /*
     * Test Cases
     * - null
     * - 1 element array
     * - 2 element array
     * - a combination where 1 recursive call fails but
     *     another comination works, test to see if the
     *     track back works or not
     * - combination exists at the beginning/end
     *
     **/
    public static void main(String[] args)
    {
        // Need more cases.
        int[] nums = {4, 2, 5, 6, 7, 11};
        int sum = 16;

        // Check if this array works for the sum.
        boolean isSubsetSum = IsSubsetSum.isSubsetSumStored(nums, sum);
        System.out.println("It is " + isSubsetSum);
    }

    // This first iteration is a recursive solution that looks
    //   through the sums in tree-like fashion. It has an
    //   exponential runtime.
    // Pre: arr is the array filled with positive integers.
    //      sum is a positive integer.
    // Post: return true if sum
    public static boolean isSubsetSum(int[] arr, int sum)
    {
        // Check if arr or sum are null or empty.
        if(arr == null || sum < 0)
        {
            return false;
        }

        // Check to see if the sum exists.
        return IsSubsetSum.isSubsetSum(arr, sum, arr.length - 1);
    }

    public static boolean isSubsetSum(int[] arr, int sum, int index)
    {
        // Check if index is less than 0.
        if(index < 0)
        {
            return false;
        }

        // Check if the current value is equal to the sum.
        if(arr[index] == sum)
        {
            return true;
        }

        // Check if the current value is greater than the sum.
        // If so, ignore this current value.
        if(arr[index] > sum)
        {
            return IsSubsetSum.isSubsetSum(arr, sum, index - 1);
        }

        // Check if the sum exists either with or without
        //   this current value included.
        return IsSubsetSum.isSubsetSum(arr, sum, index - 1) ||
               IsSubsetSum.isSubsetSum(arr, sum - arr[index], index - 1);
    }

    // sumExists checks if the subarray ([i][j])
    //   i - arr.length contains the sum j.
    public static boolean[][] sumExists;

    // traversed checks to see if an subarray has been
    //   checked for a particular sum.
    public static boolean[][] traversed;

    // The alternative way to determine if the array
    //   contains a sum from the subset.
    public static boolean isSubsetSumStored(int[] arr, int sum)
    {
        // Check if arr or sum are null or empty or invalid.
        if(arr == null || arr.length == 0 || sum < 0)
        {
            return false;
        }

        // Initialize the boolean array for arr element
        //   amount and possible sums from 0 to sum.
        // Initialize to sum + 1 because this will allow
        //   easy access to the sum, wastes a little memory.
        IsSubsetSum.sumExists = new boolean[arr.length][sum + 1];
        IsSubsetSum.traversed = new boolean[arr.length][sum + 1];

        // Check to see if the sum exists.
        return IsSubsetSum.isSubsetSumStored(arr, sum, arr.length - 1);
    }

    public static boolean isSubsetSumStored(int[] arr, int sum, int index)
    {
        // Check if this particular combination has
        //   been traveled before.
        if(IsSubsetSum.traversed[index][sum])
        {
            return IsSubsetSum.sumExists[index][sum];
        }

        // Check if index is less than 0.
        if(index < 0)
        {
            return false;
        }

        // Check if the current value is equal to the sum.
        if(arr[index] == sum)
        {
            IsSubsetSum.sumExists[index][sum] = true;
        }
        // Check if the current value is greater than the sum.
        // If so, skip this number.
        else if(arr[index] > sum)
        {
            IsSubsetSum.sumExists[index][sum] =
                    IsSubsetSum.isSubsetSum(arr, sum, index - 1);
        }
        // Check if the sum exists either with or without
        //   the current value.
        else
        {
            // This is really saying sumExists[index - 1][sum] ||
            //                       sumExists[index - 1][sum - arr[index]]
            // In both cases, it checks adding a new element to the subarray.
            // For one case, it checks to see if the subarray adds to the
            //   current sum or if it adds up to the subtracted sum.
            IsSubsetSum.sumExists[index][sum] =
                    IsSubsetSum.isSubsetSum(arr, sum, index - 1) ||
                    IsSubsetSum.isSubsetSum(arr, sum - arr[index], index - 1);
        }

        IsSubsetSum.traversed[index][sum] = true;
        return IsSubsetSum.sumExists[index][sum];
    }
}
