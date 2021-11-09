package company;

import java.util.Comparator;

public class SalesCountComparator implements Comparator<SalesCount> {
    // Comparator:
    // https://www.geeksforgeeks.org/how-to-sort-arraylist-using-comparator/
    public int compare(SalesCount sc1, SalesCount sc2) {

        if (sc1.count==sc2.count) {
            return 0;
        } else if (sc1.count>sc2.count) {
            return -1;
        } else {
            return 1;
        }
    }
}