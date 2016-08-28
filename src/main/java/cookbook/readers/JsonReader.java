package cookbook.readers;

import cookbook.model.Category;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonReader {

    public List<Category> readCategories (String filePath) throws IOException, ParseException {

        String categoriesJson = new JSONParser()
                .parse(new FileReader(filePath)).toString();
        return Arrays.asList(new ObjectMapper().readValue(categoriesJson, Category[].class));
    }
}
