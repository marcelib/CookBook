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

@JsonRootName(value = "recipe")
public class Recipe {

    @JsonIgnore
    private ImageIcon image;
    @JsonProperty("ingredients")
    private List<Ingredient> ingredientList;
    @JsonProperty("imagePath")
    private String imagePath;
    private String title;
    private String description;
    private int people;

    @JsonCreator
    Recipe(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("ingredients") List<Ingredient> ingredientList,
            @JsonProperty("imagePath") String imagePath,
            @JsonProperty("people") int people) {
        this.title = title;
        this.description = description;
        this.ingredientList = ingredientList;
        this.imagePath = imagePath;
        this.people = people;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    @JsonIgnore
    public ImageIcon getImage() {
        return image;
    }

    public int getPeople() {
        return people;
    }

    void loadImage() {
        this.image = new ImageIcon(getClass().getResource(imagePath));
    }
}
