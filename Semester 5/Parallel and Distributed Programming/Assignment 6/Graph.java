import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Graph {
    private List<Integer> nodes;
    private List<List<Integer>> edges;

    public Graph(int noNodes) {
        this.nodes = new ArrayList<>();
        for (int i = 0; i < noNodes; i++) {
            this.nodes.add(i);
        }
        generateEdges();
    }

    public Graph(List<Integer> nodes, List<List<Integer>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    private void generateEdges() {
        edges = new ArrayList<>();
        for (var node: nodes) {
            edges.add(new ArrayList<>());
        }

        Random random = new Random();
        var size = Math.pow(nodes.size(), 2);
        for (int i = 0; i < size / 2; i++) {
            var node1 = random.nextInt(nodes.size());
            var node2 = random.nextInt(nodes.size());
            addEdge(node1, node2);
        }
    }

    private void addEdge(int node1, int node2) {
        if (node1 == node2) {
            return;
        }
        if (edges.get(node1).contains(node2)) {
            return;
        }
        edges.get(node1).add(node2);
    }

    public List<Integer> getEdgesForNode(int node) {
        return edges.get(node);
    }

    public int size() {
        return nodes.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph with ").append(nodes.size()).append(" nodes:\n");

        for (int i = 0; i < nodes.size(); i++) {
            sb.append("Node ").append(i).append(" has edges to: ");
            List<Integer> neighbors = edges.get(i);

            if (neighbors.isEmpty()) {
                sb.append("No outgoing edges.");
            } else {
                sb.append(neighbors.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}