package Threads;

import static Threads.GraphGenerator.generateRandomGraph;

public class Main {
    public static void main(String[] args) {
//        int size = 10000;
//        Graph g = new Graph(size);
//        g.addEdge(0, 1);
//        g.addEdge(0, 2);
//        g.addEdge(1, 2);
//        g.addEdge(1, 3);
//        g.addEdge(2, 3);
//        g.addEdge(3, 4);

        /*
               _____
             /       \
             0---1---2
               /   /
              /  /
             / /
            3---------4    */
//        long startTime = System.nanoTime();
//        g.colorGraph();
//        long endTime = System.nanoTime();
//
//        long duration = (endTime - startTime);
//        g.printColors();
//
//        System.out.println("it took: " + (double)duration/1000000000 + " seconds.");


        int numberOfNodes = 100000;
        int numberOfEdges = 900000;

        Graph graph = generateRandomGraph(numberOfNodes, numberOfEdges);
        long startTime1 = System.nanoTime();
        graph.colorGraph();
        long endTime1 = System.nanoTime();
        long duration = (endTime1 - startTime1);
        graph.printColors();

        System.out.println("it took: " + (double)duration/1000000000 + " seconds.");
    }
}

