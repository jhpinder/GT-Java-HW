/**
 * Your implementation of a linked queue.
 *
 * @author James Pinder
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        T temp = tail.getData();
        LinkedNode<T> current = head;
        if (size == 1) {
            head = null;
            tail = null;
            size = 0;
            return temp;
        }
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        tail = current;
        tail.setNext(null);
        size--;
        return temp;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data passed in was null," +
                " please do not pass in null as argument");
        }
        if (isEmpty()) {
            LinkedNode<T> newNode = new LinkedNode<T>(data);
            head = newNode;
            tail = newNode;
            size = 1;
        } else {
            LinkedNode<T> newNode = new LinkedNode<T>(data, head);
            head = newNode;
            size++;
        }
    }

    @Override
    public boolean isEmpty() {
        return (head == null && tail == null);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
