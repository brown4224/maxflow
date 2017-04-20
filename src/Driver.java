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
        int cycles = 10;   // Set your number of cycles
        final int countSortValue = 250;  // Count sort's max number.   Recommend switching to a Heap or quicksort


        // Graphs

        // Graphs 0
        s = 0;
        t = 6;
        st.add(new Integer[] {s, t});
        int g_0[][] =new int[][] {
                {0, 16, 130, 0, 0, 0,30},
                {0, 0, 0, 120, 0, 0,0},
                {0, 40, 0, 0, 140, 0,0},
                {0, 0, 90, 0, 0, 20,10},
                {0, 0, 0, 70, 0, 40,0},
                {0, 0, 0, 0, 0, 0,150},
                {0, 0, 0, 0, 0, 0,0}
        };

        //  Graph 1
        s = 0;
        t = 5;
        st.add(new Integer[] {s, t});
        int g_1[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        //  Graph 2
        s = 0;
        t = 5;
        st.add(new Integer[] {s, t});
        int g_2[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };


        // Graph 3
        s = 0;
        t = 5;
        st.add(new Integer[] {s, t});
        int g_3[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        //  Graph 4
        s = 0;
        t = 5;
        st.add(new Integer[] {s, t});
        int g_4[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };


        //  Load Graphs
        int g[][][] = new int[][][] {
                 g_0, g_1, g_2, g_3, g_4
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

        // Time Results
        // Each Graph's Average
        double[] resultFordFulkerson;
        double[] resultSmithMcGlincySorted;
        double[] resultSmithMcGlincyQuicksort;

        // Time Results
        // Average of all data
        // Global Average
        double resultFordFulkersonAVG;
        double resultSmithMcGlincySortedAVG = 0;
        double resultSmithMcGlincyQuicksortAVG = 0;

        // Results
        // Each Graph's Max Flow
        int[] ansFordFulkerson = new int[g.length];
        int[] ansSmithMcGlincySorted = new int[g.length];
        int[] ansSmithMcGlincyQuicksort = new int[g.length];


        // Initialize
        // Set arrays to zero
        r_1 = flush(r_1, count);
        r_2 = flush(r_2, count);
        r_3 = flush(r_3, count);


        //  Run the graphs
        for(int i=0; i< count; i++) {
            //System.out.println(st.get(0)[1]);


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
        }

        //  Make Calculations
        resultFordFulkersonAVG = calTime(r_1, count);
        resultFordFulkerson = calTimeEACH(r_1, count, g.length);
        resultSmithMcGlincySortedAVG = calTime(r_2, count);
        resultSmithMcGlincySorted = calTimeEACH(r_2, count, g.length);
        resultSmithMcGlincyQuicksortAVG = calTime(r_3, count);
        resultSmithMcGlincyQuicksort = calTimeEACH(r_3, count, g.length);


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
        ////////////////////     Global AVG    ///////////////////////////////
        /////////////////////////////////////////////////////////////////////
         */
        String fast = "";
        if(resultFordFulkersonAVG < resultSmithMcGlincySortedAVG && resultFordFulkersonAVG < resultSmithMcGlincyQuicksortAVG)
            fast = "Fort Fulderson";
        else if(resultSmithMcGlincySortedAVG < resultFordFulkersonAVG && resultSmithMcGlincySortedAVG <resultSmithMcGlincyQuicksortAVG)
            fast = "Smith McGlincy Sorted";
        else
            fast = "Smith McGlincy Quicksort";

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
