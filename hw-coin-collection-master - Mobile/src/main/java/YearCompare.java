import java.util.Comparator;
public class YearCompare implements Comparator<Coin> {
	public int compare(Coin a, Coin b) {
		return Integer.compare(a.getYear(), b.getYear());
	}
}
