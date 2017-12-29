package InterviewProblems.Array;

// This problem deals with searching for strings in a
//   sorted array with empty string.
public class SearchStrEmpty
{
    /*
     * Test Cases
     * - null
     * - even/odd amount of elements
     * - 1, 2, 3 elements
     * - 5 spots, every other spot is a empty string
     * - multiple empty strings, check to see if going
     *     in the proper direction.
     *
     **/
    public static void main(String[] args)
    {
        String[] n = null;
        String[] one = {"eight"};
        String[] full = {"five", "", "ten", "", "word"};

        //int one_index = SearchStrEmpty.searchStrEmpty(one, "eight");
        int full_index = SearchStrEmpty.searchStrEmpty(full, "word");

        //System.out.println("one: " + one_index);
        System.out.println("full: " + full_index);
    }

    // Pre: str is an array of strings that contains
    //        either "real" strings and/or empty strings
    public static int searchStrEmpty(String[] arr, String word)
    {
        // Check if the array is null or empty.
        if(arr == null || arr.length == 0)
        {
            return -1;
        }

        // Look through if word exists in arr.
        int b = 0;
        int e = arr.length - 1;
        while(b < e)
        {
            System.out.println(b + " : " + e);
            int middle = ((b + e)/2);
            int mid_left = middle;
            int mid_right = middle;
            while(mid_left >= b && mid_right <= e &&
                  arr[mid_left].equals("") && arr[mid_right].equals(""))
            {
                mid_left--;
                mid_right++;
            }

            // Check that the middle indicies are inbounds and
            //   and the array has a non empty element.
            if(b > e)
            {
                return -1;
            }
            else if(!arr[mid_left].equals("") && mid_left <= b)
            {
                middle = mid_left;
            }
            else if(!arr[mid_right].equals("") && mid_right >= e)
            {
                middle = mid_right;
            }

            // Check if word is equal/>/< the current element.
            // comp == 0 means equal strings.
            // comp > 0 means word is further in dictionary
            // comp < 0 means word is before in dictionary
            int comp = word.compareTo(arr[middle]);
            if(comp == 0)
            {
                return middle;
            }
            else if(comp > 0)
            {
                b = middle + 1;
            }
            else
            {
                e = middle - 1;
            }
        }

        if(b == e && arr[b].equals(word))
        {
            return b;
        }


        // Return the "doesn't exist".
        return -1;
    }
}
