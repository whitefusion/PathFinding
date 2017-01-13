/**
 * the methods for Dijkstra algorithm.
 * @author whitefusion
 */
public interface Dijkstra226 {
    /**
     * find the minimum path from start to end (single source).
     * @param start name of start, driver's location.
     */
    void findMin(String start);
    
    /**
     * get cost of the certain location after findMin().
     * @param client the location of client.
     * @return return cost.
     */
    Integer distTo(String client);
    
    /**
     * print out the path from driver to client.
     * @return the path.
     */
    Path getPath();
    
    /**
     * build the path for driver based on the location of client.
     * @param client  the location of client.
     * @return return min cost.
     */
    int buildPath(String client);
    
}
