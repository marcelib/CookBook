package cookbook.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@JsonRootName(value = "category")
public class Category {

    @JsonIgnore
    private ImageIcon categoryImage;
    @JsonProperty("recipes")
    private List<Recipe> recipeList;
    @JsonProperty("imagePath")
    private String imagePath;
    private String title;

    @JsonCreator
    Category(@JsonProperty("title") String title,
             @JsonProperty("recipes") List<Recipe> recipeList,
             @JsonProperty("imagePath") String imagePath) throws IOException {
        this.title = title;
        this.recipeList = recipeList;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public ImageIcon getCategoryImage() {
        return categoryImage;
    }

    public void loadImages() {
        this.categoryImage = new ImageIcon(getClass().getResource(imagePath));
        recipeList.forEach(Recipe::loadImage);
    }
}