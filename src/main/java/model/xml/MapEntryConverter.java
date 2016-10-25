package model.xml;

import java.util.AbstractMap;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MapEntryConverter implements Converter {
	
	@SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return AbstractMap.class.isAssignableFrom(clazz);
    }	

    /**
     * Transform a Map to the XML structure
     */
    @SuppressWarnings("rawtypes")
	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        AbstractMap map = (AbstractMap) value;
        for (Object obj : map.entrySet()) {
            Map.Entry entry = (Map.Entry) obj;
            writer.startNode(entry.getKey().toString());
            Object val = entry.getValue();
            if ( null != val ) {
                //writer.setValue();
            	if(val instanceof String){
            		writer.setValue(val.toString());
            	}else{
            		this.marshal(val, writer, context);	
            	}
            }
            writer.endNode();
        }
    }
    
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
    	return null;
    }

}