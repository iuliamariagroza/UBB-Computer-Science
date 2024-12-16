import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class CycleFinder {
    public Graph graph;
    private final AtomicBoolean cycleFound = new AtomicBoolean(false);

    public CycleFinder(Graph graph) {
        this.graph = graph;
    }

    public void beginSearch(ThreadPoolExecutor executor) {
        ArrayList<Integer> path = new ArrayList<>();
        path.add(0);

        try {
            search(0, path, executor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void search(int currentNode, ArrayList<Integer> path, ThreadPoolExecutor executor) {
        if (graph.getEdgesForNode(currentNode).contains(0) && path.size() == graph.size()) {
            System.out.println("Hamiltonian Cycle Found: " + path);
//            cycleFound.set(true);
            return;
        }

        if (path.size() == graph.size()) {
            return;
        }

        for (int i = 0; i < graph.size(); i++) {
//            if (cycleFound.get()) return;

            if (graph.getEdgesForNode(currentNode).contains(i) && !path.contains(i)) {
                path.add(i);
                graph.getEdgesForNode(currentNode).remove(Integer.valueOf(i));

                final int node = i;
                final ArrayList<Integer> newPath = new ArrayList<>(path);
                executor.execute(() -> {
                    try {
                        search(node, newPath, executor);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

                graph.getEdgesForNode(currentNode).add(i);
                path.removeLast();
            }
        }
    }
}
