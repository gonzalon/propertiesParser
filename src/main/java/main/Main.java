package main;


import java.util.HashMap;

import org.apache.commons.cli.HelpFormatter;

import model.CommandLineUtil;
import model.Parser;
import model.ParserException;
import model.ParserFactory;

/**
 * Java program that parses a properties file and generates and output in different formats (XML and JSON so far)
 * The program should take the following arguments: 
 * Long form: `--input=<file> --output=<file> --format=<format>`
 * Short form: `-i <file> -o <file> -f <format>
 * @version 1
 * @author Gonzalo Naveira
 */
public class Main {
	
	public static void main(String[] args) throws ParserException{

		try {
			CommandLineUtil clu = new CommandLineUtil();
	        if(clu.areMainOptions(args)){	        	// Main options
	
	        	ParserFactory factory = new ParserFactory();
	        	HashMap<String, String> arguments = clu.populateArguments(args);
	            Parser fileParser = factory.getParser(arguments);
	            fileParser.parse();
	            
	        }else{	        	// Help
	        	HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("Parser", clu.getMainOptions());        	
	        }

        } catch (Exception e) {
            throw new ParserException(e);
        }
	}
}