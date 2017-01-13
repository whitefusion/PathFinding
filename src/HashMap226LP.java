import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * the function to build a hashMap. 
 * @author whitefusion
 *
 * @param <K> K is the key of data.
 * @param <V> V is the data value.
 */
public class HashMap226LP<K, V> implements HashMap226<K, V> {
    
    /**
     * default size of hashtable.
     */
    private static final int DEFAULT_SIZE = 11;
    /**
     * default load factor(the percentage of hashtable usage).
     */
    private static final float DEFAULT_LF = 0.5f;
    /**
     * current occupancy of hashtable. 
     */
    private int currSize; 
    /**
     * an array that store the entry.
     */
    private Entry[] myEntry;
    /**
     * an array that records the status of each hash cell.
     * 0-empty, 1-occupied, 2- deleted.
     */
    private Integer[] eStatus;
    
    
/**
 * Inner class to associate the key and data value.
 * @author whitefusion
 *
 * @param <K>   key to access the data.
 * @param <V>   value of data. 
 */
    private class Entry {
        /** 
         * key to access the data.
         */
        K key;
        /**
         * value of the data to store.
         */
        V value;
        
        /**
         * the constructor of the class.
         * @param key0  input key.
         * @param value0 input value.
         */
        public Entry(K key0, V value0) {
            this.key = key0;
            this.value = value0;
        }
        
        /**
         * return the key.
         * @return key.
         */
        public K getKey() {
            return this.key;
        }
        
       /**
        * @return the value
        */
        public V getValue() {
            return this.value;
        }

    }
    
    /**
     * Emtpy constructor of hashtable.
     */
    public HashMap226LP() {
        this(DEFAULT_SIZE, DEFAULT_LF);
    }
    
    /**
     * HashMap constructor with specified size and loadfactor.
     * @param size  hashmap size you want. 
     * @param loadfactor    loadfactor of hashmap you want.
     * @throws IllegalArgumentException when input arguement is illegal 
     */
    @SuppressWarnings("unchecked")
    public HashMap226LP(int size, float loadfactor) 
            throws IllegalArgumentException {
        if (size < 0 || loadfactor <= 0 || loadfactor > 1) {
            System.out.println("size should be non-zero integer");
            // loadfactor should be less than 1 for linear probing.
            System.out.println("loadfactor should be between 0 and 1"); 
            throw new IllegalArgumentException();
        }
        this.myEntry 
            = (Entry[]) java.lang.reflect.Array.newInstance(Entry.class, size);
        this.currSize = 0;
        this.eStatus = new Integer[size];
        for (int i = 0; i < size; i++) {
            this.eStatus[i] = 0;
        } // initial status to 0
    }
    
    @Override
    public V put(K k, V v) {
        V temp = null;
        if ((float) this.currSize / this.myEntry.length > DEFAULT_LF) {
            this.reHash();
        }
        
        int loc = this.linSearch(k);
        if (loc < this.myEntry.length) {
            temp = this.myEntry[loc].value;
            this.myEntry[loc].value = v;
        } else {
            temp = this.linProbing(k, v);
        }
        this.currSize++;
        return temp;
    }
    
    @Override
    public V get(Object k) {
        int loc = this.linSearch(k);
        if (loc < this.myEntry.length) {
            if (this.myEntry[loc].value == null) {
                System.out.println("The key found,the mapping value is null");
            }
            return this.myEntry[loc].value;
        }
        System.out.println("No key found, return null");
        return null;
    }
    
    @Override
    public boolean containsKey(Object k) {
        int temp = this.linSearch(k);
        if (temp < this.myEntry.length) {
            return true;
        }
        return false;
    }
    
    @Override
    public V remove(Object k) {
        if (this.containsKey(k)) {
            int loc = this.linSearch(k);
            V temp = this.myEntry[loc].value;
            this.myEntry[loc].value = null;
            this.myEntry[loc].key = null;
            this.eStatus[loc] = 2; // set status to deleted
            this.currSize--;
            return temp;
        }
        return null; // object k does not exist
    }
    
    @Override
    public void clear() {
        for (int i = 0; i < this.myEntry.length; i++) {
            if (this.eStatus[i] == 1) {
                this.remove(this.myEntry[i].key);
            }
        }
    }
    
