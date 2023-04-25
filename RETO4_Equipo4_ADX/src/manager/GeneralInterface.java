package manager;

import java.sql.SQLException;
import java.util.List;

public interface GeneralInterface<T> {
	
	public T select(int id) throws SQLException, Exception;
	
	public List<T> select() throws SQLException, Exception;
	
	public void insert(T t) throws SQLException, Exception;
	
	public void update(T t) throws SQLException, Exception;
	
	public void delete (int id) throws SQLException, Exception;
}
