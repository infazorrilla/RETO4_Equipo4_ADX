package manager;

import java.sql.SQLException;
import java.util.List;

import model.pojos.Nurse;

public class NurseManager extends AbstractManager<Nurse> {

	// RUBRICA | SPRINT 2 | INDIVIDUAL
	// Management of the Table:
	/**
	 * Select: The equivalent of the operation “select * from table” | Insert:
	 * Insert a new entry in the table | Update: Update an entry in the table |
	 * Delete: Delete an entry in the table by its Id | Common errors have to be
	 * reported: empty table, does not exist, I could not delete, etc.
	 */
	// Complex operations
	/**
	 * At least one non-elementary operation is performed on the table chosen by the
	 * student | An operation that affects several tables, for example
	 */

	@Override
	public Nurse select(int id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Nurse> select() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Nurse t) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Nurse t) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

}
