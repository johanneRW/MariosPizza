package company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    public void deleteContents(String fileName, StringBuilder text) throws IOException {
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(String.valueOf(text));
        myWriter.close();    }

}
