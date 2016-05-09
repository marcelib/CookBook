import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceli on 09.05.2016.
 */
public class RecipeTest {
    private Recipe testRecipe;

    @Before
    public void setUp() {
        testRecipe = new Recipe("Noodles", "Great noodles for everyone", Arrays.asList(new Ingredient("Potato", "Grams", 20),
                new Ingredient("Herbs", "Grams", 2),
                new Ingredient("Water", "Liters", 20)), null, null);

    }

    @Test
    public void TestIngredients() {
        List<Ingredient> ingredients = testRecipe.getIngredients();
        assertEquals("Potato", ingredients.get(0).getName());
        assertEquals("Grams", ingredients.get(0).getUnit());
        assertEquals(20, ingredients.get(0).getAmount());

    }

    @Test
    public void TestTitle() {
        assertEquals("Noodles", testRecipe.getTitle());
    }
    @Test
    public void TestDescription() {
        assertEquals("Great noodles for everyone", testRecipe.getDescription());
    }
    @Test
    public void TestImages() {
        assertEquals(null, testRecipe.getImage());
        assertEquals(null, testRecipe.getMiniature());
    }
}
