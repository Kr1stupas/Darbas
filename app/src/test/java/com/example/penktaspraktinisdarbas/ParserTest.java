import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

import com.example.penktaspraktinisdarbas.Parser;

public class ParserTest {

    @Test
    public void testParseXml() {
        String xmlContent = "<root><item><title>Title 1</title><description>Desc 1</description></item></root>";
        List<String> result = Parser.parseXml(xmlContent);

        assertEquals(1, result.size());
        assertEquals("Title 1 - Desc 1", result.get(0));
    }
}