    @Override
    public int size() {
        return this.currSize;
    }
    
    @Override
    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet<K>();
        for (int i = 0; i < this.myEntry.length; i++) {
            if (this.eStatus[i] == 1) {
                keySet.add(this.myEntry[i].getKey());
            }
        }
        return keySet;
    }
    
    @Override
    public Collection<V> values() {
        ArrayList<V> valueList = new ArrayList<V>();
        for (int i = 0; i < this.myEntry.length; i++) {
            if (this.eStatus[i] == 1) {
                valueList.add(this.myEntry[i].getValue());
            }
        }
        return (Collection<V>) valueList;
    }
    
    /**
     * calculate the ideal location of a number in the hash table.
     * @param keyHash  the hashCode of key.
     * @return  return the ideal lcoation in the array.
     *  (should be 0 to entry.length - 1)
     */
    private int hash(int keyHash) {
        int hashVal = keyHash;
        hashVal %= this.length();
        if (hashVal < 0) {
            hashVal += this.length();
        }
        
        return hashVal;
    }
    
    /**
     * the method to expand the entry capacity to the smallest prime number
     * that is twice bigger than the current size. 
     */
    @SuppressWarnings("unchecked")
    private void reHash() {
        Entry[] oldArray = this.myEntry;
        Integer newL = (int) 2 * this.length();
        while (!isPrime(newL)) {
            newL++;
        }
        this.myEntry 
            = (Entry[]) java.lang.reflect.Array.newInstance(Entry.class, newL);
        this.eStatus = new Integer[newL];
        this.currSize = 0;
        for (int i = 0; i < newL; i++) {
            this.eStatus[i] = 0;
        }
        
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null) {
                this.put(oldArray[i].key, oldArray[i].value);
            }
        }
        
    }
    
    /**
     * to judege if the input num is prime number or not.
     * @param num   number of judge. 
     * @return  return true if it is a prime number. 
     */
    private static boolean isPrime(int num) {
        final int inPrime = 3;
        if (num % 2 == 0) {
            return false;
        }
        for (int i = inPrime; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     *  the method to do linear probing to solve the collsion.
     * @param k     the key of the input.
     * @param v     the value of data
     * @return V    return the data that being inserted into hashtable.    
     */
    private V linProbing(K k, V v) {
        int hCode = k.hashCode();
        int loc = this.hash(hCode);
        V temp = null;
        Entry element = new Entry(k, v);

        if (this.eStatus[loc] == 0) {
            this.myEntry[loc] = element;
            this.eStatus[loc] = 1; 
            
        } else {
            while (!(this.eStatus[loc] == 0)) {
                if ((this.eStatus[loc] == 1
                        && this.myEntry[loc].getKey() == k)) {
                    temp = this.myEntry[loc].getValue();
                    break;
                } else if (!(this.eStatus[loc] == 1)) {
                    break;
                }
                hCode++;
                loc = this.hash(hCode);
            }
            this.myEntry[loc] = element;
            this.eStatus[loc] = 1;
        }
        return temp;
    }
    /**
     * perform linSearch to find exact k, return unique value. 
     * stop when meeting a real empty entry. 
     * @param k key of the data 
     * @return  retrun the location of k, 
     * or length+1 when k does not exist.
     */
    private int linSearch(Object k) {
        int hCode = k.hashCode();
        int loc = this.hash(hCode);
        
        //need to record the number of iterations 
        //since the load factor might be set to 1.
        int it = 1;
        if (!(this.eStatus[loc] == 0)) {
            while (it < this.myEntry.length) {
                if (this.eStatus[loc] == 1 
                        && this.myEntry[loc].key.equals(k)) {
                    break; // stop search when the same key appear.
                } else if (this.eStatus[loc] == 0) {
                    break; // stop search when a real empty appear.
                }
                loc = this.hash(hCode++);
                it++;
            }
            if (it < this.myEntry.length && this.eStatus[loc] != 0) {
                return loc;
            }
        }
        return this.myEntry.length + 1; 
      //return a number that is impossible to be a location index,
        // when hashtable does not contain a certain key,
        // so we know if the entry contains this key or not
    }
    

    /**
     * @return  return total capacity of array.
     */
    public int length() {
        return this.myEntry.length;
    }
}
