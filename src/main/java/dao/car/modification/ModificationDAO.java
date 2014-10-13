package dao.car.modification;

import java.util.List;

import dao.GenericDAO;
import dao.car.model.CarModel;

/**
 * Modification DAO interface.
 * @author Aleksei_Ivshin
 *
 */
public interface ModificationDAO extends GenericDAO<Modification, Long> {
	/**
	 * Find modification with name like parameter.
	 * 
	 * @param model
	 *            model of modification
	 * @param name
	 *            part or full car modification name
	 * @return founded modifications
	 */
	List<Modification> findAny(CarModel model, String name);
	
	/**
	 * Find modification with name equal parameter.
	 * 
	 * @param model
	 *            model of modification
	 * @param name
	 *            full modification name
	 * @return founded modification or null (if not found)
	 */
	Modification findOne(CarModel model, String name);


	List<Modification> findByMark(String markName);
	
}
