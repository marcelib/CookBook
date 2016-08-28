package cookbook.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RecipeTest {

    private final static String IMAGE_PATH = "src/test/resources/testImage.png";
    private String recipeJson;
    private Recipe testRecipe;

    @Before
    public void setUp () throws Exception {
        recipeJson = new JSONParser()
                .parse(new FileReader("./src/test/resources/json/testRecipe.json")).toString();
        testRecipe = new Recipe("Noodles", "Great noodles for everyone",
                Arrays.asList(new Ingredient("Potato", 20, "Grams", 1),
                        new Ingredient("Herbs", 2, "Grams", 1),
                        new Ingredient("Water", 20, "Liters", 1)),
                IMAGE_PATH, 5);
        testRecipe.loadImage();
    }

    @Test
    public void ingredientsTest () {
        assertEquals("Potato", testRecipe.getIngredientList().get(0).getName());
        assertEquals("Grams", testRecipe.getIngredientList().get(0).getUnit());
        assertEquals(20, testRecipe.getIngredientList().get(0).getAmount());

    }

    @Test
    public void titleTest () {
        assertEquals("Noodles", testRecipe.getTitle());
    }

    @Test
    public void descriptionTest () {
        assertEquals("Great noodles for everyone", testRecipe.getDescription());
    }

    @Test
    public void imagesTest () throws IOException {
        assertEquals(576, testRecipe.getImage().getHeight());
        assertEquals(768, testRecipe.getImage().getWidth());
    }

    @Test
    public void toJsonTest () throws Exception {
        String testRecipeJSON = (new JSONParser()
                .parse(new ObjectMapper().writeValueAsString(testRecipe))).toString();
        assertEquals(recipeJson, testRecipeJSON);
    }
}
