import sun.nio.cs.ArrayDecoder;

import java.util.ArrayDeque;
import java.util.ArrayList;


public class SmithMcGlincy {

    private int s;
    private int t;
    private int V;
    private int[][] G;  // Graph
    private int[][] R;  //Reverse Graph

    // TODO: 4/4/17 Make Generic
    private int[] cost;
    private int[] requires;

    private ArrayDeque<Integer> fifo = new ArrayDeque<Integer>();
    private int[] edgeSorted;
    private int maxCountSortValue;

    public SmithMcGlincy(int[][] g, int s, int t, int maxCountSortValue) {
        this.s = s;
        this.t = t;
        this.G = g;
        this.V = g.length;
        this.maxCountSortValue = maxCountSortValue;
        R = new int[V][V];


        // TODO: 4/4/17 make generic
        cost = new int[V];
        requires = new int[V];
        edgeSorted = new int[V];


    }

    public int maxFlow() {
        init();
        fifo.addFirst(t);
        while (!fifo.isEmpty()) {
            int current = fifo.pop();
            if (current != s) {
//                System.out.println();
//                System.out.println("Processing Node: " + current);
//                System.out.print("Requires");
//                for (int j = 0; j < requires.length; j++) {
//                    System.out.print(requires[j] + " ");
//                }
//                System.out.println();
//                System.out.print("Cost: ");
//                for (int j = 0; j < cost.length; j++) {
//                    System.out.print(cost[j] + " ");
//                }
//                System.out.println();

                // Sort the edges
                edgeSorted = countSort(R[current]);
                ArrayDeque<Integer> edgeQueue = new ArrayDeque<Integer>();
                int first = -1;


                // Go through each edge
                // Start at end of array and go forward until zero
                for (int i = V - 1; i >= 0; i--) {

                    // Add all edges with value to queue
                    if (R[current][edgeSorted[i]] > 0) {

                        // If connected to s.  Use that first
                        if (edgeSorted[i] == s)
                            first = edgeSorted[i];

                            // If next node is one away from s
                        else if (G[s][edgeSorted[i]] > 0) {
                            edgeQueue.addFirst(edgeSorted[i]);

                        }
                        // Other wise add edge to back
                        else
                            edgeQueue.addLast(edgeSorted[i]);
                    }

                }
                // Put S First
                if (first > -1)
                    edgeQueue.addFirst(first);


                while (!edgeQueue.isEmpty()) {

                    int edge = edgeQueue.pop();
//                    System.out.println("Edge: " + edge);


                    if (edge != current) {

                        //process edge
                        int adjCost = cost[edge] - requires[edge];   //Cost of edge - require edge.  Most times requires will be 0
                        int value = Math.min(adjCost, requires[current]);
                        value = Math.min(value, R[current][edge]);
//                        System.out.println("Current: " + current + " Edge: " + edge + " cost: " + cost[edge] + " Adj Cost: " + adjCost);
//                        System.out.println("Current: " + current + " Edge: " + edge + " requires: " + requires[current]);
//                        System.out.println("Current: " + current + " Edge: " + edge + " R: " + R[current][edge]);
//                        System.out.println("Choose: " + value);
//                        System.out.println();

                        if (value > 0){
                            fifo.addLast(edge);
                            requires[current] -= value;
                            requires[edge] += value;
                            R[current][edge] -= value;

                        }

                    }

                }
            }
        }


        return requires[s];
    }

    // Make reverse graph and calculate cost
    private void init() {
        for (int i = 0; i < V; i++) {
            //// TODO: 4/4/17 make generic
            int total = 0;
            requires[i] = 0;
            for (int j = 0; j < V; j++) {
                total += G[i][j];
                R[j][i] = G[i][j];
            }
            cost[i] = total;
//            System.out.println();
        }
//        System.out.println("Cost: ");
//        for (int i = 0; i < V; i++) {
//            System.out.print(cost[i] + ", ");
//        }
//        System.out.println("\n");


        // Scan the outgoing edges
        // Choose min(input vs output)
        for (int i = 1; i < V; i++) {     // Skip first row
            // TODO: 4/4/17 make "total" generics
            int total = 0;
            for (int j = 0; j < V; j++) {
//                System.out.print(R[i][j] + " ");
                total += R[i][j];
            }
            cost[i] = Math.min(total, cost[i]);
            if (i == t) {
                requires[t] = total;
//                System.out.println();
//                System.out.println("T Required: " + requires[t]);
            }
//            System.out.println();
        }
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//        System.out.println("Cost 2: ");
//        for (int i = 0; i < V; i++) {
//            System.out.print(cost[i] + ", ");
//        }

    }

    /**
     * @param array
     * @return A sorted array based on the index values.  Does not return sorted array
     * Think POINTERS
     */
    private int[] countSort(int[] array) {

        int maxValue = this.maxCountSortValue;
        int length = array.length;
        int[] count = new int[maxValue];
        int[] ans = new int[length];
        for (int i = 0; i < maxValue; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < length; i++) {
            ++count[array[i]];
        }
        for (int i = 1; i < maxValue; i++) {
            count[i] += count[i - 1];
        }
        for (int i = 0; i < length; i++) {
            ans[count[array[i]] - 1] = i;
            --count[array[i]];
        }


        return ans;
    }

}
