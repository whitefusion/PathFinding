import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The driver of taxi class.
 */
public final class TaxiDriver {
    
    /**
     * k nearest driver.
     */
    public static Integer k;
    
    /**
     * used in args[3].
     */
    public static final int THREE = 3;
    /**
     * Do nothing here.
     */
    
    /**
     * The hashmap for vertices.
     */
    private static HashMap226<String, Vertex> mapLocHash;
    /**
     * The arraylist for name of loation.
     */
    private static ArrayList<String> locName;
    
    /**
     * the arraylist to store drivers.
     */
    private static ArrayList<Driver> drivers;
    
    /**
     * do nothing here.
     */
    private TaxiDriver() {
        
    }
    
    /**
     * main function for taxidriver.
     * @param args  the input of command line.
     * @throws IOException Input output exception.
     */
    public static void main(String[] args) throws IOException {
        try {
            k = Integer.parseInt(args[0]);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The first input" 
                    + "must be an Integer");
        }
        
        File mapLoc = null;
        File mapConnect = null;
        File driverLoc = null;
        
        if (args.length >= THREE) {
            mapLoc = new File(args[1]);
            mapConnect = new File(args[2]);
            driverLoc = new File(args[THREE]);
        } else {
            System.err.println("Invalid arguments count:" + args.length);
            System.exit(0);
        }
        
        
        Integer[] num = new Integer[2];
        int numDriver;
        num = loadMaps(mapLoc, mapConnect);
        numDriver = loadDrivers(driverLoc);
        
        System.out.println(
                "Collecting map locations from mapLocations.txt...");
        System.out.println(num[0] + " locations input.\n");
        System.out.println(
                "Collecting map connections from mapConnections.txt...");
        System.out.println(num[1] + " connections input.\n");
        System.out.println(
                "Collecting driver locations from driverLocations.txt...");
        System.out.println(numDriver + " drivers input.\n");
        System.out.println("Map locations are:\n");
        
        printAllLoc();
        
        System.out.println("\n" 
               + "Enter number of recent client pickup request location:");
        Integer c = 0;
        BufferedReader input1 = 
                new BufferedReader(new InputStreamReader(System.in));
        String clientLoc = input1.readLine();
        if (checkK(clientLoc)) {
            c = Integer.parseInt(clientLoc);
        } else {
            System.out.println("Goodbye O[^_^]O");
            System.exit(0);
        }
        if (c > num[1]) {
            throw new IndexOutOfBoundsException();
        }
        clientLoc = locName.get(c - 1);
        System.out.println("\nThe location of client is:");
        System.out.println(clientLoc);
        System.out.println("\nThe " + k 
                + " drivers to alert about this pickup are:");
        Taxi myTaxi = new Taxi(k, drivers);
        
        for (int i = 1; i <= drivers.size(); i++) {
            myTaxi.find(clientLoc, drivers.get(i - 1), mapLocHash);
            loadMaps(mapLoc, mapConnect);
        }
        myTaxi.printKdriver(k);
        
        System.out.println("\nEnter the ID number"
                        + "of the driver who responded:");
        Integer c2 = 0;
        BufferedReader input2 = 
                new BufferedReader(new InputStreamReader(System.in));
        String clientChoice = input2.readLine();
        c2 = Integer.parseInt(clientChoice);
        myTaxi.printSelPath(c2);
        System.out.println("Thank you, enjoy your journey.");

    }
    
    /**
     * The function to build vertex hashmap.
     * @param mapLoc    file mapLocations. 
     * @param mapConnect    file mapConnections.
     * @throws IOException  file input/output exceptions.
     * @return Integer[] the number of locations in map.
     */
    private static Integer[] loadMaps(File mapLoc, File mapConnect) 
            throws IOException {
        mapLocHash = new HashMap226LP<String, Vertex>();
        locName = new ArrayList<String>();
        String currLoc;
        String currCon;
        Integer locCounter = 0;
        Integer conCounter = 0;

        BufferedReader locBuff = new BufferedReader(new FileReader(mapLoc));
        BufferedReader conBuff = new BufferedReader(new FileReader(mapConnect));

        //Scanner locStream = new Scanner(locBuff);
        //Scanner conStream = new Scanner(conBuff);
        while ((currLoc = locBuff.readLine()) != null) {
            currLoc = currLoc.trim();
            locName.add(currLoc);
            Vertex tempLoc = new Vertex(currLoc);
            mapLocHash.put(currLoc, tempLoc);
            locCounter++;
        }
        
        while ((currCon = conBuff.readLine()) != null) {
            int comma = currCon.indexOf(',');
            int bracket = currCon.indexOf(')');
            String tempLoc1 = currCon.substring(1, comma);
            int secondBegin = comma + 1;
            String tempLoc2 = 
                    currCon.substring(secondBegin, bracket);
            tempLoc2 = tempLoc2.trim();
            String distS = currCon
                    .substring(bracket + 1, currCon.length());
            String newdistS = distS.trim();
            int dist = Integer.parseInt(newdistS);
            //System.out.println(tempLoc1 + '|' + tempLoc2 + '|' + dist);
            buildAdj(tempLoc1, tempLoc2, dist);
            conCounter++;
        }
        
        locBuff.close();
        conBuff.close();
        Integer[] num = new Integer[2];
        num[0] = locCounter;
        num[1] = conCounter;
        return num;
    }
    
    /**
     * build adj heap in each vertex. 
     * @param loc1 the start of a connection.
     * @param loc2  the end of a connection.
     * @param d the distance between two places.
     */
    private static void buildAdj(String loc1, String loc2, int d) {
        Vertex tempV1 = mapLocHash.get(loc1); 
        Vertex tempV2 = mapLocHash.get(loc2);
        tempV1.addAdj(loc2, d);
        tempV2.addAdj(loc1, d);
        mapLocHash.put(loc1, tempV1);
        mapLocHash.put(loc2, tempV2);
    }
    
    /**
     * the method to build driver arraylist.
     * @param driverfile    the textfile stores drivers infomation.
     * @exception IOException throws input outputexception.
     * @return int return number of drivers.
     */
    private static int loadDrivers(File driverfile) 
            throws IOException {
        drivers = new ArrayList<Driver>();
        String currDriver;
        int driverCounter = 0;
        BufferedReader driverBuff = 
                new BufferedReader(new FileReader(driverfile));
        while ((currDriver = driverBuff.readLine()) != null) {
            int whiteSpace = currDriver.indexOf('\t');
            String id = currDriver.substring(0, whiteSpace);
            //System.out.println(id);
            int driverId = 
                    Integer.parseInt(id);
            String driverLoc = currDriver
                    .substring(whiteSpace, currDriver.length());
            driverLoc = driverLoc.trim();
            Driver temp = new Driver(driverId, driverLoc);
            drivers.add(temp);
            driverCounter++;
            
        }
        driverBuff.close();
        return driverCounter;
    }
    
    /**
     * print all locations for user to choose.
     */
    private static void printAllLoc() {
        int counter = 1;
        for (String location: locName) {
            System.out.println("\t\t" + counter + " " + location);
            counter++;
        }
    }
    
    /**
     * the funciton to check if input is an integer. 
     * @param inputK the input string of k.
     * @return  integer k.
     */
    public static boolean checkK(String inputK) {
        boolean myK = true;
        try {
            Integer.parseInt(inputK);
        } catch (NumberFormatException e) {
            System.out.println("input MUST be an INTEGER.");
            myK = false;
        }
        return myK;
    }
    

}
