package company;
import java.util.Scanner;

public class UserInterface {
    PizzaMenu menu = new PizzaMenu();
    OrderInterface OrderInterface = new OrderInterface();

    public UserInterface(){
       try {
           start();
       }catch(FileReadException exception) {
           System.out.println("Couldn't find a menu");

    }
    }
    public void start() {
        System.out.println("Velkommen hos Marios Pizza");
        System.out.println("---------------------------");

        mainMenu();
    }

    private void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("""
                                        
                    Foretag et valg:
                    1) Se Menukortet
                    2) Opret en ordre
                    3) Se alle aktive ordrer
                    4) Færdiggør ordre
                    5) Udskriv statistik
                    0) Exit application""");
            int selection = scanner.nextInt();
            switch (selection) {
                case 1:
                    menu.printMenu();
                    break;
                case 2:
                    OrderInterface.addOrder();
                    break;
                case 3:
                    for (String order : OrderInterface.showOrders()) {
                        System.out.println(order);
                    }
                    break;
                case 4:
                    for (String order : OrderInterface.showOrders()) {
                        System.out.println(order);
                    }
                    System.out.println("Skriv ordrenummeret på ordren der skal arkiveres.");
                    int orderNumber = scanner.nextInt();
                    OrderInterface.sendOrderToArchive(orderNumber);
                    break;
                case 5:
                    for (SalesCount salesCount : OrderInterface.getSalesStats()) {
                        System.out.println(salesCount);
                    }
                    break;
                case 0:
                    System.out.println("Tak for at vælge Marios Pizza");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg - Vælg et tal fra menuen.");
                    break;
            }
        }
    }
}

