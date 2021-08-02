package List;

public class MyArrayList implements List {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] objects;
    private int size;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity){
        objects = new Object[capacity];
        size = 0;
    }


    @Override
    public void add(Object value) {
        arrayExtension();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(Object value, int index) {
        indexOutOfBoundsExceptionCheck(index);
        arrayExtension();
        if (size != 0) {
            add(objects[size - 1]);
            for (int i = size - 2; i > index; i--) {
                objects[i] = objects[i - 1];
            }
            objects[index] = value;
        } else {
            add(value);
        }
    }

    @Override
    public Object remove(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        indexOutOfBoundsExceptionCheck(index);
        Object removedObject = objects[index];
        for (int i = index; i < size - 1; i++) {
            objects[i] = objects[i + 1];
        }
        size--;
        return removedObject;
    }

    @Override
    public Object get(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        indexOutOfBoundsExceptionCheck(index);
        return objects[index];
    }

    @Override
    public Object set(Object value, int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
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
        return size==0;
    }

    @Override
    public boolean contains(Object value) {
        if (indexOf(value) == -1) {
            return false;
    }
        return true;
    }

    @Override
    public int indexOf(Object value) {
        int noValueIndex = -1;
        for (int i = 0; i < size; i++) {
            if (value == null) {
                if (objects[i] == null) {
                    return i;
                }
            }
            if (objects[i].equals(value)) {
                return i;
            }
        }
        return noValueIndex;
    }

    @Override
    public int lastIndexOf(Object value) {
        int noValueIndex = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (value == null) {
                if (objects[i] == null) {
                    return i;
                }
            }
            if (objects[i].equals(value)) {
                return i;
            }
        }
        return noValueIndex;
    }

    private void indexOutOfBoundsExceptionCheck(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }


    private int indexOfLastElement() {
        int lastElement = 0;
        for (int i = objects.length - 1; i > 0; i--) {
            if (!objects[i].equals(null)) {
                lastElement = i;
            }
        }
        return lastElement;
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
