import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList<E> implements Iterable<E>, Cloneable {

    private int size;

    private class Node {

        E data;
        Node next;
        Node prev;

        public Node(E element) {

            this.data = element;
        }
    }

    private Node head;
    private Node tail;

    //add remove empty clear
    public void add(E element) {

        Node node = new Node(element);
        if (head == null) {

            head = tail = node;

            head.prev = null;
            tail.next = null;

        } else {

            tail.next = node;
            node.prev = tail;
            tail = node;
            tail.next = null;
        }
        size++;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            Node temp = head;

            @Override
            public boolean hasNext() {

                return temp != null;
            }

            @Override
            public E next() {

                E data = temp.data;
                temp = temp.next;
                return data;
            }
        };
    }

    public void add(int index, E element) {

        if (!(index >= 0 && index <= size))
            throw new IndexOutOfBoundsException("out of bound !!!");


        Node node = new Node(element);

        if (index == size) {
            add(element);
            return;
        }

        if (index + 1 == size) {

            tail.prev.next = node;
            node.prev = tail.prev;
            node.next = tail;

            tail.prev = node;
            tail.next = null;

        } else if (index == 0) {

            node.prev = null;
            node.next = head;
            head.prev = node;

            head = node;

        } else {
            Node temp = null;
            Node test = head;
            int str = 0;

            while (head != null) {

                if (str == index) {
                    temp = test;
                    break;
                }
                str++;
                test = test.next;
            }

            assert temp != null;
            Node last = temp.prev;

            last.next = node;
            node.prev = last;
            node.next = temp;

            temp.prev = node;

        }
        size++;

    }

    public void addFirst(E element) {

        Node node = new Node(element);

        head.prev = node;

        node.next = head;

        node.prev = null;
        head = node;
    }

    public boolean remove(E element) {

        if (!contains(element))
            return false;

        Node temp = head;
        int index = 0;

        while (temp != null) {

            if (temp.data.equals(element))
                break;
            temp = temp.next;
            index++;
        }

        return remove(index) != null;
    }

    public E remove(int index) {

        E element = get(index);
        E result = null;

        if (index == 0) {

            return removeFirst();

        } else if (index == size - 1) {

            tail = tail.prev;
            tail.next = null;

        } else {

            Node temp = head;

            while (temp != null) {

                if (temp.data.equals(element)) {


                    Node temp2 = temp.prev;
                    temp.prev.next = temp.next;
                    temp.next.prev = temp2;

                    result = temp.data;
                    break;
                }

                temp = temp.next;

            }
        }

        size--;
        return result;

    }

    public E removeFirst() {

        if (size == 0)
            throw new IndexOutOfBoundsException(" empty list !!!");

        E element = head.data;

        head = head.next;
        head.prev = null;
        size--;

        return element;
    }

    public void clear() {

        for (Node node = head; head != null; ) {
            node.prev = null;
            node.next = null;
        }
        head = null;
        tail = null;

    }

    public boolean isEmpty() {

        return (head == null);
    }

    public E get(int index) {

        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("out of bound !!");
        }

        int i = 0;
        for (Node str = head; str != null; i++) {

            if (i == index)
                return str.data;
            str = str.next;
        }

        return null;
    }


    public int size() {
        return size;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder("[");

        for (Node temp = head; temp != null; ) {

            str.append(temp.data).append(",");
            temp = temp.next;
        }
        return str.substring(0, str.lastIndexOf(",")) + "]";
    }

    public boolean contains(E element) {

        for (Node temp = head; temp != null; ) {

            if (temp.data.equals(element))
                return true;

            temp = temp.next;
        }
        return false;
    }

    public void addAll(Collection<? extends E> collection) {

        for (E e : collection)
            add(e);
    }

    public Object[] toArray() {

        Object[] objects = new Object[size];

        for (int index = 0; index < size; index++) {

            objects[index] = get(index);
        }
        return objects;

    }

    public void set(int index, E element) {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("index : " + index + ", size:" + size);

        int i = 0;
        for (Node node = head; node != null; ) {

            if (i==index)
                node.data = element;

            i++;
            node = node.next;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

