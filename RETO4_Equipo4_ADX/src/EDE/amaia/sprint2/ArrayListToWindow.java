package EDE.amaia.sprint2;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.pojos.TimeSlot;
import model.pojos.WorkingDay;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import manager.TimeSlotManager;
import manager.WorkingDayManager;

public class ArrayListToWindow {

	private ArrayList<WorkingDay> workingDays;
	private ArrayList<TimeSlot> timeSlots;
	private TimeSlotManager timeSlotManager;
	private WorkingDayManager workingDayManager;
	private JFrame frame;
	private JTable tableWorkingDay;
	private JTable tableTimeSlot;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayListToWindow window = new ArrayListToWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ArrayListToWindow() {
		workingDayManager = new WorkingDayManager();
		timeSlotManager = new TimeSlotManager();
		try {
			workingDays = (ArrayList<WorkingDay>) workingDayManager.select();
			timeSlots = (ArrayList<TimeSlot>) timeSlotManager.select();
		} catch (SQLException e) {
		} catch (Exception e) {
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 831, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 797, 456);
		frame.getContentPane().add(tabbedPane);

		JPanel panelWorkingDay = new JPanel();
		tabbedPane.addTab("Working Day", null, panelWorkingDay, null);
		panelWorkingDay.setLayout(null);

		JScrollPane scrollPaneWorkingDay;
		scrollPaneWorkingDay = new JScrollPane();
		scrollPaneWorkingDay.setBounds(42, 39, 706, 235);
		panelWorkingDay.add(scrollPaneWorkingDay);

		tableWorkingDay = new JTable();
		scrollPaneWorkingDay.setViewportView(tableWorkingDay);
		tableWorkingDay.setDefaultEditor(Object.class, null);
		tableWorkingDay.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "id", "Fecha", "Hora inicio", "Hora fin" }));
		DefaultTableModel model = (DefaultTableModel) tableWorkingDay.getModel();
		model.setRowCount(0);
		for (WorkingDay workingDay : workingDays) {
			model.addRow(new String[] { Integer.toString(workingDay.getId()), workingDay.getDate().toString(),
					workingDay.getStartTime().toString(), workingDay.getEndTime().toString() });
		}

		JPanel panelTimeSlot = new JPanel();
		tabbedPane.addTab("Time Slot", null, panelTimeSlot, null);
		
		JScrollPane scrollPaneTimeSlot;
		scrollPaneTimeSlot = new JScrollPane();
		scrollPaneTimeSlot.setBounds(42, 39, 706, 235);
		panelTimeSlot.add(scrollPaneTimeSlot);

		tableTimeSlot = new JTable();
		scrollPaneTimeSlot.setViewportView(tableTimeSlot);
		tableTimeSlot.setDefaultEditor(Object.class, null);
		tableTimeSlot.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "id", "Hora inicio", "Hora fin" }));
		model = (DefaultTableModel) tableTimeSlot.getModel();
		model.setRowCount(0);
		for (TimeSlot timeSlot : timeSlots) {
			model.addRow(new String[] { Integer.toString(timeSlot.getId()),
					timeSlot.getStartTime().toString(), timeSlot.getEndTime().toString() });
		}
	}
}
