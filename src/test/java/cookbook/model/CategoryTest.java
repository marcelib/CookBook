package cookbook.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    private String categoryJson;
    private Category testCategory;

    @Before
    public void setUp () throws Exception {

        BufferedImage testImage = ImageIO.read(this.getClass().getResource("/testImage.png"));
        BufferedImage testMiniature = ImageIO.read(this.getClass().getResource("/testMiniature.png"));

        Ingredient testIngredient1 = new Ingredient("Potato", "Grams", 20, 1);
        Ingredient testIngredient2 = new Ingredient("Fries", "Grams", 200, 1);

        Recipe testRecipe1 = new Recipe("Noodles", "Great noodles for everyone",
                Arrays.asList(testIngredient1, testIngredient2), testImage, testMiniature, 5);
        Recipe testRecipe2 = new Recipe("French fries", "A classic dinner!",
                Arrays.asList(testIngredient1, testIngredient2), testImage, testMiniature, 5);

        categoryJson = new JSONParser().parse(new FileReader("./src/test/resources/json/testCategory.json")).toString();
        testCategory = new Category("Dinner", Arrays.asList(testRecipe1, testRecipe2), testImage, testMiniature);
    }

    @Test
    public void categoryImageTest () {
        assertEquals(576, testCategory.getCategoryImage().getHeight());
        assertEquals(768, testCategory.getCategoryImage().getWidth());
        assertEquals(100, testCategory.getCategoryMiniature().getWidth());
        assertEquals(100, testCategory.getCategoryMiniature().getHeight());
    }

    @Test
    public void imageListTest () {
        assertEquals(576, testCategory.getRecipeList().get(0).getImage().getHeight());
        assertEquals(768, testCategory.getRecipeList().get(0).getImage().getWidth());
        assertEquals(100, testCategory.getRecipeList().get(1).getMiniature().getHeight());
        assertEquals(100, testCategory.getRecipeList().get(1).getMiniature().getWidth());
    }

    @Test
    public void toJsonTest () throws Exception {
        String testCategoryJson = (new JSONParser()
                .parse(new ObjectMapper().writeValueAsString(testCategory))).toString();
        assertEquals(categoryJson, testCategoryJson);
    }
}
