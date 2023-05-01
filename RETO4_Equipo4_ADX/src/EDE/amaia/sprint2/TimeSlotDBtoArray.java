package EDE.amaia.sprint2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import manager.TimeSlotManager;
import model.pojos.TimeSlot;

public class TimeSlotDBtoArray {

	private static TimeSlotManager timeSlotManager;
	
	
	public static void main(String[] args) {
		timeSlotManager = new TimeSlotManager();
		ArrayList<TimeSlot> timeSlots = null;
		try {
			timeSlots = (ArrayList<TimeSlot>) timeSlotManager.select();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(timeSlots.toString());
		
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		
		try {
			fileWriter = new FileWriter("src//EDE//amaia//sprint2//logTimeSlots.txt");
			printWriter = new PrintWriter(fileWriter, true);
			
			for(TimeSlot timeSlot: timeSlots) {
				printWriter.println(timeSlot.toString());
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

}
