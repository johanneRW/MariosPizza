package company;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    private ArrayList<Pizza> pizzas;
    private LocalTime timeAdded;
    private int orderNumber;


    public Order(ArrayList<Pizza> listOfPizzas, LocalTime timeAdded) {
        this.pizzas = listOfPizzas;
        this.timeAdded = timeAdded;
        this.orderNumber = getNextOrderNumber();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public LocalTime getTimeAdded() {
        LocalTime localTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        return localTime;
    }

    public int getNextOrderNumber() {
        int linesInOrdersFile = getLinesInFile("data/orders.txt");
        int linesInOrderHistoryFile = getLinesInFile("data/orderHistory.txt");
        return linesInOrdersFile + linesInOrderHistoryFile + 1;
    }

    public int getLinesInFile(String fileName) {
        File file = new File(fileName);
        int lines = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lines++;
            }
            return lines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public String toString() {
        return pizzas + "\nOprettet:" + timeAdded;
    }
}
