package list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractListTest {
    private List list = getList();

    abstract List getList();

    @Test
    public void testAdd() {
        int size = 5; // < 10 (capacity)
        populateList(list, size);

        assertEquals(size, list.size());
        println("Size: " + list.size());
        println(list);

        list.add("Element New", 2);
        assertEquals("Element 0", list.get(0));
        assertEquals("Element 1", list.get(1));
        assertEquals("Element New", list.get(2));
        assertEquals("Element 2", list.get(3));
        assertEquals("Element 3", list.get(4));
        assertEquals("Element 4", list.get(5));

        assertEquals(size + 1, list.size());
        println("Size: " + list.size());
        println(list);

        list = getList();
        size = 12; // > 10 (capacity)
        populateList(list, size);

        assertEquals(size, list.size());
        println("Size: " + list.size());
        println(list);

        // add: null-1
        list.add(null);
        assertEquals(size + 1, list.size());

        // add: null-2
        list.add(null);
        assertEquals(size + 2, list.size());

    }

    @Test
    public void testAddThrow() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Element", -1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Element", list.size() + 1);
        });

        list.add("Element 0");
        list.add("Element 1");
        list.add("Element 2");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Element", -1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Element", list.size() + 1);
        });
    }

    @Test
    public void testRemove() {


        int size = 5;
        populateList(list, size);

        assertEquals(size, list.size());
        println("Size: " + list.size());
        println(list);

        Object value = list.remove(2);
        assertEquals("Element 2", value);

        assertEquals("Element 0", list.get(0));
        assertEquals("Element 1", list.get(1));
        assertEquals("Element 3", list.get(2));
        assertEquals("Element 4", list.get(3));

        assertEquals(size - 1, list.size()); // size = 4
        println("Size: " + list.size());
        println(list);

        list.add(null);          // index = 4, size = 5
        list.add("New Element"); // index = 5, size = 6
        list.add(null);          // index = 6, size = 7

        value = list.remove(4);
        assertNull(value);
        assertEquals(6, list.size()); // size = 6

        assertEquals("Element 0", list.get(0));
        assertEquals("Element 1", list.get(1));
        assertEquals("Element 3", list.get(2));
        assertEquals("Element 4", list.get(3));
        assertEquals("New Element", list.get(4));
        assertEquals(null, list.get(5));


    }

    @Test
    public void testRemoveThrow() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(0);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(list.size() + 1);
        });

        list.add("Element 0");
        list.add("Element 1");
        list.add("Element 2");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Element", list.size() + 1);
        });
    }

    @Test
    public void testStructure() {


        // isEmpty/size
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());

        int size = 5; // < 10 (capacity)
        populateList(list, size);

        // isEmpty/size
        assertTrue(!list.isEmpty());
        assertEquals(size, list.size());

        list.clear();

        // isEmpty/size
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());

    }

    @Test
    public void testGetSet() {


        int size = 5;
        populateList(list, size);

        Object value = null;
        for (int i = 0; i < list.size(); i++) {
            value = list.get(i);
            assertEquals("Element " + i, value);
        }

        for (int i = 0; i < list.size(); i++) {
            list.set("New Element " + i, i);
            value = list.get(i);
            assertEquals("New Element " + i, value);
        }

        assertNotNull(list.get(3));
        list.set(null, 3);
        assertNull(list.get(3));

    }

    @Test
    public void testGetThrow() {

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(list.size() + 1);
        });

        list.add("Element 0");
        list.add("Element 1");
        list.add("Element 2");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(list.size() + 1);
        });
    }

    @Test
    public void testSetThrow() {

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("Element", -1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("Element", 0);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("Element", list.size() + 1);
        });

        list.add("Element 0");
        list.add("Element 1");
        list.add("Element 2");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("Element", -1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("Element", list.size() + 1);
        });
    }

    @Test
    public void testIndex() {
        int size = 5;
        populateList(list, size);

        Object value = null;
        int index = 0;

        // indexOf/contains
        for (int i = 0; i < list.size(); i++) {
            value = list.get(i);
            index = list.indexOf(value);
            assertEquals(i, index);
            assertTrue(list.contains(value));
        }

        // lastIndexOf
        for (int i = list.size() - 1; i >= 0; i--) {
            value = list.get(i);
            index = list.lastIndexOf(value);
            assertEquals(i, index);
        }

        list = getList();
        list.add("Element 0");
        list.add("Element 1");
        list.add("Element Copy"); // index: 2
        list.add("Element 3");
        list.add("Element Copy");
        list.add("Element Copy"); // index: 5
        list.add("Element 6");
        list.add("Element 7");

        // index: found
        index = list.indexOf("Element Copy");
        assertEquals(2, index);

        index = list.lastIndexOf("Element Copy");
        assertEquals(5, index);

        // index: not found
        index = list.indexOf("Element Zero");
        assertEquals(-1, index);

        index = list.lastIndexOf("Element Zero");
        assertEquals(-1, index);

        // contains: found
        assertTrue(list.contains("Element Copy"));

        // contains: not found
        assertFalse(list.contains("Element Zero"));

        // indexOf/lastIndexOf/contains: null
        assertEquals(-1, list.indexOf(null));
        assertEquals(-1, list.lastIndexOf(null));
        assertFalse(list.contains(null));

        list.add(null);         // index = 8
        list.add("Element 9");  // index = 9
        list.add(null);         // index = 10
        list.add("Element 11"); // index = 11

        assertEquals(8, list.indexOf(null));
        assertEquals(10, list.lastIndexOf(null));
        assertTrue(list.contains(null));

    }

    private void println(Object value) {
        System.out.println(value);
    }

    private void populateList(List list, int size) {
        for (int i = 0; i < size; i++) {
            list.add("Element " + i);
        }
    }

    @Test
    void addWithIndexFirstLastAndMiddleElementTest() {
        list.add("First", 0);
        list.add("Next", 1);
        list.add("Last", 2);
        list.add("Middle", 1);
        assertEquals(4, list.size());
        assertEquals("First", list.get(0));
        assertEquals("Middle", list.get(1));
        assertEquals("Next", list.get(2));
        assertEquals("Last", list.get(3));
    }

    @Test
    void addWithoutIndexTest() {
        list.add("green");
        list.add("white");
        list.add("yellow");

        assertEquals(3, list.size());
        assertEquals("green", list.get(0));
        assertEquals("white", list.get(1));
        assertEquals("yellow", list.get(2));
    }

    @Test
    void addFirstElementByIndexInClearedListTest() {

        list.add("1", 0);
        assertEquals(1, list.size());
        assertEquals("1", list.get(0));
    }

    @Test
    void addNullElementWithIndexTest() {
        list.add("one", 0);
        list.add(null, 0);
        list.add(null, 2);
        list.add(null, 1);

        assertEquals(4, list.size());

        assertNull(list.get(0));
        assertNull(list.get(1));
        assertEquals("one", list.get(2));
        assertNull(list.get(3));
    }

    @Test
    void addNullElementWithoutIndexTest() {
        list.add(null);
        list.add("one");
        list.add(null);

        assertEquals(3, list.size());

        assertNull(list.get(0));
        assertEquals("one", list.get(1));
        assertNull(list.get(2));
    }

    @Test
    void addElementByIndexOutOfBoundsTest() {
        list.add("green");
        list.add("white");
        list.add("yellow");
        assertEquals(3, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("-10", -10);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("20", 20);
        });
    }

    @Test
    void removeFirstLastAndMiddleByIndexTest() {
        list.add("green");
        list.add("white");
        list.add("yellow");
        list.add("white");
        list.add("black");
        assertEquals(5, list.size());

        assertEquals("green", list.remove(0));
        assertEquals("black", list.remove(3));
        assertEquals("yellow", list.remove(1));

        assertEquals(2, list.size());

        assertEquals("white", list.remove(0));
        assertEquals("white", list.remove(0));

        assertEquals(0, list.size());
    }

    @Test
    void removeNullElementByIndexTest() {
        list.add("1");
        list.add(null);
        list.add("2");
        assertNull(list.remove(1));
        assertEquals(2, list.size());
    }

    @Test
    void removeElementByIndexOutOfBoundsTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(0);
        });
        list.add("green");
        list.add("white");
        list.add("yellow");
        assertEquals(3, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(5);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(13);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-10);
        });
    }

    @Test
    void getElementByIndexTest() {
        list.add("green");
        list.add("white");
        list.add("yellow");

        assertEquals("green", list.get(0));
        assertEquals("white", list.get(1));
        assertEquals("yellow", list.get(2));
    }

    @Test
    void getNullElementTest() {
        list.add(null);
        list.add("second");
        assertNull(list.get(0));
    }

    @Test
    void getElementByIndexOutOfBoundsTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });

        list.add("green");
        list.add("white");
        list.add("yellow");
        assertEquals(3, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(10);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-10);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        });
    }

    @Test
    void setFirstLastAndMiddleElementTest() {
        list.add("green");
        list.add("white");
        list.add("yellow");
        assertEquals(3, list.size());

        assertEquals("green", list.set("red", 0));
        assertEquals("white", list.set("brown", 1));
        assertEquals("yellow", list.set("pink", 2));

        assertEquals("red", list.get(0));
        assertEquals("brown", list.get(1));
        assertEquals("pink", list.get(2));

        assertEquals(3, list.size());
    }

    @Test
    void setElementInListWithOneElementTest() {
        list.add("first");
        assertEquals("first", list.set("replacement", 0));
        assertEquals("replacement", list.get(0));
    }

    @Test
    void setNullElementTest() {
        list.add("First");
        list.add(null);
        list.add("Last");

        assertNull(list.set("value", 1));
        assertEquals("value", list.get(1));
    }

    @Test
    void setElementByIndexOutOfBoundsTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("red", 0);
        });

        list.add("green");
        list.add("white");
        list.add("yellow");
        assertEquals(3, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("-2", -2);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("100", 100);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("5", 5);
        });
    }

    @Test
    void indexOfWithExistElementTest() {
        list.add("green");
        list.add("white");
        list.add("yellow");
        list.add("white");
        list.add("black");

        assertEquals(0, list.indexOf("green"));
        assertEquals(1, list.indexOf("white"));
        assertEquals(2, list.indexOf("yellow"));
        assertEquals(4, list.indexOf("black"));

    }

    @Test
    void indexOfWithNonexistentElementTest() {
        list.add("green");
        list.add("white");
        list.add("yellow");

        assertEquals(-1, list.indexOf("red"));
        assertEquals(-1, list.indexOf("blue"));

    }

    @Test
    void lastIndexOfWithExistElementTest() {
        list.add("green");
        list.add("white");
        list.add("yellow");
        list.add("white");
        list.add("black");

        assertEquals(0, list.lastIndexOf("green"));
        assertEquals(3, list.lastIndexOf("white"));
        assertEquals(2, list.lastIndexOf("yellow"));
        assertEquals(4, list.lastIndexOf("black"));
    }

    @Test
    void lastIndexOfWithNonexistentElementTest() {
        list.add("green");
        list.add("white");
        list.add("yellow");

        assertEquals(-1, list.lastIndexOf("brown"));
        assertEquals(-1, list.lastIndexOf("red"));
    }

    @Test
    void indexOfWithNullElementTest() {
        list.add("green");
        list.add(null);
        list.add("yellow");
        list.add(null);
        list.add("black");

        assertEquals(1, list.indexOf(null));

    }

    @Test
    void lastIndexOfWithNullElementTest() {
        list.add("green");
        list.add(null);
        list.add("yellow");
        list.add(null);
        list.add("black");

        assertEquals(3, list.lastIndexOf(null));
    }

    @Test
    void containsNotNullElementTest() {
        list.add("green");
        list.add(null);
        list.add("white");
        list.add("yellow");

        assertTrue(list.contains("green"));
        assertTrue(list.contains("white"));
        assertTrue(list.contains("yellow"));

    }

    @Test
    void containsWithMissingElementElementTest() {
        list.add("green");
        list.add("white");
        list.add("yellow");

        assertFalse(list.contains("pink"));
        assertFalse(list.contains("red"));
    }

    @Test
    void containsNullElementTest() {
        list.add(null);
        list.add("element");
        list.add(null);
        list.add("last");
        assertTrue(list.contains(null));
    }

    @Test
    void clearTest() {
        list.add("green");
        list.add(null);
        list.add("yellow");
        assertEquals(3, list.size());

        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    void isEmptyTest() {
        list.add("green");
        list.add(null);
        list.add("yellow");
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void sizeTest() {
        list.add("green");
        list.add(null);
        list.add("yellow");
        assertEquals(3, list.size());
        list.add("brown");
        assertEquals(4, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void toStringTest() {
        list.add("green");
        list.add(null);
        list.add("yellow");
        assertEquals("[green, null, yellow]", list.toString());
        list.remove(0);
        assertEquals("[null, yellow]", list.toString());
        list.add("red");
        assertEquals("[null, yellow, red]", list.toString());
        list.clear();
        assertEquals("[]", list.toString());
        list.add("first");
        assertEquals("[first]", list.toString());
    }
}
