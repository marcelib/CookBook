
public class Ingredient {
    private String name;
    private String unit;
    private final int defaultAmount;
    private int amount;

    public Ingredient(String name, String unit, int amount) {
        this.name = name;
        this.unit = unit;
        defaultAmount = amount;
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

    public void reScale(int numberOfPeople) {
        amount = numberOfPeople * defaultAmount;
    }

    public String toString() {
        return name + "   " + amount + "  " + unit;
    }
}
