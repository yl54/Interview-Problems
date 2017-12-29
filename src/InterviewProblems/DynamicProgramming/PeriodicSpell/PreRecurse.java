package InterviewProblems.DynamicProgramming.PeriodicSpell;

import InterviewProblems.Utils.Nodes.StringNode;

import java.util.Set;

public class PreRecurse
{
    public static boolean canSpell(Set<String> elements, String word)
    {
        // Check if word is null or empty.
        if(word == null || word.length() == 0)
        {
            return false;
        }

        // Find all of the combinations that exist
        //   from the word in the elements hashset
        StringNode[] combos = PreRecurse.getCombos(elements, word);

        // Debug the combinations reached.
        PreRecurse.printCombos(combos);

        // Then just recurse through this list.
        return PreRecurse.canSpellHelper(combos, 0);
    }


    // This function helps to return the combinations
    //   that exist starting from certain indicies.
    // Assume that at this point the word is valid.
    public static StringNode[] getCombos
                (Set<String> elements, String word)
    {
        int wordLength = word.length();
        StringNode[] nodes = new StringNode[wordLength];

        // Loop through each index of the word.
        for(int i = 0; i < wordLength; i++)
        {
            // Check if the pattern combinations exist.
            for(int j = i + 1; j < Math.min(i + 3, wordLength + 1); j++)
            {
                String possible = word.substring(i, j);
                if(elements.contains(possible))
                {
                    StringNode c_node = new StringNode(possible);
                    // Check if it exists or not
                    if(nodes[i] == null)
                    {
                        nodes[i] = c_node;
                    }
                    // If it exists, then loop to the end
                    //    of linked list to attach the last combo.
                    // If too expensive, don't have to go to end
                    //    of the linked list, can continuously
                    //    insert at beginning.
                    else
                    {
                        StringNode root = nodes[i];
                        StringNode temp = root;
                        while(temp.next != null)
                        {
                            temp = temp.next;
                        }

                        temp.next = c_node;
                    }
                }
            }
        }

        return nodes;
    }

    // This function goes through the linkedlists and
    //   displays each combination at each index.
    public static void printCombos(StringNode[] combos)
    {
        for(int i = 0; i < combos.length; i++)
        {
            StringNode temp = combos[i];
            System.out.print(i + ": ");
            while(temp != null)
            {
                System.out.print(temp.data + " | ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    // This function will recurse through the already formed
    //   combinations, and will see if full combinations
    //   exist or not.
    public static boolean canSpellHelper(StringNode[] nodes, int index)
    {
        // Check to see if it made to the end.
        if(index >= nodes.length)
        {
            return true;
        }

        // Loop through combinations that exist.
        StringNode node = nodes[index];
        while(node != null)
        {
            if(PreRecurse.canSpellHelper(nodes, index + node.data.length()))
            {
                return true;
            }
            else
            {
                node = node.next;
            }
        }

        // If it reaches the end, then nothing was available.
        return false;
    }
}
