package rest.elements;

public class JsonData<T> {
	private final T data;
	private final JsonExceptionData exception;
	
	public JsonData(T data, JsonExceptionData exception){
		this.data = data;
		this.exception = exception;
	}

	public T getData() {
		return data;
	}

	public JsonExceptionData getException() {
		return exception;
	}
}
