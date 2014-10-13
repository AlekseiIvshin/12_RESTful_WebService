package dao.car.mark;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of table 'car_mark'.
 * 
 * @author AlekseiIvshin
 *
 */
@Entity
@Table(name = "car_mark")
public class Mark {

	/**
	 * Car mark id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_car_mark")
	private int id;

	/**
	 * Car mark name.
	 */
	@Column(name = "name", unique = true)
	private String name;

	/**
	 * Default constructor.
	 */
	public Mark() {
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param name
	 *            new mark name
	 */
	public Mark(final String name) {
		this.name = name;
	}

	public long getId() {
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

	@Override
	public final String toString() {
		return "Mark [id=" + id + ", name=" + name + "]";
	}
}
