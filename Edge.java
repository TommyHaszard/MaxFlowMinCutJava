
// Edge class implementation 
public class Edge {

  // edge edge contains 2 nodes (u,v)
  private Node u;
  private Node v;

  // the maximum flow of the edge
  private int max_capacity;

  // current flow default is 0
  private int curr_flow;

  // the attached residual edge used for augmenting paths
  private Edge residualEdge;
  private int path_flow;

  public Edge(Node u, Node v, int max_capacity) {
    this.u = u;
    this.v = v;
    this.max_capacity = max_capacity;
    this.curr_flow = 0;
    this.path_flow = 0;
  }

  public int getFlow() {
    return this.curr_flow;
  }

  // adds current flow, decreases residdual flow and sets the path flow
  public void setCurrentFlow(int flow) {
    this.curr_flow += flow;
    this.residualEdge.curr_flow -= flow;
    this.path_flow = flow;
  }

  public Node returnNodeU() {
    return this.u;
  }

  public Node returnNodeV() {
    return this.v;
  }

  // find the current capacity as the max capacity minus current flow
  public int getRemainingCapacity() {
    return this.max_capacity - this.curr_flow;
  }

  public int getMaxCapacity() {
    return this.max_capacity;
  }

  public void setResidual(Edge residualEdge) {
    this.residualEdge = residualEdge;
  }

  public void printEdgeInformation() {
    if (this.max_capacity == 0) {
      System.out.println("from: " + this.u.name + " to: "
          + this.v.name + ", remaining: " + this.getRemainingCapacity() + "/"
          + this.max_capacity
          + ", curr flow " + this.curr_flow + ", minFlow: " + this.path_flow + ", ooo augmented path!");
    } else {
      System.out.println("from: " + this.u.name + " to: "
          + this.v.name + ", remaining: " + this.getRemainingCapacity() + "/"
          + this.max_capacity
          + ", curr flow " + this.curr_flow + ", minFlow: " + this.path_flow);
    }
  }
}
