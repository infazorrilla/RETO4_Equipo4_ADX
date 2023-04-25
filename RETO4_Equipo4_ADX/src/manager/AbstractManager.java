package manager;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractManager<T> implements GeneralInterface <T>{

	@Override
	public T select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> select() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(T t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
