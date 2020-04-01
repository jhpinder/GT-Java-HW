/**
 * Dime class
 *
 * @author jpinder3
 */
public class Dime extends Coin {

    /**
     * Dime constructor
     * @param  year year of Dime
     */
    public Dime(int year) {
        super(10, year);
    }

    @Override
    public String toString() {
        return String.format("Dime : 10 %d", this.getYear());
    }

    @Override
    public boolean equals(Object other) {
        return other != null && (other instanceof Dime)
            && ((Dime) other).getYear() == this.getYear();
    }
}
