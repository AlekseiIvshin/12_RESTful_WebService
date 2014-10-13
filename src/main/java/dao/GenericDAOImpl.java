package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic DAO implementation.
 * 
 * @author Aleksei_Ivshin
 *
 * @param <Model>
 *            entity type
 * @param <IdType>
 *            entity id
 */
public class GenericDAOImpl<Model, IdType> implements GenericDAO<Model, IdType> {

	/**
	 * Entity manager.
	 */
	protected EntityManagerFactory entityManagerFactory;

	/**
	 * Type of entity.
	 */
	protected Class<Model> entityType;

	/**
	 * Logger.
	 */
	static final Logger LOG = LoggerFactory.getLogger(GenericDAOImpl.class);

	/**
	 * Add entity manager to DAO class.
	 * 
	 * @param entityManager
	 *            entity manager
	 */
	@SuppressWarnings("unchecked")
	public GenericDAOImpl(final EntityManagerFactory entityManagerFactory) {
		this.entityType = (Class<Model>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		this.entityManagerFactory = entityManagerFactory;
	}

	/**
	 * Create new entity in data store.
	 * 
	 * @param entity
	 *            new entity
	 * @return persisted entity
	 */
	public final Model create(final Model entity) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("Create entity error",e);
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		return entity;
	}

	/**
	 * Update entity in data store.
	 * 
	 * @param entity
	 *            changed entity
	 * @return merged entity
	 */
	public final Model update(final Model entity) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Model result = null;
		try {
			entityManager.getTransaction().begin();
			result = entityManager.merge(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("Update entity error",e);
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Find entity in data store by id.
	 * 
	 * @param id
	 *            entity id
	 * @return founded entity
	 */
	public final Model find(final IdType id) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Model result = null;
		try {
			result = entityManager.find(entityType, id);
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Find all entities (not recommend).
	 * 
	 * @return list of entities
	 */
	@SuppressWarnings("unchecked")
	public final List<Model> findAll() {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		List<Model> result = null;
		try {
			result = entityManager.createQuery(
					"SELECT entity FROM " + entityType.getName() + " entity")
					.getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Delete entity from data store.
	 * 
	 * @param id
	 *            id of entity
	 */
	public final void deleteById(final IdType id) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Model m = entityManager.find(entityType, id);
			entityManager.remove(m);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("Delete entity error",e);
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	/**
	 * Find entities with paging.
	 * 
	 * @param offset
	 *            position, where start get entities
	 * @param limit
	 *            maximum count of entities in result
	 * @return list of entities
	 */
	@SuppressWarnings("unchecked")
	public final List<Model> find(final int offset, final int limit) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		List<Model> result = null;
		try {
			result = entityManager
					.createQuery(
							"SELECT entity FROM " + entityType.getName()
									+ " entity").setFirstResult(offset)
					.setMaxResults(limit).getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Delete entity from data store.
	 * 
	 * @param entity
	 *            entity
	 */
	public final void delete(final Model entity) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("Delete entity error",e);
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

}
