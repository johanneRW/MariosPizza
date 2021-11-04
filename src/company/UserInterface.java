package company;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    PizzaMenu menu = new PizzaMenu();
    OrderInterface OrderInterface = new OrderInterface();

    public UserInterface() throws Exception {
        start();
    }
    public void start() throws Exception {
        System.out.println("Velkommen hos Marios Pizza");
        System.out.println("---------------------------");

        mainMenu();
    }
    private void mainMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                                        
                    Foretag et valg:
                    1) Opret en ordre
                    2) Se Menukortet
                    3) Se alle aktive ordrer
                    4) Fjern ordre
                    0) Exit application""");
            int selection = scanner.nextInt();
            switch (selection) {
                case 1:
                    OrderInterface.addOrder();
                    break;
                case 2:
                    menu.printMenuKort();
                    break;
                case 3:
                    int counter = 1;
                    for (Order order : OrderInterface.showOrders()) {
                        System.out.println(counter + " - " + order );
                        counter++;
                    }
                    break;
                case 4:
                    //TODO fjerne bestem ordre fra Arrayet med nuværende ordre
                    OrderInterface.removeOrder();
                    break;

                case 5:
                    //TODO færdiggøre ordre.

                case 0:
                    System.out.println("Tak for at vælge Marios Pizza");
                    isRunning = false;
                    break;
            }
            }
        }
    }

