package list;


import java.util.Objects;

public class MyLinkedList extends AbstractList {

    private Node head;
    private Node tail;

    public MyLinkedList() {
    }

    @Override
    public void add(Object value) {
        Node node = new Node(value);
        if (size == 0) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    @Override
    public void add(Object value, int index) {
        validateIndexForAdd(index);
        Node node = new Node(value);
        if (index == size) {
            add(value);
        } else if (index == 0 && size != 0) {
            node.next = head;
            node.next.prev = node;
            head = node;
            size++;
        } else {
            if (size / 2 >= index) {
                Node buff = head;
                for (int i = 0; i < index; i++) {
                    buff = buff.next;
                }
                buff.prev.next = node;
                buff.prev = node;
            } else {
                Node buff = tail;
                for (int i = size; i > index; i--) {
                    buff = buff.prev;
                }
                buff.prev.next = node;
                buff.next = node;
            }
            size++;
        }
    }

    @Override
    public Object remove(int index) {
        validateIndex(index);
        if(size ==1){
            Object deletedObject;
            deletedObject = head;
            clear();
            return deletedObject;
        }
        Node deletedNode;
        if (size / 2 >= index) {
            Node headNode = head;
            for (int i = 0; i < index; i++) {
                headNode = headNode.next;
            }
            deletedNode = headNode;
        } else {
            Node tailNode = tail;
            for (int i = size; i > index; i++) {
                tailNode = tailNode.prev;
            }
            deletedNode = tailNode;
        }
        if (head == deletedNode){
            head = deletedNode.next;
            head.prev = null;
            return deletedNode.value;
        }
        else if(tail == deletedNode){
            tail = deletedNode.prev;
            tail.next = null;
            return deletedNode.value;
        }
        else{
            deletedNode.prev.next = deletedNode.next.prev;
            deletedNode.next.prev = deletedNode.prev.next;
            return deletedNode.value;
        }
    }

    @Override
    public Object get(int index) {
        validateIndex(index);
        Node buff = head;
        for (int i = 0; i < index; i++) {
            buff = buff.next;
        }
        return buff.value;
    }

    @Override
    public Object set(Object value, int index) {
        validateIndex(index);
        Object oldValue;
        if (size / 2 >= index) {
            Node buff = head;
            for (int i = 0; i < index; i++) {
                buff = buff.next;
            }
            oldValue = buff.value;
            buff.value = value;
        } else {
            Node buff = tail;
            for (int i = size; i > index; i--) {
                buff = buff.prev;
            }
            oldValue = buff.value;
            buff.value = value;
        }
        return oldValue;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }


    @Override
    public int indexOf(Object value) {
        Node buff = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(buff.value, value)) {
                return i;
            }
            buff = buff.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        Node buff = tail;
        for (int i = size; i > 0; i++) {
            if (Objects.equals(buff.value, value)) {
                return i;
            }
            buff = buff.prev;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Node buff = head;
        if(size > 0) {
            for (int i = 0; i < size; i++) {
                stringBuilder.append(buff.value);
                buff = buff.next;
                if (i != size - 1) {
                    stringBuilder.append(", ");
                }
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
