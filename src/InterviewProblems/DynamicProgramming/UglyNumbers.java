package InterviewProblems.DynamicProgramming;

import InterviewProblems.Utils.Utils;

import java.util.HashSet;

// This problem deals with the ugly numbers problem.
// An ugly number is one that only has prime factors
//   of 2, 3, and 5. The basic idea is that every
//   ugly number can be made from multiplying powers
//   of 2, 3, and 5.
public class UglyNumbers
{
    /*
     * Test Cases:
     * - Look for 2, 3, 5 as ugly numbers.
     * - Numbers of form 2^x * 3^y * 5*z
     * - Numbers of form 2^x * 3^y * 5*z * (7, 11, 13...)
     *   - Make sure these numbers are avoided.
     *
     * */
    public static void main(String[] args)
    {
//        UglyNumbers.execute(4);
        UglyNumbers.execute(10);
    }

    public static void execute(int num)
    {
        int n = UglyNumbers.getUNumRec(num);
        int o = UglyNumbers.getUNumBuild(num);
        System.out.println("n_" + num + ": " + n);
        System.out.println("o_" + num + ": " + o);
    }

    // This function deals with getting the nth ugly number.
    // This checks each one one by one and ends up doing a
    //   lot of the same calcualtion.
    public static int getUNumRec(int n)
    {
        // Check if n is valid.
        if(n < 1)
        {
            return -1;
        }

        int i = 2;
        int count = 0;

        // Continuously loop to get to nth number.
        while(count < n)
        {
            // Check if end division result is one.
            if(UglyNumbers.isUglyNum(i))
            {
                count++;
                System.out.println(i + " is the " + count + "th ugly number.");
            }

            i++;
        }

        // Return last number tried.
        return i - 1;
    }

    // This function checks to see if i is
    //   an ugly number.
    public static boolean isUglyNum(int i)
    {
        // Do continuous division by 2, 3, and 5.
        int div_2 = UglyNumbers.divide(2, i);
        int div_3 = UglyNumbers.divide(3, div_2);
        int div_5 = UglyNumbers.divide(5, div_3);
        return div_5 == 1;
    }

    // This function continuously divides start by d.
    public static int divide(int d, int start)
    {
        // Continuously check if start % d is 0.
        while(start % d == 0)
        {
            start /= d;
        }

        // Return number its not divisible by.
        return start;
    }


    // This function deals with getting the nth ugly number.
    // This checks each one one by one. This is an optimized
    //   version that records previously seen ugly numbers.
    //   The point is that many ugly numbers are built on top
    //   of each other.
    public static int getUNumRecOpt(int n)
    {
        // Check if n is valid.
        if(n < 1)
        {
            return -1;
        }

        HashSet<Integer> ugly_nums = new HashSet<Integer>();
        int i = 1;
        int count = 0;

        // Continuously loop to get to nth number.
        while(count < n)
        {
            // Check if end division result is one.
            if(UglyNumbers.isUglyNumOpt(i, ugly_nums))
            {
                System.out.println(i + " is the " + count + "th ugly number.");
                ugly_nums.add(i);
                count++;
            }

            i++;
        }

        // Return last number tried.
        return i - 1;
    }

    // This function checks to see if i is an ugly
    //   number, with reference to ugly_nums.
    public static boolean isUglyNumOpt(int i, HashSet<Integer> ugly_nums)
    {
        // Do continuous division by 2, 3, and 5.
        int div_2 = UglyNumbers.divideOpt(2, i, ugly_nums);
        int div_3 = UglyNumbers.divideOpt(3, div_2, ugly_nums);
        int div_5 = UglyNumbers.divideOpt(5, div_3, ugly_nums);
        return div_5 == 1;
    }

    // This function continuously divides start by d.
    public static int divideOpt(int d, int start,
                                HashSet<Integer> ugly_nums)
    {
        // Continuously check if start % d is 0.
        while(start % d == 0)
        {
            start /= d;
            // This will stop lots of previous computations from
            //   happenning again.
            if(ugly_nums.contains(d))
            {
                return 1;
            }
        }

        // Return number its not divisible by.
        return start;
    }

    // This function gets the nth ugly number by interatively
    //   multiplying numbers, since these numbers are just
    //   subsequences of each other.
    public static int getUNumBuild(int n)
    {
        // Check if n is a valid number.
        if(n < 1)
        {
            return -1;
        }

        int count = 0;

        // Initialize array with 1 to start the multiplication.
        int[] ugly_nums = new int[n + 1];
        ugly_nums[0] = 1;
        int i_2 = 0;
        int i_3 = 0;
        int i_5 = 0;

        int m_2 = 2 * ugly_nums[i_2];
        int m_3 = 3 * ugly_nums[i_3];
        int m_5 = 5 * ugly_nums[i_5];

        // Keep going until nth ugly number is found.
        while(count < n)
        {
            count++;
            int min = Utils.min(m_2, Utils.min(m_3, m_5));

            // Multiples may intersect, so need to increment all
            //   those that are applicable. Don't need to compare
            //   same elements again.
            if(min == m_2)
            {
                ugly_nums[count] = m_2;
                i_2++;
                m_2 = 2 * ugly_nums[i_2];
            }
            if(min == m_3)
            {
                ugly_nums[count] = m_3;
                i_3++;
                m_3 = 3 * ugly_nums[i_3];
            }
            if(min == m_5)
            {
                ugly_nums[count] = m_5;
                i_5++;
                m_5 = 5 * ugly_nums[i_5];
            }
        }

        // Return nth ugly number.
        return ugly_nums[n];
    }
}
