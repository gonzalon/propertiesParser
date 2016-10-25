package model;
import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.mockito.Mock;

public class PropertiesToJsonParserTest {
	
	@Mock
	PropertiesToJsonParser p2json = new PropertiesToJsonParser();

	
	@Test
	public void testParserWhenResultIsOk(){
		Properties properties = new Properties();
		properties.setProperty("this.is.a.propertie", "value");
		
		Map<String, Object> map = p2json.populateMap(properties);
		
		try{
		p2json.process(map);
		}catch(Exception ex){
			// do nothing
		}
		
		String result = p2json.getParsedContent().replaceAll("\\r?\\n", "").replaceAll("\\s+", "").trim();
		String expected = "{\"root\":{\"this\":{\"is\":{\"a\":{\"propertie\":\"value\"}}}}}";
		
		assertEquals(expected, result);
	}
	
	@Test(expected=ParserException.class)
	public void testParserWhenWrongParameter(){
		Map<String, Object> map = null;
		p2json.process(map);
	}
	
}