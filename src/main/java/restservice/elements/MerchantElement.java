package restservice.elements;

/**
 * Merchant domain implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class MerchantElement{

	/**
	 * Merchant id.
	 */
	private int id;
	/**
	 * Merchant name.
	 */
	private String name;
	/**
	 * Merchant surname.
	 */
	private String surname;
	/**
	 * Merchant patronymic.
	 */
	private String patronymic;

	/**
	 * Constructor with merchant id parameter.
	 * 
	 * @param id
	 *            merchant id
	 */
	public MerchantElement(final int id) {
		this.id = id;
	}
	
	public MerchantElement(){};

	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	@Override
	public final String toString() {
		return "CustomerDomain [id=" + id + ", surname=" + surname + ", name="
				+ name + ", patronymic=" + patronymic + "]";
	}
}
