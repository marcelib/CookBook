package cookbook.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName(value = "ingredient")
public class Ingredient {

    @JsonProperty("name")
    private String name;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("people")
    private int people;

    @JsonCreator
    Ingredient (
            @JsonProperty("name") String name,
            @JsonProperty("amount") int amount,
            @JsonProperty("unit") String unit,
            @JsonProperty("people") int people) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.people = people;
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
        int rescaledAmount = amount * newNumberOfPeople / people;
        return new Ingredient(name, rescaledAmount, unit, newNumberOfPeople);
    }

    @Override
    public String toString () {
        return name + "   " + amount + "  " + unit;
    }
}

