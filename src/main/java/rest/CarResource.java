package rest;

import javax.ws.rs.core.Response;

/**
 * Car REST service.
 * 
 * @author Aleksei_Ivshin
 *
 */
public interface CarResource {

	Response getCarById(long id);

	Response getCarByName(String markName, String modelName, String modification);

	Response getAllMarks();

	Response getAllModels(long markId);

	Response getAllModifications(long modelId);
	
	Response createCar(String data);
}
