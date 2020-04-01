/**
 * Penny class
 *
 * @author jpinder3
 */
public class Penny extends Coin {

    /**
     * Penny constructor
     * @param  year year of Penny
     */
    public Penny(int year) {
        super(1, year);
    }

    @Override
    public String toString() {
        return String.format("Penny : 1 %d", this.getYear());
    }

    @Override
    public boolean equals(Object other) {
        return (other != null) && (other instanceof Penny)
            && ((Penny) other).getYear() == this.getYear();
    }
}
