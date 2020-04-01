/**
 * Your implementation of a DoublyLinkedList
 *
 * @author James Pinder
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data passed in was null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (this.isEmpty()) {
            LinkedListNode<T> add = new LinkedListNode<T>(data);
            head = add;
            tail = add;
            size = 1;
        } else if (size == 1) {
            if (index == 0) {
                this.addToFront(data);
            } else {
                this.addToBack(data);
            }
        } else if (index == size) {
            this.addToBack(data);
        } else if (index == 0) {
            this.addToFront(data);
        } else {
            LinkedListNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            LinkedListNode<T> next = current.getNext();
            LinkedListNode<T> add = new LinkedListNode<T>(data, current, next);
            next.setPrevious(add);
            current.setNext(add);
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data passed in was null");
        }
        if (this.isEmpty()) {
            LinkedListNode<T> add = new LinkedListNode<T>(data);
            tail = add;
            head = add;
            size = 1;
        } else {
            LinkedListNode<T> add = new LinkedListNode<T>(data, null, head);
            head.setPrevious(add);
            head = add;
            size++;
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data passed in was null");
        }
        LinkedListNode<T> add = new LinkedListNode<T>(data, tail, null);
        if (this.isEmpty()) {
            tail = add;
            head = add;
            size = 1;
        } else {
            tail.setNext(add);
            tail = add;
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        LinkedListNode<T> remove = head;
        for (int i = 0; i < index; i++) {
            remove = remove.getNext();
        }
        if (index == 0) {
            return this.removeFromFront();
        } else if (index == size - 1) {
            return this.removeFromBack();
        } else {
            remove.getNext().setPrevious(remove.getPrevious());
            remove.getPrevious().setNext(remove.getNext());
            size--;
            return remove.getData();
        }
    }

    @Override
    public T removeFromFront() {
        if (this.isEmpty()) {
            return null;
        }
        LinkedListNode<T> temp = head;
        if (size == 1) {
            this.clear();
            return (T) temp.getData();
        }
        head = head.getNext();
        head.setPrevious(null);
        size--;
        return (T) temp.getData();
    }

    @Override
    public T removeFromBack() {
        if (this.isEmpty()) {
            return null;
        }
        LinkedListNode<T> temp = tail;
        if (size == 1) {
            return this.removeFromFront();
        }
        tail = tail.getPrevious();
        tail.setNext(null);
        size--;
        return (T) temp.getData();
    }

    @Override
    public boolean removeAllOccurrences(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data passed in was null");
        } else if (this.isEmpty()) {
            return false;
        } else if (size == 1) {
            if (head.getData().equals(data)) {
                this.removeFromFront();
                return true;
            }
            return false;
        }
        int firstSize = size;
        LinkedListNode<T> current = head;
        while (current != tail) {
            if (current.getData().equals(data)) {
                if (current == head) {
                    this.removeFromFront();
                } else {
                    current.getNext().setPrevious(current.getPrevious());
                    current.getPrevious().setNext(current.getNext());
                    size--;
                }
            }
            current = current.getNext();
        }
        if (current.getData().equals(data)) {
            this.removeFromBack();
        }
        return size != firstSize;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            return (T) head.getData();
        }
        if (index == size - 1) {
            return (T) tail.getData();
        }
        LinkedListNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return (T) current.getData();
    }

    @Override
    public Object[] toArray() {
        Object[] out = new Object[size];
        if (!this.isEmpty()) {
            LinkedListNode<T> current = head;
            for (int i = 0; i < size - 1; i++) {
                out[i] = current.getData();
                current = current.getNext();
            }
            out[size - 1] = current.getData();
        }
        return out;
    }

    @Override
    public boolean isEmpty() {
        return (head == null && tail == null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
