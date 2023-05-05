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
					// ViewSabi window = new ViewSabi();
					// ViewAmaia window = new ViewAmaia();
					ViewDaniel window = new ViewDaniel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
