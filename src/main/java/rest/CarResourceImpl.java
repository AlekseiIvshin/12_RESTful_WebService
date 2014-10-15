package rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
import service.car.CarService;
import service.car.CarServiceImpl;
import domain.CarDomain;

public class CarResourceImpl implements CarResource {
	static final Logger logger = LoggerFactory.getLogger(CarResourceImpl.class);

	public CarService carService = new CarServiceImpl();
	MainMapper mainMapper = new MainMapper();
	ObjectMapper jsonMapper = new ObjectMapper();

	@GET
	@Path("/id/{id: [0-9]*}")
	@Produces("application/json")
	public Response getCarById(@PathParam("id") long id) {
		CarDomain foundCar = carService.get(id);
		CarElement mappedCar = mainMapper.map(foundCar, CarElement.class);

		JsonData<CarElement> jsonData = packResponse(mappedCar,
				JsonExceptionData.withError("SqlException", "Not exist"));

		return getResponse(jsonData);
	}

	@GET
	@Path("/{markName}/{modelName}/{modification}")
	@Produces("application/json")
	public Response getCarByName(@PathParam("markName") String markName,
			@PathParam("modelName") String modelName,
			@PathParam("modification") String modification) {
		CarDomain foundCar;
		try {
			foundCar = carService.findOne(markName, modelName, modification);
		} catch (SQLException e) {
			logger.error("Find car exception", e);
			return getResponse(packResponse(null,
					JsonExceptionData.withError(e)));
		}

		CarElement mappedCar = mainMapper.map(foundCar, CarElement.class);

		JsonData<CarElement> jsonData = packResponse(mappedCar,
				JsonExceptionData.withError("SqlException", "Not exist"));

		return getResponse(jsonData);
	}

	@GET
	@Path("/marks")
	@Produces("application/json")
	public Response getAllMarks() {
		List<CarDomain> marks = carService.getMarks();
		List<CarElement> mapped = mainMapper.mapAsList(marks, CarElement.class);

		JsonData<List<CarElement>> jsonData = packResponse(mapped,
				JsonExceptionData.withError("SqlException",
						"Car marks not found"));
		return getResponse(jsonData);
	}

	@GET
	@Path("/mark/{markId}/models")
	public Response getAllModels(@PathParam("markId") long markId) {
		List<CarDomain> marks = carService.getModels(markId);
		List<CarElement> mapped = mainMapper.mapAsList(marks, CarElement.class);

		JsonData<List<CarElement>> jsonData = packResponse(mapped,
				JsonExceptionData.withError("SqlException",
						"Car marks not found"));
		return getResponse(jsonData);
	}

	@GET
	@Path("/model/{modelId}/modifications")
	public Response getAllModifications(@PathParam("modelId") long modelId) {
		List<CarDomain> modifications = carService.getModifications(modelId);
		List<CarElement> mapped = mainMapper.mapAsList(modifications,
				CarElement.class);

		JsonData<List<CarElement>> jsonData = packResponse(mapped,
				JsonExceptionData.withError("SqlException",
						"Car marks not found"));
		return getResponse(jsonData);
	}

	@Override
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCar(String data) {

		CarElement carRecieved = null;
		JsonData<String> jsonData = null;
		try {
			carRecieved = jsonMapper.readValue(data, CarElement.class);
		} catch (IOException e1) {
			logger.error("Map exception", e1);
			jsonData = packResponse(null, JsonExceptionData.withError(
					"MapperException", "Can not parse data"));
			return getResponse(jsonData);
		}
		if (carRecieved != null) {
			CarDomain mapped = mainMapper.map(carRecieved, CarDomain.class);

			String id;
			JsonExceptionData exception;
			try {

				CarDomain persisted = carService.create(mapped);
				id = persisted.getId() + "";
				exception = JsonExceptionData.none();
			} catch (SQLException e1) {
				id = null;
				exception = JsonExceptionData.withError(e1);
			}

			jsonData = packResponse(id, exception);
		}
		return getResponse(jsonData);

	}

	private <T> JsonData<T> packResponse(T data, JsonExceptionData possibleError) {
		JsonData<T> jsonData;
		if (data == null) {
			jsonData = new JsonData<T>(null, possibleError);
		} else {
			jsonData = new JsonData<T>(data, JsonExceptionData.none());
		}
		return jsonData;
	}

	private Response getResponse(JsonData<?> data) {
		try {
			return Response.ok(jsonMapper.writeValueAsString(data),
					MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
