package InterviewProblems.DynamicProgramming.PeriodicSpell;

import InterviewProblems.Utils.Utils;

import java.util.*;

public class PeriodicSpell
{
    public static Set<String> elements;
    public static String DIRECTORY = "src/InterviewProblems/PeriodicSpell/";
    public static String FILE_ELEMENTS = DIRECTORY + "elements.txt";
    public static String FILE_WORDS = DIRECTORY + "words.txt";

    /*
     * Test Cases
     * - null
     * - elements of 1, 2, 3... chars
     * - words of 1, 2, 3... chars long
     * - words with combinations at beginning/end
     * - no combinations in middle vs beginning/end
     * - same elements and words
     * - different element and word
     * - combinations that fail later, will they track backwords
     *
     **/
    public static void main(String[] args)
    {
        // Initialize words and element sets.
        Collection<String> words = Utils.collectFromFile(FILE_WORDS);
        words.add(null);
        words.add("");
        Collection<String> elem = Utils.collectFromFile(FILE_ELEMENTS);
        elements = new HashSet<>();
        elements.addAll(elem);

        // Loop through each word.
        for(String w: words)
        {
            // Test to see if word can be built by elements.
            boolean spell = PreRecurse.canSpell(elements, w);
            System.out.println(w + ": " + spell);
        }
    }
}
