package cookbook;

import cookbook.displays.CategoryDisplay;
import cookbook.displays.MenuDisplay;
import cookbook.displays.RecipeDisplay;
import cookbook.model.Category;
import cookbook.readers.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.SwingUtilities.invokeLater;

public class CookBook {

    private static final Logger LOGGER = Logger.getLogger(CookBook.class.getName());
    private static Integer currentCategory;
    private static Integer currentRecipe;
    private static List<Category> categories = new ArrayList<>();
    //Observer pattern- state is represented by these three objects
    private static RecipeDisplay recDisplay;
    private static CategoryDisplay catDisplay;
    private static MenuDisplay menuDisplay;

    private CookBook () {
    }

    public static void main (String[] args) throws IOException {

        try {
            categories = new JsonReader().readCategories("src/main/resources/json/categories.json");
            categories.forEach(Category::loadImages);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, "An exception has occured while loading categories", e);
            return;
        }

        invokeLater(() -> {
            setCurrRecipe(0);
            setCurrCategory(0);
            try {
                setRecDisplay(new RecipeDisplay(getCategories().get(0).getRecipeList().get(0)));
                setMenuDisplay(new MenuDisplay());
                setCatDisplay(new CategoryDisplay(getCategories().get(0)));
                getCatDisplay().createAndShowGUI();
                getRecDisplay().createAndShowGUI();
                getMenuDisplay().createAndShowGUI();
            } catch(IOException e) {
                LOGGER.log(Level.SEVERE, "An exception has occured while loading GUI", e);
            }
        });
    }

    public static Integer getCurrCategory () {
        return currentCategory;
    }

    public static void setCurrCategory (Integer currentCategory) {
        CookBook.currentCategory = currentCategory;
    }

    public static Integer getCurrRecipeIndex () {
        return currentRecipe;
    }

    public static void setCurrRecipe (Integer currentRecipe) {
        CookBook.currentRecipe = currentRecipe;
    }

    public static List<Category> getCategories () {
        return categories;
    }

    public static RecipeDisplay getRecDisplay () {
        return recDisplay;
    }

    private static void setRecDisplay (RecipeDisplay recDisplay) {
        CookBook.recDisplay = recDisplay;
    }

    public static CategoryDisplay getCatDisplay () {
        return catDisplay;
    }

    private static void setCatDisplay (CategoryDisplay catDisplay) {
        CookBook.catDisplay = catDisplay;
    }

    public static MenuDisplay getMenuDisplay () {
        return menuDisplay;
    }

    private static void setMenuDisplay (MenuDisplay menuDisplay) {
        CookBook.menuDisplay = menuDisplay;
    }
}
