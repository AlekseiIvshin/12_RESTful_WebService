package service.store;

import service.DomainService;
import domain.CarDomain;
import domain.StoreDomain;

/**
 * Store service interface.
 * 
 * @author Aleksei_Ivshin
 *
 */
public interface StoreService extends DomainService<StoreDomain, Integer> {

	/**
	 * Get store by car.
	 * 
	 * @param car
	 *            car
	 * @return founded store
	 */
	StoreDomain get(CarDomain car);
}
