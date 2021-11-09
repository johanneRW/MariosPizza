package company;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Order {
    private ArrayList<Pizza> pizzas;
    private LocalTime timeAdded;
    private int orderNumber;
    private LocalTime pickUpTime;


    public Order(ArrayList<Pizza> listOfPizzas, LocalTime timeAdded, int orderNumber, LocalTime pickUpTime) {
        this.pizzas = listOfPizzas;
        this.timeAdded = timeAdded;
        this.orderNumber = orderNumber;
        this.pickUpTime = pickUpTime;

    }
    public Order(ArrayList<Pizza> listOfPizzas, LocalTime timeAdded, int orderNumber) {
        this.pizzas = listOfPizzas;
        this.timeAdded = timeAdded;
        this.orderNumber = orderNumber;
        this.pickUpTime = timeAdded.plusMinutes(10);

    }

    public LocalTime getPickUpTime() {
        return pickUpTime.truncatedTo(ChronoUnit.MINUTES);
    }


    public int getOrderNumber() {
        return orderNumber;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public LocalTime getTimeAdded() {
        return timeAdded.truncatedTo(ChronoUnit.MINUTES);
    }

    @Override
    public String toString() {
        return pizzas + "\nOprettet:" + getTimeAdded() + ". Afhentes: " + getPickUpTime();
    }
}
