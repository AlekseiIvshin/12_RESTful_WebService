package mapper;

import java.util.List;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rest.elements.CarElement;
import rest.elements.CustomerElement;
import rest.elements.MerchantElement;
import rest.elements.SalesElement;
import rest.elements.StoreElement;
import dao.car.mark.Mark;
import dao.car.model.CarModel;
import dao.car.modification.Modification;
import dao.customer.Customer;
import dao.merchant.Merchant;
import dao.sales.Sales;
import dao.store.Store;
import domain.CarDomain;
import domain.CustomerDomain;
import domain.MerchantDomain;
import domain.SalesDomain;
import domain.StoreDomain;

/**
 * Main mapper for application.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class MainMapper implements Mapper {

	/**
	 * Logger.
	 */
	static final Logger LOG = LoggerFactory.getLogger(MainMapper.class);

	/**
	 * Mapper factory.
	 */
	private MapperFactory mapperFactory;

	/**
	 * Default constructor.
	 */
	public MainMapper() {
		mapperFactory = new DefaultMapperFactory.Builder().build();
		initMap();
	}

	/**
	 * Initialize mapping.
	 */
	private void initMap() {
		// Customer Domain to Customer Entity
		mapperFactory.classMap(CustomerDomain.class, Customer.class)
				.constructorA("id").field("name", "name")
				.field("surname", "surname").field("patronymic", "patronymic")
				.field("passportSeries", "passportSeries")
				.field("passportNumber", "passportNumber")
				.field("birthDate", "birthDate").field("id", "id").register();

		// Merchant domain to Merchant entity
		mapperFactory.classMap(MerchantDomain.class, Merchant.class)
				.constructorA("id").field("name", "name")
				.field("surname", "surname").field("patronymic", "patronymic")
				.field("id", "id").register();

		// Car domain to Car Modification entity
		mapperFactory.classMap(CarDomain.class, Modification.class)
				.constructorA("id").field("id", "id")
				.field("mark", "model.mark.name").field("model", "model.name")
				.field("modification", "name").register();
		

		// Car domain to Car Model entity
		mapperFactory.classMap(CarDomain.class, CarModel.class)
				.constructorA("id").field("id", "id")
				.field("mark", "mark.name").field("model", "name").register();
		

		// Car domain to Car Mark entity
		mapperFactory.classMap(CarDomain.class, Mark.class)
				.constructorA("id").field("id", "id")
				.field("mark", "name").register();

		// Store domain to Store entity
		mapperFactory.classMap(StoreDomain.class, Store.class)
				.field("id", "id").field("quantity", "count")
				.field("price", "price")
				.field("enableTestDrive", "testDriveAvaible")
				.field("car", "modification").register();

		// Sales domain to sales entity
		mapperFactory.classMap(SalesDomain.class, Sales.class)
				.field("id", "id").field("customer", "customer")
				.field("merchant", "merchant").field("saleDate", "saleDate")
				.field("price", "price").field("car", "modification")
				.register();

		// Customer Domain to Customer Element
		mapperFactory.classMap(CustomerDomain.class, CustomerElement.class)
				.byDefault().register();

		// Merchant domain to Merchant Element
		mapperFactory.classMap(MerchantDomain.class, MerchantElement.class)
				.byDefault().register();

		// Car domain to Car Modification Element
		mapperFactory.classMap(CarDomain.class, CarElement.class).byDefault()
				.register();

		// Store domain to Store Element
		mapperFactory.classMap(StoreDomain.class, StoreElement.class)
				.byDefault().register();

		// Sales domain to sales Element
		mapperFactory.classMap(SalesDomain.class, SalesElement.class)
				.byDefault().register();

	}

	/**
	 * Map object.
	 * 
	 * @param <FromClass>
	 *            object class
	 * @param <ToClass>
	 *            target class
	 * @param object
	 *            object, that will be mapped
	 * @param toClass
	 *            target class for mapping
	 * @return mapped object
	 */
	public final <FromClass, ToClass> ToClass map(final FromClass object,
			final Class<ToClass> toClass) {
		if(object == null){
			return null;
		}
		MapperFacade mapperFacade = mapperFactory.getMapperFacade();
		return mapperFacade.map(object, toClass);
	}

	/**
	 * Map objects list.
	 * 
	 * @param <FromClass>
	 *            object class
	 * @param <ToClass>
	 *            target class
	 * @param object
	 *            object, that will be mapped
	 * @param toClass
	 *            target class for mapping
	 * @return list of mapped objects
	 */
	public final <FromClass, ToClass> List<ToClass> mapAsList(
			final List<FromClass> object, final Class<ToClass> toClass) {
		MapperFacade mapperFacade = mapperFactory.getMapperFacade();
		return mapperFacade.mapAsList(object, toClass);
	}
}
