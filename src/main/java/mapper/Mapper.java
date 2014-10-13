package mapper;

import java.util.List;

/**
 * Mapper interface.
 * 
 * @author Aleksei_Ivshin
 *
 */
public interface Mapper {

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
	<FromClass, ToClass> ToClass map(FromClass object, Class<ToClass> toClass);

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
	<FromClass, ToClass> List<ToClass> mapAsList(List<FromClass> object,
			Class<ToClass> toClass);
}
