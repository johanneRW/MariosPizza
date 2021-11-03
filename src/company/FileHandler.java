package company;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileHandler {

    public void saveNewPizza(String FILE_PATH, Pizza pizza) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        PrintStream ps = new PrintStream(new FileOutputStream(file, true));
        ps.println(pizza.toString());
        ps.close();
    }

    public ArrayList<Pizza> getAllPizzas(String FILE_PATH) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        ArrayList<Pizza> pizzas = new ArrayList<>();
        Pizza pizza = null;
        while (scanner.hasNext()) {
            String found = scanner.nextLine();
            String name= found.substring(0, found.indexOf(','));
            String[] ingredients = found.substring(found.indexOf(',')+2,found.lastIndexOf(',')-1).split(" ");
            int price = Integer.parseInt(found.substring(found.lastIndexOf(',')+1));


            pizzas.add(new Pizza(name, ingredients, price));
        }

        return pizzas;
    }
    public void saveNewOrder(String FILE_PATH, Order order) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        PrintStream ps = new PrintStream(new FileOutputStream(file, true));
        int counter = 1;
        ps.println(counter + ". " +order.getPizzas() + "," + order.getTimeAdded());
        counter++;
        ps.close();
    }

    public void deleteContents(String fileName, StringBuilder text) throws IOException {
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(String.valueOf(text));
        myWriter.close();    }

    public ArrayList<Order> getAllOrders(String ORDER_FILE_PATH) throws FileNotFoundException {
        PizzaMenu Pizza = new PizzaMenu();
        File file = new File(ORDER_FILE_PATH);
        Scanner scanner = new Scanner(file);
        ArrayList<Order> orders = new ArrayList<>();
        Order order = null;
        while (scanner.hasNext()) {
            String found = scanner.nextLine();
            String[] pizzas = found.substring(found.indexOf('.')+2, found.lastIndexOf(',')).split(" ");
            String time = found.substring(found.lastIndexOf(',')+2);
            System.out.println(time);
            for (int i = 0; i < pizzas.length; i++) {
            orders.add(new Order(Pizza.findPizza(pizzas[i].toLowerCase()),LocalTime.parse(time))); // https://stackoverflow.com/questions/30788369/coverting-string-to-localtime-with-without-nanoofseconds

            }



        }

        return orders;
    }

}
