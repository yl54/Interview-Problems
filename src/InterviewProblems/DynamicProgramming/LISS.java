package InterviewProblems.DynamicProgramming;

// This class serves as a main to house the
//   largest increasing subsequence problem.
public class LISS
{
    public static int[] MAX_PER_INDEX;

    public static void main(String[] args)
    {
        int[][] cases = makeCases();
        for(int[] c: cases)
        {
            int amt = LISS.lISSStored(c);
            //LISS.printResults(c, amt);
        }
    }

    public static int[][] makeCases()
    {
        int[][] cases = new int[5][];

        // null case
        cases[0] = null;

        // empty case
        cases[1] = new int[0];

        // single value case.
        cases[2] = new int[1];
        cases[2][0] = 2;

        // multiple int case.
        cases[3] = new int[5];
        cases[3][0] = 4;
        cases[3][1] = 14;
        cases[3][2] = 5;
        cases[3][3] = 13;
        cases[3][4] = 8;

        // no increasing sequences
        cases[4] = new int[3];
        cases[4][0] = 4;
        cases[4][1] = 3;
        cases[4][2] = 2;

        return cases;
    }

    public static int overallMax;

    // Longest increasing subsequence.
    public static int lISS(int[] arr)
    {
        if(arr == null || arr.length == 0)
        {
            return 0;
        }

        int t = LISS.lISSHelper(arr, arr.length - 1);
        //printResults(arr, t);
        return LISS.overallMax;
    }

    // Do some recursion.
    public static int lISSHelper(int[] arr, int index)
    {
        // Check if index < 0. Just in case.
        if(index < 0)
        {
            return 0;
        }

        // Index is 0 is base case, as no matter what, the first
        //   element will always be its own longest subsequence.
        int max_amt = 1;

        // Loop through to check for the max amt.
        // There is probably a way to traverse this array
        //   to get the exact sequence of max amt.
        for(int i = 0; i < index; i++)
        {
            int comp = LISS.lISSHelper(arr, i);
            // Check if the int that appears afterwards is greater
            //   than the previous and check the amount in its
            //   subsequence is greater than the current one.
            if(arr[index] > arr[i] && comp + 1 > max_amt)
            {
                max_amt = comp + 1;
            }
        }

        if(max_amt > LISS.overallMax)
        {
            LISS.overallMax = max_amt;
        }

        // return the largest result.
        return max_amt;
    }

    public static int lISSStored(int[] arr)
    {
        if(arr == null || arr.length == 0)
        {
            return 0;
        }

        LISS.MAX_PER_INDEX = new int[arr.length];
        int t = LISS.lISSStoredHelper(arr, arr.length - 1);
        printResults(LISS.MAX_PER_INDEX, t);
        return LISS.overallMax;
    }

    // Do some recursion with some saved states to save computation time.
    public static int lISSStoredHelper(int[] arr, int index)
    {
        // Check if index < 0. Just in case.
        if(index < 0)
        {
            return 0;
        }

        // Index is 0 is base case, as no matter what, the first
        //   element will always be its own longest subsequence.
        int max_amt = 1;

        // Loop through to check for the max amt.
        // There is probably a way to traverse this array
        //   to get the exact sequence of max amt.
        for(int i = 0; i < index; i++)
        {
            // Check the value in the array, and either traverse
            //   or use precomputed value.
            int check = LISS.MAX_PER_INDEX[i];
            int comp = (check != 0)? check : LISS.lISSStoredHelper(arr, i);

            // Check if the int that appears afterwards is greater than the
            //   previous and check the amount in its subsequence is greater
            //   than the current one. For this variation of the problem,
            //   this is ok because the subsequence doesn't have to be exactly
            //   adjacent.
            if(arr[index] > arr[i] && comp + 1 > max_amt)
            {
                max_amt = comp + 1;
            }
        }

        LISS.MAX_PER_INDEX[index] = max_amt;
        if(max_amt > LISS.overallMax)
        {
            LISS.overallMax = max_amt;
        }

        // return the largest result.
        return max_amt;
    }

    public static void printResults(int[] c, int amt)
    {
        if(c == null)
        {
            return;
        }
        System.out.print("|");
        for(int i = 0; i < c.length; i++)
        {
            System.out.print(c[i] + "|");
        }
        System.out.println(" : " + amt + ".");
    }
}
