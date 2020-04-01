public class Test {
    public static void main(String[] args) {
        ArrayQueue ls = new ArrayQueue();
        ls.enqueue("L");
        ls.enqueue("M");
        ls.enqueue("N");
        ls.enqueue("O");
        ls.enqueue("A");
        ls.enqueue("B");
        ls.enqueue("C");
        ls.enqueue("D");
        ls.dequeue();
        ls.dequeue();
        ls.dequeue();
        ls.dequeue();
        ls.enqueue("L");
        ls.enqueue("M");
        ls.enqueue("N");
        ls.enqueue("O");
        System.out.println(ls.size());
        Object[] arrsOb = ls.getBackingArray();
        for (int i = 0; i < arrsOb.length; i++) {
            System.out.println(arrsOb[i]);
        }
        ls.enqueue("L");
        ls.enqueue("M");
        ls.enqueue("N");
        ls.enqueue("O");

        System.out.println(ls.size());
        arrsOb = ls.getBackingArray();
        for (int i = 0; i < arrsOb.length; i++) {
            System.out.println(arrsOb[i]);
        }
        //System.out.println(ls.dequeue());
        //System.out.println(ls.size());
        /*
        ArrayStack arrs = new ArrayStack();
        arrs.push("A");
        arrs.push("b");
        arrs.push("C");
        System.out.print("Size: ");
        System.out.println(arrs.size());
        System.out.println(arrs.pop());
        arrs.pop();
        arrs.pop();
        System.out.print("Size: ");
        System.out.println(arrs.size());
        arrs.push("A");
        arrs.push("b");
        arrs.push("C");
        System.out.print("Size: ");
        System.out.println(arrs.size());
        System.out.println("Stack is now:");
        Object[] arrsOb = arrs.getBackingArray();
        for (int i = 0; i < arrsOb.length; i++) {
            System.out.println(arrsOb[i]);
        }
        */
    }
}
