package List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyArrayListTest {
    private List list = new MyArrayList();

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