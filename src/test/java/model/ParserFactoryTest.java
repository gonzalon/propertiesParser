package model;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ParserFactoryTest {
	
	ParserFactory factory;
	
	@Before
	public void setUp(){
		factory = new ParserFactory();
	}
	
	@Test(expected=ParserException.class)
	public void testWhenEmptyHashMap(){
		factory.getParser(new HashMap<String, String>());
	}
	
	@Test(expected=ParserException.class)
	public void testWhenNullParameter(){
		factory.getParser(null);
	}	
	
	@Test(expected=ParserException.class)
	public void testWhenWrongFormatValue(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("format", "scv");
		factory.getParser(map);
	}
	
	@Test
	public void testParametersOkInParser(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("format", "json");
		map.put("input", "test.properties");
		map.put("output", "out.json");
		
		Parser parser = factory.getParser(map);

		assertEquals(parser.getInput(), "test.properties");
		assertEquals(parser.getOutput(), "out.json");
	}
	
	@Test
	public void testParametersWrongInParser(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("format", "json");
		map.put("input12", "test.properties");
		map.put("output42", "out.json");
		
		Parser parser = factory.getParser(map);

		assertEquals(parser.getInput(), null);
		assertEquals(parser.getOutput(), null);
	}
	
	
	
}
