package InterviewProblems.Array;

// This problem deals with figuring out the
//   smallest integer that cannot be formed
//   from any subset in a sorted array.
public class FindSmallest
{
    /*
     * Test Cases
     * - null
     * - one value (one and greater than one)
     * - no differences between values
     * - differences of one between values
     * - differences of more than one between values
     *
     **/
    public static void main(String[] args)
    {
        int[] n = null;
        int[] full_increase = {1, 2, 3, 4, 5};
        int[] gap_increase = {1, 2, 3, 8, 9};
        int[] no_one = {2, 3};

        FindSmallest.execute("null", n);
        FindSmallest.execute("full_increase", full_increase);
        FindSmallest.execute("gap_increase", gap_increase);
        FindSmallest.execute("no_one", no_one);
    }

    public static void execute(String name, int[] arr)
    {
        int result = FindSmallest.findSmall(arr);
        System.out.println(name + " : " + result);
    }

    // Pre: arr is a sorted, ascending array of
    //        positive integers
    // Post: returns the smallest integer that
    //         cannot be created by a subset.
    public static int findSmall(int[] arr)
    {
        // Check if arr is null or empty.
        if(arr == null || arr.length == 0)
        {
            return 0;
        }

        // Init the smallest positive integer.
        int result = 1;

        // Loop through to check for next possible results.
        // The goal is to either find a gap in the numbers
        //   that cannot be formed from the provided array
        //   or figure out what the number that's just larger
        //   than the sums that the array can hold.
        for(int i = 0; i < arr.length; i++)
        {
            result += (result >= arr[i])? arr[i] : 0;
        }

        // Return smallest integer.
        return result;
    }
}
