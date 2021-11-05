package company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Statistik {
    public ArrayList<SalesCount> getPizzasHistory(String STATS_FILE_PATH) throws FileNotFoundException {
        File file = new File(STATS_FILE_PATH);
        Scanner scanner = new Scanner(file);
        ArrayList<SalesCount> salg = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String found = scanner.nextLine();
            int startPåOrdre = found.indexOf("[");
            int slutPåOrdre = found.indexOf("]");
            String[] pizzaHistorik = found.substring(startPåOrdre + 1, slutPåOrdre).split(", ");

            for (int i = 0; i < pizzaHistorik.length; i++) {
                String pizza=pizzaHistorik[i];
                if (salg.size() == 0) {
                    salg.add(new SalesCount(pizza, 1));
                } else {
                    boolean done = false;
                    for (int j = 0; j < salg.size(); j++) {
                        SalesCount sc = salg.get(j);
                        if (sc.getPizzaName().equalsIgnoreCase(pizza)) {
                            sc.count= sc.getCount()+1;
                            salg.set(j, sc);
                            done = true;
                        }
                    }
                    if (!done){

                            salg.add(new SalesCount(pizza, 1));
                        }
                    }
                }
            }

        return salg;


        //new ArrayList<>(Arrays.asList(pizzaHistorik));
    }
}
