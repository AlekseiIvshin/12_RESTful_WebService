package dao.car.modification;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import dao.car.model.CarModel;

/**
 * Entity model of car modification.
 * 
 * @author Aleksei_Ivshin
 *
 */
@StaticMetamodel(Modification.class)
public class Modification_ {

	/**
	 * Modification id.
	 */
	public static SingularAttribute<Modification, Integer> id;
	/**
	 * Modification name.
	 */
	public static SingularAttribute<Modification, String> name;
	/**
	 * Modification model.
	 */
	public static SingularAttribute<Modification, CarModel> model;
}
