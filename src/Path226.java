/**
 * method for path.
 */
public interface Path226 {
    /**
     * add path pair to driver's path.
     * @param pathPair  string of two locations.
     */
    void addPath(String pathPair);
    /**
     * a method to get the driver of this path.
     * @return return driver's id.
     */
    int getId();
    /**
     * set cost of a path.
     * @param e cost of this path.
     */
    void setcost(int e);
    
    /**
     * return cost of this path.
     * @return  cost.
     */
    int getCost();
    
    /**
     * set the driver for path.
     * @param id    driver id.
     */
    void setDriver(int id);
    
    /**
     * the method to print path.
     */
    void printPath();
}
