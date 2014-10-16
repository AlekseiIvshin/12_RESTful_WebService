package rest.helpers;

import rest.elements.JsonData;
import rest.elements.JsonExceptionData;

public interface ResponsePacker {

	<T> JsonData<T> packOk(T data);
	<T> JsonData<T> packError(JsonExceptionData exception);
}
