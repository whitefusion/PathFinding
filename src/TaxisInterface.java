/**
 * Interface for taxis program.
 */
public interface TaxisInterface {

    //alert k drivers

    /**
     * Print information of k nearest drivers.
     * Use a priority queue.
     * @param client the location of client.
     * @param aDriver just a driver.
     * @param mapLoc the hashtable for map.
     */
    void find(String client, Driver aDriver, HashMap226<String, Vertex> mapLoc);
    
    /**
     * print k selected driver.
     * @param k number of drivers.
     */
    void printKdriver(int k);
    
    /**
     * print out the path based on seleceted driver.
     * @param id driver id.
     */
    void printSelPath(int id);
    /**
     * Returns shortest path from the vertex 
     * where the driver is to where the client is.
     * @return return iterator.
     */
    //Iterable<String> pathTo();
    
    
    //note: the following methods may be private
    //or put in a separate Dijkstra's interface/class
    
    
    //Dijkstra's shortest path tree
    //uses edge weighted graph written for task 1

    
    /**
     * Is there a path from v to w.
     * @param w  the terminate verex.
     * @return whether there is a path to w
     */
    //boolean hasPath(String w);
    
  
}
