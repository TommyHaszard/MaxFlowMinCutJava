
public class EdmondsKarpTest {

  public static void main(String[] args) {
    // FordFulkerson ff = new FordFulkerson();
    EdmondsKarp ed = new EdmondsKarp();

    // Graph searched = ff.searchAlgo(singleNodeGraph());
    // ff.printMinCuts(searched);

    Graph searched = ed.searchAlgo(bigTest());
    ed.printMinCuts(searched);
    // disconnectedSink();
    // highFlow();
    // cycleOnSourceED();
    // cycleOnSourceFF();
    // bigTest();
  }

  public static Graph singleNodeGraph() {
    System.out.println("Test with one node!");
    System.out.println("------------------------------------------------------------------");
    Graph graph = new Graph();

    Node sink = new Node("Sink");
    Node source = new Node("Source");

    graph.createVertex(sink);
    graph.createVertex(source);

    graph.setSink(sink);
    graph.setSource(source);

    Node node1 = new Node("Node1");
    graph.createVertex(node1);

    graph.addEdge(source, node1, 5);
    graph.addEdge(node1, sink, 5);

    return graph;
  }

  public static Graph disconnectedSink() {
    System.out.println("Test with disconnected sink!");
    System.out.println("------------------------------------------------------------------");
    Graph graph = new Graph();

    Node sink = new Node("Sink");
    Node source = new Node("Source");

    graph.createVertex(sink);
    graph.createVertex(source);

    graph.setSink(sink);
    graph.setSource(source);

    Node node1 = new Node("Node1");
    graph.createVertex(node1);

    graph.addEdge(source, node1, 5);
    return graph;
  }

  public static Graph highFlow() {
    System.out.println("Test with high flow!");
    System.out.println("------------------------------------------------------------------");
    Graph graph = new Graph();

    Node sink = new Node("Sink");
    Node source = new Node("Source");

    graph.createVertex(sink);
    graph.createVertex(source);

    graph.setSink(sink);
    graph.setSource(source);

    Node node1 = new Node("Node1");
    graph.createVertex(node1);

    Node node2 = new Node("Node2");
    graph.createVertex(node2);

    Node node3 = new Node("Node3");
    graph.createVertex(node3);

    Node node4 = new Node("Node4");
    graph.createVertex(node4);

    graph.addEdge(source, node1, 5);
    graph.addEdge(source, node2, 100);
    graph.addEdge(node1, node3, 5);
    graph.addEdge(node1, node4, 0);
    graph.addEdge(node2, node4, 5);
    graph.addEdge(node3, sink, 100);
    graph.addEdge(node4, sink, 100);

    return graph;
  }

  public static Graph cycleOnSource() {
    System.out.println("Test with cycle on source");
    System.out.println("------------------------------------------------------------------");
    Graph graph = new Graph();

    Node sink = new Node("Sink");
    Node source = new Node("Source");

    graph.createVertex(sink);
    graph.createVertex(source);

    graph.setSink(sink);
    graph.setSource(source);

    Node node1 = new Node("Node1");
    graph.createVertex(node1);

    Node node2 = new Node("Node2");
    graph.createVertex(node2);

    Node node3 = new Node("Node3");
    graph.createVertex(node3);

    graph.addEdge(source, node1, 9);
    graph.addEdge(source, node3, 4);

    graph.addEdge(node1, node2, 7);

    graph.addEdge(node2, source, 2);
    graph.addEdge(node2, node3, 2);
    graph.addEdge(node2, sink, 5);

    graph.addEdge(node3, sink, 6);

    return graph;
  }

  public static Graph bigTest() {
    System.out.println("Test with cycle, multiple paths and residual path!");
    System.out.println("------------------------------------------------------------------");
    Graph graph = new Graph();

    Node sink = new Node("Sink");
    Node source = new Node("Source");

    graph.createVertex(sink);
    graph.createVertex(source);

    graph.setSink(sink);
    graph.setSource(source);

    Node node1 = new Node("Node1");
    graph.createVertex(node1);

    Node node2 = new Node("Node2");
    graph.createVertex(node2);

    Node node3 = new Node("Node3");
    graph.createVertex(node3);

    Node node4 = new Node("Node4");
    graph.createVertex(node4);

    Node node5 = new Node("Node5");
    graph.createVertex(node5);

    Node node6 = new Node("Node6");
    graph.createVertex(node6);

    Node node7 = new Node("Node7");
    graph.createVertex(node7);

    graph.addEdge(source, node1, 3);
    graph.addEdge(source, node2, 3);

    graph.addEdge(node1, node5, 3);
    graph.addEdge(node1, node3, 3);

    graph.addEdge(node2, node3, 3);

    graph.addEdge(node3, node4, 4);

    graph.addEdge(node4, node1, 3);
    graph.addEdge(node4, node5, 1);
    graph.addEdge(node4, node6, 2);

    graph.addEdge(node5, node6, 2);
    graph.addEdge(node5, node7, 6);

    graph.addEdge(node6, node3, 1);
    graph.addEdge(node6, sink, 1);

    graph.addEdge(node7, sink, 9);

    return graph;
  }
}