package model;


import java.util.Map;

import com.thoughtworks.xstream.XStream;

import model.xml.MapEntryConverter;

public class PropertiesToXMLParser extends Parser {
	
	@Override
	public void process(Map<String, Object> data) throws ParserException {
		
		if(data == null) throw new ParserException("Error getting the input file.");
		
        try {	
	
	        XStream xStreamApi = new XStream();
	        xStreamApi.registerConverter(new MapEntryConverter());
	        String encoding = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	        xStreamApi.alias("root", Map.class);
	        String xml = encoding + "\n" + xStreamApi.toXML(data);
	        
	        this.setParsedContent(xml);
	        
		} catch (Exception e) {
			throw new ParserException(e);
		}
	}	
}
