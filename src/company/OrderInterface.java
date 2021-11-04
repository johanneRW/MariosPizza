package company;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class OrderInterface {
    PizzaMenu menu = new PizzaMenu();
    Scanner scanner = new Scanner(System.in);
    private FileHandler fileHandler = new FileHandler();
    private static final String menuFile = "data/menu.txt";
    private static final String ordersFile = "data/orders.txt";
    private static final String orderHistoryFile = "data/orderHistory.txt";

    public void addOrder() throws FileNotFoundException {
        System.out.println("Skriv venligst navnet på hvilke pizzaer der skal med i bestillingen fra den nedenstående liste(slut med 0):");


        ArrayList<Pizza> menuList = null;
        try {
            menuList = fileHandler.getAllPizzas(menuFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Pizza> listOfPizzas = new ArrayList<>();
        while (true) {
        String search = scanner.nextLine().trim().toLowerCase();
            if (!search.equals("0")) {
                listOfPizzas.addAll(menu.findPizza(search));

            } else {
                break;
            }
        }
        String time = fileHandler.tidspunkt;
        Order order = new Order(listOfPizzas, time);
        fileHandler.saveNewOrder(ordersFile, order);
    }

    public ArrayList<Order> showOrders() throws FileNotFoundException {

        return fileHandler.getAllOrders(ordersFile);
    }

}
