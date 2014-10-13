package dao.car.mark;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Enityt model car mark.
 * 
 * @author Aleksei_Ivshin
 *
 */
@StaticMetamodel(Mark.class)
public class Mark_ {

	/**
	 * Car mark id.
	 */
	public static SingularAttribute<Mark, Integer> id;
	/**
	 * Car mark name.
	 */
	public static SingularAttribute<Mark, String> name;
}
