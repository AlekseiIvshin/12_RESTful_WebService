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
import javax.ws.rs.core.Response.Status;

import mapper.MainMapper;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CustomerDomain;
import rest.elements.CustomerElement;
import rest.elements.JsonData;
import rest.elements.JsonExceptionData;
import service.customer.CustomerService;
import service.customer.CustomerServiceImpl;

@Path("/customer")
public class CustomerResourceImpl implements CustomerResource {
	static final Logger logger = LoggerFactory
			.getLogger(CustomerResourceImpl.class);

	public CustomerService customerService = new CustomerServiceImpl();
	MainMapper mainMapper = new MainMapper();
	ObjectMapper jsonMapper = new ObjectMapper();

	@GET
	@Path("/id/{id: [0-9]*}")
	@Produces("application/json")
	public Response getById(@PathParam("id") int id) {
		CustomerElement customer;
		customer = mainMapper.map(customerService.get(id),
				CustomerElement.class);
		JsonData<CustomerElement> data;
		if (customer == null) {
			data = new JsonData<CustomerElement>(null,
					JsonExceptionData.withError("SqlException", "Not exist"));
		} else {
			data = new JsonData<CustomerElement>(customer,
					JsonExceptionData.none());
		}

		try {
			return Response.ok(jsonMapper.writeValueAsString(data),
					MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			logger.error("Some exception", e);
			return Response.ok(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/passport/{series: [0-9]{4}}/{number: [0-9]{6}}")
	@Produces("application/json")
	public Response getByPassport(@PathParam("series") String series,
			@PathParam("number") String number) {
		logger.debug("Request to find customer by passport {} {}", series,
				number);
		CustomerElement customer = mainMapper.map(
				customerService.findByPassport(series, number),
				CustomerElement.class);
		JsonData<CustomerElement> data;
		if (customer == null) {
			data = new JsonData<CustomerElement>(null,
					JsonExceptionData.withError("SqlException", "Not exist"));
		} else {
			data = new JsonData<CustomerElement>(customer,
					JsonExceptionData.none());
		}
		logger.debug("Data value: {}. Status: {}. Error message: {}", data
				.getData(), data.getException().getStatus(), data
				.getException().getExceptionMessage());
		try {
			return Response.ok(jsonMapper.writeValueAsString(data),
					MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewCustomerAndGetId(String customer) {
		logger.debug("Received {}", customer);
		CustomerElement customerReaded = null;
		JsonData<String> data = null;
		try {
			customerReaded = jsonMapper.readValue(customer,
					CustomerElement.class);
		} catch (IOException e1) {
			logger.error("Map exception", e1);
			data = new JsonData<String>(null, JsonExceptionData.withError(
					"MapperException", "Can not parse data"));
		}
		if (customerReaded != null) {
			logger.debug("New customer: {}", customerReaded);
			logger.debug("New customer's birth date: {}",
					customerReaded.getBirthDate());
			try {
				CustomerDomain mapped = mainMapper.map(customerReaded,
						CustomerDomain.class);
				CustomerDomain customerDomaint = customerService.create(mapped);
				data = new JsonData<String>(customerDomaint.getId() + "",
						JsonExceptionData.none());
			} catch (SQLException e) {
				data = new JsonData<String>(null, JsonExceptionData.withError(
						"SQLException", e.getMessage()));
			}
		}
		try {
			return Response.ok(jsonMapper.writeValueAsString(data),
					MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			logger.error("Map exception", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
