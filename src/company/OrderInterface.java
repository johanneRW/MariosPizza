package company;

import java.time.LocalTime;
import java.util.ArrayList;

public class OrderInterface {
    PizzaMenu menu = new PizzaMenu();
    private FileHandler fileHandler = new FileHandler();
    private static final String MENU_FILE = "data/menu.txt";
    private static final String ORDERS_FILE = "data/orders.txt";
    private static final String ORDER_HISTORY_FILE = "data/orderHistory.txt";

    public ArrayList<Pizza> getMenu() {
        return fileHandler.getAllPizzas(MENU_FILE);
    }


    public ArrayList<Pizza> collectPizzaNames(UserInterface userInterface) {
        // tilføj pizzaer til ordren indtil brugeren siger stop
        ArrayList<Pizza> listOfPizzas = new ArrayList<>();
        boolean doneAddingPizzas = false;
        while (!doneAddingPizzas) {
            String search = userInterface.getUserInput();
            if (search.equals("0")) {
                doneAddingPizzas = true;
            } else {
                ArrayList<Pizza> choices = menu.findPizzas(search);
                if (choices.size() == 0) {
                    userInterface.print("Kunne ikke genkende pizza-navnene");
                } else {
                    userInterface.print("Tilføjet til ordren: " + choices);
                }
                listOfPizzas.addAll(choices);
            }
        }
        return listOfPizzas;
    }

    public void addQuickOrder(UserInterface userInterface) {
        ArrayList<Pizza> listOfPizzas = collectPizzaNames(userInterface);
        // opret en ny ordre
        LocalTime time = LocalTime.now();
        int orderNumber = fileHandler.getNextOrderNumber(ORDERS_FILE, ORDER_HISTORY_FILE);
        Order order = new Order(listOfPizzas, time, orderNumber);
        fileHandler.saveNewOrder(ORDERS_FILE, order);
        userInterface.print("Pizzaer i ordre: " + order);
    }

    public void addOrder(UserInterface userInterface) {
        ArrayList<Pizza> listOfPizzas = collectPizzaNames(userInterface);
        // opret en ny ordre
        LocalTime time = LocalTime.now();
        int orderNumber = fileHandler.getNextOrderNumber(ORDERS_FILE, ORDER_HISTORY_FILE);
        LocalTime pickUpTime = userInterface.getPickupTime();
        Order order = new Order(listOfPizzas, time, orderNumber, pickUpTime);
        fileHandler.saveNewOrder(ORDERS_FILE, order);
        userInterface.print("Pizzaer i ordre: " + order);
    }

    public ArrayList<String> showOrders() {
        return fileHandler.getAllOrders(ORDERS_FILE);
    }

    public void sendOrderToArchive(UserInterface userInterface, int orderNumber) {
        String order = fileHandler.sendOrderToArchive(ORDERS_FILE, ORDER_HISTORY_FILE, orderNumber);
        userInterface.print("Sendte ordren " + order + " til arkivet");
    }

    public ArrayList<SalesCount> getSalesStats() {
        return fileHandler.getPizzasHistory(ORDER_HISTORY_FILE);
    }
}
