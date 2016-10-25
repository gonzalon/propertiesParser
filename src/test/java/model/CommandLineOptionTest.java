package model;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Main;

public class CommandLineOptionTest {
	
	@Test(expected=ParserException.class)
	public void testWhenNoValidArgument() {
		String[] args = new String[]{ "--a" };
		Main.main(args);
	}
	
	@Test(expected=ParserException.class)
	public void testWhenInvalidFormat() {
		String[] args = new String[]{ "--input=test.properties", "--output=test.json", "--format=test" };
		Main.main(args);
	}
	
	@Test(expected=ParserException.class)
	public void testWhenArgumentWithoutValue() {
		String[] args = new String[]{ "--input", "--output=test.json", "--format=test" };
		Main.main(args);
	}
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void testHelp() {
		String expected = "usage: Parser -f,--format <Format>            Format for the output file [json, xml]. -i,--input <Input filename>     Input properties file. -o,--output <Output filename>   Output file.";
		
		String[] args = new String[]{ "--h"};
		Main.main(args);
		
		String result = outContent.toString().replaceAll("\\r?\\n", "");
		
		assertEquals(expected, result);		
	}
}