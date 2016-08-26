package cookbook.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    private String ingredientJson;
    private Ingredient testIngredient;

    @Before
    public void setUp () throws IOException {
        ingredientJson = new String(Files.readAllBytes(Paths.get("./src/test/resources/json/testIngredient.json")));
        testIngredient = new Ingredient("Pepper", "grams", 20, 1);
    }

    @Test
    public void toStringTest () {
        assertEquals("Pepper   20  grams", testIngredient.toString());
    }

    @Test
    public void reScaleTest () {
        Ingredient rescaledIngredient = testIngredient.reScale(5);
        assertEquals(rescaledIngredient.getAmount(), 100);
    }

    @Test
    public void rescaleAndToStringTest () {
        Ingredient rescaledIngredient = testIngredient.reScale(10);
        assertEquals("Pepper   200  grams", rescaledIngredient.toString());
    }

    @Test
    public void toJsonTest () throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String testIngredientJson = mapper.writeValueAsString(testIngredient);
        assertEquals(ingredientJson, testIngredientJson);
    }
}
