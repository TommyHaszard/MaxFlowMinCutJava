import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;

// Graph implementation
public class Graph {
  // source and sink node
  private Node source;
  private Node sink;
  private int maxFlow = 0;
  private int count = 0;
  private HashSet<Edge> edgeSet = new HashSet<Edge>();

  // hashmap for nodes for instant lookup of the node
  private HashMap<String, Node> vertices = new HashMap<String, Node>();

  // checks if the graph contains the node supplied
  public Boolean contains(Node u) {
    if (vertices.containsKey(u.getName())) {
      return true;
    }
    return false;
  }

  // create a new node, if it doesnt exist already in the vertex hashmap
  public void createVertex(Node u) {
    if (!contains(u)) {
      this.vertices.put(u.getName(), u);
    }
  }

  // takes 2 nodes and the max flow between the nodes
  public void addEdge(Node u, Node v, Integer max_flow) {
    // make sure the nodes exist and are not the same node
    if (contains(u) && contains(v) && u != v) {
      // creates the edge and the residual edge
      Edge newEdge = new Edge(u, v, max_flow);
      Edge residual = new Edge(v, u, 0);
      newEdge.setResidual(residual);
      residual.setResidual(newEdge);
      u.addEdge(newEdge);
      v.addEdge(residual);
      edgeSet.add(newEdge);
      edgeSet.add(residual);
    }
  }

  public void setSource(Node source) {
    this.source = source;
  }

  public void setSink(Node sink) {
    this.sink = sink;
  }

  public Node getSource() {
    return this.source;
  }

  public Node getSink() {
    return this.sink;
  }

  public void setMaxFlow(int maxFlow) {
    this.maxFlow = maxFlow;
  }

  public int returnMaxFlow() {
    return this.maxFlow;
  }

  public HashSet<Edge> returnEdges() {
    return edgeSet;
  }

  public ArrayList<Node> returnVertices() {
    Collection<Node> collection = this.vertices.values();
    ArrayList<Node> list = new ArrayList<>(collection);
    return list;
  }

  public void incrementCount() {
    this.count++;
  }

  public int returnCount() {
    return this.count;
  }
}