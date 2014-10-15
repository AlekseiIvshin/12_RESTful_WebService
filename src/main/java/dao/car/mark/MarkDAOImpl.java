package dao.car.mark;

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

/**
 * Mark DAO implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class MarkDAOImpl extends GenericDAOImpl<Mark, Integer> implements
		MarkDAO {

	/**
	 * Logger.
	 */
	static final Logger LOG = LoggerFactory.getLogger(MarkDAOImpl.class);

	/**
	 * Constructor.
	 * 
	 * @param entityManager
	 *            entity manager
	 */
	public MarkDAOImpl(final EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	/**
	 * Find marks with name like parameter.
	 * 
	 * @param name
	 *            part or full car mark name
	 * @return founded marks
	 */
	public final List<Mark> findAny(final String name) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Mark> query = builder.createQuery(Mark.class);
		Root<Mark> root = query.from(Mark.class);
		query.where(builder.like(root.get(Mark_.name), name)).select(root);
		List<Mark> result = null;
		try {
			TypedQuery<Mark> ctq = entityManager.createQuery(query);
			result = ctq.getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Find marks with name equal parameter.
	 * 
	 * @param name
	 *            full mark name
	 * @return founded mark or null (if not found)
	 */
	public final Mark findOne(final String name) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Mark> query = builder.createQuery(Mark.class);
		Root<Mark> root = query.from(Mark.class);
		query.where(builder.equal(root.get(Mark_.name), name));

		Mark result = null;
		try {
			TypedQuery<Mark> ctq = entityManager.createQuery(query);
			result = ctq.getSingleResult();
		} catch (Exception e) {
			LOG.error("Find any mark", e);
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Find car mark. If not found create new mark with this name
	 * 
	 * @param name
	 *            mark name
	 * @return founded or created mark
	 */
	public final Mark findOrCreate(final String name) {
		Mark markData = findOne(name);
		if (markData == null) {
			markData = new Mark(name);
			markData = create(markData);
		}
		return markData;
	}

	@Override
	public List<String> findAllNames() {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> query = builder.createQuery(String.class);
		Root<Mark> root = query.from(Mark.class);
		query.multiselect(root.get(Mark_.name));
		List<String> result = null;
		try {
			result = entityManager.createQuery(query).getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}

	@Override
	public List<Mark> findMarks() {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Mark> query = builder.createQuery(Mark.class);
		Root<Mark> root = query.from(Mark.class);
		query.select(root);
		List<Mark> result = null;
		try {
			result = entityManager.createQuery(query).getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}

}
