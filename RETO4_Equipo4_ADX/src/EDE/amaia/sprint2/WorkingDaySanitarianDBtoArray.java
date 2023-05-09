package EDE.amaia.sprint2;

import java.sql.SQLException;
import java.util.ArrayList;

import manager.WorkingDaySanitarianManager;
import model.pojos.WorkingDaySanitarian;

public class WorkingDaySanitarianDBtoArray {

	private static WorkingDaySanitarianManager workingDaySanitarianManager;

	public static void main(String[] args) {
		workingDaySanitarianManager = new WorkingDaySanitarianManager();
		ArrayList<WorkingDaySanitarian> workingDaySanitarians = null;
		try {
			workingDaySanitarians = (ArrayList<WorkingDaySanitarian>) workingDaySanitarianManager.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(workingDaySanitarians.toString());
	}

}