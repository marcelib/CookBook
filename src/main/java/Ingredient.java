
class Ingredient {
    private String name;
    private String unit;
    private final int defaultAmount;
    private int amount;

    Ingredient(String name, String unit, int amount) {
        this.name = name;
        this.unit = unit;
        defaultAmount = amount;
        this.amount = amount;
    }

    String getName() {
        return name;
    }

    String getUnit() {
        return unit;
    }

    int getAmount() {
        return amount;
    }

    void reScale(int numberOfPeople) {
        amount = numberOfPeople * defaultAmount;
    }

    public String toString() {
        return name + "   " + amount + "  " + unit;
    }
}
