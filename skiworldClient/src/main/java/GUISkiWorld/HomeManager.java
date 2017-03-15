package GUISkiWorld;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import java.awt.Image;
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
import java.awt.Dimension;
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
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.hibernate.cache.CacheException;
import org.joda.time.LocalDate;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.toedter.calendar.JDateChooser;
import Model.PublicationModel;
import Model.TrainingModel;
import business.EventBusiness;
import business.ResortBusinessDelegate;
import business.TrainingBusiness;
import contracts.EventCrudRemote;
import contracts.ResortCrudEJBRemote;
import contracts.TrainingCrudRemote;
import entities.Event;
import entities.Resort;
import entities.Training;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import maps.java.StaticMaps;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import contracts.EquipementCrudEJBRemote;
import javax.swing.event.ChangeEvent;
import entities.Clothes;
import entities.Equipments;
import javax.swing.table.DefaultTableModel;
import business.ClothBusiness;
import business.EquipementBusiness;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;
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
import models.ClotheModel;
import models.EquipementModel;
import models.HotelModel;
import models.PisteModel;
import models.StoreModel;
import models.TransportModel;
import skiworldClient.SMS;
import tk.plogitech.darksky.forecast.ForecastException;

public class HomeManager {

	private JFrame ManagerGUI;
	private JTextField EventName;
	private JTable EventTable = new JTable();
	public Event RowSelectedEvent = null;
	public Training RowSelectedTraining = null;
	private JTextField Filter_Event;
	private JTextField EventNameShow;
	PublicationModel pubicationModel = new PublicationModel();
	TrainingModel trainingModel = new TrainingModel();
	private JTextField TrainingName;
	private JTextField TrainingNameShow;
	private JTable TrainingTable;
	private JTextField Filter_Trainings;
	private JTextField EventPicturePath;
	private String EventImage;
	private JTextField txtName;
	private JTextField txtDesc;
	private JTextField txtPrice;
	private JTextField txtDeal;
	private JTextField txtType;
	private JTextField txtNameClth;
	private JTextField txtDescClth;
	private JTextField txtPriceClth;
	private JTextField txtDealClth;
	private JTextField txtTypeClth;
	private StaticMaps ObjStaticMaps = new StaticMaps();
	private JComboBox JCombo_ME_Formato = new JComboBox();
	private JComboBox cbx_lib;
	private JComboBox JCombo_ME_TipoMapa = new JComboBox();
	private JComboBox cbxStore = new JComboBox();
	private JTextField JText_ME_Zoom;
	private JTextField JText_ME_Escala;
	private JLabel JLabel_ME_Imagen = new JLabel("");
	private String imageEqp;
	/******************** Table ***************************/
	private JTable tableEqp;

	/******************** Model ****************************/
	EquipementModel equipementModel = new EquipementModel();
	ClotheModel clotheModel = new ClotheModel();
	/******************** Business ***************************/

	/******************* Row **************************/
	public Equipments rowEquipments = null;
	public Clothes rowClothes = null;
	/****************************************************/
	public static String pathImgEqp;
	public static String pathImgClth;
	public static String pathImgOut;
	public File f;
	private JTextField i_imagepath;
	private JLabel lblImg = new JLabel("");
	private String ImgPath = null;
	private JLabel RequiredFieldName;
	private JLabel RequiredFieldDesc;
	private JLabel RequiredFieldType;
	private JLabel RequiredFieldPrice;
	private JLabel RequiredFieldDeal;
	private JTextField i_imagepathClth;
	private JTextField storeName;
	private JTextField storeLocation;
	private JTextField phoneStore;
	private JTextField emailStore;
	private JComboBox<String> resortStore;
	public Store rowSelectedStore;
	public List<Resort> listStore;
	private JTable storeTable;
	private JTextField i_hotelname;
	private JTextField i_imagepathf;
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
	private JLabel lblNewLabel_desc;
	private JScrollPane scrollPaneMap;
	private JTable tableClth;

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
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws NamingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JAXBException
	 * @throws ForecastException
	 */

	public HomeManager() throws NamingException, SQLException, IOException, JAXBException, ForecastException {
		initialize();
		tableClth.setModel(clotheModel.getClothesModel());
		tableEqp.setModel(equipementModel.getEquipmentsModel());
		EventTable.setModel(pubicationModel.getEventModel());
		fillCbResort();
	}

	private void partageFacebook(String m, String url) throws SQLException, IOException, NamingException {

		String accesstoken = "EAACEdEose0cBAI61nuNc8qOxBTTZCfQMSd95MM3ipr6e8ZBwQJSZB8yyZBvmX0qFB6ZBiwNTJhvvth8wPZCZAM387GZA3q44JzFPkoqJcSmBqppeYvSIWm71ZAFYt3JH0vkVwIo0j6z6gE8AHElTR9nSO7xoIZBcm6XHZBWTQ4xcZA2JSTDPDMjwvDDUHgHyHgxijysZD";
		FacebookClient fbclient = new DefaultFacebookClient(accesstoken);

		FileInputStream f = new FileInputStream(new File(url));

		fbclient.publish("me/photos", FacebookType.class, BinaryAttachment.with("limitless.jpg", f),
				Parameter.with("message", m));

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws NamingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
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
		ManagerGUI.setBounds(100, 100, 901, 698);
		ManagerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManagerGUI.getContentPane().setLayout(null);
		JPanel header = new JPanel();
		header.setBounds(0, 0, 885, 76);
		ManagerGUI.getContentPane().add(header);
		header.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		lblNewLabel_2.setBounds(0, 0, 885, 76);
		header.add(lblNewLabel_2);
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
		tabbedPane.setBounds(0, 76, 885, 583);
		ManagerGUI.getContentPane().add(tabbedPane);
		EventTable = new JTable();
		JPanel Store = new JPanel();
		tabbedPane.addTab("Store", null, Store, null);
		Store.setLayout(null);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 875, 555);
		Store.add(tabbedPane_1);
		JPanel StoreEquipments = new JPanel();
		tabbedPane_1.addTab("Equipments", null, StoreEquipments, null);
		tableEqp = new JTable();
		StoreEquipments.setLayout(null);
		JLabel RequiredFieldType = new JLabel("Required Field");
		RequiredFieldType.setBounds(135, 204, 155, 14);
		RequiredFieldType.setForeground(Color.RED);
		StoreEquipments.add(RequiredFieldType);
		i_pi_table = new JTable();
		JLabel RequiredFieldDeal = new JLabel("Required Field");
		RequiredFieldDeal.setBounds(135, 161, 155, 14);
		RequiredFieldDeal.setForeground(Color.RED);
		StoreEquipments.add(RequiredFieldDeal);

		JLabel RequiredFieldName = new JLabel("Required Field");
		RequiredFieldName.setBounds(135, 30, 155, 14);
		RequiredFieldName.setForeground(Color.RED);
		StoreEquipments.add(RequiredFieldName);

		JLabel RequiredFieldDesc = new JLabel("Required Field");
		RequiredFieldDesc.setBounds(135, 74, 155, 14);
		RequiredFieldDesc.setForeground(Color.RED);
		StoreEquipments.add(RequiredFieldDesc);

		JLabel RequiredFieldPrice = new JLabel("Required Field");
		RequiredFieldPrice.setBounds(135, 118, 155, 14);
		RequiredFieldPrice.setForeground(Color.RED);
		StoreEquipments.add(RequiredFieldPrice);

		RequiredFieldName.setVisible(false);
		RequiredFieldDesc.setVisible(false);
		RequiredFieldPrice.setVisible(false);
		RequiredFieldType.setVisible(false);
		RequiredFieldDeal.setVisible(false);
		JPanel StoreClothes = new JPanel();
		tabbedPane_1.addTab("Clothes", null, StoreClothes, null);
		StoreClothes.setLayout(null);
		JLabel RequiredFieldNameClth = new JLabel("RequiredField");
		RequiredFieldNameClth.setBounds(136, 30, 158, 14);
		RequiredFieldNameClth.setForeground(Color.RED);
		StoreClothes.add(RequiredFieldNameClth);
		JButton btnAddClothFK = new JButton("Add");
		btnAddClothFK.setBounds(398, 419, 89, 23);
		StoreClothes.add(btnAddClothFK);

		JLabel RequiredFieldDescClth = new JLabel("RequiredField");
		RequiredFieldDescClth.setBounds(136, 83, 158, 14);
		RequiredFieldDescClth.setForeground(Color.RED);
		StoreClothes.add(RequiredFieldDescClth);

		JLabel RequiredFieldPriceClth = new JLabel("RequiredField");
		RequiredFieldPriceClth.setBounds(136, 128, 158, 14);
		RequiredFieldPriceClth.setForeground(Color.RED);
		StoreClothes.add(RequiredFieldPriceClth);

		JLabel RequiredFieldDealClth = new JLabel("RequiredField");
		RequiredFieldDealClth.setBounds(136, 172, 158, 14);
		RequiredFieldDealClth.setForeground(Color.RED);
		StoreClothes.add(RequiredFieldDealClth);

		JLabel RequiredFieldTypeClth = new JLabel("RequiredField");
		RequiredFieldTypeClth.setBounds(136, 221, 158, 14);
		RequiredFieldTypeClth.setForeground(Color.RED);
		StoreClothes.add(RequiredFieldTypeClth);
		RequiredFieldNameClth.setVisible(false);
		RequiredFieldDescClth.setVisible(false);
		RequiredFieldPriceClth.setVisible(false);
		RequiredFieldTypeClth.setVisible(false);
		RequiredFieldDealClth.setVisible(false);
		JLabel lblName_1 = new JLabel("Name *");
		lblName_1.setBounds(36, 14, 46, 14);
		StoreClothes.add(lblName_1);

		JLabel lblDescription = new JLabel("Description *");
		lblDescription.setBounds(36, 64, 77, 14);
		StoreClothes.add(lblDescription);

		JLabel lblPrice_1 = new JLabel("Price *");
		lblPrice_1.setBounds(36, 111, 77, 14);
		StoreClothes.add(lblPrice_1);

		JLabel lblDeal_1 = new JLabel("Deal *");
		lblDeal_1.setBounds(36, 156, 77, 14);
		StoreClothes.add(lblDeal_1);

