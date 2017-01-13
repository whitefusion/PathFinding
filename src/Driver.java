/**
 * a class for taxi driver.
 * 
 * @author whitefusion
 *
 */
public class Driver implements Driver226 {

    /**
     * the iD of driver. so user can make a selection.
     */
    private int iD;

    /**
     * the location of the driver.
     */
    private String location;
    
    /**
     * the way of driving.
     */
    private Path driveWay;
    
    /**
     * constructor of driver.
     * @param id    id of driver
     * @param loc  location of driver.
     */
    public Driver(int id, String loc) {
        this.iD = id;
        this.location = loc;
        this.driveWay = new Path();
    }

    @Override
    public String getLoc() {
        return this.location;
    }
    
    @Override
    public int getId() {
        return this.iD;
    }
    @Override
    public void printPath() {
        this.driveWay.printPath();
    }
    @Override
    public void setPath(Path route) {
        this.driveWay = route;
    }
    @Override
    public int getTime() {
        return this.driveWay.getCost();
    }
}
