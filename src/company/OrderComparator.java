package company;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class OrderComparator implements Comparator<String> {
    // Comparator:
    // https://www.geeksforgeeks.org/how-to-sort-arraylist-using-comparator/
    // Sammenligning af LocalTime:
    // https://stackoverflow.com/questions/19046058/comparing-the-two-times-is-greater-or-lesser-in-java

    // override the compare() method
    public int compare(String o1, String o2) {
        LocalTime pickup1 = getPickUpTime(o1);
        LocalTime pickup2 = getPickUpTime(o2);

        if (pickup1.equals(pickup2)) {
            return 0;
        } else if (pickup1.isAfter(pickup2)) {
            return 1;
        } else {
            return -1;
        }
    }

    private LocalTime getPickUpTime(String order) {
        int pickupStart = order.indexOf("/");
        int pickupEnd = order.lastIndexOf("/");
        String pickup = order.substring(pickupStart + 1, pickupEnd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime pickUpTime = LocalTime.parse(pickup, formatter);
        return pickUpTime;
    }
}
