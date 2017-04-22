import sun.nio.cs.ArrayDecoder;

import java.util.ArrayDeque;
import java.util.ArrayList;


public class SmithMcGlincyQuickSort {

    private int s;
    private int t;
    private int V;
    private int[][] G;  // Graph
    private Tuple<Integer, Integer>[][] R;  //Reverse Graph

    // TODO: 4/4/17 Make Generic
    private int[] cost;
    private int[] requires;

    private ArrayDeque<Integer> fifo = new ArrayDeque<Integer>();
    private Tuple<Integer, Integer>[] edgeSorted;
    private int maxCountSortValue;

    public SmithMcGlincyQuickSort(int[][] g, int s, int t) {
        this.s = s;
        this.t = t;
        this.G = g;
        this.V = g.length;
        this.maxCountSortValue = maxCountSortValue;
        R = new Tuple[V][V];


        // TODO: 4/4/17 make generic
        cost = new int[V];
        requires = new int[V];
        edgeSorted = new Tuple[V];


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
                quickSort(R[current], 0, V-1);
                ArrayDeque<Tuple> edgeQueue = new ArrayDeque<Tuple>();
                boolean first = false;
                Tuple<Integer, Integer> f = new Tuple<>(0,0);


                // Go through each edge
                // Start at end of array and go forward until zero
                for (int i = V - 1; i >= 0; i--) {

                    // Add all edges with value to queue
                    if (R[current][i].getCapacity() > 0) {

                        // If connected to s.  Use that first
                        if (R[current][i].getEdge() == s) {

                            first = true;
                            f = R[current][i];




                            // If next node is one away from s
                        }else if (G[s][R[current][i].getEdge()] > 0) {
                            edgeQueue.addFirst(R[current][i]);

                        }
                        // Other wise add edge to back
                        else
                            edgeQueue.addLast(R[current][i]);
                    }

                }
                // Put S First
                if (first)
                    edgeQueue.addFirst(f);


                while (!edgeQueue.isEmpty()) {

                    Tuple<Integer, Integer> edge = edgeQueue.pop();
//                    System.out.println("Edge: " + edge);


                    if (edge.getEdge() != current) {

                        //process edge
                        int adjCost = cost[edge.getEdge()] - requires[edge.getEdge()];   //Cost of edge - require edge.  Most times requires will be 0
                        int value = Math.min(adjCost, requires[current]);
                        value = Math.min(value, edge.getCapacity());
//                        System.out.println("Current: " + current + " Edge: " + edge + " cost: " + cost[edge] + " Adj Cost: " + adjCost);
//                        System.out.println("Current: " + current + " Edge: " + edge + " requires: " + requires[current]);
//                        System.out.println("Current: " + current + " Edge: " + edge + " R: " + R[current][edge]);
//                        System.out.println("Choose: " + value);
//                        System.out.println();

                        if (value > 0){
                            fifo.addLast(edge.getEdge());
                            requires[current] -= value;
                            requires[edge.getEdge()] += value;
                            edge.setCapacity(edge.getCapacity() - value);
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
                R[j][i] = new Tuple<>(G[i][j], i);
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
                total += R[i][j].getCapacity();
            }
            cost[i] = Math.min(total, cost[i]);
            if(i == t){
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
    private void quickSort(Tuple<Integer, Integer>[] array, int low, int high) {
        if(low < high){
            int p = partition(array, low, high);
            quickSort(array, low, p -1);
            quickSort(array, p +1, high);
        }
    }
    private int partition(Tuple<Integer, Integer>[] array, int low, int high){
        int p = array[high].getCapacity();
        int i = low -1;
        for(int j = low; j <= high -1; j++){
            if (array[j].getCapacity() <= p){
                i++;
                Tuple<Integer, Integer> temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        i++;
        Tuple<Integer, Integer> temp = array[i];
        array[i] = array[high];
        array[high] = temp;
        return i++;
    }

}
