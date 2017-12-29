package InterviewProblems.Utils.HeapQueue;

public class HeapQueueInt {
    // A field with the values. Raw array of ints. Assume
    //   all values added are positive integers.
    private int[] values;

    // A field about the size of the queue.
    private int size;

    // Constructor.
    public HeapQueueInt() {
        this.values = new int[16];
    }

    // Function to enqueue a value.
    public void enqueue (int data) {
        // Check if size is 70% of array space.
        double max_load = values.length * 0.7;
        if (this.size > max_load) {
            // Resize the array.
        }

        // Place the integer in the last array index.
        this.size++;
        this.values[this.size] = data;

        // Heapify the array by seeing how far the added
        //   value can be brought to the top.
        int i = this.size / 2;
        boolean isChanged = true;
        while (i > 0 && isChanged) {
            isChanged = this.heapify(i);
            i /= 2;
        }
    }

    // Function to dequeue a value.
    public int dequeue () {
        // Check if the heap is empty.
        if (this.size == 0) {
            System.out.println("Error: Queue is empty.");
            return -1;
        }

        // Take the first value out of the heap.
        int dq = this.values[0];

        // Take the last element and put it at the top.
        this.values[0] = this.values[this.size];

        // Decrement size.
        this.size--;

        // Heapify the rest of the array.
        heapify(0);

        return dq;
    }

    // Function to heapify the heap. It takes one integer
    //   and moves it to its proper spot.
    private boolean heapify (int index) {
        boolean isChanged = false;

        // Take the element at the current position.
        while (index <= this.size / 2) {
            // Check to see if children are less than it.
            int child_index = index * 2;
            int child_value = this.values[child_index];
            int right_index = index * 2 + 1;
            if ((right_index <= this.size) &&
                    (this.values[right_index] < child_value)) {
                child_index = right_index;
                child_value = this.values[right_index];
            }

            // Check if a switch is necessary.
            if (child_index < this.values[index]) {
                this.values[child_index] = this.values[index];
                this.values[index] = child_value;
                isChanged = true;
            } else {
                break;
            }
        }

        // Return true if stuff happened, false if nothing happenned.
        return isChanged;
    }

    // Function to resize the array.
    public void resize (int new_size) {
        // Check if new_size is valid.
        if (new_size < 1 || new_size < this.size) {
            System.out.println("Error: new_size is too small.");
            return;
        }

        // Make the new array of new_size.
        int[] n_arr = new int[new_size];

        // Transfer the elements from the old array to the new one.
        for (int i = 1; i <= this.size; i++) {
            n_arr[i] = this.values[i];
        }

        // Reassign to the new array.
        this.values = n_arr;
    }
}