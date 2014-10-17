package service.sales;

import mapper.MainMapper;
import mapper.Mapper;
import service.DomainServiceImpl;
import dao.car.modification.Modification;
import dao.car.modification.ModificationDAO;
import dao.car.modification.ModificationDAOImpl;
import dao.customer.Customer;
import dao.customer.CustomerDAO;
import dao.customer.CustomerDAOImpl;
import dao.merchant.Merchant;
import dao.merchant.MerchantDAO;
import dao.merchant.MerchantDAOImpl;
import dao.sales.Sales;
import dao.sales.SalesDAOImpl;
import domain.CarDomain;
import domain.CustomerDomain;
import domain.MerchantDomain;
import domain.SalesDomain;

/**
 * Sales service implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class SalesServiceImpl extends
		DomainServiceImpl<SalesDomain, Integer, Sales, Integer, SalesDAOImpl>
		implements SalesService {

	/**
	 * Default constructor.
	 */
	public SalesServiceImpl() {
		super();
		dao = new SalesDAOImpl(entityManagerFactory);
	}

	/**
	 * Create new sale and update store (decrement car count).
	 * 
	 * @param newSales
	 *            new sale data
	 * @return sale data
	 * @throws Exception
	 *             some error
	 */
	public final SalesDomain newSaleAndUpdateStore(final SalesDomain newSales)
			throws Exception {
		return newSaleAndUpdateStore(newSales.getCustomer(),
				newSales.getMerchant(), newSales.getCar());
	}

	/**
	 * Create new sale and update store (decrement car count).
	 * 
	 * @param customer
	 *            who buy car
	 * @param merchant
	 *            who sale car
	 * @param car
	 *            car
	 * @return sale date
	 * @throws Exception
	 *             some error
	 */
	public final SalesDomain newSaleAndUpdateStore(
			final CustomerDomain customer, final MerchantDomain merchant,
			final CarDomain car) throws Exception {
		Mapper mapper = new MainMapper();
		Customer cust = mapper.map(customer, Customer.class);
		Merchant merch = mapper.map(merchant, Merchant.class);
		Modification modif = mapper.map(car, Modification.class);

		CustomerDAO customerDAO = new CustomerDAOImpl(entityManagerFactory);

		Customer persisted = customerDAO.findByPassport(cust);
		if(persisted==null){
			persisted = customerDAO.create(cust);
		}
		
		Sales changedSales = dao.newSaleAndUpdateStore(persisted, merch, modif);
		return mapper.map(changedSales, SalesDomain.class);
	}

	@Override
	public SalesDomain newSaleAndUpdateStore(int customerId, int merchantId,
			long carId) {
		Mapper mapper = new MainMapper();
		
		MerchantDAO merchantDAO = new MerchantDAOImpl(entityManagerFactory);
		CustomerDAO customerDAO = new CustomerDAOImpl(entityManagerFactory);
		ModificationDAO modificationDAO = new ModificationDAOImpl(entityManagerFactory);
		
		
		
		Customer cust = customerDAO.find(customerId);
		Merchant merch = merchantDAO.find(merchantId);
		Modification mod = modificationDAO.find(carId);
		
		Sales changedSales = dao.newSaleAndUpdateStore(cust, merch, mod);
		return mapper.map(changedSales, SalesDomain.class);
	}

}
