import java.util.Iterator;
import java.util.Set;
import java.util.Collection;

/**
 * @author jpinder3
 * @param <T> The type of objects contained in this Set
 */
@SuppressWarnings("unchecked")
public class MySet<T> implements Set<T> {

    // The following statements have been provided to you.
    // Do not modify these statements.
    private T[] backingArray;
    private int numElements;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * add function for MySet
     * @param  t Generic type to add
     * @return  returns false if the array already contains t
     */
    public boolean add(T t) {
        updateNumElements();
        if (t.equals(null)) {
            throw new NullPointerException("You passed in null");
        }
        if (numElements != 0) {
            for (T t1 : backingArray) {
                if (t1 != null && t.equals(t1)) {
                    return false;
                }
            }
        }
        T[] newArr = (T[]) new Object[numElements + 1];
        int i = 0;
        if (numElements != 0) {
            for (T t1 : backingArray) {
                newArr[i] = t1;
                i++;
            }
        }
        newArr[i] = t;
        numElements++;
        backingArray = newArr;
        updateNumElements();
        return true;
    }

    /**
     * Adds all elements of c to this set
     * @param c Collection to add to this set
     * @return  returns false if none were added
     */
    public boolean addAll(Collection<? extends T> c) {
        updateNumElements();
        boolean check = false;
        for (T t : c) {
            if (this.add(t) && !check) {
                check = true;
            }
        }
        updateNumElements();
        return check;
    }

    /**
     * Removes all T from this set
     */
    public void clear() {
        numElements = 0;
        T[] newArr = (T[]) new Object[0];
        backingArray = newArr;
    }

    /**
     * Returns whether or not this set contains a given object
     * @param  o Object to test
     * @return   True if this set contains o, false otherwise
     */
    public boolean contains(Object o) {
        updateNumElements();
        if (numElements != 0 && o != null) {
            for (T t : backingArray) {
                if (t != null && t.equals(o)) {
                    updateNumElements();
                    return true;
                }
            }
        }
        updateNumElements();
        return false;
    }

    /**
     * Returns reut if this set contains all of the elements in c
     * @param  c Collection to check
     * @return   True if all elements are present in this set, false otherwise
     */
    public boolean containsAll(Collection<?> c) {
        updateNumElements();
        boolean check = false;
        for (Object o : c) {
            check = this.contains(o);
            if (!check) {
                updateNumElements();
                return false;
            }
        }
        updateNumElements();
        return check;
    }

    /**
     * Equals function for MySet
     * @param  o Object to check
     * @return   True if equal, false otherwise
     */
    public boolean equals(Object o) {
        updateNumElements();
        return o != null && (o instanceof MySet)
            && this.size() == ((MySet<T>) o).size()
            && this.containsAll((Collection<?>) o);
    }

    /**
     * Empty function for MySet
     * @return Returns true if MySet is Empty, false otherwise
     */
    public boolean isEmpty() {
        updateNumElements();
        if (numElements == 0) {
            return true;
        }
        for (T t : backingArray) {
            if (!t.equals(null)) {
                updateNumElements();
                return false;
            }
        }
        updateNumElements();
        return true;
    }

    /**
     * Removes o from MySet
     * @param  o Object to remove
     * @return   Returns true if o was removed, false otherwise
     */
    public boolean remove(Object o) {
        updateNumElements();
        for (int i = 0; i < numElements; i++) {
            if (backingArray[i].equals(o)) {
                removeArr(i);
                updateNumElements();
                return true;
            }
        }
        updateNumElements();
        return false;
    }
    private boolean removeNull() {
        updateNumElements();
        if (numElements != 0) {
            for (int i = 0; i < backingArray.length; i++) {
                if (backingArray[i] == null) {
                    if (!removeNull()) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    private void removeArr(int index) {
        updateNumElements();
        T[] newArr = (T[]) new Object[numElements - 1];
        for (int i = 0; i < index; i++) {
            newArr[i] = backingArray[i];
        }
        for (int i = index; i < numElements - 1; i++) {
            newArr[i] = backingArray[i + 1];
        }
        backingArray = newArr;
        numElements--;
    }
    private void updateNumElements() {
        int i = 0;
        if (numElements != 0) {
            for (T t : backingArray) {
                if (t != null) {
                    i++;
                }
            }
            numElements = i;
        }
    }
    /**
     * removeAll function for MySet
     * @param  c Collection to remove
     * @return   Returns true if MySet changed at all, false otherwise
     */
    public boolean removeAll(Collection<?> c) {
        updateNumElements();
        boolean check = false;
        for (Object o : c) {
            if (this.remove(o) && !check) {
                check = true;
            }
        }
        updateNumElements();
        return check;
    }

    /**
     * Keeps all elements in c
     * @param  c Collection to keep
     * @return   Returns true if changes were made to MySet, false otherwise
     */
    public boolean retainAll(Collection<?> c) {
        updateNumElements();
        boolean check = false;
        if (numElements != 0) {
            for (T t : backingArray) {
                if (!c.contains(t)) {
                    this.remove(t);
                    numElements--;
                    check = true;
                }
            }
        }
        updateNumElements();
        return check;
    }

    /**
     * Getter for size of MySet
     * @return Returns numElements as int
     */
    public int size() {
        updateNumElements();
        return numElements;
    }


    public Object[] toArray() {
        T[] newArr = (T[]) new Object[numElements];
        int i = 0;
        if (numElements != 0) {
            for (T t : backingArray) {
                if (t != null) {
                    newArr[i] = t;
                    i++;
                }
            }
        }
        return newArr;
    }
    //TODO
    public void printSet() {
        for (T t : backingArray) {
            if (t != null) {
                System.out.println(t.toString());
            } else {
                System.out.println("null");
            }
        }
    }


    // The methods below have been provided to you. You should not modify them.

    /**
     * Returns an iterator over the elements in this set.
     * You will need to know Iterable/Iterator for your exams, so take a look at
     * this implementation and the Javadocs
     * @return an iterator instance
     */
    @Override
    public Iterator<T> iterator() {
        return new MySetIterator();
    }

    private class MySetIterator implements Iterator<T> {
        private int index = 0;

        public boolean hasNext() {
            return index < numElements;
        }
        public T next() {
            return backingArray[index++];
        }
        public void remove() {
            MySet.this.remove(backingArray[index - 1]);
            index--;
        }
    }

    /**
     * Returns a hash representation of the object, based on the hashCode of the
     * backing array
     * @return an integer hash of the set
     */
    public int hashCode() {
        return java.util.Arrays.hashCode(backingArray);
    }

    /**
     * Not implemented in this Set
     * @param a an array which specifies the runtime return type for toArray
     * @param <S> the return type of the array
     * @return null
     */
    @Override
    public <S> S[] toArray(S[] a) {
        return null;
    }


    /**
     * Returns the backing array directly.
     * This violates the set's encapsulation principle, but is necessary for
     * grading.
     * @return the backing array
     */
    public T[] getBackingArray() {
        return this.backingArray;
    }
}
