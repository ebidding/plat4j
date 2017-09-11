package org.net.plat4j.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.net.plat4j.sr.core.utils.LogHelper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils {
	protected static LogHelper logger = new LogHelper(JsonUtils.class);
	private final static ObjectMapper objectMapper;
	
	static{
		objectMapper = new ObjectMapper();
	}
	 
    public static <T> T toBean(String content, Class<T> valueType) {
    	
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
 
        return null;
    }
    
    public static <T> List<T> toList(String content, Class<T> valueType) {

    	try {
	    	JsonParser parser = objectMapper.getFactory().createParser(content);   
	    	JsonNode nodes = parser.readValueAsTree();
	    	List<T> list  = new ArrayList<T>(nodes.size());
	    	for(JsonNode node : nodes){
	    		list.add(objectMapper.readValue(node.toString(), valueType));
	    	} 
	    	
	    	return list;
    	} catch(Exception e) {
    		logger.error(e.getMessage(), e);
    	}
    	return null;
	 }
 
    public static String toJSon(Object object) {
    	
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
 
        return null;
    }
}
