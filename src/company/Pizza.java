package company;


public class Pizza {

    private String name;
    private String[] ingredients;
    private int price;

    public Pizza(String name, String[] ingredients, int price) {

        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getIngredients() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ingredients.length; i++) {
            String ingredient = ingredients[i];
            sb.append(ingredient);
            if (i == ingredients.length - 2) {
                sb.append(" og ");
            } else if (i < ingredients.length - 2) {
                sb.append(" ");
            }
        }
        return sb.toString();

    }
}