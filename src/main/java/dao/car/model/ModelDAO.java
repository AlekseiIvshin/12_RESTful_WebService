package dao.car.model;

import java.util.List;

import dao.GenericDAO;
import dao.car.mark.Mark;

/**
 * Model DAO interface.
 * 
 * @author Aleksei_Ivshin
 *
 */
public interface ModelDAO extends GenericDAO<CarModel, Integer> {

	/**
	 * Find models with name like parameter.
	 * 
	 * @param mark
	 *            mark of model
	 * @param name
	 *            part or full car model name
	 * @return founded models
	 */
	List<CarModel> findAny(Mark mark, String name);

	/**
	 * Find model with name equal parameter.
	 * 
	 * @param mark
	 *            mark of model
	 * @param name
	 *            full model name
	 * @return founded model or null (if not found)
	 */
	CarModel findOne(Mark mark, String name);
	
	/**
	 * Find car model. If not founded create this model
	 * 
	 * @param mark
	 *            car mark
	 * @param name
	 *            model name
	 * @return founded or created model
	 */
	CarModel findOrCreate(Mark mark, String name);
}
