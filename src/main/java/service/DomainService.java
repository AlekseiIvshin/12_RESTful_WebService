package service;

import java.sql.SQLException;
import java.util.List;

/**
 * Domain service interface.
 * 
 * @author Aleksei_Ivshin
 *
 * @param <DomainClass>
 *            Domain class
 * @param <IdType>
 *            Domain id type
 */
public interface DomainService<DomainClass, IdType> {

	/**
	 * Get all domain objects.
	 * 
	 * @return founded objects
	 */
	List<DomainClass> getAll();

	/**
	 * Change domain object in data store.
	 * 
	 * @param changedData
	 *            changed object
	 * @return changedObject
	 */
	DomainClass change(DomainClass changedData);

	/**
	 * Set new domain object to data store.
	 * 
	 * @param newData
	 *            new object
	 * @return created object
	 * @throws SQLException 
	 */
	DomainClass create(DomainClass newData) throws SQLException;

	/**
	 * Get object by id.
	 * 
	 * @param id
	 *            object id
	 * @return founded object
	 */
	DomainClass get(IdType id);

	/**
	 * Get some objects from position.
	 * 
	 * @param offset
	 *            starting position
	 * @param limit
	 *            maximum count of objects
	 * @return list of founded objects
	 */
	List<DomainClass> get(int offset, int limit);

	/**
	 * Delete object from data store.
	 * 
	 * @param obj
	 *            removing object
	 */
	void remove(DomainClass obj);
}
