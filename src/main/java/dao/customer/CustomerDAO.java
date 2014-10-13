package dao.customer;

import dao.GenericDAO;

/**
 * Customer DAO interface.
 * @author Aleksei_Ivshin
 *
 */
public interface CustomerDAO extends GenericDAO<Customer, Integer> {

	/**
	 * Find customer by passport data.
	 * @param customer some customer data
	 * @return founded customer
	 */
	Customer findByPassport(Customer customer);
}
