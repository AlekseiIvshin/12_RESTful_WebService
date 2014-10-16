package rest;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import rest.elements.CustomerElement;
import rest.elements.JsonData;

public class CustomerResourceImplTest {

	CustomerResourceImpl resource;
	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		resource = new CustomerResourceImpl();
	}

	@Test
	public void testGetById() {
		Response res = resource.getById(1);
		String entity = (String) res.getEntity();
		JsonData<CustomerElement> customer;
		try {
			customer = mapper.readValue(entity,
					new TypeReference<JsonData<CustomerElement>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return;
		}

		assertNotNull(customer);
		assertNotNull(customer.getData());
		assertEquals(1, customer.getData().getId());
	}

	@Test
	public void testGetByIdWithException() {
		Response res = resource.getById(-1);
		String entity = (String) res.getEntity();
		JsonData<CustomerElement> customer;
		try {
			customer = mapper.readValue(entity,
					new TypeReference<JsonData<CustomerElement>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return;
		}

		assertNotNull(customer);
		assertNull(customer.getData());
		assertTrue(customer.getException().haveError());
		assertTrue(customer.getException().getExceptionClass()
				.equalsIgnoreCase("sqlexception"));
	}

	@Test
	public void testGetByPassport() {
		Response res = resource.getByPassport("9100", "100100");
		String entity = (String) res.getEntity();
		JsonData<CustomerElement> customer;
		try {
			customer = mapper.readValue(entity,
					new TypeReference<JsonData<CustomerElement>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return;
		}

		assertNotNull(customer);
		assertNotNull(customer.getData());
		assertEquals(customer.getData().getPassportSeries(),"9100");
		assertEquals(customer.getData().getPassportNumber(),"100100");
	}
	

	@Test
	public void testGetByPassportWithException() {
		Response res = resource.getByPassport("0000", "100100");
		String entity = (String) res.getEntity();
		JsonData<CustomerElement> customer;
		try {
			customer = mapper.readValue(entity,
					new TypeReference<JsonData<CustomerElement>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return;
		}

		assertNotNull(customer);
		assertNull(customer.getData());
		assertTrue(customer.getException().haveError());
		assertTrue(customer.getException().getExceptionClass()
				.equalsIgnoreCase("sqlexception"));
	}
	

	@Ignore
	@Test
	public void testCreateNewCustomerAndGetId() {
		
	}

}
