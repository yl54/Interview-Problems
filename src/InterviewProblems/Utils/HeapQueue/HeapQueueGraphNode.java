package InterviewProblems.Utils.HeapQueue;

import InterviewProblems.Utils.Nodes.GraphNode;

public class HeapQueueGraphNode {
    // A field with the values. Raw array of ints. Assume
    //   all values added are positive integers.
    private GraphNode[] values;

    // A field about the size of the queue.
    private int size;

    // Constructor.
    public HeapQueueGraphNode() {
        this.values = new GraphNode[16];
        this.size = 0;
    }

    // Function to enqueue a value.
    public void enqueue (GraphNode node) {
        // Check if node is valid or not.
        if (node == null) {
            System.out.println("Enqueue - Error: null input found.");
            return;
        }

        // Check if the array is too filled up.
        // Resize the array if necessary.
        if (this.size > this.values.length * 0.7) {
            resize(this.size * 2);
        }

        // Put node into the queue.
        this.size++;
        this.values[this.size] = node;

        // Heapify array from the bottom to the top.
        int i = this.size;
        boolean isChanged = true;

        // Two possibilities to end heapify checking.
        // - i reaches top of array / end of heap
        //   i = 1 will start at top of the heap
        //   i = 0 will signal past top of heap
        // - if new node already found its place
        while (i > 0 && isChanged) {
            isChanged = this.heapify(i);
            i /= 2;
        }

        System.out.println("Enqueue - Task: finished heapify.");
    }

    // Function to dequeue a value.
    public GraphNode dequeue () {
        // Check if the queue is empty or not.
        if (this.values == null || this.size < 0) {
            return null;
        }

        // Pick the node at the top of the queue.
        GraphNode pick = this.values[0];

        // Place the last node at the front of the queue.
        this.values[0] = this.values[this.size];

        // Decrement size.
        this.size--;

        // Heapify from the top of the queue.
        this.heapify(1);

        // Check if queue needs to be shrunk.
        if (this.size < this.values.length / 4) {
            this.resize(this.values.length / 2);
        }

        return pick;
    }

    // Function to heapify the heap. It takes one integer
    //   and moves it to its proper spot.
    // Q: Should this retun an int or boolean?
    private boolean heapify (int index) {
        // Check if index is out of bounds.
        if (index < 1) {
            return false;
        }

        GraphNode current = this.values[index];
        boolean isChanged = false;
        while (index * 2 < this.size) {
            // Compare the left child with the right child if the right one exists.
            GraphNode child = this.values[index * 2];
            int child_i = index * 2;
            if (index * 2 + 1 <= this.size) {
                GraphNode right = this.values[index * 2 + 1];
                if (child.data > right.data) {
                    child = right;
                    child_i = index * 2 + 1;
                }
            }

            // Compare the current node against the smallest child.

            // If current >= child, end loop.
            if (current.data <= child.data) {
                break;
            }

            // Swap positions.
            // Flag as changed.
            // Make the child swapped index the current index.
            this.values[child_i] = current;
            this.values[index] = child;
            isChanged = true;
            index = child_i;
        }

        return isChanged;
    }

    // Function to resize the array.
    public void resize (int new_size) {
        // Check if new_size is out of bounds.
        if (new_size < 0) {
            System.out.println("Resize - Error: invalid size.");
            return;
        }

        // Check if new_size is big enough to hold all the elements.
        if (new_size < this.size) {
            System.out.println("Resize - Error: new_size too small to hold all of the elements.");
            return;
        }

        // Make a new array of new_size.
        GraphNode[] n_arr = new GraphNode[new_size];

        // Import all of the nodes from the previous array onto the new one.
        for (int i = 1; i <= this.size; i++) {
            n_arr[i] = this.values[i];
        }

        // Assign values to the new array.
        this.values = n_arr;
    }
}