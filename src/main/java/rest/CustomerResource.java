package rest;

import javax.ws.rs.core.Response;

import rest.elements.CustomerElement;

public interface CustomerResource {

	public Response getById(int id);

	public Response getByPassport(String series, String number);
	
	public Response createNewCustomerAndGetId(String customer);
}
