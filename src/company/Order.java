package company;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Order {
    private ArrayList<Pizza> pizzas;
    private String timeAdded;

    public Order(ArrayList<Pizza> listOfPizzas, String TimeAdded) {
        this.pizzas = listOfPizzas;
        this.timeAdded = TimeAdded;
    }



    public ArrayList<Pizza> getPizzas() {

        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
    }
    @Override
    public String toString() {
        return pizzas + "\nOprettet:" + timeAdded;
    }
}
