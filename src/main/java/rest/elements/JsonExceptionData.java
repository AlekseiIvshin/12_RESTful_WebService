package rest.elements;

public class JsonExceptionData {

	private final int status;
	private final String exceptionClass;
	private final String exceptionMessage;
	
	public static JsonExceptionData none(){
		return new JsonExceptionData();
	}
	
	public static JsonExceptionData withError(String exceptionClass, String message){
		return new JsonExceptionData(exceptionClass, message);
	}
	
	private JsonExceptionData(){
		status = -1;
		exceptionClass = null;
		exceptionMessage = null;
	}
	
	private JsonExceptionData(String exceptionClass, String message){
		this.status = 1;
		this.exceptionClass = exceptionClass;
		this.exceptionMessage = message;
		
	}
	
	public int getStatus() {
		return status;
	}
	public String getExceptionClass() {
		return exceptionClass;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
}
