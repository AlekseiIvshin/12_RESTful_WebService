package rest.helpers;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public interface RequestDataParser {

	<T> T parseData(String json, Class<T> targetClass) throws JsonParseException, JsonMappingException, IOException;
}
