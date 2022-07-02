package nvu1HW7;

/** 
 * ObjectHashMap.java
 * A child class extended from AbstractHashMap that have similar methods to a HashMap
 * Part 1 of Homework 7
*/
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ObjectHashMap extends AbstractHashMap{

    private LinkedList<Entry>[] table;
    
    public ObjectHashMap(double maxLoad) {
        super(maxLoad);
        table = new LinkedList[capacity];
        for(int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<Entry>();
        }
    }

    public ObjectHashMap(){
        super(0.7);
        table = new LinkedList[capacity];
        for(int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<Entry>();
        }
    }

    @Override
    public void put(Object key, Object value) {
        if (key == null){
            throw new IllegalArgumentException("Invalid key");
        }

        // If an entry is found in the hashMap, update it with new value
        // Else create new Entry and added it to the bucket(the linkedlist at the location of hash(key))
        Entry curEntry = findEntry(key);
        if (curEntry != null){
            curEntry.value = value;
        }else{
            LinkedList<Entry> bucket = (LinkedList<Entry>) table[hash(key)];
            bucket.add(new Entry(key,value));
            numKeys++;
            // Check if numKeys surpass maxLoad
            if (numKeys/capacity > maxLoad){
                resize();
            }
        }
    }

    @Override
    public Object find(Object key) {
        if (key == null){
            throw new IllegalArgumentException("Invalid key");
        }
        // Return value if a key exist in the hashMap, else throw exception
        Entry curEntry = findEntry(key);
        if (curEntry != null){
            return curEntry.value;
        }else{
            throw new NoSuchElementException("Key is not in the dictionary");
        }
    }

    @Override
    protected void resize() {
        // Get currentEntries first before resetting capacity and numKeys
        Entry[] curEntries = getEntries();
        capacity *= 2;
        numKeys = 0;
        table = new LinkedList[capacity];
        for(int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<Entry>();
        }
        // Copy to the new hashMap after resizing
        for (Entry e : curEntries){
            put(e.key, e.value);
        }
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null){
            throw new IllegalArgumentException("Invalid key");
        // Return true if key exist in the hashMap
        }else if (findEntry(key) != null){
            return true;
        }
        return false;
    }

    @Override
    public Entry[] getEntries() {
        Entry[] entryArray = new Entry[numKeys];
        int count = 0;
        // Loop through every entry and add to the new array
        for (LinkedList<Entry> bucket : table){
            for (Entry e : bucket){
                entryArray[count++] = e;
            }
        }
        return entryArray;
    }
    
    /**
     * Method to find the entry that holds the key
     * @param key an object that acts as key of hashMap
     * @return either the entry that holds key or null if key does not exist
     */
    protected Entry findEntry(Object key){
        int h = hash(key);
        LinkedList<Entry> bucket = table[h];
        for (Entry e : bucket){
            if (e.key.equals(key)){
                return e;
            }
        }
        return null;
    }
}
