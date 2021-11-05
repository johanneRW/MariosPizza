package company;

import java.io.*;
import java.time.LocalTime;
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
        //int counter = 1;
        ps.println(order.getOrderNumber()+ ". " +order.getPizzas() + ";" + order.getTimeAdded()+"/");
        //counter++;
        ps.close();
    }

    public void deleteContents(String fileName, StringBuilder text) throws IOException {
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(String.valueOf(text));
        myWriter.close();    }

    public ArrayList<String> getAllOrders(String ORDER_FILE_PATH) throws FileNotFoundException {
        PizzaMenu Pizza = new PizzaMenu();
        File file = new File(ORDER_FILE_PATH);
        Scanner scanner = new Scanner(file);
        ArrayList<String> orders = new ArrayList<>();
       // Order order = null;
        while (scanner.hasNext()) {
            String found = scanner.nextLine();
            orders.add(found);


           /*String orderNumberText = found.substring(0, found.indexOf('.'));
            int orderNumber = Integer.parseInt(orderNumberText);
            String pizzaList = found.substring(found.indexOf('[') + 1, found.indexOf(']') - 1);

            String time = found.substring(found.lastIndexOf(';')+1,found.lastIndexOf("/")-1);

            //for (String pizza : pizzas) {
                orders.add(new Order(Pizza.findPizzas(pizzaList.toLowerCase()), LocalTime.parse(time),orderNumber)); // https://stackoverflow.com/questions/30788369/coverting-string-to-localtime-with-without-nanoofseconds

*/
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
     /*public void test(String order, String FILE_PATH, String FILE_PATH_FINAL, int number) throws IOException {
       ArrayList<Order> saved = null;
        try {
            saved = getAllOrders(FILE_PATH_FINAL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ;
        Order issue = saved.get(number - 1);
        saved.remove(issue);
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (Order s : saved) {
            sb.append(counter + ". " + s + ";");
        }
        counter++;
        deleteContents(FILE_PATH_FINAL, sb);
        saveNewIssue(FILE_PATH, issue);
    }

    public void saveNewIssue(String FILE_PATH, Order order) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        PrintStream ps = new PrintStream(new FileOutputStream(file, true));
        ps.println(order);
        ps.close();*/
    }

