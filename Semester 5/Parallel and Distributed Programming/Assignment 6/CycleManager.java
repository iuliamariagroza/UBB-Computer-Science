import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class CycleManager {
    public Graph graph;
    private final AtomicBoolean cycleFound = new AtomicBoolean(false);

    public CycleManager(Graph graph) {
        this.graph = graph;
    }

    public void beginSearch() {
        ArrayList<Integer> path = new ArrayList<>();

        try {
            path.add(0);
            search(0, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void search(int currentNode, ArrayList<Integer> path) throws Exception {
        if (cycleFound.get()) return;

        if (graph.getEdgesForNode(currentNode).contains(0) && path.size() == graph.size()) {
            System.out.println("Hamiltonian Cycle Found: " + path);
            cycleFound.set(true);
            return;
        }

        if (path.size() == graph.size()) {
            return;
        }

        for (int i = 0; i < graph.size(); i++) {
            if (cycleFound.get()) return;

            if (graph.getEdgesForNode(currentNode).contains(i) && !path.contains(i)) {
                path.add(i);
                graph.getEdgesForNode(currentNode).remove(Integer.valueOf(i));

                ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
                final int node = i;
                final Runnable task = () -> {
                    try {
                        search(node, path);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                };
                executor.execute(task);
                executor.shutdown();
                executor.awaitTermination(50, TimeUnit.SECONDS);

                graph.getEdgesForNode(currentNode).add(i);
//                path.remove(path.size() - 1);
                path.removeLast();
            }
        }
    }
}
