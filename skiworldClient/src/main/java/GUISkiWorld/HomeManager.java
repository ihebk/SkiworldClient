package GUISkiWorld;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import contracts.HotelCrudEJBRemote;
import entities.Hotel;
import skiworldClient.test;

public class HomeManager {

	private JFrame ManagerGUI;
	private JTextField textField_i;
	private JTextField i_imagepath;
	private String image;
	private JTable table;
	public DefaultTableModel tableModel;
	test t = new test();

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
	 * 
	 * @throws NamingException
	 */
	public HomeManager() throws NamingException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws NamingException
	 */
	private void initialize() throws NamingException {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
			

			// UIManager.setLookAndFeel(
			// ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel() );
			// set UI manager properties here that affect Quaqua
		} catch (Exception e) {
			// take an appropriate action here
		}

		// InitialContext ctx = new InitialContext();
		// HotelCrudEJBRemote proxy_hotel=(HotelCrudEJBRemote)
		// ctx.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote");
		
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
		lblHotelName_i.setBounds(10, 27, 99, 25);
		lblHotelName_i.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JTextArea i_textArea = new JTextArea();
		i_textArea.setBounds(139, 82, 204, 69);
		panel_i.add(i_textArea);

		textField_i = new JTextField();
		textField_i.setBounds(139, 31, 149, 20);
		textField_i.setColumns(10);

		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setBounds(10, 82, 99, 25);
		lblDescription.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JLabel lblCapacity = new JLabel("Capacity :");
		lblCapacity.setBounds(10, 174, 99, 25);
		lblCapacity.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JSpinner spinner = new JSpinner();
		spinner.setBounds(139, 178, 123, 20);

		JLabel lblRating = new JLabel("Image :");
		lblRating.setBounds(30, 226, 99, 25);
		lblRating.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setBounds(93, 288, 135, 23);
		btnNewButton.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InitialContext ctx;
				try {
					ctx = new InitialContext();
					HotelCrudEJBRemote proxy_hotel = (HotelCrudEJBRemote) ctx
							.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote");

					Hotel hotel = new Hotel();
					hotel.setDescription(i_textArea.getText());
					hotel.setName(textField_i.getText());
					hotel.setCapacity((Integer) spinner.getValue());
					Path path = Paths.get(image);
					byte[] data = Files.readAllBytes(path);
					hotel.setImage(new SerialBlob(data));

					proxy_hotel.addHotel(hotel);
					table.setModel(t.all());
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SerialException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		panel_i.setLayout(null);
		panel_i.add(lblHotelName_i);
		panel_i.add(textField_i);
		panel_i.add(lblDescription);
		panel_i.add(lblCapacity);
		panel_i.add(spinner);
		panel_i.add(lblRating);
		panel_i.add(btnNewButton);

		JButton i_chooseimage = new JButton("choose image");
		i_chooseimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser ch = new JFileChooser();
				ch.showOpenDialog(null);
				File f = ch.getSelectedFile();
				image = f.getAbsolutePath();

				i_imagepath.setText(image);
			}
		});
		i_chooseimage.setBounds(139, 229, 113, 23);
		panel_i.add(i_chooseimage);

		i_imagepath = new JTextField();
		i_imagepath.setBounds(262, 230, 148, 20);
		panel_i.add(i_imagepath);
		i_imagepath.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(424, 11, 329, 165);
		panel_i.add(scrollPane);

		table = new JTable();
		

		table.setModel(t.all());
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Iheb\\Desktop\\15755113534_f75fd1636c_k.jpg"));
		lblNewLabel_1.setBounds(0, 0, 763, 316);
		panel_i.add(lblNewLabel_1);
		JPanel panel = new JPanel();

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));

		lblNewLabel.setBounds(0, 0, 768, 398);
		ManagerGUI.getContentPane().add(lblNewLabel);
	}
}
