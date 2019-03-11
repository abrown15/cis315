import java.util.*;

public class Assignment_2 {

    public static void main(String[] args) {
        //First, we need to init our scanner...
        Scanner jeff = new Scanner(System.in);

        int numNodes = jeff.nextInt();          //Retrieve number of nodes
        int numEdges = jeff.nextInt();          //Retrieve number of edges

        //Initialize Weight matrix/
        int[][] W = new int[numNodes + 1][numNodes + 1];

        //Initialize LP[] (longest path array)
        double[] LP = new double[numNodes + 1];

        //Initialize pathsTo[u] (records number of paths to node u
        int pathsTo[] = new int[numNodes + 1];

        //Populate W with data
        for (int i = 0; i < numEdges; i++) {
            //u, v are nodes and w is the weight of the edge between them
            int u = jeff.nextInt();
            int v = jeff.nextInt();
            int w = jeff.nextInt();

            //W[u][v] = w <--> Edge from u to v has weight w
            W[u][v] = w;


        }

        jeff.close();

        //Set LP[1] to 0 and the rest to -inf(We've yet to find longest path to any node)
        LP[1] = 0;
        pathsTo[1] = 1;
        for (int i = 2; i <= numNodes; i++) {
            LP[i] = Double.NEGATIVE_INFINITY;
        }


        //Time Complexity of O(numNodes^2)
        for (int i = 1; i <= numNodes - 1; i++) {
            for (int j = i + 1; j <= numNodes; j++) {

                //If there is a path from i to j
                if (W[i][j] != 0) {

                    if(LP[i] + W[i][j] > LP[j]){
                        LP[j] = LP[i] + W[i][j];
                        pathsTo[j] = pathsTo[i];

                    } else if(LP[i] + W[i][j] == LP[j]){
                        pathsTo[j] = pathsTo[j] + pathsTo[i];
                    }
                }
            }
        }


        System.out.println("Longest path: " + LP[numNodes]);
        System.out.println("Number of longest paths: " + pathsTo[numNodes]);

    }
}
