package org.mcphase.base;

import java.util.Hashtable;
import java.util.Map.Entry;

/**
 * This class implements a bidirectional hashtable.
 * @author Till Hoffmann
 * @param <K>
 * @param <V>
 */
public class BiHashtable<K, V> extends Hashtable<K, V> {

    @Override
    public synchronized V put(K key, V value) {
        if(this.containsValue(value))
            throw new NullPointerException("Cannot add duplicate value.");
        return super.put(key, value);
    }

    /**
     * Returns the key to which the specified value is mapped, or
     * <code>null</code> if this map contains no mapping for the value.
     * @param value the value whose associated key is to be returned
     * @return the key to which the specified value is mapped, or
     * <code>null</code> if this map contains no mapping for the value
     */
    public K getKey(V value){
        for(Entry<K,V> e : this.entrySet()){
            //Check whether the value is the desired one
            if(e.getValue() == value)
                //Return the associated key
                return e.getKey();
        }
        //Couldn't find key
        return null;
    }
}
