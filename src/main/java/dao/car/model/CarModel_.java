package dao.car.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import dao.car.mark.Mark;

/**
 * Entity model for car model.
 * @author Aleksei_Ivshin
 *
 */
@StaticMetamodel(CarModel.class)
public class CarModel_ {

	public static SingularAttribute<CarModel, Integer> id;
	public static SingularAttribute<CarModel, String> name;
	public static SingularAttribute<CarModel, Mark> mark;
}
