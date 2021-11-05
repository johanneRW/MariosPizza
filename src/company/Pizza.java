package company;


public class Pizza {

    private String name;
    private String[] ingredients;
    private int prize;
    private int antalSolgt;

    public Pizza(String name, String[] ingredients, int prize) {

        this.name = name;
        this.ingredients = ingredients;
        this.prize = prize;
        this.antalSolgt=0;
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

       for (String s : this.ingredients){
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString();


    }
}