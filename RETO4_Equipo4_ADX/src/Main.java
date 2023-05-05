import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import manager.*;
import model.pojos.*;
import view.View;
import view.*;

public class Main {

	public Main() {

	}

	public static void main(String[] args) throws SQLException, Exception {

		// SABI - WINDOW //
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ViewSabi window = new ViewSabi();
					//ViewAmaia window = new ViewAmaia();
					ViewDaniel window = new ViewDaniel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// MANAGER VERIFICATION | AMBULATORY //
	
		/**
		// SELECT
		AmbulatoryManager ambulatoryManager = new AmbulatoryManager() ; // Instantiate
		List<Ambulatory> ambulatory = AmbulatoryManager.select() ;
		
		for(Ambulatory ambulatoryForEach: ambulatory) {
			System.out.println(ambulatoryForEach);
		}
		
		// INSERT
		AmbulatoryManager ambulatoryManager = new AmbulatoryManager() ; // Instantiate
		Ambulatory insertAmbulatory = new Ambulatory (0, "Osakidetza", "Crueces", "945000000");
		int registerAfected = ambulatoryManager.insert(insertAmbulatory);
		List<Ambulatory> ambulatory = ambulatoryManager.select();
		for(Ambulatory ambulatoryForEach: ambulatory) {
			System.out.println(ambulatoryForEach);
		}
		
		// UPDATE 
		AmbulatoryManager ambulatoryManager = new AmbulatoryManager() ; // Instantiate
		*/
		
	}
}
