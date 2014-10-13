package dao.store;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import dao.car.modification.Modification;

/**
 * Store entity model.
 * 
 * @author Aleksei_Ivshin
 *
 */
@StaticMetamodel(Store.class)
public class Store_ {
	/**
	 * Id.
	 */
	public static SingularAttribute<Store, Integer> id;
	/**
	 * Car modification.
	 */
	public static SingularAttribute<Store, Modification> modification;
	/**
	 * Car count.
	 */
	public static SingularAttribute<Store, Integer> count;
	/**
	 * Car price.
	 */
	public static SingularAttribute<Store, Float> price;
	/**
	 * Can test drive this car.
	 */
	public static SingularAttribute<Store, Boolean> testDriveAvaible;

}
