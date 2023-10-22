package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

public class SetAdapter implements HSet {//accepts null elements.
    private Vector<Object> vect;

    public SetAdapter() //Creates a new scrolling set.
    {
        vect = new Vector<Object>();
    }

    public SetAdapter(int initialCapacity) //Constructs an empty set with the specified initial capacity..
    {
        if (initialCapacity < 0)
            throw new IndexOutOfBoundsException();
        vect = new Vector<Object>(initialCapacity);
    }

    public SetAdapter(int initialCapacity, int capacityIncrement) //Constructs an empty set with the specified initial capacity and capacity increment.
    {
        if (initialCapacity < 0 || capacityIncrement <= 0)
            throw new IndexOutOfBoundsException();
        vect = new Vector<Object>(initialCapacity, capacityIncrement);
    }

    public boolean add(Object o)// Adds the specified element to this set if it is not already present.
    {
        if (vect.contains(o))
            return false;
        vect.add(o);
        return true;
    }

    public boolean addAll(HCollection c)// Adds all of the elements in the specified collection to this set if they're not already present.
    {
        if (c == null)
            throw new NullPointerException();
        SetAdapter set = (SetAdapter) c;
        boolean added = false; //flag to check if any elements were added
        for (int i = 0; i < set.size(); i++) {
            if (!contains(set.get(i))) {
                vect.addElement(set.get(i));
                added = true;
            }
        }
        return added;
    }

    public void clear()// Removes all of the elements from this set .
    {
        vect.removeAllElements();
    }

    public boolean contains(Object o)// Returns true if this set contains the specified element.
    {
        return vect.contains(o);
    }

    public boolean containsAll(HCollection c)// Returns true if this set contains all of the elements of the specified collection.
    {
        if (c == null)
            throw new NullPointerException();
        SetAdapter set = (SetAdapter) c;
        for (int i = 0; i < set.size(); i++)
            if (!vect.contains(set.get(i)))
                return false;
        return true;
    }

    public boolean equals(Object o)// Compares the specified object with this set for equality.
    {
        if (o == null || this.getClass() != o.getClass()) // Checks if the Object o is an instance of SetAdapter
            return false;
        SetAdapter set = (SetAdapter) o;
        if (vect.size() != set.size()) // Checks if they have the same size
            return false;
        for (int i = 0; i < vect.size(); i++) // Check if the individual elements are the same respectively
            if (this.get(i) != set.get(i) && !this.get(i).equals(set.get(i)))
                return false;
        return true;
    }

    public int hashCode() // Returns the hash code value for this set.
    {
        int hashCode = 1;
        SetIterator i = new SetIterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    public boolean isEmpty() // Returns true if this set contains no elements. (null counts as element)
    {
        if (vect.size() == 0)
            return true;
        else
            return false;
    }

    public HIterator iterator() // Returns an iterator over the elements in this set.
    {
        HIterator it = new SetIterator();
        return it;
    }

    public boolean remove(Object o) // Removes the specified element from this set if it is present.
    {
        return vect.removeElement(o);
    }

    public boolean removeAll(HCollection c) // Removes from this set all of its elements that are contained in the specified collection.
    {
        if (c == null)
            throw new NullPointerException();
        boolean removed = false; // Flag to check if any elements were removed
        SetAdapter set = (SetAdapter) c;
        for (int i = 0; i < set.size(); i++)
            if (vect.removeElement(set.get(i)))
                removed = true;
        return removed;
    }

    public boolean retainAll(HCollection c) // Retains only the elements in this set that are contained in the specified collection.
    {
        if (c == null)
            throw new NullPointerException();
        boolean retained = false; // Flag to check if any elements were retained
        SetAdapter set = (SetAdapter) c;
        for (int i = 0; i < vect.size(); i++)
            if (!set.contains(vect.get(i)))//if the collection doesn't contain the element, it gets removed
            {
                vect.removeElementAt(i);
                retained = true;
                i--;
            }
        return retained;
    }

    public int size()// Returns the number of elements in this set (its cardinality).
    {
        return vect.size();
    }

    public Object[] toArray()// Returns an array containing all of the elements in this set.
    {
        Object[] obj = new Object[vect.size()];
        for (int i = 0; i < obj.length; i++) {
            obj[i] = vect.elementAt(i);
        }
        return obj;
    }

    public Object[] toArray(Object[] a) // Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the specified array.
    {
        if (a == null)
            throw new NullPointerException();
        if (a.length >= vect.size())
            for (int i = 0; i < vect.size(); i++) //va messo a null quando finisce l'i di vect??
                a[i] = vect.elementAt(i);
        else
            a = this.toArray();
        return a;
    }

    public Object get(int index) // Returns the element at the specified position in this set.
    {
        if (index < 0 || index >= vect.size())//if the index is out of range
            throw new IndexOutOfBoundsException();
        else
            return vect.elementAt(index);
    }

    public class SetIterator implements HIterator {
        private int index;
        private int lastOne = -1; // Last object returned by a call to next() or previous(). -1 is an invalid value to understand if the lastOne index is invalid.

        public SetIterator() //default constructor.
        {
            index = 0;
        }

        public boolean hasNext() // Returns true if the iterator has more elements.
        {
            if (!vect.isEmpty() && index < vect.size())
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
                throw new NoSuchElementException(); // if the iterator has no next element.
        }

        public void remove() // Removes from the underlying collection the last element returned by the iterator. This call can only be made once per call to next.
        {
            if (lastOne >= 0) {
                vect.removeElementAt(lastOne);
                lastOne = -1;
                index--;
            } else
                throw new IllegalStateException(); // neither next nor previous have been called, or remove or add have been called after the last call to next or previous.
        }
    }
}
