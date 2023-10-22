package myAdapter;

public interface HList extends HCollection {
    /**Inserts the specified element at the specified position in this list. 
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * 
     * @param index - index at which the specified element is to be inserted.
     * @param element - element to be inserted.
     * @throws UnsupportedOperationException - if the add method is not supported by this list.
     * @throws ClassCastException - if the class of the specified element prevents it from being added to this list.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     * @throws IllegalArgumentException - if some aspect of the specified element prevents it from being added to this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     */
    void add(int index, Object element);

    /**Inserts all of the elements in the specified collection into this list at the specified position. 
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices). 
     * The new elements will appear in this list in the order that they are returned by the specified collection's iterator. 
     * The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. 
     * (Note that this will occur if the specified collection is this list, and it's nonempty.)
     * 
     * @param index - index at which to insert first element from the specified collection.
     * @param c - elements to be inserted into this list.
     * @return true if this list changed as a result of the call.
     * @throws UnsupportedOperationException - if the addAll method is not supported by this list.
     * @throws ClassCastException - if the class of one of elements of the specified collection prevents it from being added to this list.
     * @throws NullPointerException - if the specified collection contains one or more null elements and this list does not support null elements, or if the specified collection is null.
     * @throws IllegalArgumentException - if some aspect of one of elements of the specified collection prevents it from being added to this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     */
    boolean addAll(int index, HCollection c);

    /**Returns the element at the specified position in this list.
     * 
     * @param index - index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     */
    Object get(int index);

    /**Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element. 
     * More formally, returns the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
     * 
     * @param o - element to search for.
     * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws ClassCastException - if the type of the specified element is incompatible with this list (optional).
     * @throws NullPointerException - if the specified element is null and this list does not support null elements (optional)
     */
    int indexOf(Object o);

    /**Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. 
     * More formally, returns the highest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
     * 
     * @param o - element to search for.
     * @return the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws ClassCastException - if the type of the specified element is incompatible with this list (optional).
     * @throws NullPointerException - if the specified element is null and this list does not support null elements (optional).
    
     */
    int lastIndexOf(Object o);

    /**Returns a list iterator of the elements in this list (in proper sequence).
     * 
     * @return a list iterator of the elements in this list (in proper sequence).
     */
    HListIterator listIterator();

    /**Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. 
     * The specified index indicates the first element that would be returned by an initial call to the next method. 
     * An initial call to the previous method would return the element with the specified index minus one.
     * 
     * @param index - index of first element to be returned from the list iterator (by a call to the next method).
     * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     */
    HListIterator listIterator(int index);

    /**Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). 
     * Returns the element that was removed from the list.
     * 
     * @param index - the index of the element to removed.
     * @return the element previously at the specified position.
     * @throws UnsupportedOperationException - if the remove method is not supported by this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
    
     */
    Object remove(int index);

    /**Replaces the element at the specified position in this list with the specified element.
     * 
     * @param index - index of element to replace.
     * @param element - element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws UnsupportedOperationException - if the set method is not supported by this list.
     * @throws ClassCastException - if the class of the specified element prevents it from being added to this list.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     * @throws IllegalArgumentException - if some aspect of the specified element prevents it from being added to this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     */
    Object set(int index, Object element);

    /**Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, the returned list is empty.) 
     * The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa. 
     * The returned list supports all of the optional list operations supported by this list.
     * This method eliminates the need for explicit range operations (of the sort that commonly exist for arrays).
     *  Any operation that expects a list can be used as a range operation by passing a subList view instead of a whole list. For example, the following idiom removes a range of elements from a list:
     *
     *  list.subList(from, to).clear();
     *
     * Similar idioms may be constructed for indexOf and lastIndexOf, and all of the algorithms in the Collections class can be applied to a subList.
     * The semantics of the list returned by this method become undefined if the backing list (i.e., this list) is structurally modified in any way other than via the returned list. 
     * (Structural modifications are those that change the size of this list, or otherwise perturb it in such a fashion that iterations in progress may yield incorrect results.)
     * 
     * @param fromIndex - low endpoint (inclusive) of the subList.
     * @param toIndex - high endpoint (exclusive) of the subList.
     * @return a view of the specified range within this list.
     * @throws IndexOutOfBoundsException - for an illegal endpoint index value (fromIndex < 0 || toIndex > size || fromIndex > toIndex).
     */
    HList subList(int fromIndex, int toIndex);

}