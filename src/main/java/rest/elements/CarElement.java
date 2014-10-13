package rest.elements;

/**
 * Car domain implementation.
 * @author Aleksei_Ivshin
 *
 */
public class CarElement {

	/**
	 * Car id.
	 */
	private long id;
	
	/**
	 * Car mark name.
	 */
	private String mark;
	
	/**
	 * Car model name.
	 */
	private String model;
	
	/**
	 * Car modification.
	 */
	private String modification;

	/**
	 * Constructor with set car id.
	 * @param id car id
	 */
	public CarElement(final long id) {
		this.id = id;
	}
	

	public CarElement() {}
	
	public void setId(long id){
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModification() {
		return modification;
	}

	public void setModification(String modification) {
		this.modification = modification;
	}

	@Override
	public final String toString() {
		return "Car [id=" + id + ", mark=" + mark + ", model=" + model
				+ ", modification=" + modification + "]";
	}

}
