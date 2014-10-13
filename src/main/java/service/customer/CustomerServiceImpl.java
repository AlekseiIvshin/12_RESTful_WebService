package service.customer;

import mapper.MainMapper;
import mapper.Mapper;
import service.DomainServiceImpl;
import dao.customer.Customer;
import dao.customer.CustomerDAOImpl;
import domain.CustomerDomain;

/**
 * Customer service implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class CustomerServiceImpl
		extends
		DomainServiceImpl<CustomerDomain, Integer, Customer, Integer, CustomerDAOImpl>
		implements CustomerService {

	/**
	 * Default constructor.
	 */
	public CustomerServiceImpl() {
		super();
		dao = new CustomerDAOImpl(entityManagerFactory);
	}

	/**
	 * Find customer by passport.
	 * 
	 * @param customer
	 *            some customer data
	 * @return founded customer
	 */
	public final CustomerDomain findByPassport(final CustomerDomain customer) {
		Mapper mapper = new MainMapper();
		Customer customerDao = mapper.map(customer, Customer.class);
		return mapper
				.map(dao.findByPassport(customerDao), CustomerDomain.class);
	}

	public CustomerDomain findByPassport(String series, String number) {
		return mapper
				.map(dao.findByPassport(series,number), CustomerDomain.class);
	}

}
