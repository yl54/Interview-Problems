package InterviewProblems.Array;

public class MinPathArr
{
    /*
     * Test Cases
     * - null
     * - three elements
     * - one line vertical/horizontal
     * - 3 x 3
     * - has vertical/horizontal path
     *
     **/
    public static void main(String[] args)
    {
        int[][] genericCase = MinPathArr.makeGenericCase();
        int min = MinPathArr.findMinPath(genericCase);
        System.out.println(min + ".");
    }

    public static int[][] makeGenericCase()
    {
        int[][] cases = new int[3][3];

        cases[0][0] = 1;
        cases[0][1] = 2;
        cases[0][2] = 3;

        cases[1][0] = 4;
        cases[1][1] = 8;
        cases[1][2] = 2;

        cases[2][0] = 1;
        cases[2][1] = 5;
        cases[2][2] = 3;

        return cases;
    }

    // Pure recursion, no dynamic programming to store results.
    // Pre: arr is the array to analyze.
    // Post: returns the minimum cost path of arr.
    // Assume: all entries are positive integers.
    //         arr is m x n array.
    public static int findMinPath(int[][] arr)
    {
        // Check for null and empty case.
        if(arr == null || arr.length == 0 || arr[0].length == 0)
        {
            return 0;
        }

        // Do recursive method on minimum paths.
        return MinPathArr.findMinPathHelper(arr, arr.length - 1, arr[0].length - 1);
    }

    // Note: can make the paths from bottom to top or top to bottom.
    // Pre: arr is the 2d array of costs.
    //      i is the vertical index.
    //      j is the horizontal index.
    // Post: return the minimum cost.
    public static int findMinPathHelper(int[][] arr, int i, int j)
    {
        // Check if the array indicies are within bounds.
        if(i < 0 && j < 0)
        {
            return 0;
        }

        int cost = 0;

        // Check each valid path for a lowest cost with respect
        //   to conditions that remain inside.
        boolean in_i = i - 1 >= 0;
        boolean in_j = j - 1 >= 0;

        // Check if able to move north. If so, then
        //   compare number to argument.
        cost = MinPathArr.pickCost(arr, in_i, cost, i - 1, j);
        cost = MinPathArr.pickCost(arr, in_j, cost, i, j - 1);
        cost = MinPathArr.pickCost(arr, in_i && in_j, cost, i - 1, j - 1);

        System.out.println(i + ", " + j + ": " + cost);
        return cost + arr[i][j];
    }

    // Pre: arr is the array to analyze
    //      cond is the condition to check for on the next traversal
    //      cost is the current cost.
    //      i is the next i index to analyze.
    //      j is the next j index to analyze.
    public static int pickCost(int[][] arr, boolean cond, int cost, int i, int j)
    {
        if(cond)
        {
            int move = MinPathArr.findMinPathHelper(arr, i, j);
            cost = (cost > move || cost == 0)? move : cost;
        }

        return cost;
    }
}
