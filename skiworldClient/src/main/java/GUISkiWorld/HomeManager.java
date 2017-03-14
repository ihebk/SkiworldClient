package GUISkiWorld;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;

import org.apache.regexp.RE;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import business.HotelDelegate;
import business.PisteDelegate;
import business.ResortBusinessDelegate;
import business.StoreBusinessDelegate;
import business.TransportDelegate;
import business.UserBusiness;
import business.UserDelegate;
import entities.Hotel;
import entities.Piste;
import entities.Resort;
import entities.Store;
import entities.Transport;
import entities.User;
import models.HotelModel;
import models.PisteModel;
import models.StoreModel;
import models.TransportModel;
import skiworldClient.SMS;
import tk.plogitech.darksky.forecast.ForecastException;

public class HomeManager {

	private JFrame ManagerGUI;
	private JTextField storeName;
	private JTextField storeLocation;
	private JTextField phoneStore;
	private JTextField emailStore;
	private JComboBox<String> resortStore;
	public Store rowSelectedStore;
	public List<Resort> listStore;
	private JTable storeTable;
	private JTextField i_hotelname;
	private JTextField i_imagepath;
	private String image;
	private JTable table;
	public DefaultTableModel tableModel;
	private JTextField h_tf_phone;
	private JTextField h_tf_firstname;
	private JTextField h_tf_lastname;
	private JTextField h_tf_address;
	private JTextField h_tf_email;
	private JLabel h_lbl_role;
	boolean enabled = false;
	private String h_image;
	private JLabel h_lbl_genderview;
	private JPasswordField h_tf_password;
	private JPasswordField h_tf_paswwordConfirm;
	private String mail;
	private JTable i_table_hotel;
	HotelModel hotelmodel = new HotelModel();
	TransportModel transportmodel = new TransportModel();
	PisteModel pistemodel = new PisteModel();
	public Hotel hotelrow = null;
	public Transport trrow = null;
	public Piste pirow = null;
	private BufferedImage img_display;
	HotelDelegate hotelDelegate = new HotelDelegate();
	TransportDelegate transportDelegate = new TransportDelegate();
	PisteDelegate pisteDelegate = new PisteDelegate();
	private JTextField i_tr_path;
	private JTable i_tr_table;
	private JTextField i_pi_name;
	private JTextField i_pi_path;
	private JTable i_pi_table;

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

	public boolean CheckMail(String mail) {
		RE r = new RE(
				"^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$");
		return r.match(mail);
	}

	void setTextFields() {
		h_lbl_role.setText(Authentification.userConnected.getRole());
		h_tf_firstname.setText(Authentification.userConnected.getFirstname());
		h_tf_lastname.setText(Authentification.userConnected.getLastname());
		h_tf_email.setText(Authentification.userConnected.getEmail());
		h_lbl_genderview.setText(Authentification.userConnected.getGender());
		h_tf_address.setText(Authentification.userConnected.getAddress());
		h_tf_phone.setText(String.valueOf(Authentification.userConnected.getPhone()));
	}

