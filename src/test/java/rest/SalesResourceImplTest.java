package rest;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;

import rest.elements.JsonData;
import rest.elements.SalesElement;

public class SalesResourceImplTest {

	SalesResourceImpl resource;
	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		resource = new SalesResourceImpl();
	}

	@Test
	public void testSaleCar() {
		int customerId = 1;
		int merchantId = 1;
		long carId = 2;

		Response res = resource.saleCar(customerId, merchantId, carId);
		String entity = (String) res.getEntity();
		JsonData<SalesElement> sale;
		try {
			sale = mapper.readValue(entity,
					new TypeReference<JsonData<SalesElement>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return;
		}

		assertNotNull(sale);
		assertNotNull(sale.getData());
		assertEquals(sale.getData().getCustomer().getId(), 1);
		assertEquals(sale.getData().getMerchant().getId(), 1);
		assertEquals(sale.getData().getCar().getId(), 1);
	}

}
