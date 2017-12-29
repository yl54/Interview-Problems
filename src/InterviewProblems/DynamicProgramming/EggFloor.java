package InterviewProblems.DynamicProgramming;

import InterviewProblems.Utils.Utils;

// This problem deals with the egg drop problem. The
//   goal of this program isn't necessarily to figure
//   out the breaking point, but to find the least
//   number of egg drops that's guaranteed to work.
//   Essentially, its finding the least worst case scenario.
public class EggFloor
{
    /*
     * Test Cases
     * - both 0
     * - 1 egg scenarios
     * - multiple egg scenarios
     * - more eggs than stories
     *
     **/
    public static void main(String[] args)
    {
//        EggFloor.execute(2, 10);
        EggFloor.execute(2, 36);
    }

    public static void execute(int n, int k)
    {
        int result = EggFloor.findFloorStored(n, k);
        System.out.println("n: " + n);
        System.out.println("k: " + k);
        System.out.println("result: " + result);
        System.out.println();
    }

    // Summary: This code deals with the recursive solution to
    //            this problem. The basic idea to this algorithm
    //            is that you want to make the traversal to every
    //            floor be the same or less than a certain max, so
    //            you have to spread your eggs out, in descending
    //            linear fashion.
    // Pre: n is the number of eggs given.
    //      k is the number of stories to measure.
    public static int findFloorRec(int n, int k)
    {
        // Check if n or k is invalid.
        if(n < 1 || k < 1)
        {
            return -1;
        }

        // Do recursion.
        return EggFloor.findFloorRecHelper(n, k);
    }

    public static int findFloorRecHelper(int n, int k)
    {
        // Check if k is at the bottom of the building.
        if(k < 2)
        {
            return k;
        }

        // Check if there is only one egg left.
        // If so, then have to drop from 1 -> kth floor.
        //   This leads to worst case scenario of k drops.
        if(n == 1)
        {
            return k;
        }

        // Find out what the minimum worst case amount is.
        int min = Integer.MAX_VALUE;

        // Loop from the top.
        // Two scenarios can happen:
        // - egg broke on current story, so lose one
        //     egg and check the stories starting from below .
        // - egg did not break for a few stories, so can
        //     skip them and preserve eggs.
        // Goal: Get worst case from these two.
        //       See if this worst case is better than other
        //         accrued worst case scenarios.
        //
        // Pre: e_b_t = eggs broke true
        //      e_b_f = eggs broke false
        //      i is two things.
        //      - for the true case, it serves as the point from
        //          the bottom that the egg breaks
        //      - for the false case, it serves as the number of
        //          steps that it can skip from the top.
        for(int i = 1; i <= k; i++)
        {
            int e_b_t = EggFloor.findFloorRecHelper(n - 1, i - 1);
            int e_b_f = EggFloor.findFloorRecHelper(n, k - i);
            int worst_case = Utils.max(e_b_t, e_b_f);
            min = Utils.min(min, worst_case);
        }

        // Q: Why do i need to add 1 here?
        return min + 1;
    }

    // This will help to store the previous results
    //   that appear. [i][j] will store the best worst
    //   case number of drops with i eggs and j floors.
    public static int[][] worst_case_drops;

    // This solution helps to find the best worst case
    //   amount of drops that can be used with the help
    //   of storing previous results.
    public static int findFloorStored(int n, int k)
    {
        // Check if n or k is an invlaid integer.
        if(n < 1 || k < 1)
        {
            return -1;
        }

        // Initialize worst_case_drops
        // - some values can already be easily initialized
        //     such as scenarios with one egg or only one
        //     floor to test.
        EggFloor.worst_case_drops = new int[n + 1][k + 1];
        // Fill in one floor/variable eggs case.
        for(int i = 1; i <= n; i++)
        {
            EggFloor.worst_case_drops[i][1] = 1;
        }
        // Fill in one egg/variable floors case.
        for(int i = 1; i <= k; i++)
        {
            EggFloor.worst_case_drops[1][i] = i;
        }

        // Do recursion to find out value.
        // Extra: build up to result with for loops, since
        //          stuff is built up from bottom anyways
        int result = EggFloor.findFloorStoredFor(n, k);
        return result;
    }

    public static int findFloorStoredRec(int n, int k)
    {
        // Check if [n][k] value already exists.
        if(EggFloor.worst_case_drops[n][k] != 0 || n < 2 || k < 2)
        {
            return EggFloor.worst_case_drops[n][k];
        }

        // Do recursion to check for combinations of n and k.
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= k; i++)
        {
            int e_b_t = EggFloor.findFloorStoredRec(n - 1, i - 1);
            int e_b_f = EggFloor.findFloorStoredRec(n, k - i);
            int worst_case = Utils.max(e_b_t, e_b_f);
            min = Utils.min(min, worst_case);
        }

        // Save and return the best worst case scenario from the group.
        // Always need to add one to account for the current story.
        EggFloor.worst_case_drops[n][k] = min + 1;
        return EggFloor.worst_case_drops[n][k];
    }

    // This function builds the results from bottom up,
    //   since everything is built on top of results
    //   already calculated.
    public static int findFloorStoredFor(int n, int k) {
        // Double for loop (n x k) from the bottom.
        // Need to build up the stories dimension before
        //   worrying about a variable amount of eggs.
        // i = number of eggs
        // j = which story its at
        // l = index to best value of stories below current story
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                EggFloor.worst_case_drops[i][j] = Integer.MAX_VALUE;
                for (int l = 1; l <= j; l++) {
                    int e_b_t = EggFloor.worst_case_drops[i - 1][l - 1] + 1;
                    int e_b_f = EggFloor.worst_case_drops[i][j - l] + 1;
                    int best_worst_case = Utils.max(e_b_t, e_b_f);
                    EggFloor.worst_case_drops[i][j] =
                            Utils.min(EggFloor.worst_case_drops[i][j],
                                      best_worst_case);
                }
            }
        }

        return EggFloor.worst_case_drops[n][k];
    }
}
