package dao;

import java.util.List;

/**
 * Generic DAO interface.
 * 
 * @author Aleksei_Ivshin
 *
 * @param <Model>
 *            entity type
 * @param <IdType>
 *            entity id
 */
public interface GenericDAO<Model, IdType> {

	/**
	 * Create new entity in data store.
	 * 
	 * @param entity
	 *            new entity
	 * @return persisted entity
	 */
	Model create(Model entity);

	/**
	 * Update entity in data store.
	 * 
	 * @param entity
	 *            changed entity
	 * @return merged entity
	 */
	Model update(Model entity);

	/**
	 * Find entity in data store by id.
	 * 
	 * @param id
	 *            entity id
	 * @return founded entity
	 */
	Model find(IdType id);

	/**
	 * Find all entities (not recommend).
	 * 
	 * @return list of entities
	 */
	List<Model> findAll();

	/**
	 * Delete entity from data store.
	 * 
	 * @param id
	 *            id of entity
	 */
	void deleteById(IdType id);
	

	/**
	 * Delete entity from data store.
	 * 
	 * @param entity
	 *            entity
	 */
	void delete(Model entity);

	/**
	 * Find entities with paging.
	 * 
	 * @param offset
	 *            position, where start get entities
	 * @param limit
	 *            maximum count of entities in result
	 * @return list of entities
	 */
	List<Model> find(int offset, int limit);
}
