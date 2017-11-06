
package com.gongsibao.api.config;

import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonContextProvider implements ContextResolver<ObjectMapper> {
    final ObjectMapper d;
    final ObjectMapper c;

    public JsonContextProvider() {
        d = createDefaultMapper();
        c = createDateMapper();
    }

    private static ObjectMapper createDateMapper() {
        ObjectMapper result = new ObjectMapper();
        result.configure(Feature.INDENT_OUTPUT, true);
        return result;
    }

    private static ObjectMapper createDefaultMapper() {
        ObjectMapper result = new ObjectMapper();
        result.configure(Feature.INDENT_OUTPUT, true);
        return result;
    }
    
    @Override
    public ObjectMapper getContext(Class<?> type) {
    	
        if (type == Date.class) {
            return c;
        } else {
            return d;
        }
    }
}
