import java.util.ArrayList;

/**
 * Created by sean on 4/4/17.
 */
public class Driver {

    public static void main(String[] args) {
        // Initialize
        int s = 0;
        int t = 0;
        ArrayList<Integer[]> st = new ArrayList<>();

        /*

        ///////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////   Start Input HERE!!!   /////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////

        Note: use values less then 250
        Graphs can be as big as your want but edge capacity needs to be less then 250

        Set Cycles: Number of Iterations per Graph
         */


        // Values
        int cycles = 5;   // Set your number of cycles
        final int countSortValue = 250;  // Count sort's max number.   Recommend switching to a Heap or quicksort


        // Graphs
        // Graphs 0
        // Graph From Class
        s = 0;
        t = 5;
        st.add(new Integer[] {s, t});
        int g_0[][] =new int[][] {
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 0, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        // Graphs 1
        // Graph From Class
        s = 0;
        t = 5;
        st.add(new Integer[] {s, t});
        int g_1[][] =new int[][] {
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},  // Different then g_0
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        //  Graph 2
        //Ryan's Control
        // Max Flow 35
        //
        s = 0;
        t = 5;
        st.add(new Integer[] {s, t});
        int g_2[][] =new int[][] {
                {0, 20, 30, 0, 0, 0},  //This is S
                {0, 0, 0, 10, 5, 0},
                {0, 0, 0, 0, 25, 0},
                {0, 0, 0, 0, 0, 5},
                {0, 0, 0, 0, 0, 30},
                {0, 0, 0, 0, 0, 0}  //This is T

        };

        //  Graph 3
        // Ryan's first test
        // Max Flow 100
        s = 0;
        t = 6;
        st.add(new Integer[] {s, t});
        int g_3[][] =new int[][] {
                {0, 16, 130, 0, 0, 0,30},
                {0, 0, 0, 120, 0, 0,0},
                {0, 40, 0, 0, 140, 0,0},
                {0, 0, 90, 0, 0, 20,10},
                {0, 0, 0, 70, 0, 40,0},
                {0, 0, 0, 0, 0, 0,150},
                {0, 0, 0, 0, 0, 0,0}
        };


        // Graph 4
        // Loop Master
        // Max Flow: 25
        s = 0;
        t = 6;
        st.add(new Integer[] {s, t});
        int g_4[][] =new int[][] {
                {0, 12, 15, 0, 0, 0, 0},  //This is S
                {0, 0, 0, 0, 21, 0, 0},
                {0, 15, 0, 0, 0, 11, 0},
                {0, 14, 12, 0, 0, 0, 0},
                {0, 0, 0, 11, 0, 12, 11},
                {0, 0, 0, 9, 0, 0, 14},
                {0, 0, 0, 0, 0, 0, 0}  //This is T

        };

        //  Graph 5
        // LB
        // Small to Big Capacity
        // Max Flow 11
        s = 0;
        t = 6;
        st.add(new Integer[] {s, t});
        int g_5[][] =new int[][] {
                {0, 6, 5, 0, 0, 0, 0},  //This is S
                {0, 0, 0, 56, 35, 0, 0},
                {0, 15, 0, 57, 0, 62, 0},
                {0, 0, 0, 0, 45, 55, 65},
                {0, 0, 0, 0, 0, 0, 75},
                {0, 0, 0, 0, 0, 0, 55},
                {0, 0, 0, 0, 0, 0, 0}  //This is T
        };

        //  Graph 6
        // G 19
        //32 Edges
        // Max Flow: 41
        s = 0;
        t = 18;
        st.add(new Integer[] {s, t});
        int g_6[][] =new int[][] {
                {0, 75, 20, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //This is S
                {0, 0, 0, 25, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 12, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 15, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 20, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 75, 0, 0, 0, 76, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 56, 87, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 80, 0, 31, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 55, 0, 63, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 42, 43, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 13, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 83},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 42},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  //This is T
        };


        //  Graph 7
        // G 19 S
        // Max Flow 60    Ryan Check value
        s = 0;
        t = 18;
        st.add(new Integer[] {s, t});
        int g_7[][] =new int[][] {
                {0, 30, 50, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //This is S
                {0, 0, 0, 20, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 10, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 20, 20, 20, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 10, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 10, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  //This is T
        };

        //  Load Graphs
        int g[][][] = new int[][][] {
                 g_0, g_1, g_2, g_3, g_4, g_5, g_6, g_7
//                g_0
        };

        /*
        ///////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////   END Input HERE!!!   /////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////
        */

        // Data
        int count = cycles * g.length;
        long start = 0;


        //  Time Results
        //  Raw Data
        long[] r_1 = new long[count];
        long[] r_2 = new long[count];
        long[] r_3 = new long[count];
        long[] r_4 = new long[count];


        // Time Results
        // Each Graph's Average
        double[] resultFordFulkerson;
        double[] resultSmithMcGlincySorted;
        double[] resultSmithMcGlincyQuicksort;
        double[] resultSmithMcGlincyUnsorted;


        // Time Results
        // Average of all data
        // Global Average
        double resultFordFulkersonAVG;
        double resultSmithMcGlincySortedAVG = 0;
        double resultSmithMcGlincyQuicksortAVG = 0;
        double resultSmithMcGlincyUnsortedAVG = 0;

        // Results
        // Each Graph's Max Flow
        int[] ansFordFulkerson = new int[g.length];
        int[] ansSmithMcGlincySorted = new int[g.length];
        int[] ansSmithMcGlincyQuicksort = new int[g.length];
        int[] ansSmithMcGlincyUnsorted = new int[g.length];


        // Initialize
        // Set arrays to zero
        r_1 = flush(r_1, count);
        r_2 = flush(r_2, count);
        r_3 = flush(r_3, count);
        r_4 = flush(r_3, count);


        //  Run the graphs
        for(int i=0; i< count; i++) {


            int id = i% g.length; // Graph ID
            int[][] graph = g[id];

            start = System.nanoTime();
            MaxFlow n = new MaxFlow();

            ansFordFulkerson[id] = n.fordFulkerson(graph, st.get(id)[0], st.get(id)[1]);
            r_1[i] = System.nanoTime() - start;

            start = System.nanoTime();
            SmithMcGlincy m = new SmithMcGlincy(graph, st.get(id)[0], st.get(id)[1], countSortValue);
            ansSmithMcGlincySorted[id] = m.maxFlow();
            r_2[i] = System.nanoTime() - start;

            start = System.nanoTime();
            SmithMcGlincyQuickSort l = new SmithMcGlincyQuickSort(graph, st.get(id)[0], st.get(id)[1]);
            ansSmithMcGlincyQuicksort[id] = l.maxFlow();
            r_3[i] = System.nanoTime() - start;

            start = System.nanoTime();
            SmithMcGlincyUnsorted u = new SmithMcGlincyUnsorted(graph, st.get(id)[0], st.get(id)[1]);
            ansSmithMcGlincyUnsorted[id] = u.maxFlow();
            r_4[i] = System.nanoTime() - start;
        }

        //  Make Calculations
        resultFordFulkersonAVG = calTime(r_1, count);
        resultFordFulkerson = calTimeEACH(r_1, count, g.length);
        resultSmithMcGlincySortedAVG = calTime(r_2, count);
        resultSmithMcGlincySorted = calTimeEACH(r_2, count, g.length);
        resultSmithMcGlincyQuicksortAVG = calTime(r_3, count);
        resultSmithMcGlincyQuicksort = calTimeEACH(r_3, count, g.length);
        resultSmithMcGlincyUnsortedAVG= calTime(r_4, count);
        resultSmithMcGlincyUnsorted = calTimeEACH(r_4, count, g.length);


        /*
        //////////////////////////////////////////////////////////////////////
        /////////////////    FORD FULKERSON    ///////////////////////////////
        /////////////////////////////////////////////////////////////////////
         */
        System.out.println("Ford Fulkerson: "  + " Time AVG: " + resultFordFulkersonAVG);
        System.out.print("Calculated MaxFlow ");
        for(int j = 0; j< resultFordFulkerson.length; j++){
            System.out.print("Graph " + j + " Results: " + ansFordFulkerson[j % count] + "\t");
        }
        System.out.println("");
        System.out.print("Each Graph's AVG Time: ");
        for(int j = 0; j< resultFordFulkerson.length; j++){
            System.out.print("Graph " + j + ": " + resultFordFulkerson[j] + "\t");
        }
        System.out.println("\n");

        /*
        //////////////////////////////////////////////////////////////////////
        ////////////////////      Sorted       ///////////////////////////////
        /////////////////////////////////////////////////////////////////////
         */
        System.out.println("Smith McGlincy: "  + " Time AVG: " + resultSmithMcGlincySortedAVG);
        System.out.print("Calculated MaxFlow ");
        for(int j = 0; j< resultFordFulkerson.length; j++){
            System.out.print("Graph " + j + " Results: " + ansSmithMcGlincySorted[j % count] + "\t");
        }
        System.out.println("");
        System.out.print("Each Graph's AVG Time: ");
        for(int j = 0; j< resultSmithMcGlincySorted.length; j++){
            System.out.print("Graph " + j + ": " + resultSmithMcGlincySorted[j] + "\t");
        }
        System.out.println("\n");


        /*
        //////////////////////////////////////////////////////////////////////
        ////////////////////     Quick-Sorted    ///////////////////////////////
        /////////////////////////////////////////////////////////////////////
         */


        System.out.println("Smith McGlincy Quicksort: "+ " Time AVG: " + resultSmithMcGlincyQuicksortAVG);
        System.out.print("Calculated MaxFlow ");
        for(int j = 0; j< resultFordFulkerson.length; j++){
            System.out.print("Graph " + j + " Results: " + ansSmithMcGlincyQuicksort[j % count] + "\t");
        }
        System.out.println("\n");
        System.out.print("Each Graph's AVG Time: ");
        for(int j = 0; j< resultSmithMcGlincyQuicksort.length; j++){
            System.out.print("Graph " + j + ": " + resultSmithMcGlincyQuicksort[j] + "\t");
        }
        System.out.println("\n");

        /*
        //////////////////////////////////////////////////////////////////////
        ////////////////////     Unsorted-Sorted    ///////////////////////////////
        /////////////////////////////////////////////////////////////////////
         */


        System.out.println("Smith McGlincy Un-sorted: "+ " Time AVG: " + resultSmithMcGlincyUnsortedAVG);
        System.out.print("Calculated MaxFlow ");
        for(int j = 0; j< resultFordFulkerson.length; j++){
            System.out.print("Graph " + j + " Results: " + ansSmithMcGlincyUnsorted[j % count] + "\t");
        }
        System.out.println("\n");
        System.out.print("Each Graph's AVG Time: ");
        for(int j = 0; j< resultSmithMcGlincyUnsorted.length; j++){
            System.out.print("Graph " + j + ": " + resultSmithMcGlincyUnsorted[j] + "\t");
        }
        System.out.println("\n");



        /*
        //////////////////////////////////////////////////////////////////////
        ////////////////////     Global AVG    ///////////////////////////////
        /////////////////////////////////////////////////////////////////////
         */
        String fast = "";
        if(resultFordFulkersonAVG < resultSmithMcGlincySortedAVG && resultFordFulkersonAVG < resultSmithMcGlincyQuicksortAVG && resultFordFulkersonAVG < resultSmithMcGlincyUnsortedAVG)
            fast = "Fort Fulderson";
        else if(resultSmithMcGlincySortedAVG < resultFordFulkersonAVG && resultSmithMcGlincySortedAVG < resultSmithMcGlincyQuicksortAVG && resultSmithMcGlincySortedAVG < resultSmithMcGlincyUnsortedAVG)
            fast = "Smith McGlincy Sorted";
        else if(resultSmithMcGlincyQuicksortAVG < resultFordFulkersonAVG && resultSmithMcGlincyQuicksortAVG < resultSmithMcGlincySortedAVG && resultSmithMcGlincyQuicksortAVG < resultSmithMcGlincyUnsortedAVG)
            fast = "Smith McGlincy Quicksort";
        else
            fast = "Smith McGlincy Unsorted";

        System.out.println("The fastest algorithm is: " + fast);

    }

    private static long[] flush(long[] result, int count){
        for (int i= 0; i< count; i++){
            result[i] = 0;
        }
        return result;
    }
    private static double calTime(long[] result, int count){
        long total = 0;
        for (int i= 0; i< count; i++){
            total += result[i];
        }
        return total /( (double) count);
    }
    private static double[] calTimeEACH(long[] result, int count, int graphLength){
        double [] ans = new double[graphLength];
        for (int j = 0; j < graphLength; j++){
            long total = 0;
            for (int i= j; i< count; i += graphLength){
                total += result[i];
            }
            ans[j] = total /( (double) count / graphLength);
        }
        return ans;
    }
}
