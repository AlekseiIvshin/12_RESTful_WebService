package rest.elements;


/**
 * Store domain implementation.
 * @author Aleksei_Ivshin
 *
 */
public class StoreElement{

	/**
	 * Store item id.
	 */
	private int id;

	/**
	 * Car in store.
	 */
	private CarElement car;
	/**
	 * Car quantity.
	 */
	private int quantity;
	/**
	 * Current car price.
	 */
	private float price;
	/**
	 * Car enable to test drive.
	 */
	private boolean canTestDrive;

	public boolean canSale() {
		return quantity > 0;
	}

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean getEnableTestDrive() {
		return canTestDrive;
	}

	public void setEnableTestDrive(boolean canTestDrive) {
		this.canTestDrive = canTestDrive;
	}

	public boolean canTestDrive() {
		return canTestDrive;
	}

}
