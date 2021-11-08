package company;


public class Pizza {

    private String name;
    private String[] ingredients;
    private int prize;

    public Pizza(String name, String[] ingredients, int prize) {

        this.name = name;
        this.ingredients = ingredients;
        this.prize = prize;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getPrize() {
        return prize;
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
/*
       for (String s : this.ingredients){
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString();*/


    }
}