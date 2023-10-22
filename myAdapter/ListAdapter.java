package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

public class ListAdapter implements HList { //ListAdapter accepts both duplicate and null elements.
    private Vector<Object> vect;

    public ListAdapter() //Creates a new scrolling list.
    {
        vect = new Vector<Object>();
    }

    public ListAdapter(int initialCapacity) //Constructs an empty list with the specified initial capacity.
    {
        if (initialCapacity < 0)
            throw new IndexOutOfBoundsException();
        vect = new Vector<Object>(initialCapacity);
    }

    public ListAdapter(int initialCapacity, int capacityIncrement) //Constructs an empty list with the specified initial capacity and capacity increment.
    {
        if (initialCapacity < 0 || capacityIncrement <= 0)
            throw new IndexOutOfBoundsException();
        vect = new Vector<Object>(initialCapacity, capacityIncrement);
    }

    public void add(int index, Object element) // Inserts the specified element at the specified position in this list
    {
        if (index < 0 || index > vect.size())//if the index is out of range
            throw new IndexOutOfBoundsException();
        else
            vect.insertElementAt(element, index);
    }

    public boolean add(Object o) // Appends the specified element to the end of this list.
    {
        vect.addElement(o);
        return true;
    }

    public boolean addAll(HCollection c) // Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator .
    {
        if (c == null)
            throw new NullPointerException();
        ListAdapter lst = (ListAdapter) c;
        for (int i = 0; i < lst.size(); i++)
            vect.addElement(lst.get(i));

        boolean changed = false; // Flag to check if this ListAdapter has been changed by AddAll.
        if (lst.size() != 0)
            changed = true;
        return changed;
    }

    public boolean addAll(int index, HCollection c) // Inserts all of the elements in the specified collection into this list at the specified position .
    {
        if (index < 0 || index > vect.size())
            throw new IndexOutOfBoundsException();
        if (c == null)
            throw new NullPointerException();
        ListAdapter lst = (ListAdapter) c;
        for (int i = 0; i < lst.size(); i++)
            vect.insertElementAt(lst.get(i), i + index);

        boolean changed = false; // Flag to check if this ListAdapter has been changed by AddAll.
        if (lst.size() != 0)
            changed = true;
        return changed;
    }

    public void clear() // Removes all of the elements from this list .
    {
        vect.removeAllElements();
    }

    public boolean contains(Object o) // Returns true if this list contains the specified element.
    {
        return vect.contains(o);
    }

    public boolean containsAll(HCollection c) // Returns true if this list contains all of the elements of the specified collection.
    {
        if (c == null)
            throw new NullPointerException();
        ListAdapter lst = (ListAdapter) c;
        for (int i = 0; i < lst.size(); i++)
            if (!vect.contains(lst.get(i)))
                return false;
        return true;
    }

    public boolean equals(Object o) // Compares the specified object with this list for equality.
    {
        if (o == null || this.getClass() != o.getClass()) // Checks if the Object o is an instance of listAdapter
            return false;
        ListAdapter lst = (ListAdapter) o;
        if (vect.size() != lst.size()) // Checks if they have the same size
            return false;
        for (int i = 0; i < vect.size(); i++) // Checks if the individual elements are the same respectively
            if (this.get(i) != lst.get(i))
                return false;
        return true;
    }

    public Object get(int index)// Returns the element at the specified position in this list.
    {
        if (index < 0 || index >= vect.size()) // if the index is out of range
            throw new IndexOutOfBoundsException();
        else
            return vect.elementAt(index);
    }

