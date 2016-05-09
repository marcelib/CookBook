import java.awt.image.BufferedImage;
import java.util.List;

public class Recipe {
    private String title;
    private String description;
    private List<Ingredient> ingredients;
    private BufferedImage image;
    private BufferedImage miniature;

    public Recipe(String title, String description, List<Ingredient> ingredients, BufferedImage image, BufferedImage miniature) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.image = image;
        this.miniature = miniature;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return getDescription();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage getMiniature() {
        return miniature;
    }

}
