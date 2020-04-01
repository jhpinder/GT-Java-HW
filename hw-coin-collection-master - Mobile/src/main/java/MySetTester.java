import java.util.Arrays;
/**
 * Tester to test MySet
 * @author jpinder3
 */
public class MySetTester {

    /**
     * Main method to test all methods in MySet
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        /*
         * Test MySet
         */
        MySet<String> set = new MySet<>();

        System.out.println("Testing add:");

        // test add and contains
        set.add("That");
        set.add("aint");
        set.add("Falco!");
        set.add("Falco!");
        set.add("That");
        System.out.printf("Your set has %d elements, and \"Falco!\" %s"
                        + " in the set.  PASS? %b\n",
                set.size(),
                set.contains("Falco!") ? "is" : "is not",
                set.size() == 3 && set.contains("Falco!"));


        // test remove
        set.remove("Falco!");
        System.out.printf("Your set has %d elements, and \"Falco!\" %s"
                        + " in the set.  PASS? %b\n",
                set.size(),
                set.contains("Falco!") ? "is" : "is not",
                set.size() == 2 && !set.contains("Falco!"));

        // test addAll
        set.addAll(Arrays.asList("Happy", "Feet"));
        System.out.printf("Your set has %d elements, and \"Feet\" %s"
                        + " in the set.  PASS? %b\n",
                set.size(),
                set.contains("Feet") ? "is" : "is not",
                set.size() == 4 && set.contains("Feet"));

        // test removeAll
        set.removeAll(Arrays.asList("Happy", "That"));
        System.out.printf("Your set has %d elements, and \"That\" %s"
                        + " in the set.  PASS? %b\n",
                set.size(),
                set.contains("That") ? "is" : "is not",
                set.size() == 2 && !set.contains("That"));

        // test clear & size
        set.clear();
        System.out.printf("Cleared the set. PASS? %b\n", set.size() == 0);

        // test isEmpty
        System.out.printf("Is the set empty? %b\n", set.isEmpty());

        // test containsAll
        set.addAll(Arrays.asList("One", "Two", "Too"));
        System.out.printf("Your set has %d elements, and \"One\" "
            + "and \"Two\" %s both in the set. PASS? %b\n",
            set.size(),
            set.containsAll(
                Arrays.asList("One", "Two")) ? "are" : "are not",
            set.containsAll(Arrays.asList("One", "Two")));

        // test equals
        MySet<String> set2 = new MySet<>();
        set2.addAll(Arrays.asList("One", "Two", "Too"));
        System.out.printf("Set set2 to set successfully. Are they "
            + "equal? %b\n", set.equals(set2));

        // test retainAll
        set.add("To");
        set.retainAll(Arrays.asList("Two", "Too", "To"));
        set2.clear();
        set2.addAll(Arrays.asList("To", "Two", "Too"));
        System.out.printf("Retaining To, Two, Too. Successful? %b\n",
            set.equals(set2));

        // test toArray
        System.out.println("Printing out set one line at a time:");
        Object[] str = set.toArray();
        for (Object o : str) {
            System.out.println(o);
        }
    }
}
