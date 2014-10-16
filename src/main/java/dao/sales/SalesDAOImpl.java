package dao.sales;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.GenericDAOImpl;
import dao.car.modification.Modification;
import dao.customer.Customer;
import dao.merchant.Merchant;
import dao.store.Store;
import dao.store.StoreDAOImpl;

/**
 * Sales DAO implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class SalesDAOImpl extends GenericDAOImpl<Sales, Integer> implements
		SalesDAO {

	static final Logger logger = LoggerFactory.getLogger(SalesDAOImpl.class);

	/**
	 * Constructor.
	 * 
	 * @param entityManager
	 */
	public SalesDAOImpl(final EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	/**
	 * Find sales by modification.
	 * 
	 * @param modification
	 *            modification
	 * @return list of sales
	 */
	@SuppressWarnings("unchecked")
	public final List<Sales> find(final Modification modification) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		List<Sales> result = null;
		try {
			result = entityManager
					.createQuery(
							"SELECT s FROM Sales s "
									+ "WHERE s.modification=:modif")
					.setParameter("modif", modification).getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Find sales by customer.
	 * 
	 * @param customer
	 *            customer
	 * @return list of sales
	 */
	@SuppressWarnings("unchecked")
	public final List<Sales> find(final Customer customer) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		List<Sales> result = null;
		try {
			result = entityManager
					.createQuery(
							"SELECT s FROM Sales s "
									+ "WHERE s.customer=:customer")
					.setParameter("customer", customer).getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Find sales by merchant.
	 * 
	 * @param merchant
	 *            merchant
	 * @return list of sales
	 */
	@SuppressWarnings("unchecked")
	public final List<Sales> find(final Merchant merchant) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		List<Sales> result = null;
		try {
			result = entityManager
					.createQuery(
							"SELECT s FROM Sales s "
									+ "WHERE s.merchant=:merchant")
					.setParameter("merchant", merchant).getResultList();
		} finally {
			entityManager.close();
		}
		return result;
	}

	/**
	 * Create new sale and update store: decrement count of cars in store item.
	 * 
	 * @param customer
	 *            customer, who buy car
	 * @param merchant
	 *            merchant, who sale car
	 * @param modification
	 *            car that is sale
	 * @return created sales
	 */
	public final Sales newSaleAndUpdateStore(final Customer customer,
			final Merchant merchant, final Modification modification) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		entityManager.getTransaction().begin();
		Sales result = null;
		try {
			Store store = new StoreDAOImpl(entityManagerFactory)
					.find(modification);
			if (store == null || store.getCount() == 0) {
				throw new NoResultException("Car " + modification.toString()
						+ " is not exist in store.");
			}
			Sales sales = new Sales();
			sales.setPrice(store.getPrice());
			sales.setCustomer(customer);
			sales.setMerchant(merchant);
			sales.setModification(modification);
			entityManager.persist(sales);
			store.setCount(store.getCount() - 1);
			entityManager.merge(store);
			entityManager.getTransaction().commit();
			result = sales;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			logger.error("Store can't update", e);
		} finally {
			entityManager.close();
		}
		return result;
	}
}
