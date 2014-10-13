package service;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mapper.MainMapper;
import mapper.Mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.GenericDAO;

/**
 * Domain service implementation.
 * 
 * @author Aleksei_Ivshin
 *
 * @param <DomainClass>
 *            domain object type
 * @param <DomainId>
 *            domain object id type
 * @param <Model>
 *            entity type
 * @param <IdType>
 *            entity id type
 * @param <DAOClass>
 *            DAO class, using in service
 */
public abstract class DomainServiceImpl<DomainClass, DomainId, Model, IdType, 
		DAOClass extends GenericDAO<Model, IdType>>
		implements DomainService<DomainClass, IdType> {
	
	protected EntityManagerFactory entityManagerFactory;
	/**
	 * DAO class.
	 */
	protected DAOClass dao;
	/**
	 * Domain object type.
	 */
	protected Class<DomainClass> domainType;
	/**
	 * Entity type.
	 */
	protected Class<Model> modelType;
	/**
	 * Mapper.
	 */
	protected Mapper mapper;

	/**
	 * Logger.
	 */
	static final Logger LOG = LoggerFactory.getLogger(DomainServiceImpl.class);

	/**
	 * Default constructor.
	 */
	@SuppressWarnings("unchecked")
	public DomainServiceImpl() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory("AutoShow");
		this.domainType = (Class<DomainClass>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		this.modelType = (Class<Model>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[3];
		mapper = new MainMapper();
	}

	/**
	 * Get all domain objects.
	 * 
	 * @return founded objects
	 */
	public final List<DomainClass> getAll() {
		List<Model> cusomers = dao.findAll();
		return mapper.mapAsList(cusomers, domainType);
	}

	/**
	 * Change domain object in data store.
	 * 
	 * @param changedData
	 *            changed object
	 * @return changedObject
	 */
	public final DomainClass change(final DomainClass changedData) {
		Model modelClass = mapper.map(changedData, modelType);
		DomainClass result = null;
		try {
			Model changedCustomer = dao.update(modelClass);
			result = mapper.map(changedCustomer, domainType);
		} catch (Exception e) {
			LOG.error("Change entity error",e);
		} 
		return result;
	}

	/**
	 * Set new domain object to data store.
	 * 
	 * @param newData
	 *            new object
	 * @return created object
	 * @throws SQLException 
	 */
	public final DomainClass create(final DomainClass newData) throws SQLException {
		Model model = mapper.map(newData, modelType);

		DomainClass result = null;
		try {
			Model persistedData = dao.create(model);
			result = mapper.map(persistedData, domainType);
		} catch (Exception e) {
			LOG.error("Create entity error",e);
		}
		return result;
	}

	/**
	 * Get object by id.
	 * 
	 * @param id
	 *            object id
	 * @return founded object
	 */
	public final DomainClass get(final IdType id) {
		Model data = dao.find(id);
		return mapper.map(data, domainType);
	}

	/**
	 * Get some objects from position.
	 * 
	 * @param offset
	 *            starting position
	 * @param limit
	 *            maximum count of objects
	 * @return list of founded objects
	 */
	public final List<DomainClass> get(final int offset, final int limit) {
		List<Model> cusomers = dao.find(offset, limit);
		return mapper.mapAsList(cusomers, domainType);
	}

	/**
	 * Delete object from data store.
	 * 
	 * @param obj
	 *            removing object
	 */
	public final void remove(final DomainClass obj) {
		Model entity = mapper.map(obj, modelType);
		try {
			dao.delete(entity);
		} catch (Exception e) {
			LOG.error("Delete",e);
		}
	}

}
