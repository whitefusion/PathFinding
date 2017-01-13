/**
 * interface for driver class.
 */
public interface Driver226 {
    /**
     * return id of the driver.
     * @return  driver's id.
     */
    int getId();
    /**
     * get location of a driver.
     * @return  return the location of driver according to the id provided.
     */
    String getLoc();
    
    /**
     * the method to print the path for driver.
     */
    void printPath();
    
    /**
     * set driver's path.
     * @param route path.
     */
    void setPath(Path route);
    
    /**
     * get the cost of the route.
     * @return  the time waste.
     */
    int getTime();
    
}
