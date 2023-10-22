package myTest;

import myAdapter.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

public class mapAdapterTest {
    private MapAdapter map1 = new MapAdapter();

    @Before
    public void b4() {
        map1.put(0, (double) 10 / 3); // Puts different types of objects in the map.
        map1.put(1, -5);
        map1.put(2, 'L');
        map1.put(3, "Test");
        map1.put(4, 7);
        map1.put(5, (double) 10 / 3);
        map1.put(6, true);
    }

    @Test
    public void constructorNotNull() // MapAdapter()
    {
        MapAdapter map2 = new MapAdapter();
        assertNotNull("Should not be null", map1);
    }

    @Test
    public void constructor2NotNull() // MapAdapter(MapAdapter map)
    {
        MapAdapter map2 = new MapAdapter();
        assertNotNull("Should not be null", new MapAdapter(map1));
    }

    @Test
    public void clear() // clear()
    {
        map1.clear();
        assertTrue(map1.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void containsKeyNullPointer() //containsKey(Object key)
    {
        map1.containsKey(null);
    }

    @Test
    public void containsKey() //containsKey(Object key)
    {
        assertTrue(map1.containsKey(0));
        assertTrue(map1.containsKey(2));
        assertTrue(map1.containsKey(4));
        assertTrue(map1.containsKey(6));

        assertFalse(map1.containsKey(18));
        assertFalse(map1.containsKey(-6));
    }

    @Test(expected = NullPointerException.class)
    public void containsValueNullPointer() // containsValue(Object value)
    {
        map1.containsValue(null);
    }

    @Test
    public void containsValue() // containsValue(Object value)
    {
        assertTrue(map1.containsValue((double) 10 / 3));
        assertTrue(map1.containsValue(-5));
        assertTrue(map1.containsValue('L'));
        assertTrue(map1.containsValue("Test"));
        assertTrue(map1.containsValue((double) 10 / 3));
        assertTrue(map1.containsValue(true));

        assertFalse(map1.containsValue(854));
        assertFalse(map1.containsValue('f'));

    }

    @Test
    public void entrySet() // entrySet()
    {
        HSet set1 = map1.entrySet();
        Object[] arr = set1.toArray();
        HIterator it = set1.iterator();
        int i = 0;
        while (it.hasNext()) {
            assertEquals(it.next(), arr[i]);
            i++;
        }
        // HSet set2 = map1.entrySet();
        // assertEquals(set1, set2);
    }

    @Test
    public void entrySetAssertSizeEquals() // entrySet()
    {
        HSet set = map1.entrySet();
        assertEquals(map1.size(), set.size());
    }

    @Test
    public void equals() // equals(Object o)
    {
        MapAdapter map2 = new MapAdapter();
        assertFalse(map1.equals(map2));
        map2.put(0, (double) 10 / 3); // Puts different types of objects in the map.
        map2.put(1, -5);
        map2.put(2, 'L');
        map2.put(3, "Test");
        map2.put(4, 7);
        map2.put(5, (double) 10 / 3);
        map2.put(6, true);
        map1.equals(map2);
        assertTrue(map1.equals(map2));
    }

    @Test(expected = NullPointerException.class)
    public void getNullPointer() //get(Object key)
    {
        map1.get(null);
    }

    @Test
    public void get() //get(Object key)
    {
        assertEquals((double) 10 / 3, map1.get(0));
        assertEquals(-5, map1.get(1));
        assertEquals('L', map1.get(2));
        assertEquals("Test", map1.get(3));
        assertEquals(7, map1.get(4));
        assertEquals((double) 10 / 3, map1.get(5));
        assertEquals(true, map1.get(6));

        assertNull(map1.get(8));
        assertNull(map1.get(65));
        assertNull(map1.get(-13));
    }

    @Test
    public void hashCodeTest() // hashCode()
    {
        map1.clear();
        assertEquals(0, map1.hashCode());
        map1.put("Test",9);
        assertNotEquals(map1.hashCode(), 31);
    }

    @Test
    public void isEmpty() //isEmpty()
    {
        assertFalse(map1.isEmpty());
        map1.clear();
        assertTrue(map1.isEmpty());
        assertEquals(0, map1.size());

        map1.put(3, "Test");
        assertFalse(map1.isEmpty());
    }

    @Test
    public void KeySet() // keySet
    {
        HSet set1 = new SetAdapter();
        for (int i = 0; i < 7; i++)
            set1.add(i);
        HSet set2 = map1.keySet();
        assertTrue(set2.containsAll(set1));
        assertTrue(set1.containsAll(set2));
        assertEquals(set1.size(), set2.size());
    }

    @Test(expected = NullPointerException.class)
    public void putNullPointer() //put(Object key, Object value)
    {
        map1.put(0, null);
    }

    @Test(expected = NullPointerException.class)
    public void putNullPointer2() //put(Object key, Object value)
    {
        map1.put(null, 1);
    }

    @Test
    public void put() //put(Object key, Object value)
    {
        assertEquals("Test", map1.get(3));
        map1.put(3, 17);
        assertEquals(17, map1.get(3));
    }

    @Test(expected = NullPointerException.class)
    public void putAllNullPointer() //putAll(HMap t)
    {
        map1.putAll(null);
    }

    @Test
    public void putAll() //putAll(HMap t)
    {
        MapAdapter map2 = new MapAdapter();
        map2.putAll(map1);
        map1.equals(map2);
        assertEquals(map1, map2);
        for (int i = 0; i < map1.size(); i++)
            assertEquals(map1.get(i), map2.get(i));
    }

    @Test(expected = NullPointerException.class)
    public void removeNullPointer() //remove(Object key)
    {
        map1.remove(null);
    }

    @Test
    public void remove() //remove(Object key)
    {
        assertEquals((double) 10 / 3, map1.remove(0));
        assertEquals(-5, map1.remove(1));
        assertEquals('L', map1.remove(2));
        assertEquals("Test", map1.remove(3));
        assertEquals(7, map1.remove(4));
        assertEquals((double) 10 / 3, map1.remove(5));
        assertEquals(true, map1.remove(6));
        
        assertEquals(0, map1.size());
    }

    @Test
    public void size() //size
    {
        assertEquals(7, map1.size());
        assertEquals("Test", map1.remove(3));
        assertEquals(6, map1.size());
        map1.put(56, -5);
        map1.put(12, 'L');
        assertEquals(8, map1.size());
    }

    @Test
    public void values() //values()
    {
        ListAdapter lst2 = (ListAdapter) map1.values();
        ListAdapter lst1 = new ListAdapter();
        lst1.add((double) 10 / 3);
        lst1.add(-5);
        lst1.add('L');
        lst1.add("Test");
        lst1.add(7);
        lst1.add((double) 10 / 3);
        lst1.add(true);
        assertTrue(lst1.containsAll(lst2));
        assertTrue(lst2.containsAll(lst1));
        assertEquals(lst1.size(), lst2.size());
    }

    @Test
    public void mapEntryConstructorNotNull() //MapEntry(Object k, Object v)
    {
        HSet set = map1.entrySet();
        assertNotNull(set);
    }
}