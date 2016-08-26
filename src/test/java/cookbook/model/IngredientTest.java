package cookbook.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {
    private Ingredient testIngredient;

    @Before
    public void setUp () {
        testIngredient = new Ingredient("Pepper", "grams", 20, 1);
    }

    @Test
    public void testToString () {
        assertEquals("Pepper   20  grams", testIngredient.toString());
    }

    @Test
    public void testReScale () {
        Ingredient rescaledIngredient = testIngredient.reScale(5);
        assertEquals(rescaledIngredient.getAmount(), 100);
    }

    @Test
    public void testRescaleAndToString () {
        Ingredient rescaledIngredient = testIngredient.reScale(10);
        assertEquals("Pepper   200  grams", rescaledIngredient.toString());
    }
}
