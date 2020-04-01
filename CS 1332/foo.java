
public class foo {
    Comparable[] data;
    public boolean isIncreasingOrder() {
        if (data.length == 0) { //checks to see if array is empty
            return false;
        }
        for (int i = 1; i < data.length; i++) { //iterates through array
            if (data[i] == null || data[i - 1] == null) { //checks to see if either of the two in question are null
                return false;
            }
            if (data[i].compareTo(data[i - 1]) == -1) { //checks to see if they are decreasing
                return false;
            }
        }
        return true;
    }
}
