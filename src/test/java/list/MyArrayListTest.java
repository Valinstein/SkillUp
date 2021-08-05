package list;

class MyArrayListTest extends AbstractListTest {
    @Override
    List getList() {
        return new MyArrayList();
    }
}