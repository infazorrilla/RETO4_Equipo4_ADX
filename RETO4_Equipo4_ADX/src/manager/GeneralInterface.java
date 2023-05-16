package manager;

import java.sql.SQLException;
import java.util.List;

/**
 * This the interface to be extends
 * 
 * @author adx
 *
 * @param <T>
 */
public interface GeneralInterface<T> {

	/**
	 * 
	 * @param id | Is a Integer
	 * @return T
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public T select(int id) throws SQLException, Exception;

	/**
	 * 
	 * @return List<T>
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public List<T> select() throws SQLException, Exception;

	/**
	 * 
	 * @param t
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public void insert(T t) throws SQLException, Exception;

	/**
	 * 
	 * @param t
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public void update(T t) throws SQLException, Exception;

	/**
	 * 
	 * @param id | Is a Integer
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public void delete(int id) throws SQLException, Exception;
}
