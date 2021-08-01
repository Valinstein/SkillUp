package List;

public class MyArrayList implements List {

    Object[] objects;
    int size;

    public MyArrayList() {
        this.objects = new Object[10];
        this.size = 0;
    }


    @Override
    public void add(Object value) {
        arrayExtension();
        this.objects[size] = value;
        this.size++;
    }

    @Override
    public void add(Object value, int index) {
        indexOutOfBoundsExceptionCheck(index);
        arrayExtension();
        if (size != 0) {
            this.add(this.objects[size - 1]);
            for (int i = size - 2; i > index; i--) {
                this.objects[i] = this.objects[i - 1];
            }
            this.objects[index] = value;
        } else {
            this.add(value);
        }
    }

    @Override
    public Object remove(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        indexOutOfBoundsExceptionCheck(index);
        Object removedObject = this.objects[index];
        for (int i = index; i < this.size - 1; i++) {
            this.objects[i] = this.objects[i + 1];
        }
        this.size--;
        return removedObject;
    }

    @Override
    public Object get(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        indexOutOfBoundsExceptionCheck(index);
        return this.objects[index];
    }

    @Override
    public Object set(Object value, int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        indexOutOfBoundsExceptionCheck(index);
        Object oldObject = this.objects[index];
        this.objects[index] = value;
        return oldObject;
    }

    @Override
    public void clear() {
        this.objects = new Object[10];
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
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
        for (int i = 0; i < this.size; i++) {
            if (value == null) {
                if (this.objects[i] == null) {
                    return i;
                }
            }
            if (this.objects[i].equals(value)) {
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
                if (this.objects[i] == null) {
                    return i;
                }
            }
            if (this.objects[i].equals(value)) {
                return i;
            }
        }
        return noValueIndex;
    }

    private void indexOutOfBoundsExceptionCheck(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
    }


    private int indexOfLastElement() {
        int lastElement = 0;
        for (int i = this.objects.length - 1; i > 0; i--) {
            if (!this.objects[i].equals(null)) {
                lastElement = i;
            }
        }
        return lastElement;
    }

    private void arrayExtension() {
        if (size == this.objects.length) {
            int newSize = (int) (this.objects.length * 1.5 + 1);
            Object[] newArray = new Object[newSize];
            for (int i = 0; i <= this.size; i++) {
                newArray[i] = this.objects[i];
            }
            this.objects = newArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            if (this.objects[i] == null) {
                stringBuilder.append("null");
            }
            if (this.objects[i] != null) {
                stringBuilder.append(this.objects[i].toString());
            }
            if (i != size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
