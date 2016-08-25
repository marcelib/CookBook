package cookbook.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

import java.awt.image.BufferedImage;
import java.util.List;

@JsonRootName(value = "recipe")
public class Recipe {

    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("ingredients")
    private List<Ingredient> ingredientList;
    @JsonIgnore
    private BufferedImage image;
    @JsonIgnore
    private BufferedImage miniature;
    @JsonProperty("numberOfPeople")
    private int numberOfPeople;

    public Recipe (String title, String description, List<Ingredient> ingredientList, BufferedImage image, BufferedImage miniature, int numberOfPeople) {
        this.title = title;
        this.description = description;
        this.ingredientList = ingredientList;
        this.image = image;
        this.miniature = miniature;
        this.numberOfPeople = numberOfPeople;
    }

    public String getTitle () {
        return title;
    }

    public String getDescription () {
        return description;
    }

    public List<Ingredient> getIngredientList () {
        return ingredientList;
    }

    @JsonIgnore
    public BufferedImage getImage () {
        return image;
    }

    @JsonIgnore
    BufferedImage getMiniature () {
        return miniature;
    }

    public int getNumberOfPeople () {
        return numberOfPeople;
    }
}
