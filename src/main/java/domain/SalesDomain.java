package domain;

import java.util.Date;

/**
 * Sale Domain implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class SalesDomain{

	/**
	 * Sale id.
	 */
	private int id;
	/**
	 * Car sold.
	 */
	private CarDomain car;
	/**
	 * Merchant, who sale car.
	 */
	private MerchantDomain merchant;
	/**
	 * Customer, who buy car.
	 */
	private CustomerDomain customer;
	/**
	 * Car price.
	 */
	private float price;
	/**
	 * Date of sale.
	 */
	private Date saleDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CarDomain getCar() {
		return car;
	}

	public void setCar(CarDomain car) {
		this.car = car;
	}

	public MerchantDomain getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantDomain merchant) {
		this.merchant = merchant;
	}

	public CustomerDomain getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDomain customer) {
		this.customer = customer;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

}
