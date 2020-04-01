public class test {
    public static void main(String[] args) {
        DoublyLinkedList<String> list = new DoublyLinkedList<String>();
        list.addToFront("meme");
        list.addToFront("meme");
        list.addToFront("meme");
        list.addToFront("meme");
        list.addToFront("meme");
        list.addToFront("meme");
        list.addToFront("meme");
        Object[] arr = list.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toString());
        }
        System.out.print("size: ");
        System.out.println(list.size());
        list.removeAllOccurrences("meme");
        arr = list.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toString());
        }
        System.out.print("size: ");
        System.out.print(list.size());
    }
}
