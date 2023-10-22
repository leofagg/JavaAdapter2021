package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;

public class MapAdapter implements HMap {//non accetta valori nulli
    private Hashtable<Object, Object> hash;

    public MapAdapter()//map default constructor
    {
        hash = new Hashtable<Object, Object>();
    }

    public MapAdapter(MapAdapter map) //creates a new map with the same key-value mappings as its argument.
    {
        hash = map.hash;
    }

    public void clear() // Removes all mappings from this map.
    {
        hash.clear();
    }

    public boolean containsKey(Object key) // Returns true if this map contains a mapping for the specified key.
    {
        if (key == null)
            throw new NullPointerException();
        return hash.containsKey(key);
    }

    public boolean containsValue(Object value) // Returns true if this map maps one or more keys to the specified value.
    {
        if (value == null)
            throw new NullPointerException();
        return hash.contains(value);
    }

    public HSet entrySet() // Returns a set view of the mappings contained in this map.
    {
        HSet set = new SetAdapter();
        Enumeration<Object> onu = hash.keys();
        while (onu.hasMoreElements()) {
            Object o = onu.nextElement();
            MapEntry en = new MapEntry(o, hash.get(o));
            set.add(en);
        }
        return set;
    }

    public boolean equals(Object o) // Compares the specified object with this map for equality.
    {
        if (this.getClass() != o.getClass()) // Checks if the Object o is an instance of mapAdapter.
            return false;
        MapAdapter map = (MapAdapter) o;
        if (hash.size() != map.size()) // Checks if they have the same size (not strictly necessary but it improves the efficiency)
            return false;
      
        if (!this.entrySet().equals(map.entrySet()))
            return false;
        return true;
    }

    public Object get(Object key) // Returns the value to which this map maps the specified key.
    {
        if (key == null)

            throw new NullPointerException();
        return hash.get(key);
    }

    public int hashCode() // Returns the hash code value for this map.
    {
        int hashcode = 0;
        HSet set = this.entrySet(); // Creates a HSet of MapEntry in which all the keys and values are stored.
        HIterator it = set.iterator(); // Creates a HIterator for set.
        while (it.hasNext())
            hashcode += it.next().hashCode(); // Adds the hashcode of each MapEntry, therefore each and every key and value.
        return hashcode;
    }

    public boolean isEmpty() // Returns true if this map contains no key-value mappings.
    {
        return hash.isEmpty();
    }

    public HSet keySet() // Returns a set view of the keys contained in this map.
    {
        HSet set = new SetAdapter(); //Creates a set to store the values.
        Enumeration<Object> keys = hash.keys(); //Creates an Enumeration in which all the keys of the map are stored.
        while (keys.hasMoreElements()) //Copies all the keys from the Enumeration to the set.
            set.add(keys.nextElement());
        return set;
    }

    public Object put(Object key, Object value) // Associates the specified value with the specified key in this map.
    {
        if (key == null || value == null)
            throw new NullPointerException();
        return hash.put(key, value);
    }

    public void putAll(HMap t) // Copies all of the mappings from the specified map to this map.
    {
        if (t == null)
            throw new NullPointerException();
        HSet set = t.entrySet(); // Creates a HSet of MapEntry in which all the keys and values of t are stored.
        HIterator it = set.iterator(); // Creates a HIterator for set.
        while (it.hasNext()) {
            MapEntry entry = (MapEntry) it.next(); // Creates an Object and stores there the key.
            hash.put(entry.getKey(), entry.getValue());// Adds the key and its value to this map.
        }
    }

    public Object remove(Object key) // Removes the mapping for this key from this map if it is present.
    {
        if (key == null)
            throw new NullPointerException();
        return hash.remove(key);
    }

    public int size() // Returns the number of key-value mappings in this map.
    {
        return hash.size();
    }

    public HCollection values() // Returns a collection view of the values contained in this map.
    {
        HCollection cll = new ListAdapter(); // Creates a list to store the values.
        Enumeration<Object> values = hash.elements(); // Creates an Enumeration in which all the values of the map are stored.
        while (values.hasMoreElements()) // Copies all the values from the Enumeration to the list.
            cll.add(values.nextElement());
        return cll;
    }

    private class MapEntry implements HMapEntry {
        private Object key;
        private Object value;

        MapEntry(Object k, Object v) {
            if (k == null || v == null)
                throw new NullPointerException();
            key = k;
            value = v;
        }

        public boolean equals(Object o) //Compares the specified object with this entry for equality.
        {
            if (o.getClass() != this.getClass())
                return false;

            if (((MapEntry) o).getKey().equals(this.getKey()) && ((MapEntry) o).getValue().equals( this.getValue()))
                return true;
            else
            return false;
        }

        public Object getKey() //Returns the key corresponding to this entry.
        {
            return key;
        }

        public Object getValue() // Returns the value corresponding to this entry.
        {
            return value;
        }

        public int hashCode() // Returns the hash code value for this map entry.
        {
            return (this.getKey() == null ? 0 : this.getKey().hashCode())^ (this.getValue() == null ? 0 : this.getValue().hashCode());
        }

        public Object setValue(Object v) // Replaces the value corresponding to this entry with the specified value.
        {
            if (value == null)
                throw new NullPointerException();

            Object temp = value;
            value = v;
            return temp;
        }
    }
}