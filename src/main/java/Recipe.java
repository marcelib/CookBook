import java.awt.image.BufferedImage;
import java.util.List;

class Recipe {
    private String title;
    private String description;
    private List<Ingredient> ingredientList;
    private BufferedImage image;
    private BufferedImage miniature;
    private int numberOfPeople;

    Recipe (String title, String description, List<Ingredient> ingredientList, BufferedImage image, BufferedImage miniature, int numberOfPeople) {
        this.title = title;
        this.description = description;
        this.ingredientList = ingredientList;
        this.image = image;
        this.miniature = miniature;
        this.numberOfPeople = numberOfPeople;
    }

    String getTitle () {
        return title;
    }

    String getDescription () {
        return description;
    }

    List<Ingredient> getIngredientList () {
        return ingredientList;
    }

    BufferedImage getImage () {
        return image;
    }

    BufferedImage getMiniature () {
        return miniature;
    }

    int getNumberOfPeople () {
        return numberOfPeople;
    }
}
