package list;

public abstract class AbstractList implements List{

    int size;
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


    void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index: " + index + " is " +
                    "out of range: [0 " + (size) + "]");
        }
    }

    void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index: " + index + " is " +
                    "out of range: [0 " + (size - 1) + "]");
        }
    }
}
