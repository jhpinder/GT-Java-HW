/**
 * Your implementation of a linked stack.
 *
 * @author James Pinder
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public T pop() {
        T temp = head.getData();
        head = head.getNext();
        size--;
        return temp;
    }

    @Override
    public void push(T data) {
        LinkedNode<T> newNode = new LinkedNode<T>(data, head);
        head = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this stack.
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
}
