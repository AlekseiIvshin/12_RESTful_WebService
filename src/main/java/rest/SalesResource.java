package rest;

import javax.ws.rs.core.Response;

public interface SalesResource {

	Response saleCar(int customerId, int merchantId, long carId);
}
