package company;
import java.util.Scanner;

public class UserInterface {
    PizzaMenu menu = new PizzaMenu();
    public UserInterface() {
        start();
    }
    public void start() {
        System.out.println("velkommen hos Marios Pizza");
        System.out.println("---------------------------");

        mainMenu();
    }
    private void mainMenu() {
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
                    break;
                case 2:
                    menu.printMenuKort();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("Tak for at vælge Marios Pizza");
                    isRunning = false;
                    break;
            }
            }
        }
    }

