


/**
 * This Kheap is a maximum priority Queue to save the k nearest driver.
 * The heap that stores the most popular K items. (not a standard one)
 * @author The code is orginal written by MARK ALLEN WEISS and 
 * modified by Xin Bai.
 */
public class Kheap {
    
    /**
     * the heap is an array of T.
     */
    private T[] myHeap;
    /**
     * show current number of items in the array.
     */
    private int currSize;
    
    /**
     * class T to associate item name and item number.
     */
    class T {

        /**
         * frequency of the item.
         */
        Integer itemNum;
        
        /** 
         * name of the item.
         */
        Driver myDriver;
        
        /**
         * constuctor of the T. 
         * @param number    item number.
         * @param aDriver      item name.
         */
        public T(Integer number, Driver aDriver) {
            this.myDriver = aDriver;
            this.itemNum = number;
        }
        
    }
    
    /**
     * Construct a heap with capacity k.
     * @param capacity  the capacity of the heap.
     */
    public Kheap(int capacity) {
        //this.myHeap = new T[capacity];
        // array[0] is not used for storing. 
        this.myHeap 
            = (T[]) java.lang.reflect.Array.newInstance(T.class, capacity + 1);
        this.currSize = 0;
    }
    
    /** 
     * insert with bubbling up.
     * @param itemNum   the frequency of item.
     * @param myDriver  the name of the item.
     */
    public void insert(Integer itemNum, Driver myDriver) {
        T temp = new T(itemNum, myDriver);
        int hole = ++this.currSize;
        for (this.myHeap[0] = temp;
                    temp.itemNum > this.myHeap[(hole / 2)].itemNum; hole /= 2) {
            this.myHeap[hole] = this.myHeap[hole / 2];
        }
        this.myHeap[hole] = temp;
    }
    
    /**
     * delete the max item in the heap and bubbling down to find another max.
     * @return  item with maximum number.
     */
    public T deleteMax() {
        if (this.isEmpty()) {
            throw new UnderflowException();
        }
        
        T minItem = this.findMax();
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
    public T findMax() {
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
        // resize when the heap is full
        if (this.size() == this.myHeap.length - 1) {
            this.reSize();
        }
        T tmp = this.myHeap[hole];
        
        for (; hole * 2 <= this.currSize; hole = child) {
            child = hole * 2;
            if (child != this.currSize && this.myHeap[child + 1].itemNum 
                    > this.myHeap[child].itemNum) {
                child++;
            }
            
            if (this.myHeap[child].itemNum > tmp.itemNum) {
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
            System.out.println("\t" + this.myHeap[i].myDriver.getLoc() + '(' 
                    + this.myHeap[i].itemNum + ')');
        }
    }
    
    /**
     * get the number of min item. 
     * @return  return item number. 
     */
    public Integer numGet() {
        if (!this.isEmpty()) {
            return this.findMax().itemNum;
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
     * @param myItem the name of new item.
     * @return  true if there exists same item.
     */
    public boolean updateItem(String myItem) {
        for (int i = 1; i <= this.currSize; i++) {
            if (this.myHeap != null) {
                if (this.myHeap[i].myDriver.getLoc().equals(myItem)) {
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
    
}
