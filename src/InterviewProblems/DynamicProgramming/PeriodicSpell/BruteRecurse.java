package InterviewProblems.DynamicProgramming.PeriodicSpell;

import java.util.Set;

public class BruteRecurse
{
    public static boolean canSpell(Set<String> elements, String word)
    {
        // Check if word is null or empty;
        if(word == null || word.length() == 0)
        {
            return false;
        }

        // Do recursive solution
        return helper(elements, word, 0);
    }

    public static boolean helper(Set<String> elements, String word, int index)
    {
        // Check if index is still within bounds.
        if(index >= word.length())
        {
            return true;
        }

        // Check if the one letter solution exists.
        boolean one_letter = recurseFurther(elements, word, index, 1);
        if(one_letter)
        {
            return true;
        }

        // Check if the two letter solution exists.
        if(index + 1 < word.length())
        {
            boolean two_letter = recurseFurther(elements, word, index, 2);
            if(two_letter)
            {
                return true;
            }
        }

        /*
        // Check if the three letter solution exists.
        if(index + 2 < word.length())
        {
            boolean three_letter = recurseFurther(elements, word, index, 3);
            if(three_letter)
            {
                return true;
            }
        }

        // Check if the four letter solution exists.
        if(index + 3 < word.length())
        {
            boolean four_letter = recurseFurther(elements, word, index, 4);
            if(four_letter)
            {
                return true;
            }
        }
        */

        // If none of them exists, then its false.
        return false;
    }

    public static boolean recurseFurther(Set<String> elements, String word, int index, int amt)
    {
        String letter = word.substring(index, index + amt);
        System.out.println(index + ": " + letter);
        return elements.contains(letter) && helper(elements, word, index + amt);
    }
}
