package model;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class CommandLineUtilTest {

	@Test(expected=ParseException.class)
	public void testWhenWrongParameters() throws ParseException{
		CommandLineUtil clu = new CommandLineUtil();
		clu.populateArguments(null);
	}
	
	@Test
	public void testLongParametersWhenOk() throws ParseException{
		CommandLineUtil clu = new CommandLineUtil();
		String[] args = new String[]{ "--input=test.properties", "--output=test.json", "--format=test" };
		HashMap<String, String> result = clu.populateArguments(args);
		
		Assert.assertNotNull(result.get("input"));
		Assert.assertNotNull(result.get("output"));
		Assert.assertNotNull(result.get("format"));
		assertEquals(result.get("input"), "test.properties");
		assertEquals(result.get("output"), "test.json");
		assertEquals(result.get("format"), "test");
		
	}
	
	@Test
	public void testShortParametersWhenOk() throws ParseException{
		CommandLineUtil clu = new CommandLineUtil();
		String[] args = new String[]{ "-i=test.properties", "-o=test.json", "-f=test" };
		HashMap<String, String> result = clu.populateArguments(args);
		
		Assert.assertNotNull(result.get("input"));
		Assert.assertNotNull(result.get("output"));
		Assert.assertNotNull(result.get("format"));
		assertEquals(result.get("input"), "test.properties");
		assertEquals(result.get("output"), "test.json");
		assertEquals(result.get("format"), "test");
	}
	
}
