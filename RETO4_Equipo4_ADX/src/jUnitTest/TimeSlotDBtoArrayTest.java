package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import EDE.amaia.sprint2.TimeSlotDBtoArray;
import model.pojos.TimeSlot;

class TimeSlotDBtoArrayTest {

	@Test
	void test() {		
		ArrayList<TimeSlot> timeSlots = null;
		try {
			timeSlots = TimeSlotDBtoArray.getTimeSlots();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TimeSlotDBtoArray.writeLog(timeSlots);
		
		String path= "src//EDE//amaia//sprint2//logTimeSlots.txt";
		 File fic = new File(path);
		 BufferedReader fichero = null;
		 
		 ArrayList<String> test = new ArrayList<String>();
	     
			try {
				fichero = new BufferedReader(new FileReader(fic));		     
		      String linea; 
		      
		      while((linea = fichero.readLine())!=null) 
		      {
		    	 test.add(linea);		        
		      }
		      
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			} 
			finally {
		      try {
				fichero.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			} 
		
		}
			
		assertNotNull(test);	}

}