		JLabel lblType_2 = new JLabel("Type *");
		lblType_2.setBounds(36, 205, 77, 14);
		StoreClothes.add(lblType_2);

		txtNameClth = new JTextField();
		txtNameClth.setBounds(136, 11, 158, 20);
		txtNameClth.setColumns(10);
		StoreClothes.add(txtNameClth);

		txtDescClth = new JTextField();
		txtDescClth.setBounds(136, 61, 158, 20);
		txtDescClth.setColumns(10);
		StoreClothes.add(txtDescClth);

		txtPriceClth = new JTextField();
		txtPriceClth.setBounds(136, 108, 158, 20);
		txtPriceClth.setColumns(10);
		StoreClothes.add(txtPriceClth);

		txtDealClth = new JTextField();
		txtDealClth.setBounds(136, 153, 158, 20);
		txtDealClth.setColumns(10);
		StoreClothes.add(txtDealClth);

		txtTypeClth = new JTextField();
		txtTypeClth.setBounds(136, 202, 158, 20);
		txtTypeClth.setColumns(10);
		StoreClothes.add(txtTypeClth);

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
		lblHotelName_i.setBounds(137, 65, 99, 25);
		lblHotelName_i.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JTextArea i_hoteldescription = new JTextArea();
		i_hoteldescription.setBounds(266, 100, 204, 69);
		panel_i_hotel.add(i_hoteldescription);
		JSpinner i_tr_price = new JSpinner();
		i_tr_price.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
		i_tr_price.setBounds(192, 268, 123, 20);

		i_hotelname = new JTextField();
		i_hotelname.setBounds(266, 69, 149, 20);
		i_hotelname.setColumns(10);

		JComboBox i_tr_type_combo = new JComboBox();
		i_tr_type_combo.setModel(new DefaultComboBoxModel(new String[] { "Car", "JetSki", "Sky tram" }));
		i_tr_type_combo.setBounds(192, 76, 204, 20);

		JComboBox i_hotel_combo = new JComboBox();
		i_hotel_combo.setBounds(266, 276, 113, 20);
		panel_i_hotel.add(i_hotel_combo);
		JComboBox i_tr_resort_combo = new JComboBox();
		i_tr_resort_combo.setBounds(192, 299, 113, 20);
		JComboBox i_pi_resortcombo = new JComboBox();

		i_table_hotel = new JTable();
		i_pi_table = new JTable();
		i_tr_table = new JTable();
		JButton UpdateEvent = new JButton("UPDATE");
		i_table_hotel.setModel(hotelmodel.hotelModel());
		i_tr_table.setModel(transportmodel.transportModel());
		i_pi_table.setModel(pistemodel.pisteModel());

		hotelmodel.fillResortComboBox(i_hotel_combo);
		pistemodel.fillResortComboBox(i_pi_resortcombo);
		transportmodel.fillResortComboBox(i_tr_resort_combo);

		JLabel i_lblDescription = new JLabel("Description :");
		i_lblDescription.setBounds(137, 98, 99, 25);
		i_lblDescription.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JLabel i_lblCapacity = new JLabel("Capacity :");
		i_lblCapacity.setBounds(137, 182, 99, 25);
		i_lblCapacity.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JSpinner i_hotelcapacity = new JSpinner();
		i_hotelcapacity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		i_hotelcapacity.setBounds(266, 186, 123, 20);

		JLabel i_lblRating = new JLabel("Image :");
		i_lblRating.setBounds(137, 224, 99, 25);
		i_lblRating.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JButton i_btnNewButton = new JButton("Add");
		i_btnNewButton.setBounds(164, 364, 99, 23);
		i_btnNewButton.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		panel_i_hotel.setLayout(null);
		panel_i_hotel.add(lblHotelName_i);
		panel_i_hotel.add(i_hotelname);
		panel_i_hotel.add(i_lblDescription);
		panel_i_hotel.add(i_lblCapacity);
		panel_i_hotel.add(i_hotelcapacity);
		panel_i_hotel.add(i_lblRating);
		panel_i_hotel.add(i_btnNewButton);

		JButton i_chooseimage = new JButton("choose image");

		i_chooseimage.setBounds(266, 224, 113, 23);
		panel_i_hotel.add(i_chooseimage);

		i_imagepath = new JTextField();
		i_imagepath.setEditable(false);
		i_imagepath.setBounds(388, 225, 148, 20);
		panel_i_hotel.add(i_imagepath);
		i_imagepath.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(424, 11, 329, 165);
		JLabel i_imgdisplay = new JLabel("");
		i_imgdisplay.setBounds(598, 219, 150, 130);
		i_imgdisplay.setIcon((Icon) img_display);
		panel_i_hotel.add(i_imgdisplay);

		table = new JTable();
		JScrollPane i_scrollPane = new JScrollPane();

		i_scrollPane.setBounds(507, 49, 329, 165);
		panel_i_hotel.add(i_scrollPane);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Iheb\\Desktop\\15755113534_f75fd1636c_k.jpg"));
		lblNewLabel_1.setBounds(0, 0, 763, 316);

		JPanel managerProfilePanel = new JPanel();
		JLabel h_lbl_image = new JLabel("");
		h_lbl_image.setBounds(522, 46, 99, 96);

		tabbedPane.addTab("Profil", null, managerProfilePanel, null);

		i_scrollPane.setViewportView(i_table_hotel);

		JButton i_edit = new JButton("Edit");
		

		i_edit.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_edit.setBounds(308, 364, 89, 23);
		panel_i_hotel.add(i_edit);

		JButton i_remove = new JButton("Remove");
		
		i_remove.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_remove.setBounds(446, 364, 89, 23);
		panel_i_hotel.add(i_remove);

		JLabel lblResort = new JLabel("Resort :");
		lblResort.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		lblResort.setBounds(137, 272, 99, 25);
		panel_i_hotel.add(lblResort);

		JLabel i_background = new JLabel("New label");
		i_background.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		i_background.setBounds(0, 0, 880, 555);
		panel_i_hotel.add(i_background);

		JPanel panel_i_trans = new JPanel();
		panel_i_trans.setLayout(null);
		tabbedPane.addTab("Manage Transport", null, panel_i_trans, null);

		JTextArea i_tr_descriptiont = new JTextArea();
		i_tr_descriptiont.setBounds(192, 107, 204, 69);
		panel_i_trans.add(i_tr_descriptiont);

		JLabel i_type = new JLabel("Type :");
		i_type.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_type.setBounds(63, 72, 99, 25);
		panel_i_trans.add(i_type);

		JLabel i_desc = new JLabel("Description :");
		i_desc.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_desc.setBounds(63, 121, 99, 25);
		panel_i_trans.add(i_desc);

		JLabel i_lb = new JLabel("Capacity :");
		i_lb.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_lb.setBounds(63, 187, 99, 25);
		panel_i_trans.add(i_lb);

		JSpinner i_tr_cap = new JSpinner();
		i_tr_cap.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		i_tr_cap.setBounds(192, 187, 123, 20);
		panel_i_trans.add(i_tr_cap);

		JLabel i_label_3 = new JLabel("Image :");
		i_label_3.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_label_3.setBounds(63, 217, 99, 25);
		panel_i_trans.add(i_label_3);

		panel_i_trans.add(i_tr_resort_combo);
		panel_i_trans.add(i_tr_type_combo);

		JButton i_tr_add = new JButton("Add");
		
		i_tr_add.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_tr_add.setBounds(92, 379, 99, 23);
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
		i_tr_choose_img.setBounds(192, 218, 113, 23);
		panel_i_trans.add(i_tr_choose_img);

		i_tr_path = new JTextField();
		i_tr_path.setEditable(false);
		i_tr_path.setColumns(10);
		i_tr_path.setBounds(315, 221, 148, 20);
		panel_i_trans.add(i_tr_path);
		panel_i_trans.add(i_tr_price);

		JLabel i_tr_image = new JLabel("");
		i_tr_image.setBounds(569, 248, 150, 130);
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
		i_tr_edit.setBounds(236, 379, 89, 23);
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
		i_tr_remove.setBounds(374, 379, 89, 23);
		panel_i_trans.add(i_tr_remove);

		JLabel label_5 = new JLabel("Resort :");
		label_5.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		label_5.setBounds(63, 295, 99, 25);
		panel_i_trans.add(label_5);

		JScrollPane i_tr_scrollPane = new JScrollPane();
		i_tr_scrollPane.setBounds(473, 72, 333, 157);
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
		i_lblBookingprice.setBounds(63, 264, 119, 25);
		panel_i_trans.add(i_lblBookingprice);

		JLabel i_lblDt = new JLabel("$");
		i_lblDt.setFont(new Font("Source Sans Pro", Font.BOLD, 16));
		i_lblDt.setBounds(321, 269, 46, 14);
		panel_i_trans.add(i_lblDt);

		JLabel i_backg = new JLabel("");
		i_backg.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		i_backg.setBounds(0, 0, 880, 555);
		panel_i_trans.add(i_backg);

		storeTable = new JTable();
		JPanel Trainingpanel = new JPanel();
		Trainingpanel.setLayout(null);
		tabbedPane.addTab("TRAINING", null, Trainingpanel, null);
		TrainingName = new JTextField();
		TrainingName.setColumns(10);
		TrainingName.setBounds(252, 61, 244, 35);
		Trainingpanel.add(TrainingName);
		JButton btnDelete_Clothe = new JButton("Delete");
		JButton btnAddEvent = new JButton("ADD EVENT");

		JTextPane txtpnTrainingName = new JTextPane();
		txtpnTrainingName.setText("TRAINING NAME");
		txtpnTrainingName.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		txtpnTrainingName.setBounds(84, 61, 139, 25);
		Trainingpanel.add(txtpnTrainingName);

		JTextArea TrainingDescription = new JTextArea();
		TrainingDescription.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		TrainingDescription.setBounds(252, 107, 244, 70);
		Trainingpanel.add(TrainingDescription);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("DESCRIPTION");
		textPane_1.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		textPane_1.setBounds(94, 112, 111, 25);
		Trainingpanel.add(textPane_1);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("STARTING DATE");
		textPane_2.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		textPane_2.setBounds(94, 188, 121, 25);
		Trainingpanel.add(textPane_2);
	

