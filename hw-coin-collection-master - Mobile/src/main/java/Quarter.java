/**
 * Quarter class
 *
 * @author jpinder3
 */
public class Quarter extends Coin {

    private State state;
    /**
     * Quarter constructor
     * @param  year year of Quarter
     */
    public Quarter(int year, State state) {
        super(25, year);
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("Quarter : 25 %d %s", this.getYear(),
            this.state.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other != null && (other instanceof Quarter)
            && ((Quarter) other).getYear() == this.getYear()
            && ((Quarter) other).getState() == this.state;
    }
    //TODO
    public State getState() {
        return this.state;
    }
    //TODO
    public int compareTo(Coin other) {
        int thisyear = this.getYear();
        int otheryear = other.getYear();
        if (other instanceof Quarter) {
            Quarter o = (Quarter) other;
            State s = o.getState();
            if (this.state.equals(o.getState())) {
                if (thisyear > otheryear) {
                    return 1;
                } else if (thisyear < otheryear) {
                    return -1;
                } else {
                    return 0;
                }
            } else if (this.state.ordinal()
                < (s.ordinal())) {
                return 1;
            }
            return -1;
        }
        return super.compareTo(other);
    }
}
