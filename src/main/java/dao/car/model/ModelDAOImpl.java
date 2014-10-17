package dao.car.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.GenericDAOImpl;
import dao.car.mark.Mark;
import dao.car.mark.MarkDAO;
import dao.car.mark.MarkDAOImpl;

/**
 * Model DAO implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class ModelDAOImpl extends GenericDAOImpl<CarModel, Integer> implements
		ModelDAO {

	/**
	 * Logger.
	 */
	static final Logger LOG = LoggerFactory.getLogger(ModelDAOImpl.class);

	/**
	 * Constructor.
	 * 
	 * @param entityManager
	 *            entity manager
	 */
	public ModelDAOImpl(final EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	/**
	 * Find models with name like parameter.
	 * 
	 * @param mark
	 *            mark of model
	 * @param name
	 *            part or full car model name
	 * @return founded models
	 */
	public final List<CarModel> findAny(final Mark mark, final String name) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarModel> query = builder.createQuery(CarModel.class);
		Root<CarModel> root = query.from(CarModel.class);
		query.where(builder.like(root.get(CarModel_.name), name))
				.where(builder.equal(root.get(CarModel_.mark), mark))
				.select(root);
		List<CarModel> result = null;
		try {
			TypedQuery<CarModel> ctq = entityManager.createQuery(query);
			result = ctq.getResultList();
		} catch (Exception e) {
			LOG.error("Find any car model", e);
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Find model with name equal parameter.
	 * 
	 * @param mark
	 *            mark of model
	 * @param name
	 *            full model name
	 * @return founded model or null (if not found)
	 */
	public final CarModel findOne(final Mark mark, final String name) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarModel> query = builder.createQuery(CarModel.class);
		Root<CarModel> root = query.from(CarModel.class);
		query.where(
				builder.and(builder.equal(root.get(CarModel_.mark), mark),
						builder.equal(root.get(CarModel_.name), name))).select(
				root);
		CarModel result = null;
		try {
			TypedQuery<CarModel> ctq = entityManager.createQuery(query);
			result = ctq.getSingleResult();
		} catch (Exception e) {
			LOG.error("Find one model", e);
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Find car model. If not founded create this model
	 * 
	 * @param mark
	 *            car mark
	 * @param name
	 *            model name
	 * @return founded or created model
	 */
	public final CarModel findOrCreate(final Mark mark, final String name) {
		CarModel modelData = findOne(mark, name);
		if (modelData == null) {
			modelData = new CarModel();
			modelData.setMark(mark);
			modelData.setName(name);
			modelData = create(modelData);
		}
		return modelData;
	}

	@Override
	public List<CarModel> getModels(long markId) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarModel> query = builder.createQuery(CarModel.class);
		Root<CarModel> root = query.from(CarModel.class);
		MarkDAO markDao = new MarkDAOImpl(entityManagerFactory);
		Mark mark = markDao.find((int) markId);
		query.where(builder.equal(root.get(CarModel_.mark), mark)).select(root);
		List<CarModel> result = null;
		try {
			TypedQuery<CarModel> ctq = entityManager.createQuery(query);
			result = ctq.getResultList();
		} catch (Exception e) {
			LOG.error("Find one model", e);
		} finally {
			entityManager.close();
		}
		return result;
	}
}
