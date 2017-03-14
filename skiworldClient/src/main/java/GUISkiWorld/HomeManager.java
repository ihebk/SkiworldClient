package GUISkiWorld;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.xml.bind.JAXBException;

import business.HotelDelegate;
import business.PisteDelegate;
import business.ResortBusiness;
import business.TransportDelegate;
import entities.Hotel;
import entities.Piste;
import entities.Transport;
import models.HotelModel;
import models.PisteModel;
import models.TransportModel;
import skiworldClient.SMS;
import tk.plogitech.darksky.forecast.ForecastException;

public class HomeManager {

	private JFrame ManagerGUI;
	private JTextField i_hotelname;
	private JTextField i_imagepath;
	private String image ;
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
	ResortBusiness resortBusiness = new ResortBusiness();
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
		ManagerGUI.setBounds(100, 100, 784, 459);
		ManagerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManagerGUI.getContentPane().setLayout(null);
		ManagerGUI.getContentPane().setLayout(null);

		//SMS.main(null, "+21624056027", "hello");
//		YahooWeatherService service = new YahooWeatherService();
//		Channel channel = service.getForecast("2502265", DegreeUnit.CELSIUS);
//		System.out.println(channel.getDescription());
//		System.out.println(channel.getWind());
//		System.out.println(channel.getAstronomy());
		
//			ForecastRequest request = new ForecastRequestBuilder()
//		        .key(new APIKey("ad7177e55a8be03097509cb51411cd08"))
//		        .time(Instant.now().minus(5, ChronoUnit.DAYS))
//		        .language(ForecastRequestBuilder.Language.de)
//		        .units(ForecastRequestBuilder.Units.us)
//		        .exclude(ForecastRequestBuilder.Block.minutely)
//		        .extendHourly()
//		        .location(new GeoCoordinates(new Longitude(13.377704), new Latitude(52.516275))).build();
//
//		    DarkSkyClient client = new DarkSkyClient();
//		    String forecast = client.forecastJsonString(request);
//		    System.out.println(forecast);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 76, 768, 344);
		ManagerGUI.getContentPane().add(tabbedPane);

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
		i_pi_resortcombo.setBounds(139, 238, 113, 20);

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
				try {
					hotel.setResort(
							resortBusiness.getProxy().findResortByLabel(i_hotel_combo.getSelectedItem().toString()));
				} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

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
					
					image= "src//main//resources//imgs//defaulthotel.jpg";
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

		JLabel i_imgdisplay = new JLabel("");
		i_imgdisplay.setBounds(516, 187, 150, 130);
		i_imgdisplay.setIcon((Icon) img_display);
		panel_i_hotel.add(i_imgdisplay);

		JScrollPane i_scrollPane = new JScrollPane();

		i_scrollPane.setBounds(424, 11, 329, 165);
		panel_i_hotel.add(i_scrollPane);

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
					try {
						hotel.setResort(resortBusiness.getProxy()
								.findResortByLabel(i_hotel_combo.getSelectedItem().toString()));
					} catch (NamingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

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
						SMS.main(null, "+21624056027", "The hotel named "+hotelrow.getName().toUpperCase()+" was removed ! :)");



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
				try {
					transport.setResort(resortBusiness.getProxy()
							.findResortByLabel(i_tr_resort_combo.getSelectedItem().toString()));
				} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

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
					
					image= "src//main//resources//imgs//defaultTr.jpg";
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
					try {
						transport.setResort(resortBusiness.getProxy()
								.findResortByLabel(i_tr_resort_combo.getSelectedItem().toString()));
					} catch (NamingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

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
				try {
					piste.setResort(
							resortBusiness.getProxy().findResortByLabel(i_pi_resortcombo.getSelectedItem().toString()));
				} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

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
					
					image= "src//main//resources//imgs//defaultpiste.jpg";
					i_pi_path.setText(image);
				}

			
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
					try {
						piste.setResort(resortBusiness.getProxy()
								.findResortByLabel(i_hotel_combo.getSelectedItem().toString()));
					} catch (NamingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

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
						SMS.main(null, "+21624056027", "The Piste named "+pirow.getName().toUpperCase()+" was removed ! :)");


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

		i_label.setBounds(0, 0, 768, 420);
		ManagerGUI.getContentPane().add(i_label);

	}
}