		JDateChooser TrainingStartingDate = new JDateChooser();
		TrainingStartingDate.setDateFormatString("yyyy-MM-dd");
		TrainingStartingDate.setBounds(274, 188, 200, 25);
		Trainingpanel.add(TrainingStartingDate);

		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("ENDING DATE");
		textPane_3.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		textPane_3.setBounds(494, 188, 111, 25);
		Trainingpanel.add(textPane_3);

		JDateChooser TrainingEndingDate = new JDateChooser();
		TrainingEndingDate.setDateFormatString("yyyy-MM-dd");
		TrainingEndingDate.setBounds(607, 188, 200, 25);
		Trainingpanel.add(TrainingEndingDate);

		JComboBox TrainingResort = new JComboBox();
		TrainingResort.setBounds(607, 256, 158, 20);
		Trainingpanel.add(TrainingResort);

		JTextPane ResortCombo = new JTextPane();
		ResortCombo.setText("RESORT");
		ResortCombo.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		ResortCombo.setBounds(507, 251, 74, 25);
		Trainingpanel.add(ResortCombo);

		JComboBox TrainingType = new JComboBox();
		TrainingType.setModel(new DefaultComboBoxModel(new String[] { "BEGINNER", "EXTREME ", "FREESTYLE" }));
		TrainingType.setBounds(274, 251, 200, 20);
		JButton TrainingShare = new JButton("Share on Facebook");
		Trainingpanel.add(TrainingType);
		TrainingShare.setBounds(536, 272, 135, 27);
		panel.add(TrainingShare);
		TrainingTable = new JTable();
		JPanel Eventspanel = new JPanel();
		tabbedPane.addTab("EVENTS", null, Eventspanel, null);
		Eventspanel.setLayout(null);

		EventName = new JTextField();
		EventName.setBounds(198, 23, 244, 35);
		Eventspanel.add(EventName);
		EventName.setColumns(10);

		JTextPane txtpnEventName = new JTextPane();
		txtpnEventName.setEditable(false);
		txtpnEventName.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		txtpnEventName.setText("EVENT NAME");
		txtpnEventName.setBounds(58, 23, 111, 25);
		Eventspanel.add(txtpnEventName);

		JTextArea EventDescription = new JTextArea();
		EventDescription.setBounds(198, 69, 244, 70);
		Eventspanel.add(EventDescription);
		btnAddEvent.setBounds(572, 197, 89, 23);
		Eventspanel.add(btnAddEvent);

		EventTable.setBounds(10, 49, 399, 198);

		

		JTextPane txtpnDescription = new JTextPane();
		txtpnDescription.setEditable(false);
		txtpnDescription.setText("DESCRIPTION");
		txtpnDescription.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		txtpnDescription.setBounds(58, 69, 111, 25);
		Eventspanel.add(txtpnDescription);

		JTextPane txtpnStartingDate = new JTextPane();
		txtpnStartingDate.setText("STARTING DATE");
		txtpnStartingDate.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		txtpnStartingDate.setBounds(58, 150, 121, 25);
		Eventspanel.add(txtpnStartingDate);

		JDateChooser dateStart = new JDateChooser();
		dateStart.setDateFormatString("yyyy-MM-dd");
		dateStart.setBounds(220, 150, 200, 25);
		Eventspanel.add(dateStart);

		JTextPane txtpnEndingDate = new JTextPane();
		txtpnEndingDate.setText("ENDING DATE");
		txtpnEndingDate.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		txtpnEndingDate.setBounds(58, 197, 121, 25);
		Eventspanel.add(txtpnEndingDate);

		JDateChooser dateEnd = new JDateChooser();
		dateEnd.setDateFormatString("yyyy-MM-dd");
		dateEnd.setBounds(220, 197, 200, 25);
		Eventspanel.add(dateEnd);

		JComboBox EventType = new JComboBox();
		EventType.setModel(
				new DefaultComboBoxModel(new String[] { "FAMILY", "EXTREME SPORTS", "NIGHT PARTY", "SKI CHALLENGE" }));
		EventType.setBounds(220, 233, 200, 20);
		Eventspanel.add(EventType);

		JTextPane txtpnType = new JTextPane();
		txtpnType.setText("TYPE");
		txtpnType.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		txtpnType.setBounds(85, 233, 51, 25);
		Eventspanel.add(txtpnType);

		JComboBox EventResort = new JComboBox();
		EventResort.setBounds(584, 71, 142, 20);
		Eventspanel.add(EventResort);

		JPanel AllEventspanel = new JPanel();
		tabbedPane.addTab("All EVENTS", null, AllEventspanel, null);
		AllEventspanel.setLayout(null);
		EventNameShow = new JTextField();
		EventNameShow.setBounds(452, 14, 244, 30);
		AllEventspanel.add(EventNameShow);
		EventNameShow.setColumns(10);
		EventTable.setFont(new Font("Arial", Font.BOLD, 12));
		EventTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		AllEventspanel.add(EventTable);
		

		JDateChooser StartingDateShow = new JDateChooser();
		StartingDateShow.setDateFormatString("yyyy-MM-dd");
		StartingDateShow.setBounds(434, 170, 135, 25);
		AllEventspanel.add(StartingDateShow);

		JDateChooser EndingDateShow = new JDateChooser();
		EndingDateShow.setDateFormatString("yyyy-MM-dd");
		EndingDateShow.setBounds(590, 170, 149, 25);
		AllEventspanel.add(EndingDateShow);

		JTextArea EventDescriptionShow = new JTextArea();
		EventDescriptionShow.setBounds(452, 77, 244, 70);
		AllEventspanel.add(EventDescriptionShow);

		// pubicationModel.fillEventResortComboBox
		pubicationModel.fillEventResortComboBox(EventResort);
		

		JButton EventAddPicture = new JButton("ADD PICTURE");

		EventAddPicture.setBounds(463, 29, 104, 23);
		Eventspanel.add(EventAddPicture);

		EventPicturePath = new JTextField();
		EventPicturePath.setBounds(584, 30, 142, 20);
		Eventspanel.add(EventPicturePath);
		EventPicturePath.setColumns(10);
		
		JLabel r2_background = new JLabel("");
		r2_background.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		r2_background.setBounds(0, 0, 880, 555);
		Eventspanel.add(r2_background);
		panel.setBounds(710, 14, 1, 1);
		panel.setLayout(null);

		StoreClothes.add(btnAddClothFK);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(329, 14, 507, 373);
		StoreClothes.add(scrollPane_1);
		JLabel lblImgClth = new JLabel("");
		lblImgClth.setBounds(89, 293, 205, 88);
		StoreClothes.add(lblImgClth);
		tableClth = new JTable();
		JComboBox cbxStoreClth = new JComboBox();
		cbxStoreClth.setBounds(136, 246, 158, 20);
		StoreClothes.add(cbxStoreClth);

		JTextPane TrainingT = new JTextPane();
		TrainingT.setText("TYPE");
		TrainingT.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		TrainingT.setBounds(139, 251, 51, 25);
		Trainingpanel.add(TrainingT);
		trainingModel.fillTrainingResortComboBox(TrainingResort);
		JButton AddTraining = new JButton("ADD TRAINING");

		AddTraining.setBounds(307, 303, 131, 23);
		Trainingpanel.add(AddTraining);
		
		JLabel r_background = new JLabel("");
		r_background.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		r_background.setBounds(0, 0, 880, 555);
		Trainingpanel.add(r_background);
		JPanel panel_i_piste = new JPanel();
		panel_i_piste.setLayout(null);
		tabbedPane.addTab("Manage Pistes", null, panel_i_piste, null);

		JTextArea i_pi_description = new JTextArea();
		i_pi_description.setBounds(221, 132, 204, 69);
		panel_i_piste.add(i_pi_description);

		JLabel lblPisteName = new JLabel("Piste name :");
		lblPisteName.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		lblPisteName.setBounds(92, 97, 99, 25);
		panel_i_piste.add(lblPisteName);

		i_pi_name = new JTextField();
		i_pi_name.setColumns(10);
		i_pi_name.setBounds(221, 101, 149, 20);
		panel_i_piste.add(i_pi_name);
		JButton btnEdit_Clothe = new JButton("Edit");
		btnEdit_Clothe.setBounds(532, 419, 89, 23);
		StoreClothes.add(btnEdit_Clothe);
		JComboBox i_pi_typecombo = new JComboBox();
		i_pi_typecombo.setModel(new DefaultComboBoxModel(new String[] { "Training", "Daily Activities" }));
		i_pi_typecombo.setBounds(221, 218, 113, 20);
		panel_i_piste.add(i_pi_typecombo);

		JLabel i_pi_lblType = new JLabel("Type :");
		i_pi_lblType.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pi_lblType.setBounds(92, 214, 99, 25);
		panel_i_piste.add(i_pi_lblType);

		JLabel i_deslabel_1 = new JLabel("Description :");
		i_deslabel_1.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_deslabel_1.setBounds(92, 130, 99, 25);
		panel_i_piste.add(i_deslabel_1);

		JLabel i_pi_l = new JLabel("Image :");
		i_pi_l.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pi_l.setBounds(92, 256, 99, 25);
		panel_i_piste.add(i_pi_l);

		i_pi_resortcombo.setBounds(221, 308, 113, 20);
		panel_i_piste.add(i_pi_resortcombo);

		JButton i_pi_add = new JButton("Add");

		i_pi_add.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pi_add.setBounds(119, 373, 99, 23);
		panel_i_piste.add(i_pi_add);

		JButton i_pi_chooseimage = new JButton("choose image");
		
		i_pi_chooseimage.setBounds(221, 256, 113, 23);
		panel_i_piste.add(i_pi_chooseimage);

		i_pi_path = new JTextField();
		i_pi_path.setEditable(false);
		i_pi_path.setColumns(10);
		i_pi_path.setBounds(343, 257, 148, 20);
		panel_i_piste.add(i_pi_path);

