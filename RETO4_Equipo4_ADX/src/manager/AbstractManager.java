package manager;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractManager<T> implements GeneralInterface <T>{

	@Override
	public abstract T select(int id) ;

	@Override
	public abstract List<T> select() throws SQLException, Exception;

	@Override
	public abstract void insert(T t) throws SQLException, Exception;

	@Override
	public abstract void update(T t) throws SQLException, Exception;

	@Override
	public abstract void delete(int id) throws SQLException, Exception;
	

}
