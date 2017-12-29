package InterviewProblems.Sort.BinarySearch;

// This problem deals with finding a provided integer
//   in a rotated, sorted array. This solution will
//   not use concactenation or look the list in one
//   loop. It will use a binary search to find the
//   pivot, and then binary search over the 2 arrays.
public class RotatedSearch {
    // This function uses a binary search to look through
    //   the array and find the index for a particular
    //   element in a rotated array.
    public static int findRotatedBS(int[] arr, int s) {
        // Check if arr is null or s is an invalid value.
        if(arr == null || arr.length == 0) {
            return -1;
        }

        int result = -1;

        // Check if the array is rotated or not.
        if(arr[0] > arr[arr.length - 1]) {
            // If not, then go find the pivot index.
            int pivot = findPivot(arr);

            // Take the part of the array its in,
            //   and do a binary search.
            int[] left_arr = RotatedSearch.buildArr(arr, 0, pivot);
            int[] right_arr = RotatedSearch.buildArr(arr, pivot + 1, arr.length - 1);

            int left_index = BinarySearch.binarySearch(left_arr, s);
            int right_index = BinarySearch.binarySearch(right_arr, s);
            if (left_index != -1) {
                result = left_index;
            } else {
                result = right_index;
            }
        } else {
            // If so, just do a normal binary search.
            result = BinarySearch.binarySearch(arr, s);
        }

        return result;
    }

    // This function searches for the pivot value in binary search fashion.
    // It assumes that the array is sorted and pivoted.
    public static int findPivot(int[] arr) {
        // Initialize the beginning and end indicies.
        int b = 0;
        int e = arr.length - 1;
        int m = (b + e) / 2;

        boolean isAlignedWithEnd = arr[m] < arr[e];
        int bound_begin = arr[b];
        int bound_end = arr[e];

        // Need to find point where arr[i] > arr[i + 1].
        // Make sure m is in bounds.
        // There are two possibilities.
        // - arr[i] is less than bound_begin and bound_end.
        // - arr[i] is greater than bound_begin and bound_end.
        // Not possible to be greater than one and less than the other.
        while (m < arr.length && m >= 0 && b <= e && arr[m] < arr[m + 1]) {
            if (arr[m] > bound_begin && arr[m] > bound_end) {
                b = m + 1;
            }
            else if (arr[m] < bound_begin && arr[m] < bound_end) {
                e = m - 1;
            }

            // Recalculate m.
            m = (b + e)/2;
        }

        return m;
    }

    // This function returns a new array that spans from
    //   the b index to e index, inclusive.
    public static int[] buildArr(int[] arr, int b, int e) {
        int length = e - b + 1;
        int[] n_arr = new int[length];
        for (int i = 0; i < length; i++) {
            n_arr[i] = i + b;
        }
        return n_arr;
    }
}
