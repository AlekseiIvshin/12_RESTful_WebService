package rest.elements;

import rest.elements.JsonExceptionData;

public class JsonData<T> {
	private T data;
	private JsonExceptionData exception;
	

	public JsonData(T data, JsonExceptionData exception) {
		this.data = data;
		this.exception = exception;
	}

	public JsonData(){}
	
	public T getData() {
		return data;
	}

	public JsonExceptionData getException() {
		return exception;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setException(JsonExceptionData exception) {
		this.exception = exception;
	}
}
