import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Filename: Graph.java Project: p4 Authors: Shefali Mukerji
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {

  private int size; // keeps track of number of edges
  private int order; // keeps track of number of vertices
  private List<GraphNode> vertices; // ArrayList of GraphNodes that keeps track of vertices in graph

  /*
   * private inner class which defines the GraphNodes
   */
  private class GraphNode {

    private String vertex; // The vertex associated with this node


    // ArrayList of string which holds the vertices that this GraphNode is dependent on
    private ArrayList<String> neighbors;

    // constructor which only takes vertex as parameter
    private GraphNode(String vertex) {
      this.vertex = vertex;
      this.neighbors = new ArrayList<String>();
    }

    // constructor which takes vertex and an ArrayList as parameters
    private GraphNode(String vertex, ArrayList<String> neighbors) {
      this.vertex = vertex;
      this.neighbors = neighbors;
    }

    /**
     * @return the String vertex of this GraphNode
     */
    private String getVertex() {
      return this.vertex;
    }

    /**
     * adds a vertex to neighbors ArrayList
     * 
     * @param vertex - new vertex to be added
     */
    private void addNeighbor(String vertex) {
      this.neighbors.add(vertex);
    }

    /**
     * sets the neighbors ArrayList equal to the specified ArrayList
     * 
     * @param neighbors - the new neighbors ArrayList
     */
    private void setNeighbors(ArrayList<String> neighbors) {
      this.neighbors = neighbors;
    }

    /**
     * removes specified neighbor from ArrayList
     * 
     * @param vertex - the vertex to remove
     */
    private void removeNeighbor(String vertex) {
      this.neighbors.remove(vertex);
    }

    /**
     * @return the neighbors ArrayList
     */
    private List<String> getNeighbors() {
      return this.neighbors;
    }
  }

  /*
   * Default no-argument constructor
   */
  public Graph() {
    this.size = 0;
    this.order = 0;
    vertices = new ArrayList<GraphNode>();
  }

  /**
   * Adds a new vertex to the graph. If vertex is null or already exists, method ends without adding
   * a vertex or throwing an exception.
   *
   * @param vertex - the vertex to be added
   */
  public void addVertex(String vertex) {
    if (vertex != null && !this.contains(vertex)) {
      vertices.add(new GraphNode(vertex));
      ++this.order;
    }
  }

  /**
   * Remove a vertex and all associated edges from the graph. If vertex is null or does not exist,
   * method ends without removing a vertex, edges, or throwing an exception.
   * 
   * @param vertex - the vertex to be removed
   */
  public void removeVertex(String vertex) {
    if (vertex != null && this.contains(vertex)) {
      for (int i = 0; i < vertices.size(); ++i) {
        if (vertices.get(i).getNeighbors().contains(vertex)) {
          vertices.get(i).getNeighbors().remove(vertex);
        }
      }
      int index = this.getIndex(vertex);
      vertices.remove(index);
      --this.order;
    }
  }

  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted) If either
   * vertex does not exist, no edge is added and no exception is thrown. If the edge exists in the
   * graph, no edge is added and no exception is thrown.
   * 
   * @param vertex1 - the vertex that will be added into the neighbors ArrayList
   * @param vertex2 - the vertex whose neighbors ArrayList will hold vertex1.
   */
  public void addEdge(String vertex1, String vertex2) {
    if (vertex1 != null && vertex2 != null) {
      if (!this.contains(vertex1)) {
        this.addVertex(vertex1);
      }
      if (!this.contains(vertex2)) {
        this.addVertex(vertex2);
      }
      int index = this.getIndex(vertex1);
      if (!vertices.get(index).getNeighbors().contains(vertex2)) {
        vertices.get(index).addNeighbor(vertex2);
        ++this.size;

      }
    }
  }

  /**
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
   * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * @param vertex1 - the vertex that will be removed from a neighbors ArrayList
   * @param vertex2 - the vertex whose neighbors ArrayList vertex1 will be removed from
   */
  public void removeEdge(String vertex1, String vertex2) {
    if (vertex1 != null && vertex2 != null) {
      if (vertex1.compareTo(vertex2) != 0) {
        if (this.contains(vertex1) && this.contains(vertex2)) {
          int index = this.getIndex(vertex1);
          if (vertices.get(index).getNeighbors().contains(vertex2)) {
            vertices.get(index).removeNeighbor(vertex2);
            --this.size;
          }
        }
      }
    }
  }

  /**
   * @return a Set that contains all the vertices in the graph
   */
  public Set<String> getAllVertices() {
    Set<String> set = new TreeSet<String>();
    for (int i = 0; i < vertices.size(); ++i) {
      set.add(vertices.get(i).getVertex());
    }
    return set;
  }

  /**
   * Gets a list of all the neighbor (adjacent) vertices of a vertex
   * 
   * @param vertex - the vertex whose adjacent vertices are being found
   * @return List of adjacent vertices
   */
  public List<String> getAdjacentVerticesOf(String vertex) {
    if (!this.contains(vertex)) {
      return null;
    }
    int index = this.getIndex(vertex);
    return vertices.get(index).getNeighbors();
  }

  /**
   * @return the number of edges in this graph.
   */
  public int size() {
    return this.size;
  }

  /**
   * @return the number of vertices in this graph.
   */
  public int order() {
    return this.order;
  }

  /**
   * determines whether a string vertex is currently in the graph
   * 
   * @param vertex - the vertex to check
   * @return true if vertices ArrayList contains a GraphNode with vertex that matches the param, and
   *         false otherwise.
   */
  private boolean contains(String vertex) {
    for (int i = 0; i < vertices.size(); ++i) {
      if (vertices.get(i).getVertex().compareTo(vertex) == 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * returns index of vertices ArrayList which contains GraphNode with vertex that matches param.
   * 
   * @param vertex - the vertex to determine index of
   * @return index where GraphNode is with vertex 'vertex' is located, or -1 if it is not found.
   */
  private int getIndex(String vertex) {
    for (int i = 0; i < vertices.size(); ++i) {
      if (vertices.get(i).getVertex().compareTo(vertex) == 0) {
        return i;
      }
    }
    return -1;
  }
}
