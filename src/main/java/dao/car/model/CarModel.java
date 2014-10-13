package dao.car.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import dao.car.mark.Mark;

/**
 * Entity of table 'car_model'.
 * 
 * @author AlekseiIvshin
 *
 */
@Entity
@Table(name = "car_model", 
	uniqueConstraints = { @UniqueConstraint(columnNames = {
		"name", "id_car_mark" }) })
public class CarModel {

	/**
	 * Car model id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_car_model")
	private int id;

	/**
	 * Car model name.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Car mark.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_car_mark")
	private Mark mark;

	/**
	 * Default constructor.
	 */
	public CarModel() {
	}

	/**
	 * Get model id.
	 * 
	 * @return id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * Set model id.
	 * 
	 * @param id
	 *            new id
	 */
	public final void setId(final int id) {
		this.id = id;
	}

	/**
	 * Get model name.
	 * 
	 * @return name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Set model name.
	 * 
	 * @param name
	 *            new name
	 */
	public final void setName(final String name) {
		this.name = name;
	}

	/**
	 * Get model mark.
	 * 
	 * @return current mark
	 */
	public final Mark getMark() {
		return mark;
	}

	/**
	 * Set model mark.
	 * 
	 * @param mark
	 *            model mark
	 */
	public final void setMark(final Mark mark) {
		this.mark = mark;
	}

	@Override
	public final String toString() {
		return "Model [id=" + id + ", name=" + name + ", mark="
				+ mark.toString() + "]";
	}
}
