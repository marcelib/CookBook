package cookbook.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    private String ingredientJson;
    private Ingredient testIngredient;

    @Before
    public void setUp () throws Exception {
        ingredientJson = new JSONParser()
                .parse(new FileReader("./src/test/resources/json/testIngredient.json")).toString();
        testIngredient = new Ingredient("Pepper", 20, "grams", 1);
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
    public void toJsonTest () throws Exception {
        String testIngredientJson = (new JSONParser()
                .parse(new ObjectMapper().writeValueAsString(testIngredient))).toString();
        assertEquals(testIngredientJson, ingredientJson);
    }
}
