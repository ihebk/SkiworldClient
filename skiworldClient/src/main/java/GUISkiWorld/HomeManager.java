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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.Border;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import businessDelegates.ResortBusinessDelegate;
import businessDelegates.StoreBusinessDelegate;
import entities.Resort;
import entities.Store;
import tableModels.ResortModel;
import tableModels.StoreModel;

import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JTextField;
import javax.swing.UIManager;

import business.HotelBusiness;
import business.PisteBusiness;
import business.ResortBusiness;
import business.TransportBusiness;
import contracts.HotelCrudEJBRemote;
import contracts.ResortCrudEJBRemote;
import entities.Hotel;
import entities.Piste;
import entities.Transport;
import models.HotelModel;
import models.PisteModel;
import models.TransportModel;
import skiworldClient.SMS;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;
import javax.swing.DefaultComboBoxModel;

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
	private JTable i_table_hotel;
	HotelModel hotelmodel = new HotelModel();
	TransportModel transportmodel = new TransportModel();
	PisteModel pistemodel = new PisteModel();
	public Hotel hotelrow = null;
	public Transport trrow = null;
	public Piste pirow = null;
	private BufferedImage img_display;
	HotelBusiness hotelBusiness = new HotelBusiness();
	ResortBusiness resortBusiness = new ResortBusiness();
	TransportBusiness transportBusiness = new TransportBusiness();
	PisteBusiness pisteBusiness = new PisteBusiness();
	private JTextField i_tr_path;
	private JTable i_tr_table;
	private JTextField i_tr_price;
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
	 */

	public HomeManager() throws NamingException, SQLException, IOException {
		initialize();
		fillCbResort();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws NamingException
	 * @throws IOException
	 * @throws SQLException
	 */

	private void initialize() throws NamingException, SQLException, IOException {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");

		} catch (Exception e) {
		}
		ManagerGUI = new JFrame();
		ManagerGUI.setBounds(100, 100, 784, 459);
		ManagerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManagerGUI.getContentPane().setLayout(null);
		ManagerGUI.getContentPane().setLayout(null);

		// SMS.main(null, "+21624056027", "hello");
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

		i_hotelname = new JTextField();
		i_hotelname.setBounds(139, 31, 149, 20);
		i_hotelname.setColumns(10);

		JLabel i_lblDescription = new JLabel("Description :");
		i_lblDescription.setBounds(10, 60, 99, 25);
		i_lblDescription.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JLabel i_lblCapacity = new JLabel("Capacity :");
		i_lblCapacity.setBounds(10, 144, 99, 25);
		i_lblCapacity.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JSpinner i_hotelcapacity = new JSpinner();
		i_hotelcapacity.setBounds(139, 148, 123, 20);

		JComboBox i_hotel_combo = new JComboBox();
		i_hotel_combo.setBounds(139, 238, 113, 20);
		panel_i_hotel.add(i_hotel_combo);

		JLabel i_lblRating = new JLabel("Image :");
		i_lblRating.setBounds(10, 186, 99, 25);
		i_lblRating.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JButton i_btnNewButton = new JButton("Add");
		i_btnNewButton.setBounds(39, 288, 99, 23);
		i_btnNewButton.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
					hotelBusiness.getProxy().addHotel(hotel);

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
				image = f.getAbsolutePath();

				i_imagepath.setText(image);
			}
		});
		i_chooseimage.setBounds(139, 186, 113, 23);
		panel_i_hotel.add(i_chooseimage);

		i_imagepath = new JTextField();
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

		i_table_hotel = new JTable();
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

		i_table_hotel.setModel(hotelmodel.hotelModel());
		i_scrollPane.setViewportView(i_table_hotel);

		JButton i_edit = new JButton("Edit");
		i_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotelrow = null;
				int index = i_table_hotel.getSelectedRow();

				if (index != -1) {

					hotelrow = hotelmodel.hotellist.get(index);

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
						hotelBusiness.getProxy().updateHotel(hotel);
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
						hotelBusiness.getProxy().deleteHotel(hotelrow.getIdHotel());

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

		JLabel i_lblNewLabel_1 = new JLabel("New label");
		i_lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Iheb\\Desktop\\15755113534_f75fd1636c_k.jpg"));
		i_lblNewLabel_1.setBounds(0, 0, 763, 316);
		panel_i_hotel.add(i_lblNewLabel_1);

		JPanel panel_i_trans = new JPanel();
		panel_i_trans.setLayout(null);
		tabbedPane.addTab("Manage Transport", null, panel_i_trans, null);

		JTextArea i_tr_descriptiont = new JTextArea();
		i_tr_descriptiont.setBounds(139, 46, 204, 69);
		panel_i_trans.add(i_tr_descriptiont);

		JComboBox i_tr_resort_combo = new JComboBox();
		i_tr_resort_combo.setBounds(139, 238, 113, 20);
		panel_i_trans.add(i_tr_resort_combo);

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
		i_tr_cap.setBounds(139, 126, 123, 20);
		panel_i_trans.add(i_tr_cap);

		JLabel i_label_3 = new JLabel("Image :");
		i_label_3.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_label_3.setBounds(10, 156, 99, 25);
		panel_i_trans.add(i_label_3);

		JComboBox i_tr_type_combo = new JComboBox();
		i_tr_type_combo.setModel(new DefaultComboBoxModel(new String[] { "Car", "JetSki", "Sky tram" }));
		i_tr_type_combo.setBounds(139, 15, 204, 20);
		panel_i_trans.add(i_tr_type_combo);

		JButton i_tr_add = new JButton("Add");
		i_tr_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Transport transport = new Transport();
				transport.setType(i_tr_type_combo.getSelectedItem().toString());

				transport.setDescription(i_tr_descriptiont.getText());
				transport.setBookingPrice(Float.parseFloat(i_tr_price.getText()));
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
					transportBusiness.getProxy().addTransport(transport);
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
				image = f.getAbsolutePath();

				i_tr_path.setText(image);

			}
		});
		i_tr_choose_img.setBounds(139, 157, 113, 23);
		panel_i_trans.add(i_tr_choose_img);

		i_tr_path = new JTextField();
		i_tr_path.setColumns(10);
		i_tr_path.setBounds(262, 160, 148, 20);
		panel_i_trans.add(i_tr_path);

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
					transport.setBookingPrice(Float.parseFloat(i_tr_price.getText()));
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
						transportBusiness.getProxy().updateTransport(transport);
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
						transportBusiness.getProxy().deleteTransport(trrow.getIdTransport());

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

		transportmodel.fillResortComboBox(i_tr_resort_combo);

		JScrollPane i_tr_scrollPane = new JScrollPane();
		i_tr_scrollPane.setBounds(420, 11, 333, 157);
		panel_i_trans.add(i_tr_scrollPane);

		i_tr_table = new JTable();
		i_tr_table.setModel(transportmodel.transportModel());

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
					i_tr_price.setText(String.valueOf(trrow.getBookingPrice()));

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

		i_tr_price = new JTextField();
		i_tr_price.setBounds(139, 207, 123, 20);
		panel_i_trans.add(i_tr_price);
		i_tr_price.setColumns(10);

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

		JComboBox i_pi_resortcombo = new JComboBox();
		i_pi_resortcombo.setBounds(139, 238, 113, 20);
		panel_i_piste.add(i_pi_resortcombo);

		JButton i_pi_add = new JButton("Add");
		i_pi_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
					pisteBusiness.getProxy().addPiste(piste);

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
				image = f.getAbsolutePath();

				i_pi_path.setText(image);

			}
		});
		i_pi_chooseimage.setBounds(139, 186, 113, 23);
		panel_i_piste.add(i_pi_chooseimage);

		i_pi_path = new JTextField();
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
		i_pi_table.setModel(pistemodel.pisteModel());
		i_pi_scrollPane.setViewportView(i_pi_table);

		JButton i_pi_edit = new JButton("Edit");
		i_pi_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pirow = null;
				int pi_index = i_pi_table.getSelectedRow();

				if (pi_index != -1) {

					pirow = pistemodel.pistelist.get(pi_index);

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
						pisteBusiness.getProxy().updatePiste(piste);
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
						pisteBusiness.getProxy().deletePiste(pirow.getIdPiste());

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
				StoreBusinessDelegate rdb = new StoreBusinessDelegate();
				ResortBusinessDelegate res = new ResortBusinessDelegate();
				Store r = new Store();
				int testPH = 0, testEm = 0;

				try {
					r.setResort(res.getResortProxy().findAllResort().get(resortStore.getSelectedIndex()));
				} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					for (int i = 0; i < rdb.getStoreProxy().findAllStore().size(); i++) {
						if (rdb.getStoreProxy().findAllStore().get(i).getEmail().equals(emailStore.getText()))
							testEm = 1;
					}
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
						try {
							for (int i = 0; i < rdb.getStoreProxy().findAllStore().size(); i++) {
								if (rdb.getStoreProxy().findAllStore().get(i).getPhone() == Long
										.parseLong(phoneStore.getText()))
									lblAlreadyExistPhR.setVisible(true);
							}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NamingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					if (testEm == 1)
						lblAlreadyExistEmR.setVisible(true);

				} else {
					try {
						if (!rdb.getStoreProxy().addStore(r)) {
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
					int testPH = 0, testEm = 0;
					StoreBusinessDelegate rdb = new StoreBusinessDelegate();
					ResortBusinessDelegate res = new ResortBusinessDelegate();

					try {
						for (int i = 0; i < rdb.getStoreProxy().findAllStore().size(); i++) {
							if (rdb.getStoreProxy().findAllStore().get(i).getEmail().equals(emailStore.getText()))
								testEm = 1;
							if (rdb.getStoreProxy().findAllStore().get(i).getPhone() == Long
									.parseLong(phoneStore.getText()))
								testPH = 1;

						}
						if ((storeName.getText().length() == 0) || (storeDescription.getText().length() == 0)
								|| (storeLocation.getText().length() == 0) || (emailStore.getText().length() == 0)
								|| (phoneStore.getText().length() == 0) || (testEm == 1) || (testPH == 1)) {

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
							if (testPH == 1)
								lblAlreadyExistPhR.setVisible(true);
							if (testEm == 1)
								lblAlreadyExistEmR.setVisible(true);
						} else {
							r.setIdStore(rowSelectedStore.getIdStore());
							r.setName(storeName.getText());
							r.setDescription(storeDescription.getText());
							r.setResort(rowSelectedStore.getResort());
							r.setLocation(storeLocation.getText());
							r.setEmail(emailStore.getText());
							r.setPhone(Long.parseLong(phoneStore.getText()));
							r.setResort(res.getResortProxy().findAllResort().get(resortStore.getSelectedIndex()));
							if (!rdb.getStoreProxy().updateStore(r)) {
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
								lblAlreadyExistEmR.setVisible(false);
								lblAlreadyExistPhR.setVisible(false);
							}
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
						rdb.getStoreProxy().removeStore(r);
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
		ResortBusinessDelegate bdr = new ResortBusinessDelegate();
		List<Resort> list = new ArrayList<>();
		list = bdr.getResortProxy().findAllResort();
		for (Resort name : list)
			resortStore.addItem(name.getName());

	}
}
