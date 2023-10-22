package myAdapter;

public interface HMap {
    /**Removes all mappings from this map.
     * 
     * @throws UnsupportedOperationException - clear is not supported by this map.
     */
    void clear();

    /**Returns true if this map contains a mapping for the specified key. 
     * More formally, returns true if and only if this map contains at a mapping for a key k such that (key==null ? k==null : key.equals(k)). 
     * (There can be at most one such mapping.)
     * 
     * @param key - key whose presence in this map is to be tested.
     * @return true if this map contains a mapping for the specified key.
     * @throws ClassCastException - if the key is of an inappropriate type for this map (optional).
     * @throws NullPointerException - if the key is null and this map does not not permit null keys (optional).
     */
    boolean containsKey(Object key);

    /**Returns true if this map maps one or more keys to the specified value. 
     * More formally, returns true if and only if this map contains at least one mapping to a value v such that (value==null ? v==null : value.equals(v)). 
     * This operation will probably require time linear in the map size for most implementations of the Map interface.
     * 
     * @param value - value whose presence in this map is to be tested.
     * @return true if this map maps one or more keys to the specified value.
     * @throws ClassCastException - if the value is of an inappropriate type for this map (optional).
     * @throws NullPointerException - if the value is null and this map does not not permit null values (optional).
     */
    boolean containsValue(Object value);

    /**Returns a set view of the mappings contained in this map. Each element in the returned set is a Map.Entry. 
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. 
     * If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined. 
     * The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll and clear operations. 
     * It does not support the add or addAll operations.
     * 
     * @return a set view of the mappings contained in this map.
     */
    HSet entrySet();

    /**Compares the specified object with this map for equality. Returns true if the given object is also a map and the two Maps represent the same mappings. 
     * More formally, two maps t1 and t2 represent the same mappings if t1.entrySet().equals(t2.entrySet()). This ensures that the equals method works properly across different implementations of the Map interface.
     * 
     * @param o - object to be compared for equality with this map.
     * @return true if the specified object is equal to this map.
     */
    @Override
    boolean equals(Object o);

    /**Returns the value to which this map maps the specified key. Returns null if the map contains no mapping for this key. 
     * A return value of null does not necessarily indicate that the map contains no mapping for the key; it's also possible that the map explicitly maps the key to null. 
     * The containsKey operation may be used to distinguish these two cases.
     * More formally, if this map contains a mapping from a key k to a value v such that (key==null ? k==null : key.equals(k)), then this method returns v; otherwise it returns null. 
     * (There can be at most one such mapping.)
     * 
     * @param key - key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or null if the map contains no mapping for this key.
     * @throws ClassCastException - if the key is of an inappropriate type for this map (optional).
     * @throws NullPointerException - key is null and this map does not not permit null keys (optional).
     */
    Object get(Object key);

    /**Returns the hash code value for this map. The hash code of a map is defined to be the sum of the hashCodes of each entry in the map's entrySet view. 
     * This ensures that t1.equals(t2) implies that t1.hashCode()==t2.hashCode() for any two maps t1 and t2, as required by the general contract of Object.hashCode.
     * 
     * @return the hash code value for this map.
     */
    @Override
    int hashCode();

    /**Returns true if this map contains no key-value mappings.
     * 
     * @return true if this map contains no key-value mappings.
     */
    boolean isEmpty();

    /**Returns a set view of the keys contained in this map. 
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. 
     * If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined.0 
     * The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll retainAll, and clear operations. 
     * It does not support the add or addAll operations.
     * 
     * @return a set view of the keys contained in this map.
     */
    HSet keySet();

    /**Associates the specified value with the specified key in this map. If the map previously contained a mapping for this key, the old value is replaced by the specified value. 
     * (A map m is said to contain a mapping for a key k if and only if m.containsKey(k) would return true.))
     * 
     * @param key - key with which the specified value is to be associated.
     * @param value - value to be associated with the specified key.
     * @return previous value associated with specified key, or null if there was no mapping for key. A null return can also indicate that the map previously associated null with the specified key, if the implementation supports null values.
     * @throws UnsupportedOperationException - if the put operation is not supported by this map.
     * @throws ClassCastException - if the class of the specified key or value prevents it from being stored in this map.
     * @throws IllegalArgumentException - if some aspect of this key or value prevents it from being stored in this map.
     * @throws NullPointerException - this map does not permit null keys or values, and the specified key or value is null.
     */
    Object put(Object key, Object value);

    /**Copies all of the mappings from the specified map to this map. The effect of this call is equivalent to that of calling put(k, v) on this map once for each mapping from key k to value v in the specified map. 
     * The behavior of this operation is unspecified if the specified map is modified while the operation is in progress.
     * 
     * @param t - Mappings to be stored in this map.
     * @throws UnsupportedOperationException - if the putAll method is not supported by this map.
     * @throws ClassCastException - if the class of a key or value in the specified map prevents it from being stored in this map.
     * @throws IllegalArgumentException - some aspect of a key or value in the specified map prevents it from being stored in this map.
     * @throws NullPointerException - the specified map is null, or if this map does not permit null keys or values, and the specified map contains null keys or values.
     */
    void putAll(HMap t);

    /**Removes the mapping for this key from this map if it is present. 
     * More formally, if this map contains a mapping from key k to value v such that (key==null ? k==null : key.equals(k)), that mapping is removed. 
     * (The map can contain at most one such mapping.)
     * Returns the value to which the map previously associated the key, or null if the map contained no mapping for this key. 
     * (A null return can also indicate that the map previously associated null with the specified key if the implementation supports null values.) 
     * The map will not contain a mapping for the specified key once the call returns.
     * 
     * @param key - key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or null if there was no mapping for key.
     * @throws ClassCastException - if the key is of an inappropriate type for this map (optional).
     * @throws NullPointerException - if the key is null and this map does not not permit null keys (optional).
     * @throws UnsupportedOperationException - if the remove method is not supported by this map.

     */
    Object remove(Object key);

    /**Returns the number of key-value mappings in this map. If the map contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * 
     * @return the number of key-value mappings in this map.
     */
    int size();

    /**Returns a collection view of the values contained in this map. The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa. 
     * If the map is modified while an iteration over the collection is in progress, the results of the iteration are undefined. 
     * The collection supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Collection.remove, removeAll, retainAll and clear operations. 
     * It does not support the add or addAll operations.
     * 
     * @return a collection view of the values contained in this map.
     */
    HCollection values();
}