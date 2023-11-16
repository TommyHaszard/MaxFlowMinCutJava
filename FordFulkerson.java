
import java.util.*;

public class FordFulkerson implements SearchStrategy {

  @Override
  public Graph searchAlgo(Graph graph) {
    findAllPaths(graph, graph.getSource(), graph.getSink());
    return graph;
  }

  public static ArrayList<LinkedList<Edge>> findAllPaths(Graph graph, Node start, Node end) {
    ArrayList<LinkedList<Edge>> allPaths = new ArrayList<>();
    LinkedList<Edge> currentPath = new LinkedList<>();
    HashSet<Node> visited = new HashSet<>();
    dfs(graph, start, end, currentPath, allPaths, visited);
    return allPaths;
  }

  private static void dfs(
          Graph graph,
          Node current,
          Node end,
          LinkedList<Edge> currentPath,
          ArrayList<LinkedList<Edge>> allPaths,
          HashSet<Node> visited
  ) {
    visited.add(current);

    if (current == end) {
      allPaths.add(new LinkedList<>(currentPath));
    } else {
      for (Edge edge : current.getEdges()) {
        Node neighbor = edge.returnNodeV();
        if (!visited.contains(neighbor)) {
          currentPath.addLast(edge);
          dfs(graph, neighbor, end, currentPath, allPaths, visited);

        }
      }
    }
    visited.remove(current);
    if(!currentPath.isEmpty()) {
      currentPath.removeLast();
    }
  }

  @Override
  public void printMinCuts(Graph graph) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'printMinCuts'");
  }

}

//  ArrayList<LinkedList<Edge>> paths = new ArrayList<>();
//  int maxFlow = 0;
//  Node source = graph.getSource();
//  Node sink = graph.getSink();
//
//  Stack<Node> stack = new Stack<>();
//  Map<String, Boolean> visited = new HashMap<>();
//  Map<String, Node> edgeTo = new HashMap<>();
//
//    stack.push(source);
//            String pathStr = "source - ";
//            LinkedList<Edge> path = new LinkedList<>();
//        while(!stack.empty()) {
//        Node currentNode = stack.pop();
//
//        visited.put(currentNode.getName(), true);
//        for(Edge edge : currentNode.getEdges()) {
//        Node neighbour = edge.returnNodeV();
//
//        if (neighbour.name.equals(sink.name)) {
//        System.out.println(pathStr.concat("sink"));
////          path = "source - ";
//        break;
//        }
//        if(!visited.containsKey(neighbour.getName()) && edge.getRemainingCapacity() > 0) {
//        pathStr = pathStr + neighbour.getName() + " - ";
//        stack.push(neighbour);
//        edgeTo.put(neighbour.getName(), currentNode);
//        }
//
//        }
//        }
//
//
//        graph.setMaxFlow(maxFlow);
