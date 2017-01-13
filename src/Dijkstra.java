import java.util.LinkedList;
import java.util.Queue;


/**
 * The dijkstra class to find shortest path.
 */
public class Dijkstra implements Dijkstra226 {
    /**
     * the hashtable to store vertices.
     * key is the vertex name.
     * value is the vertex.
     */
    private HashMap226<String, Vertex> vertexHash;
    
    /**
     * The Queue to store the adjacency of a vertex.
     */
    private Queue<Vertex> currAdj;
    
    /**
     * the stack hold the array of path.
     */
    private Path myPath;
    /**
     * the default load factor.
     */
    private final float lf = (float) 0.5;
    
    
    /**
     * constructor.
     * @param myLoc locations on the map.
     */
    public Dijkstra(HashMap226<String, Vertex> myLoc) {
        this.vertexHash = 
                new HashMap226LP<String, Vertex>(myLoc.size(), this.lf);
        //this.edgeHash = new HashMap226LP<String, Edge>();
        this.vertexHash = myLoc;
        //this.edgeHash = myConnect;
        this.myPath = new Path();
        
    }
    
    @Override
    public void findMin(String start) {
        Vertex driver = this.vertexHash.get(start);
        this.currAdj = new LinkedList<Vertex>();
        driver.setDist(0); // set distance of source to 0;
        driver.setknown(); // set status of source to known;
        
        //put the adjacency into queue in order and update their distance.
        while (driver.getAdj() != null) {
            String str = driver.getAdj();
            Vertex tempVertex = this.vertexHash.get(str);
            Integer tempDist = driver.adj.getNum(str);
            tempVertex.setPrev(driver);
            tempVertex.setDist(tempDist + driver.getDist());
            this.currAdj.add(tempVertex);
            driver.removeMin();
        }
        
        while (!this.currAdj.isEmpty()) {
            Vertex next = this.currAdj.poll();
            next.setknown(); // set dequeue vertex to be known.
            this.update(next);
        }
    }
    
    @Override
    public Integer distTo(String client) {
        return this.vertexHash.get(client).getDist();
    }
    

    @Override
    public Path getPath() {
        return this.myPath;
    }
    /**
     * update information of a vertex if it is unknown or 
     * better path found for it.
     * @param center current center.
     */
    private void update(Vertex center) {
        Integer baseDist = center.getDist();
        while (center.getAdj() != null) {
            String adj = center.getAdj();
            Vertex tempV = this.vertexHash.get(adj);
            if (!tempV.isknown()) {
                Integer cost = center.adj.getNum(adj);
                if (cost + baseDist < tempV.getDist()) {
                    tempV.setDist(cost + baseDist);
                    tempV.setPrev(center);
                }
                this.currAdj.add(tempV);
            }
            center.removeMin();
        }
    }

    @Override
    public int buildPath(String client) {
        int min;
        this.myPath = new Path();
        Vertex dest = this.vertexHash.get(client);
        min = dest.getDist();
        if (dest.getPrev() != null) {
            this.buildPath(dest.getPrev().getName());
            this.myPath.addPath("to");
        }
        this.myPath.addPath(client);
        this.myPath.setcost(dest.getDist());
        return min;
    }
   
}
    
    

