package rest.helpers;

import rest.elements.JsonData;
import rest.elements.JsonExceptionData;

public class ResponsePackerImpl implements ResponsePacker {


	@Override
	public <T> JsonData<T> packOk(T data) {
		return new JsonData<T>(data, JsonExceptionData.none());
	}

	@Override
	public <T> JsonData<T> packError(JsonExceptionData exception) {
		return new JsonData<T>(null, exception);
	}

}
