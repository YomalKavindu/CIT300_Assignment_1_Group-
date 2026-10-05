import java.util.*;

public class CampusGraph {

    // Adjacency List representation: Location -> List of connected Locations
    private Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // 1. Add a campus location (Vertex)
    public boolean addLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            return false;
        }
        String formattedLocation = location.trim();
        if (adjacencyList.containsKey(formattedLocation)) {
            return false; // Location already exists
        }
        adjacencyList.put(formattedLocation, new ArrayList<>());
        return true;
    }

    // 2. Remove a campus location and all its associated connections
    public boolean removeLocation(String location) {
        if (!adjacencyList.containsKey(location)) {
            return false; // Location not found
        }

        // Remove the vertex from all other vertices' neighbor lists
        adjacencyList.remove(location);
        for (List<String> neighbors : adjacencyList.values()) {
            neighbors.remove(location);
        }
        return true;
    }

    // 3. Add a bidirectional connection/road between two campus locations (Edge)
    public boolean addConnection(String source, String destination) {
        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            return false; // One or both locations do not exist
        }

        List<String> sourceNeighbors = adjacencyList.get(source);
        List<String> destNeighbors = adjacencyList.get(destination);

        // Check for existing connection or self-loop
        if (source.equalsIgnoreCase(destination) || sourceNeighbors.contains(destination)) {
            return false;
        }

        sourceNeighbors.add(destination);
        destNeighbors.add(source); // Undirected graph connection
        return true;
    }

    // 4. Remove a connection/road between two campus locations
    public boolean removeConnection(String source, String destination) {
        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            return false;
        }

        List<String> sourceNeighbors = adjacencyList.get(source);
        List<String> destNeighbors = adjacencyList.get(destination);

        if (!sourceNeighbors.contains(destination)) {
            return false; // Connection does not exist
        }

        sourceNeighbors.remove(destination);
        destNeighbors.remove(source);
        return true;
    }

    // 5. Display all campus locations and their connections
    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations found in the graph network.");
            return;
        }

        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("                         CAMPUS ROUTE NETWORK (GRAPH)                        ");
        System.out.println("-----------------------------------------------------------------------------");
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.printf("%-20s --> %s\n", entry.getKey(), 
                    entry.getValue().isEmpty() ? "[No Direct Connections]" : entry.getValue().toString());
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    // 6. Breadth-First Search (BFS) Traversal
    public void bfsTraversal(String startLocation) {
        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Start location '" + startLocation + "' does not exist in the campus graph.");
            return;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startLocation);
        queue.add(startLocation);

        System.out.println("\n--- Campus Traversal (BFS starting from: " + startLocation + ") ---");
        List<String> traversalPath = new ArrayList<>();

        while (!queue.isEmpty()) {
            String current = queue.poll();
            traversalPath.add(current);

            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println(String.join(" -> ", traversalPath));
    }

    // 7. Depth-First Search (DFS) Traversal
    public void dfsTraversal(String startLocation) {
        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Start location '" + startLocation + "' does not exist in the campus graph.");
            return;
        }

        Set<String> visited = new HashSet<>();
        List<String> traversalPath = new ArrayList<>();

        dfsRecursive(startLocation, visited, traversalPath);

        System.out.println("\n--- Campus Traversal (DFS starting from: " + startLocation + ") ---");
        System.out.println(String.join(" -> ", traversalPath));
    }

    private void dfsRecursive(String current, Set<String> visited, List<String> traversalPath) {
        visited.add(current);
        traversalPath.add(current);

        for (String neighbor : adjacencyList.get(current)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited, traversalPath);
            }
        }
    }

    public boolean containsLocation(String location) {
        return adjacencyList.containsKey(location);
    }
}