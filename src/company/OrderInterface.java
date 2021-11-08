package company;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderInterface {
    PizzaMenu menu = new PizzaMenu();
    Scanner scanner = new Scanner(System.in);
    private FileHandler fileHandler = new FileHandler();
    private static final String MENU_FILE = "data/menu.txt";
    private static final String ORDERS_FILE = "data/orders.txt";
    private static final String ORDER_HISTORY_FILE = "data/orderHistory.txt";

    public void addOrder() {
        System.out.println("Skriv venligst navnet på hvilke pizzaer der skal med i bestillingen fra den nedenstående liste(slut med 0):");
        System.out.println(fileHandler.getAllPizzas(MENU_FILE));
        ArrayList<Pizza> listOfPizzas = new ArrayList<>();
        while (true) {
            String search = scanner.nextLine().trim().toLowerCase();
            if (!search.equals("0")) {
                listOfPizzas.addAll(menu.findPizzas(search));
            } else {
                break;
            }
            LocalTime time = LocalTime.now();
            Order order = new Order(listOfPizzas, time, fileHandler.getNextOrderNumber(ORDERS_FILE,ORDER_HISTORY_FILE));
            fileHandler.saveNewOrder(ORDERS_FILE, order);
        }
    }

    public ArrayList<String> showOrders() {
        return fileHandler.getAllOrders(ORDERS_FILE);
    }

    public void sendOrderToArchive(int orderNumber) {
        fileHandler.sendOrderToArchive(ORDERS_FILE, ORDER_HISTORY_FILE, orderNumber);
    }

    public ArrayList<SalesCount> getSalesStats() {
        return fileHandler.getPizzasHistory(ORDER_HISTORY_FILE);
    }
}
