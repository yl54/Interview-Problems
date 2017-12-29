package InterviewProblems.DynamicProgramming.LongestPalindromeSubsequence;

import InterviewProblems.Utils.Utils;
import java.util.Collection;

// This class deals with the problem of finding the
//   longest palindromic subsequence.
public class LPSS
{
    public static String DIRECTORY =
            "src/InterviewProblems/DynamicProgramming/LongestPalindromeSubsequence/";
    public static String FILE_WORDLIST = DIRECTORY + "words.txt";

    /*
     * Test Cases:
     * - null/empty string
     * - palindrome with different character at beg/end
     * - no same letters
     * - two letter same, rest are different
     * - palindrome is in the middle/on sides
     *
     * */
    public static void main(String[] args)
    {
        Collection<String> words = Utils.collectFromFile(FILE_WORDLIST);
        LPSS.executeArr(words);
    }

    public static void executeArr(Collection<String> words)
    {
        for(String word: words)
        {
            LPSS.execute(word);
        }
    }

    public static void execute(String word)
    {
        int result = LPSS.lPSSStored(word);
        System.out.println("word: " + word);
        System.out.println("result: " + result);
        System.out.println();
    }

    // This solution recursively figures out the length
    //   of the longest possible palindrome.
    public static int lPSSRec(String word)
    {
        // Check if word is invalid/empty.
        if(word == null || word.length() == 0)
        {
            return -1;
        }

        // Do recursion on the word.
        return LPSS.lPSSRecHelper(word);
    }

    public static int lPSSRecHelper(String word)
    {
        // Check if the word is one letter.
        int length = word.length();
        if(length == 0 || length == 1)
        {
            return length;
        }

        // Check if the front and back letters are the same.
        boolean isSameCh = word.charAt(0) == word.charAt(length - 1);

        // If the same, then can subtract from begin/end,
        //   and continue traversing string.
        if(isSameCh)
        {
            return 2 + LPSS.lPSSRecHelper(word.substring(1, length - 1));
        }
        // If different, traverse subtracting from both ends.
        //   return the largest result.
        else
        {
            return Utils.max(
                    LPSS.lPSSRecHelper(word.substring(1, length)),
                    LPSS.lPSSRecHelper(word.substring(0, length - 1))
            );
        }
    }

    // This stores the maximum length of subsequences
    //   from i to j in the [i][j] entry. With this in mind
    //   it will waste the lower half of the space.
    public static int[][] max_amt;

    // Note: Can also be done recursively.

    // This solution figures out the palindromic possibilities
    //   from a bottom up fashion, since every larger solution
    //   depends on those from the bottom.
    public static int lPSSStored(String word)
    {
        // Check if word is invalid/empty.
        if(word == null || word.length() == 0)
        {
            return -1;
        }

        // Initialize the array.
        int length = word.length();
        LPSS.max_amt = new int[length][length];

        // Initialize cases of 1 letter and two letter first.
        for(int i = 0; i < length; i++)
        {
            LPSS.max_amt[i][i] = 1;
        }

        for(int i = 0; i < length - 1; i++)
        {
            boolean isSameCh = word.charAt(i) == word.charAt(i + 1);
            int result = 1;
            if(isSameCh)
            {
                result = 2;
            }
            LPSS.max_amt[i][i + 1] = result;
        }

        // Build the results from the bottom up.
        // Note: the [i][i] entry will always be one.
        // variables: diff is the amount of characters to analyze.
        //            anch is the starting (anchor) position to analyze from.
        for(int diff = 2; diff < length; diff++)
        {
            for(int anch = 0; anch < length - diff; anch++)
            {
                boolean isSameCh = word.charAt(anch) == word.charAt(anch + diff);
                int result = 2 + LPSS.max_amt[anch + 1][anch + diff - 1];
                if(!isSameCh)
                {
                    int beg = LPSS.max_amt[anch][anch + diff - 1];
                    int end = LPSS.max_amt[anch + 1][anch + diff];
                    result = Utils.max(beg, end);
                }
                LPSS.max_amt[anch][anch + diff] = result;
            }
        }

        // Return the last entry that exists.
        return LPSS.max_amt[0][length - 1];
    }
}
