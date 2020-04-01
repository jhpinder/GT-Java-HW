import java.util.Collection;
import java.util.List;

/**
 * Your implementation of a binary search tree.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data passed in is null, "
                + "do not pass in null to this method.");
        }
        root = null;
        size = 0;
        for (T t : data) {
            if (t == null) {
                throw new IllegalArgumentException("Some data in the "
                    + "collection is null, do not pass in null data.");
            }
            this.add(t);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data passed in is null, "
                + "do not pass in null to this method.");
        }
        if (root == null) {
            root = new BSTNode(data);
            size = 1;
        } else {
            myAdd(data, root);
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data passed in is null, "
                + "do not pass in null to this method.");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException("The element passed in "
            + "was not found in the BST.");
        }
        if (root.getData().equals(data)) {
            T temp = root.getData();
            if (root.getRight() == null && root.getLeft() == null) {
                size = 0;
                root = null;
                return temp;
            } else if (root.getLeft() == null) { //right child only
                BSTNode<T> oldroot = root;
                root = root.getRight();
                oldroot.setRight(null);
                size--;
                return temp;
            } else if (root.getRight() == null) { //left child only
                BSTNode<T> oldroot = root;
                root = root.getLeft();
                oldroot.setLeft(null);
                size--;
                return temp;
            } else { //find predecessorParent
                BSTNode<T> predecessorParent;
                BSTNode<T> predecessor;
                if (root.getLeft().getRight() == null) {
                    predecessor = root.getLeft();
                } else {
                    predecessorParent = findPreParent(root.getLeft());
                    predecessor = predecessorParent.getRight();
                    predecessorParent.setRight(predecessor.getLeft());
                    predecessor.setLeft(root.getLeft());
                }
                predecessor.setRight(root.getRight());
                root.setLeft(null);
                root.setRight(null);
                root = predecessor;
                size--;
                return temp;
            }
        } else if (data.compareTo(root.getData()) < 0) {
            return myRemove(root.getLeft(), root, data);
        } else if (data.compareTo(root.getData()) > 0) {
            return myRemove(root.getRight(), root, data);
        }
        return null;
    }
    /**
     * recursive function for remove
     * @param   node node that we are looking at
     * @param   parent parent of the node we are looking at
     * @param   data the data we are trying to remove
     * @return  the data that we removed
     */
    private T myRemove(BSTNode<T> node, BSTNode<T> parent, T data) {
        if (node == null) {
            throw new java.util.NoSuchElementException("The element passed in "
            + "was not found in the BST.");
        } else if (data.compareTo(node.getData()) < 0) {
            return myRemove(node.getLeft(), node, data);
        } else if (data.compareTo(node.getData()) > 0) {
            return myRemove(node.getRight(), node, data);
        }
        T temp = node.getData();
        if (node.getLeft() == null && node.getRight() == null) { //no children
            if (parent.getRight() == node) {
                parent.setRight(null);
            } else {
                parent.setLeft(null);
            }
        } else if (node.getLeft() == null) { //right child only
            if (parent.getRight() == node) {
                parent.setRight(node.getRight());
            } else {
                parent.setLeft(node.getRight());
            }
            node.setRight(null);
        } else if (node.getRight() == null) { //left child only
            if (parent.getRight() == node) {
                parent.setRight(node.getLeft());
            } else {
                parent.setLeft(node.getLeft());
            }
            node.setLeft(null);
        } else { //find predecessorParent
            BSTNode<T> predecessorParent;
            BSTNode<T> predecessor;
            if (node.getLeft().getRight() == null) {
                predecessor = node.getLeft();
                if (parent.getRight() == node) {
                    parent.setRight(predecessor);
                } else {
                    parent.setLeft(predecessor);
                }
                predecessor.setRight(node.getRight());
            } else {
                predecessorParent = findPreParent(node.getLeft());
                predecessor = predecessorParent.getRight();
                predecessorParent.setRight(predecessor.getLeft());
                if (parent.getRight() == node) {
                    parent.setRight(predecessor);
                } else {
                    parent.setLeft(predecessor);
                }
                predecessor.setRight(node.getRight());
                predecessor.setLeft(node.getLeft());
            }
            node.setRight(null);
            node.setLeft(null);
        }
        size--;
        return temp;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data passed in is null, "
                + "do not pass in null to this method.");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException("The data that"
                + " was passed in was not found in the BST.");
        }
        return myGet(data, root);
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data passed in is null, "
                + "do not pass in null to this method.");
        }
        if (root == null) {
            return false;
        }
        return myContains(data, root);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> list = new java.util.ArrayList();
        return myPreO(root, list);
    }

    @Override
    public List<T> postorder() {
        List<T> list = new java.util.ArrayList();
        return myPostO(root, list);
    }

    @Override
    public List<T> inorder() {
        List<T> list = new java.util.ArrayList();
        return myInO(root, list);
    }

    @Override
    public List<T> levelorder() {
        List<T> list = new java.util.ArrayList<T>();
        if (root == null) {
            return list;
        }
        java.util.Queue<BSTNode> q = new java.util.LinkedList();
        q.add(root);
        while (!q.isEmpty()) {
            BSTNode<T> current = (BSTNode<T>) q.remove();
            list.add(current.getData());
            if (current.getLeft() != null) {
                q.add(current.getLeft());
            }
            if (current.getRight() != null) {
                q.add(current.getRight());
            }
        }
        return list;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        return myHeight(0, root);
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }

    /**
     * recursive function for add
     * @param   data data to be added
     * @param   node node to which the data will be added
     */
    private void myAdd(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode<T>(data));
                size++;
            } else {
                myAdd(data, node.getLeft());
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTNode<T>(data));
                size++;
            } else {
                myAdd(data, node.getRight());
            }
        }
    }
    /**
     * recursive function for finding the parent of the predecessor
     * @param   node the node left of the node to be removed
     * @return  the parent of the predecessor
     */
    private BSTNode<T> findPreParent(BSTNode<T> node) {
        if (node.getRight() != null) {
            if (node.getRight().getRight() == null) {
                return node;
            } else {
                node = findPreParent(node.getRight());
                return node;
            }
        } else {
            return node;
        }
    }
    /**
     * recursive function for get
     * @param   data the data to be searched for
     * @param   node the node which we are searching
     * @return  null if the data isn't found, data otherwise
     */
    private T myGet(T data, BSTNode<T> node) {
        if (data.equals(node.getData())) {
            return node.getData();
        } else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                throw new java.util.NoSuchElementException("The data that"
                    + " was passed in was not found in the BST.");
            } else {
                return myGet(data, node.getLeft());
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                throw new java.util.NoSuchElementException("The data that"
                    + " was passed in was not found in the BST.");
            } else {
                return myGet(data, node.getRight());
            }
        }
        return null;
    }
    /**
     * recursive function for contains
     * @param   data the data we are searching for
     * @param   node the node we are searching
     * @return  true if data is found, false otherwise
     */
    private boolean myContains(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                return false;
            } else {
                return myContains(data, node.getRight());
            }
        } else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                return false;
            } else {
                return myContains(data, node.getLeft());
            }
        } else if (data.equals(node.getData())) {
            return true;
        }
        return false;
    }
    /**
     * recursive function for height
     * @param   h the current height
     * @param   node the node to calculate height
     * @return  the calculated height
     */
    private int myHeight(int h, BSTNode<T> node) {
        if (node.getRight() == null && node.getLeft() == null) {
            return 0;
        } else if (node.getLeft() != null && node.getRight() == null) {
            return myHeight(h, node.getLeft()) + 1;
        } else if (node.getLeft() == null && node.getRight() != null) {
            return myHeight(h, node.getRight()) + 1;
        } else {
            return Math.max(myHeight(h, node.getLeft()), myHeight(h,
                node.getRight())) + 1;
        }
    }
    /**
     * recursive function for preorder
     * @param   node node to be added to list
     * @param   list list to be added to
     * @return  list with the node's subtree data added
     */
    private List<T> myPreO(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return list;
        }
        list.add(node.getData());
        list = myPreO(node.getLeft(), list);
        list = myPreO(node.getRight(), list);
        return list;
    }
    /**
     * recursive function for postorder
     * @param   node node to be added to list
     * @param   list list to be added to
     * @return  list with the node's subtree data added
     */
    private List<T> myPostO(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return list;
        }
        list = myPostO(node.getLeft(), list);
        list = myPostO(node.getRight(), list);
        list.add(node.getData());
        return list;
    }
    /**
     * recursive function for inorder
     * @param   node node to be added to list
     * @param   list list to be added to
     * @return  list with the node's subtree data added
     */
    private List<T> myInO(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return list;
        }
        list = myInO(node.getLeft(), list);
        list.add(node.getData());
        list = myInO(node.getRight(), list);
        return list;
    }
}
