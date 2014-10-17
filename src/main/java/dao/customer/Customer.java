package dao.customer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity of table 'customer'.
 * 
 * @author AlekseiIvshin
 *
 */
@Entity
@Table(name = "customer")
public class Customer {

	/**
	 * Customer id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_customer")
	private int id;

	/**
	 * Customer name.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Customer surname.
	 */
	@Column(name = "surname")
	private String surname;

	/**
	 * Customer patronymic.
	 */
	@Column(name = "patronic")
	private String patronymic;

	/**
	 * Customer passport series.
	 */
	@Column(name = "passport_series")
	private String passportSeries;

	/**
	 * Customer passport number.
	 */
	@Column(name = "passport_number")
	private String passportNumber;

	/**
	 * Customer birth date.
	 */
	@Column(name = "birthdate")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	/**
	 * Default constructor.
	 */
	public Customer() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getPassportSeries() {
		return passportSeries;
	}

	public void setPassportSeries(String passportSeries) {
		this.passportSeries = passportSeries;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "CustomerDomain [id=" + id + ", surname=" + surname + ", name="
				+ name + ", patronymic=" + patronymic + ", passport="
				+ passportSeries + " " + passportNumber + "]";
	}

}
