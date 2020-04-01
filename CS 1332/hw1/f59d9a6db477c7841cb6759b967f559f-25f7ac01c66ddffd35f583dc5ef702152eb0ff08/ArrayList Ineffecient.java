/**
 * Your implementation of an ArrayList.
 *
 * @author James Pinder
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else if (data == null) {
            throw new IllegalArgumentException("Data passed in was null");
        }
        if (backingArray.length == size) { // checks to make sure there's room
            T[] tempArray = (T[]) new Object[size * 2]; // makes bigger array
            for (int i = 0; i < backingArray.length; i++) { // copies
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray; // "renames"
        }
        for (int i = size; i > index; i--) { // shifts existing data
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[index] = data; // adds new data
        size++; // adds 1 to size
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data passed in was null");
        }
        if (backingArray.length == size) { // checks to make sure there's room
            T[] tempArray = (T[]) new Object[size * 2]; // makes bigger array
            for (int i = 0; i < backingArray.length; i++) { // copies
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray; // "renames"
        }
        for (int i = size; i > 0; i--) { // shifts existing data
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[0] = data; // adds new data
        size++; // adds 1 to size
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data passed in was null");
        }
        if (backingArray.length == size) { // checks to make sure there's room
            T[] tempArray = (T[]) new Object[size * 2]; // makes bigger array
            for (int i = 0; i < backingArray.length; i++) { // copies
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray; // "renames"
        }
        backingArray[size] = data;
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T temp = backingArray[index];
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        T temp = backingArray[0];
        for (int i = 0; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        T temp = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
