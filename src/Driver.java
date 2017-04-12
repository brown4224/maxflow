import com.sun.org.apache.xpath.internal.SourceTree;

import java.math.BigInteger;

/**
 * Created by sean on 4/4/17.
 */
public class Driver {

    public static void main(String[] args) {
        int g_1[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        int g_2[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        int g_3[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        int g_4[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        int g_5[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        int g[][][] = new int[][][] {
                g_1, g_2, g_3, g_4, g_5
        };




        int count = 10 * g.length;
        long start = 0;


        long[] r_1 = new long[count];
        long[] r_2 = new long[count];
        long[] r_3 = new long[count];

        double[] resultFordFulkerson;
        double[] resultSmithMcGlincySorted;
        double[] resultSmithMcGlincyUnsorted;

        double resultFordFulkersonAVG;
        double resultSmithMcGlincySortedAVG = 0;
        double resultSmithMcGlincyUnsortedAVG = 0;

        int ansFordFulkerson = 0;
        int ansSmithMcGlincySorted = 0;
        int ansSmithMcGlincyUnsorted = 0;


        r_1 = flush(r_1, count);
        r_2 = flush(r_2, count);
        r_3 = flush(r_3, count);

        for(int i=0; i< count; i++) {

            int[][] graph = g[i%5];

            start = System.nanoTime();
            MaxFlow n = new MaxFlow();
            ansFordFulkerson = n.fordFulkerson(graph, 0, 5);
            r_1[i] = System.nanoTime() - start;

            start = System.nanoTime();
            SmithMcGlincy m = new SmithMcGlincy(graph, 0, 5);
            ansSmithMcGlincySorted = m.maxFlow();
            r_2[i] = System.nanoTime() - start;

            start = System.nanoTime();
            SmithMcGlincyUnsorted l = new SmithMcGlincyUnsorted(graph, 0, 5);
            ansSmithMcGlincyUnsorted = l.maxFlow();
            r_3[i] = System.nanoTime() - start;
        }

        resultFordFulkersonAVG = calTime(r_1, count);
        resultFordFulkerson = calTimeEACH(r_1, count, g.length);
        resultSmithMcGlincySortedAVG = calTime(r_2, count);
        resultSmithMcGlincySorted = calTimeEACH(r_2, count, g.length);
        resultSmithMcGlincyUnsortedAVG = calTime(r_3, count);
        resultSmithMcGlincyUnsorted = calTimeEACH(r_3, count, g.length);

        System.out.println("Ford Fulkerson: Ans: " + ansFordFulkerson + " Time AVG: " + resultFordFulkersonAVG);
        System.out.print("Each Graph's AVG: ");
        for(int j = 0; j< resultFordFulkerson.length; j++){
            System.out.print(resultFordFulkerson[j] + "\t");
        }
        System.out.println("\n");


        System.out.println("Smith McGlincy: Ans: " + ansSmithMcGlincySorted + " Time AVG: " + resultSmithMcGlincySortedAVG);
        System.out.print("Each Graph's AVG: ");
        for(int j = 0; j< resultSmithMcGlincySorted.length; j++){
            System.out.print(resultSmithMcGlincySorted[j] + "\t");
        }
        System.out.println("\n");


        System.out.println("Smith McGlincy Unsorted: Ans: " + ansSmithMcGlincyUnsorted + " Time AVG: " + resultSmithMcGlincyUnsortedAVG);
        System.out.print("Each Graph's AVG: ");
        for(int j = 0; j< resultSmithMcGlincyUnsorted.length; j++){
            System.out.print(resultSmithMcGlincyUnsorted[j] + "\t");
        }
        System.out.println("\n");


        String fast = "";
        if(resultFordFulkersonAVG < resultSmithMcGlincySortedAVG && resultFordFulkersonAVG < resultSmithMcGlincyUnsortedAVG)
            fast = "Fort Fulderson";
        else if (resultSmithMcGlincySortedAVG < resultFordFulkersonAVG  && resultSmithMcGlincySortedAVG < resultSmithMcGlincyUnsortedAVG)
            fast = "Smith McGlincy Sorted";
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
