package myAdapter;

public interface HIterator {
    /**Returns true if the iteration has more elements. 
     * (In other words, returns true if next would return an element rather than throwing an exception.)
     * 
     * @return true if the iterator has more elements.
     */
    boolean hasNext();

    /**Returns the next element in the iteration.
     * 
     * @return the next element in the iteration.
     * @throws NoSuchElementException - iteration has no more elements.
     */
    Object next();

    /**Removes from the underlying collection the last element returned by the iterator. This method can be called only once per call to next. 
     * The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any way other than by calling this method.
     * 
     * @throws UnsupportedOperationException - if the remove operation is not supported by this Iterator.
     * @throws IllegalStateException - if the next method has not yet been called, or the remove method has already been called after the last call to the next method.
     */
    void remove();
}