		JLabel i_pi_image = new JLabel("");
		i_pi_image.setBounds(613, 251, 150, 130);
		panel_i_piste.add(i_pi_image);
		JButton btnImageClth = new JButton("image");
		btnImageClth.setBounds(115, 419, 89, 23);
		StoreClothes.add(btnImageClth);
		JScrollPane i_pi_scrollPane = new JScrollPane();
		i_pi_scrollPane.setBounds(506, 81, 329, 165);
		panel_i_piste.add(i_pi_scrollPane);
		JButton i_pi_edit = new JButton("Edit");
		
		i_pi_edit.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pi_edit.setBounds(263, 373, 89, 23);
		panel_i_piste.add(i_pi_edit);
		
		i_pi_scrollPane.setViewportView(i_pi_table);
		UpdateEvent.setBounds(10, 270, 111, 30);
		AllEventspanel.add(UpdateEvent);
		JLabel EventPictureShow = new JLabel("");
		EventPictureShow.setBounds(469, 206, 227, 105);
		AllEventspanel.add(EventPictureShow);
		
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
		i_pi_remove.setBounds(401, 373, 89, 23);
		panel_i_piste.add(i_pi_remove);

		JLabel i_pilabel_res = new JLabel("Resort :");
		i_pilabel_res.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_pilabel_res.setBounds(92, 304, 99, 25);
		panel_i_piste.add(i_pilabel_res);

