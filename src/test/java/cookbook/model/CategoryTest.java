package cookbook.model;

import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CategoryTest {
    private Category testCategory;

    @Before
    public void setUp () throws IOException {

        BufferedImage testImage = ImageIO.read(this.getClass().getResource("/testImage.png"));
        BufferedImage testMiniature = ImageIO.read(this.getClass().getResource("/testMiniature.png"));

        Ingredient testIngredient1 = new Ingredient("Potato", "Grams", 20, 1);
        Ingredient testIngredient2 = new Ingredient("Fries", "Grams", 200, 1);

        Recipe testRecipe1 = new Recipe("Noodles", "Great noodles for everyone",
                Arrays.asList(testIngredient1, testIngredient2), testImage, testMiniature, 5);
        Recipe testRecipe2 = new Recipe("French fries", "A classic dinner!",
                Arrays.asList(testIngredient1, testIngredient2), testImage, testMiniature, 5);

        testCategory = new Category("Dinner", Arrays.asList(testRecipe1, testRecipe2), testImage, testMiniature);
    }

    @Test
    public void testGetCategoryImage () {
        assertEquals(576, testCategory.getCatImage().getHeight());
        assertEquals(768, testCategory.getCatImage().getWidth());
        assertEquals(100, testCategory.getCatMiniature().getWidth());
        assertEquals(100, testCategory.getCatMiniature().getHeight());
    }

    @Test
    public void testListImage () {
        assertEquals(576, testCategory.getRecipeList().get(0).getImage().getHeight());
        assertEquals(768, testCategory.getRecipeList().get(0).getImage().getWidth());
        assertEquals(100, testCategory.getRecipeList().get(1).getMiniature().getHeight());
        assertEquals(100, testCategory.getRecipeList().get(1).getMiniature().getWidth());
    }
}
