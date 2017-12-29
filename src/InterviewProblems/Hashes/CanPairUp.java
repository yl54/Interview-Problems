package InterviewProblems.Hashes;

import java.util.HashMap;
import java.util.Map;

// This problem deals with determining whether
//   or not an array can be divided into pairs
//   that add up to a number.
public class CanPairUp {
    // NOTE: This solution is susceptible to halves.
    //       For this, can check if even occurrences exist.
    // This solution performs 2 for loops.
    // This problem can also be done recursively.
    public static boolean canPairUp(int[] arr, int sum) {
        // Check if arr is valid.
        if (arr == null || arr.length == 0) {
            return false;
        }

        // Initialize a map of the differences that exist.
        Map<Integer, Integer> m_diff = new HashMap<>();

        // Insert every sum - arr[i], and add to count
        //   if more than one copy exists.
        for (int i = 0; i < arr.length; i++) {
            // Get the difference.
            int d = sum - arr[i];

            // Check if exists in map already.
            if (!m_diff.containsKey(d)) {
                m_diff.put(d, 0);
            }

            // Increment amount.
            m_diff.put(d, m_diff.get(d) + 1);
        }

        // Check if each arr[i] exists as a key.
        for (int i = 0; i < arr.length; i++) {
            // If so, then check if there exists a spot to be filled.
            // If not, then it's not true.
            if (!m_diff.containsKey(arr[i])) {
                return false;
            }

            int amt = m_diff.get(arr[i]);
            if (amt <= 0) {
                return false;
            }

            // Check if the difference is half of the sum.
            if (sum / 2 == arr[i] && amt % 2 == 0) {
                // Do nothing, move on.
            } else {
                // There exists a spot to be filled, so decrement value.
                m_diff.put(arr[i], amt - 1);
            }
        }

        // If reaches the end, it is true.
        return true;
    }
}
