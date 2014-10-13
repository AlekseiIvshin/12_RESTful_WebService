package dao.merchant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of table 'merchant'.
 * @author AlekseiIvhin
 *
 */
@Entity
@Table(name = "merchant")
public class Merchant {

	/**
	 * Merchant id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_merchant")
	private int id;
	/**
	 * Merchant name.
	 */
	@Column(name = "name")
	private String name;
	
	/** 
	 * Merchant surname.
	 */
	@Column(name = "surname")
	private String surname;
	
	/**
	 * Merchant patronymic.
	 */
	@Column(name = "patronic")
	private String patronymic;
	
	/**
	 * Merchant is deleted.
	 */
	@Column(name = "deleted", columnDefinition = "BIT", length = 1)
	private boolean deleted;
	
	/**
	 * Default constructor.
	 */
	public Merchant() { }

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

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
