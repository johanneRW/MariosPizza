package company;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInterface {
    PizzaMenu menu = new PizzaMenu();
    OrderInterface OrderInterface = new OrderInterface();
    Scanner scanner = new Scanner(System.in);

    public UserInterface(){
        try {
           start();
        }catch(FileReadException exception) {
            System.out.println("Couldn't find the file needed");
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
            String selection = scanner.next();
            switch (selection) {
                case "1":
                    menu.printMenu();
                    break;
                case "2":
                    System.out.println("Skriv 'Q' for at oprette en quick-ordre (10 min.), ellers tryk 'enter' for at oprette en almindelig ordre");
                    String orderType = getUserInput();
                    System.out.println("Skriv venligst navnet på hvilke pizzaer der skal med i bestillingen fra den nedenstående liste(slut ordren med 0):");
                    System.out.println(OrderInterface.getMenu());
                    if (orderType.equalsIgnoreCase("q")) {
                        OrderInterface.addQuickOrder(this);
                    } else {
                        OrderInterface.addOrder(this);
                    }
                    break;
                case "3":
                    for (String order : OrderInterface.showOrders()) {
                        System.out.println(order);
                    }
                    break;
                case "4":
                    for (String order : OrderInterface.showOrders()) {
                        System.out.println(order);
                    }
                    System.out.println("Skriv ordrenummeret på ordren der skal arkiveres.");
                    int orderNumber = scanner.nextInt();
                    OrderInterface.sendOrderToArchive(this, orderNumber);
                    break;
                case "5":
                    for (SalesCount salesCount : OrderInterface.getSalesStats()) {
                        System.out.println(salesCount);
                    }
                    break;
                case "0":
                    System.out.println("Tak for at vælge Marios Pizza");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg - Vælg et tal fra menuen.");
                    break;
            }
        }
    }

    public void print (String output) {
        System.out.println(output);
    }

    public String getUserInput () {
        return scanner.nextLine().trim().toLowerCase();
    }

    public LocalTime getPickupTime () {
        print("Angiv et klokkeslæt (hh:mm)");
        String pickup = getUserInput();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime pickUpTime = LocalTime.parse(pickup, formatter);
        return pickUpTime;
    }
}

