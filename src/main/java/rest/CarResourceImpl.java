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
import mapper.MainMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rest.elements.CarElement;
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

	private final CarService carService = new CarServiceImpl();
	private final MainMapper mainMapper = new MainMapper();
	private final RequestDataParser requestParser = new RequestDataParserImpl();
	private final ResponsePacker responsePacker = new ResponsePackerImpl();

	@GET
	@Path("/id/{id: [0-9]*}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response getCarById(@PathParam("id") long id) {
		CarDomain foundCar = carService.get(id);

		if (foundCar == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Not exist"));
		}

		CarElement mappedCar = mainMapper.map(foundCar, CarElement.class);

		if (mappedCar == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Mapping error"));
		}

		return responsePacker.packOk(mappedCar);
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
			return responsePacker.packError(JsonExceptionData.withError(e));
		}

		CarElement mappedCar = mainMapper.map(foundCar, CarElement.class);

		if (mappedCar == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Not exist"));
		}
		return responsePacker.packOk(mappedCar);
	}

	@GET
	@Path("/marks")
	@Produces("application/json")
	public Response getAllMarks() {
		List<CarDomain> marks = carService.getMarks();

		if (marks == null || marks.isEmpty()) {
			return responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Car marks not found"));
		}

		List<CarElement> mapped = mainMapper.mapAsList(marks, CarElement.class);

		if (mapped == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Mapping error"));
		}

		return responsePacker.packOk(mapped);
	}

	@GET
	@Path("/mark/{markId}/models")
	public Response getAllModels(@PathParam("markId") long markId) {
		List<CarDomain> cars = carService.getModels(markId);

		if (cars == null || cars.isEmpty()) {
			return responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Car marks not found"));
		}

		List<CarElement> mapped = mainMapper.mapAsList(cars, CarElement.class);

		if (mapped == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Mapping error"));
		}

		return responsePacker.packOk(mapped);
	}

	@GET
	@Path("/model/{modelId}/modifications")
	public Response getAllModifications(@PathParam("modelId") long modelId) {
		List<CarDomain> cars = carService.getModifications(modelId);
		if (cars == null || cars.isEmpty()) {
			return responsePacker.packError(JsonExceptionData.withError(
					"SqlException", "Car marks not found"));
		}

		List<CarElement> mapped = mainMapper.mapAsList(cars, CarElement.class);

		if (mapped == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Mapping error"));
		}

		return responsePacker.packOk(mapped);
	}

	@Override
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCar(String data) {

		CarElement carRecieved = null;
		try {
			carRecieved = requestParser.parseData(data, CarElement.class);
		} catch (IOException e1) {
			logger.error("Map exception", e1);
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Can not parse data"));
		}

		CarDomain mapped = mainMapper.map(carRecieved, CarDomain.class);

		if (mapped == null) {
			return responsePacker.packError(JsonExceptionData.withError(
					"MapperException", "Mapping error"));
		}
		CarDomain persisted;
		try {

			persisted = carService.addCar(mapped.getMark(), mapped.getModel(),
					mapped.getModification());
		} catch (SQLException e1) {
			return responsePacker.packError(JsonExceptionData.withError(e1));
		}

		return responsePacker.packOk(String.valueOf(persisted.getId()));

	}
}
