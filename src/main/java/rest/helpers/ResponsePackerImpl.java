package rest.helpers;

import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;

import rest.elements.JsonData;
import rest.elements.JsonExceptionData;

public class ResponsePackerImpl implements ResponsePacker {

	private final ObjectMapper jsonMapper = new ObjectMapper();

	public <T> Response packOk(T data) {
		try {
			return Response.ok(jsonMapper.writeValueAsString(packOkJson(data)),
					MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	public Response packError(JsonExceptionData exception) {
		try {
			return Response.ok(
					jsonMapper.writeValueAsString(packErrorJson(exception)),
					MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	private <T> JsonData<T> packOkJson(T data) {
		return new JsonData<T>(data, JsonExceptionData.none());
	}

	private <T> JsonData<T> packErrorJson(JsonExceptionData exception) {
		return new JsonData<T>(null, exception);
	}
}
