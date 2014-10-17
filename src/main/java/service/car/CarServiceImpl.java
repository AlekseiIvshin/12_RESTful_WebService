package service.car;

import java.sql.SQLException;
import java.util.List;

import mapper.MainMapper;
import mapper.Mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.DomainServiceImpl;
import dao.car.mark.Mark;
import dao.car.mark.MarkDAO;
import dao.car.mark.MarkDAOImpl;
import dao.car.model.CarModel;
import dao.car.model.ModelDAO;
import dao.car.model.ModelDAOImpl;
import dao.car.modification.Modification;
import dao.car.modification.ModificationDAO;
import dao.car.modification.ModificationDAOImpl;
import domain.CarDomain;

/**
 * Car service implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class CarServiceImpl
		extends
		DomainServiceImpl<CarDomain, Long, Modification, Long, ModificationDAOImpl>
		implements CarService {

	/**
	 * Logger.
	 */
	static final Logger LOG = LoggerFactory.getLogger(CarServiceImpl.class);

	/**
	 * Default constructor.
	 */
	public CarServiceImpl() {
		super();
		dao = new ModificationDAOImpl(entityManagerFactory);
	}

	/**
	 * Add new car. Create mark and model if required.
	 * 
	 * @param mark
	 *            mark name
	 * @param model
	 *            model name
	 * @param modification
	 *            car modification
	 * @return created car
	 * @throws SQLException some exception
	 */
	public final CarDomain addCar(final String mark, final String model,
			final String modification) throws SQLException {
		MarkDAO markDAO = new MarkDAOImpl(entityManagerFactory);
		ModelDAO modelDAO = new ModelDAOImpl(entityManagerFactory);
		ModificationDAO modifDAO = new ModificationDAOImpl(entityManagerFactory);
		
		Mark markData = markDAO.findOrCreate(mark);
		CarModel modelData = modelDAO.findOrCreate(markData, model);
		Modification modif = modifDAO.findOne(modelData, modification);
		if (modif != null) {
			SQLException e = new SQLException("Modification already exist: "+mark+" "+model+" "+modification);
			LOG.error("Modification already exist",e);
			throw e;
		}
		modif = new Modification();
		modif.setModel(modelData);
		modif.setName(modification);

		modif = modifDAO.create(modif);

		Mapper mapper = new MainMapper();
		return mapper.map(modif, CarDomain.class);
	}

	/**
	 * Remove car.
	 * 
	 * @param mark
	 *            mark name
	 * @param model
	 *            model name
	 * @param modification
	 *            car modification
	 */
	public final void removeCar(final String mark, final String model,
			final String modification) throws Exception {
		// search mark by name
		MarkDAO markDAO = new MarkDAOImpl(entityManagerFactory);
		Mark markEntity = markDAO.findOne(mark);
		if (markEntity == null) {
			Exception e = new Exception("Not found mark with name "+mark);
			LOG.error("Not found mark",e);
			throw e;
		}
			

		ModelDAO modelDAO = new ModelDAOImpl(entityManagerFactory);
		CarModel modelEntity = modelDAO.findOne(markEntity, model);
		if (modelEntity == null) {
			Exception e = new Exception("Not found model of "+mark+" with name "+model);
			LOG.error("Not found model",e);
			throw e;
		}

		ModificationDAO modificationDAO = new ModificationDAOImpl(entityManagerFactory);
		Modification modifEntity = modificationDAO.findOne(modelEntity,
				modification);

		if (modifEntity == null) {
			Exception e = new Exception("Not found modification of "+mark+" "+model+" with name "+modification);
			LOG.error("Not found model",e);
			throw e;
		}

		modificationDAO.deleteById(modifEntity.getId());
	}

	/**
	 * Find car by name.
	 * 
	 * @param mark
	 *            mark name
	 * @param model
	 *            model name
	 * @param modification
	 *            modification
	 * @return founded car
	 * @throws Exception 
	 */
	public final CarDomain findOne(final String mark, final String model,
			final String modification) throws SQLException {
		MarkDAO markDAO = new MarkDAOImpl(entityManagerFactory);
		Mark markEntity = markDAO.findOne(mark);
		if (markEntity == null) {
			SQLException e = new SQLException("Not found mark with name "+mark);
			LOG.error("Not found mark",e);
			throw e;
		}

		ModelDAO modelDAO = new ModelDAOImpl(entityManagerFactory);
		CarModel modelEntity = modelDAO.findOne(markEntity, model);
		if (modelEntity == null) {
			SQLException e = new SQLException("Not found model of "+mark+" with name "+model);
			LOG.error("Not found model",e);
			throw e;
		}

		ModificationDAO modificationDAO = new ModificationDAOImpl(entityManagerFactory);
		Modification modifEntity = modificationDAO.findOne(modelEntity,
				modification);

		if (modifEntity == null) {
			SQLException e = new SQLException("Not found modification of "+mark+" "+model+" with name "+modification);
			LOG.error("Not found model",e);
			throw e;
		}

		Mapper mapper = new MainMapper();
		return mapper.map(modifEntity, CarDomain.class);
	}

	public List<CarDomain> findByMarkName(String markName) {
		List<Modification> modif = dao.findByMark(markName);
		
		Mapper mapper = new MainMapper();
		return mapper.mapAsList(modif, CarDomain.class);
	}

	public List<CarDomain> getMarks() {
		MarkDAO markDAO = new  MarkDAOImpl(entityManagerFactory);
		return mapper.mapAsList(markDAO.findMarks(),CarDomain.class);
	}

	public List<CarDomain> findByMarkAndModel(String markName, String modelName) {
		
		List<Modification> modif = dao.findByMarkAndModel(markName,modelName);
		
		Mapper mapper = new MainMapper();
		return mapper.mapAsList(modif, CarDomain.class);
	}

	@Override
	public List<CarDomain> getModels(long markId) {
		ModelDAO modelDAO = new  ModelDAOImpl(entityManagerFactory);
		return mapper.mapAsList(modelDAO.getModels(markId),CarDomain.class);
	}

	@Override
	public List<CarDomain> getModifications(long modelId) {
		ModificationDAO modifDAO = new  ModificationDAOImpl(entityManagerFactory);
		return mapper.mapAsList(modifDAO.getModifications(modelId),CarDomain.class);
	}

}
