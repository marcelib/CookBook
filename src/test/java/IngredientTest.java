import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {
    private Ingredient testIngredient;

    @Before
    public void setUp() {
        testIngredient = new Ingredient("Pepper", "grams", 20);

    }

    @Test
    public void testToString() {
        assertEquals("Pepper   20  grams", testIngredient.toString());
    }

    @Test
    public void reScaleTest() {
        testIngredient.reScale(5);
        assertEquals("Pepper   100  grams", testIngredient.toString());
        testIngredient.reScale(2);
        assertEquals("Pepper   40  grams", testIngredient.toString());
        testIngredient.reScale(3);
        assertEquals("Pepper   60  grams", testIngredient.toString());
    }
}
