package myTest;

import myAdapter.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.NoSuchElementException;

public class listAdapterTest {
    private ListAdapter lst1 = new ListAdapter();

    @Before
    public void b4() {
        lst1.add(null); // Adds different types of objects.
        lst1.add((double) 10 / 3);
        lst1.add(-5);
        lst1.add('L');
        lst1.add("Test");
        lst1.add(7);
        lst1.add((double) 10 / 3);
        lst1.add(true);
    }

    @Test
    public void constructorNotNull() // ListAdapter()
    {
       ListAdapter lst2 = new ListAdapter();
        assertNotNull("Failure - ListAdapter is null.", lst2); // Checks if the new ListAdapter is null
    }

    @Test
    public void constructorEquals()//ListAdapter()
    {
        ListAdapter lst2 = new ListAdapter();
        ListAdapter lst3 = new ListAdapter();
        assertEquals("Failure - Should be equals", lst2, lst3); // Checks if the two new empty ListAdapters are equals
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void constructor2IndexOutOfBounds() // ListAdapter(int initialCapacity)
    {
        ListAdapter lst2 = new ListAdapter(-(int) (Math.random() * 100 + 1)); // Creates a ListAdapter with a random Capacity between -100 and -1 and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void constructor2NotNull() // ListAdapter(int initialCapacity)
    {
        ListAdapter lst2 = new ListAdapter((int) (Math.random() * 101));//creates a ListAdapter with a random Capacity between 0 and 100
        assertNotNull("Failure - ListAdapter is null.", lst1);//Checks if the new ListAdapter is null
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void constructor3IndexOutOfBounds() // ListAdapter(int initialCapacity, int capacityIncrement)
    {
        ListAdapter lst2 = new ListAdapter(-(int) (Math.random() * 100 + 1), -(int) (Math.random() * 101)); // Creates a ListAdapter with a random Capacity between -100 and -1 and a random Capacity between -100 and 0, and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void constructor3NotNull() // ListAdapter(int initialCapacity, int capacityIncrement)
    {
        ListAdapter lst2 = new ListAdapter((int) (Math.random() * 101), (int) (Math.random() * 100 + 1)); // creates a ListAdapter with a random Capacity between 0 and 100 and a CapacityIncrement between 1 and 100.
        assertNotNull("Failure - ListAdapter is null.", lst1); // Checks if the new ListAdapter is null
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addIndexOutOfBounds() // add(int index, Object element)
    {
        lst1.add(-(int) (Math.random() * 100 + 1), null); // Adds a null Object at a random index between -100 and 1, and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addIndexOutOfBounds2() // add(int index, Object element)
    {
        lst1.add((int) (Math.random() * 92 + 9), null); // Adds a null Object at a random index between 9 and 100 (therefore greater than the size of the list), and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void addIndex() // add(int index, Object element)
    {
        lst1.add(4, 100); //Adds an element in an occupied position. (The element in that position should shift).

        assertNull(lst1.get(0)); // Checks if they are in the right positions in the ListAdapter.
        assertEquals((double) 10 / 3, lst1.get(1));
        assertEquals(-5, lst1.get(2));
        assertEquals('L', lst1.get(3));
        assertEquals(100,lst1.get(4));
        assertEquals("Test", lst1.get(5));
        assertEquals(7, lst1.get(6));
        assertEquals((double) 10 / 3, lst1.get(7));
        assertEquals(true, lst1.get(8));
    }
    @Test
    public void addIndex2() // add(int index, Object element)
    {
        lst1.clear();
        ListAdapter lst2 = new ListAdapter();
        for (int i = 0; i < 20; i++) {
            int number = (int) (Math.random() * 201 - 100);
            lst1.add(i, number); // Adds the same random number (between -100 and 100) to both ListAdapters.
            lst2.add(i, number);
        }
        for (int i = 0; i < 20; i++)
            assertEquals(lst1.get(i), lst2.get(i)); // Checks if the elements were added in the same positions in both the ListAdapters.
    }

    @Test
    public void add() // add(Object element)
    {
        lst1.clear();
        lst1.add(null); // Adds different types of objects.
        lst1.add((double) 10 / 3);
        lst1.add(-5);
        lst1.add('L');
        lst1.add("Test");
        lst1.add(7);
        lst1.add((double) 10 / 3);
        lst1.add(true);

        assertNull(lst1.get(0)); // Checks if they are in the right positions in the ListAdapter.
        assertEquals((double) 10 / 3, lst1.get(1));
        assertEquals(-5, lst1.get(2));
        assertEquals('L', lst1.get(3));
        assertEquals("Test", lst1.get(4));
        assertEquals(7, lst1.get(5));
        assertEquals((double) 10 / 3, lst1.get(6));
        assertEquals(true, lst1.get(7));
    }

    @Test
    public void add2() // add(Object element)
    {
        lst1.clear();
        ListAdapter lst2 = new ListAdapter();
        for (int i = 0; i < 20; i++) {
            int number = (int) (Math.random() * 201 - 100);
            lst1.add(number); // Adds the same random number (between -100 and 100) to both ListAdapters.
            lst2.add(number);
        }
        for (int i = 0; i < 20; i++)
            assertEquals(lst1.get(i), lst2.get(i)); // Checks if the elements were added in the same positions in both the ListAdapters.
    }

    @Test(expected = NullPointerException.class)
    public void addAllNullPointer() // addAll(HCollection c)
    {
        lst1.addAll(null);
    }

    @Test
    public void addAll() // addAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        ListAdapter lst3 = new ListAdapter();

        lst2.add('k'); // Adds different types of objects to lst2.
        lst2.add("tseT");
        lst2.add(-7);

        lst2.addAll(lst1); // Adds lst1 to lst2.

        assertEquals('k', lst2.get(0)); // Checks if the elements are in the right positions in lst2.
        assertEquals("tseT", lst2.get(1));
        assertEquals(-7, lst2.get(2));
        assertNull(lst2.get(3));
        assertEquals((double) 10 / 3, lst2.get(4));
        assertEquals(-5, lst2.get(5));
        assertEquals('L', lst2.get(6));
        assertEquals("Test", lst2.get(7));
        assertEquals(7, lst2.get(8));
        assertEquals((double) 10 / 3, lst2.get(9));
        assertEquals(true, lst2.get(10));

        lst3.add('k'); // Adds to lst3 the same elements added to lst2.
        lst3.add("tseT");
        lst3.add(-7);
        lst3.addAll(lst1); // Adds lst1 to lst3.
        for (int i = 0; i < lst2.size(); i++)
            assertEquals(lst2.get(i), lst3.get(i)); // Checks if the elements were added in the same positions in both the ListAdapters.
    }

    @Test(expected = NullPointerException.class)
    public void addAllIndexNullPointer() // addAll(int index, HCollection c)
    {
        lst1.addAll(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllIndexOutOfBounds() // addAll(int index, HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.add(9);
        lst1.addAll((int) (Math.random() * 92 + 9), lst2); // Adds a ListAdapter at a random index between 9 and 100 (therefore greater than the size of the list), and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllIndexOutOfBounds2() // addAll(int index, HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.add(9);
        lst1.addAll(-(int) (Math.random() * 100 + 1), lst2); // Adds a ListAdapter at a random index between -1 and -100, and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void addAllIndex() // addAll(int index, HCollection)
    {
        ListAdapter lst2 = new ListAdapter();
        ListAdapter lst3 = new ListAdapter();

        lst2.add('k'); // Adds different types of objects to lst2.
        lst2.add("tseT");
        lst2.add(-7);

        lst2.addAll(2, lst1); // Adds lst1 to lst2.

        assertEquals('k', lst2.get(0)); // Checks if the elements are in the right positions in lst2.
        assertEquals("tseT", lst2.get(1));
        assertNull(lst2.get(2));
        assertEquals((double) 10 / 3, lst2.get(3));
        assertEquals(-5, lst2.get(4));
        assertEquals('L', lst2.get(5));
        assertEquals("Test", lst2.get(6));
        assertEquals(7, lst2.get(7));
        assertEquals((double) 10 / 3, lst2.get(8));
        assertEquals(true, lst2.get(9));
        assertEquals(-7, lst2.get(10));

        lst3.add('k'); // Adds to lst3 the same elements added to lst2.
        lst3.add("tseT");
        lst3.add(-7);
        lst3.addAll(2, lst1); // Adds lst1 to lst3.
        for (int i = 0; i < lst2.size(); i++)
            assertEquals(lst2.get(i), lst3.get(i)); // Checks if the elements were added in the same positions in both the ListAdapters.
    }

    @Test
    public void clear() // clear()
    {
        lst1.clear();
        assertEquals(lst1.size(), 0);
    }

    @Test
    public void contains()// contains(Object o)
    {
        assertTrue(lst1.contains(null)); // Checks if they are in the ListAdapter.
        assertTrue(lst1.contains(-5));
        assertTrue(lst1.contains((double) 10 / 3));
        assertTrue(lst1.contains('L'));
        assertTrue(lst1.contains("Test"));
        assertTrue(lst1.contains(7));
        assertTrue(lst1.contains(true));

        assertFalse(lst1.contains("Omniman"));
        assertFalse(lst1.contains(964));
    }

    @Test(expected = NullPointerException.class)
    public void containsAllNullPointerException() // containsAll(HCollection c)
    {
        lst1.containsAll(null);
    }

    @Test
    public void containsAll()// containsAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();

        lst2.add('k'); // Adds different types of objects to lst2.
        lst2.add("tseT");
        lst2.add(-7);

        lst2.addAll(lst1); // Adds lst1 to lst2.

        assertTrue(lst2.containsAll(lst1));
        assertFalse(lst1.containsAll(lst2));
    }

    @Test
    public void equals() // equals (Object o)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.addAll(lst1);

        assertTrue(lst1.equals(lst2));
        lst2.remove(4);
        assertFalse(lst1.equals(lst2));

        ListAdapter lst3 = new ListAdapter();
        lst3.add(null); // Adds all the elements of lst3 but in different order.
        lst3.add("Test");
        lst3.add(7);
        lst3.add(true);
        lst3.add('L');
        lst3.add(-5);
        lst3.add((double) 10 / 3);
        lst3.add((double) 10 / 3);

        assertFalse(lst1.equals(lst3));

        assertFalse(lst1.equals(null));
        assertFalse(lst1.equals("Test"));
        assertFalse(lst1.equals(75));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBounds() // get(int index)
    {
        lst1.get(-(int) (Math.random() * 100 + 1)); // Gets a null Object at a random index between -100 and 1, and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBounds2() // get(int index)
    {
        lst1.get((int) (Math.random() * 92 + 9)); // Gets an Object at a random index between 9 and 100 (therefore greater than the size of the list), and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void get() // add(int index)
    {
        assertNull(lst1.get(0)); // Checks if they are in the right positions in the ListAdapter.
        assertEquals((double) 10 / 3, lst1.get(1));
        assertEquals(-5, lst1.get(2));
        assertEquals('L', lst1.get(3));
        assertEquals("Test", lst1.get(4));
        assertEquals(7, lst1.get(5));
        assertEquals((double) 10 / 3, lst1.get(6));
        assertEquals(true, lst1.get(7));
    }

    @Test
    public void hashCodeTest() // hashCode()
    {
        ListAdapter lst2 = new ListAdapter();
        assertEquals(lst2.hashCode(), 1);
        lst2.add(null);
        assertEquals(lst2.hashCode(), 31);
        lst2.add("Test");
        assertNotEquals(lst2.hashCode(), 31);
    }

    @Test
    public void indexOf() // indexOf(Object o)
    {
        assertEquals(0, lst1.indexOf(null)); // Checks if the indexes are correct.
        assertEquals(1, lst1.indexOf((double) 10 / 3));
        assertEquals(2, lst1.indexOf(-5));
        assertEquals(3, lst1.indexOf('L'));
        assertEquals(4, lst1.indexOf("Test"));
        assertEquals(5, lst1.indexOf(7));
        assertEquals(1, lst1.indexOf((double) 10 / 3));
        assertEquals(7, lst1.indexOf(true));
        assertEquals(-1, lst1.indexOf(false)); // the list does not contain this element.
    }

    @Test
    public void isEmpty() // isEmpty()
    {
        lst1.clear();
        assertTrue(lst1.isEmpty());
        lst1.add(null);
        assertFalse(lst1.isEmpty());
        lst1.clear();
        assertTrue(lst1.isEmpty());
        lst1.add(97 / 3);
        assertFalse(lst1.isEmpty());
        lst1.clear();
        assertTrue(lst1.isEmpty());
    }

    @Test
    public void iteratorNotnull() // iterator()
    {
        assertNotNull(lst1.iterator());
    }

    @Test
    public void iteratorHasNext() // HIterator.hasNext()
    {
        lst1.clear();
        HIterator it = lst1.iterator();
        assertFalse(it.hasNext());
        lst1.add(0);
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextNoSuchElementException() // HIterator.Next()
    {
        lst1.clear();
        HIterator it = lst1.iterator();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextNoSuchElementException2() // HIterator.Next()
    {
        HIterator it = lst1.iterator();
        while (it.hasNext())
            it.next();
        it.next();
    }

    @Test
    public void iteratorNext() // HIterator.Next()
    {
        HIterator it = lst1.iterator();
        assertEquals(null, it.next());
        assertEquals((double) 10 / 3, it.next());
        assertEquals(-5, it.next());
        assertEquals('L', it.next());
        assertEquals("Test", it.next());
        assertEquals(7, it.next());
        assertEquals((double) 10 / 3, it.next());
        assertEquals(true, it.next());
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorRemoveIllegalStateException() // HIterator.remove()
    {
        HIterator it = lst1.iterator();
        it.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorRemoveIllegalStateException2() // HIterator.remove()
    {

        HIterator it = lst1.iterator();
        lst1.add((double) 10 / 3);
        it.remove();
        it.remove();
    }

    @Test
    public void iteratorRemove() // HIterator.remove()
    {
        HIterator it = lst1.iterator();
        it.next();
        it.next();
        it.next();
        it.remove();
        assertEquals('L', lst1.get(2));
        assertEquals('L', it.next());
        it.next();
        it.next();
        it.next();
        it.remove();
        assertEquals(true, lst1.get(5));
        assertEquals(true, it.next());
        assertEquals(6, lst1.size());
    }

    @Test
    public void lastIndexOf() // lastIndexOf(Object o)
    {
        assertEquals(0, lst1.lastIndexOf(null)); // Checks if the lastIndexes are correct.
        assertEquals(6, lst1.lastIndexOf((double) 10 / 3));
        assertEquals(2, lst1.lastIndexOf(-5));
        assertEquals(3, lst1.lastIndexOf('L'));
        assertEquals(4, lst1.lastIndexOf("Test"));
        assertEquals(5, lst1.lastIndexOf(7));
        assertEquals(6, lst1.lastIndexOf((double) 10 / 3));
        assertEquals(7, lst1.lastIndexOf(true));
        assertEquals(-1, lst1.lastIndexOf(false)); // the list does not contain this element.
    }

    @Test
    public void listIteratorNotnull() // listIterator()
    {
        assertNotEquals(null, lst1.listIterator());
    }

    @Test
    public void listIteratorHasNext() // ListIterator.hasNext()
    {
        lst1.clear();
        HListIterator it = lst1.listIterator();
        assertFalse(it.hasNext());
        lst1.add(0);
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void listIteratorHasPrevious() // ListIterator.hasPrevious()
    {
        HListIterator it = lst1.listIterator();
        assertFalse(it.hasPrevious());
        lst1.add(0);
        assertFalse(it.hasPrevious());
        it.next();
        assertTrue(it.hasPrevious());
        it.remove();
        assertFalse(it.hasPrevious());
    }

    @Test(expected = NoSuchElementException.class)
    public void listIteratorNextNoSuchElementException() // HListIterator.Next()
    {
        lst1.clear();
        HListIterator it = lst1.listIterator();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void listIteratorNextNoSuchElementException2() // HListIterator.Next()
    {
        HListIterator it = lst1.listIterator();
        while (it.hasNext())
            it.next();
        it.next();
    }

    @Test
    public void listIteratorNext() // HListIterator.Next()
    {
        HListIterator it = lst1.listIterator();
        assertNull(it.next());
        assertEquals((double) 10 / 3, it.next());
        assertEquals(-5, it.next());
        assertEquals('L', it.next());
        assertEquals("Test", it.next());
        assertEquals(7, it.next());
        assertEquals((double) 10 / 3, it.next());
        assertEquals(true, it.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void listIteratorPreviousNoSuchElementException() // HListIterator.Previous()
    {
        HListIterator it = lst1.listIterator();
        it.previous();
    }

    @Test(expected = NoSuchElementException.class)
    public void listIteratorPreviousNoSuchElementException2() // HListIterator.Previous()
    {
        HListIterator it = lst1.listIterator();
        while (it.hasNext())
            it.next();
        while (it.hasPrevious())
            it.previous();
        it.previous();
    }

    @Test
    public void listIteratorPrevious() // HListIterator.Previous()
    {
        HListIterator it = lst1.listIterator();
        while (it.hasNext())
            it.next();

        assertEquals(true, it.previous());
        assertEquals((double) 10 / 3, it.previous());
        assertEquals(7, it.previous());
        assertEquals("Test", it.previous());
        assertEquals('L', it.previous());
        assertEquals(-5, it.previous());
        assertEquals((double) 10 / 3, it.previous());
        assertNull(it.previous());
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorRemoveIllegalStateException() // HListIterator.remove()
    {
        HListIterator it = lst1.listIterator();
        it.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorRemoveIllegalStateException2() // HListIterator.remove()
    {
        HListIterator it = lst1.listIterator();
        it.next();
        it.remove();
        it.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorRemoveIllegalStateException3() // HListIterator.remove()
    {
        HListIterator it = lst1.listIterator();
        it.add((double) 10 / 3);
        it.add(5);
        it.remove();
    }

    @Test
    public void listIteratorRemove() // HListIterator.remove()
    {
        HListIterator it = lst1.listIterator();
        it.next();
        it.next();
        it.next();
        it.remove();
        assertEquals('L', lst1.get(2));
        assertEquals('L', it.next());
    }

    @Test
    public void listIteratorAdd() // HListIterator.add()
    {
        lst1.clear();
        HListIterator it = lst1.listIterator();

        it.add(null); // Adds different types of objects.
        it.add((double) 10 / 3);
        it.add(-5);
        it.add('L');
        it.add("Test");
        it.add(7);
        it.add((double) 10 / 3);
        it.add(true);

        assertEquals(true, it.previous());
        assertEquals((double) 10 / 3, it.previous());
        assertEquals(7, it.previous());
        assertEquals("Test", it.previous());
        assertEquals('L', it.previous());
        assertEquals(-5, it.previous());
        assertEquals((double) 10 / 3, it.previous());
        assertNull(it.previous());

    }

    @Test
    public void listIteratorNextIndex() // HListIterator.nextIndex()
    {
        HListIterator it = lst1.listIterator();

        for (int i = 0; it.hasNext(); i++) {
            assertEquals(i, it.nextIndex());
            it.next();
        }
    }

    @Test
    public void listIteratorPreviousIndex() // HListIterator.previousIndex()
    {
        HListIterator it = lst1.listIterator();
        while (it.hasNext())
            it.next();
        for (int i = lst1.size() - 1; it.hasPrevious(); i--) {
            assertEquals(i, it.previousIndex());
            it.previous();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorSetIllegalStateException() //HListIterator.set(Object o)
    {
        HListIterator it = lst1.listIterator();
        it.set(5);
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorSetIllegalStateException2() //HListIterator.set(Object o)
    {
        HListIterator it = lst1.listIterator();
        it.next();
        it.add(8);
        it.set('p');
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorSetIllegalStateException3() //HListIterator.set(Object o)
    {
        HListIterator it = lst1.listIterator();

        it.next();
        it.remove();
        it.set('p');
    }

    @Test
    public void listIteratorSet() //HListIterator.set(Object o)
    {

        HListIterator it = lst1.listIterator();
        while (it.hasNext())
            it.next();

        it.previous();
        it.set('p');
        assertEquals('p', lst1.get(7));
        it.set(97);
        assertEquals(97, lst1.get(7));

        it.previous();
        it.previous();
        it.previous();
        it.previous();
        it.next();
        it.set("testing");
        assertEquals("testing", lst1.get(3));
        it.set(null);
        assertNull(lst1.get(3));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorIndexOutOfBounds() // listIterator(int ind)
    {
        HListIterator it = lst1.listIterator(-(int) (Math.random() * 100 + 1)); // Creates an HListIterator with a random Capacity between -100 and -1 and checks if the IndexOutOfBoundsException is thrown.

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorIndexOutOfBounds2() // listIterator(int ind)
    {
        HListIterator it = lst1.listIterator((int) (Math.random() * 92 + 9)); // Creates an HListIterator with a random Capacity between between 9 and 100 (therefore greater than the size of the list), and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void listIteratorIndexNotnull() // listIterator(int ind)
    {
        assertNotEquals(null, lst1.listIterator(3));
    }

    @Test
    public void listIteratorIndex() // listIterator(int ind)
    {
        HListIterator it = lst1.listIterator(3);
        assertEquals('L', it.next());
        assertEquals("Test", it.next());
        assertEquals(7, it.next());
        assertEquals((double) 10 / 3, it.next());
        assertEquals(true, it.next());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexOutOfBounds() // remove(int index)
    {
        lst1.remove(-(int) (Math.random() * 100 + 1)); // Removes a null Object at a random index between -100 and 1, and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexOutOfBounds2() // remove(int index)
    {

        lst1.remove((int) (Math.random() * 92 + 9)); // removes an Object at a random index between 9 and 100 (therefore greater than the size of the list), and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void removeIndex() // remove(int index)
    {
        assertEquals(-5, lst1.remove(2));
        assertEquals(true, lst1.remove(6));
        assertEquals(6, lst1.size());
    }

    @Test
    public void removeObject() // remove(Object o)
    {
        assertTrue(lst1.remove(true));
        assertTrue(lst1.remove("Test"));
        assertEquals(7, lst1.get(4));
        assertFalse(lst1.remove("tseT"));
        assertEquals(6, lst1.size());

    }

    @Test(expected = NullPointerException.class)
    public void removeAllNullPointerException() // removeAll(HCollection c)
    {
        lst1.removeAll(null);
    }

    @Test
    public void removeAllTrue() // removeAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.addAll(lst1);
        lst1.add("tseT");
        lst1.add(97);
        lst1.add((double) 10 / 23);
        lst1.add(false);

        assertTrue(lst1.removeAll(lst2));
        assertEquals(4, lst1.size());

        assertEquals("tseT", lst1.get(0));
        assertEquals(97, lst1.get(1));
        assertEquals((double) 10 / 23, lst1.get(2));
        assertEquals(false, lst1.get(3));
    }

    @Test
    public void removeAllFalse() // removeAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.add("tseT");
        lst2.add(96);
        lst2.add(false);

        assertFalse(lst1.removeAll(lst2));
        assertEquals(8, lst1.size());

    }

    @Test(expected = NullPointerException.class)
    public void retainAllNullPointerException() // retainAll(HCollection c)
    {
        lst1.retainAll(null);
    }

    @Test
    public void retainAllTrue() // retainAll(HCollection c)
    {
        lst1.clear();
        lst1.add(null); // Adds different types of objects.
        lst1.add((double) 10 / 3);
        lst1.add(-5);
        lst1.add('L');
        lst1.add('L');
        ListAdapter lst2 = new ListAdapter();
        lst2.addAll(lst1);

        lst1.add("Test");
        lst1.add(7);
        lst1.add((double) 10 / 3);
        lst1.add(true);

        assertTrue(lst1.retainAll(lst2));
        assertEquals(6, lst1.size());

        assertNull(lst1.get(0));
        assertEquals((double) 10 / 3, lst1.get(1));
        assertEquals(-5, lst1.get(2));
        assertEquals('L', lst1.get(3));
        assertEquals('L', lst1.get(4));
        assertEquals((double) 10 / 3, lst1.get(5));
    }

    @Test
    public void retainAllTrue2() // retainAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();

        assertTrue(lst1.retainAll(lst2));
        assertEquals(0, lst1.size());
    }

    @Test
    public void retainAllFalse() // retainAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.addAll(lst1);

        assertFalse(lst1.retainAll(lst2));
        assertEquals(8, lst1.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setIndexOutOfBounds() // set(int index, Object element)
    {
        lst1.set(-(int) (Math.random() * 100 + 1), 96); // Sets an Object at a random Capacity between -100 and -1 and checks if the IndexOutOfBoundsException is thrown.

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setIndexOutOfBounds2() // set(int index, Object element)
    {
        lst1.set((int) (Math.random() * 92 + 9), 96); // Sets an Object at a random Capacity between between 9 and 100 (therefore greater than the size of the list), and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void set() // set(int index, Object element)
    {
        lst1.set(2, 10560);
        lst1.set(4, 'R');
        lst1.set(7, false);

        assertEquals(10560, lst1.get(2));
        assertEquals('R', lst1.get(4));
        assertEquals(false, lst1.get(7));
    }

    @Test
    public void size() // size()
    {
        assertEquals(8, lst1.size());
        lst1.clear();
        assertEquals(0, lst1.size());
    }

    @Test
    public void toArray() // toArray()
    {
        Object[] arr = lst1.toArray();

        assertNull(arr[0]);
        assertEquals((double) 10 / 3, arr[1]);
        assertEquals(-5, arr[2]);
        assertEquals('L', arr[3]);
        assertEquals("Test", arr[4]);
        assertEquals(7, arr[5]);
        assertEquals((double) 10 / 3, arr[6]);
        assertEquals(true, arr[7]);
    }

    @Test(expected = NullPointerException.class)
    public void toArrayObjNullPointerException() // toArray(Object[] a)
    {
        lst1.toArray(null);
    }

    @Test
    public void toArrayObjInt() // toArray(Object[] a)
    {
        lst1.clear();
        for (int i = 0; i <= 15; i++)
            lst1.add(-i);

        Integer[] arrInt = new Integer[50];
        for (int i = 0; i < arrInt.length; i++)
            arrInt[i] = i;

        Object[] arr = lst1.toArray();
        arrInt = (Integer[]) lst1.toArray(arrInt);

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
        Object[] arr = lst1.toArray();
        Object[] obj = (Object[]) lst1.toArray(arrInt);
        assertArrayEquals(arr, obj);
    }
}