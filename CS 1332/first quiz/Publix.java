public class Publix extends GroceryStore implements DrugProvider {
    @Override
    public void shop() {
        System.out.println("Shopping from Publix...");
    }
    public void fillPrescription() {
        System.out.println("Filling Prescription...");
    }
}
