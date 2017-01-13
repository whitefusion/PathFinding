

/**
 * methods for vertex class.
 */
public interface Vertex226 {
    /**
     * get the name(location) of a vertex.
     * @return  return name of the vertex, which should be a String.
     */
    String getName();
    
    /**
     *@return true if this vertex is known.
     */
    boolean isknown();
    
    /**
     * get the adjacency of current vertex.
     * @return return adjacency of vertex as an arrayList.
     */
    String getAdj();
    
    /**
     * get its previous vertex in the path.
     * @return return its previous vertex.
     */
    Vertex getPrev();
    
    /**
     * get the distance from this vertex to the source.
     * @return distance.
     */
    Integer getDist();
    
    /**
     * get the adjacency num of vertex.
     * @return return number of adjacencies.
     */
    int getAdjNum();
    
    /**
     *  add an adjacency to a vertex.
     * @param adjName  the name of its adjecency.
     * @param distant the distance between two adjacencies.
     */
    void addAdj(String adjName, int distant);
    /**
     * the distance to the source.
     * @param path the distance.
     */
    void setDist(Integer path);
    /**
     * set previous vertex in a path.
     * @param before the previous vertex.
     */
    void setPrev(Vertex before);
    /**
     * set known to true.
     */
    void setknown();
    
    /**
     * remove nearest adjacency.
     */
    void removeMin();
    
    /**
     * for copy constructor, return heap.
     * @return  return heap of adj.
     */
    Heap getAdjHeap();
}
