package list;

import java.util.Iterator;
import java.util.Objects;

public class MyArrayList extends AbstractList {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] objects;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        objects = new Object[capacity];
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        validateIndexForAdd(index);
        ensureCapacity();

        if(index != size) {
            System.arraycopy(objects, index, objects, index + 1, size);
        }
        objects[index] = value;
        size++;
    }

    @Override
    public Object remove(int index) {
        validateIndex(index);
        Object removedObject = objects[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(objects, index + 1, objects, index, size - 1 - index);
        }
        objects[size-1] = null;
        size--;
        return removedObject;
    }

    @Override
    public Object get(int index) {
        validateIndex(index);
        return objects[index];
    }

    @Override
    public Object set(Object value, int index) {
        validateIndex(index);
        Object oldObject = objects[index];
        objects[index] = value;
        return oldObject;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            objects[i] = null;
        }
        size = 0;
    }


    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, objects[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(value, objects[i])) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity() {
        if (size == objects.length) {
            int newSize = (int) (objects.length * 1.5 + 1);
            Object[] newArray = new Object[newSize];
            System.arraycopy(objects, 0, newArray, 0, size);
            objects = newArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(objects[i]);
            if (i != size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
        };
    }
}
