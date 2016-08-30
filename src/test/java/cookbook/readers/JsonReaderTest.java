package cookbook.readers;

import cookbook.model.Category;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonReaderTest {

    @Test
    public void readCategories() throws Exception {
        List<Category> categoryList = new JsonReader().readCategories("/json/testCategories.json");
        assertEquals(categoryList.get(0).getTitle(), "Dinner");
        assertEquals(categoryList.get(0).getRecipeList().get(0).getTitle(), "Noodles");
        assertEquals(categoryList.get(0).getRecipeList().get(0).getDescription(), "Great noodles for everyone");
    }
}