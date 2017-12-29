package InterviewProblems.Sort.BinarySearch;

public class BinarySearch {

    // This function will perform a binary search over
    //   an ascending sorted array.
    public static int binarySearch(int[] arr, int s) {
        // Check if arr is null or empty.
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // Initialize b, e and m.
        int b = 0;
        int e = arr.length - 1;
        int m = (b + e) / 2;

        // Check the middle number.
        // If s > m, then move right.
        // If s < m, then move left.
        // If s == m, then return that index.
        // KEY: Every time an entry is not equivalent
        //        to s, exclude that entry from the search.
        while (b <= e) {
            if (s == arr[m]) {
                return m;
            } else if (s < arr[m]) {
                e = m - 1;
            } else if (s > arr[m]) {
                b = m + 1;
            }

            m = (b + e) / 2;
        }

        // Return -1, since didn't find a match.
        return -1;
    }
}
