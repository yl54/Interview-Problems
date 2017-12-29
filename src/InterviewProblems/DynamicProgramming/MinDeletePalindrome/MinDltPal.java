package InterviewProblems.DynamicProgramming.MinDeletePalindrome;

import InterviewProblems.Utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

// This problem is about deleting letters
//   from a string to produce a palindrome.
public class MinDltPal
{
    public static String DIRECTORY =
            "src/InterviewProblems/DynamicProgramming/MinDeletePalindrome/";
    public static String FILE_WORDLIST = DIRECTORY + "words.txt";

    /*
     * Test Cases:
     * - invalid string
     * - length 1 string
     * - wows, swow
     * - wosw
     *
     * */
    public static void main(String[] args)
    {
        // Can put these in a file
        Collection<String> words = Utils.collectFromFile(MinDltPal.FILE_WORDLIST);
        MinDltPal.executeArr(words);
    }

    public static void executeArr(Collection<String> words)
    {
        for(String word: words)
        {
            execute(word);
        }
    }

    public static void execute(String word)
    {
        int result = MinDltPal.minDeleteStored(word);
        System.out.println("word: " + word);
        System.out.println("result: " + result);
        System.out.println();
    }

    // This is a recursive solution that will check the
    //   minimum number of deletes needed to generate a
    //   palindrome. As long as the string is nonempty,
    //   this will always work because a single character
    //   is considered a palindrome.
    public static int minDelete(String word) {
        // Check if word is null/empty.
        if (word == null || word.length() == 0) {
            return -1;
        }

        // Do recursion on word to remove.
        return MinDltPal.minDeleteHelper(word, word.length() - 1);
    }

    // This function returns either the minimum number of
    //   deletes necessary to make word a palindrome, or
    //   MAX_VALUE if the traversal failed.
    public static int minDeleteHelper(String word, int index)
    {
        // Check if word is one letter or is a palindrome.
        if (word.length() == 1 || Utils.isPalindrome(word)) {
            System.out.println(word);
            return 0;
        }

        // Check if word is empty or index is out of bounds.
        if (word.length() == 0 || index < 0) {
            return Integer.MAX_VALUE;
        }

        // Two traversal paths:
        // Keep the letter
        int incl = MinDltPal.minDeleteHelper(word, index - 1);

        // Delete current letter
        String[] parts = new String[2];
        parts[0] = word.substring(0, index);
        parts[1] = "";
        if (index != word.length() - 1) {
            parts[1] = word.substring(index + 1, word.length());
        }
        String rmv = parts[0] + parts[1];
        int excl = MinDltPal.minDeleteHelper(rmv, index - 1);
        if(excl != Integer.MAX_VALUE) {
            excl += 1;
        }

        // Return the count that is smaller between the two.
        return Utils.min(incl, excl);
    }


    // This stores the minimum number of cuts for the string.
    public static HashMap<String, Integer> min_cuts;

    // This solution stores the results as it goes along.
    // Note: Another way to do this is to store the palindromes and
    //         traversed words in separate hashes, then find the
    //         biggest palindrome, and compare sizes to the original
    //         word.
    public static int minDeleteStored(String word)
    {
        // Check if the string is invalid/empty.
        if(word == null || word.length() == 0)
        {
            return -1;
        }

        // Initialize the hashmap
        MinDltPal.min_cuts = new HashMap<>();

        // Do recursion. Will be faster because lots of
        //   combinations repeated by traversals.
        return MinDltPal.minDeleteStoredHelper(word, word.length() - 1);
    }

    public static int minDeleteStoredHelper(String word, int index)
    {
        // Check if the word already exists in the hashmap.
        if(MinDltPal.min_cuts.containsKey(word))
        {
            return MinDltPal.min_cuts.get(word);
        }

        // Check if word is empty or index is out of bounds.
        if(word.length() == 0 || index < 0)
        {
            return Integer.MAX_VALUE;
        }

        // Check if word is one letter or is a palindrome.
        if(word.length() == 1 || Utils.isPalindrome(word))
        {
            MinDltPal.min_cuts.put(word, 0);
            return 0;
        }

        // These traversals are the most expensive parts, as many
        //   of the same paths will be traveled.
        //   of this operation.
        // Two traversal paths:
        // - keep the letter
        int incl = MinDltPal.minDeleteHelper(word, index - 1);

        // - delete current letter
        String[] parts = new String[2];
        parts[0] = word.substring(0, index);
        parts[1] = "";
        if(index != word.length() - 1)
        {
            parts[1] = word.substring(index + 1, word.length());
        }

        String rmv = parts[0] + parts[1];
        int excl = 1 + MinDltPal.minDeleteHelper(rmv, index - 1);

        // Return the count that is smaller between the two.
        MinDltPal.min_cuts.put(word, Utils.min(incl, excl));
        return MinDltPal.min_cuts.get(word);
    }
}
