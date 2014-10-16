package rest.helpers;

import javax.ws.rs.core.Response;

import rest.elements.JsonExceptionData;

public interface ResponsePacker {

	<T> Response packOk(T data);
	<T> Response packError(JsonExceptionData exception);
}
