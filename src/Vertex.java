/**
 * the class for a vertex.
 */
public class Vertex implements Vertex226 {
    
    /**
     * define infinity integer to initialize distance.
     */
    private static Integer inf = Integer.MAX_VALUE;
    
    /**
     * the arraylist of adjacencies.
     */
    public Heap adj;
    /**
     * name of vertex, should be an address in this case.
     */
    private String name;
    
    /**
     * the vertex before this one, null for initial.
     */
    private Vertex prev;
    
    /**
     * the distance to the source, max for initial.
     */
    private Integer dist;
    
    /**
     * to test if this vertex is known or not.
     */
    private boolean known;
    
    
    
    /**
     * constructor.
     * @param address   need address to construct.
     */
    public Vertex(String address) {
        this.name = address;
        this.prev = null;
        this.known = false;
        this.adj = new Heap();
        this.dist = inf;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isknown() {
        return this.known;
    }

    @Override
    public String getAdj() {
        String adjList = null;
        if (this.adj.isEmpty()) {
            return null;
        } else {
            adjList = (this.adj.findMin().itemName);
        }
        return adjList;
    }
    
    @Override
    public void removeMin() {
        this.adj.deleteMin();
    }

    @Override
    public Vertex getPrev() {
        return this.prev;
    }

    @Override
    public Integer getDist() {
        return this.dist;
    }
    
    @Override
    public void addAdj(String adjName, int distant) {
        this.adj.insert(distant, adjName);
    }

    @Override
    public void setknown() {
        this.known = true;
    }
    @Override
    public void setDist(Integer path) {
        this.dist = path;
    }
    @Override
    public void setPrev(Vertex before) {
        this.prev = before;    
    }
    @Override
    public int getAdjNum() {
        return this.adj.size();
    }
    
    @Override
    public Heap getAdjHeap() {
        return this.adj;
    }

}
