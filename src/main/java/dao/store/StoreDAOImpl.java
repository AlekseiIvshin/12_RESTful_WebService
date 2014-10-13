package dao.store;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import dao.GenericDAOImpl;
import dao.car.modification.Modification;

/**
 * Store DAO implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class StoreDAOImpl extends GenericDAOImpl<Store, Integer> implements
		StoreDAO {

	/**
	 * Constructor.
	 * 
	 * @param entityManager
	 *            entity manager
	 */
	public StoreDAOImpl(final EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	/**
	 * Find store by car modification.
	 * 
	 * @param modification
	 *            car modification
	 * @return founded store item
	 */
	public final Store find(final Modification modification) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Store> query = builder.createQuery(Store.class);
		Root<Store> rootStore = query.from(Store.class);
		query.where(
				builder.equal(rootStore.get(Store_.modification), modification))
				.select(rootStore);
		Store result = null;
		try {
			result = entityManager.createQuery(query).getSingleResult();
		} finally {
			entityManager.close();
		}
		return result;
	}

}
