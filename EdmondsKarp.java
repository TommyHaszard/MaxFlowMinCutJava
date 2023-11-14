import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarp implements SearchStrategy {

  @Override
  public Graph searchAlgo(Graph graph) {
    int max_flow = 0;
    // set the source and sink
    Node sink = graph.getSink();
    Node source = graph.getSource();

    while (true) {
      // create the queue as a linkedList of nodes
      Queue<Node> queue = new LinkedList<Node>();
      // create a hashmap of the backwards edges for backwards traversal to augment
      // the path
      HashMap<String, Edge> backwardsEdges = new HashMap<>();

      // add the source to the queue
      queue.add(source);
      while (!queue.isEmpty()) {
        Node current = queue.poll();
        // get the edges for the current node
        ArrayList<Edge> currEdges = current.getEdges();

        // loop through the edges
        for (Edge currEdge : currEdges) {
          Node v = currEdge.returnNodeV();
          // check if the unused capacity for the current edge is greater then 0 and check
          // if the backwards edge list contains the node
          int unusedCap = currEdge.getRemainingCapacity();
          if (unusedCap > 0 && !backwardsEdges.containsKey(v.getName())) {
            backwardsEdges.put(v.getName(), currEdge);
            // we have found the sink no need to add it to the queue and exit as we found
            // the shortest path
            if (v.name.equals(sink.name)) {
              break;
            }
            // add the node to the queue
            queue.add(v);
          }
        }
      }
      // prev edge should contain sink, if not then it was not reached, return
      if (!backwardsEdges.containsKey(sink.name)) {
        break;
      }

      // start at current edge, go until edge is null and set next edge to be the
      // loop backwards through the backwards edge list starting at the sink, to find
      // the min value throughout the graph to get the min value of the path
      int minFlow = Integer.MAX_VALUE;
      Edge currBackwardEdge = backwardsEdges.get(sink.name);
      boolean sourceFound = false;
      while (!sourceFound) {
        // source is found, set the loop var to false, we still want to get the minFlow
        // of this edge but exit right after
        if (currBackwardEdge.returnNodeU() == source) {
          sourceFound = true;
        }
        minFlow = Math.min(minFlow, currBackwardEdge.getRemainingCapacity());
        currBackwardEdge = backwardsEdges.get(currBackwardEdge.returnNodeU().name);
      }

      // if flow is equal to 0 then all the capacities have been reaches and no
      // more volume can go through, break out to return max flow
      if (minFlow == 0) {
        break;
      }

      // update the path with the capacity as the bottleNeck for the next search
      currBackwardEdge = backwardsEdges.get(sink.name);
      sourceFound = false;
      while (!sourceFound) {
        // if the backwards U node is source, then we have found the source then after
        // this iteration we exit
        if (currBackwardEdge.returnNodeU() == source) {
          sourceFound = true;
        }
        // set the current flow
        currBackwardEdge.setCurrentFlow(minFlow);
        currBackwardEdge.printEdgeInformation();
        // get the next backwards edge
        currBackwardEdge = backwardsEdges.get(currBackwardEdge.returnNodeU().name);
      }
      max_flow += minFlow;
      // print out the current max flow for this path
      System.out.println("Max_Flow currently: " + max_flow + "\n");
    }
    graph.setMaxFlow(max_flow);
    return graph;
  }

  @Override
  public void printMinCuts(Graph graph) {
    HashSet<Node> sourceNodes = new HashSet<Node>();
    Node source = graph.getSource();
    // implement bfs again to search throug the graph to find which nodes are not
    // visited
    Queue<Node> queue = new LinkedList<Node>();

    queue.add(source);
    sourceNodes.add(source);
    while (!queue.isEmpty()) {
      Node current = queue.poll();
      ArrayList<Edge> currEdges = current.getEdges();
      for (Edge currEdge : currEdges) {
        Node v = currEdge.returnNodeV();
        if (currEdge.getRemainingCapacity() > 0 && !sourceNodes.contains(v)) {
          sourceNodes.add(v);
          queue.add(v);
        }
      }
    }

    System.out.println("\nEdmonds Karp MinCut Results:");

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
