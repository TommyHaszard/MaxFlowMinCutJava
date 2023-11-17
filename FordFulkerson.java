import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class FordFulkerson implements SearchStrategy {
  @Override
  public Graph searchAlgo(Graph graph) {
    System.out.println("\nFord-Fulkerson:");
    System.out.println("----------------------------------------------------\n");
    LinkedList<Edge> currentPath = new LinkedList<>();
    HashSet<Node> visited = new HashSet<>();
    Node source = graph.getSource();
    Node sink = graph.getSink();
    int maxFlow = 0;
    while (dfs(graph, source, sink, currentPath, visited)) {
      int flow = Integer.MAX_VALUE;
      Iterator<Edge> iterator = currentPath.descendingIterator();
      while (iterator.hasNext()) {
        Edge currEdge = iterator.next();
        flow = Math.min(flow, currEdge.getRemainingCapacity());
      }
      iterator = currentPath.descendingIterator();
      while (iterator.hasNext()) {
        Edge currEdge = iterator.next();
        currEdge.setCurrentFlow(flow);
        currEdge.printEdgeInformation();

      }
      currentPath.clear();
      visited.clear();
      maxFlow += flow;

      System.out.println("\nMax Flow currently: " + maxFlow);
    }
    graph.setMaxFlow(maxFlow);
    return graph;

  }

  private static boolean dfs(
      Graph graph,
      Node current,
      Node sink,
      LinkedList<Edge> currentPath,
      // ArrayList<LinkedList<Edge>> allPaths,
      HashSet<Node> visited) {
    if (current.equals(sink)) {
      return true;
    }
    visited.add(current);
    for (Edge edge : current.getEdges()) {
      Node neighbor = edge.returnNodeV();
      if (!visited.contains(neighbor) && edge.getRemainingCapacity() > 0) {
        currentPath.addLast(edge);
        if (dfs(graph, neighbor, sink, currentPath, visited)) {
          return true;
        }
      }
    }

    if (!currentPath.isEmpty()) {
      currentPath.removeLast();
    }
    return false;
  }

  @Override
  public void printMinCuts(Graph graph) {
    HashSet<Node> sourceNodes = new HashSet<Node>();
    Node source = graph.getSource();
    Stack<Node> stack = new Stack<Node>();

    // we wont be able to reach Sink from Source so we can't reuse the recursive
    // method, iterative approach with Stack applied to find all sourceNodes
    stack.add(source);
    sourceNodes.add(source);
    while (!stack.isEmpty()) {
      Node current = stack.pop();
      ArrayList<Edge> currEdges = current.getEdges();
      for (Edge currEdge : currEdges) {
        Node v = currEdge.returnNodeV();
        if (currEdge.getRemainingCapacity() > 0 && !sourceNodes.contains(v)) {
          sourceNodes.add(v);
          stack.push(v);
        }
      }
    }

    System.out.println("MinCut Results:\n");

    // now we have all the nodes in the source cutset, lets make a sink cutset with
    // the remaining nodes
    HashSet<Node> sinkNodes = new HashSet<Node>();
    for (Node node : graph.returnVertices()) {
      if (!sourceNodes.contains(node)) {
        sinkNodes.add(node);
      }
    }

    // go through each edge and check if sourceNodes contains the U node in that
    // edge and the sinkNodes contains the V node in that edge. This will be the cut
    // edge.
    int maxFlow = 0;
    for (Edge edge : graph.returnEdges()) {
      if (sourceNodes.contains(edge.returnNodeU()) &&
          sinkNodes.contains(edge.returnNodeV())) {
        System.out.println(edge.returnNodeU().getName() + " - " +
            edge.returnNodeV().getName() + " with capacity: "
            + edge.getMaxCapacity());

        maxFlow += edge.getMaxCapacity();
      }
    }

    System.out.println("\nSum of Cut Edge Capacities = " + maxFlow);
    System.out.println("Max Flow of the Graph = " + graph.returnMaxFlow() + "\n");
  }
}