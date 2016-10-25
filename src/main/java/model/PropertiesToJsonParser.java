package model;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PropertiesToJsonParser extends Parser {
	
	@Override
	public void process(Map<String, Object> data) throws ParserException {
		if(data == null) throw new ParserException("Error getting the input file.");

        try {

		    Map<String, Object> root = new HashMap<String, Object>();
		    root.put("root", data);
		    Gson gson = new GsonBuilder().setPrettyPrinting().create();
		    
	        this.setParsedContent(gson.toJson(root));
	        
		} catch (Exception e) {
			throw new ParserException(e);
		}
	}

}