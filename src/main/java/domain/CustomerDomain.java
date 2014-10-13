package domain;

import java.util.Date;

/**
 * Customer domain implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class CustomerDomain{

	/**
	 * Customer id.
	 */
	private int id;
	/**
	 * Customer name.
	 */
	private String name;
	/**
	 * Customer surname.
	 */
	private String surname;
	/**
	 * Customer patronymic.
	 */
	private String patronymic;
	/**
	 * Customer passport number.
	 */
	private String passportNumber;
	/**
	 * Customer passport series.
	 */
	private String passportSeries;
	/**
	 * Customer birth date.
	 */
	private Date birthDate;
	

	/**
	 * Default constructor.
	 */
	public CustomerDomain() {
	};

	/**
	 * Constructor with id parameter.
	 * 
	 * @param id
	 *            customer id
	 */
	public CustomerDomain(int id) {
		this.id = id;
	}

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

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPassportSeries() {
		return passportSeries;
	}

	public void setPassportSeries(String passportSeries) {
		this.passportSeries = passportSeries;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public final String toString() {
		return "CustomerDomain [id=" + id + ", surname=" + surname + ", name="
				+ name + ", patronymic=" + patronymic + ", passport="
				+ passportSeries + " " + passportNumber + "]";
	}
}
