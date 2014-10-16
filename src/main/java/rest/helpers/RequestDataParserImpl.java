package rest.helpers;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rest.elements.CarElement;
import rest.elements.JsonExceptionData;

public class RequestDataParserImpl implements RequestDataParser {

	static final Logger logger = LoggerFactory
			.getLogger(RequestDataParserImpl.class);

	protected final ObjectMapper jsonMapper;

	public RequestDataParserImpl() {
		jsonMapper = new ObjectMapper();
	}

	@Override
	public <T> T parseData(String json, Class<T> targetClass) throws JsonParseException, JsonMappingException, IOException {
		return jsonMapper.readValue(json, targetClass);
	}

}
