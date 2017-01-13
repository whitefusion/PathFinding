import java.util.ArrayList;

/**
 * The class implements "TaxisInterface" interface.
 * find the k nearest driver near the passenger.
 * print out the route.
 */
public class Taxi implements TaxisInterface {

    /**
     * the hashtable to store driver.
     * key is the location of driver.
     * Integer is its id.
     */
    private ArrayList<Driver> myDriver;
    
    /**
     * the Dijkstra class that solve shortes path finding problem.
     */
    private Dijkstra myDijkstra;
    
    /**
     * the local hashmap.
     */
    //private HashMap226<String, Vertex> mapLocation;
    
    
    /**
     * the heap to save k shortes path.
     */
    private Kheap myHeap;
    
    /**
     * the number of nearest drivers.
     */
    private int s0;
    /**
     * constructor of taxi class.
     * @param driverList    list of drivers.
     * @param s the number of nearest driver.
     */
    public Taxi(int s,
            ArrayList<Driver> driverList) {
        this.myDriver = new ArrayList<Driver>();
        
        this.myDriver = driverList;
        this.myHeap = new Kheap(s);
        this.s0 = s;
        //this.mapLocation = new HashMap226LP<String, Vertex>();

    }
    
    @Override
    public void find(String client, Driver eachDriver
            , HashMap226<String, Vertex> mapLoc) {
        int min;
        this.myDijkstra = new Dijkstra(mapLoc);
        this.myDijkstra.findMin(eachDriver.getLoc());
        min = this.myDijkstra.buildPath(client);
        eachDriver.setPath(this.myDijkstra.getPath());  
        if (!this.myHeap.isEmpty() && this.myHeap.size() == this.s0) {     
            if (this.myHeap.findMax().itemNum > min 
                   && this.myHeap.size() == this.s0) {
                this.myHeap.deleteMax();
                this.myHeap.insert(min, eachDriver);
            }
        } else if (this.myHeap.isEmpty() || this.myHeap.size() < this.s0) {
            this.myHeap.insert(min, eachDriver);
        }
    }
    
    @Override
    public void printKdriver(int k) {
        for (int i = 1; i <= k; i++) {
            Driver tempDriver = this.myHeap.deleteMax().myDriver;
            System.out.println(tempDriver.getId() + " at " 
                    + tempDriver.getLoc());
            System.out.println("Estimated waiting time:" 
                    + tempDriver.getTime() + " min" + '\n');
        }
    }
    
    @Override
    public void printSelPath(int id) {
        for (Driver selDriver: this.myDriver) {
            if (selDriver.getId() == id) {
                System.out.println("The recommended route for driver " 
                        + selDriver.getId() + " is:\n");
                selDriver.printPath();
                System.out.println("\n\tThe expected total time is: " 
                        + selDriver.getTime() + " min\n");
            }
        }
    }
    

}
