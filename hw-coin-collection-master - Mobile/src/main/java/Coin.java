/**
 * Abstract Coin class.
 *
 * @author jpinder3
 * @version 1.0
 */
public abstract class Coin implements Comparable<Coin> {
    private int value;
    private int year;

    /**
     * A Coin class with value and year
     * @param   the value of the Coin
     * @param   the year of the Coin
     */
    public Coin(int value, int year) {
        this.value = value;
        this.year = year;
    }

    /**
     * Getter method for value
     * @return value as an int
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Getter method for year
     * @return year as an int
     */
    public int getYear() {
        return this.year;
    }

    /**
     * toString of Coin
     * @return Coin value year as string
     */
    public String toString() {
        return String.format("Coin: %d %d", value, year);
    }

    @Override
    public int compareTo(Coin other) {
        return Integer.compare(this.value, other.getValue());
    }

    @Override
    public boolean equals(Object other) {
        return other != null && (other instanceof Coin)
            && ((Coin) other).getYear() == this.year
            && ((Coin) other).getValue() == this.value;
    }

    // This method should not be modified
    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + year;
        hash = 31 * hash + value;
        return hash;
    }
}
