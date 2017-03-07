package GUISkiWorld;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class HomeAdmin {

	private JFrame ManagerGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeAdmin window = new HomeAdmin();
					window.ManagerGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ManagerGUI = new JFrame();
		ManagerGUI.setBounds(100, 100, 784, 459);
		ManagerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManagerGUI.getContentPane().setLayout(null);
		
		JPanel header = new JPanel();
		header.setBounds(0, 11, 768, 43);
		ManagerGUI.getContentPane().add(header);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 70, 768, 339);
		ManagerGUI.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
	}
}
