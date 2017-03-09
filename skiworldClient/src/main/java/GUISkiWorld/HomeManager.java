package GUISkiWorld;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import contracts.HotelCrudEJBRemote;
import entities.Hotel;
import javax.swing.ImageIcon;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;

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
		try { 
            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");

			
//            UIManager.setLookAndFeel(
//                ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel()  );
       // set UI manager properties here that affect Quaqua
       } catch (Exception e) {
           // take an appropriate action here
       }
		
		InitialContext ctx = new InitialContext();
		HotelCrudEJBRemote proxy_hotel=(HotelCrudEJBRemote) ctx.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote");

		
		
		
		ManagerGUI = new JFrame();
		ManagerGUI.setBounds(100, 100, 784, 459);
		ManagerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManagerGUI.getContentPane().setLayout(null);
		ManagerGUI.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 76, 768, 344);
		ManagerGUI.getContentPane().add(tabbedPane);
		
		JPanel panel_i = new JPanel();
		tabbedPane.addTab("Manage Hotels", null, panel_i, null);
		
		JLabel lblHotelName_i = new JLabel("Hotel  name :");
		lblHotelName_i.setBounds(30, 29, 99, 25);
		lblHotelName_i.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		
		textField_i = new JTextField();
		textField_i.setBounds(139, 31, 149, 20);
		textField_i.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setBounds(30, 81, 99, 25);
		lblDescription.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		
		textField_1 = new JTextField();
		textField_1.setBounds(139, 83, 259, 64);
		textField_1.setColumns(10);
		
		JLabel lblCapacity = new JLabel("Capacity :");
		lblCapacity.setBounds(30, 176, 99, 25);
		lblCapacity.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(139, 178, 123, 20);
		
		JLabel lblRating = new JLabel("Rating :");
		lblRating.setBounds(30, 226, 99, 25);
		lblRating.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(139, 228, 123, 20);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setBounds(93, 288, 135, 23);
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
		panel_i.setLayout(null);
		panel_i.add(lblHotelName_i);
		panel_i.add(textField_i);
		panel_i.add(lblDescription);
		panel_i.add(textField_1);
		panel_i.add(lblCapacity);
		panel_i.add(spinner);
		panel_i.add(lblRating);
		panel_i.add(spinner_1);
		panel_i.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Iheb\\Desktop\\15755113534_f75fd1636c_k.jpg"));
		lblNewLabel_1.setBounds(0, 0, 763, 316);
		panel_i.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));
		
		lblNewLabel.setBounds(0, 0, 768, 398);
		ManagerGUI.getContentPane().add(lblNewLabel);
	}
}
