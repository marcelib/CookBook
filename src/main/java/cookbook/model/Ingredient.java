package cookbook.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName(value = "ingredient")
public class Ingredient {

    @JsonProperty("people")
    private int numberOfPeople;
    @JsonProperty("name")
    private String name;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("amount")
    private int amount;

    public Ingredient (String name, String unit, int amount, int numberOfPeople) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.numberOfPeople = numberOfPeople;
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

    public Ingredient reScale (int newNumberOfPeople) {
        int rescaledAmount = amount * newNumberOfPeople / numberOfPeople;
        return new Ingredient(name, unit, rescaledAmount, newNumberOfPeople);
    }

    @Override
    public String toString () {
        return name + "   " + amount + "  " + unit;
    }
}

