package cookbook.model;

import java.awt.image.BufferedImage;
import java.util.List;

public class Recipe {
    private String title;
    private String description;
    private List<Ingredient> ingredientList;
    private BufferedImage image;
    private BufferedImage miniature;
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

    public BufferedImage getImage () {
        return image;
    }

    BufferedImage getMiniature () {
        return miniature;
    }

    public int getNumberOfPeople () {
        return numberOfPeople;
    }
}
