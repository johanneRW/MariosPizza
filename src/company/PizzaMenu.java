package company;


import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PizzaMenu {
    private FileHandler fileHandler = new FileHandler();
    private static final String menu = "data/menu.txt";
        ArrayList<Pizza> menuList = null;

        public void addToPizzaList(){
        try {
            menuList = fileHandler.getAllPizzas(menu);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printMenuKort() {
        addToPizzaList();
        System.out.println("Alle pizzaer:");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(i + 1 + ". " + menuList.get(i).getName()+ "\n" + menuList.get(i).getIngredients()+ "\n" + menuList.get(i).getPrize() + "kr.");
        }
        System.out.println("____________________________________________");
    }
    public ArrayList<Pizza> findPizza(String search){
        addToPizzaList();
        ArrayList<Pizza> foundPizza = new ArrayList<>();
        for(Pizza pizza : menuList) {
            if(pizza.getName().toLowerCase().matches(search)) {
                foundPizza.add(pizza);
            }
        }

        return foundPizza;
    }
    }


