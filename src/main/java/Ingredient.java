
public class Ingredient {
    private String name;
    private String unit;
    private int amount;

    public Ingredient(String name, String unit, int amount) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getAmount() {
        return amount;
    }
}
