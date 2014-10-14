package dao.customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.GenericDAOImpl;

/**
 * Customer DAO implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class CustomerDAOImpl extends GenericDAOImpl<Customer, Integer>
		implements CustomerDAO {

	/**
	 * Logger.
	 */
	static final Logger LOG = LoggerFactory.getLogger(CustomerDAOImpl.class);

	/**
	 * Constructor.
	 * 
	 * @param entityManager
	 */
	public CustomerDAOImpl(final EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	/**
	 * Find customer by passport data.
	 * 
	 * @param customer
	 *            some customer data
	 * @return founded customer
	 */
	public final Customer findByPassport(final Customer customer) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
		Root<Customer> resultCustomer = query.from(Customer.class);
		query.where(
				builder.equal(resultCustomer.get(Customer_.passportSeries),
						customer.getPassportSeries()))
				.where(builder.equal(
						resultCustomer.get(Customer_.passportNumber),
						customer.getPassportNumber())).select(resultCustomer);
		Customer result = null;
		try {
			TypedQuery<Customer> ctq = entityManager.createQuery(query);
			result = ctq.getSingleResult();
		} finally {
			entityManager.close();
		}
		return result;
	}

	public final Customer findByPassport(final String series,
			final String number) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
		Root<Customer> resultCustomer = query.from(Customer.class);
		query.where(
				builder.and(builder.equal(
						resultCustomer.get(Customer_.passportSeries), series),
						builder.equal(
								resultCustomer.get(Customer_.passportNumber),
								number))).select(resultCustomer);
		Customer result = null;
		try {
			TypedQuery<Customer> ctq = entityManager.createQuery(query);
			result = ctq.getSingleResult();
		}catch(NoResultException e){
			return null;
		} finally {
			entityManager.close();
		}
		return result;
	}

}
