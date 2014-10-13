package dao.car.modification;

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

import dao.car.model.CarModel;

/**
 * Entity of table 'modification'.
 * 
 * @author AlekseiIvshin
 *
 */
@Entity
@Table(name = "car_modification", 
	uniqueConstraints = { @UniqueConstraint(columnNames = {
		"name", "id_car_model" }) })
public class Modification {

	/**
	 * Modification id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_car_modification")
	private long id;

	/**
	 * Modification name.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Modification model.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_car_model")
	private CarModel model;

	/**
	 * Default constructor.
	 */
	public Modification() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Modification [id=" + id + ", name=" + name + ", model="
				+ model.toString() + "]";
	}
}
