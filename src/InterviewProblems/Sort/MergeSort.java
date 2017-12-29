package InterviewProblems.Sort;

public class MergeSort
{
    public static void main(String[] args)
    {
        // initialize array fo integers to analyze
        int[] nums = {5, 4, 9, 2, 7, 1, 3, 8, 4};

        // call for sorting method
        int[] result = MergeSort.mergeSort(nums, 0, nums.length - 1);

        for(int i = 0; i < result.length; i++)
        {
            System.out.print(result[i] + " | ");
        }
    }

    // Pre: arr is the array to sort
    //      b is the beginning index
    //      e is the ending index
    // Post: a sorted array
    public static int[] mergeSort(int[] arr, int b, int e)
    {
        // check if beginning index = end index
        if(b < e)
        {
            int middle = (b + e)/2;

            // get results from left and right half
            int[] left_arr = MergeSort.mergeSort(arr, b, middle);
            int[] right_arr = MergeSort.mergeSort(arr, middle + 1, e);

            // merge results from left and right halves
            return MergeSort.mergeSortHelper(left_arr, right_arr);
        }
        // This indicates a "one" value array
        else
        {
            int[] value = new int[1];
            value[0] = arr[b];
            return value;
        }
    }

    // Pre: left_arr is the left half to merge
    //      right_arr is the right half to merge
    // Post: a sorted, merged array
    public static int[] mergeSortHelper(int[] left_arr, int[] right_arr)
    {
        // init new array of this length
        int total_length = left_arr.length + right_arr.length;
        int[] merged_nums = new int[total_length];
        // init the beginning of right and left half indicies
        int left_index = 0;
        int right_index = 0;
        int full_index = 0;
        // compare and fit into new array
        while(left_index < left_arr.length &&
              right_index < right_arr.length &&
              full_index < total_length)
        {
            if(left_arr[left_index] < right_arr[right_index])
            {
                merged_nums[full_index] = left_arr[left_index];
                left_index++;
            }
            else
            {
                merged_nums[full_index] = right_arr[right_index];
                right_index++;
            }

            full_index++;
        }

        // If leftovers exist, then place rest into array.
        while(left_index < left_arr.length && full_index < total_length)
        {
            merged_nums[full_index] = left_arr[left_index];
            left_index++;
            full_index++;
        }

        while(right_index < right_arr.length && full_index < merged_nums.length)
        {
            merged_nums[full_index] = right_arr[right_index];
            right_index++;
            full_index++;
        }

        return merged_nums;
    }
}
