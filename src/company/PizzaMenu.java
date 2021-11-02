package company;


import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PizzaMenu {
    private FileHandler fileHandler = new FileHandler();
    private static final String menu = "data/menu.txt";


    public void printMenuKort() {
        ArrayList<Pizza> menuList = null;
        try {
            menuList = fileHandler.getAllPizzas(menu);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("All pizzas:");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(i + 1 + ". " + menuList.get(i));
        }
        System.out.println("____________________________________________");
    }

    }

