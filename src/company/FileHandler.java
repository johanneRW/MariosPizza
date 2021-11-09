package company;

import java.io.*;
import java.util.*;

public class FileHandler {

    public ArrayList<Pizza> getAllPizzas(String FILE_PATH){
        File file = new File(FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<Pizza> pizzas = new ArrayList<>();
            Pizza pizza = null;
            while (scanner.hasNext()) {
                String found = scanner.nextLine();
                String name = found.substring(0, found.indexOf(','));
                String[] ingredients = found.substring(found.indexOf(',') + 1, found.lastIndexOf(',')).split(" ");
                int price = Integer.parseInt(found.substring(found.lastIndexOf(',') + 1));

                pizzas.add(new Pizza(name, ingredients, price));
            }
            return pizzas;
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from " + file, e);
        }
    }

    public void saveNewOrder(String FILE_PATH, Order order){
        File file = new File(FILE_PATH);
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));
            ps.println(order.getOrderNumber() + ". " + order.getPizzas() + ";"
                    + order.getTimeAdded() + "/"+order.getPickUpTime()+ "/");
            ps.close();
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Can't write to " + file, e);

        }
    }

    public ArrayList<String> getAllOrders(String ORDER_FILE_PATH){
        File file = new File(ORDER_FILE_PATH);

        try {
            Scanner scanner = new Scanner(file);
            ArrayList<String> orders = new ArrayList<>();

            while (scanner.hasNext()) {
                String foundLine = scanner.nextLine();
                orders.add(foundLine);
            }

            orders.sort(new OrderComparator());
            return orders;
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from " + file, e);
        }
    }


    public String sendOrderToArchive(String FILE_PATH, String ORDER_HISTORY_FILE_PATH, int number){
        String result = "";
        File file = new File(FILE_PATH);
        File stats = new File(ORDER_HISTORY_FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);

            ArrayList<String> orders = getAllOrders(FILE_PATH);

            // arkivering
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String stringNumber = line.substring(0, line.indexOf('.'));
                int orderNumber = Integer.parseInt(stringNumber);
                try {

                    if (number == orderNumber) {
                        result = line;
                        // tilføj linje til arkiv
                        PrintStream printStream = new PrintStream(new FileOutputStream(stats, true));
                        printStream.println(line);
                        printStream.close();
                    }
                } catch (FileNotFoundException e) {
                    throw new FileWriteException("Can't write to " + file, e);
                }
            }
            // fjern fra ordreliste

            // 1. tøm filen
            new PrintStream(FILE_PATH).close();

            // 2. skriv alle linjer, undtagen den der er arkiveret
           try {
               PrintStream printStream = new PrintStream(new FileOutputStream(file, true));
               for (String order : orders) {
                   String stringNumber = order.substring(0, order.indexOf('.'));
                   int orderNumber = Integer.parseInt(stringNumber);

                   if (number != orderNumber) {
                       printStream.println(order);
                   }
               }
               printStream.close();
           } catch (FileNotFoundException e) {
               throw new FileWriteException("Can't write to " + file, e);}
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from " + file, e);
        }

        return result;
    }


    public ArrayList<SalesCount> getPizzasHistory(String ORDER_HISTORY_FILE_PATH){
        File file = new File(ORDER_HISTORY_FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);

            ArrayList<SalesCount> sales = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String foundLine = scanner.nextLine();
                int startOfOrder = foundLine.indexOf("[");
                int endOfOrder = foundLine.indexOf("]");
                String[] pizzaHistory = foundLine.substring(startOfOrder + 1, endOfOrder).split(", ");

                for (String pizza : pizzaHistory) {
                    if (sales.size() == 0) {
                        sales.add(new SalesCount(pizza, 1));
                    } else {
                        boolean done = false;
                        for (int j = 0; j < sales.size(); j++) {
                            SalesCount salesCount = sales.get(j);
                            if (salesCount.getPizzaName().equalsIgnoreCase(pizza)) {
                                salesCount.count = salesCount.getCount() + 1;
                                sales.set(j, salesCount);
                                done = true;
                            }
                        }
                        if (!done) {
                            sales.add(new SalesCount(pizza, 1));
                        }
                    }
                }
            }
            sales.sort(new SalesCountComparator());
            return sales;
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from " + file, e);
        }}

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
                throw new FileReadException("Can't read from " + file, e);
            }
        }


    public int getNextOrderNumber(String ORDER_FILE_PATH, String ORDER_HISTORY_FILE_PATH) {
        int linesInOrdersFile = getLinesInFile(ORDER_FILE_PATH);
        int linesInOrderHistoryFile = getLinesInFile(ORDER_HISTORY_FILE_PATH);
        return linesInOrdersFile + linesInOrderHistoryFile + 1;
    }

}

