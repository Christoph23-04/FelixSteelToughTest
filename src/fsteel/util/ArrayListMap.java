package fsteel.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ArrayListMap<K, V> extends HashMap<K, ArrayList<V>> {

    public void putObject(K key, V value){
        if(!super.containsKey(key)){
            super.put(key, new ArrayList<V>());
        }
        super.get(key).add(value);
    }

    public void putObjects(K key, Collection<? extends V> values){
        for(V v : values){
            putObject(key, v);
        }
    }

    public void removeObject(K key, V value){
        if(super.containsKey(key)){
            super.get(key).remove(value);
        }
    }
}
