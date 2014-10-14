package rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;

import service.customer.CustomerService;
import service.customer.CustomerServiceImpl;

@Path("customer")
public class CustomerResource {

	public CustomerService customer = new CustomerServiceImpl();

	@GET
	@Path("/{id: [0-9]*}")
	@Produces("text/plain")
	public Response getById(@PathParam("id") int id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return Response.ok(mapper.writeValueAsString(customer.get(id)),
					MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/passport/{series: [0-9]{4}}/{number: [0-9]{6}}")
	@Produces("text/plain")
	public Response getByPassport(@PathParam("series") String series,
			@PathParam("number") String number) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return Response.ok(mapper.writeValueAsString(customer.findByPassport(series, number)),
					MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
