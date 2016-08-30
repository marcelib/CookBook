package cookbook.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    private final static String IMAGE_PATH = "/testImage.png";
    private String categoryJson;
    private Category testCategory;

    @Before
    public void setUp() throws Exception {


        Ingredient testIngredient1 = new Ingredient("Potato", 20, "Grams", 1);
        Ingredient testIngredient2 = new Ingredient("Fries", 200, "Grams", 1);

        Recipe testRecipe1 = new Recipe("Noodles", "Great noodles for everyone",
                Arrays.asList(testIngredient1, testIngredient2), IMAGE_PATH, 5);
        Recipe testRecipe2 = new Recipe("French fries", "A classic dinner!",
                Arrays.asList(testIngredient1, testIngredient2), IMAGE_PATH, 5);

        categoryJson = new JSONParser()
                .parse(new FileReader("./src/test/resources/json/testCategory.json")).toString();
        testCategory = new Category("Dinner", Arrays.asList(testRecipe1, testRecipe2), IMAGE_PATH);
        testCategory.loadImages();
    }

    @Test
    public void categoryImageTest() {
        assertEquals(576, testCategory.getCategoryImage().getIconHeight());
        assertEquals(768, testCategory.getCategoryImage().getIconWidth());
    }

    @Test
    public void imageListTest() {
        assertEquals(576, testCategory.getRecipeList().get(0).getImage().getIconHeight());
        assertEquals(768, testCategory.getRecipeList().get(0).getImage().getIconWidth());
    }

    @Test
    public void toJsonTest() throws Exception {
        String testCategoryJson = (new JSONParser()
                .parse(new ObjectMapper().writeValueAsString(testCategory))).toString();
        assertEquals(categoryJson, testCategoryJson);
    }
}
