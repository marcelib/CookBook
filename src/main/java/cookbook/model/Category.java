package cookbook.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.awt.image.BufferedImage;
import java.util.List;

public class Category {
    private String title;
    private List<Recipe> recipeList;
    @JsonIgnore
    private BufferedImage categoryImage;
    @JsonIgnore
    private BufferedImage categoryMiniature;

    public Category (String title, List<Recipe> recipeList, BufferedImage categoryImage, BufferedImage categoryMiniature) {
        this.title = title;
        this.recipeList = recipeList;
        this.categoryImage = categoryImage;
        this.categoryMiniature = categoryMiniature;
    }

    public String getTitle () {
        return title;
    }

    public List<Recipe> getRecList() {
        return recipeList;
    }

    public BufferedImage getCatImage() {
        return categoryImage;
    }

    BufferedImage getCatMiniature() {
        return categoryMiniature;
    }

}
