import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Marceli on 09.05.2016.
 */

public class IngredientTest {
    private Recipe testRecipe;
    @Before
    public void setUp() {
        testRecipe = new Recipe("Noodles", "Great noodles for everyone", Arrays.asList(new Ingredient("Potato", "Grams", 20),
                new Ingredient("Herbs", "Grams", 2),
                new Ingredient("Water", "Liters", 20)), null, null);

    }
}
