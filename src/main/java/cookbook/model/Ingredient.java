package cookbook.model;

public class Ingredient {
    private final int defaultAmount;
    private String name;
    private String unit;
    private int amount;

    public Ingredient (String name, String unit, int amount, int numberOfPeople) {
        this.name = name;
        this.unit = unit;
        defaultAmount = (amount + numberOfPeople - 1) / numberOfPeople;
        this.amount = defaultAmount;
    }

    String getName () {
        return name;
    }

    String getUnit () {
        return unit;
    }

    int getAmount () {
        return amount;
    }

    public void reScale (int numberOfPeople) {
        amount = numberOfPeople * defaultAmount;
    }

    public String toString () {
        return name + "   " + amount + "  " + unit;
    }
}

