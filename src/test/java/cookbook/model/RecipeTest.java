package cookbook.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RecipeTest {

    private String recipeJson;
    private Recipe testRecipe;

    @Before
    public void setUp () throws IOException {
        recipeJson = new String(Files.readAllBytes(Paths.get("./src/test/resources/json/testRecipe.json")));
        testRecipe = new Recipe("Noodles", "Great noodles for everyone",
                Arrays.asList(new Ingredient("Potato", "Grams", 20, 1),
                        new Ingredient("Herbs", "Grams", 2, 1),
                        new Ingredient("Water", "Liters", 20, 1)),
                ImageIO.read(this.getClass().getResource("/testImage.png")),
                ImageIO.read(this.getClass().getResource("/testMiniature.png")), 5);
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
        assertEquals(100, testRecipe.getMiniature().getWidth());
        assertEquals(100, testRecipe.getMiniature().getHeight());
    }

    @Test
    public void toJsonTest () throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String testIngredientJson = mapper.writeValueAsString(testRecipe);
        assertEquals(recipeJson, testIngredientJson);
    }
}
