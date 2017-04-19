import java.util.ArrayDeque;


public class SmithMcGlincyUnsorted {

    private int s;
    private int t;
    private int V;
    private int[][] G;  // Graph
    private int[][] R;  //Reverse Graph

    // TODO: 4/4/17 Make Generic
    private int[] cost;
    private int[] requires;


    private boolean[] visited;
    private boolean[] flag;
    private ArrayDeque<Integer> fifo = new ArrayDeque<Integer>();
    private int[] edgeSorted;

    // Save State
    private int [] saveState;
    private boolean next;

    public SmithMcGlincyUnsorted(int[][] g, int s, int t) {
        this.s = s;
        this.t = t;
        this.G = g;
        this.V = g.length;
        R = new int[V][V];


        // TODO: 4/4/17 make generic
        cost = new int[V];
        requires = new int[V];
        visited = new boolean[V];
        edgeSorted = new int[V];
        flag = new boolean[V];
        saveState = new int[V];
        next = false;

    }

    public int maxFlow() {
        init();
        fifo.addFirst(t);
        visited[t] = true;
        while (!fifo.isEmpty()) {
            int current = fifo.pop();
            next = false;
            if(current != s){
                System.out.println();
                System.out.println("Processing Node: " + current);
                System.out.print("Requires");
                for (int j=0; j< requires.length; j++){
                    System.out.print(requires[j] + " ");
                }
                System.out.println();


                // Go through each edge
                for (int edge = 0; edge < V && !next; edge++) {

                    // Chick if edge is being re-processed
                    if(saveState[current] > -1 && flag[current]){
                        // Load Node for re-processing
                        System.out.println("Re-Processing: " + current);
                        edge = saveState[current];
                        edge++;

                        // Update Markers
                        flag[current] = false;
                        saveState[current] = -1;
                    }


                    if (R[current][edge] > 0 && edge != current ) {


                        //process edge
                        int adjCost = cost[edge] - requires[edge];   //Cost of edge - require edge.  Most times requires will be 0
                        int value = Math.min(adjCost, requires[current]);
                        value = Math.min(value, R[current][edge]);


                            System.out.println("Current: " + current + " Edge: " + edge + " cost: " + cost[edge]);
                            System.out.println("Current: " + current + " Edge: " + edge + " requires: " + requires[current]);
                            System.out.println("Current: " + current + " Edge: " + edge + " R: " + R[current][edge]);
                            System.out.println("Choose: " + value);
                            System.out.println();
                            requires[current] -= value;
                            requires[edge] += value;


                            //If node has been depleted remove backward edges
                            if (requires[current] <= 0) {
                                flag[current] = true;
                                saveState[current] = edge;
                                next = true;

                                System.out.println("Saving:  " + saveState[current] + " Edge: " + edge);

                                // Check if Edge Needs to be re-processed
                                if(flag[edge] && fifo.size() > 1){
                                    System.out.println("Triggered!!!!!!!!!!!!!!");
                                    System.out.println("Saving State: " + edge);
                                    fifo.addLast(edge);
                                }
                            }

                            // TODO: 4/4/17 update if requires hits zero

                            // Add to queue if not visited
                            if (visited[edge] == false) {
                                fifo.addLast(edge);
                                visited[edge] = true;
                            } else if (flag[edge] && !flag[current]) {

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
            saveState[i]= -1;
            for (int j = 0; j < V; j++) {
                total += G[i][j];
                R[j][i] = G[i][j];
            }
            cost[i] = total;
            System.out.println();
        }


        // Scan the outgoing edges
        // Choose min(input vs output)
        for (int i = 1; i < V; i++) {     // Skip first row
            // TODO: 4/4/17 make "total" generics
            int total = 0;
            for (int j = 0; j < V; j++) {
                System.out.print(R[i][j] + " ");
                total += R[i][j];
            }
            cost[i] = Math.min(total, cost[i]);
            if(i == t){
                requires[t] = total;
                System.out.println();
                System.out.println("T Required: " + requires[t]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


}
