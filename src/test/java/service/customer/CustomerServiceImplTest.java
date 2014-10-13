package service.customer;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.CustomerDomain;

public class CustomerServiceImplTest {

	@Test
	public void testFindByPassportStringString() {
		CustomerServiceImpl serviceImpl = new CustomerServiceImpl();
		CustomerDomain customer = serviceImpl.findByPassport("9100", "100100");
		assertNotNull(customer);
	}

}
