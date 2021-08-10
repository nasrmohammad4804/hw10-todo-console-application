import java.util.*;

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

            Node temp = getNode(index);


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

        E result = null;

        if (index == 0) {

            return removeFirst();

        } else if (index == size - 1) {

            tail = tail.prev;
            tail.next = null;

        } else {

            Node temp = getNode(index);
            Node temp2 = temp.prev;
            temp.prev.next = temp.next;
            temp.next.prev = temp2;

            result = temp.data;

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

        return getNode(index).data;
    }

    private Node getNode(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("out of bound !!");
        }

        if (index < size >> 1) {

            Node first = head;
            for (int i = 0; i < index; i++)
                first = first.next;
            return first;
        } else {
            Node last = tail;
            for (int i = size - 1; i > index; i--)
                last = last.prev;

            return last;

        }
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

        getNode(index).data = element;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    //add cache to myLinkedList

    public static class LRUCache<K, V> {

        private class Node {
            K key;
            V value;
            Node pre;
            Node next;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }

        }

        private Node head;
        private Node tail;
        private int capacity;
        private final int defaultCapacity = 5;


        private HashMap<K, Node> map;

        public LRUCache(int capacity) {

            this.capacity = capacity;
            map = new HashMap<>();

        }

        public LRUCache() {

            capacity = defaultCapacity;
            map = new HashMap<>();
        }

        public V get(K key) {

            if (map.containsKey(key)) {

                Node node = map.get(key);
                delete(node);
                setHead(node);
                return node.value;
            }
            return null;
        }

        private void delete(Node node) {


            if (node.next == null){
                tail = node.pre;
                tail.next=null;
            }

            else if (node.pre != null) {
                node.pre.next = node.next;
                node.next.pre = node.pre;

            } else head = node.next;

        }

        private void setHead(Node node) {

            node.next = head;
            node.pre = null;

            if (head != null)
                head.pre = node;

            head = node;

            if (tail == null)
                tail = head;
        }

        public V put(K key, V value) {

            V oldValue = null;
            if (map.containsKey(key)) {

                Node old = map.get(key);
                oldValue = old.value;

                if (!old.value.equals(value))
                    old.value = value;

                delete(old);
                setHead(old);
            } else {
                if (map.size() >= capacity) {

                    map.remove(tail.key);
                    delete(tail);

                }

                Node newNode = new Node(key, value);
                setHead(newNode);
                map.put(key, newNode);
            }

            return oldValue;

        }

        public Set<? extends K> keySet() {

            return map.keySet();
        }

        public Collection<? extends V> values() {

            Collection<V> collection = new ArrayList<>();

            for (Node node = head; node != null; node = node.next) {
                collection.add(node.value);
            }
            return collection;
        }

        @Override
        public String toString() {

            StringBuilder str = new StringBuilder("{");

            for (Node temp = head; temp != null; temp = temp.next) {

                str.append(temp.key).append("=").append(temp.value).append(",");
            }
            return str.substring(0, str.lastIndexOf(",")).concat("}");
        }
    }
}

