package company;

import java.util.ArrayList;

public class PizzaMenu {
    private FileHandler fileHandler = new FileHandler();
    private static final String MENU_FILE = "data/menu.txt";
    ArrayList<Pizza> menuList = null;

    public void addToPizzaList() {
            menuList = fileHandler.getAllPizzas(MENU_FILE);
        }


    public void printMenu() {
        addToPizzaList();
        System.out.println("Alle pizzaer:");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println((i + 1) + ". " + menuList.get(i).getName() + "\n" + menuList.get(i).getIngredients()
                    + "\n" + menuList.get(i).getPrice() + "kr.");
        }
        System.out.println("____________________________________________");
    }

    public ArrayList<Pizza> findPizzas(String search) {
        addToPizzaList();
        String[] searchPizzas = search.split(", ");
        ArrayList<Pizza> foundPizzas = new ArrayList<>();
        for (Pizza pizza : menuList) {
            for (String searchPizza : searchPizzas)
                if (pizza.getName().toLowerCase().matches(searchPizza)) {
                    foundPizzas.add(pizza);
                }
        }
        return foundPizzas;
    }
}


