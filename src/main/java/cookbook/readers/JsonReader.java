package cookbook.readers;

import cookbook.model.Category;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonReader {

    public List<Category> readCategories(String filePath) throws IOException, ParseException {

        String categoriesJson = IOUtils.toString(this.getClass().getResourceAsStream(filePath));
        return Arrays.asList(new ObjectMapper().readValue(categoriesJson, Category[].class));
    }
}
