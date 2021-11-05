package company;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;

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
                    1) Se Menukortet
                    2) Opret en ordre
                    3) Se alle aktive ordrer
                    4) Færdiggør ordre
                    5) Fjern ordre
                    0) Exit application""");
               int selection = scanner.nextInt();
               switch (selection) {
                   case 1:
                       menu.printMenuKort();
                       break;
                   case 2:
                       OrderInterface.addOrder();
                       break;
                   case 3:
                       int counter = 1;
                       for (String order : OrderInterface.showOrders()) {
                           System.out.println(order);
                       }
                       break;
                   case 4:
                       System.out.println("Skriv ordrenummeret på ordren der skal arkiveres");
                       int orderNumber = scanner.nextInt();
                       OrderInterface.sendOrderToArchive(orderNumber);
                       break;
                   case 5:
                       //TODO fjerne bestem ordre fra Arrayet med nuværende ordre
                       OrderInterface.removeOrder();
                   case 0:
                       System.out.println("Tak for at vælge Marios Pizza");
                       isRunning = false;
                       break;
                   default:
                       System.out.println("Ugyldigt valg-Vælg et tal fra menuen.");
                       break;
               }
           }
        }
    }

