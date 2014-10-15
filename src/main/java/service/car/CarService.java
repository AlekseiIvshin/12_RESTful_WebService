package service.car;

import java.sql.SQLException;
import java.util.List;

import service.DomainService;
import domain.CarDomain;

/**
 * Car service interface.
 * 
 * @author AlekseiIvshin
 *
 */
public interface CarService extends DomainService<CarDomain, Long> {

	/**
	 * Add new car. Create mark and model if required.
	 * @param mark mark name
	 * @param model model name
	 * @param modification car modification
	 * @return created car
	 * @throws SQLException 
	 * @throws Exception 
	 */
	CarDomain addCar(String mark, String model, String modification) throws  SQLException;

	/**
	 * Remove car.
	 * @param mark mark name
	 * @param model model name
	 * @param modification car modification
	 * @throws Exception 
	 */
	void removeCar(String mark, String model, String modification) throws Exception;

	/**
	 * Find car by name.
	 * @param mark mark name
	 * @param model model name
	 * @param modification modification
	 * @return founded car
	 * @throws Exception 
	 */
	CarDomain findOne(String mark, String model, String modification) throws SQLException;

	List<CarDomain> getMarks();
	
	List<CarDomain> findByMarkAndModel(String markName, String modelName);

	List<CarDomain> getModels(long markId);

	List<CarDomain> getModifications(long modelId);
}
