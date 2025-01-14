package Threads;

import java.util.Random;

public class GraphGenerator {

    public static Graph generateRandomGraph(int numberOfNodes, int numberOfEdges) {
        if (numberOfEdges > numberOfNodes * (numberOfNodes - 1)) {
            throw new IllegalArgumentException("Too many edges for the given number of nodes.");
        }

        Graph graph = new Graph(numberOfNodes);
        Random random = new Random();

        int edgesAdded = 0;
        while (edgesAdded < numberOfEdges) {
            int src = random.nextInt(numberOfNodes);
            int dest = random.nextInt(numberOfNodes);

            if (src != dest && !graph.getAllNeighbours(src).contains(dest)) {
                graph.addEdge(src, dest);
                edgesAdded++;
            }
        }
        return graph;
    }
}
