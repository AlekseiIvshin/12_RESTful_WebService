package rest;

import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mapper.MainMapper;

import domain.CustomerDomain;
import rest.elements.CustomerElement;
import rest.elements.JsonExceptionData;
import service.customer.CustomerService;
import service.customer.CustomerServiceImpl;

@Path("/customer")
public class CustomerResourceImpl extends GenericRest<CustomerService> implements CustomerResource {
	
	public CustomerResourceImpl(){
		super(new CustomerServiceImpl(), new MainMapper());
	}


	@GET
	@Path("/id/{id: [0-9]*}")
	@Produces("application/json")
	public Response getById(@PathParam("id") int id) {

		CustomerDomain customerDomain = service.get(id);
		if (customerDomain == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Not exist"));
		}
		CustomerElement customer = mainMapper.map(customerDomain,
				CustomerElement.class);
		if (customer == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Mapping error"));
		}

		return responsePacker.packOk(customer);
	}

	@GET
	@Path("/passport/{series: [0-9]{4}}/{number: [0-9]{6}}")
	@Produces("application/json")
	public Response getByPassport(@PathParam("series") String series,
			@PathParam("number") String number) {
		CustomerDomain customerDomain = service.findByPassport(series,
				number);

		if (customerDomain == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Not exist"));
		}
		CustomerElement customer = mainMapper.map(customerDomain,
				CustomerElement.class);
		if (customer == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Mapping error"));
		}

		return responsePacker.packOk(customer);
	}

	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewCustomerAndGetId(String customer) {
		CustomerElement customerReaded;
		try {
			customerReaded = requestParser.parseData(customer,
					CustomerElement.class);
		} catch (IOException e1) {
			logger.error("Map exception", e1);
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Can not parse data"));
		}

		CustomerDomain mapped = mainMapper.map(customerReaded,
				CustomerDomain.class);
		if (mapped == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Mapping error"));
		}
		CustomerDomain persisited;
		try {
			persisited = service.create(mapped);
		} catch (SQLException e) {
			return responsePacker.packError(JsonExceptionData.withError(e));
		}
		return responsePacker.packOk(String.valueOf(persisited.getId()));
	}
}
