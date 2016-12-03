import java.util.AbstractList;
public class LinkedListTester {
    public static void main(String[] args) throws IndexOutOfBoundsException{
        LinkedList<String> ll = new LinkedList<String>();
        ll.addFront("Sam");
        ll.addBack("Jastin");
        ll.addBack("Navell");
        String data = ll.removeLast();
        System.out.println(data);
    }
}

class LinkedList<E> extends AbstractList<E>{
    public Node<E> head;
    public Node<E> tail;
    public int size;

    public E remove(int index) throws IndexOutOfBoundsException{
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = this.head;
        while(index != 0) {
            temp = temp.next;
            --index;
        }
        Node<E> pre = temp.prev;
        Node<E> nex = temp.next;
        if(pre == null && nex == null) {
            this.head = null;
            this.tail = null;
            --size;
        } else if(pre == null) {
            removeFirst();
        } else if(nex == null) {
            removeLast();
        } else {
            pre.next = nex;
            nex.prev = pre;
            --size;
        }
        return temp.data;
    }

    public E removeFirst() throws IndexOutOfBoundsException{
        if(size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = this.head;
        this.head = temp.next;
        this.head.prev = null;
        E data = temp.data;
        temp = null;

        --size;
        return data;
    }

    public E removeLast() throws IndexOutOfBoundsException{
        if(size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = this.tail;
        this.tail = temp.prev;
        this.tail.next = null;
        E data = temp.data;
        temp = null;

        --size;
        return data;
    }

    public E get(int index) throws IndexOutOfBoundsException{
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException(); 
        }
        Node<E> temp = this.head;
        while(index != 0) {
            temp = temp.next;
            --index;
        }
        return temp.data;
    }

    public E set(int index, E data) throws IndexOutOfBoundsException, NullPointerException {
        if(data == null) {
            throw new NullPointerException();
        }
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = this.head;
        while(index != 0) {
            temp = temp.next;
            --index;
        }
        temp.data = data;
        return temp.data;
    }

    public boolean addFront(E data) {
        Node<E> newNode = new Node<E>(data);
        if(head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.head.prev = newNode;
            newNode.next = this.head;
            this.head = newNode;
        }
        ++size;
        return true;
    }

    public boolean addBack(E data) {
        Node<E> newNode = new Node<E>(data);
        if(tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.prev = this.tail;
            this.tail.next = newNode;
            this.tail = newNode;
        }
        ++size;
        return true;
    }

    public void addAtIndex(int index, E data) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) {
            addFront(data);
        } else {
            Node<E> newNode = new Node<E>(data);
            Node<E> temp = this.head;
            while(index != 0) {
                temp = temp.next;
                --index;
            }
            Node<E> prev = temp.prev;

            temp.prev = newNode;
            newNode.next = temp;
            prev.next = newNode;
            newNode.prev = prev;  
            ++size;
        }
    }

    public int size() {
        return this.size;
    }

    public void printForward() {
        Node<E> temp = this.head;
        while(temp != null) {
            System.out.print(temp);
            temp = temp.next;
        }
    }

    public void printBackword() {
        Node<E> temp = this.tail;
        while(temp != null) {
            System.out.print(temp);
            temp = temp.prev;
        }
    }
}

class Node<E> {
    public Node<E> next;
    public Node<E> prev;
    public E data;

    public Node(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        String str = this.data + " ";
        return str;
    }
}
