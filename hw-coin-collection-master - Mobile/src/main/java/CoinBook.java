import java.util.Set;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
/**
 * A CoinBook which you store all the collected Coins.
 *
 * @author jpinder3
 */
public class CoinBook {
    private Set<Coin> coinBook;
    //TODO
    public CoinBook() {
        coinBook = new MySet<Coin>();
    }
    //TODO
    public boolean addCoin(Coin c) {
        return coinBook.add(c);
    }
    //TODO
    public boolean removeCoin(Coin c) {
        return coinBook.remove(c);
    }
    //TODO
    public List<Coin> sortByValue() {
        ArrayList<Coin> list = new ArrayList<Coin>();
        list.addAll(coinBook);
        Collections.sort(list);
        return list;
    }
    //TODO
    public List<Coin> sortByYear() {
        ArrayList<Coin> list = new ArrayList<Coin>();
        list.addAll(coinBook);
        Collections.sort(list, new YearCompare());
        return list;
    }
    //TODO
    public String toString() {
        String list = "";
        for (Coin c : coinBook) {
            list = list + c.toString();
        }
        return list;
    }
}
