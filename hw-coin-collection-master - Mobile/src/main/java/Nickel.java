/**
 * Nickel class
 *
 * @author jpinder3
 */
public class Nickel extends Coin {

    /**
     * Nickel constructor
     * @param  year year of Nickel
     */
    public Nickel(int year) {
        super(5, year);
    }

    @Override
    public String toString() {
        return String.format("Nickel : 5 %d", this.getYear());
    }

    @Override
    public boolean equals(Object other) {
        return other != null && (other instanceof Nickel)
            && ((Nickel) other).getYear() == this.getYear();
    }
}
