package company;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class FileHandler {

    public ArrayList<Pizza> getAllPizzas(String FILE_PATH) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        ArrayList<Pizza> pizzas = new ArrayList<>();
        Pizza pizza = null;
        while (scanner.hasNext()) {
            String found = scanner.nextLine();
            String name= found.substring(0, found.indexOf(','));
            String[] ingredients = found.substring(found.indexOf(',')+1,found.lastIndexOf(',')).split(" ");
            int price = Integer.parseInt(found.substring(found.lastIndexOf(',')+1));

            pizzas.add(new Pizza(name, ingredients, price));
        }
        return pizzas;
    }
    public void saveNewOrder(String FILE_PATH, Order order) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        PrintStream ps = new PrintStream(new FileOutputStream(file, true));
        ps.println(order.getOrderNumber()+ ". " +order.getPizzas() + ";" + order.getTimeAdded()+"/");
        ps.close();
    }


    public ArrayList<String> getAllOrders(String ORDER_FILE_PATH) throws FileNotFoundException {
        File file = new File(ORDER_FILE_PATH);
        Scanner scanner = new Scanner(file);
        ArrayList<String> orders = new ArrayList<>();
        while (scanner.hasNext()) {
            String found = scanner.nextLine();
            orders.add(found);
        }
        return orders;
    }



    public void sendOrderToArchive(String FILE_PATH, String STATS_FILE_PATH, int number) throws IOException {
        File file = new File(FILE_PATH);
        File stats = new File(STATS_FILE_PATH);
        Scanner scanner = new Scanner(file);

        ArrayList<String> orders = getAllOrders(FILE_PATH);

        // arkivering
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String num = line.substring(0, line.indexOf('.'));
            int orderNumber = Integer.parseInt(num);

            if (number == orderNumber) {
                // tilføj linje til arkiv
                PrintStream ps = new PrintStream(new FileOutputStream(stats, true));
                ps.println(line);
                ps.close();
            }
        }
        // fjern fra ordreliste

        // 1. tøm filen
        new PrintStream(FILE_PATH).close();

        // 2. skriv alle linjer, undtagen den der er arkiveret
        PrintStream ps = new PrintStream(new FileOutputStream(file, true));
        for (int i = 0; i < orders.size(); i++) {
            String order = orders.get(i);
            String num = order.substring(0, order.indexOf('.'));
            int orderNumber = Integer.parseInt(num);

            if (number != orderNumber) {
                ps.println(order);
            }
        }
        ps.close();
    }


        public ArrayList<SalesCount> getPizzasHistory(String STATS_FILE_PATH) throws FileNotFoundException {
            File file = new File(STATS_FILE_PATH);
            Scanner scanner = new Scanner(file);
            ArrayList<SalesCount> salg = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String found = scanner.nextLine();
                int startPåOrdre = found.indexOf("[");
                int slutPåOrdre = found.indexOf("]");
                String[] pizzaHistorik = found.substring(startPåOrdre + 1, slutPåOrdre).split(", ");

                for (int i = 0; i < pizzaHistorik.length; i++) {
                    String pizza=pizzaHistorik[i];
                    if (salg.size() == 0) {
                        salg.add(new SalesCount(pizza, 1));
                    } else {
                        boolean done = false;
                        for (int j = 0; j < salg.size(); j++) {
                            SalesCount sc = salg.get(j);
                            if (sc.getPizzaName().equalsIgnoreCase(pizza)) {
                                sc.count= sc.getCount()+1;
                                salg.set(j, sc);
                                done = true;
                            }
                        }
                        if (!done){
                            salg.add(new SalesCount(pizza, 1));
                        }
                    }
                }
            }

            return salg;
    }
}

