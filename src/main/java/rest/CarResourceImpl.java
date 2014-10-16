package rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

import rest.elements.CarElement;
import rest.elements.JsonData;
import rest.elements.JsonExceptionData;
import rest.helpers.RequestDataParser;
import rest.helpers.RequestDataParserImpl;
import rest.helpers.ResponsePacker;
import rest.helpers.ResponsePackerImpl;
import service.car.CarService;
import service.car.CarServiceImpl;
import domain.CarDomain;

@Path("/car")
public class CarResourceImpl implements CarResource {
	static final Logger logger = LoggerFactory.getLogger(CarResourceImpl.class);

	public CarService carService = new CarServiceImpl();
	MainMapper mainMapper = new MainMapper();
	ObjectMapper jsonMapper = new ObjectMapper();
	RequestDataParser requestParser = new RequestDataParserImpl();
	ResponsePacker responsePacker = new ResponsePackerImpl();

	@GET
	@Path("/id/{id: [0-9]*}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response getCarById(@PathParam("id") long id) {
		CarDomain foundCar = carService.get(id);
		CarElement mappedCar = mainMapper.map(foundCar, CarElement.class);

		JsonData<CarElement> jsonData;

		if (mappedCar == null) {
			jsonData = responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Not exist"));
		} else {
			jsonData = responsePacker.packOk(mappedCar);
		}

		return getResponseJsonOut(jsonData);
	}

	@GET
	@Path("/{markName}/{modelName}/{modification}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response getCarByName(@PathParam("markName") String markName,
			@PathParam("modelName") String modelName,
			@PathParam("modification") String modification) {
		CarDomain foundCar;
		try {
			foundCar = carService.findOne(markName, modelName, modification);
		} catch (SQLException e) {
			logger.error("Find car exception", e);
			return getResponseJsonOut(responsePacker.packError(JsonExceptionData
					.withError(e)));
		}

		CarElement mappedCar = mainMapper.map(foundCar, CarElement.class);

		JsonData<CarElement> jsonData;

		if (mappedCar == null) {
			jsonData = responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Not exist"));
		} else {
			jsonData = responsePacker.packOk(mappedCar);
		}

		return getResponseJsonOut(jsonData);
	}

	@GET
	@Path("/marks")
	@Produces("application/json")
	public Response getAllMarks() {
		List<CarDomain> marks = carService.getMarks();
		List<CarElement> mapped = mainMapper.mapAsList(marks, CarElement.class);

		JsonData<List<CarElement>> jsonData;

		if (mapped == null) {
			jsonData = responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Car marks not found"));
		} else {
			jsonData = responsePacker.packOk(mapped);
		}

		return getResponseJsonOut(jsonData);
	}

	@GET
	@Path("/mark/{markId}/models")
	public Response getAllModels(@PathParam("markId") long markId) {
		List<CarDomain> marks = carService.getModels(markId);
		List<CarElement> mapped = mainMapper.mapAsList(marks, CarElement.class);

		JsonData<List<CarElement>> jsonData;

		if (mapped == null) {
			jsonData = responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Car models not found"));
		} else {
			jsonData = responsePacker.packOk(mapped);
		}

		return getResponseJsonOut(jsonData);
	}

	@GET
	@Path("/model/{modelId}/modifications")
	public Response getAllModifications(@PathParam("modelId") long modelId) {
		List<CarDomain> modifications = carService.getModifications(modelId);
		List<CarElement> mapped = mainMapper.mapAsList(modifications,
				CarElement.class);

		JsonData<List<CarElement>> jsonData;

		if (mapped == null) {
			jsonData = responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Car modifications not found"));
		} else {
			jsonData = responsePacker.packOk(mapped);
		}

		return getResponseJsonOut(jsonData);
	}

	@Override
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCar(String data) {

		CarElement carRecieved = null;
		JsonData<String> jsonData = null;
		try {
			carRecieved = requestParser.parseData(data, CarElement.class);
		} catch (IOException e1) {
			logger.error("Map exception", e1);
			jsonData = responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Can not parse data"));
			return getResponseJsonOut(jsonData);
		}
		CarDomain mapped = mainMapper.map(carRecieved, CarDomain.class);
		if (mapped != null) {
			String id;
			JsonExceptionData exception;
			try {

				CarDomain persisted = carService.addCar(mapped.getMark(),
						mapped.getModel(), mapped.getModification());
				id = persisted.getId() + "";
				jsonData = responsePacker.packOk(id);
			} catch (SQLException e1) {
				exception = JsonExceptionData.withError(e1);
				jsonData = responsePacker.packError(exception);
			}
		}
		return getResponseJsonOut(jsonData);

	}

	private Response getResponseJsonOut(JsonData<?> data) {
		try {
			return Response.ok(jsonMapper.writeValueAsString(data),
					MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}
}
