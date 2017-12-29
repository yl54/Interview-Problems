package InterviewProblems.DynamicProgramming.LongestCommonSubsequence;

import InterviewProblems.Utils.Utils;

import java.util.Collection;

// This class tests the longest common
//   subsequence problem.
// LCSS = Longest Common Subsequence
public class LCSS
{
    public static String DIRECTORY =
            "src/InterviewProblems/DynamicProgramming/LongestCommonSubsequence/";
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
                int longestAmt = LCSSUtils.lCSS(wp[0], wp[1]);
                amt = 0;
            }
            else
            {
                wp[amt] = word;
                amt++;
            }
        }
    }
}
