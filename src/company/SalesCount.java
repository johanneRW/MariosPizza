package company;

public class SalesCount {
    public String pizzaName;
    public int count;

    public SalesCount(String pizzaName, int count) {
        this.pizzaName = pizzaName;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    @Override
    public String toString() {
        return "pizza= " + pizzaName + ", antal= " + count;
    }
}