    public int hashCode() // Returns the hash code value for this list.
    {
        int hashCode = 1;
        ListIterator i = new ListIterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    public int indexOf(Object o)// Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
    {
        return vect.indexOf(o);
    }

    public boolean isEmpty()// Returns true if this list contains no elements. (null counts as element)
    {
        if (vect.size() == 0)
            return true;
        else
            return false;
    }

    public HIterator iterator() // Returns an iterator over the elements in this list in proper sequence.
    {
        HIterator it = new ListIterator();
        return it;
    }

    public int lastIndexOf(Object o)// Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
    {
        return vect.lastIndexOf(o);
    }

    public HListIterator listIterator() // Returns a list iterator of the elements in this list (in proper sequence).
    {
        HListIterator listIt = new ListIterator();
        return listIt;
    }

    public HListIterator listIterator(int index) // Returns a list iterator of the elements in this list (in proper sequence) starting at the specified position in this list.
    {
        if (index < 0 || index > size()) //if the index is out of range (index < 0 || index > size()).
            throw new IndexOutOfBoundsException();

        HListIterator listIt = new ListIterator(index);
        return listIt;
    }

    public Object remove(int index) // Removes the element at the specified position in this list.
    {
        if (index < 0 || index >= vect.size())//if the index is out of range
            throw new IndexOutOfBoundsException();

        Object obj = vect.elementAt(index);
        vect.removeElementAt(index);
        return obj;

    }

    public boolean remove(Object o) // Removes the first occurrence in this list of the specified element.
    {
        return vect.removeElement(o);
    }

    public boolean removeAll(HCollection c) // Removes from this list all the elements that are contained in the specified collection.
    {
        if (c == null)
            throw new NullPointerException();
        boolean removed = false; //flag to check if any elements were removed
        ListAdapter lst = (ListAdapter) c;
        for (int i = 0; i < lst.size(); i++)
            if (vect.removeElement(lst.get(i)))
                {removed = true;
                    i--;
                }
        return removed;
    }

    public boolean retainAll(HCollection c) // Retains only the elements in this list that are contained in the specified collection.
    {
        if (c == null)
            throw new NullPointerException();
        boolean retained = false; //flag to check if any elements were retained
        ListAdapter lst = (ListAdapter) c;
        for (int i = 0; i < vect.size(); i++)
            if (!lst.contains(vect.get(i))) //if the collection doesn't contain the element, it gets removed
            {
                vect.removeElementAt(i);
                retained = true;
                i--;
            }
        return retained;
    }

    public Object set(int index, Object element) // Replaces the element at the specified position in this list with the specified element .
    {
        if (index < 0 || index >= vect.size()) // if the index is out of range
            throw new IndexOutOfBoundsException();
        Object obj = vect.elementAt(index);
        vect.setElementAt(element, index);
        return obj;
    }

    public int size() // Returns the number of elements in this list.
    {
        return vect.size();
    }

    public HList subList(int start, int end) // Returns a view of the portion of this list between the specified start, inclusive, and end, exclusive.
    {
        if (start > end || start < 0 || end > vect.size())
            throw new IndexOutOfBoundsException();
        HList sub = new SubList(start, end);
        return sub;
    }

    public Object[] toArray()// Returns an array containing all of the elements in this list in proper sequence.
    {
        Object[] obj = new Object[vect.size()];
        for (int i = 0; i < obj.length; i++)
            obj[i] = vect.elementAt(i);
        return obj;
    }

    public Object[] toArray(Object[] a)// Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
    {
        if (a == null)
            throw new NullPointerException();
        if (a.length >= vect.size())
            for (int i = 0; i < vect.size(); i++)
                a[i] = vect.elementAt(i);
        else
            a = this.toArray();
        return a;
    }

    private class ListIterator implements HListIterator {
        private int index;
        private int lastOne = -1; // Last object returned by a call to next() or previous(). -1 is an invalid value to understand if the lastOne index is invalid.

        public ListIterator() // Default constructor.
        {
            index = 0;
        }

        public ListIterator(int ind) // Constructor starting at the specified position.
        {
            index = ind;
        }

        public boolean hasNext() // Returns true if the iterator has more elements.
        {
            if (!vect.isEmpty() && index < vect.size())
                return true;
            else
                return false;
        }

        public boolean hasPrevious() // Returns true if the iterator has more elements.
        {
            if (index > 0)
                return true;
            else
                return false;
        }

        public Object next() // Returns the next element in the iterator.
        {
            if (hasNext()) {
                index++;
                lastOne = index - 1;
                return vect.elementAt(index - 1);
            } else
                throw new NoSuchElementException();// if the iterator has no next element.
        }

        public Object previous() // Returns the previous element in the iterator.
        {

            if (hasPrevious()) {
                index--;
                lastOne = index;
                return vect.elementAt(index);
            } else
                throw new NoSuchElementException();
        }

        public void remove() // Removes from the underlying collection the last element returned by the iterator. This call can only be made once per call to next or previous. It can be made only if ListIterator.add has not been called after the last call to next or previous.
        {
            if (lastOne >= 0) {
                vect.removeElementAt(lastOne);
                lastOne = -1;
                index--;
            } else
                throw new IllegalStateException();// neither next nor previous have been called, or remove or add have been called after the last call to next or previous.
        }

        public void add(Object o) //Inserts the specified element into the list.
        {
            vect.insertElementAt(o, index);
            index++;
            lastOne = -1; // lastOne is now invalid
        }

        public int nextIndex() // Returns the index of the element that would be returned by a subsequent call to next, or list size if list iterator is at end of list.
        {
            return index;
        }

        public int previousIndex() // Returns the index of the element that would be returned by a subsequent call to previous, or -1 if list iterator is at beginning of list.
        {
            return index - 1;
        }

        public void set(Object o) // Replaces the last element returned by next or previous with the specified element. Unlike remove() it can be called multiple times consecutively .
        {
            if (lastOne >= 0)
                vect.setElementAt(o, lastOne);
            else
                throw new IllegalStateException(); // if neither next nor previous have been called, or remove or add have been called after the last call to next or previous.
        }
    }

    private class SubList implements HList {
        private int start;
        private int end;
        private int elements;

        public SubList(int a, int b) {
            start = a;
            end = b;

        }

        public void add(int index, Object element) // Inserts the specified element at the specified position in this sublist.
        {

            if (index < 0 || index >= this.size())
                throw new IndexOutOfBoundsException();
            vect.insertElementAt(element, index + start);
            size(); // updates the size.

        }

        public boolean add(Object o) // Appends the specified element to the end of this sublist.
        {

            if (vect.size() < end)
                vect.insertElementAt(o, start + this.size());
            else
                vect.insertElementAt(o, end - 1);
            size(); // updates the size.
            return true;
        }

        public boolean addAll(HCollection c) // Appends all of the elements in the specified collection to the end of this sublist, in the order that they are returned by the specified collection's iterator.
        {
            if (c == null)
                throw new NullPointerException();
            ListAdapter lst = (ListAdapter) c;
            for (int i = 0; i < lst.size(); i++)
                this.add(lst.get(i));
            boolean changed = false; // Flag to check if this ListAdapter has been changed by AddAll.
            if (lst.size() != 0)
                changed = true;
            size(); // updates the size.
            return changed;
        }

        public boolean addAll(int index, HCollection c) // Inserts all of the elements in the specified collection into this sublist at the specified position.
        {
            if (c == null)
                throw new NullPointerException();
            if (index < 0 || index >= this.size())
                throw new IndexOutOfBoundsException();
            ListAdapter lst = (ListAdapter) c;
            for (int i = 0; i < lst.size() && index < this.size(); i++) {
                vect.insertElementAt(lst.get(i), index + start);
                index++;
            }
            boolean changed = false; // Flag to check if this ListAdapter has been changed by AddAll.
            if (lst.size() != 0)
                changed = true;
            size(); // updates the size.
            return changed;
        }

        public void clear() // Removes all of the elements from this sublist.
        {
            int size = this.size();
            for (int i = 0; i < size; i++)
                vect.removeElementAt(start);
            size(); // updates the size.

        }

        public boolean contains(Object o) // Returns true if this sublist contains the specified element.
        {
            for (int i = 0; i < this.size(); i++) {
                if (vect.elementAt(start + i) == null && o == null)
                    return true;
                else if (vect.elementAt(start + i).equals(o))
                    return true;
            }
            return false;
        }

        public boolean containsAll(HCollection c) // Returns true if this sublist contains all of the elements of the specified collection.
        {
            if (c == null)
                throw new NullPointerException();
            ListAdapter lst = (ListAdapter) c;
            for (int i = 0; i < lst.size(); i++)
                if (!this.contains(lst.get(i)))
                    return false;
            return true;
        }

        public boolean equals(Object o) // Compares the specified object with this sublist for equality.
        {
            if (o == null || this.getClass() != o.getClass()) // Checks if the Object o is an instance of sublist
                return false;
            HList lst = (HList) o;
            if (this.size() != lst.size()) // Checks if they have the same size
                return false;
            for (int i = 0; i < this.size(); i++) // Checks if the individual elements are the same respectively
                if (this.get(i) != lst.get(i))
                    return false;
            return true;
        }

        public Object get(int index) // Returns the element at the specified position in this sublist.
        {
            if (index < 0 || index >= this.size()) // if the index is out of range
                throw new IndexOutOfBoundsException();
            return vect.elementAt(start + index);
        }

        public int hashCode() // Returns the hash code value for this sublist.
        {
            int hashCode = 1;
            HListIterator i = (SubListIterator) this.iterator();
            while (i.hasNext()) {
                Object obj = i.next();
                hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
            }
            return hashCode;
        }

        public int indexOf(Object o) // Returns the index in this sublist of the first occurrence of the specified element, or -1 if this sublist does not contain this element.
        {
            int index = -1;
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).equals(o) || (this.get(i) == null && o == null)) {
                    index = i;
                    return index;
                }
            }
            return index;
        }

        public boolean isEmpty() // Returns true if this sublist contains no elements.
        {
            return elements == 0;
        }

        public HIterator iterator() // Returns an iterator over the elements in this sublist in proper sequence.
        {
            size();
            HIterator it = new SubListIterator();
            return it;
        }

        public int lastIndexOf(Object o) // Returns the index in this sublist of the last occurrence of the specified element, or -1 if this sublist does not contain this element.
        {
            int index = -1;
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).equals(o) || (this.get(i) == null && o == null)) {
                    index = i;
                }
            }
            return index;
        }

        public HListIterator listIterator() // Returns a list iterator of the elements in this sublist (in proper sequence).
        {
            size();
            HListIterator lt = new SubListIterator();
            return lt;
        }

        public HListIterator listIterator(int index) // Returns a list iterator of the elements in this sublist (in proper sequence), starting at the specified position in this sublist.
        {
            if (index < 0 || index >= this.size())
                throw new IndexOutOfBoundsException();
            HListIterator listIt = new SubListIterator(index);
            return listIt;
        }

        public Object remove(int index) // Removes the element at the specified position in this sublist.
        {
            if (index < 0 || index >= this.size())
                throw new IndexOutOfBoundsException();

            Object obj = vect.elementAt(start + index);
            vect.removeElementAt(start + index);
            size(); // updates the size.
            return obj;

        }

        public boolean remove(Object o) // Removes the first occurrence in this sublist of the specified element.
        {
            for (int i = 0; i < this.size(); i++)
                if (this.get(i).equals(o) || (this.get(i) == null && o == null)) {
                    vect.removeElementAt(start + i);
                    size(); // updates the size.
                    return true;
                }
            return false;
        }

        public boolean removeAll(HCollection c) // Removes from this sublist all the elements that are contained in the specified collection.
        {
            if (c == null)
                throw new NullPointerException();
            boolean removed = false; // flag to check if any elements were removed
            int index = 0; // flag to check if the index goes over the end of the sublist.
            ListAdapter lst = (ListAdapter) c;

            ListAdapter lst2 = new ListAdapter(); // creates a copy of the sublist that will remain unchanged.
            HListIterator it = this.listIterator();
            while (it.hasNext())
                lst2.add(it.next());

            for (int i = 0; i < lst.size() && index < this.size(); i++) {
                if (lst2.contains(lst.get(i))) // checks if the element was in the sublist before the call to removeAll().
                    if (this.remove(lst.get(i))) {
                        removed = true;
                        index++;
                    }
            }
            size(); // updates the size.
            return removed;
        }

        public boolean retainAll(HCollection c) // Retains only the elements in this sublist that are contained in the specified collection.
        {
            if (c == null)
                throw new NullPointerException();
            boolean retained = false; // flag to check if any elements were retained
            HList lst = (HList) c;

            ListAdapter lst2 = new ListAdapter(); // creates a copy of the sublist that will remain unchanged.
            HListIterator it = this.listIterator();
            while (it.hasNext())
                lst2.add(it.next());
            it = lst2.listIterator(); // resets the iterator.

            for (int i = 0; it.hasNext(); i++)
                if (!lst.contains(it.next())) // if the collection doesn't contain the element, it gets removed
                {
                    vect.removeElementAt(i + start);
                    retained = true;
                    i--;
                }
            size(); // updates the size.

            return retained;
        }

        public Object set(int index, Object element) // Replaces the element at the specified position in this sublist with the specified element.
        {
            if (index < 0 || index >= this.size()) // if the index is out of range
                throw new IndexOutOfBoundsException();
            Object obj = vect.elementAt(index + start);
            vect.setElementAt(element, index + start);
            return obj;
        }

        public int size() // Returns the number of elements in this sublist.
        {
            if (vect.size() - start <= 0) // sublist empty.
                elements = 0;
            else if (vect.size() - end >= 0) // sublist full.
                elements = end - start;
            else
                elements = vect.size() - start;
            return elements;
        }

        public HList subList(int start, int end)// Returns a view of the portion of this sublist between the specified start, inclusive, and end, exclusive.
        {
            if (start < 0 || end > this.size() || start > end)
                throw new IndexOutOfBoundsException();
            HList sub = new SubList(start, end);
            return sub;
        }

        public Object[] toArray() // Returns an array containing all of the elements in this sublist in proper sequence.
        {
            Object[] obj = new Object[this.size()];
            for (int i = 0; i < obj.length; i++)
                obj[i] = vect.get(start + i);
            return obj;
        }

        public Object[] toArray(Object[] a) // Returns an array containing all of the elements in this sublist in proper sequence; the runtime type of the returned array is that of the specified array.
        {
            if (a == null)
                throw new NullPointerException();
            if (a.length >= this.size())
                for (int i = 0; i < this.size(); i++)
                    a[i] = vect.elementAt(i + start);
            else
                a = this.toArray();
            return a;
        }

        private class SubListIterator implements HListIterator {
            private int index;
            private int lastOne = -1; // Last object returned by a call to next() or previous(). -1 is an invalid value to understand if the lastOne index is invalid.

            public SubListIterator() // Default constructor.
            {
                index = 0;
            }

            public SubListIterator(int ind) // Constructor starting at the specified position.
            {
                if (ind < 0 || ind >= (end - start))
                    throw new IndexOutOfBoundsException();
                index = ind;
            }

            public void add(Object o) // Inserts the specified element into the list.
            {
                vect.insertElementAt(o, start + index);
                index++;
                lastOne = -1; // lastOne is now invalid
            }

            public boolean hasNext() // Returns true if this list iterator has more elements when traversing the list in the forward direction.
            {
                return index < elements;
            }

            public boolean hasPrevious() // Returns true if this list iterator has more elements when traversing the list in the reverse direction.
            {
                if (elements > 0 && index > 0)
                    return true;
                return false;
            }

            public Object next() // Returns the next element in the list.
            {
                if (hasNext()) {
                    index++;
                    lastOne = index - 1;
                    return vect.elementAt(start + index - 1);
                } else
                    throw new NoSuchElementException();// if the iterator has no next element.
            }

            public int nextIndex() // Returns the index of the element that would be returned by a subsequent call to next.
            {
                return index;
            }

            public Object previous() // Returns the previous element in the list.
            {
                if (this.hasPrevious()) {
                    index--;
                    lastOne = index;
                    return vect.elementAt(start + index);
                } else
                    throw new NoSuchElementException();
            }

            public int previousIndex() // Returns the index of the element that would be returned by a subsequent call to previous.
            {
                return index - 1;
            }

            public void remove() // Removes from the list the last element that was returned by next or previous.
            {
                if (lastOne >= 0) {
                    vect.removeElementAt(lastOne + start);
                    lastOne = -1;
                    index--;
                } else
                    throw new IllegalStateException();// neither next nor previous have been called, or remove or add have been called after the last call to next or previous.
            }

            public void set(Object o) // Replaces the last element returned by next or previous with the specified element.
            {
                if (lastOne >= 0)
                    vect.setElementAt(o, start + lastOne);
                else
                    throw new IllegalStateException(); // if neither next nor previous have been called, or remove or add have been called after the last call to next or previous.
            }
        }
    }

}