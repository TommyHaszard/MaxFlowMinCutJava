public class MinCutMaxFlowTest {

  public static void main(String[] args) {
    // FordFulkerson ff = new FordFulkerson();
    EdmondsKarp ed = new EdmondsKarp();
    FordFulkerson ff = new FordFulkerson();

    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    System.out.println("Test with one node!");
    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    Graph edGraph = ed.searchAlgo(singleNodeGraph());
    ed.printMinCuts(edGraph);
    Graph fordGraph = ff.searchAlgo(singleNodeGraph());
    ff.printMinCuts(fordGraph);

    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    System.out.println("Test with disconnected sink!");
    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    edGraph = ed.searchAlgo(disconnectedSink());
    ed.printMinCuts(edGraph);
    fordGraph = ff.searchAlgo(disconnectedSink());
    ff.printMinCuts(fordGraph);

    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    System.out.println("Test with high flow!!");
    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    edGraph = ed.searchAlgo(highFlow());
    ed.printMinCuts(edGraph);
    fordGraph = ff.searchAlgo(highFlow());
    ff.printMinCuts(fordGraph);

    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    System.out.println("Test with cycle on source!");
    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    edGraph = ed.searchAlgo(cycleOnSource());
    ed.printMinCuts(edGraph);
    fordGraph = ff.searchAlgo(cycleOnSource());
    ff.printMinCuts(fordGraph);

    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    System.out.println("Test with cycle, multiple paths and residual path!");
    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    edGraph = ed.searchAlgo(bigTest());
    ed.printMinCuts(edGraph);
    fordGraph = ff.searchAlgo(bigTest());
    ff.printMinCuts(fordGraph);
  }

  public static Graph singleNodeGraph() {
    Graph graph = new Graph();

    Node sink = new Node("Sink");
    Node source = new Node("Source");

    graph.createVertex(sink);
    graph.createVertex(source);

    graph.setSink(sink);
    graph.setSource(source);

    Node node1 = new Node("1");
    graph.createVertex(node1);

    graph.addEdge(source, node1, 5);
    graph.addEdge(node1, sink, 5);

    return graph;
  }

  public static Graph disconnectedSink() {
    Graph graph = new Graph();

    Node sink = new Node("Sink");
    Node source = new Node("Source");

    graph.createVertex(sink);
    graph.createVertex(source);

    graph.setSink(sink);
    graph.setSource(source);

    Node node1 = new Node("1");
    graph.createVertex(node1);

    graph.addEdge(source, node1, 5);
    return graph;
  }

  public static Graph highFlow() {
    Graph graph = new Graph();

    Node sink = new Node("Sink");
    Node source = new Node("Source");

    graph.createVertex(sink);
    graph.createVertex(source);

    graph.setSink(sink);
    graph.setSource(source);

    Node node1 = new Node("1");
    graph.createVertex(node1);

    Node node2 = new Node("2");
    graph.createVertex(node2);

    Node node3 = new Node("3");
    graph.createVertex(node3);

    Node node4 = new Node("4");
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
    Graph graph = new Graph();

    Node sink = new Node("Sink");
    Node source = new Node("Source");

    graph.createVertex(sink);
    graph.createVertex(source);

    graph.setSink(sink);
    graph.setSource(source);

    Node node1 = new Node("1");
    graph.createVertex(node1);

    Node node2 = new Node("2");
    graph.createVertex(node2);

    Node node3 = new Node("3");
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
    Graph graph = new Graph();

    Node sink = new Node("Sink");
    Node source = new Node("Source");

    graph.createVertex(sink);
    graph.createVertex(source);

    graph.setSink(sink);
    graph.setSource(source);

    Node node1 = new Node("1");
    graph.createVertex(node1);

    Node node2 = new Node("2");
    graph.createVertex(node2);

    Node node3 = new Node("3");
    graph.createVertex(node3);

    Node node4 = new Node("4");
    graph.createVertex(node4);

    Node node5 = new Node("5");
    graph.createVertex(node5);

    Node node6 = new Node("6");
    graph.createVertex(node6);

    Node node7 = new Node("7");
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