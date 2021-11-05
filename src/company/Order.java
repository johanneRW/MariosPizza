package company;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    private ArrayList<Pizza> pizzas;
    private LocalTime timeAdded;
    private int orderNumber;

    public Order(ArrayList<Pizza> listOfPizzas, LocalTime TimeAdded) {
        this.pizzas = listOfPizzas;
        this.timeAdded = TimeAdded;
        this.orderNumber=getNextOrderNumber();
    }
   public Order(ArrayList<Pizza> listOfPizzas, LocalTime TimeAdded,int orderNumber) {
       this.pizzas = listOfPizzas;
       this.timeAdded = TimeAdded;
       this.orderNumber = orderNumber;
   }

    public int getOrderNumber() {
        return orderNumber;
    }

    public ArrayList<Pizza> getPizzas() {

        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public LocalTime getTimeAdded() {
        LocalTime tidspunkt = LocalTime.now().truncatedTo( ChronoUnit.MINUTES );
        return tidspunkt;
    }

    public int getNextOrderNumber() {
        int linjerIOrdreListe = getLinesInFile("data/orders.txt");
        int linjerIOrdreArkiv = getLinesInFile("data/orderHistory.txt");
        return linjerIOrdreListe + linjerIOrdreArkiv + 1;
    }

    public int getLinesInFile(String filename){
        File file = new File(filename);
        int lines=0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                scanner.nextLine();
              lines++;
            }
            return lines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public void setTimeAdded(LocalTime timeAdded) {
        this.timeAdded = timeAdded;
    }
    @Override
    public String toString() {
        return pizzas + "\nOprettet:" + timeAdded;
    }
}
