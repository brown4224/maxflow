import java.util.ArrayDeque;


public class SmithMcGlincyQuickSort {

    private int s;
    private int t;
    private int V;
    private int[][] G;  // Graph
    private Tuple<Integer, Integer>[][] R;  //Reverse Graph

    // TODO: 4/4/17 Make Generic
    private int[] cost;
    private int[] requires;


    private boolean[] visited;
    private boolean[] flag;
    private ArrayDeque<Integer> fifo = new ArrayDeque<Integer>();
    private int[] edgeSorted;
    private int maxCountSortValue;


    public SmithMcGlincyQuickSort (int[][] g, int s, int t) {
        this.s = s;
        this.t = t;
        this.G = g;
        this.V = g.length;
        this.maxCountSortValue = maxCountSortValue;
        R = new Tuple[V][V];



        // TODO: 4/4/17 make generic
        cost = new int[V];
        requires = new int[V];
        visited = new boolean[V];
        edgeSorted = new int[V];
        flag = new boolean[V];

    }

    public int maxFlow() {
        init();
        fifo.addFirst(t);
        visited[t] = true;
        while (!fifo.isEmpty()) {
            int current = fifo.pop();
            if(current != s){
//                System.out.println();
//                System.out.println("Processing Node: " + current);
//                System.out.print("Requires");
//                for (int j=0; j< requires.length; j++){
//                    System.out.print(requires[j] + " ");
//                }
//                System.out.println();

                // Sort the edges
                quickSort(R[current], 0, V- 1);

//                System.out.println("Quicksort array");
//                for(int i= 0; i< V; i++){
//                    System.out.println("Capacity " + R[current][i].getCapacity() + " Edge: " + R[current][i].getEdge());
//                }
//                System.out.println();


                // Go through each edge
                // Start at end of array and go forward until zero
                for (int i = V -1 ; i >=0; i--) {
                    int edge = R[current][i].getEdge();
//                    System.out.println("Edge: " + edge);

                    if (R[current][i].getCapacity()  == 0) {
                        break;  // no more edges
                    }


                    if (edge != current && !flag[edge]) {
                        //process edge
                        int adjCost = cost[edge] - requires[edge];   //Cost of edge - require edge.  Most times requires will be 0
                        int value = Math.min(adjCost, requires[current]);
                        value = Math.min(value, R[current][i].getCapacity());
//                        System.out.println("Current: " + current + " Edge: " + edge + " cost: " + cost[edge]);
//                        System.out.println("Current: " + current + " Edge: " + edge + " requires: " + requires[current]);
//                        System.out.println("Current: " + current + " Edge: " + edge + " R: " + R[current][i].getCapacity());
//                        System.out.println("Choose: " + value);
//                        System.out.println();
                        requires[current] -= value;
                        requires[edge] += value;

                        //If node has been depleted remove backward edges
                        if(requires[current] == 0)
                            flag[current] = true;

                        // TODO: 4/4/17 update if requires hits zero

                        // Add to queue if not visited
                        if (visited[edge] == false){
                            fifo.addLast(edge);
                            visited[edge] =true;
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
            visited[i] = false;
            flag[i] = false;
            for (int j = 0; j < V; j++) {
                total += G[i][j];
                R[j][i] = new Tuple<>(G[i][j], i);
            }
            cost[i] = total;
//            System.out.println();
        }


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
    }


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

//    /**
//     *
//     * @param array
//     * @return  A sorted array based on the index values.  Does not return sorted array
//     * Think POINTERS
//     */
//    private int[] countSort(int[] array) {
//
//        int maxValue = this.maxCountSortValue;
//        int length = array.length;
//        int[] count = new int[maxValue];
//        int[] ans = new int[length];
//        for (int i = 0; i < maxValue; i++) {
//            count[i] = 0;
//        }
//        for (int i = 0; i < length; i++) {
//            ++count[array[i]];
//        }
//        for (int i = 1; i < maxValue; i++) {
//            count[i] += count[i - 1];
//        }
//        for (int i = 0; i < length; i++) {
//            ans[count[array[i]] - 1] = i;
//            --count[array[i]];
//        }
//
////        System.out.print("Sorted Index: ");
////        for (int i = 0; i< length; i++){
////            System.out.print(ans[i] + " ");
////        }
////        System.out.println();
//
//        return ans;
//    }

}
