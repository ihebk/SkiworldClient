package GUISkiWorld;

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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.apache.regexp.RE;

import business.UserBusiness;
import business.UserDelegate;
import contracts.HotelCrudEJBRemote;
import entities.Hotel;
import entities.User;
import skiworldClient.test;

public class HomeManager {

	private JFrame ManagerGUI;
	private JTextField textField_i;
	private JTextField i_imagepath;
	private String image;
	private JTable table;
	public DefaultTableModel tableModel;
	test t = new test();
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
		ManagerGUI.setBounds(100, 100, 784, 475);
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
		JPanel panel = new JPanel();

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));

		lblNewLabel.setBounds(0, 0, 780, 436);
		ManagerGUI.getContentPane().add(lblNewLabel);
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

	}
}
