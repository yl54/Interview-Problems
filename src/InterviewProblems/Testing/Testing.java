package InterviewProblems.Testing;

import InterviewProblems.Utils.Utils;

import java.util.ArrayList;

// This class is used for miscellaneous tests.
// Probably for figuring out java properties.
public class Testing
{
    public static void main(String[] args)
    {
        Testing.testMod();
    }

    // This function helps to test how java's pointers
    //   work with objects.
    // Q: Will it create a reference, or will it create a new object?
    // A: Seems to treat as C ptr ref.
    public static void testArrays()
    {
        // Test with normal arrays.
        int[] arr = {1, 4, 5, 6, 2};
        int[] ptr = arr;
        ptr[2] = 100;
        System.out.println("This is printing arr.");
        Utils.printArray(arr);
        System.out.println("This is printing ptr.");
        Utils.printArray(ptr);

        System.out.println("-----------------------------");

        // Test with arraylists.
        ArrayList<Integer> arr_list = new ArrayList<Integer>();
        arr_list.add(3);
        arr_list.add(5);
        ArrayList<Integer> ptr_list = arr_list;
        ptr_list.add(6);
        System.out.println("This is printing arr_list.");
        Utils.printArrayList(arr_list);
        System.out.println("This is printing ptr_list.");
        Utils.printArrayList(ptr_list);
    }

    public static void testMod()
    {
        int d = 1;
        int e = 2;
        int p = 6;

        int d_p = d % p;
        int d_e = d % e;
        int p_d = p % d;
        int p_e = p % e;

        Testing.printInt("d_p: ", d_p);
        Testing.printInt("d_e: ", d_e);
        Testing.printInt("p_d: ", p_d);
        Testing.printInt("p_e: ", p_e);
    }

    public static void printInt(String message, int i)
    {
        System.out.println(message + i);
    }
}
