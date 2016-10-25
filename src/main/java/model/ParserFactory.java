package model;

import java.util.HashMap;
import java.util.Map;

public class ParserFactory {
	
	public static String FORMAT = "format";
	public static String INPUT  = "input";
	public static String OUTPUT = "output";
	
	public enum ParserType{
		JSON{
			public Parser create(){
				return new PropertiesToJsonParser();
			}
		},
		XML{
			public Parser create(){
				return new PropertiesToXMLParser();
			}
		};
		
		static final Map<String,ParserType> parserMapByFormat = new HashMap<String,ParserType>();
	    static {
	        for (ParserType parserType : ParserType.values()){
	            parserMapByFormat.put(parserType.name().trim().toLowerCase(), parserType);
	        }
	    }
	    
	    public static ParserType get(String name){
	    	return parserMapByFormat.get(name);
	    }
	    
	     abstract public Parser create();
	}
	
	public ParserFactory(){}
	
	/**
	 * Create a parser regarding the options: format, input, output.
	 * @param HashMap<String, String> optionValue 
	 * @return Parser - Parsed for the chosen format
	 */
	public Parser getParser(HashMap<String, String> optionValue){
		ParserType parserType = null;
		String formatToBuild = null;
		
		try{
			formatToBuild = optionValue.get(FORMAT);
			parserType = ParserType.get(formatToBuild.trim().toLowerCase());
		}catch(Exception ex){
			throw new ParserException("Can't build the Parser");
		}
		
		if(parserType == null){
			throw new ParserException(formatToBuild  + ": Format not available yet.");
		}					
		Parser parser = parserType.create();
		
		if(parser == null){
			throw new ParserException(formatToBuild  + ": Format not available yet.");
		}
		
		parser.setInput(optionValue.get(INPUT));
		parser.setOutput(optionValue.get(OUTPUT));
		
		return parser;
	}

}