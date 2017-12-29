package InterviewProblems.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Utils
{
    // This function returns lines of strings from a file.
    public static Collection<String> collectFromFile(String fileName)
    {
        BufferedReader br = null;
        FileReader fr = null;
        Collection<String> s = new LinkedList<String>();
        try
        {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                s.add(sCurrentLine);
            }

            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return s;
    }

    // Return the max result.
    public static int max(int num_1, int num_2)
    {
        return (num_1 > num_2)? num_1 : num_2;
    }

    // Return the min result.
    public static int min(int num_1, int num_2)
    {
        return (num_1 < num_2)? num_1 : num_2;
    }

    // This function determines whether or not arr_1 is a subarray of
    //   arr_2. This handles arrays filled with integers.
    public static boolean isSubArray(int[] arr_1, int[] arr_2)
    {
        // Check if arr_1 or arr_2 is null or empty.
        if(arr_1 == null || arr_2 == null ||
           arr_1.length == 0 || arr_2.length == 0)
        {
            return false;
        }

        // Check if arr_1 is longer than arr_2.
        if(arr_1.length > arr_2.length)
        {
            return false;
        }

        // Loop through arr_2, up to the point where arr_1 can
        //   completely fit in arr_2.
        for(int i = 0; i <= arr_2.length - arr_1.length; i++)
        {
            // If the initial value of arr_1 is the same
            //   as arr_2 value, then check to see if eaach
            //   of the values are the same.
            if(arr_1[0] == arr_2[i])
            {
                for(int j = 0; j < arr_1.length; j++)
                {
                    // Check if each value is the same.
                    if(arr_1[j] != arr_2[i + j])
                    {
                        break;
                    }
                    // If it reaches the end of arr_1 and
                    //   every element is the same, then
                    //   arr_1 is a subarray of arr_2.
                    else if(j == arr_1.length - 1)
                    {
                        return true;
                    }
                }
            }
        }

        // At this point, no sub array found, so arr_1 is
        //   not a subarray of arr_2.
        return false;
    }

    // This function prints the contents of
    //   an integer array.
    public static void printArray(int[] arr)
    {
        if(arr == null)
        {
            return;
        }

        System.out.print("Contents: ");
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " | ");
        }

        System.out.println();
    }

    // This function prints the contents of
    //   an integer array.
    public static void printArrayList(ArrayList<Integer> arr)
    {
        if(arr == null)
        {
            return;
        }

        System.out.print("Contents: ");
        for(int i = 0; i < arr.size(); i++)
        {
            System.out.print(arr.get(i) + " | ");
        }

        System.out.println();
    }

    // This function checks to see if the given
    //   word is a palindrome.
    public static boolean isPalindrome(String word)
    {
        if(word == null || word.length() == 0)
        {
            return false;
        }

        int w_length = word.length();
        for(int i = 0; i < w_length/2; i++)
        {
            if(word.charAt(i) != word.charAt(w_length - 1 - i))
            {
                return false;
            }
        }

        return true;
    }

    // This function determines whether or not the string value
    //   is a null or empty string.
    public static boolean isStrNullEmpty(String str)
    {
        return str == null || str.length() == 0;
    }

    // This function determines whether or not the int array
    //   is a null or empty.
    public static boolean isArrNullEmpty(int[][] arr)
    {
        return arr == null || arr.length == 0;
    }
}
