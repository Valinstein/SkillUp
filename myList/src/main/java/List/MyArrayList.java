package List;

import java.util.Objects;

public class MyArrayList implements List {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] objects;
    private int size;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        objects = new Object[capacity];
        size = 0;
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        indexOutOfRangeCheck(index);
        arrayExtension();
        if (size != 0 && index != size) {
            add(this.objects[size - 1]);
            if (size - 2 - index >= 0) System.arraycopy(objects, index, objects, index + 1, size - 2 - index);
            this.objects[index] = value;
        } else {
            objects[size] = value;
            size++;
        }
    }

    @Override
    public Object remove(int index) {
        indexOutOfBoundsExceptionCheck(index);
        Object removedObject = objects[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(objects, index + 1, objects, index, size - 1 - index);
        }
        size--;
        return removedObject;
    }

    @Override
    public Object get(int index) {
        indexOutOfBoundsExceptionCheck(index);
        return objects[index];
    }

    @Override
    public Object set(Object value, int index) {
        indexOutOfBoundsExceptionCheck(index);
        Object oldObject = objects[index];
        objects[index] = value;
        return oldObject;
    }

    @Override
    public void clear() {
        objects = new Object[10];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1;
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

    private void indexOutOfBoundsExceptionCheck(int index) throws IndexOutOfBoundsException {
        if (size == 0) {
            throw new IndexOutOfBoundsException("the List is empty");
        }
        indexOutOfRangeCheck(index);
    }

    private void indexOutOfRangeCheck(int index) throws IndexOutOfBoundsException  {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index is out of range");
        }
    }

    private void arrayExtension() {
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
}
