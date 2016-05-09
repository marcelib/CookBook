import java.awt.image.BufferedImage;
import java.util.List;

class Category {
    private String title;
    private List<Recipe> recipeList;
    private BufferedImage categoryImage;
    private BufferedImage categoryMiniature;

    Category(String title, List<Recipe> recipeList, BufferedImage categoryImage, BufferedImage categoryMiniature) {
        this.title = title;
        this.recipeList = recipeList;
        this.categoryImage = categoryImage;
        this.categoryMiniature = categoryMiniature;
    }

    public String getTitle() {
        return title;
    }

    List<Recipe> getRecipeList() {
        return recipeList;
    }

    BufferedImage getCategoryImage() {
        return categoryImage;
    }

    BufferedImage getCategoryMiniature() {
        return categoryMiniature;
    }

}
