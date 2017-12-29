package InterviewProblems.DynamicProgramming.CountCapCombination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// This problem is about counting the various ways to
//   assign a unique cap to each person. In this problem
//   people can have same id caps, but only have one cap
//   per id per person. The solution on the web seems to
//   add the condition that people may or may not be wearing
//   caps (say 32 people). Then, this can be measured with
//   bits. However, the permutations part still is still
//   needed to be done via recursion.
public class CountCaps
{
    /*
     * Test Cases
     * - null/invalid caps input
     * - 0 to multiple people, no caps
     * - 0 to multiple people, all have same cap
     * - 0 to multiple people, all have different caps
     *
     **/
    public static void main(String[] args)
    {
        ArrayList<ArrayList<Integer>> caps = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> p_1 = new ArrayList<Integer>();
        ArrayList<Integer> p_2 = new ArrayList<Integer>();
        ArrayList<Integer> p_3 = new ArrayList<Integer>();

        p_1.add(1);
        p_1.add(11);
        p_1.add(2);

        p_2.add(1);
        p_2.add(2);
        p_2.add(3);

        p_3.add(20);
        p_3.add(21);
        p_3.add(22);

        caps.add(p_1);
        caps.add(p_2);
        caps.add(p_3);

        CountCaps.execute(caps);
     }

    public static void execute(ArrayList<ArrayList<Integer>> caps){
        int result = CountCaps.countWaysRec(caps);
        System.out.println("Number of people: " + caps.size());
        System.out.println("Number of combinations: " + result);
    }

    // This set will store the currently seen caps.
    public static Set<Integer> caps_chosen;

    // This number will store the current amount of seen
    //   combinations.
    public static int total_combinations;

    // This solution deals with this by recursively
    //   trying every possible state.
    public static int countWaysRec
    (ArrayList<ArrayList<Integer>> caps)
    {
        // Check if caps is invalid.
        if(caps == null)
        {
            return -1;
        }

        // Initialize helper variables.
        CountCaps.caps_chosen = new HashSet<Integer>();
        CountCaps.total_combinations = 0;

        // Do recursion on this caps.
        CountCaps.countWaysRecHelper(caps, 0);

        return CountCaps.total_combinations;
    }

    public static void countWaysRecHelper
            (ArrayList<ArrayList<Integer>> caps,
             int p_index)
    {
        if(p_index < 0)
        {
            return;
        }

        if(p_index >= caps.size())
        {
            CountCaps.total_combinations++;
            return;
        }

        int p_next = p_index + 1;
        ArrayList<Integer> cap_collection = caps.get(p_index);
        for(int i = 0; i < cap_collection.size(); i++)
        {
            int cap_id = cap_collection.get(i);
            if(!CountCaps.caps_chosen.contains(cap_id))
            {
                CountCaps.caps_chosen.add(cap_id);
                CountCaps.countWaysRecHelper(caps, p_next);
                CountCaps.caps_chosen.remove(cap_id);
            }
        }
    }
}
