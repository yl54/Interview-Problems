package InterviewProblems.DynamicProgramming.LongestCommonSubstring;

import InterviewProblems.Utils.Utils;

import java.util.Collection;

// This class deals with the longest common substring
//   between two words problem.
public class LCSStr
{
    public static String DIRECTORY = "src/InterviewProblems/DynamicProgramming/LongestCommonSubstring/";
    public static String FILE_WORDLIST = DIRECTORY + "word_pairs.txt";

    /*
     * Test Cases
     * - null
     * - one character each
     * - two characters each
     * - no common substrings
     * - same strings as input
     * - one smaller than other, clearly has substring
     * - substring at beginning/ending
     * - two separate common substrings (qweriop, qwertyiop)
     *
     **/
    public static void main(String[] args)
    {
        // Gather words from file.
        Collection<String> words = Utils.collectFromFile(FILE_WORDLIST);

        // Pair words together and compare them.
        int amt = 0;
        String[] wp = new String[2];
        for(String word: words)
        {
            if(amt == 2)
            {
                System.out.println("Word 1: " + wp[0]);
                System.out.println("Word 2: " + wp[1]);
                int longestAmt = LCSStr.lCSSBotUp(wp[0], wp[1]);
                amt = 0;
                System.out.println("Result: " + longestAmt);
            }
            else
            {
                wp[amt] = word;
                amt++;
            }
        }
    }

    // This solution to get the longest common substring
    //   length builds from the bottom up.
    public static int lCSSBotUp(String word_1, String word_2)
    {
        // Check if either word is null/empty.
        if(Utils.isStrNullEmpty(word_1) || Utils.isStrNullEmpty(word_2))
        {
            return -1;
        }

        // Initialize the array. Keep track of the max amount.
        int w_1_len = word_1.length();
        int w_2_len = word_2.length();
        int[][] track_substr = new int[w_1_len + 1][w_2_len + 1];
        int max = 0;

        // Do a m x n traversal to check where the
        //   substrings are.
        // Note: Prob a function prob can extract the largest substring.
        for(int i = 1; i <= w_1_len; i++)
        {
            for(int j = 1; j <= w_2_len; j++)
            {
                boolean isSame = word_1.charAt(i - 1) == word_2.charAt(j - 1);
                if(isSame)
                {
                    track_substr[i][j] = track_substr[i - 1][j - 1] + 1;
                    max = Utils.max(max, track_substr[i][j]);
                }
            }
        }

        // Return the largest.
        return max;
    }
}
