package GUISkiWorld;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import contracts.HotelCrudEJBRemote;
import entities.Hotel;

import java.awt.Font;
import javax.swing.JSpinner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeManager {

	private JFrame ManagerGUI;
	private JTextField textField_i;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeManager window = new HomeManager();
					window.ManagerGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws NamingException 
	 */
	public HomeManager() throws NamingException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws NamingException 
	 */
	private void initialize() throws NamingException {
		InitialContext ctx = new InitialContext();
		HotelCrudEJBRemote proxy_hotel=(HotelCrudEJBRemote) ctx.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote");

		
		
		
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
		
		JPanel panel_i = new JPanel();
		tabbedPane.addTab("Manage Hotels", null, panel_i, null);
		panel_i.setLayout(null);
		
		JLabel lblHotelName_i = new JLabel("Hotel  name :");
		lblHotelName_i.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		lblHotelName_i.setBounds(30, 29, 99, 25);
		panel_i.add(lblHotelName_i);
		
		textField_i = new JTextField();
		textField_i.setBounds(139, 31, 149, 20);
		panel_i.add(textField_i);
		textField_i.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		lblDescription.setBounds(30, 81, 99, 25);
		panel_i.add(lblDescription);
		
		textField_1 = new JTextField();
		textField_1.setBounds(139, 83, 259, 64);
		panel_i.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCapacity = new JLabel("Capacity :");
		lblCapacity.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		lblCapacity.setBounds(30, 176, 99, 25);
		panel_i.add(lblCapacity);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(139, 178, 123, 20);
		panel_i.add(spinner);
		
		JLabel lblRating = new JLabel("Rating :");
		lblRating.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		lblRating.setBounds(30, 226, 99, 25);
		panel_i.add(lblRating);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(139, 228, 123, 20);
		panel_i.add(spinner_1);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Hotel hotel = new Hotel();
				hotel.setDescription(textField_1.getText());
				hotel.setName(textField_1.getText());
				hotel.setCapacity((Integer)spinner.getValue());
				hotel.setRating(Float.parseFloat(spinner_1.getValue().toString()));
				
				proxy_hotel.addHotel(hotel);
				
			}
		});
		btnNewButton.setBounds(93, 288, 135, 23);
		panel_i.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
	}
}
