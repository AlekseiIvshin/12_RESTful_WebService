package dao.car.modification;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.GenericDAOImpl;
import dao.car.mark.Mark;
import dao.car.mark.Mark_;
import dao.car.model.CarModel;
import dao.car.model.CarModel_;
import dao.car.model.ModelDAO;
import dao.car.model.ModelDAOImpl;

/**
 * Modification DAO implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class ModificationDAOImpl extends GenericDAOImpl<Modification, Long>
		implements ModificationDAO {
	static final Logger LOG = LoggerFactory
			.getLogger(ModificationDAOImpl.class);

	/**
	 * Constructor.
	 * 
	 * @param entityManager
	 *            entity manager
	 */
	public ModificationDAOImpl(final EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	/**
	 * Find modification with name like parameter.
	 * 
	 * @param model
	 *            model of modification
	 * @param name
	 *            part or full car modification name
	 * @return founded modifications
	 */
	public final List<Modification> findAny(final CarModel model,
			final String name) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Modification> query = builder
				.createQuery(Modification.class);
		Root<Modification> root = query.from(Modification.class);
		query.where(builder.like(root.get(Modification_.name), name))
				.where(builder.equal(root.get(Modification_.model), model))
				.select(root);
		List<Modification> result = null;
		try {
			TypedQuery<Modification> ctq = entityManager.createQuery(query);
			result = ctq.getResultList();
		} catch (Exception e) {
			LOG.error("Find any modification", e);
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Find modification with name equal parameter.
	 * 
	 * @param model
	 *            model of modification
	 * @param name
	 *            full modification name
	 * @return founded modification or null (if not found)
	 */
	public final Modification findOne(final CarModel model, final String name) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Modification> query = builder
				.createQuery(Modification.class);
		Root<Modification> root = query.from(Modification.class);
		query.where(
				builder.and(
						builder.equal(root.get(Modification_.model), model),
						builder.equal(root.get(Modification_.name), name)))
				.select(root);
		Modification result = null;
		try {
			TypedQuery<Modification> ctq = entityManager.createQuery(query);
			result = ctq.getSingleResult();
		} catch (Exception e) {
			LOG.error("Find one modification", e);
		} finally {
			entityManager.close();
		}
		return result;
	}

	@Override
	public List<Modification> findByMark(String markName) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Modification> query = builder
				.createQuery(Modification.class);

		Root<Modification> root = query.from(Modification.class);
		Join<Modification, CarModel> models = root.join(Modification_.model);
		Join<CarModel, Mark> marks = models.join(CarModel_.mark);
		query.where(builder.equal(marks.get(Mark_.name), markName))
				.select(root);
		List<Modification> result = null;
		try {
			result = entityManager.createQuery(query).getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}

	public List<Modification> findByMarkAndModel(String markName,
			String modelName) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Modification> query = builder
				.createQuery(Modification.class);

		Root<Modification> root = query.from(Modification.class);
		Join<Modification, CarModel> models = root.join(Modification_.model);
		Join<CarModel, Mark> marks = models.join(CarModel_.mark);
		query.where(
				builder.and(builder.equal(marks.get(Mark_.name), markName),
						builder.equal(models.get(CarModel_.name), modelName)))
				.select(root);

		List<Modification> result = null;
		try {
			result = entityManager.createQuery(query).getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}

	@Override
	public List<Modification> getModifications(long modelId) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		ModelDAO modelDao = new ModelDAOImpl(entityManagerFactory);
		CarModel model = modelDao.find((int) modelId);

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Modification> query = builder
				.createQuery(Modification.class);

		Root<Modification> root = query.from(Modification.class);
		query.where(builder.equal(root.get(Modification_.model), model))
				.select(root);

		List<Modification> result = null;
		try {
			result = entityManager.createQuery(query).getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}
}
