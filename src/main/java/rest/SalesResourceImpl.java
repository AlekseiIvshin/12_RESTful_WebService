package rest;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import domain.SalesDomain;
import mapper.MainMapper;
import rest.elements.CustomerElement;
import rest.elements.JsonExceptionData;
import rest.elements.SalesElement;
import service.customer.CustomerService;
import service.sales.SalesService;
import service.sales.SalesServiceImpl;

@Path("/sales")
public class SalesResourceImpl extends GenericRest<SalesService> implements
		SalesResource {

	public SalesResourceImpl() {
		super(new SalesServiceImpl(), new MainMapper());
	}

	@GET
	@Path("/new")
	@Consumes("text/plain")
	@Produces("application/json")
	@Override
	public Response saleCar(@QueryParam("customer") int customerId,
			@QueryParam("merchant") int merchantId,
			@QueryParam("car") long carId) {
		SalesDomain sales = service.newSaleAndUpdateStore(customerId, merchantId, carId);
		
		if(sales == null){
			return responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Create sales error"));
		}
		SalesElement salesElement = mainMapper.map(sales,
				SalesElement.class);
		if (salesElement == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Mapping error"));
		}

		return responsePacker.packOk(salesElement);
	}

}
