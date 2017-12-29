package InterviewProblems.DynamicProgramming;

import InterviewProblems.Utils.Utils;

public class CutRod
{
    /*
     * Test Cases:
     * - invalid prices/n
     * - 1 price, length 1
     * - length 2, price of length 1 is half of length 2
     * - length 2, price of length 2 is half of length 1
     *
     * */
    public static void main(String[] args)
    {
        int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20};
        CutRod.execute(8, prices);
    }

    public static void execute(int n, int[] prices)
    {
        int result = CutRod.maxValueStore(n, prices);
        System.out.println("n: " + n);
        System.out.print("prices: ");
        Utils.printArray(prices);
        System.out.println("result: " + result);
        System.out.println();
    }

    // This function takes a recursive approach to
    //   getting the max value from cutting a rod up.
    public static int maxValue(int n, int[] prices)
    {
        // Check if n and prices are valid.
        if (n < 0 || prices == null)
        {
            return -1;
        }

        // Do recursion to find the maximum value.
        return CutRod.maxValueHelper(n, prices, 0);
    }

    public static int maxValueHelper
            (int n, int[] prices, int amt)
    {
        // Check if n is 0.
        if(n <= 0)
        {
            return amt;
        }

        // Loop through and record result each time.
        int max = amt;
        for(int i = 1; i <= n; i++)
        {
            int temp = CutRod.maxValueHelper(n - i, prices, amt + prices[i]);
            max = (temp > max)? temp : max;
        }

        return max;
    }

    public static int[] max_amt;

    // This function solves the problem of the max
    //   value obtainable by cutting the rod by storing
    //   the values as you go.
    public static int maxValueStore(int n, int[] prices)
    {
        // Check if n and prices are valid.
        if (n < 0 || prices == null)
        {
            return -1;
        }

        CutRod.max_amt = new int[n + 1];

        // Do recursion to find the maximum value.
        return CutRod.maxValueHelper(n, prices, 0);
    }

    public static int maxValueStoreHelper
            (int n, int[] prices, int amt)
    {
        // Check if n is 0.
        if(n <= 0)
        {
            return amt;
        }

        if(CutRod.max_amt[n] != 0)
        {
            return CutRod.max_amt[n];
        }

        // Loop through and record result each time.
        int max = amt;
        for(int i = 1; i <= n; i++)
        {
            CutRod.max_amt[n - i] = CutRod.maxValueHelper(n - i, prices, amt + prices[i]);
            max = (CutRod.max_amt[n - i] > max)? CutRod.max_amt[n - i] : max;
        }

        CutRod.max_amt[n] = max;
        return CutRod.max_amt[n];
    }
}
