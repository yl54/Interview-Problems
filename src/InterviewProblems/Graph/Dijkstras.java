package InterviewProblems.Graph;

import InterviewProblems.Utils.Nodes.GraphNode;

import java.util.*;

// This class will be used to solve the shortest
//   path problem using Dijkstra's algorithm.
public class Dijkstras {
    // This program deals with implementing
    //   Dijkstra's algorithm on a set of verticies.
    // Assume all costs are positive and unique ids.
    public static int findShortestPath (GraphNode begin, GraphNode end) {
        // Check to see if begin or end are invalid.
        if (begin == null || end == null) {
            return -1;
        }

        // Create a traversed set of graph nodes.
        Set<Integer> traversed_nodes = new HashSet<>();

        // Have a map that stores the minimum cost to get to the
        //   particular node from begin.
        // This queue has to be ordered by the least costly node
        //   coming out first. Priority queue with the lowest
        //   costs at the peak.
        Map<Integer, Integer> cost_values = new HashMap<Integer, Integer>();
        Queue<GraphNode> next_nodes = new PriorityQueue<>();

        next_nodes.add(begin);
        cost_values.put(begin.id, 0);

        // Do a dijkstra's search starting from the beginning node.
        // Check the queue if other nodes need to be traversed.
        while (!next_nodes.isEmpty()) {
            // Pull the node out.
            GraphNode current_node = next_nodes.remove();

            // Check if this node has already been traversed.
            if (traversed_nodes.contains(current_node.id)) {
                continue;
            }

            // Check all of the node's neighbors.
            int total_cost = cost_values.get(current_node.id);
            for (GraphNode neighbor_node: current_node.neighbors) {
                // Calculate the total cost to that node, which is the
                //   cost to the current node plus the cost to the next node.
                int neighbor_cost = total_cost + neighbor_node.data;

                // If the node hasn't been traversed yet, then place the
                //   cost in as the current cost. If this current cost
                //   is less, then replace the stored cost.
                if (!cost_values.containsKey(neighbor_node.id) ||
                   (cost_values.get(neighbor_node.id) > neighbor_cost)) {
                    cost_values.put(neighbor_node.id, neighbor_cost);
                }

                // Insert into queue if haven't traversed. Add a line of code
                //   into queue implementation to check to see if the node
                //   already exists in the queue.
                if (!traversed_nodes.contains(neighbor_node.id)) {
                    next_nodes.add(neighbor_node);
                }
            }

            traversed_nodes.add(current_node.id);
        }

        // Check if there is a path to the end destination.
        if (!cost_values.containsKey(end.id)) {
            return -1;
        }

        // Get the cost to the end node.
        return cost_values.get(end.id);
    }
}
