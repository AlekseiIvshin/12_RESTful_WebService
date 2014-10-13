package restservice.elements;

import java.util.Date;

/**
 * Sale Domain implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class SalesElement{

	/**
	 * Sale id.
	 */
	private int id;
	/**
	 * Car sold.
	 */
	private CarElement car;
	/**
	 * Merchant, who sale car.
	 */
	private MerchantElement merchant;
	/**
	 * Customer, who buy car.
	 */
	private CustomerElement customer;
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

	public CarElement getCar() {
		return car;
	}

	public void setCar(CarElement car) {
		this.car = car;
	}

	public MerchantElement getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantElement merchant) {
		this.merchant = merchant;
	}

	public CustomerElement getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerElement customer) {
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
