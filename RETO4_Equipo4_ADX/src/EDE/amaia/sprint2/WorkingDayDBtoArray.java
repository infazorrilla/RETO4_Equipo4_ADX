package EDE.amaia.sprint2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import manager.WorkingDayManager;
import model.pojos.WorkingDay;

public class WorkingDayDBtoArray {

	public static ArrayList<WorkingDay> getWorkingDays(){
		ArrayList<WorkingDay> ret = new ArrayList<WorkingDay>();
		
		WorkingDayManager workingDayManager = new WorkingDayManager();
		try {
			ret = (ArrayList<WorkingDay>) workingDayManager.select();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public static void writeLog(ArrayList<WorkingDay> workingDays) {
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		
		try {
			fileWriter = new FileWriter("src//EDE//amaia//sprint2//logWorkingDays.txt");
			printWriter = new PrintWriter(fileWriter, true);
			
			for(WorkingDay workingDay: workingDays) {
				printWriter.println(workingDay.toString());
			}
		} catch (IOException e) {
			System.out.println("IOException - Error de escritura en el fichero");
		} finally {
			printWriter.close();
			try {
				fileWriter.close();
			} catch (IOException e) {
			}
		}
	}

	public static void main(String[] args) {
		ArrayList<WorkingDay> workingDays = null;
		try {
			workingDays = getWorkingDays();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(workingDays.toString());
		
		writeLog(workingDays);
	}

}
