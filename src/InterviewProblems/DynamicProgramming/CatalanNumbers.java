package InterviewProblems.DynamicProgramming;

// This problem deals with getting the nth catalan number.
public class CatalanNumbers
{
    // Test Cases:
    // - Do every number: 0, 1, 2, 3...
    public static void main(String[] args)
    {
        for(int i = 0; i < 20; i++)
        {
            CatalanNumbers.execute(i);
        }
    }

    public static void execute(int n)
    {
        int result = CatalanNumbers.getCatalanNumRecStore(n);
        System.out.println(n + ": " + result);
    }

    // This is a linear solution to getting the nth catalan number.
    // DISCLAIMER: Not perfect results due to nature of java computing
    //               doubles and integers.
    public static int getCatalanNumLinear(int n)
    {
        // Check if n is a valid number.
        if(n < 0)
        {
            return -1;
        }

        // Check if n is 0 or 1.
        if(n == 0 || n == 1)
        {
            return 1;
        }

//        System.out.println("n: " + n);

        // Just do linear solution first.
        double result = 1;
        for(int i = 2; i <= n; i++)
        {
            double temp = (double) (n + i)/i;
            result *= temp;
//            System.out.println("n + i: " + (n + i));
//            System.out.println("i: " + i);
//            System.out.println("temp: " + temp);
//            System.out.println("result: " + result);
        }

        return (int) result;
    }

    // This is the recursive solution. The algorithm consists of
    //   adding up the results of C(i) * C(n - 1 - i).
    public static int getCatalanNumRec(int n)
    {
        // Check if the n is a valid number.
        if(n < 0)
        {
            return -1;
        }

        // Do recursion.
        return CatalanNumbers.getCatalanNumRecHelper(n);
    }

    public static int getCatalanNumRecHelper(int n)
    {
        // Check if the n is 0 or 1.
        if(n == 0 || n == 1)
        {
            return 1;
        }

        // Do recursion for addition.
        int result = 0;
        for(int i = 0; i < n; i++)
        {
            result += CatalanNumbers.getCatalanNumRecHelper(i) *
                      CatalanNumbers.getCatalanNumRecHelper(n - 1 - i);
        }

        return result;
    }


    public static int[] cat_store;

    // This is the recursive solution that stores stuff.
    public static int getCatalanNumRecStore(int n)
    {
        // Check if the n is a valid number.
        if(n < 0)
        {
            return -1;
        }

        if(n == 0 || n == 1)
        {
            return 1;
        }

        CatalanNumbers.cat_store = new int[n + 1];
        CatalanNumbers.cat_store[0] = 1;
        CatalanNumbers.cat_store[1] = 1;

        // Do recursion.
        return CatalanNumbers.getCatalanNumRecHelperStore(n);
    }

    public static int getCatalanNumRecHelperStore(int n)
    {
        // Check if the n has already been recorded.
        if(CatalanNumbers.cat_store[n] != 0)
        {
            return CatalanNumbers.cat_store[n];
        }

        // Do recursion for addition.
        int result = 0;
        for(int i = 0; i < n; i++)
        {
            CatalanNumbers.cat_store[i] = CatalanNumbers.getCatalanNumRecHelperStore(i);
            CatalanNumbers.cat_store[n - 1 - i] = CatalanNumbers.getCatalanNumRecHelperStore(n - 1 - i);
            result += CatalanNumbers.cat_store[i] *
                      CatalanNumbers.cat_store[n - 1 - i];
        }

        CatalanNumbers.cat_store[n] = result;
        return CatalanNumbers.cat_store[n];
    }
}
