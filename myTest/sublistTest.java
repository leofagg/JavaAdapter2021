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

public class sublistTest {
    private ListAdapter lst1 = new ListAdapter();
    private HList sub;

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
        sub = lst1.subList(2, 6);
    }

    @Test
    public void constructorNotNull() // ListAdapter().subList(int start, int end)
    {
        assertNotNull("Failure - ListAdapter is null.", lst1.subList(2, 4)); // Checks if the new SubList is null
    }

    @Test
    public void constructorEquals()// ListAdapter().subList(int start, int end)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.addAll(lst1);
        assertEquals("Failure - Should be equals", lst1.subList(2, 4), lst1.subList(2, 4)); // Checks if the two SubLists are equals
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void constructorIndexOutOfBounds() // ListAdapter().subList(int start, int end)
    {
        lst1.subList(0, 15);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void constructorIndexOutOfBounds2() // ListAdapter().subList(int start, int end)
    {
        lst1.subList(-9, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void constructorIndexOutOfBounds3() // ListAdapter().subList(int start, int end)
    {
        lst1.subList(4, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addIndexOutOfBounds() // ListAdapter().add(int index, Object element)
    {
        sub.add(-2, 15);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addIndexOutOfBounds2() // ListAdapter().add(int index, Object element)
    {
        sub.add(12, 15);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addIndexOutOfBounds3() // ListAdapter().add(int index, Object element)
    {
        sub.add(12, 6);
    }

    @Test
    public void addIndex() // ListAdapter().add(int index, Object element)
    {
        sub.add(2, 100); // Adds an element in an occupied position. (The element in that position should shift).

        assertNull(lst1.get(0)); // Checks if they are in the right positions in the ListAdapter.
        assertEquals((double) 10 / 3, lst1.get(1));
        assertEquals(-5, lst1.get(2));
        assertEquals('L', lst1.get(3));
        assertEquals(100, lst1.get(4));
        assertEquals("Test", lst1.get(5));
        assertEquals(7, lst1.get(6));
        assertEquals((double) 10 / 3, lst1.get(7));
        assertEquals(true, lst1.get(8));
    }

    @Test
    public void addIndex2() // ListAdapter().add(int index, Object element)
    {
        ListAdapter lst2 = lst1;
        HList sub2 = lst2.subList(2, 6);
        for (int i = 0; i < 4; i++) {
            int number = (int) (Math.random() * 201 - 100);
            sub.add(i, number); // Adds the same random number (between -100 and 100) to both ListAdapters.
            sub2.add(i, number);
        }
        for (int i = 0; i < lst1.size(); i++)
            assertEquals(lst1.get(i), lst2.get(i)); // Checks if the elements were added in the same positions in both the ListAdapters.
    }

    @Test
    public void add() // add(Object element)
    {
        sub.add(0);
        sub.add(1.7);
        sub.add('q');
        assertEquals(-5, sub.get(0));
        assertEquals('L', sub.get(1));
        assertEquals("Test", sub.get(2));
        assertEquals('q', sub.get(3));

        assertEquals(-5, lst1.get(2));
        assertEquals('L', lst1.get(3));
        assertEquals("Test", lst1.get(4));
        assertEquals('q', lst1.get(5));
        assertEquals(1.7, lst1.get(6));
        assertEquals(0, lst1.get(7));
    }

    @Test
    public void add2() // add(Object element)
    {
        ListAdapter lst2 = lst1;
        HList sub2 = lst2.subList(2, 6);
        for (int i = 0; i < 4; i++) {
            int number = (int) (Math.random() * 201 - 100);
            sub.add(number); // Adds the same random number (between -100 and 100) to both ListAdapters.
            sub2.add(number);
        }
        for (int i = 0; i < lst1.size(); i++)
            assertEquals(lst1.get(i), lst2.get(i)); // Checks if the elements were added in the same positions in both the ListAdapters.
    }

    @Test(expected = NullPointerException.class)
    public void addAllNullPointer() // addAll(HCollection c)
    {
        sub.addAll(null);
    }

    @Test
    public void addAll() // addAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();

        lst2.add('k'); // Adds different types of objects to lst2.
        lst2.add("tseT");
        lst2.add(0);
        lst2.add(-7);

        assertTrue(lst2.subList(1, 3).addAll(lst1));

        assertEquals('k', lst2.get(0));
        assertEquals("tseT", lst2.get(1));
        assertEquals(true, lst2.get(2));
        assertEquals((double) 10 / 3, lst2.get(3));
    }

    @Test(expected = NullPointerException.class)
    public void addAllIndexNullPointer() // addAll(int index, HCollection c)
    {
        sub.addAll(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllIndexOutOfBounds() // addAll(int index, HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.add(9);
        sub.addAll(8, lst2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllIndexOutOfBounds2() // addAll(int index, HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.add(9);
        sub.addAll(-1, lst2);
    }

    @Test
    public void addAllIndex() // addAll(int index, HCollection)
    {
        ListAdapter lst2 = new ListAdapter();

        lst2.add('k'); // Adds different types of objects to lst2.
        lst2.add("tseT");
        lst2.add(0);
        lst2.add(-7);

        assertTrue(lst2.subList(1, 3).addAll(lst1));

        assertEquals('k', lst2.get(0));
        assertEquals("tseT", lst2.get(1));
        assertEquals(true, lst2.get(2));
        assertEquals((double) 10 / 3, lst2.get(3));
    }

    @Test
    public void clear() // clear()
    {
        sub.clear();
        assertEquals(lst1.size(), 4);
    }

    @Test
    public void contains()// contains(Object o)
    {
        assertFalse(sub.contains(null)); // Checks if they are in the ListAdapter.
        assertTrue(sub.contains(-5));
        assertFalse(sub.contains((double) 10 / 3));
        assertTrue(sub.contains('L'));
        assertTrue(sub.contains("Test"));
        assertTrue(sub.contains(7));
        assertFalse(sub.contains(true));

        assertFalse(sub.contains("Omniman"));
        assertFalse(sub.contains(964));
    }

    @Test(expected = NullPointerException.class)
    public void containsAllNullPointerException() // containsAll(HCollection c)
    {
        sub.containsAll(null);
    }

    @Test
    public void containsAll()// containsAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();

        lst2.add(-5); // Adds different types of objects to lst2.
        lst2.add(7);
        lst2.add('L');
        lst2.add("Test");

        assertTrue(sub.containsAll(lst2));
        lst2.add(false);
        assertFalse(sub.containsAll(lst2));
    }

    @Test
    public void equals() // equals (Object o)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.addAll(lst1);

        assertTrue(sub.equals(lst2.subList(2, 6)));
        assertFalse(sub.equals(lst2.subList(3, 7)));
        lst2.add(4, 'f');
        assertFalse(sub.equals(lst2.subList(2, 6)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBounds() // get(int index)
    {
        sub.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBounds2() // get(int index)
    {
        sub.get(6);
    }

    @Test
    public void get() // get(int index)
    {
        assertEquals(-5, sub.get(0));
        assertEquals('L', sub.get(1));
        assertEquals("Test", sub.get(2));
        assertEquals(7, sub.get(3));
    }

    @Test
    public void hashCodeTest() // hashCode()
    {
        assertEquals(lst1.subList(0, 1).hashCode(), 31);
        assertNotEquals(lst1.subList(0, 2).hashCode(), 31);
    }

    @Test
    public void indexOf() // indexOf(Object o)
    {
        assertEquals(0, sub.indexOf(-5));
        assertEquals(1, sub.indexOf('L'));
        assertEquals(2, sub.indexOf("Test"));
        assertEquals(3, sub.indexOf(7));
        assertEquals(-1, sub.indexOf(true)); // the list does not contain this element.
    }

    @Test
    public void isEmpty() // isEmpty()
    {
        sub.clear();
        assertFalse(sub.isEmpty());
        sub.clear();
        assertTrue(sub.isEmpty());
    }

    @Test
    public void isEmpty2() // isEmpty()
    {
        lst1.clear();
        assertTrue(sub.isEmpty());
    }

    @Test
    public void iteratorNotnull() // iterator()
    {
        assertNotEquals(null, sub.iterator());
    }

    @Test
    public void iteratorHasNext() // HIterator.hasNext()
    {
        HIterator it = sub.iterator();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextNoSuchElementException() // HIterator.Next()
    {
        lst1.clear();
        HIterator it = sub.iterator();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextNoSuchElementException2() // HIterator.Next()
    {
        HIterator it = sub.iterator();
        while (it.hasNext())
            it.next();
        it.next();
    }

    @Test
    public void iteratorNext() // HIterator.Next()
    {
        HIterator it = sub.iterator();
        assertEquals(-5, it.next());
        assertEquals('L', it.next());
        assertEquals("Test", it.next());
        assertEquals(7, it.next());
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorRemoveIllegalStateException() // HIterator.remove()
    {
        HIterator it = sub.iterator();
        it.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorRemoveIllegalStateException2() // HIterator.remove()
    {

        HIterator it = sub.iterator();
        sub.add((double) 10 / 3);
        it.remove();
        it.remove();
    }

    @Test
    public void iteratorRemove() // HIterator.remove()
    {
        HIterator it = sub.iterator();
        it.next();
        it.remove();
        assertEquals('L', sub.get(0));
        assertEquals('L', it.next());
        it.next();
        it.remove();
        assertEquals(7, sub.get(1));
        assertEquals(4, sub.size());
    }

    @Test
    public void lastIndexOf() // lastIndexOf(Object o)
    {
        sub.add(3, -5);
        assertEquals(3, sub.lastIndexOf(-5));
        assertEquals(1, sub.lastIndexOf('L'));
        assertEquals(2, sub.lastIndexOf("Test"));
        assertEquals(-1, sub.lastIndexOf(7));
        assertEquals(-1, sub.lastIndexOf(false)); // the sublist does not contain this element.
        assertEquals(-1, sub.lastIndexOf((double) 10 / 3)); // the sublist does not contain this element.
    }

    @Test
    public void listIteratorNotnull() // listIterator()
    {
        assertNotEquals(null, sub.listIterator());
    }

    @Test
    public void listIteratorHasNext() // ListIterator.hasNext()
    {
        sub.clear();
        HIterator it = sub.listIterator();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void listIteratorHasPrevious() // ListIterator.hasPrevious()
    {
        HListIterator it = sub.listIterator();
        assertFalse(it.hasPrevious());
        sub.clear();
        sub.clear();
        sub.add(0);
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
        HIterator it = sub.iterator();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void listIteratorNextNoSuchElementException2() // HListIterator.Next()
    {
        HIterator it = sub.listIterator();
        while (it.hasNext())
            it.next();
        it.next();
    }

    @Test
    public void listIteratorNext() // HListIterator.Next()
    {
        HIterator it = sub.listIterator();
        assertEquals(-5, it.next());
        assertEquals('L', it.next());
        assertEquals("Test", it.next());
        assertEquals(7, it.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void listIteratorPreviousNoSuchElementException() // HListIterator.Previous()
    {
        lst1.clear();
        HListIterator it = sub.listIterator();
        it.previous();
    }

    @Test(expected = NoSuchElementException.class)
    public void listIteratorPreviousNoSuchElementException2() // HListIterator.Previous()
    {
        HListIterator it = sub.listIterator();
        while (it.hasNext())
            it.next();
        while (it.hasPrevious())
            it.previous();
        it.previous();
    }

    @Test
    public void listIteratorPrevious() // HListIterator.Previous()
    {
        HListIterator it = sub.listIterator();
        while (it.hasNext())
            it.next();
        assertEquals(7, it.previous());
        assertEquals("Test", it.previous());
        assertEquals('L', it.previous());
        assertEquals(-5, it.previous());
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorRemoveIllegalStateException() // HListIterator.remove()
    {
        HListIterator it = sub.listIterator();
        it.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorRemoveIllegalStateException2() // HListIterator.remove()
    {
        HListIterator it = sub.listIterator();
        it.next();
        it.remove();
        it.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorRemoveIllegalStateException3() // HListIterator.remove()
    {
        HListIterator it = sub.listIterator();
        it.add((double) 10 / 3);
        it.add(5);
        it.remove();
    }

    @Test
    public void listIteratorRemove() // HListIterator.remove()
    {
        HListIterator it = sub.listIterator();
        it.next();
        it.next();
        it.next();
        it.remove();
        assertEquals('L', sub.get(1));
        assertEquals(7, it.next());
    }

    @Test
    public void listIteratorAdd() // HListIterator.add()
    {
        while(!sub.isEmpty())
            sub.remove(0);
        HListIterator it = sub.listIterator();
        it.add(-5);
        it.add('L');
        it.add("Test");
        it.add(7);

        assertEquals(7, it.previous());
        assertEquals("Test", it.previous());
        assertEquals('L', it.previous());
        assertEquals(-5, it.previous());
    }

    @Test
    public void listIteratorNextIndex() // HListIterator.nextIndex()
    {
        HListIterator it = sub.listIterator();

        for (int i = 0; it.hasNext(); i++) {
            assertEquals(i, it.nextIndex());
            it.next();
        }
    }

    @Test
    public void listIteratorPreviousIndex() // HListIterator.previousIndex()
    {
        HListIterator it = sub.listIterator();
        while (it.hasNext())
            it.next();
        for (int i = sub.size() - 1; it.hasPrevious(); i--) {
            assertEquals(i, it.previousIndex());
            it.previous();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorSetIllegalStateException() //HListIterator.set(Object o)
    {
        HListIterator it = sub.listIterator();
        it.set(5);
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorSetIllegalStateException2() //HListIterator.set(Object o)
    {
        HListIterator it = sub.listIterator();
        it.next();
        it.add(8);
        it.set('p');
    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorSetIllegalStateException3() //HListIterator.set(Object o)
    {
        HListIterator it = sub.listIterator();

        it.next();
        it.remove();
        it.set('p');
    }

    @Test
    public void listIteratorSet() //HListIterator.set(Object o)
    {
        HListIterator it = sub.listIterator();
        while (it.hasNext())
            it.next();

        it.previous();
        it.set('p');
        assertEquals('p', sub.get(3));
        it.set(97);
        assertEquals(97, sub.get(3));

        it.previous();
        it.previous();
        it.next();
        it.set("testing");
        assertEquals("testing", sub.get(1));
        it.set(null);
        assertNull(sub.get(1));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorIndexOutOfBounds() // listIterator(int ind)
    {
        HListIterator it = sub.listIterator(-(int) (Math.random() * 100 + 1)); // Creates an HListIterator with a random Capacity between -100 and -1 and checks if the IndexOutOfBoundsException is thrown.

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorIndexOutOfBounds2() // listIterator(int ind)
    {
        HListIterator it = sub.listIterator((int) (Math.random() * 92 + 9)); // Creates an HListIterator with a random Capacity between between 9 and 100 (therefore greater than the size of the sublist), and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void listIteratorIndexNotnull() // listIterator(int ind)
    {
        assertNotEquals(null, sub.listIterator(3));
    }

    @Test
    public void listIteratorIndex() // listIterator(int ind)
    {
        HListIterator it = sub.listIterator(2);
        assertEquals("Test", it.next());
        assertEquals(7, it.next());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexOutOfBounds() // remove(int index)
    {
        sub.remove(-(int) (Math.random() * 100 + 1)); // Removes a null Object at a random index between -100 and 1, and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexOutOfBounds2() // remove(int index)
    {
        sub.remove((int) (Math.random() * 92 + 9)); // removes an Object at a random index between 9 and 100 (therefore greater than the size of the sublist), and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void removeIndex() // remove(int index)
    {
        assertEquals(-5, sub.remove(0));
        assertEquals((double) 10 / 3, sub.remove(3));
        assertEquals(true, sub.remove(3));
        assertEquals(3, sub.size());
    }

    @Test
    public void removeObject() // remove(Object o)
    {
        assertTrue(sub.remove("Test"));
        assertEquals(7, sub.get(2));
        assertFalse(sub.remove("tseT"));
        assertEquals(4, sub.size());

    }

    @Test(expected = NullPointerException.class)
    public void removeAllNullPointerException() // removeAll(HCollection c)
    {
        sub.removeAll(null);
    }

    @Test
    public void removeAllTrue() // removeAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.add("Test");
        lst2.add(7);
        lst2.add((double) 10 / 3);
        lst2.add(true);

        assertTrue(sub.removeAll(lst2));
        assertEquals(4, sub.size());

        assertEquals(-5, sub.get(0));
        assertEquals('L', sub.get(1));
        assertEquals((double) 10 / 3, sub.get(2));
        assertEquals(true, sub.get(3));
    }

    @Test
    public void removeAllFalse() // removeAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.add("tseT");
        lst2.add(96);
        lst2.add(false);

        assertFalse(sub.removeAll(lst2));
        assertEquals(4, sub.size());

    }

    @Test(expected = NullPointerException.class)
    public void retainAllNullPointerException() // retainAll(HCollection c)
    {
        sub.retainAll(null);
    }

    @Test
    public void retainAllTrue() // retainAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.add(null); // Adds different types of objects.
        lst2.add((double) 10 / 3);
        lst2.add(-5);
        lst2.add('L');
        lst2.add('L');

        assertTrue(sub.retainAll(lst2));
        assertEquals(4, sub.size());

        assertEquals(-5, sub.get(0));
        assertEquals('L', sub.get(1));
        assertEquals((double) 10 / 3, sub.get(2));
    }

    @Test
    public void retainAllFalse() // retainAll(HCollection c)
    {
        ListAdapter lst2 = new ListAdapter();
        lst2.addAll(lst1); // Adds different types of objects.

        assertFalse(sub.retainAll(lst2));
        assertEquals(4, sub.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setIndexOutOfBounds() // set(int index, Object element)
    {
        sub.set(-(int) (Math.random() * 100 + 1), 96); // Sets an Object at a random Capacity between -100 and -1 and checks if the IndexOutOfBoundsException is thrown.

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setIndexOutOfBounds2() // set(int index, Object element)
    {
        sub.set((int) (Math.random() * 92 + 9), 96); // Sets an Object at a random Capacity between between 9 and 100 (therefore greater than the size of the list), and checks if the IndexOutOfBoundsException is thrown.
    }

    @Test
    public void set() // set(int index, Object element)
    {
        sub.set(2, 10560);
        sub.set(3, 'R');
        sub.set(1, false);

        assertEquals(10560, sub.get(2));
        assertEquals('R', sub.get(3));
        assertEquals(false, sub.get(1));
    }

    @Test
    public void size() // size()
    {
        assertEquals(4, sub.size());
        sub.clear();
        assertEquals(2, sub.size());
        sub.add(9);
        assertEquals(3, sub.size());
    }

    @Test
    public void toArray() // toArray()
    {
        Object[] arr = sub.toArray();

        assertEquals(-5, arr[0]);
        assertEquals('L', arr[1]);
        assertEquals("Test", arr[2]);
        assertEquals(7, arr[3]);
    }

    @Test(expected = NullPointerException.class)
    public void toArrayObjNullPointerException() // toArray(Object[] a)
    {
        sub.toArray(null);
    }

    @Test
    public void toArrayObjInt() // toArray(Object[] a)
    {
        lst1.clear();
        for (int i = 0; i <= 15; i++)
            lst1.add(-i);
        sub = lst1.subList(3, 12);

        Integer[] arrInt = new Integer[50];
        for (int i = 0; i < arrInt.length; i++)
            arrInt[i] = i;

        Object[] arr = sub.toArray();
        arrInt = (Integer[]) sub.toArray(arrInt);

        for (int i = 0; i < arr.length; i++)
            assertEquals(arr[i], arrInt[i]);
    }

    @Test
    public void toArrayObj() // toArray(Object[] a)
    {
        Integer[] arrInt = new Integer[2];
        for (int i = 0; i < arrInt.length; i++) {
            arrInt[i] = i;
        }
        Object[] arr = sub.toArray();
        Object[] obj = (Object[]) sub.toArray(arrInt);
        assertArrayEquals(arr, obj);
    }
}