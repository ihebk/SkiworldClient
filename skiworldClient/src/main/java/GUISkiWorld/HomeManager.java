package GUISkiWorld;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.ejb.Startup;
import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import contracts.HotelCrudEJBRemote;
import entities.Hotel;
import junit.framework.Test;
import models.HotelModel;
import skiworldClient.SMS;
import skiworldClient.test;

import javax.swing.ImageIcon;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;

import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class HomeManager {

	private JFrame ManagerGUI;
	private JTextField i_hotelname;
	private JTextField i_imagepath;
	private String image;
	private JTable i_table_hotel;
	HotelModel hotelmodel = new HotelModel();
	public Hotel row = null;
	private BufferedImage img_display;

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

		// SMS.main(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 76, 768, 344);
		ManagerGUI.getContentPane().add(tabbedPane);

		JPanel panel_i = new JPanel();
		tabbedPane.addTab("Manage Hotels", null, panel_i, null);

		JLabel lblHotelName_i = new JLabel("Hotel  name :");
		lblHotelName_i.setBounds(10, 27, 99, 25);
		lblHotelName_i.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JTextArea i_hoteldescription = new JTextArea();
		i_hoteldescription.setBounds(139, 82, 204, 69);
		panel_i.add(i_hoteldescription);

		i_hotelname = new JTextField();
		i_hotelname.setBounds(139, 31, 149, 20);
		i_hotelname.setColumns(10);

		JLabel i_lblDescription = new JLabel("Description :");
		i_lblDescription.setBounds(10, 82, 99, 25);
		i_lblDescription.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JLabel i_lblCapacity = new JLabel("Capacity :");
		i_lblCapacity.setBounds(10, 174, 99, 25);
		i_lblCapacity.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JSpinner i_hotelcapacity = new JSpinner();
		i_hotelcapacity.setBounds(139, 178, 123, 20);

		JLabel i_lblRating = new JLabel("Image :");
		i_lblRating.setBounds(10, 226, 99, 25);
		i_lblRating.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JButton i_btnNewButton = new JButton("Add");
		i_btnNewButton.setBounds(39, 288, 99, 23);
		i_btnNewButton.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InitialContext ctx;
				try {
					ctx = new InitialContext();
					HotelCrudEJBRemote proxy_hotel = (HotelCrudEJBRemote) ctx
							.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote");

					Hotel hotel = new Hotel();
					hotel.setDescription(i_hoteldescription.getText());
					hotel.setName(i_hotelname.getText());
					hotel.setCapacity((Integer) i_hotelcapacity.getValue());

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

					proxy_hotel.addHotel(hotel);
					i_table_hotel.setModel(hotelmodel.hotelModel());
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
		panel_i.add(i_hotelname);
		panel_i.add(i_lblDescription);
		panel_i.add(i_lblCapacity);
		panel_i.add(i_hotelcapacity);
		panel_i.add(i_lblRating);
		panel_i.add(i_btnNewButton);

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

		JLabel i_imgdisplay = new JLabel("New label");
		i_imgdisplay.setBounds(516, 187, 150, 130);
		i_imgdisplay.setIcon((Icon) img_display);
		panel_i.add(i_imgdisplay);

		JScrollPane i_scrollPane = new JScrollPane();

		i_scrollPane.setBounds(424, 11, 329, 165);
		panel_i.add(i_scrollPane);

		i_table_hotel = new JTable();
		i_table_hotel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = i_table_hotel.getSelectedRow();
				row = hotelmodel.hotellist.get(index);

				try {
					FileOutputStream fos = new FileOutputStream("C:\\Games\\test\\new.jpg");
					fos.write(row.getImage());
					fos.close();
					ImageIcon imgThisImg1 = new ImageIcon("C:\\Games\\test\\new.jpg");
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

					i_hotelname.setText(row.getName());
					i_hoteldescription.setText(row.getDescription());
					i_hotelcapacity.setValue(row.getCapacity());

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
				row = null;
				int index = i_table_hotel.getSelectedRow();

				if (index != -1) {

					row = hotelmodel.hotellist.get(index);

					InitialContext ctx;
					try {
						ctx = new InitialContext();
						HotelCrudEJBRemote proxy_hotel = (HotelCrudEJBRemote) ctx
								.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote");
						Hotel hotel = new Hotel();
						hotel.setIdHotel(row.getIdHotel());
						hotel.setDescription(i_hoteldescription.getText());
						hotel.setName(i_hotelname.getText());
						hotel.setCapacity((Integer) i_hotelcapacity.getValue());

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

						proxy_hotel.updateHotel(hotel);
						i_table_hotel.setModel(hotelmodel.hotelModel());

					} catch (NamingException | SQLException | IOException e1) {
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
		panel_i.add(i_edit);

		JButton i_remove = new JButton("Remove");
		i_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = null;
				int index = i_table_hotel.getSelectedRow();

				if (index != -1) {

					row = hotelmodel.hotellist.get(index);

					InitialContext ctx;
					try {
						ctx = new InitialContext();
						HotelCrudEJBRemote proxy_hotel = (HotelCrudEJBRemote) ctx
								.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote");

						proxy_hotel.deleteHotel(row.getIdHotel());
						i_table_hotel.setModel(hotelmodel.hotelModel());

					} catch (NamingException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select a hotel to remove");
				}

			}
		});
		i_remove.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		i_remove.setBounds(321, 290, 89, 23);
		panel_i.add(i_remove);

		JLabel i_lblNewLabel_1 = new JLabel("New label");
		i_lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Iheb\\Desktop\\15755113534_f75fd1636c_k.jpg"));
		i_lblNewLabel_1.setBounds(0, 0, 763, 316);
		panel_i.add(i_lblNewLabel_1);
		JPanel panel = new JPanel();

		JLabel i_label = new JLabel("New label");
		i_label.setIcon(new ImageIcon(
				"C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));

		i_label.setBounds(0, 0, 768, 398);
		ManagerGUI.getContentPane().add(i_label);
	}
}
