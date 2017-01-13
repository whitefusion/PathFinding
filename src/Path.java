import java.util.Stack;

/**
 * class to store path.
 */
public class Path implements Path226 {
    
    /**
     * the stack to store path pairs.
     */
    private Stack<String> pathSects;
    
    /**
     * driver id.
     */
    private int driverId;
    
    /**
     * cost of this path.
     */
    private int cost;
    
    /**
     * default constructor of path.
     */
    public Path() {
        this.pathSects = new Stack<String>();
        this.cost = 0;
    }
    
    /**
     * path class constructor.
     * @param driver driver id.
     */
    public Path(int driver) {
        this.driverId = driver;
        this.pathSects = new Stack<String>();
        this.cost = 0;
    }
    
    @Override
    public void addPath(String pathPair) {
        this.pathSects.push(pathPair);
    }
    
    @Override
    public int getId() {
        return this.driverId;
    }
    
    @Override
    public void setcost(int e) {
        this.cost = e;
    }
    
    @Override
    public int getCost() {
        return this.cost;
    }
    
    @Override
    public void printPath() {
        for (String pathPair: this.pathSects) {
            System.out.println("\t" + pathPair);
        }
    }
    
    @Override
    public void setDriver(int id) {
        this.driverId = id;
    }
}

