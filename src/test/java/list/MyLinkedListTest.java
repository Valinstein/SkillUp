package list;

class MyLinkedListTest extends AbstractListTest {
    @Override
    List getList() {
        return new MyLinkedList();
    }
}