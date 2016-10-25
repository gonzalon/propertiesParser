package model;
import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.mockito.Mock;

public class PropertiesToXmlParserTest {
	
	@Mock
	PropertiesToXMLParser p2xml = new PropertiesToXMLParser();

	
	@Test
	public void testParserWhenResultIsOk(){
		Properties properties = new Properties();
		properties.setProperty("this.is.a.propertie", "value");
		
		Map<String, Object> map = p2xml.populateMap(properties);
		
		try{
		p2xml.process(map);
		}catch(Exception ex){
			// do nothing
		}
		
		String result = p2xml.getParsedContent().replaceAll("\\r?\\n", "");
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>  <this>    <is>      <a>        <propertie>value</propertie>      </a>    </is>  </this></root>";
		
		assertEquals(expected, result);
	}
	
	@Test(expected=ParserException.class)
	public void testParserWhenWrongParameter(){
		Map<String, Object> map = null;
		p2xml.process(map);
	}
	
}