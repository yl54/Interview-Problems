package InterviewProblems.Hashes;

import java.util.*;

// This problem deals with finding the number of
//   distinct elements in each sliding window.
public class DistinctSlidingWindow {
    // This solution uses a hashset and hashmap to
    //   record the distinct values. Simultaneously
    //   do this problem as solutions are similar.
    public static void findDistinctWindow(int[] arr, int w_length) {
        // Check if arr is null or empty or w_length is invalid.
        if (arr == null || arr.length == 0 ||
                w_length < 0 || w_length > arr.length) {
            return;
        }

        // Initialize a hashset to hold values.
        Set<Integer> s_values = new HashSet<>();

        // Initialize a hashmap to hold values.
        Map<Integer, Integer> m_values = new HashMap<>();

        // Do a double for loop from index to number
        //   in the window.
        for (int i = 0; i < arr.length - w_length  + 1; i++) {
            for (int j = i; i < i + w_length; i++) {
                s_values.add(arr[j]);
            }

            // Record amount and empty the set for next window.
            int s_amt = s_values.size();
            System.out.println("s_amt: " + s_amt);
            s_values.clear();


            if (i >= w_length) {
                // Print the amount of distinct values.
                System.out.println("m_amt: " + m_values.size());

                // Remove the one from the first number in the window.
                m_values.put(arr[i], m_values.get(arr[i - w_length]) - 1);
            }
            if (!m_values.containsKey(arr[i])) {
                m_values.put(arr[i], 0);
            }

            m_values.put(arr[i], m_values.get(arr[i]) + 1);

        }
    }
}
