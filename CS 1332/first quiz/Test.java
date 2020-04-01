public class Test {
    public static void main(String[] args) {
        GroceryStore g = new Publix();
        g.shop();
        DrugProvider d = new Publix();
        System.out.println(d);
        Object o = new Publix();
        ((GroceryStore)o).shop();
        Publix p = new GroceryStore();
        ((DrugProvider)p).fillPrescription();
    }
}
