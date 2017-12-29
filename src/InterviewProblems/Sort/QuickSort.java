package InterviewProblems.Sort;

public class QuickSort
{
    // Using a static variable because its an easy
    //   way to save the state of the array while
    //   returning other important values, such as
    //   the pivot index.
    public static int[] nums = {5, 4, 9, 2, 7, 1, 3, 8, 4};

    public static void main(String[] args)
    {
        // sort the array
        QuickSort.quickSort(QuickSort.nums, 0, nums.length - 1);

        // print out the elements
        QuickSort.printArr();
    }

    // This method will perform a quick sort on an array. It will
    //   take any partitioning method and then sort it out
    // Pre: arr is the array to be sorted
    //        arr works because its a static class variable, so its
    //        within the scope
    //      b is the beginning index.
    //      e is the ending index.
    // Post: a sorted version of arr
    public static void quickSort(int[] arr, int b, int e)
    {
        // Check if the ending is greater than the beginning
        // System.out.println(b + " | " + e);
        if(b < e)
        {
            // Partition and get middle index
            int pivot = QuickSort.endPartition(arr, b, e);

            // dive into left and right parts of array
            QuickSort.quickSort(arr, b, pivot - 1);
            QuickSort.quickSort(arr, pivot + 1, e);
        }
    }

    // A partitioning method that uses the last element
    //   as the partitioning pivot.
    // Pre: arr is the array to sort
    //      b is the beginning index
    //      e is the ending index
    // Post: returns 2 arrays, one is
    public static int endPartition(int[] arr, int b, int e)
    {
        int f_index = QuickSort.partitionCheck(arr, b, e - 1, e);

        // check the last element with the first element
        if(arr[e] < arr[f_index])
        {
            int temp = arr[e];
            arr[e] = arr[f_index];
            arr[f_index] = temp;
        }

        // return the pivot index
        return f_index;
    }

    // A partitioning method that uses the median of three
    //   values as the partitioning pivot.
    public static int mOfThrPartition(int[] arr, int b, int e)
    {
        // pick the pivot element
        int f_pivot = b;
        int e_pivot = e;
        int m_pivot = (b + e)/2;
        int[] choice = {f_pivot, e_pivot, m_pivot};

        // Sort the choices to find the median.
        for(int i = 0; i < choice.length; i++)
        {
            int choice_1 = choice[i];
            for(int j = i + 1; j < choice.length; j++)
            {
                int choice_2 = choice[j];
                if(arr[choice_1] > arr[choice_2])
                {
                    choice[i] = choice_2;
                    choice[j] = choice_1;
                }
            }
        }

        int pivot = choice[1];
        int f_index = QuickSort.partitionCheck(arr, b, e, pivot);

        // check the last element with the first element
        boolean larger = arr[pivot] > arr[f_index];

        // Check to see if:
        //   Pivot index greater than ending index and pivot
        //     value is not larger(less than or equal to)
        //     than ending value.
        //   Pivot index is less than ending index and pivot
        //     value is larger than ending value.
        // Goal is to make sure all values are in ascending order.
        if((pivot > f_index && !larger) ||
           (pivot < f_index && larger))
        {
            int temp = arr[pivot];
            arr[pivot] = arr[f_index];
            arr[f_index] = temp;
        }

        // return the pivot index
        return f_index;
    }

    // This function loops forward from the beginning index and
    //   backwards from the ending index to switch positions of
    //   elements that are greater than and less than the
    //   pivot element.
    public static int partitionCheck(int[] arr, int b, int e, int pivot)
    {
        int f_index = b;
        int e_index = e;

        // loop through and find which elements should be
        //   to the left or right
        // the found boolean check failed
        while(f_index < e_index)
        {
            // Find the value/index that is greater than pivot.
            while(f_index < e_index && arr[f_index] <= arr[pivot])
            {
                f_index++;
            }

            // Find the value/index that is less than pivot.
            while(f_index < e_index && arr[e_index] > arr[pivot])
            {
                e_index--;
            }

            // Check if the two indicies are different.
            if(f_index < e_index)
            {
                int temp = arr[f_index];
                arr[f_index] = arr[e_index];
                arr[e_index] = temp;
            }
        }

        // This index marks the meeting point between the
        //   iterator that started from the beginning and
        //   the one that started from the end.
        return f_index;
    }

    public static void printArr()
    {
        for(int i = 0; i < QuickSort.nums.length; i++)
        {
            System.out.print(QuickSort.nums[i] + " | ");
        }

        System.out.println();
    }
}


