package InterviewProblems.DynamicProgramming;

// This problem deals with counting the potential
//   even length binary sequences where the sum of
//   the first and second half bits are the same.
public class CountEvenSeq
{
    /*
     * Test Cases:
     * - 0
     * - 1
     * - 2
     * - 3
     *
     * */
    public static void main(String[] args)
    {
        execute(1);
        execute(2);
        execute(3);
        execute(4);
    }

    public static void execute(int n)
    {
        int result = CountEvenSeq.countSeq(n);
        System.out.println(n + ": " + result);
    }

    public static int[][] seqAmt;

    public static int countSeq(int n)
    {
        // Check if n is a valid number.
        if(n < 1)
        {
            return -1;
        }

        // Initialize a n x n array to store previous
        //   computations of numbers less than n.
        CountEvenSeq.seqAmt = new int[n + 1][n + 1];

        // Base case is if n is 1.
        CountEvenSeq.seqAmt[1][1] = 2;

        // Do recursion to get the rest of the combinations.
        return CountEvenSeq.countSeq(n, n);
    }

    // This function recursively goes through the
    //   combinations and gets the amount for x and y.
    public static int countSeq(int x, int y)
    {
        // Check if x and y are out of bounds. This should not
        //   happen if it runs correctly.
        if(x < 1 || y < 1 || CountEvenSeq.seqAmt[x][y] != 0)
        {
            return CountEvenSeq.seqAmt[x][y];
        }

        // Do recursion based off of value.
        CountEvenSeq.seqAmt[x][y] = CountEvenSeq.countSeq(x - 1, y - 1) +
                                    CountEvenSeq.countSeq(x - 1, y) +
                                    CountEvenSeq.countSeq(x, y - 1);

        return CountEvenSeq.seqAmt[x][y];
    }
}
