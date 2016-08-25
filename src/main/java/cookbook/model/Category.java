package cookbook.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

import java.awt.image.BufferedImage;
import java.util.List;

@JsonRootName(value = "category")
public class Category {

    @JsonProperty("title")
    private String title;
    @JsonProperty("recipes")
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

    public List<Recipe> getRecipeList () {
        return recipeList;
    }

    @JsonIgnore
    public BufferedImage getCatImage () {
        return categoryImage;
    }

    @JsonIgnore
    BufferedImage getCatMiniature () {
        return categoryMiniature;
    }
}
