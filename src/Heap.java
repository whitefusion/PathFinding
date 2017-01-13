
/**
 * Adaptable heap.
 * @author whitefusion
 */
public class Heap {
    
    /**
     * the heap is an array of T.
     */
    private T[] myHeap;
    /**
     * show current number of items in the array.
     */
    private int currSize;
    
    /**
     * the default size of heap is 5.
     */
    private final int dS = 5;

    /**
     * class T to associate item name and item number.
     * @author whitefusion
     *
     */
        
    class T {
        /** 
         * name of the item.
         */
        String itemName;
        /**
         * frequency of the item.
         */
        Integer itemNum;
        
        /**
         * constuctor of the T. 
         * @param number    item number.
         * @param name      item name.
         */
        public T(Integer number, String name) {
            this.itemName = name;
            this.itemNum = number;
        }
        
    }
    
    /**
     * 
     */
    public Heap() {
        this.myHeap 
            = (T[]) java.lang.reflect.Array.newInstance(T.class, this.dS + 1);
        this.currSize = 0;
    }
    /**
     * Construct a heap with capacity k.
     * @param capacity  the capacity of the heap.
     */
    public Heap(int capacity) {
        //this.myHeap = new T[capacity];
        // array[0] is not used for storing. 
        this.myHeap 
            = (T[]) java.lang.reflect.Array.newInstance(T.class, capacity + 1);
        
    }
    
    /** 
     * insert with bubbling up.
     * @param itemNum   the frequency of item.
     * @param itemName  the name of the item.
     */
    public void insert(Integer itemNum, String itemName) {
        // resize when the heap is full
        if (this.size() == this.myHeap.length - 1) {
            this.reSize();
        }
        T temp = new T(itemNum, itemName);
        int hole = ++this.currSize;
        for (this.myHeap[0] = temp;
                    temp.itemNum < this.myHeap[(hole / 2)].itemNum; hole /= 2) {
            this.myHeap[hole] = this.myHeap[hole / 2];
        }
        this.myHeap[hole] = temp;
    }
    
    /**
     * delete the max item in the heap and bubbling down to find another max.
     * @return  item with maximum number.
     */
    public T deleteMin() {
        if (this.isEmpty()) {
            throw new UnderflowException();
        }
        
        T minItem = this.findMin();
        this.myHeap[1] = this.myHeap[this.currSize];
        this.bubbleDown(1);
        this.myHeap[this.currSize] = null;
        this.currSize--;
        return minItem;
    }
    
    /**
     * find the item with max frequency in the heap.
     * usually in the first location.
     * @return  return the maximum item information.
     */
    public T findMin() {
        if (this.isEmpty()) {
            throw new UnderflowException();
        }
        return this.myHeap[1];
    }
    
    /**
     * To show the heap is empty or not. 
     * @return  true if it is empty.
     */
    public boolean isEmpty() {
        return this.currSize == 0;
    }
    
    /**
     * do bubbling down movement.
     * @param hole  the current hole location.
     */
    private void bubbleDown(int hole) {
        int child;
        T tmp = this.myHeap[hole];
        
        for (; hole * 2 <= this.currSize; hole = child) {
            child = hole * 2;
            if (child != this.currSize && this.myHeap[child + 1].itemNum 
                    < this.myHeap[child].itemNum) {
                child++;
            }
            
            if (this.myHeap[child].itemNum < tmp.itemNum) {
                this.myHeap[hole] = this.myHeap[child];
            } else {
                break;
            }
        }
        this.myHeap[hole] = tmp;
    }
    
    /**
     * the function to print out all the item in the heap.
     */
    public void printItem() {
        for (int i = 1; i <= this.currSize; i++) {
            System.out.println("\t" + this.myHeap[i].itemName + '(' 
                    + this.myHeap[i].itemNum + ')');
        }
    }
    
    /**
     * get the number of min item. 
     * @return  return item number. 
     */
    public Integer numGet() {
        if (!this.isEmpty()) {
            return this.findMin().itemNum;
        }
        return null;
    }
    
    /**
     * return current size of Integer. 
     * @return  return current size.
     */
    public Integer size() {
        return this.currSize;
    }
    
    /**
     * return the location of item if it is already exist.
     * @param myItem the name of new item
     * @return  true if there exists same item.
     */
    public boolean updateItem(String myItem) {
        for (int i = 1; i <= this.currSize; i++) {
            if (this.myHeap != null) {
                if (this.myHeap[i].itemName.equals(myItem)) {
                    this.myHeap[i].itemNum++;
                    this.bubbleDown(i);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * resize the heap when it is full.
     */
    public void reSize() {
        T[] temp
            = (T[]) java.lang.reflect.Array
            .newInstance(T.class, 2 * this.myHeap.length + 1);
        for (int i = 1; i <= this.size(); i++) {
            temp[i] = this.myHeap[i];
        }
        this.myHeap = temp;
    }
    
    /**
     * get the weight of adjacency through its name.
     * @param adjName   the name of adjacency location.
     * @return return distance between two places.
     * -1 represent they are not connected.
     */
    public int getNum(String adjName) {
        for (int i = 1; i <= this.size(); i++) {
            if (this.myHeap[i].itemName == adjName) {
                return this.myHeap[i].itemNum;
            }
        }
        return -1;
    }
}
