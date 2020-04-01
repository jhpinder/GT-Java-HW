import java.util.List;
public class Test {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.add(30);
        bst.add(10);
        bst.add(50);
        bst.add(40);
        bst.add(60);
        bst.add(5);
        bst.add(18);
        bst.add(25);
        bst.add(15);
        bst.add(13);
        bst.add(17);
        bst.remove(18);
        bst.remove(15);
        bst.add(14);
        bst.add(15);
        bst.add(16);
        bst.remove(16);
        bst.remove(17);
        bst.remove(bst.getRoot().getData());
        //System.out.println(bst.getRoot().getLeft().getLeft().getRight().getData());
        List l = bst.postorder();
        for (Object t : l) {
            System.out.println(t.toString());
        }

        //System.out.println(bst.height());
    }
}
