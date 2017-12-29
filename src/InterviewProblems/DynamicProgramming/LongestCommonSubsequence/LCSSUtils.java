package InterviewProblems.DynamicProgramming.LongestCommonSubsequence;

import InterviewProblems.Utils.Utils;

// This class provides methods to get the
//   amount of characters in the longest
//   common subssequence.
public class LCSSUtils
{
    // This represents the amount of common characters from
    //   0 - i of first word and 0 - j of second word.
    public static int[][] commonCount;

    // Traversed accurately reflects the traversal path
    //   because every combination has one solution, and
    //   the later solutions are built up from smaller
    //   iterations.
    public static boolean[][] traversed;

    public static int lCSS(String word_1, String word_2)
    {
        // Check for null or empty cases.
        if(word_1 == null || word_2 == null ||
           word_1.length() == 0 || word_2.length() == 0)
        {
            System.out.println("Something is invalid input.");
            return 0;
        }

        // Comment out if not using stored method.
        LCSSUtils.commonCount = new int[word_1.length() + 1][word_2.length() + 1];
        LCSSUtils.traversed = new boolean[word_1.length() + 1][word_2.length() + 1];

        // Go through in recursive fashion.
        int finalAmt = LCSSUtils.lCSSStoredHelper(word_1, word_2);
        String substr = getString(word_1, word_2, finalAmt);
//        LCSSUtils.printArr(LCSSUtils.commonCount);
        System.out.println(word_1 + " + " + word_2 + ": " + finalAmt + ", " + substr + ".");
        return finalAmt;
    }

    // Currently, this will only get the length of the longest
    //   substring. This function traverses from the end of
    //   the string to the beginning
    public static int lCSSBackHelper(String word_1, String word_2)
    {
        int length_1 = word_1.length();
        int length_2 = word_2.length();

        // Base case: reaches the end of the string.
        if(length_1 == 0 || length_2 == 0)
        {
            return 0;
        }

        // Need both of these substrings in both situations.
        String w_1 = word_1.substring(0, length_1 - 1);
        String w_2 = word_2.substring(0, length_2 - 1);

        // Check if the characters are the same. IF so, then
        //   can add this as part of result.
        if(word_1.charAt(length_1 - 1) == word_2.charAt(length_2 - 1))
        {
            return 1 + LCSSUtils.lCSSBackHelper(w_1, w_2);
        }
        // Characters are not the same, so need to check
        //   if the last character for each string matches
        //   with other characters.
        else
        {
            int combo_1 = LCSSUtils.lCSSBackHelper(w_1, word_2);
            int combo_2 = LCSSUtils.lCSSBackHelper(word_1, w_2);
            return Utils.max(combo_1, combo_2);
        }
    }

    // Currently, this will only get the length of the longest
    //   substring. This function traverses from the front of
    //   the string to the ending.
    public static int lCSSFrontHelper(String word_1, String word_2)
    {
        int length_1 = word_1.length();
        int length_2 = word_2.length();

        // Base case: reaches the end of the string.
        if(length_1 == 0 || length_2 == 0)
        {
            return 0;
        }

        // Need both of these substrings in both situations.
        String w_1 = word_1.substring(1, length_1);
        String w_2 = word_2.substring(1, length_2);

        // Check if the characters are the same. IF so, then
        //   can add this as part of result.
        if(word_1.charAt(0) == word_2.charAt(0))
        {
            return 1 + LCSSUtils.lCSSFrontHelper(w_1, w_2);
        }
        // Characters are not the same, so need to check
        //   if the last character for each string matches
        //   with other characters.
        else
        {
            int combo_1 = LCSSUtils.lCSSFrontHelper(w_1, word_2);
            int combo_2 = LCSSUtils.lCSSFrontHelper(word_1, w_2);
            return Utils.max(combo_1, combo_2);
        }
    }

    // Currently, this will only get the length of the longest
    //   substring. This function remembers results as it
    //   traverses through the strings. This uses backtracking,
    //   but it can be done with front tracking too.
    public static int lCSSStoredHelper(String word_1, String word_2)
    {
        int length_1 = word_1.length();
        int length_2 = word_2.length();

        // Base case: reaches the end of the string.
        if(length_1 == 0 || length_2 == 0)
        {
            return 0;
        }

        // Need both of these substrings to traverse .
        String w_1 = word_1.substring(0, length_1 - 1);
        String w_2 = word_2.substring(0, length_2 - 1);

        // Check if the characters are the same. IF so, then
        //   can add this as part of result.
        int result;
        if(word_1.charAt(length_1 - 1) == word_2.charAt(length_2 - 1))
        {
            LCSSUtils.storeCount(w_1, w_2, length_1 - 1, length_2 - 1);
            result = 1 + LCSSUtils.commonCount[length_1 - 1][length_2 - 1];
        }
        // Characters are not the same, so need to check
        //   if the last character for each string matches
        //   with other characters.
        else
        {
            LCSSUtils.storeCount(w_1, word_2, length_1 - 1, length_2);
            LCSSUtils.storeCount(word_1, w_2, length_1, length_2 - 1);
            int combo_1 = LCSSUtils.commonCount[length_1 - 1][length_2];
            int combo_2 = LCSSUtils.commonCount[length_1][length_2 - 1];
            result = Utils.max(combo_1, combo_2);
        }

        LCSSUtils.commonCount[length_1][length_2] = result;
        return LCSSUtils.commonCount[length_1][length_2];
    }

    public static void storeCount(String word_1, String word_2, int length_1, int length_2)
    {
        if(!LCSSUtils.traversed[length_1][length_2])
        {
            LCSSUtils.traversed[length_1][length_2] = true;
            LCSSUtils.commonCount[length_1][length_2] = LCSSUtils.lCSSStoredHelper(word_1, word_2);
//            System.out.println(word_1 + " | " + word_2);
        }
    }

    // This function uses the results from the longest subsequence recursion to
    //   retrieve the actual subsequence in string form.
    public static String getString(String word_1, String word_2, int max_amt)
    {
        // Find the greatest common substring.
        char[] lcss_final = new char[max_amt];
        int index_w_1 = word_1.length();
        int index_w_2 = word_2.length();
        int index_lcss = max_amt - 1;
        while((index_w_1 > 0 && index_w_2 > 0) && index_lcss >= 0)
        {
            char c_1 = word_1.charAt(index_w_1 - 1);
            char c_2 = word_2.charAt(index_w_2 - 1);
            // Check if the two characters are the same.
            if(c_1 == c_2)
            {
                lcss_final[index_lcss] = c_1;
                index_w_1--;
                index_w_2--;
                index_lcss--;
            }
            // Check which direction of traversal on the array
            //   contains a largest subset of values.
            else if(LCSSUtils.commonCount[index_w_1 - 1][index_w_2] > LCSSUtils.commonCount[index_w_1][index_w_2 - 1])
            {
                index_w_1--;
            }
            else
            {
                index_w_2--;
            }
        }

        return new String(lcss_final);
    }

    public static void printArr(int[][] arr)
    {
        for(int i = 0; i <arr.length; i++)
        {
            System.out.print(i + ": ");
            for(int j = 0; j < arr[i].length; j++)
            {
                System.out.print(arr[i][j] + " | ");
            }
            System.out.println();
        }
    }
}
