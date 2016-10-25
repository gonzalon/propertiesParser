package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Define the steps for the parsing of a file: load, mapping, processing and save.
 * Extend this class implement new Parsers.
 * @author Gonzalo Naveira
 *
 */
public abstract class Parser {
	
	protected String inputFile;
	protected String outputFile;
	
	private String parsedContent;
	
	/**
	 * Load, parse and save a file in a new format. 
	 * @throws IOException
	 */
	public void parse() throws IOException{
		Properties properties = this.loadFile();
		Map<String, Object> map = this.populateMap(properties);
		this.process(map);
		this.saveFile();
	}

	/**
	 * Should implement this method, for a new parser.
	 * Make sure calling setParsedContent(String output); for setting the data that's going to be saved. 
	 * @param data - Map with all the propertie information
	 * @throws ParserException
	 */
	protected abstract void process(Map<String, Object> data) throws ParserException;
	
	protected Properties loadFile() throws IOException{
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream(this.inputFile);
		prop.load(input);
		return prop;
	}
	
	protected Map<String, Object> populateMap(Properties properties){
	    Map<String, Object> map = new HashMap<String, Object>();
	    for (Object key : properties.keySet()) {
	    	List<String> keyList = new ArrayList<String>();
	    	String value = properties.getProperty((String) key).replaceAll("\"", "");
	    	if(key.toString().contains(".")){
	    		keyList = Arrays.asList(((String) key).split("\\."));
		        Map<String, Object> valueMap = populateMap(keyList, map);
		        valueMap.put(keyList.get(keyList.size() - 1), value);
	    	}else{
	    		map.put(key.toString(), value);
	    	}
	    }
	    return map;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> populateMap(List<String> keys, Map<String, Object> map) {
	    Map<String, Object> valueMap = (Map<String, Object>) map.get(keys.get(0));
	    if (valueMap == null) {
	        valueMap = new HashMap<String, Object>();
	    }
	    map.put(keys.get(0), valueMap);
	    Map<String, Object> out = valueMap;
	    if (keys.size() > 2) {
	        out = populateMap(keys.subList(1, keys.size()), valueMap);
	    }
	    return out;
	}
	
	private void saveFile() throws IOException{
		Path xmlOutput = Paths.get(this.getOutput());
        Files.write(xmlOutput, this.getParsedContent().getBytes("UTF-8"));
	}

	public void setInput(String value) {
		this.inputFile = value;
	}
	
	public void setOutput(String value) {
		this.outputFile = value;
	}
	
	public String getInput(){
		return this.inputFile;
	}
	
	public String getOutput(){
		return this.outputFile;
	}
	
	public void setParsedContent(String content) {
		this.parsedContent = content;
	}
	
	public String getParsedContent(){
		return this.parsedContent;
	}

}