package rest.helpers;

import javax.ws.rs.core.Response;

import rest.elements.JsonData;
import rest.elements.JsonExceptionData;

public interface ResponsePacker {

	<T> Response packOk(T data);
	<T> Response packError(JsonExceptionData exception);
}
