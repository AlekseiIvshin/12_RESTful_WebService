package dao.store;

import dao.GenericDAO;
import dao.car.modification.Modification;

/**
 * Store DAO interface.
 * 
 * @author Aleksei_Ivshin
 *
 */
public interface StoreDAO extends GenericDAO<Store, Integer> {

	/**
	 * Find store by car modification.
	 * 
	 * @param modification
	 *            car modification
	 * @return founded store item
	 */
	Store find(Modification modification);
}
