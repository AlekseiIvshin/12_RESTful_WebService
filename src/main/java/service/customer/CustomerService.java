package service.customer;

import service.DomainService;
import domain.CustomerDomain;

/**
 * Customer service interface.
 * 
 * @author Aleksei_Ivshin
 *
 */
public interface CustomerService extends DomainService<CustomerDomain, Integer> {

	/**
	 * Find customer by passport.
	 * 
	 * @param series
	 *            customer passport series
	 * 
	 * @param number
	 *            customer passport series
	 * @return founded customer
	 */
	CustomerDomain findByPassport(String series, String number);
}
