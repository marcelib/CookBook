package cookbook.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName(value = "ingredient")
public class Ingredient {

    @JsonProperty("defaultAmount")
    private final int defaultAmount;
    @JsonProperty("name")
    private String name;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("amount")
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

    @Override
    public String toString () {
        return name + "   " + amount + "  " + unit;
    }
}

