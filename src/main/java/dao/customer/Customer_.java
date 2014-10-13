package dao.customer;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Customer entity model.
 * 
 * @author Aleksei_Ivshin
 *
 */
@StaticMetamodel(Customer.class)
public class Customer_ {

	/**
	 * Customer id.
	 */
	public static SingularAttribute<Customer, Integer> id;
	/**
	 * Customer name.
	 */
	public static SingularAttribute<Customer, String> name;
	/**
	 * Customer surname.
	 */
	public static SingularAttribute<Customer, String> surname;
	/**
	 * Customer patronymic.
	 */
	public static SingularAttribute<Customer, String> patronymic;
	/**
	 * Customer passport number.
	 */
	public static SingularAttribute<Customer, String> passportNumber;
	/**
	 * Customer passport series.
	 */
	public static SingularAttribute<Customer, String> passportSeries;
	/**
	 * Customer birth date.
	 */
	public static SingularAttribute<Customer, Date> birthDate;
}
