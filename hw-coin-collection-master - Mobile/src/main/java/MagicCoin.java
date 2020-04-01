//TODO
public class MagicCoin extends Coin {
    //TODO
    public MagicCoin(int year) {
        this(50, year);
    }
    //TODO
    public MagicCoin(int value, int year) {
        super(value, year);
    }
    //TODO
    public String toString() {
        return String.format("MagicCoin : %d %d",
        this.getValue(), this.getYear());
    }
}
