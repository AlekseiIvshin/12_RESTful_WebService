package service.store;

import service.DomainServiceImpl;
import mapper.MainMapper;
import mapper.Mapper;
import dao.car.modification.Modification;
import dao.store.Store;
import dao.store.StoreDAOImpl;
import domain.CarDomain;
import domain.StoreDomain;

/**
 * Store service implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class StoreServiceImpl extends
		DomainServiceImpl<StoreDomain, Integer, Store, Integer, StoreDAOImpl>
		implements StoreService {

	/**
	 * Constructor.
	 */
	public StoreServiceImpl() {
		super();
		dao = new StoreDAOImpl(entityManagerFactory);
	}

	/**
	 * Get store by car.
	 * 
	 * @param car
	 *            car
	 * @return founded store
	 */
	public final StoreDomain get(final CarDomain car) {
		Mapper mapper = new MainMapper();
		Modification modif = mapper.map(car, Modification.class);
		Store store = dao.find(modif);
		return mapper.map(store, StoreDomain.class);
	}

}