	/**
	 * Create the application.
	 * 
	 * @throws NamingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JAXBException
	 * @throws ForecastException
	 */
	public HomeManager() throws NamingException, SQLException, IOException, JAXBException, ForecastException {

		initialize();
		fillCbResort();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws NamingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JAXBException
	 * @throws ForecastException
	 */
	private void initialize() throws NamingException, SQLException, IOException, JAXBException, ForecastException {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");

		} catch (Exception e) {
		}

		ManagerGUI = new JFrame();
		ManagerGUI.setBounds(100, 100, 784, 475);
		ManagerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManagerGUI.getContentPane().setLayout(null);
		ManagerGUI.getContentPane().setLayout(null);

		// SMS.main(null, "+21624056027", "hello");
		// YahooWeatherService service = new YahooWeatherService();
		// Channel channel = service.getForecast("2502265", DegreeUnit.CELSIUS);
		// System.out.println(channel.getDescription());
		// System.out.println(channel.getWind());
		// System.out.println(channel.getAstronomy());

		// ForecastRequest request = new ForecastRequestBuilder()
		// .key(new APIKey("ad7177e55a8be03097509cb51411cd08"))
		// .time(Instant.now().minus(5, ChronoUnit.DAYS))
		// .language(ForecastRequestBuilder.Language.de)
		// .units(ForecastRequestBuilder.Units.us)
		// .exclude(ForecastRequestBuilder.Block.minutely)
		// .extendHourly()
		// .location(new GeoCoordinates(new Longitude(13.377704), new
		// Latitude(52.516275))).build();
		//
		// DarkSkyClient client = new DarkSkyClient();
		// String forecast = client.forecastJsonString(request);
		// System.out.println(forecast);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 76, 768, 344);
		ManagerGUI.getContentPane().add(tabbedPane);
		JPanel Stores = new JPanel();
		Stores.setLayout(null);
		tabbedPane.addTab("Stores Management", null, Stores, null);

		JLabel lblStoreAdress = new JLabel("Adresse :");
		lblStoreAdress.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblStoreAdress.setBounds(10, 239, 99, 25);
		Stores.add(lblStoreAdress);

		JTextArea storeDescription = new JTextArea();
		storeDescription.setBounds(139, 62, 204, 69);
		Stores.add(storeDescription);
		JLabel lblAlreadyExistPhR = new JLabel("Already exist");
		lblAlreadyExistPhR.setForeground(Color.RED);
		lblAlreadyExistPhR.setBounds(139, 176, 89, 14);
		Stores.add(lblAlreadyExistPhR);
		lblAlreadyExistPhR.setVisible(false);
		JLabel lblAlreadyExistEmR = new JLabel("Already exist");
		lblAlreadyExistEmR.setForeground(Color.RED);
		lblAlreadyExistEmR.setBounds(282, 176, 99, 14);
		Stores.add(lblAlreadyExistEmR);
		lblAlreadyExistEmR.setVisible(false);

		JPanel panel_i_hotel = new JPanel();
		tabbedPane.addTab("Manage Hotels", null, panel_i_hotel, null);

		JLabel lblHotelName_i = new JLabel("Hotel  name :");
		lblHotelName_i.setBounds(10, 27, 99, 25);
		lblHotelName_i.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JTextArea i_hoteldescription = new JTextArea();
		i_hoteldescription.setBounds(139, 62, 204, 69);
		panel_i_hotel.add(i_hoteldescription);
		JSpinner i_tr_price = new JSpinner();
		i_tr_price.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
		i_tr_price.setBounds(139, 207, 123, 20);

		i_hotelname = new JTextField();
		i_hotelname.setBounds(139, 31, 149, 20);
		i_hotelname.setColumns(10);

		JComboBox i_tr_type_combo = new JComboBox();
		i_tr_type_combo.setModel(new DefaultComboBoxModel(new String[] { "Car", "JetSki", "Sky tram" }));
		i_tr_type_combo.setBounds(139, 15, 204, 20);

		JComboBox i_hotel_combo = new JComboBox();
		i_hotel_combo.setBounds(139, 238, 113, 20);
		panel_i_hotel.add(i_hotel_combo);
		JComboBox i_tr_resort_combo = new JComboBox();
		i_tr_resort_combo.setBounds(139, 238, 113, 20);
		JComboBox i_pi_resortcombo = new JComboBox();

		i_table_hotel = new JTable();
		i_pi_table = new JTable();
		i_tr_table = new JTable();

		i_table_hotel.setModel(hotelmodel.hotelModel());
		i_tr_table.setModel(transportmodel.transportModel());
		i_pi_table.setModel(pistemodel.pisteModel());

		hotelmodel.fillResortComboBox(i_hotel_combo);
		pistemodel.fillResortComboBox(i_pi_resortcombo);
		transportmodel.fillResortComboBox(i_tr_resort_combo);

		JLabel i_lblDescription = new JLabel("Description :");
		i_lblDescription.setBounds(10, 60, 99, 25);
		i_lblDescription.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JLabel i_lblCapacity = new JLabel("Capacity :");
		i_lblCapacity.setBounds(10, 144, 99, 25);
		i_lblCapacity.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JSpinner i_hotelcapacity = new JSpinner();
		i_hotelcapacity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		i_hotelcapacity.setBounds(139, 148, 123, 20);

		JLabel i_lblRating = new JLabel("Image :");
		i_lblRating.setBounds(10, 186, 99, 25);
		i_lblRating.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JButton i_btnNewButton = new JButton("Add");
		i_btnNewButton.setBounds(39, 288, 99, 23);
		i_btnNewButton.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				if (i_hotelname.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill the name space");
					return;
				}
				if (i_hoteldescription.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill the Description space");
					return;
				}
				if (i_imagepath.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please select a photo");
					return;
				}

				for (int i = 0; i < hotelmodel.getAll().size(); i++) {

					if (i_hotelname.getText().equals(hotelmodel.getAll().get(i).getName())) {
						JOptionPane.showMessageDialog(null, "a Hotel with that name already exists");
						return;

					}

				}

				Hotel hotel = new Hotel();
				hotel.setDescription(i_hoteldescription.getText());
				hotel.setName(i_hotelname.getText());
				hotel.setCapacity((Integer) i_hotelcapacity.getValue());
				hotel.setResort(ResortBusinessDelegate.findResortByLabel(i_hotel_combo.getSelectedItem().toString()));

				File file = new File(image);
				byte[] bFile = new byte[(int) file.length()];

				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				hotel.setImage(bFile);

				try {

					hotelDelegate.addHotel(hotel);

					i_table_hotel.setModel(hotelmodel.hotelModel());
				} catch (SQLException | IOException | NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		panel_i_hotel.setLayout(null);
		panel_i_hotel.add(lblHotelName_i);
		panel_i_hotel.add(i_hotelname);
		panel_i_hotel.add(i_lblDescription);
		panel_i_hotel.add(i_lblCapacity);
		panel_i_hotel.add(i_hotelcapacity);
		panel_i_hotel.add(i_lblRating);
		panel_i_hotel.add(i_btnNewButton);

		JButton i_chooseimage = new JButton("choose image");
		i_chooseimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser ch = new JFileChooser();
				ch.showOpenDialog(null);
				File f = ch.getSelectedFile();
				if (f != null) {
					image = f.getAbsolutePath();

					i_imagepath.setText(image);
				}

				else {

					image = "src//main//resources//imgs//defaulthotel.jpg";
					i_imagepath.setText(image);
				}

			}

		});
		i_chooseimage.setBounds(139, 186, 113, 23);
		panel_i_hotel.add(i_chooseimage);

		i_imagepath = new JTextField();
		i_imagepath.setEditable(false);
		i_imagepath.setBounds(261, 187, 148, 20);
		panel_i_hotel.add(i_imagepath);
		i_imagepath.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(424, 11, 329, 165);
		JLabel i_imgdisplay = new JLabel("");
		i_imgdisplay.setBounds(516, 187, 150, 130);
		i_imgdisplay.setIcon((Icon) img_display);
		panel_i_hotel.add(i_imgdisplay);

		table = new JTable();
		JScrollPane i_scrollPane = new JScrollPane();

		i_scrollPane.setBounds(424, 11, 329, 165);
		panel_i_hotel.add(i_scrollPane);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Iheb\\Desktop\\15755113534_f75fd1636c_k.jpg"));
		lblNewLabel_1.setBounds(0, 0, 763, 316);

		JPanel managerProfilePanel = new JPanel();
		JLabel h_lbl_image = new JLabel("");
		h_lbl_image.setBounds(452, 8, 99, 96);
		managerProfilePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					FileOutputStream fos = new FileOutputStream("src//main//resources//imgs//user.jpg");
					fos.write(Authentification.userConnected.getPhoto());
					fos.close();
					ImageIcon imgThisImg2 = new ImageIcon("src//main//resources//imgs//user.jpg");
					Image h_image = imgThisImg2.getImage(); // transform it
					Image h_newimg = h_image.getScaledInstance(150, 130, java.awt.Image.SCALE_SMOOTH); // scale
																										// it
																										// the
																										// smooth
																										// way
					ImageIcon imgThisImg3 = new ImageIcon(h_newimg); // transform
																		// it
																		// back
					h_lbl_image.setIcon(imgThisImg3);
					imgThisImg2.getImage().flush();
				} catch (Exception e1) {
					// System.out.println("image vide" + e1);
				}
			}
		});
		tabbedPane.addTab("Profil", null, managerProfilePanel, null);
		i_table_hotel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int index = i_table_hotel.getSelectedRow();
				hotelrow = hotelmodel.hotellist.get(index);

				try {
					FileOutputStream fos = new FileOutputStream("src//main//resources//imgs//hotel.jpg");
					fos.write(hotelrow.getImage());
					fos.close();
					ImageIcon imgThisImg1 = new ImageIcon("src//main//resources//imgs//hotel.jpg");
					Image image = imgThisImg1.getImage(); // transform it
					Image newimg = image.getScaledInstance(150, 130, java.awt.Image.SCALE_SMOOTH); // scale
																									// it
																									// the
																									// smooth
																									// way
					ImageIcon imgThisImg = new ImageIcon(newimg); // transform
																	// it back
					i_imgdisplay.setIcon(imgThisImg);
					imgThisImg.getImage().flush();

					// modify table

					i_hotelname.setText(hotelrow.getName());
					i_hoteldescription.setText(hotelrow.getDescription());
					i_hotelcapacity.setValue(hotelrow.getCapacity());

					// end modif

				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

		});

		i_scrollPane.setViewportView(i_table_hotel);

		JButton i_edit = new JButton("Edit");
		i_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotelrow = null;
				int index = i_table_hotel.getSelectedRow();

				if (index != -1) {

					hotelrow = hotelmodel.hotellist.get(index);

					if (i_hotelname.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Fill the name space");
						return;
					}
					if (i_hoteldescription.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Fill the Description space");
						return;
					}
					if (i_imagepath.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please select a photo");
						return;
					}

					Hotel hotel = new Hotel();
					hotel.setIdHotel(hotelrow.getIdHotel());
					hotel.setDescription(i_hoteldescription.getText());
					hotel.setName(i_hotelname.getText());
					hotel.setCapacity((Integer) i_hotelcapacity.getValue());
					hotel.setResort(
							ResortBusinessDelegate.findResortByLabel(i_hotel_combo.getSelectedItem().toString()));

					File file = new File(image);
					byte[] bFile = new byte[(int) file.length()];

					try {
						FileInputStream fileInputStream = new FileInputStream(file);
						fileInputStream.read(bFile);
						fileInputStream.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					hotel.setImage(bFile);

					try {
						hotelDelegate.updateHotel(hotel);
						i_table_hotel.setModel(hotelmodel.hotelModel());
					} catch (SQLException | IOException | NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select a hotel to modify");
				}
			}
		});

		i_edit.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_edit.setBounds(183, 288, 89, 23);
		panel_i_hotel.add(i_edit);

		JButton i_remove = new JButton("Remove");
		i_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotelrow = null;
				int index = i_table_hotel.getSelectedRow();

				if (index != -1) {

					hotelrow = hotelmodel.hotellist.get(index);

					try {
						hotelDelegate.deleteHotel(hotelrow.getIdHotel());
						SMS.main(null, "+21624056027",
								"The hotel named " + hotelrow.getName().toUpperCase() + " was removed ! :)");

						i_table_hotel.setModel(hotelmodel.hotelModel());
					} catch (SQLException | IOException | NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select a hotel to remove");
				}

			}
		});
		i_remove.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_remove.setBounds(321, 288, 89, 23);
		panel_i_hotel.add(i_remove);

		JLabel lblResort = new JLabel("Resort :");
		lblResort.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		lblResort.setBounds(10, 234, 99, 25);
		panel_i_hotel.add(lblResort);

		JLabel i_backgH = new JLabel("New label");
		i_backgH.setIcon(new ImageIcon(
				"C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));
		i_backgH.setBounds(0, 0, 763, 316);
		panel_i_hotel.add(i_backgH);

		JPanel panel_i_trans = new JPanel();
		panel_i_trans.setLayout(null);
		tabbedPane.addTab("Manage Transport", null, panel_i_trans, null);

		JTextArea i_tr_descriptiont = new JTextArea();
		i_tr_descriptiont.setBounds(139, 46, 204, 69);
		panel_i_trans.add(i_tr_descriptiont);

		JLabel i_type = new JLabel("Type :");
		i_type.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_type.setBounds(10, 11, 99, 25);
		panel_i_trans.add(i_type);

		JLabel i_desc = new JLabel("Description :");
		i_desc.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_desc.setBounds(10, 60, 99, 25);
		panel_i_trans.add(i_desc);

		JLabel i_lb = new JLabel("Capacity :");
		i_lb.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_lb.setBounds(10, 126, 99, 25);
		panel_i_trans.add(i_lb);

		JSpinner i_tr_cap = new JSpinner();
		i_tr_cap.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		i_tr_cap.setBounds(139, 126, 123, 20);
		panel_i_trans.add(i_tr_cap);

		JLabel i_label_3 = new JLabel("Image :");
		i_label_3.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_label_3.setBounds(10, 156, 99, 25);
		panel_i_trans.add(i_label_3);

		panel_i_trans.add(i_tr_resort_combo);
		panel_i_trans.add(i_tr_type_combo);

		JButton i_tr_add = new JButton("Add");
		i_tr_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (i_tr_descriptiont.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill the description space space");
					return;
				}
				if (i_tr_path.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please select a photo");
					return;
				}
				if (i_tr_price.getValue().equals("")) {
					JOptionPane.showMessageDialog(null, "Please select a photo");
					return;
				}

				Transport transport = new Transport();
				transport.setType(i_tr_type_combo.getSelectedItem().toString());

				transport.setDescription(i_tr_descriptiont.getText());
				transport.setBookingPrice((float) i_tr_price.getValue());
				transport.setCapacity((Integer) i_tr_cap.getValue());
				transport.setResort(
						ResortBusinessDelegate.findResortByLabel(i_tr_resort_combo.getSelectedItem().toString()));

				File file = new File(image);
				byte[] bFile = new byte[(int) file.length()];

				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				transport.setImage(bFile);

				try {
					transportDelegate.addTransport(transport);
					i_tr_table.setModel(transportmodel.transportModel());

				} catch (SQLException | IOException | NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}

			}
		});
		i_tr_add.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_tr_add.setBounds(39, 288, 99, 23);
		panel_i_trans.add(i_tr_add);

		JButton i_tr_choose_img = new JButton("choose image");
		i_tr_choose_img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser ch = new JFileChooser();
				ch.showOpenDialog(null);
				File f = ch.getSelectedFile();
				if (f != null) {
					image = f.getAbsolutePath();

					i_tr_path.setText(image);
				}

				else {

					image = "src//main//resources//imgs//defaultTr.jpg";
					i_tr_path.setText(image);
				}

			}
		});
		i_tr_choose_img.setBounds(139, 157, 113, 23);
		panel_i_trans.add(i_tr_choose_img);

		i_tr_path = new JTextField();
		i_tr_path.setEditable(false);
		i_tr_path.setColumns(10);
		i_tr_path.setBounds(262, 160, 148, 20);
		panel_i_trans.add(i_tr_path);
		panel_i_trans.add(i_tr_price);

		JLabel i_tr_image = new JLabel("");
		i_tr_image.setBounds(516, 187, 150, 130);
		panel_i_trans.add(i_tr_image);

		JButton i_tr_edit = new JButton("Edit");
		i_tr_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				trrow = null;
				int tr_index = i_tr_table.getSelectedRow();

				if (tr_index != -1) {

					trrow = transportmodel.transporttlist.get(tr_index);

					Transport transport = new Transport();
					transport.setIdTransport(trrow.getIdTransport());
					transport.setType(i_tr_type_combo.getSelectedItem().toString());

					transport.setDescription(i_tr_descriptiont.getText());
					transport.setBookingPrice((float) i_tr_price.getValue());
					transport.setCapacity((Integer) i_tr_cap.getValue());
					transport.setResort(
							ResortBusinessDelegate.findResortByLabel(i_tr_resort_combo.getSelectedItem().toString()));

					File file = new File(image);
					byte[] bFile = new byte[(int) file.length()];

					try {
						FileInputStream fileInputStream = new FileInputStream(file);
						fileInputStream.read(bFile);
						fileInputStream.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					transport.setImage(bFile);

					try {
						transportDelegate.updateTransport(transport);
						i_tr_table.setModel(transportmodel.transportModel());
					} catch (SQLException | IOException | NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select a meansOfTransport to modify");
				}

			}
		});
		i_tr_edit.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_tr_edit.setBounds(183, 288, 89, 23);
		panel_i_trans.add(i_tr_edit);

		JButton i_tr_remove = new JButton("Remove");
		i_tr_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trrow = null;
				int tr_index = i_tr_table.getSelectedRow();

				if (tr_index != -1) {

					trrow = transportmodel.transporttlist.get(tr_index);

					try {
						transportDelegate.deleteTransport(trrow.getIdTransport());

						i_tr_table.setModel(transportmodel.transportModel());
					} catch (SQLException | IOException | NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select a meansOfTransport to remove");
				}

			}

		});
		i_tr_remove.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_tr_remove.setBounds(321, 288, 89, 23);
		panel_i_trans.add(i_tr_remove);

		JLabel label_5 = new JLabel("Resort :");
		label_5.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		label_5.setBounds(10, 234, 99, 25);
		panel_i_trans.add(label_5);

		JScrollPane i_tr_scrollPane = new JScrollPane();
		i_tr_scrollPane.setBounds(420, 11, 333, 157);
		panel_i_trans.add(i_tr_scrollPane);

		i_tr_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int tr_index = i_tr_table.getSelectedRow();
				trrow = transportmodel.transporttlist.get(tr_index);

				try {
					FileOutputStream fos = new FileOutputStream("src//main//resources//imgs//tr.jpg");
					fos.write(trrow.getImage());
					fos.close();
					ImageIcon imgThisImg1 = new ImageIcon("src//main//resources//imgs//tr.jpg");
					Image image = imgThisImg1.getImage(); // transform it
					Image newimg = image.getScaledInstance(150, 130, java.awt.Image.SCALE_SMOOTH); // scale
																									// it
																									// the
																									// smooth
																									// way
					ImageIcon imgThisImg = new ImageIcon(newimg); // transform
																	// it back
					i_tr_image.setIcon(imgThisImg);
					imgThisImg.getImage().flush();

					// modify table
					if (trrow.getType().equals("Car"))
						i_tr_type_combo.setSelectedIndex(0);
					if (trrow.getType().equals("JetSki"))
						i_tr_type_combo.setSelectedIndex(1);
					if (trrow.getType().equals("Ski Tram"))
						i_tr_type_combo.setSelectedIndex(2);
					i_tr_descriptiont.setText(trrow.getDescription());
					i_tr_cap.setValue(trrow.getCapacity());
					i_tr_price.setValue(trrow.getBookingPrice());

					// end modif

				} catch (Exception e3) {
					e3.printStackTrace();

				}

			}
		});
		i_tr_scrollPane.setViewportView(i_tr_table);

		JLabel i_lblBookingprice = new JLabel("Booking Price :");
		i_lblBookingprice.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_lblBookingprice.setBounds(10, 203, 119, 25);
		panel_i_trans.add(i_lblBookingprice);

		JLabel i_lblDt = new JLabel("DT");
		i_lblDt.setFont(new Font("Source Sans Pro", Font.BOLD, 16));
		i_lblDt.setBounds(268, 208, 46, 14);
		panel_i_trans.add(i_lblDt);

		JLabel i_backg = new JLabel("");
		i_backg.setIcon(new ImageIcon(
				"C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));
		i_backg.setBounds(0, 0, 763, 316);
		panel_i_trans.add(i_backg);

		storeTable = new JTable();
		JPanel panel_i_piste = new JPanel();
		panel_i_piste.setLayout(null);
		tabbedPane.addTab("Manage Pistes", null, panel_i_piste, null);

		JTextArea i_pi_description = new JTextArea();
		i_pi_description.setBounds(139, 62, 204, 69);
		panel_i_piste.add(i_pi_description);

		JLabel lblPisteName = new JLabel("Piste name :");
		lblPisteName.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		lblPisteName.setBounds(10, 27, 99, 25);
		panel_i_piste.add(lblPisteName);

		i_pi_name = new JTextField();
		i_pi_name.setColumns(10);
		i_pi_name.setBounds(139, 31, 149, 20);
		panel_i_piste.add(i_pi_name);

		JComboBox i_pi_typecombo = new JComboBox();
		i_pi_typecombo.setModel(new DefaultComboBoxModel(new String[] { "Training", "Daily Activities" }));
		i_pi_typecombo.setBounds(139, 148, 113, 20);
		panel_i_piste.add(i_pi_typecombo);

		JLabel i_pi_lblType = new JLabel("Type :");
		i_pi_lblType.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pi_lblType.setBounds(10, 144, 99, 25);
		panel_i_piste.add(i_pi_lblType);

		JLabel i_deslabel_1 = new JLabel("Description :");
		i_deslabel_1.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_deslabel_1.setBounds(10, 60, 99, 25);
		panel_i_piste.add(i_deslabel_1);

		JLabel i_pi_l = new JLabel("Image :");
		i_pi_l.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pi_l.setBounds(10, 186, 99, 25);
		panel_i_piste.add(i_pi_l);

		i_pi_resortcombo.setBounds(139, 238, 113, 20);
		panel_i_piste.add(i_pi_resortcombo);

		JButton i_pi_add = new JButton("Add");

		i_pi_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (i_pi_name.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill the name space");
					return;
				}

				if (i_pi_description.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill the description space");
					return;
				}
				if (i_pi_path.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please select a photo");
					return;
				}

				for (int i = 0; i < pistemodel.getAll().size(); i++) {

					if (i_pi_name.getText().equals(pistemodel.getAll().get(i).getName())) {
						JOptionPane.showMessageDialog(null, "a Piste with that name already exists");
						return;

					}

				}

				Piste piste = new Piste();
				piste.setDescription(i_pi_description.getText());
				piste.setName(i_pi_name.getText());
				piste.setType(i_pi_typecombo.getSelectedItem().toString());
				piste.setResort(
						ResortBusinessDelegate.findResortByLabel(i_pi_resortcombo.getSelectedItem().toString()));

				File file = new File(image);
				byte[] bFile = new byte[(int) file.length()];

				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				piste.setImage(bFile);

				try {
					pisteDelegate.addPiste(piste);

					i_pi_table.setModel(pistemodel.pisteModel());
				} catch (SQLException | IOException | NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		i_pi_add.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pi_add.setBounds(39, 288, 99, 23);
		panel_i_piste.add(i_pi_add);

		JButton i_pi_chooseimage = new JButton("choose image");
		i_pi_chooseimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser ch = new JFileChooser();
				ch.showOpenDialog(null);
				File f = ch.getSelectedFile();
				if (f != null) {
					image = f.getAbsolutePath();

					i_pi_path.setText(image);
				}

				else {

					image = "src//main//resources//imgs//defaultpiste.jpg";
					i_pi_path.setText(image);
				}

				i_pi_path.setText(image);

			}
		});
		i_pi_chooseimage.setBounds(139, 186, 113, 23);
		panel_i_piste.add(i_pi_chooseimage);

		i_pi_path = new JTextField();
		i_pi_path.setEditable(false);
		i_pi_path.setColumns(10);
		i_pi_path.setBounds(261, 187, 148, 20);
		panel_i_piste.add(i_pi_path);

		JLabel i_pi_image = new JLabel("");
		i_pi_image.setBounds(516, 187, 150, 130);
		panel_i_piste.add(i_pi_image);

		JScrollPane i_pi_scrollPane = new JScrollPane();
		i_pi_scrollPane.setBounds(424, 11, 329, 165);
		panel_i_piste.add(i_pi_scrollPane);

		i_pi_table = new JTable();
		i_pi_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int pi_index = i_pi_table.getSelectedRow();
				pirow = pistemodel.pistelist.get(pi_index);

				try {
					FileOutputStream fos = new FileOutputStream("src//main//resources//imgs//piste.jpg");
					fos.write(pirow.getImage());
					fos.close();
					ImageIcon imgThisImg1 = new ImageIcon("src//main//resources//imgs//piste.jpg");
					Image image = imgThisImg1.getImage(); // transform it
					Image newimg = image.getScaledInstance(150, 130, java.awt.Image.SCALE_SMOOTH); // scale
																									// it
																									// the
																									// smooth
																									// way
					ImageIcon imgThisImg = new ImageIcon(newimg); // transform
																	// it back
					i_pi_image.setIcon(imgThisImg);
					imgThisImg.getImage().flush();

					// modify table
					if (pirow.getType().equals("Training"))
						i_pi_typecombo.setSelectedIndex(0);
					if (pirow.getType().equals("Daily Activities"))
						i_pi_typecombo.setSelectedIndex(1);

					i_pi_description.setText(pirow.getDescription());
					i_pi_name.setText(pirow.getName());

					// end modif

				} catch (Exception e3) {
					e3.printStackTrace();

				}

			}

		});

		i_pi_scrollPane.setViewportView(i_pi_table);

		JButton i_pi_edit = new JButton("Edit");
		i_pi_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pirow = null;
				int pi_index = i_pi_table.getSelectedRow();

				if (pi_index != -1) {

					pirow = pistemodel.pistelist.get(pi_index);

					if (i_pi_name.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Fill the name space");
						return;
					}

					if (i_pi_description.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Fill the description space");
						return;
					}
					if (i_pi_path.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please select a photo");
						return;
					}

					Piste piste = new Piste();
					piste.setIdPiste(pirow.getIdPiste());
					piste.setDescription(i_pi_description.getText());
					piste.setName(i_pi_name.getText());
					piste.setType(i_pi_typecombo.getSelectedItem().toString());
					piste.setResort(
							ResortBusinessDelegate.findResortByLabel(i_hotel_combo.getSelectedItem().toString()));

					File file = new File(image);
					byte[] bFile = new byte[(int) file.length()];

					try {
						FileInputStream fileInputStream = new FileInputStream(file);
						fileInputStream.read(bFile);
						fileInputStream.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					piste.setImage(bFile);

					try {
						pisteDelegate.updatePiste(piste);
						i_pi_table.setModel(pistemodel.pisteModel());
					} catch (SQLException | IOException | NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select a piste to modify");
				}
			}

		});
		i_pi_edit.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pi_edit.setBounds(183, 288, 89, 23);
		panel_i_piste.add(i_pi_edit);

		JButton i_pi_remove = new JButton("Remove");
		i_pi_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pirow = null;
				int pi_index = i_pi_table.getSelectedRow();

				if (pi_index != -1) {

					pirow = pistemodel.pistelist.get(pi_index);

					try {
						pisteDelegate.deletePiste(pirow.getIdPiste());
						SMS.main(null, "+21624056027",
								"The Piste named " + pirow.getName().toUpperCase() + " was removed ! :)");

						i_pi_table.setModel(pistemodel.pisteModel());
					} catch (SQLException | IOException | NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select a piste to remove");
				}

			}

		});
		i_pi_remove.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pi_remove.setBounds(321, 288, 89, 23);
		panel_i_piste.add(i_pi_remove);

		JLabel i_pilabel_res = new JLabel("Resort :");
		i_pilabel_res.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pilabel_res.setBounds(10, 234, 99, 25);
		panel_i_piste.add(i_pilabel_res);

		JLabel i_pi_backg = new JLabel("New label");
		i_pi_backg.setIcon(new ImageIcon(
				"C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));
		i_pi_backg.setBounds(0, 0, 763, 316);
		panel_i_piste.add(i_pi_backg);
		JPanel panel = new JPanel();

		JLabel i_label = new JLabel("");
		i_label.setIcon(new ImageIcon(
				"C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));

		// lblNewLabel.setBounds(0, 0, 780, 436);
		// ManagerGUI.getContentPane().add(lblNewLabel);
		tabbedPane.addTab("Profil", null, managerProfilePanel, null);
		managerProfilePanel.setLayout(null);

		JLabel h_lbl_status = new JLabel("You are");
		h_lbl_status.setBounds(35, 35, 46, 22);
		managerProfilePanel.add(h_lbl_status);

		managerProfilePanel.add(h_lbl_image);

		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(24, 78, 72, 14);
		managerProfilePanel.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(24, 115, 57, 14);
		managerProfilePanel.add(lblLastName);

		h_lbl_role = new JLabel("");
		h_lbl_role.setBounds(109, 39, 72, 14);
		managerProfilePanel.add(h_lbl_role);

		JLabel h_lbl_phone = new JLabel("Phone");
		h_lbl_phone.setBounds(24, 269, 46, 14);
		managerProfilePanel.add(h_lbl_phone);

		h_tf_phone = new JTextField();
		h_tf_phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		h_tf_phone.setEnabled(false);
		h_tf_phone.setBounds(105, 266, 86, 20);
		managerProfilePanel.add(h_tf_phone);
		h_tf_phone.setColumns(10);

		h_tf_firstname = new JTextField();
		h_tf_firstname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if ((Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		h_tf_firstname.setEnabled(false);
		h_tf_firstname.setBounds(105, 75, 86, 20);
		managerProfilePanel.add(h_tf_firstname);
		h_tf_firstname.setColumns(10);

		h_tf_lastname = new JTextField();
		h_tf_lastname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if ((Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		h_tf_lastname.setEnabled(false);
		h_tf_lastname.setBounds(105, 112, 86, 20);
		managerProfilePanel.add(h_tf_lastname);
		h_tf_lastname.setColumns(10);

		JLabel h_lblAddress = new JLabel("Address");
		h_lblAddress.setBounds(24, 227, 46, 14);
		managerProfilePanel.add(h_lblAddress);

		h_tf_address = new JTextField();
		h_tf_address.setEnabled(false);
		h_tf_address.setBounds(105, 224, 86, 20);
		managerProfilePanel.add(h_tf_address);
		h_tf_address.setColumns(10);

		JLabel lblGender = new JLabel("gender");
		lblGender.setBounds(24, 181, 46, 14);
		managerProfilePanel.add(lblGender);

		h_lbl_genderview = new JLabel("");
		h_lbl_genderview.setBounds(105, 181, 86, 14);
		managerProfilePanel.add(h_lbl_genderview);

		JButton btnNewButton1 = new JButton("Choose a photo");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch1 = new JFileChooser();
				ch1.showOpenDialog(null);
				File f1 = ch1.getSelectedFile();
				h_image = f1.getAbsolutePath();

				// i_imagepath.setText(image);

			}
		});
		btnNewButton1.setBounds(561, 11, 109, 23);
		managerProfilePanel.add(btnNewButton1);

		JButton btnNewButton_1 = new JButton("Apply photo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file1 = new File(h_image);
				byte[] bFile = new byte[(int) file1.length()];

				try {
					FileInputStream fileInputStream = new FileInputStream(file1);
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Authentification.userConnected.setPhoto(bFile);
				// u.setImage(bFile);
				// InitialContext ctx;

				// ctx = new InitialContext();
				// UserCrudEJBRemote proxyUser = (UserCrudEJBRemote) ctx
				// .lookup("/SkiWorld-ear/SkiWorld-ejb/UserCrudEJB!services.UserCrudEJBRemote");
				// proxyUser.updateUser(Authentification.userConnected);
				UserDelegate.updateUser(Authentification.userConnected);

			}
		});
		btnNewButton_1.setBounds(561, 39, 109, 23);
		managerProfilePanel.add(btnNewButton_1);

		JLabel h_lblEmail = new JLabel("Email");
		h_lblEmail.setBounds(373, 118, 57, 14);
		managerProfilePanel.add(h_lblEmail);

		h_tf_email = new JTextField();
		h_tf_email.setEnabled(false);
		h_tf_email.setBounds(452, 115, 134, 20);
		managerProfilePanel.add(h_tf_email);
		h_tf_email.setColumns(10);

		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setBounds(466, 181, 120, 14);
		managerProfilePanel.add(lblChangePassword);

		JLabel h_lblNewPassword = new JLabel("New Password");
		h_lblNewPassword.setBounds(354, 205, 86, 14);
		managerProfilePanel.add(h_lblNewPassword);

		JLabel h_lblConfirmPassword = new JLabel("Confirm Password");
		h_lblConfirmPassword.setBounds(354, 247, 88, 14);
		managerProfilePanel.add(h_lblConfirmPassword);

		JButton btnUpdate = new JButton("Change my informations");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserBusiness proxy = new UserBusiness();
				if (!enabled) {
					h_tf_password.setText("");
					h_tf_paswwordConfirm.setText("");
					h_tf_firstname.setEnabled(true);
					h_tf_lastname.setEnabled(true);
					h_tf_address.setEnabled(true);
					h_tf_phone.setEnabled(true);
					h_tf_email.setEnabled(true);
					h_tf_password.setEnabled(true);
					h_tf_paswwordConfirm.setEnabled(true);
					enabled = true;

				} else {
					Boolean isValid = true;

					String firstname = h_tf_firstname.getText();
					String lastname = h_tf_lastname.getText();
					String address = h_tf_address.getText();
					mail = h_tf_email.getText();
					if ((h_tf_firstname.getText().trim().length() == 0)
							|| (h_tf_lastname.getText().trim().length() == 0)
							|| (h_tf_phone.getText().trim().length() == 0)
							|| (h_tf_address.getText().trim().length() == 0)
							|| (h_tf_email.getText().trim().length() == 0)
							|| (h_tf_password.getText().trim().length() == 0)
							|| (h_tf_paswwordConfirm.getText().trim().length() == 0)) {
						JDialog dialog2 = new JDialog();
						dialog2.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog2, "You should fill all fields");
						return;
					}
					if ((h_tf_password.getText().trim().length()) <= 3) {
						JDialog dialog2 = new JDialog();
						dialog2.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog2, "Password must be longer than 3 caracters");
						return;
					}

					// int phone = Integer.parseInt(h_tf_phone.getText());
					if (!CheckMail(mail)) {

						JDialog dialog2 = new JDialog();
						dialog2.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog2, "Please enter a valid mail");
						return;
					}
					if (h_tf_phone.getText().trim().length() == 0 || h_tf_phone.getText().trim().length() < 8) {
						JDialog dialog2 = new JDialog();
						dialog2.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog2, "Please enter your phone number");
						return;

					}
					try {
						if ((proxy.getProxy().checkMailExistance(mail)) && (!proxy.getProxy().findByEmail(mail)
								.getUsername().equals(Authentification.userConnected.getUsername()))) {
							JDialog dialog2 = new JDialog();
							dialog2.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(dialog2, "The email is already used");
							return;
						}
					} catch (HeadlessException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SecurityException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (NamingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					String pass1 = h_tf_password.getText();
					String pass2 = h_tf_paswwordConfirm.getText();

					if ((!h_tf_password.getText().equals(h_tf_paswwordConfirm.getText()))
							|| h_tf_password.getText().trim().length() == 0) {
						// lbl_password.setVisible(true);
						isValid = false;
						JDialog dialog2 = new JDialog();
						dialog2.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog2, "Passwords must match");
						return;
					} else {
						// lbl_password.setVisible(false);
					}

					if (isValid) {
						User u = Authentification.userConnected;

						u.setFirstname(firstname);
						u.setLastname(lastname);

						u.setPhone(Integer.parseInt(h_tf_phone.getText()));
						u.setAddress(address);
						u.setEmail(mail);

						u.setPassword(h_tf_password.getText());

						if (UserDelegate.updateUser(u)) {
							System.out.println(u);
							Authentification.userConnected = u;
							JDialog dialog = new JDialog();
							dialog.setAlwaysOnTop(true);

							JOptionPane.showMessageDialog(dialog, "information updated");
						}

						Authentification.userConnected = u;

						h_tf_firstname.setEnabled(false);
						h_tf_lastname.setEnabled(false);
						h_tf_address.setEnabled(false);
						h_tf_email.setEnabled(false);
						h_tf_phone.setEnabled(false);
						h_tf_password.setEnabled(false);
						h_tf_paswwordConfirm.setEnabled(false);
						enabled = false;
					}

				}
			}
		});
		btnUpdate.setBounds(272, 282, 185, 23);
		managerProfilePanel.add(btnUpdate);

		h_tf_password = new JPasswordField();
		h_tf_password.setEnabled(false);
		h_tf_password.setBounds(452, 202, 134, 20);
		managerProfilePanel.add(h_tf_password);

		h_tf_paswwordConfirm = new JPasswordField();
		h_tf_paswwordConfirm.setEnabled(false);
		h_tf_paswwordConfirm.setBounds(452, 244, 134, 20);
		managerProfilePanel.add(h_tf_paswwordConfirm);

		setTextFields();
		i_label.setBounds(0, 0, 768, 420);
		ManagerGUI.getContentPane().add(i_label);

		JLabel lblStoreName = new JLabel("Store name :");
		lblStoreName.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblStoreName.setBounds(10, 27, 99, 25);
		Stores.add(lblStoreName);

		storeName = new JTextField();
		storeName.setColumns(10);
		storeName.setBounds(139, 31, 149, 20);
		Stores.add(storeName);

		JLabel label_51 = new JLabel("");
		label_51.setForeground(Color.RED);
		label_51.setBounds(10, 24, 763, 316);
		Stores.add(label_51);

		JLabel lblPhoneStore = new JLabel("Phone");
		lblPhoneStore.setBounds(10, 153, 89, 14);
		Stores.add(lblPhoneStore);

		JLabel lblEmailStore = new JLabel("Email :");
		lblEmailStore.setBounds(237, 153, 51, 14);
		Stores.add(lblEmailStore);

		phoneStore = new JTextField();
		phoneStore.setBounds(139, 150, 89, 20);
		Stores.add(phoneStore);
		phoneStore.setColumns(10);

		emailStore = new JTextField();
		emailStore.setBounds(282, 150, 132, 20);
		Stores.add(emailStore);
		emailStore.setColumns(10);

		resortStore = new JComboBox();
		resortStore.setBounds(139, 201, 99, 20);
		Stores.add(resortStore);

		JLabel lblStoreDesc = new JLabel("Description :");
		lblStoreDesc.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblStoreDesc.setBounds(10, 60, 99, 25);
		Stores.add(lblStoreDesc);

		JButton addStore = new JButton("Add");
		addStore.setFont(new Font("Dialog", Font.PLAIN, 16));
		addStore.setBounds(39, 288, 99, 23);
		Stores.add(addStore);
		addStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store r = new Store();
				int testPH = 0, testEm = 0;
				try {
					r.setResort(ResortBusinessDelegate.findAllResorts().get(resortStore.getSelectedIndex()));
					for (int i = 0; i < StoreBusinessDelegate.findAllStores().size(); i++) {
						if (StoreBusinessDelegate.findAllStores().get(i).getEmail().equals(emailStore.getText()))
							testEm = 1;
					}

					if ((storeName.getText().length() == 0) || (storeDescription.getText().length() == 0)
							|| (storeLocation.getText().length() == 0) || (emailStore.getText().length() == 0)
							|| (phoneStore.getText().length() == 0) || (testEm == 1)) {

						if (storeName.getText().length() == 0)
							lblStoreName.setForeground(Color.RED);
						if (storeDescription.getText().length() == 0)
							lblStoreDesc.setForeground(Color.RED);
						if (storeLocation.getText().length() == 0)
							lblStoreAdress.setForeground(Color.RED);
						if (emailStore.getText().length() == 0)
							lblEmailStore.setForeground(Color.RED);
						if (phoneStore.getText().length() == 0)
							lblPhoneStore.setForeground(Color.RED);
						else {

							for (int i = 0; i < StoreBusinessDelegate.findAllStores().size(); i++) {
								if (StoreBusinessDelegate.findAllStores().get(i).getPhone() == Long
										.parseLong(phoneStore.getText()))
									lblAlreadyExistPhR.setVisible(true);
							}
						}
						if (testEm == 1)
							lblAlreadyExistEmR.setVisible(true);
					} else {
						r.setDescription(storeDescription.getText());
						r.setEmail(emailStore.getText());
						r.setLocation(storeLocation.getText());
						r.setName(storeName.getText());
						r.setPhone(Long.parseLong(phoneStore.getText()));
						r.setResort(ResortBusinessDelegate.findAllResorts().get(resortStore.getSelectedIndex()));
						StoreBusinessDelegate.addStore(r);
						StoreModel storeModel = new StoreModel();
						storeTable.setModel(storeModel.getStoreModel());
						lblStoreName.setForeground(Color.BLACK);
						lblStoreDesc.setForeground(Color.BLACK);
						lblStoreAdress.setForeground(Color.BLACK);
						lblEmailStore.setForeground(Color.BLACK);
						lblPhoneStore.setForeground(Color.BLACK);
						lblAlreadyExistEmR.setVisible(false);
						lblAlreadyExistPhR.setVisible(false);
						storeName.setText("");
						storeDescription.setText("");
						phoneStore.setText("");
						storeLocation.setText("");
						emailStore.setText("");
					}
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		storeLocation = new JTextField();
		storeLocation.setColumns(10);
		storeLocation.setBounds(139, 244, 227, 20);
		Stores.add(storeLocation);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(516, 187, 150, 130);
		Stores.add(label_3);

		JScrollPane storeScroll = new JScrollPane();
		storeScroll.setBounds(424, 11, 329, 253);
		Stores.add(storeScroll);
		storeScroll.setViewportView(storeTable);
		StoreModel storeModel = new StoreModel();
		storeTable.setModel(storeModel.getStoreModel());
		JButton editStore = new JButton("Edit");
		editStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		editStore.setFont(new Font("Dialog", Font.PLAIN, 16));
		editStore.setBounds(183, 288, 89, 23);
		Stores.add(editStore);

		JButton removeStore = new JButton("Remove");
		removeStore.setFont(new Font("Dialog", Font.PLAIN, 16));
		removeStore.setBounds(321, 288, 89, 23);
		Stores.add(removeStore);

		resortStore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// try {
				// //fillCbResort();
				// } catch (NamingException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
			}
		});
		JLabel lblResort_Store = new JLabel("Resort");
		lblResort_Store.setBounds(10, 204, 51, 14);
		Stores.add(lblResort_Store);

		Stores.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { storeDescription, lblStoreName, storeName, lblStoreDesc, addStore, storeLocation,
						label_3, storeScroll, editStore, removeStore, lblStoreAdress, label_51 }));
		storeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = storeTable.getSelectedRow();
				StoreModel rt;

				try {
					rt = new StoreModel();
					rowSelectedStore = rt.getStoreList().get(index);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				storeName.setText(rowSelectedStore.getName());
				storeDescription.setText(rowSelectedStore.getDescription());
				storeLocation.setText(rowSelectedStore.getLocation());
				phoneStore.setText(Long.toString(rowSelectedStore.getPhone()));
				emailStore.setText(rowSelectedStore.getEmail());
				addStore.setEnabled(false);
			}
		});
		editStore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rowSelectedStore == null) {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Please select a Store to edit");

				} else {
					Store r = new Store();
					try {

						if ((storeName.getText().length() == 0) || (storeDescription.getText().length() == 0)
								|| (storeLocation.getText().length() == 0) || (emailStore.getText().length() == 0)
								|| (phoneStore.getText().length() == 0)) {

							if (storeName.getText().length() == 0)
								lblStoreName.setForeground(Color.RED);
							if (storeDescription.getText().length() == 0)
								lblStoreDesc.setForeground(Color.RED);
							if (storeLocation.getText().length() == 0)
								lblStoreAdress.setForeground(Color.RED);
							if (emailStore.getText().length() == 0)
								lblEmailStore.setForeground(Color.RED);
							if (phoneStore.getText().length() == 0)
								lblPhoneStore.setForeground(Color.RED);

						} else {
							r.setIdStore(rowSelectedStore.getIdStore());
							r.setName(storeName.getText());
							r.setDescription(storeDescription.getText());
							r.setResort(rowSelectedStore.getResort());
							r.setLocation(storeLocation.getText());
							r.setEmail(emailStore.getText());
							r.setPhone(Long.parseLong(phoneStore.getText()));
							r.setResort(ResortBusinessDelegate.findAllResorts().get(resortStore.getSelectedIndex()));
							StoreBusinessDelegate.updateStore(r);
							StoreModel storemodel = new StoreModel();
							storeTable.setModel(storemodel.getStoreModel());
							addStore.setEnabled(true);
							storeName.setText("");
							storeDescription.setText("");
							storeLocation.setText("");
							phoneStore.setText("");
							emailStore.setText("");
							lblStoreName.setForeground(Color.BLACK);
							lblStoreDesc.setForeground(Color.BLACK);
							lblStoreAdress.setForeground(Color.BLACK);
							lblEmailStore.setForeground(Color.BLACK);
							lblPhoneStore.setForeground(Color.BLACK);

						}
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		removeStore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rowSelectedStore == null) {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Please select a Store to remove");
				} else {
					Store r = new Store();
					StoreBusinessDelegate rdb = new StoreBusinessDelegate();
					r.setIdStore(rowSelectedStore.getIdStore());
					r = rowSelectedStore;
					try {
						StoreBusinessDelegate.removeStore(r);
						StoreModel storemodel = new StoreModel();
						storeTable.setModel(storemodel.getStoreModel());
						addStore.setEnabled(true);
						storeName.setText("");
						storeDescription.setText("");
						storeLocation.setText("");
						phoneStore.setText("");
						emailStore.setText("");
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		hotelmodel.fillResortComboBox(i_hotel_combo);
		pistemodel.fillResortComboBox(i_pi_resortcombo);
	}

	public void fillCbResort() throws NamingException {

		List<Resort> list = new ArrayList<>();
		list = ResortBusinessDelegate.findAllResorts();
		for (Resort name : list)
			resortStore.addItem(name.getName());

	}
}
