import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
        Graph graph = new Graph(1000);
        System.out.println(graph);

        // graph 1 - Hamiltonian cycle: 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
        Graph graphWithHamiltonianCycle = new Graph(
                new ArrayList<>(List.of(0, 1, 2, 3, 4,5,6,7,8,9)),
                new ArrayList<>(
                        List.of(
                                new ArrayList<>(List.of(1, 9)),
                                new ArrayList<>(List.of(2, 0)),
                                new ArrayList<>(List.of(3, 1)),
                                new ArrayList<>(List.of(4, 2)),
                                new ArrayList<>(List.of(5, 3)),
                                new ArrayList<>(List.of(6, 4)),
                                new ArrayList<>(List.of(7, 5)),
                                new ArrayList<>(List.of(8, 6)),
                                new ArrayList<>(List.of(9, 7)),
                                new ArrayList<>(List.of(0, 8))
                        )
                )
        );
        System.out.println(graphWithHamiltonianCycle);

        // graph 2 - no Hamiltonian cycle
        Graph graphWithoutHamiltonianCycle = new Graph(
                new ArrayList<>(List.of(0, 1, 2, 3, 4,5,6,7,8,9)),
                new ArrayList<>(
                        List.of(
                                new ArrayList<>(List.of(1, 9)),
                                new ArrayList<>(List.of(2, 0)),
                                new ArrayList<>(List.of(3, 1)),
                                new ArrayList<>(List.of(4, 2)),
                                new ArrayList<>(List.of(5, 3)),
                                new ArrayList<>(List.of(6, 4)),
                                new ArrayList<>(List.of(7)),
                                new ArrayList<>(List.of(8, 6)),
                                new ArrayList<>(List.of(9, 7)),
                                new ArrayList<>(List.of(1))
                        )
                )
        );
        System.out.println(graphWithoutHamiltonianCycle);


        long startTime1 = System.nanoTime();
        CycleFinder findHamiltonianCycle = new CycleFinder(graph);
        findHamiltonianCycle.beginSearch(executor);
        long duration1  = System.nanoTime() - startTime1;
        System.out.println("Graph size: " + graph.size());
        System.out.println("Time:" + (duration1/1_000_000_000.0) + " seconds\n");

        long startTime2 = System.nanoTime();
        findHamiltonianCycle = new CycleFinder(graphWithHamiltonianCycle);
        findHamiltonianCycle.beginSearch(executor);
        long duration2  = System.nanoTime() - startTime2;
        System.out.println("Graph with Hamiltonian cycle size: " + graphWithHamiltonianCycle.size());
        System.out.println("Time:" + (duration2/1_000_000_000.0) + " seconds\n");

        long startTime3 = System.nanoTime();
        findHamiltonianCycle = new CycleFinder(graphWithoutHamiltonianCycle);
        findHamiltonianCycle.beginSearch(executor);
        long duration3  = System.nanoTime() - startTime3;
        System.out.println("Graph without Hamiltonian cycle size: " + graphWithoutHamiltonianCycle.size());
        System.out.println("Time:" + (duration3/1_000_000_000.0) + " seconds");
    }
}