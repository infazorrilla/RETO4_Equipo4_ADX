import java.awt.EventQueue;

import view.View;
import view.ViewSabi;

public class Main {

public Main() {
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSabi window = new ViewSabi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
}