		JLabel i_pi_backg = new JLabel("");
		i_pi_backg.setIcon(new ImageIcon(
				"C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));
		i_pi_backg.setBounds(0, 0, 763, 316);
		panel_i_piste.add(i_pi_backg);
		
		JLabel i3_background = new JLabel("");
		i3_background.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		i3_background.setBounds(0, 0, 870, 555);
		panel_i_piste.add(i3_background);

		
		tabbedPane.addTab("ALL TRAININGS", null, panel, null);

		TrainingNameShow = new JTextField();
		TrainingNameShow.setColumns(10);
		TrainingNameShow.setBounds(473, 37, 244, 30);
		panel.add(TrainingNameShow);

		JDateChooser TrainingStartingDateShow = new JDateChooser();
		TrainingStartingDateShow.setDateFormatString("yyyy-MM-dd");
		TrainingStartingDateShow.setBounds(500, 159, 199, 25);
		panel.add(TrainingStartingDateShow);

		JDateChooser TrainingEndingDateShow = new JDateChooser();
		TrainingEndingDateShow.setDateFormatString("yyyy-MM-dd");
		TrainingEndingDateShow.setBounds(500, 206, 199, 25);
		panel.add(TrainingEndingDateShow);

		JTextArea TrainingDescriptionShow = new JTextArea();
		TrainingDescriptionShow.setBounds(473, 78, 244, 70);
		panel.add(TrainingDescriptionShow);
		EventTable.setModel(pubicationModel.getEventModel());
		
		TrainingTable.setFont(new Font("Arial", Font.BOLD, 12));
		TrainingTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		TrainingTable.setBounds(10, 49, 399, 198);
		panel.add(TrainingTable);

		JButton UpdateTraining = new JButton("UPDATE");
		UpdateTraining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RowSelectedTraining == null) {

					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Select a training first");
				} else {

					String message = "Are you sure to update " + RowSelectedTraining.getName() + " ?";
					String title = "Update Confirmation ";
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {

						try {

							Training training = new Training();
							training = TrainingBusiness.findTrainingByID(RowSelectedTraining.getIdTraining());
							String nameUpdate = TrainingNameShow.getText();
							String descriptionUpdate = TrainingDescriptionShow.getText();

							SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
							Date parsed = new Date();
							SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd");
							Date parsed2 = new Date();
							try {

								parsed = format3
										.parse(((JTextField) TrainingStartingDateShow.getDateEditor().getUiComponent())
												.getText());
								parsed2 = format4
										.parse(((JTextField) TrainingEndingDateShow.getDateEditor().getUiComponent())
												.getText());

							} catch (ParseException ex) {
								ex.printStackTrace();
							}
							java.sql.Date sql = new java.sql.Date(parsed.getTime());
							java.sql.Date sql2 = new java.sql.Date(parsed2.getTime());

							training.setStartDate(sql);
							training.setEndDate(sql2);

							training.setName(nameUpdate);
							training.setDescription(descriptionUpdate);
							TrainingBusiness.updateTraining(training);
							TrainingTable.setModel(trainingModel.getTrainingModel());

						} catch (NamingException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}

						JDialog dialog = new JDialog();
						dialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog, RowSelectedTraining.getName() + " modified !");
						TrainingDescriptionShow.setText("");
						TrainingNameShow.setText("");

					}

				}

			}
		});
		UpdateTraining.setBounds(88, 270, 111, 30);
		panel.add(UpdateTraining);

		JButton DeleteTraining = new JButton("DELETE");
		DeleteTraining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RowSelectedTraining == null) {

					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Select a training first");
				} else {

					String message = "Are you sure to delete " + RowSelectedTraining.getName() + " ?";
					String title = "Delete Confirmation";
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {

						try {

							Training training = new Training();
							training = TrainingBusiness.findTrainingByID(RowSelectedTraining.getIdTraining());
							TrainingBusiness.deleteTraining(training);

							TrainingTable.setModel(trainingModel.getTrainingModel());

						} catch (NamingException e2) {
							e2.printStackTrace();
						}

						JDialog dialog = new JDialog();
						dialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog, RowSelectedTraining.getName() + " deleted !");
						TrainingDescriptionShow.setText("");
						TrainingNameShow.setText("");

					}

				}
			}
		});
		DeleteTraining.setBounds(246, 270, 111, 30);
		panel.add(DeleteTraining);

		Filter_Trainings = new JTextField();
		Filter_Trainings.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String queryTraining = Filter_Trainings.getText();
				filterTraining(queryTraining);
			}
		});
		Filter_Trainings.setFont(new Font("Arial", Font.PLAIN, 13));
		Filter_Trainings.setColumns(10);
		Filter_Trainings.setBounds(88, 18, 240, 20);
		panel.add(Filter_Trainings);

		
		TrainingShare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameToShare = TrainingNameShow.getText();
				// String startingDateToShare = StartingDateShow.;
				String descriptionToShare = TrainingDescriptionShow.getText();
				String m = nameToShare + " " + descriptionToShare;
				String url = "H:/ski training.jpg";
				try {
					partageFacebook(m, url);
				} catch (FileNotFoundException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		btnEdit_Clothe.setBounds(496, 419, 89, 23);
		StoreClothes.add(btnEdit_Clothe);
		
		btnImageClth.setBounds(136, 419, 89, 23);
		StoreClothes.add(btnImageClth);
		
		
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					clotheModel.fillStoreComboBox(cbxStoreClth);
				} catch (NamingException | SQLException | IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

				
				
				try {
					Event event = new Event();
					Resort resort = new Resort();
					resort = ResortBusinessDelegate.findResortByLabel(EventResort.getSelectedItem().toString());
					if (EventName.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Fill the name space");
						return;
					} else if (EventName.getText().length() > 35 || EventName.getText().length() < 5) {
						JOptionPane.showMessageDialog(null, "A name must contain between 5 and 35 characters");
						return;
					}

					else if (EventDescription.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Fill the Description space");
						return;
					} else if (EventDescription.getText().length() > 255 || EventDescription.getText().length() < 10) {
						JOptionPane.showMessageDialog(null,
								"The description must contain between 10 and 255 characters");
						return;
					} else

						for (int i = 0; i < pubicationModel.getlisteEvents().size(); i++) {

							if (EventName.getText().equals(pubicationModel.getlisteEvents().get(i).getName())) {
								JOptionPane.showMessageDialog(null, "an event with this name already exists");
								return;

							}
						}
					event.setDescription(EventDescription.getText());
					event.setName(EventName.getText());
					event.setType(EventType.getSelectedItem().toString());
					event.setResort(resort);

					/**** jCALENDAR */
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedE = new Date();
					// java.util.Date parsed = new java.util.Date();

					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedE2 = new Date();
					try {

						parsedE = format.parse(((JTextField) dateStart.getDateEditor().getUiComponent()).getText());
						parsedE2 = format.parse(((JTextField) dateEnd.getDateEditor().getUiComponent()).getText());

					} catch (ParseException ex) {
						ex.printStackTrace();
					}
					java.sql.Date sql = new java.sql.Date(parsedE.getTime());
					java.sql.Date sql2 = new java.sql.Date(parsedE2.getTime());
					/****
					 * Condition date **
					 */
					LocalDate ld = new LocalDate();

					String Currentyear = ld.toString().substring(2, 4);
					String Currentmounth = ld.toString().substring(5, 7);
					String Currentday = ld.toString().substring(8, 10);

					String year = parsedE.toString();
					String parsedToFormat = String.format("%1$ty-%1$tm-%1$td", parsedE);
					String parsed2ToFormat = String.format("%1$ty-%1$tm-%1$td", parsedE2);

					String Calendaryear = parsedToFormat.substring(0, 2);
					String Calendarmounth = parsedToFormat.substring(3, 5);
					String Calendarday = parsedToFormat.substring(6, 8);

					String Calendar2year = parsed2ToFormat.substring(0, 2);
					String Calendar2mounth = parsed2ToFormat.substring(3, 5);
					String Calendar2day = parsed2ToFormat.substring(6, 8);

					if (Integer.parseInt(Calendaryear) < Integer.parseInt(Currentyear)) {
						JDialog Datedialog = new JDialog();
						Datedialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(Datedialog, "Check your starting year field");
						return;
					} else if (Integer.parseInt(Calendarmounth) < Integer.parseInt(Currentmounth)) {
						JDialog Datedialog = new JDialog();
						Datedialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(Datedialog, "Check your starting mounth field");
						return;
					} else if ((Integer.parseInt(Calendarday) < Integer.parseInt(Currentday))
							|| (Integer.parseInt(Calendarday) == Integer.parseInt(Currentday))) {
						JDialog Datedialog = new JDialog();
						Datedialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(Datedialog, "Event day field should start from Tommorow");
						return;
					} else if (Integer.parseInt(Calendar2year) < Integer.parseInt(Calendaryear)) {
						JDialog Datedialog = new JDialog();
						Datedialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(Datedialog, "Check your Ending year field");
						return;
					} else if (Integer.parseInt(Calendar2mounth) < Integer.parseInt(Calendarmounth)) {
						JDialog Datedialog = new JDialog();
						Datedialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(Datedialog, "Check your ending mounth field");
						return;
					} else if (Integer.parseInt(Calendar2day) < Integer.parseInt(Calendarday)) {
						JDialog Datedialog = new JDialog();
						Datedialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(Datedialog, "Check your ending day field");
						return;
					} else
						event.setStartDate(sql);
					event.setEndDate(sql2);

					File file = new File(EventImage);
					byte[] bFile = new byte[(int) file.length()];

					try {
						FileInputStream fileInputStream = new FileInputStream(file);
						fileInputStream.read(bFile);
						fileInputStream.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					event.setImage(bFile);

					EventBusiness.addEvent(event);

					EventTable.setModel(pubicationModel.getEventModel());

				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		
		UpdateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (RowSelectedEvent == null) {

					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Select an event first");
				} else {

					String message = "Are you sure to update " + RowSelectedEvent.getName() + " ?";
					String title = "Update Confirmation ";
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {

						try {

							Event event = new Event();
							event = EventBusiness.findEventByID(RowSelectedEvent.getIdEvent());
							if (EventNameShow.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Please Fill the name space");
								return;
							} else if (EventNameShow.getText().length() > 35 || EventNameShow.getText().length() < 5) {
								JOptionPane.showMessageDialog(null, "A name must contain between 5 and 35 characters");
								return;
							}

							else if (EventDescriptionShow.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Please Fill the Description space");
								return;
							} else if (EventDescriptionShow.getText().length() > 255
									|| EventDescriptionShow.getText().length() < 10) {
								JOptionPane.showMessageDialog(null,
										"The description must contain between 10 and 255 characters");
								return;
							} else

								for (int i = 0; i < pubicationModel.getlisteEvents().size(); i++) {

									if (EventNameShow.getText()
											.equals(pubicationModel.getlisteEvents().get(i).getName())) {
										JOptionPane.showMessageDialog(null, "an event with this name already exists");
										return;

									}
								}

							String nameUpdate = EventNameShow.getText();
							String descriptionUpdate = EventDescriptionShow.getText();

							SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
							Date parsed = new Date();
							SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd");
							Date parsed2 = new Date();
							try {

								parsed = format3.parse(
										((JTextField) StartingDateShow.getDateEditor().getUiComponent()).getText());
								parsed2 = format4.parse(
										((JTextField) EndingDateShow.getDateEditor().getUiComponent()).getText());

							} catch (ParseException ex) {
								ex.printStackTrace();
							}
							java.sql.Date sql = new java.sql.Date(parsed.getTime());
							java.sql.Date sql2 = new java.sql.Date(parsed2.getTime());

							event.setStartDate(sql);
							event.setEndDate(sql2);

							event.setName(nameUpdate);
							event.setDescription(descriptionUpdate);
							EventBusiness.updateEvent(event);
							EventTable.setModel(pubicationModel.getEventModel());

						} catch (NamingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						JDialog dialog = new JDialog();
						dialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog, RowSelectedEvent.getName() + " modified !");

					}

				}

			}
		});
		

		EventTable.setModel(pubicationModel.getEventModel());

		EventTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = EventTable.getSelectedRow();

				RowSelectedEvent = pubicationModel.getlisteEvents().get(index);
				EventNameShow.setText(RowSelectedEvent.getName());
				EventDescriptionShow.setText(RowSelectedEvent.getDescription());
				StartingDateShow.setDate(RowSelectedEvent.getStartDate());
				EndingDateShow.setDate(RowSelectedEvent.getEndDate());

				FileOutputStream fos;
				try {
					fos = new FileOutputStream("src//main//resources//imgs//ski_event.jpg");
					fos.write(RowSelectedEvent.getImage());
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ImageIcon imgThisImg1 = new ImageIcon("src//main//resources//imgs//ski_event.jpg");
				Image image = imgThisImg1.getImage(); // transform it
				Image newimg = image.getScaledInstance(150, 130, java.awt.Image.SCALE_SMOOTH);

				ImageIcon imgThisImg = new ImageIcon(newimg);
				EventPictureShow.setIcon(imgThisImg);
				imgThisImg.getImage().flush();

			}
		});

		TrainingTable.setModel(trainingModel.getTrainingModel());

		JButton btnNewButton = new JButton("CREATE PDF");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (RowSelectedTraining == null) {

					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Select a training first");
				} else {

					String TrainingNamePdf = RowSelectedTraining.getName().toString();
					String TrainingDescriptionPdf = RowSelectedTraining.getDescription().toString();
					String TrainingStartPdf = "24-03-2017";
					String TrainingeEndPdf = "25-03-2017";
					CreatePdf(TrainingNamePdf, TrainingDescriptionPdf, TrainingStartPdf, TrainingeEndPdf);
				}
			}
		});
		btnNewButton.setBounds(367, 274, 135, 23);
		panel.add(btnNewButton);
		
		JLabel r4_backgroun = new JLabel("");
		r4_backgroun.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		r4_backgroun.setBounds(0, 0, 880, 555);
		panel.add(r4_backgroun);

		TrainingTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = TrainingTable.getSelectedRow();

				RowSelectedTraining = trainingModel.getlisteEvents().get(index);
				TrainingNameShow.setText(RowSelectedTraining.getName());
				TrainingDescriptionShow.setText(RowSelectedTraining.getDescription());
				TrainingStartingDateShow.setDate(RowSelectedTraining.getStartDate());
				TrainingEndingDateShow.setDate(RowSelectedTraining.getEndDate());

			}
		});

		JButton DeleteEvent = new JButton("DELETE");
		DeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (RowSelectedEvent == null) {

					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Select an event first");
				} else {

					String message = "Are you sure to delete " + RowSelectedEvent.getName() + " ?";
					String title = "Delete Confirmation";
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {

						try {

							Event event = new Event();
							event = EventBusiness.findEventByID(RowSelectedEvent.getIdEvent());
							EventBusiness.deleteEvent(event);
							EventTable.setModel(pubicationModel.getEventModel());
							EventDescriptionShow.setText("");
							EventNameShow.setText("");
						

						} catch (NamingException e) {
							e.printStackTrace();
						}

						JDialog dialog = new JDialog();
						dialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog, RowSelectedEvent.getName() + " deleted !");

					}

				}
				//

			}
		});
		DeleteEvent.setBounds(131, 270, 111, 30);
		AllEventspanel.add(DeleteEvent);

		Filter_Event = new JTextField();
		Filter_Event.setFont(new Font("Arial", Font.PLAIN, 13));
		Filter_Event.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String query = Filter_Event.getText();
				filter(query);
			}
		});
		Filter_Event.setBounds(88, 18, 240, 20);
		AllEventspanel.add(Filter_Event);
		Filter_Event.setColumns(10);

		JButton EventShare = new JButton("Share on Facebook");
		EventShare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameToShare = EventNameShow.getText();
				// String startingDateToShare = StartingDateShow.;
				String descriptionToShare = EventDescriptionShow.getText();
				String m = nameToShare + " " + descriptionToShare;
				String url = "H:/ski event.jpg";
				try {
					partageFacebook(m, url);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		EventShare.setBounds(252, 272, 135, 27);
		AllEventspanel.add(EventShare);
		
		JLabel r3_background = new JLabel("");
		r3_background.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		r3_background.setBounds(0, 0, 880, 555);
		AllEventspanel.add(r3_background);

		i_imagepathClth = new JTextField();
		i_imagepathClth.setBounds(36, 388, 258, 20);
		StoreClothes.add(i_imagepathClth);
		i_imagepathClth.setColumns(10);

	

		JLabel lblStore_1 = new JLabel("Store");
		lblStore_1.setBounds(36, 249, 46, 14);
		StoreClothes.add(lblStore_1);

		scrollPane_1.setBounds(329, 14, 531, 373);
		StoreClothes.add(scrollPane_1);

		tableClth = new JTable();
		scrollPane_1.setViewportView(tableClth);

		JLabel lblName = new JLabel("Name *");
		lblName.setBounds(31, 14, 46, 14);
		StoreEquipments.add(lblName);

		;
		lblNewLabel_desc = new JLabel("Description *");
		lblNewLabel_desc.setBounds(31, 58, 94, 14);
		StoreEquipments.add(lblNewLabel_desc);

		JLabel lblPrice = new JLabel("Price *");
		lblPrice.setBounds(31, 102, 46, 14);
		StoreEquipments.add(lblPrice);

		JLabel lblDeal = new JLabel("Deal *");
		lblDeal.setBounds(31, 146, 46, 14);
		StoreEquipments.add(lblDeal);

		JLabel lblType = new JLabel("Type *");
		lblType.setBounds(31, 189, 46, 14);
		StoreEquipments.add(lblType);

		txtName = new JTextField();
		txtName.setBounds(135, 11, 155, 20);
		StoreEquipments.add(txtName);
		txtName.setColumns(10);

		txtDesc = new JTextField();
		txtDesc.setBounds(135, 55, 155, 20);
		StoreEquipments.add(txtDesc);
		txtDesc.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setBounds(135, 99, 155, 20);
		StoreEquipments.add(txtPrice);
		txtPrice.setColumns(10);

		txtDeal = new JTextField();
		txtDeal.setBounds(135, 143, 155, 20);
		StoreEquipments.add(txtDeal);
		txtDeal.setColumns(10);

		txtType = new JTextField();
		txtType.setBounds(135, 186, 155, 20);
		StoreEquipments.add(txtType);
		txtType.setColumns(10);

		JComboBox cbxStore = new JComboBox();
		cbxStore.setBounds(135, 232, 155, 20);
		StoreEquipments.add(cbxStore);
		// EquipementModel equipementModel = new EquipementModel();
		equipementModel.fillStoreComboBox(cbxStoreClth);
		equipementModel.fillStoreComboBox(cbxStore);
		JButton btnAddEqpFK = new JButton("Add");
		btnAddEqpFK.setBounds(351, 419, 89, 23);
		btnAddEqpFK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isFull = true;
				if (txtName.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldName.setVisible(true);
				} else {
					RequiredFieldName.setVisible(false);
				}

				if (txtDesc.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDesc.setVisible(true);
				} else {
					RequiredFieldDesc.setVisible(false);
				}

				if (txtPrice.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldPrice.setVisible(true);
				} else {
					RequiredFieldPrice.setVisible(false);
				}

				if (txtType.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldType.setVisible(true);
				} else {
					RequiredFieldType.setVisible(false);
				}

				if (txtDeal.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDeal.setVisible(true);
				} else {
					RequiredFieldDeal.setVisible(false);
				}

				if (isFull == true) {
					try {
						EquipementModel equipementModel;

						Equipments Equipment = new Equipments();
						Equipment.setName(txtName.getText());
						Equipment.setDescription(txtDesc.getText());
						Equipment.setPrice(Float.parseFloat(txtPrice.getText()));
						Equipment.setDeal(Float.parseFloat(txtDeal.getText()));
						Equipment.setType(txtType.getText());
						File file = new File(pathImgEqp);
						byte[] bFile = new byte[(int) file.length()];

						try {
							FileInputStream fileInputStream = new FileInputStream(file);
							fileInputStream.read(bFile);
							fileInputStream.close();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						Equipment.setImage(bFile);
						Equipment.setStore(StoreBusinessDelegate.findAllStores().get(cbxStore.getSelectedIndex()));
						equipementModel = new EquipementModel();
						EquipementBusiness.addEquipement(Equipment);
						tableEqp.setModel(equipementModel.getEquipmentsModel());
						JDialog dialog = new JDialog();
						dialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog, "Add successfully!");
						RequiredFieldName.setVisible(false);
						RequiredFieldDesc.setVisible(false);
						RequiredFieldPrice.setVisible(false);
						RequiredFieldType.setVisible(false);
						RequiredFieldDeal.setVisible(false);
						txtName.setText("");
						txtDesc.setText("");
						txtPrice.setText("");
						txtDeal.setText("");
						txtType.setText("");
						lblImg.setText(null);

					} catch (NamingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Please enter the required fields");

				}

			}
		});
		StoreEquipments.add(btnAddEqpFK);

		JScrollPane scrollPaneEqp = new JScrollPane();
		scrollPaneEqp.setBounds(330, 11, 509, 331);
		StoreEquipments.add(scrollPaneEqp);
		JLabel lblImg = new JLabel("");
		lblImg.setBounds(31, 263, 259, 106);
		lblImg.setIcon((Icon) img_display);
		tableEqp = new JTable();
		tableEqp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableEqp.getSelectedRow();
				EquipementModel equipementModel;
				try {
					equipementModel = new EquipementModel();
					rowEquipments = equipementModel.getEquipmentList().get(index);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FileOutputStream fos;
				try {
					fos = new FileOutputStream("src//main//resources//imgs//eqp.jpg");
					fos.write(rowEquipments.getImage());
					fos.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ImageIcon imgThisImg1 = new ImageIcon("src//main//resources//imgs//eqp.jpg");
				Image image = imgThisImg1.getImage(); // transform it
				Image newimg = image.getScaledInstance(150, 130, java.awt.Image.SCALE_SMOOTH); // scale
																								// it
																								// the
																								// smooth
																								// way
				ImageIcon imgThisImg = new ImageIcon(newimg); // transform
																// it back
				lblImg.setIcon(imgThisImg);
				imgThisImg.getImage().flush();

				txtName.setText(rowEquipments.getName());
				txtDesc.setText(rowEquipments.getDescription());
				txtPrice.setText((Float.toString(rowEquipments.getPrice())));
				txtDeal.setText((Float.toString(rowEquipments.getDeal())));
				txtType.setText(rowEquipments.getType());
				RequiredFieldName.setVisible(false);
				RequiredFieldDesc.setVisible(false);
				RequiredFieldPrice.setVisible(false);
				RequiredFieldType.setVisible(false);
				RequiredFieldDeal.setVisible(false);

			}
		});
		scrollPaneEqp.setViewportView(tableEqp);
		StoreEquipments.add(lblImg);

		JButton btnChooseImage = new JButton("Image");
		btnChooseImage.setBounds(97, 419, 94, 23);
		btnChooseImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser choix = new JFileChooser();
				choix.showOpenDialog(null);
				f = choix.getSelectedFile();
				pathImgEqp = f.getAbsolutePath();
				ImageIcon image = new ImageIcon(pathImgEqp);
				Image im = image.getImage();
				Image myImg = im.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon newImage = new ImageIcon(myImg);
				lblImg.setIcon(newImage);
			}
		});
		StoreEquipments.add(btnChooseImage);

		JLabel lblNewLabel = new JLabel("Store");
		lblNewLabel.setBounds(31, 235, 46, 14);
		StoreEquipments.add(lblNewLabel);

		i_imagepathf = new JTextField();
		i_imagepathf.setBounds(31, 380, 259, 20);
		StoreEquipments.add(i_imagepathf);
		i_imagepathf.setColumns(10);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(467, 419, 89, 23);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isFull = true;
				if (txtName.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldName.setVisible(true);
				} else {
					RequiredFieldName.setVisible(false);
				}

				if (txtDesc.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDesc.setVisible(true);
				} else {
					RequiredFieldDesc.setVisible(false);
				}

				if (txtPrice.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldPrice.setVisible(true);
				} else {
					RequiredFieldPrice.setVisible(false);
				}

				if (txtType.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldType.setVisible(true);
				} else {
					RequiredFieldType.setVisible(false);
				}

				if (txtDeal.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDeal.setVisible(true);
				} else {
					RequiredFieldDeal.setVisible(false);
				}
				int index = tableEqp.getSelectedRow();
				if (rowEquipments != null) {
					EquipementModel equipementModel;
					Equipments Equipment = new Equipments();
					Equipment.setIdEquipment(rowEquipments.getIdEquipment());
					Equipment.setName(txtName.getText());
					Equipment.setDescription(txtDesc.getText());
					Equipment.setPrice(Float.parseFloat(txtPrice.getText()));
					Equipment.setDeal(Float.parseFloat(txtDeal.getText()));
					Equipment.setType(txtType.getText());
					if (isFull == true) {
						try {
							Equipment.setStore(StoreBusinessDelegate.findAllStores().get(cbxStore.getSelectedIndex()));
							equipementModel = new EquipementModel();
							EquipementBusiness.updateEquipement(Equipment);
							tableEqp.setModel(equipementModel.getEquipmentsModel());
							JDialog dialog = new JDialog();
							dialog.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(dialog, "Edit successfully!");
							txtName.setText("");
							txtDesc.setText("");
							txtPrice.setText("");
							txtDeal.setText("");
							txtType.setText("");
							RequiredFieldName.setVisible(false);
							RequiredFieldDesc.setVisible(false);
							RequiredFieldPrice.setVisible(false);
							RequiredFieldType.setVisible(false);
							RequiredFieldDeal.setVisible(false);
						} catch (NamingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select an equipement to modify");
				}

			}
		});
		StoreEquipments.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(583, 419, 89, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableEqp.getSelectedRow();
				if (rowEquipments != null) {

					try {
						EquipementBusiness.deleteEquipement(rowEquipments.getIdEquipment());
						tableEqp.setModel(equipementModel.getEquipmentsModel());
						JDialog dialog = new JDialog();
						dialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog, "Delete successfully!");
						txtName.setText("");
						txtDesc.setText("");
						txtPrice.setText("");
						txtDeal.setText("");
						txtType.setText("");
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select an equipement to remove");
				}

			}
		});
		StoreEquipments.add(btnDelete);
		
		JLabel f1_background = new JLabel("New label");
		f1_background.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		f1_background.setBounds(0, 0, 870, 529);
		StoreEquipments.add(f1_background);

		JPanel Location = new JPanel();
		tabbedPane.addTab("Location", null, Location, null);

		JButton btnDisplayFK = new JButton("Display");
		btnDisplayFK.setBounds(203, 413, 89, 23);
		btnDisplayFK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crearMapa();

				} catch (Exception ex) {
				}
			}

		});
		Location.setLayout(null);
		Location.add(btnDisplayFK);

		scrollPaneMap = new JScrollPane();
		scrollPaneMap.setBounds(409, 11, 456, 459);
		Location.add(scrollPaneMap);

		scrollPaneMap.setViewportView(JLabel_ME_Imagen);

		JLabel lblFormat = new JLabel("Format");
		lblFormat.setBounds(31, 226, 46, 14);
		Location.add(lblFormat);

		JCombo_ME_Formato
				.setModel(new DefaultComboBoxModel(new String[] { "png", "png32", "gif", "jpg", "jpg_baseline" }));
		JCombo_ME_Formato.setBounds(143, 223, 231, 20);
		Location.add(JCombo_ME_Formato);

		JLabel lblResorts = new JLabel("Resorts");
		lblResorts.setBounds(31, 82, 46, 14);
		Location.add(lblResorts);

		cbx_lib = new JComboBox();

		cbx_lib.setBounds(143, 79, 231, 20);
		Location.add(cbx_lib);
		clotheModel.fillResortComboBox(cbx_lib);
		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setBounds(31, 158, 46, 14);
		Location.add(lblType_1);

		// JCombo_ME_TipoMapa.setModel(new DefaultComboBoxModel(new String[]
		// {"RoadMap", "Satellite", "Hybrid", "Ground"}));
		JCombo_ME_TipoMapa = new JComboBox();
		JCombo_ME_TipoMapa
				.setModel(new DefaultComboBoxModel(new String[] { "RoadMap", "Satellite", "Hybrid", "Ground" }));
		JCombo_ME_TipoMapa.setBounds(143, 155, 231, 20);
		Location.add(JCombo_ME_TipoMapa);
		/***************************************************/
		JLabel lblZoom = new JLabel("Zoom");
		lblZoom.setBounds(31, 286, 46, 14);
		Location.add(lblZoom);

		JText_ME_Zoom = new JTextField();
		JText_ME_Zoom.setText("12");
		JText_ME_Zoom.setBounds(343, 283, 33, 20);
		Location.add(JText_ME_Zoom);
		JText_ME_Zoom.setColumns(10);

		JText_ME_Escala = new JTextField();
		JText_ME_Escala.setText("1");
		JText_ME_Escala.setBounds(343, 337, 31, 20);
		Location.add(JText_ME_Escala);
		JText_ME_Escala.setColumns(10);

		JLabel lblEchelle = new JLabel("Scale");
		lblEchelle.setBounds(31, 340, 46, 14);
		Location.add(lblEchelle);

		JSlider JSlider_ME_Escala = new JSlider();
		JSlider_ME_Escala.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JText_ME_Escala.setText(String.valueOf(JSlider_ME_Escala.getValue()));
				try {
					crearMapa();

				} catch (Exception ex) {
				}
			}
		});
		JSlider_ME_Escala.setValue(1);
		JSlider_ME_Escala.setMinimum(1);
		JSlider_ME_Escala.setMaximum(2);
		JSlider_ME_Escala.setBounds(143, 337, 188, 26);
		Location.add(JSlider_ME_Escala);
		/****************************************************/
		JSlider JSlider_ME_Zoom = new JSlider();
		JSlider_ME_Zoom.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JText_ME_Zoom.setText(String.valueOf(JSlider_ME_Zoom.getValue()));
				try {
					crearMapa();

				} catch (Exception ex) {
				}
			}
		});
		JSlider_ME_Zoom.setValue(12);
		JSlider_ME_Zoom.setMinimum(1);
		JSlider_ME_Zoom.setMaximum(20);
		JSlider_ME_Zoom.setBounds(143, 286, 188, 26);
		Location.add(JSlider_ME_Zoom);
		
		JLabel f2_background = new JLabel("");
		f2_background.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		f2_background.setBounds(0, 0, 870, 555);
		Location.add(f2_background);

		JLabel i_label = new JLabel("");
		i_label.setIcon(new ImageIcon(
				"C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));

		// lblNewLabel.setBounds(0, 0, 780, 436);
		// ManagerGUI.getContentPane().add(lblNewLabel);
		tabbedPane.addTab("Profil", null, managerProfilePanel, null);
		managerProfilePanel.setLayout(null);

		JLabel h_lbl_status = new JLabel("You are");
		h_lbl_status.setBounds(94, 90, 46, 22);
		managerProfilePanel.add(h_lbl_status);

		managerProfilePanel.add(h_lbl_image);

		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(94, 133, 72, 14);
		managerProfilePanel.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(94, 170, 57, 14);
		managerProfilePanel.add(lblLastName);

		h_lbl_role = new JLabel("");
		h_lbl_role.setBounds(179, 94, 72, 14);
		managerProfilePanel.add(h_lbl_role);

		JLabel h_lbl_phone = new JLabel("Phone");
		h_lbl_phone.setBounds(94, 324, 46, 14);
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
		h_tf_phone.setBounds(175, 321, 86, 20);
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
		h_tf_firstname.setBounds(175, 130, 86, 20);
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
		h_tf_lastname.setBounds(175, 167, 86, 20);
		managerProfilePanel.add(h_tf_lastname);
		h_tf_lastname.setColumns(10);

		JLabel h_lblAddress = new JLabel("Address");
		h_lblAddress.setBounds(94, 282, 46, 14);
		managerProfilePanel.add(h_lblAddress);

		h_tf_address = new JTextField();
		h_tf_address.setEnabled(false);
		h_tf_address.setBounds(175, 279, 86, 20);
		managerProfilePanel.add(h_tf_address);
		h_tf_address.setColumns(10);

		JLabel lblGender = new JLabel("gender");
		lblGender.setBounds(94, 236, 46, 14);
		managerProfilePanel.add(lblGender);

		h_lbl_genderview = new JLabel("");
		h_lbl_genderview.setBounds(175, 236, 86, 14);
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
		btnNewButton1.setBounds(631, 66, 109, 23);
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
		btnNewButton_1.setBounds(631, 94, 109, 23);
		managerProfilePanel.add(btnNewButton_1);

		JLabel h_lblEmail = new JLabel("Email");
		h_lblEmail.setBounds(443, 173, 57, 14);
		managerProfilePanel.add(h_lblEmail);

		h_tf_email = new JTextField();
		h_tf_email.setEnabled(false);
		h_tf_email.setBounds(522, 170, 134, 20);
		managerProfilePanel.add(h_tf_email);
		h_tf_email.setColumns(10);

		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setBounds(536, 236, 120, 14);
		managerProfilePanel.add(lblChangePassword);

		JLabel h_lblNewPassword = new JLabel("New Password");
		h_lblNewPassword.setBounds(424, 260, 86, 14);
		managerProfilePanel.add(h_lblNewPassword);

		JLabel h_lblConfirmPassword = new JLabel("Confirm Password");
		h_lblConfirmPassword.setBounds(424, 302, 88, 14);
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
		btnUpdate.setBounds(310, 380, 185, 23);
		managerProfilePanel.add(btnUpdate);

		h_tf_password = new JPasswordField();
		h_tf_password.setEnabled(false);
		h_tf_password.setBounds(522, 257, 134, 20);
		managerProfilePanel.add(h_tf_password);

		h_tf_paswwordConfirm = new JPasswordField();
		h_tf_paswwordConfirm.setEnabled(false);
		h_tf_paswwordConfirm.setBounds(522, 299, 134, 20);
		managerProfilePanel.add(h_tf_paswwordConfirm);
		
		JLabel h_background = new JLabel("");
		h_background.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		h_background.setBounds(0, 0, 880, 555);
		managerProfilePanel.add(h_background);

		setTextFields();
		i_label.setBounds(0, 0, 832, 659);
		ManagerGUI.getContentPane().add(i_label);

		JLabel lblStoreName = new JLabel("Store name :");
		lblStoreName.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblStoreName.setBounds(10, 27, 99, 25);
		Stores.add(lblStoreName);

		storeName = new JTextField();
		storeName.setColumns(10);
		storeName.setBounds(139, 31, 149, 20);
		Stores.add(storeName);

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
		
				JLabel t_background = new JLabel("");
				t_background.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
				t_background.setForeground(Color.RED);
				t_background.setBounds(0, 11, 813, 544);
				Stores.add(t_background);
				Stores.setFocusTraversalPolicy(
						new FocusTraversalOnArray(new Component[] { storeDescription, lblStoreName, storeName, lblStoreDesc,
								addStore, storeLocation, storeScroll, editStore, removeStore, lblStoreAdress, t_background }));
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
		btnDelete_Clothe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableClth.getSelectedRow();
				if (rowClothes != null) {

					try {

						ClothBusiness.deleteClothe(rowClothes.getIdClothes());
						tableClth.setModel(clotheModel.getClothesModel());
						JDialog dialog = new JDialog();
						dialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog, "Delete successfully!");
						txtNameClth.setText("");
						txtDescClth.setText("");
						txtPriceClth.setText("");
						txtDealClth.setText("");
						txtTypeClth.setText("");
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a Clothe to remove");
				}
			}
		});
		btnDelete_Clothe.setBounds(627, 419, 89, 23);
		StoreClothes.add(btnDelete_Clothe);
		
		JLabel f2_backgroun = new JLabel("New label");
		f2_backgroun.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		f2_backgroun.setBounds(0, 0, 870, 527);
		StoreClothes.add(f2_backgroun);

		
		btnImageClth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choix = new JFileChooser();
				choix.showOpenDialog(null);
				f = choix.getSelectedFile();
				pathImgClth = f.getAbsolutePath();
				ImageIcon image = new ImageIcon(pathImgClth);
				Image im = image.getImage();
				Image myImg = im.getScaledInstance(lblImgClth.getWidth(), lblImgClth.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon newImage = new ImageIcon(myImg);
				lblImgClth.setIcon(newImage);
			}
		});
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
		btnAddClothFK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isFull = true;
				if (txtNameClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldNameClth.setVisible(true);
				} else {
					RequiredFieldNameClth.setVisible(false);
				}

				if (txtDescClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDescClth.setVisible(true);
				} else {
					RequiredFieldDescClth.setVisible(false);
				}

				if (txtPriceClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldPriceClth.setVisible(true);
				} else {
					RequiredFieldPriceClth.setVisible(false);
				}

				if (txtTypeClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldTypeClth.setVisible(true);
				} else {
					RequiredFieldTypeClth.setVisible(false);
				}

				if (txtDealClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDealClth.setVisible(true);
				} else {
					RequiredFieldDealClth.setVisible(false);
				}
				if (isFull == true) {
					try {
						ClotheModel clotheModel;

						Clothes Clothe = new Clothes();
						Clothe.setName(txtNameClth.getText());
						Clothe.setDescription(txtDescClth.getText());
						Clothe.setPrice(Float.parseFloat(txtPriceClth.getText()));
						Clothe.setDeal(Float.parseFloat(txtDealClth.getText()));
						Clothe.setType(txtTypeClth.getText());
						File file = new File(pathImgClth);
						byte[] bFile = new byte[(int) file.length()];

						try {
							FileInputStream fileInputStream = new FileInputStream(file);
							fileInputStream.read(bFile);
							fileInputStream.close();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						Clothe.setImage(bFile);
						Clothe.setStore(StoreBusinessDelegate.findAllStores().get(cbxStoreClth.getSelectedIndex()));
						clotheModel = new ClotheModel();
						ClothBusiness.addClothe(Clothe);
						tableClth.setModel(clotheModel.getClothesModel());
						txtNameClth.setText("");
						txtDescClth.setText("");
						txtPriceClth.setText("");
						txtDealClth.setText("");
						txtTypeClth.setText("");
						RequiredFieldNameClth.setVisible(false);
						RequiredFieldDescClth.setVisible(false);
						RequiredFieldPriceClth.setVisible(false);
						RequiredFieldTypeClth.setVisible(false);
						RequiredFieldDealClth.setVisible(false);
					} catch (NamingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Please enter the required fields");

				}

			}
		});
		btnEdit_Clothe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isFull = true;
				if (txtNameClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldNameClth.setVisible(true);
				} else {
					RequiredFieldNameClth.setVisible(false);
				}

				if (txtDescClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDescClth.setVisible(true);
				} else {
					RequiredFieldDescClth.setVisible(false);
				}

				if (txtPriceClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldPriceClth.setVisible(true);
				} else {
					RequiredFieldPriceClth.setVisible(false);
				}

				if (txtTypeClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldTypeClth.setVisible(true);
				} else {
					RequiredFieldTypeClth.setVisible(false);
				}

				if (txtDealClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDealClth.setVisible(true);
				} else {
					RequiredFieldDealClth.setVisible(false);
				}
				int index = tableClth.getSelectedRow();
				if (rowClothes != null) {
					ClotheModel clotheModel;

					Clothes clothes = new Clothes();
					clothes.setIdClothes(rowClothes.getIdClothes());
					clothes.setName(txtNameClth.getText());
					clothes.setDescription(txtDescClth.getText());
					clothes.setPrice(Float.parseFloat(txtPriceClth.getText()));
					clothes.setDeal(Float.parseFloat(txtDealClth.getText()));
					clothes.setType(txtTypeClth.getText());
					if (isFull == true) {
						try {
							clothes.setStore(
									StoreBusinessDelegate.findAllStores().get(cbxStoreClth.getSelectedIndex()));
							clotheModel = new ClotheModel();
							ClothBusiness.updateClothe(clothes);
							tableClth.setModel(clotheModel.getClothesModel());
							JDialog dialog = new JDialog();
							dialog.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(dialog, "Edit successfully!");
							txtNameClth.setText("");
							txtDescClth.setText("");
							txtPriceClth.setText("");
							txtDealClth.setText("");
							txtTypeClth.setText("");
							RequiredFieldNameClth.setVisible(false);
							RequiredFieldDescClth.setVisible(false);
							RequiredFieldPriceClth.setVisible(false);
							RequiredFieldTypeClth.setVisible(false);
							RequiredFieldDealClth.setVisible(false);
						} catch (NamingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select a clothe to modify");
				}
			}
		});
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
		AddTraining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Training training = new Training();
					Resort resort = new Resort();
					resort = ResortBusinessDelegate
							.findResortByLabel(TrainingResort.getSelectedItem().toString());
					training.setDescription(TrainingDescription.getText());
					training.setName(TrainingName.getText());
					training.setType(TrainingType.getSelectedItem().toString());
					training.setResort(resort);
					/**** jCALENDAR */
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedT = new Date();
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedT2 = new Date();
					try {

						parsedT = format
								.parse(((JTextField) dateStart.getDateEditor().getUiComponent()).getText());
						parsedT2 = format2
								.parse(((JTextField) dateEnd.getDateEditor().getUiComponent()).getText());

					} catch (ParseException ex) {
						ex.printStackTrace();
					}
					java.sql.Date sql = new java.sql.Date(parsedT.getTime());
					java.sql.Date sql2 = new java.sql.Date(parsedT2.getTime());

					training.setStartDate(sql);
					training.setEndDate(sql2);

					TrainingBusiness.addTraining(training);

					TrainingTable.setModel(trainingModel.getTrainingModel());

				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
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
		tableClth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableClth.getSelectedRow();
				ClotheModel clotheModel;
				try {
					clotheModel = new ClotheModel();
					rowClothes = clotheModel.getClotheList().get(index);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FileOutputStream fos;
				try {
					fos = new FileOutputStream("src//main//resources//imgs//clth.jpg");
					fos.write(rowClothes.getImage());
					fos.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ImageIcon imgThisImg1 = new ImageIcon("src//main//resources//imgs//clth.jpg");
				Image image = imgThisImg1.getImage(); // transform it
				Image newimg = image.getScaledInstance(150, 130, java.awt.Image.SCALE_SMOOTH); // scale
																								// it
																								// the
																								// smooth
																								// way
				ImageIcon imgThisImg = new ImageIcon(newimg); // transform
																// it
																// back
				lblImgClth.setIcon(imgThisImg);
				imgThisImg.getImage().flush();
				txtNameClth.setText(rowClothes.getName());
				txtDescClth.setText(rowClothes.getDescription());
				txtPriceClth.setText((Float.toString(rowClothes.getPrice())));
				txtDealClth.setText((Float.toString(rowClothes.getDeal())));
				txtTypeClth.setText(rowClothes.getType());
				RequiredFieldNameClth.setVisible(false);
				RequiredFieldDescClth.setVisible(false);
				RequiredFieldPriceClth.setVisible(false);
				RequiredFieldTypeClth.setVisible(false);
				RequiredFieldDealClth.setVisible(false);
			}
		});
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
		EventAddPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser ch = new JFileChooser();
				ch.showOpenDialog(null);
				File f = ch.getSelectedFile();
				EventImage = f.getAbsolutePath();

				EventPicturePath.setText(EventImage);

			}
		});
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
		// hotelmodel.fillResortComboBox(i_hotel_combo);
		// pistemodel.fillResortComboBox(i_pi_resortcombo);
	}

	public void fillCbResort() throws NamingException {

		List<Resort> list = new ArrayList<>();
		list = ResortBusinessDelegate.findAllResorts();
		for (Resort name : list)
			resortStore.addItem(name.getName());

	}

	/*********************************************************/
	private StaticMaps.Maptype seleccionarTipoMapa() {
		StaticMaps.Maptype tipoMapa = StaticMaps.Maptype.roadmap;
		switch (JCombo_ME_TipoMapa.getSelectedItem().toString()) {
		case "RoadMap":
			tipoMapa = StaticMaps.Maptype.roadmap;
			break;
		case "Satellite":
			tipoMapa = StaticMaps.Maptype.satellite;
			break;
		case "Hybrid":
			tipoMapa = StaticMaps.Maptype.hybrid;
			break;
		case "Ground":
			tipoMapa = StaticMaps.Maptype.terrain;
			break;
		}

		return tipoMapa;
	}

	/***********************************************************/
	private StaticMaps.Format seleccionarFormato() {
		StaticMaps.Format formato = StaticMaps.Format.png;
		switch (JCombo_ME_Formato.getSelectedItem().toString()) {
		case "png":
			formato = StaticMaps.Format.png;
			break;
		case "png32":
			formato = StaticMaps.Format.png32;
			break;
		case "gif":
			formato = StaticMaps.Format.gif;
			break;
		case "jpg":
			formato = StaticMaps.Format.jpg;
			break;
		case "jpg_baseline":
			formato = StaticMaps.Format.jpg_baseline;
			break;
		}
		return formato;
	}

	/************************************************************/
	private void crearMapa() throws MalformedURLException, UnsupportedEncodingException {
		Image imagenMapa = ObjStaticMaps.getStaticMap(cbx_lib.getSelectedItem().toString(),
				Integer.valueOf(JText_ME_Zoom.getText()), new Dimension(500, 500),
				Integer.valueOf(JText_ME_Escala.getText()), this.seleccionarFormato(), this.seleccionarTipoMapa());
		if (imagenMapa != null) {
			ImageIcon imgIcon = new ImageIcon(imagenMapa);
			Icon iconImage = (Icon) imgIcon;
			JLabel_ME_Imagen.setIcon(iconImage);
		}

	}

	/**********************/
	public void Aff_WESTERN() {

		cbx_lib.addItem("California");
		cbx_lib.addItem("Alaska");
		cbx_lib.addItem("Colorado");
		cbx_lib.addItem("Idaho");
		cbx_lib.addItem("Montana");
		cbx_lib.addItem("Nevada");
		cbx_lib.addItem("New Mexico");
		cbx_lib.addItem("Oregon");
		cbx_lib.addItem("Utah");
		cbx_lib.addItem("Wyoming");

	}

	/**************************/
	public void Aff_CANADA() {

		cbx_lib.addItem("Alberta");
		cbx_lib.addItem("British Columbia");
		cbx_lib.addItem("Quebec");

	}

	/**************************/
	public void Aff_EASTERN() {

		cbx_lib.addItem("Maine");
		cbx_lib.addItem("New Hampshire");
		cbx_lib.addItem("New York");
		cbx_lib.addItem("Vermont");

	}

	/**************************/
	public void Aff_EUROPE() {

		cbx_lib.addItem("Andorra");
		cbx_lib.addItem("Austria");
		cbx_lib.addItem("France");
		cbx_lib.addItem("Germany");
		cbx_lib.addItem("Italy");
		cbx_lib.addItem("Switzerland");

	}

	/**************************/
	public void Aff_AMERICA() {

		cbx_lib.addItem("Argentina");
		cbx_lib.addItem("Chile");

	}

	/**************************/
	public void Aff_ASIA() {

		cbx_lib.addItem("Japan");

	}

	private void filter(String query) {
		DefaultTableModel TableModel = (DefaultTableModel) EventTable.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(TableModel);
		EventTable.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));

	}

	private void filterTraining(String queryTraining) {
		DefaultTableModel TableModel2 = (DefaultTableModel) TrainingTable.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(TableModel2);
		TrainingTable.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(queryTraining));

	}

	private void CreatePdf(String TrainingNamePdf, String TrainingDescriptionPdf, String TrainingStartingPdf,
			String TrainingEndingPdf) {
		try {

			String fileName = "TrainingFile.pdf";

			PDDocument doc = new PDDocument();
			PDPage page = new PDPage();
			PDImageXObject pdImage = PDImageXObject.createFromFile("H:/ski-icon-web1.png", doc);
			doc.addPage(page);

			PDPageContentStream content = new PDPageContentStream(doc, page);

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 26);
			content.moveTextPositionByAmount(220, 750);
			content.drawString("TRAINING FORM ");
			content.endText();

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 16);
			content.moveTextPositionByAmount(80, 700);
			content.drawString("TRAINING NAME : " + TrainingNamePdf);
			content.endText();

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 16);
			content.moveTextPositionByAmount(80, 650);
			content.drawString("TRAINING DESCRIPTION : " + TrainingDescriptionPdf);
			content.endText();

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 16);
			content.moveTextPositionByAmount(80, 600);
			content.drawString("STARTING DATE : " + TrainingStartingPdf);
			content.endText();

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 16);
			content.moveTextPositionByAmount(80, 550);
			content.drawString("ENDING DATE : " + TrainingEndingPdf);
			content.endText();

			content.drawImage(pdImage, 120, 250);

			content.close();
			doc.save(fileName);
			doc.close();

		} catch (IOException | CacheException e) {

			System.out.println(e.getMessage());

		}
	}
}
