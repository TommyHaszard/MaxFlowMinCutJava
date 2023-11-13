import java.util.ArrayList;

// very simple Node implementation, that contains a list of all its edges. 

public class Node {
  String name;
  ArrayList<Edge> edgeList = new ArrayList<>();

  public Node(String name) {
    this.name = name;
  }

  public ArrayList<Edge> getEdges() {
    return this.edgeList;
  }

  public String getName() {
    return this.name;
  }

  public void addEdge(Edge edge) {
    this.edgeList.add(edge);
  }
}
