package myTest;

import myAdapter.*;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

public class setAdapterTest {
    private SetAdapter set1 = new SetAdapter();

    @Before
    public void b4() {
        set1.add(null); // Adds different types of objects.
        set1.add((double) 10 / 3);
        set1.add(-5);
        set1.add('L');
        set1.add("Test");
        set1.add(7);
        set1.add(true);
    }

    @Test
    public void constructorNotNull() // SetAdapter()
    {
        assertNotNull("Failure - SetAdapter is null.", set1); // Checks if the new SetAdapter is null
    }

    @Test
    public void constructorEquals()//SetAdapter()
    {
        set1.clear();
        SetAdapter set2 = new SetAdapter();
        assertEquals("Failure - Should be equals", set1, set2); // Checks if the two new empty SetAdapters are equals
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void constructor2IndexOutOfBounds() // SetAdapter(int initialCapacity)
    {
        SetAdapter set2 = new SetAdapter(-(int) (Math.random() * 100 + 1)); // Creates a SetAdapter with a random Capacity between -100 and -1 and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void constructor2NotNull() {
        SetAdapter set2 = new SetAdapter((int) (Math.random() * 101));//creates a SetAdapter with a random Capacity between 0 and 100
        assertNotNull("Failure - SetAdapter is null.", set1);//Checks if the new SetAdapter is null
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void constructor3IndexOutOfBounds() // SetAdapter(int initialCapacity, int capacityIncrement)
    {
        SetAdapter set2 = new SetAdapter((int) (Math.random() * 100 + 1), -(int) (Math.random() * 101)); // Creates a SetAdapter with a random Capacity between 1 and 100 and a random CapacityIncrement between -100 and 0, and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void constructor3NotNull() // SetAdapter(int initialCapacity, int capacityIncrement)
    {
        SetAdapter set2 = new SetAdapter((int) (Math.random() * 101), (int) (Math.random() * 100 + 1)); // creates a SetAdapter with a random Capacity between 0 and 100 and a CapacityIncrement between 1 and 100.
        assertNotNull("Failure - SetAdapter is null.", set2); // Checks if the new SetAdapter is null
    }

    @Test
    public void add() // add(Object element)
    {
        set1.clear();
        set1.add(null); // Adds different types of objects.
        set1.add((double) 10 / 3);
        set1.add(-5);
        set1.add('L');
        set1.add("Test");
        set1.add(7);
        set1.add(true);

        assertNull(set1.get(0)); // Checks if they are in the right positions in the SetAdapter.
        assertEquals((double) 10 / 3, set1.get(1));
        assertEquals(-5, set1.get(2));
        assertEquals('L', set1.get(3));
        assertEquals("Test", set1.get(4));
        assertEquals(7, set1.get(5));
        assertEquals(true, set1.get(6));
    }

    @Test
    public void add2() // add(Object element)
    {
        set1.clear();
        SetAdapter set2 = new SetAdapter();
        for (int i = 0; i < 20; i++) {
            int number = 0;
            while (set1.contains(number))
                number = (int) (Math.random() * 201 - 100);
            set1.add(number); // Adds the same random number (between -100 and 100) to both SetAdapters.
            set2.add(number);
        }
        for (int i = 0; i < 20; i++)
            assertEquals(set1.get(i), set2.get(i)); // Checks if the elements were added in the same positions in both the SetAdapters.
    }

    @Test
    public void add3() // add(Object element)
    {
        set1.add(null); // Adds duplicate objects.
        assertFalse(set1.add((double) 10 / 3));
        assertEquals(7, set1.size());
    }

    @Test(expected = NullPointerException.class)
    public void addAllNullPointer() // addAll(HCollection c)
    {
        set1.addAll(null);
    }

    @Test
    public void addAll() // addAll(HCollection c)
    {
        SetAdapter set2 = new SetAdapter();
        SetAdapter set3 = new SetAdapter();

        set2.add('k'); // Adds different types of objects to set2.
        set2.add("tseT");
        set2.add(-7);

        set2.addAll(set1); // Adds set1 to set2.

        assertFalse(set2.addAll(set1)); // Tries to add again set1 to set2. It fails because set2 already contains all the elements of set1.

        assertEquals('k', set2.get(0)); // Checks if the elements are in the right positions in set2.
        assertEquals("tseT", set2.get(1));
        assertEquals(-7, set2.get(2));
        assertNull(set2.get(3));
        assertEquals((double) 10 / 3, set2.get(4));
        assertEquals(-5, set2.get(5));
        assertEquals('L', set2.get(6));
        assertEquals("Test", set2.get(7));
        assertEquals(7, set2.get(8));
        assertEquals(true, set2.get(9));

        set3.add('k'); // Adds to set3 the same elements added to set2.
        set3.add("tseT");
        set3.add(-7);
        set3.addAll(set1); // Adds set1 to set3.
        for (int i = 0; i < set2.size(); i++)
            assertEquals(set2.get(i), set3.get(i)); // Checks if the elements were added in the same positions in both the SetAdapters.
    }

    @Test
    public void clear() // clear()
    {
        set1.clear();
        assertEquals(set1.size(), 0);
    }

    @Test
    public void contains()// contains(Object o)
    {
        assertTrue(set1.contains(null)); // Checks if all the elements are in the SetAdapter.
        assertTrue(set1.contains(-5));
        assertTrue(set1.contains((double) 10 / 3));
        assertTrue(set1.contains('L'));
        assertTrue(set1.contains("Test"));
        assertTrue(set1.contains(7));
        assertTrue(set1.contains(true));

        assertFalse(set1.contains("Omniman"));
        assertFalse(set1.contains(964));
    }

    @Test(expected = NullPointerException.class)
    public void containsAllNullPointerException() // containsAll(HCollection c)
    {
        set1.containsAll(null);
    }

    @Test
    public void containsAll()// containsAll(HCollection c)
    {
        SetAdapter set2 = new SetAdapter();

        set2.add('k'); // Adds different types of objects to set2.
        set2.add("tseT");
        set2.add(-7);

        set2.addAll(set1); // Adds set1 to set2.

        assertTrue(set2.containsAll(set1));
        assertFalse(set1.containsAll(set2));
    }

    @Test
    public void equals() // equals (Object o)
    {
        SetAdapter set2 = new SetAdapter();
        set2.addAll(set1);

        assertTrue(set1.equals(set2));
        set2.remove(true);
        assertFalse(set1.equals(set2));

        SetAdapter set3 = new SetAdapter();
        set3.add(null); // Adds all the elements of set3 but in different order.
        set3.add("Test");
        set3.add(7);
        set3.add(true);
        set3.add('L');
        set3.add(-5);
        set3.add((double) 10 / 3);
        set3.add((double) 10 / 3);

        assertFalse(set1.equals(set3));

        assertFalse(set1.equals(null));
        assertFalse(set1.equals("Test"));
        assertFalse(set1.equals(75));
    }

    @Test
    public void hashCodeTest() // hashCode()
    {
        set1.clear();
        assertEquals(set1.hashCode(), 1);
        set1.add(null);
        assertEquals(set1.hashCode(), 31);
        set1.add("Test");
        assertNotEquals(set1.hashCode(), 31);
    }

    @Test
    public void isEmpty() // isEmpty()
    {
        set1.clear();
        assertTrue(set1.isEmpty());
        set1.add(null);
        assertFalse(set1.isEmpty());
        set1.clear();
        assertTrue(set1.isEmpty());
        set1.add(97 / 3);
        assertFalse(set1.isEmpty());
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    @Test
    public void iteratorNotnull() // iterator()
    {
        assertNotEquals(null, set1.iterator());
    }

    @Test
    public void iteratorHasNext() // HIterator.hasNext()
    {
        set1.clear();
        HIterator it = set1.iterator();
        assertFalse(it.hasNext());
        set1.add(0);
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextNoSuchElementException() // HIterator.Next()
    {
        set1.clear();
        HIterator it = set1.iterator();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextNoSuchElementException2() // HIterator.Next()
    {
        HIterator it = set1.iterator();
        while (it.hasNext())
            it.next();
        it.next();
    }

    @Test
    public void iteratorNext() // HIterator.Next()
    {
        HIterator it = set1.iterator();
        assertNull(it.next());
        assertEquals((double) 10 / 3, it.next());
        assertEquals(-5, it.next());
        assertEquals('L', it.next());
        assertEquals("Test", it.next());
        assertEquals(7, it.next());
        assertEquals(true, it.next());
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorRemoveIllegalStateException() // HIterator.remove()
    {
        HIterator it = set1.iterator();
        it.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorRemoveIllegalStateException2() // HIterator.remove()
    {
        HIterator it = set1.iterator();
        set1.add((double) 10 / 3);
        it.remove();
        it.remove();
    }

    @Test
    public void iteratorRemove() // HIterator.remove()
    {
        HIterator it = set1.iterator();
        it.next();
        it.next();
        it.next();
        it.remove();
        assertEquals('L', set1.get(2));
        assertEquals('L', it.next());
        it.next();
        it.next();
        it.remove();
        assertEquals(true, set1.get(4));
        assertEquals(true, it.next());
        assertEquals(5, set1.size());
    }

    @Test
    public void removeObject() // remove(Object o)
    {
        assertTrue(set1.remove(true));
        assertTrue(set1.remove("Test"));
        assertEquals(7, set1.get(4));
        assertFalse(set1.remove("tseT"));
        assertEquals(5, set1.size());
    }

    @Test(expected = NullPointerException.class)
    public void removeAllNullPointerException() // removeAll(HCollection c)
    {
        set1.removeAll(null);
    }

    @Test
    public void removeAllTrue() // removeAll(HCollection c)
    {
        SetAdapter set2 = new SetAdapter();

        set2.add(null); // Adds different types of objects.
        set2.add((double) 10 / 3);
        set2.add(-5);
        set2.add('L');

        assertTrue(set1.removeAll(set2));
        assertEquals(3, set1.size());
        assertEquals("Test", set1.get(0));
        assertEquals(7, set1.get(1));
        assertEquals(true, set1.get(2));
    }

    @Test
    public void removeAllFalse() // removeAll(HCollection c)
    {
        SetAdapter set2 = new SetAdapter();
        set2.add("tseT");
        set2.add(85);
        set2.add(false);

        assertFalse(set1.removeAll(set2));
        assertEquals(7, set1.size());
    }

    @Test(expected = NullPointerException.class)
    public void retainAllNullPointerException() // retainAll(HCollection c)
    {
        set1.retainAll(null);
    }

    @Test
    public void retainAllTrue() // retainAll(HCollection c)
    {
        SetAdapter set2 = new SetAdapter();

        set2.add(null); // Adds different types of objects.
        set2.add((double) 10 / 3);
        set2.add(-5);
        set2.add('L');

        assertTrue(set1.retainAll(set2));
        assertEquals(4, set1.size());

        assertNull(set1.get(0));
        assertEquals((double) 10 / 3, set1.get(1));
        assertEquals(-5, set1.get(2));
        assertEquals('L', set1.get(3));
    }

    @Test
    public void retainAllTrue2() // retainAll(HCollection c)
    {
        SetAdapter set2 = new SetAdapter();
        set2.add("tseT");
        set2.add(85);
        set2.add(false);

        assertFalse(set1.removeAll(set2));
        assertEquals(7, set1.size());

        assertTrue(set1.retainAll(set2));
        assertEquals(0, set1.size());
    }

    @Test
    public void retainAllFalse() // retainAll(HCollection c)
    {
        SetAdapter set2 = new SetAdapter();
        set2.addAll(set1);

        assertFalse(set1.retainAll(set2));
        assertEquals(7, set1.size());
    }

    @Test
    public void size() // size()
    {
        assertEquals(7, set1.size());
        set1.clear();
        assertEquals(0, set1.size());
    }

    @Test
    public void toArray() // toArray()
    {
        Object[] arr = set1.toArray();

        assertNull(arr[0]);
        assertEquals((double) 10 / 3, arr[1]);
        assertEquals(-5, arr[2]);
        assertEquals('L', arr[3]);
        assertEquals("Test", arr[4]);
        assertEquals(7, arr[5]);
        assertEquals(true, arr[6]);
    }

    @Test(expected = NullPointerException.class)
    public void toArrayObjNullPointerException() // toArray(Object[] a)
    {
        set1.toArray(null);
    }

    @Test
    public void toArrayObjInt() // toArray(Object[] a)
    {
        set1.clear();
        for (int i = 0; i <= 15; i++)
            set1.add(-i);

        Integer[] arrInt = new Integer[50];
        for (int i = 0; i < arrInt.length; i++)
            arrInt[i] = i;

        Object[] arr = set1.toArray();
        arrInt = (Integer[]) set1.toArray(arrInt);

        for (int i = 0; i < arr.length; i++)
            assertEquals(arr[i], arrInt[i]);
    }

    @Test
    public void toArrayObj() // toArray(Object[] a)
    {
        Integer[] arrInt = new Integer[5];
        for (int i = 0; i < arrInt.length; i++) {
            arrInt[i] = i;
        }
        Object[] arr = set1.toArray();
        Object[] obj = set1.toArray(arrInt);
        assertArrayEquals(arr, obj);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBounds() // get(int index)
    {
        set1.get(-(int) (Math.random() * 100 + 1)); // Gets a null Object at a random index between -100 and -1, and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBounds2() // get(int index)
    {
        set1.get((int) (Math.random() * 93 + 8)); // Gets an Object at a random index between 8 and 100 (therefore greater than the size of the set), and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void get() // get(int index)
    {
        assertNull(set1.get(0)); // Checks if they are in the right positions in the SetAdapter.
        assertEquals((double) 10 / 3, set1.get(1));
        assertEquals(-5, set1.get(2));
        assertEquals('L', set1.get(3));
        assertEquals("Test", set1.get(4));
        assertEquals(7, set1.get(5));
        assertEquals(true, set1.get(6));
    }

}
