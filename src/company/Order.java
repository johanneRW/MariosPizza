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


    public Order(ArrayList<Pizza> listOfPizzas, LocalTime timeAdded, int orderNumber) {
        this.pizzas = listOfPizzas;
        this.timeAdded = timeAdded;
        this.orderNumber = orderNumber;
        //this.orderNumber = getNextOrderNumber();
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

    @Override
    public String toString() {
        return pizzas + "\nOprettet:" + timeAdded;
    }
}
