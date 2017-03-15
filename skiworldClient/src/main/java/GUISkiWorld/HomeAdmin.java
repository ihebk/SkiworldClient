package GUISkiWorld;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import javax.mail.MessagingException;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.regexp.RE;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import business.PisteDelegate;
import business.PostBusiness;
import business.ResortBusinessDelegate;
import business.UserBusiness;
import business.UserDelegate;
import entities.Post;
import entities.Resort;
import entities.Store;
import entities.User;
import models.PostsModel;
import models.ResortModel;
import models.StoreModel;
import models.UsersModel;
import statUtil.CustomRenderer;
import statUtil.Statistic;

public class HomeAdmin {

	private JTextField resortName;
	private JTextField resortLocation;
	private String image;
	private JTable resortTable;
	public Resort rowSelectedResort;
	public JLabel lblAdresREs;
	public List<Resort> listResort;
	public Store rowSelectedStore;
	public List<Resort> listStore;
	private BufferedImage img_display;
	public JPanel statTrackP, statCountResort;
	private JTextField searchResort;
	private JFrame AdminGUI;
	private JTable tableUsers;
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
	public User userRowSelected;
	public Post postRowSelected;
	public String OldRole;
	public UserBusiness h_proxy;
	public PostBusiness proxyPost;
	private JTable tablePost;
	private JTextField h_tf_likes;
	private JTextField h_tf_reports;
	private JTextArea h_ta_content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeAdmin window = new HomeAdmin();
					window.AdminGUI.setVisible(true);
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

	void setPostTextFields(String content, String nbrlikes, String nbrreports) {
		h_tf_reports.setText(nbrreports);
		h_tf_likes.setText(nbrlikes);
		h_ta_content.setText(content);
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public HomeAdmin() throws SQLException, IOException, NamingException {
		initialize();
		statTrackFN();
		statRes();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	private void initialize() throws SQLException, IOException, NamingException {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");

		} catch (Exception e) {
		}

		UsersModel usermodel = new UsersModel();
		PostsModel postsmodel = new PostsModel();
		AdminGUI = new JFrame();
		AdminGUI.setBounds(100, 100, 885, 538);
		AdminGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AdminGUI.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 869, 499);
		AdminGUI.getContentPane().add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 869, 499);
		panel.add(tabbedPane);

		JPanel h_panel_users = new JPanel();
		tabbedPane.addTab("Manage Users", null, h_panel_users, null);
		h_panel_users.setLayout(null);

		JScrollPane scrollPaneUsers = new JScrollPane();

		scrollPaneUsers.setBounds(51, 33, 329, 198);
		h_panel_users.add(scrollPaneUsers);

		tableUsers = new JTable();
		tableUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableUsers.getSelectedRow();
				h_proxy = new UserBusiness();
				List<User> listUsers;
				try {
					listUsers = h_proxy.getProxy().findAllUsers();
					userRowSelected = listUsers.get(index);
					OldRole = userRowSelected.getRole();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (userRowSelected.getRole().equals("user")) {
					System.out.println(userRowSelected);
				}

			}
		});
		tableUsers.setModel(usermodel.userModel());
		scrollPaneUsers.setViewportView(tableUsers);

		JLabel h_lblRole_validateRole = new JLabel("Role :");
		h_lblRole_validateRole.setBounds(390, 61, 46, 14);
		h_panel_users.add(h_lblRole_validateRole);

		JComboBox h_comboRole = new JComboBox();
		h_comboRole.setModel(new DefaultComboBoxModel(new String[] { "manager", "user" }));
		h_comboRole.setBounds(446, 58, 88, 20);
		h_panel_users.add(h_comboRole);

		JButton btnNewButton_2 = new JButton("Validate user");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (userRowSelected == null) {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "please select a user");
				} else if (userRowSelected.getRole().equals("admin")) {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "you can not change the role of admin");
				} else {

					String role = h_comboRole.getSelectedItem().toString();
					User user = userRowSelected;
					user.setRole(role);
					// if (OldRole.equals("user") && role.equals("lecteur")) {
					//
					// try {
					// mail.sendEmail(utilRowSelected.getEmail(), "Inscription
					// confirmée",
					// "Bienvenue dans notre application.\nVotre compte est
					// désormais actif.");
					// } catch (MessagingException ex) {
					// Logger.getLogger(Ubook.class.getName()).log(Level.SEVERE,
					// null, ex);
					// }
					// }

					service.mail mail = new service.mail();

					if (OldRole.equals("user") && role.equals("manager")) {

						try {
							mail.sendEmail(userRowSelected.getEmail(), "Account validated",
									"welcome to our application.\nyour account is now activated.");
						} catch (MessagingException ex) {
							// Logger.getLogger(Ubook.class.getName()).log(Level.SEVERE,
							// null, ex);
						}
					}

					try {
						if ((h_proxy.getProxy().updateUser(user))) {

							JDialog dialog = new JDialog();
							dialog.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(dialog, "Manager Validated !");
						} else {
							JDialog dialog = new JDialog();
							dialog.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(dialog, "Problem of modification");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SecurityException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					h_comboRole.setSelectedIndex(-1);

					userRowSelected = null;
					try {
						tableUsers.setModel(usermodel.userModel());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btnNewButton_2.setBounds(544, 57, 116, 23);
		h_panel_users.add(btnNewButton_2);
		
		JLabel h_backgound = new JLabel("");
		h_backgound.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		h_backgound.setBounds(10, -45, 854, 516);
		h_panel_users.add(h_backgound);
		JPanel h_panel_profil = new JPanel();
		JLabel h_lbl_image = new JLabel("");
		h_lbl_image.setBounds(425, 2, 126, 93);
		h_panel_profil.add(h_lbl_image);
		h_panel_profil.addMouseListener(new MouseAdapter() {
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
		tabbedPane.addTab("Profil", null, h_panel_profil, null);
		h_panel_profil.setLayout(null);

		JLabel h_lbl_status = new JLabel("You are");
		h_lbl_status.setBounds(35, 35, 46, 22);
		h_panel_profil.add(h_lbl_status);

		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(24, 78, 72, 14);
		h_panel_profil.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(24, 115, 57, 14);
		h_panel_profil.add(lblLastName);

		h_lbl_role = new JLabel("");
		h_lbl_role.setBounds(109, 39, 46, 14);
		h_panel_profil.add(h_lbl_role);

		JLabel h_lbl_phone = new JLabel("Phone");
		h_lbl_phone.setBounds(24, 269, 46, 14);
		h_panel_profil.add(h_lbl_phone);

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
		h_panel_profil.add(h_tf_phone);
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
		h_panel_profil.add(h_tf_firstname);
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
		h_panel_profil.add(h_tf_lastname);
		h_tf_lastname.setColumns(10);

		JLabel h_lblAddress = new JLabel("Address");
		h_lblAddress.setBounds(24, 227, 46, 14);
		h_panel_profil.add(h_lblAddress);

		h_tf_address = new JTextField();
		h_tf_address.setEnabled(false);
		h_tf_address.setBounds(105, 224, 86, 20);
		h_panel_profil.add(h_tf_address);
		h_tf_address.setColumns(10);

		JLabel lblGender = new JLabel("gender");
		lblGender.setBounds(24, 181, 46, 14);
		h_panel_profil.add(lblGender);

		h_lbl_genderview = new JLabel("");
		h_lbl_genderview.setBounds(105, 181, 86, 14);
		h_panel_profil.add(h_lbl_genderview);

		JButton btnNewButton = new JButton("Choose a photo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch1 = new JFileChooser();
				ch1.showOpenDialog(null);
				File f1 = ch1.getSelectedFile();
				h_image = f1.getAbsolutePath();

				// i_imagepath.setText(image);

			}
		});
		btnNewButton.setBounds(561, 11, 109, 23);
		h_panel_profil.add(btnNewButton);

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

				UserDelegate.updateUser(Authentification.userConnected);

			}
		});
		btnNewButton_1.setBounds(561, 39, 109, 23);
		h_panel_profil.add(btnNewButton_1);

		JLabel h_lblEmail = new JLabel("Email");
		h_lblEmail.setBounds(373, 118, 57, 14);
		h_panel_profil.add(h_lblEmail);

		h_tf_email = new JTextField();
		h_tf_email.setEnabled(false);
		h_tf_email.setBounds(452, 115, 134, 20);
		h_panel_profil.add(h_tf_email);
		h_tf_email.setColumns(10);

		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setBounds(466, 181, 120, 14);
		h_panel_profil.add(lblChangePassword);

		JLabel h_lblNewPassword = new JLabel("New Password");
		h_lblNewPassword.setBounds(354, 205, 86, 14);
		h_panel_profil.add(h_lblNewPassword);

		JLabel h_lblConfirmPassword = new JLabel("Confirm Password");
		h_lblConfirmPassword.setBounds(354, 247, 88, 14);
		h_panel_profil.add(h_lblConfirmPassword);

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

						try {

							if (proxy.getProxy().updateUser(u)) {
								System.out.println(u);
								Authentification.userConnected = u;
								JDialog dialog = new JDialog();
								dialog.setAlwaysOnTop(true);

								JOptionPane.showMessageDialog(dialog, "information updated");
							}
						} catch (NamingException e1) {

							e1.printStackTrace();
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
		btnUpdate.setBounds(276, 298, 185, 23);
		h_panel_profil.add(btnUpdate);

		h_tf_password = new JPasswordField();
		h_tf_password.setEnabled(false);
		h_tf_password.setBounds(452, 202, 134, 20);
		h_panel_profil.add(h_tf_password);

		h_tf_paswwordConfirm = new JPasswordField();
		h_tf_paswwordConfirm.setEnabled(false);
		h_tf_paswwordConfirm.setBounds(452, 244, 134, 20);
		h_panel_profil.add(h_tf_paswwordConfirm);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		label.setBounds(0, 2, 864, 469);
		h_panel_profil.add(label);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Reported posts", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane h_postPanel = new JScrollPane();
		// scrollPane_1.setViewportView(table);

		h_postPanel.setBounds(20, 5, 488, 135);
		panel_1.add(h_postPanel);
		h_postPanel.setViewportView(tablePost);
		tablePost = new JTable();

		tablePost.setModel(postsmodel.postModel());
		h_postPanel.setViewportView(tablePost);

		h_ta_content = new JTextArea();
		h_ta_content.setEnabled(false);
		h_ta_content.setBounds(20, 189, 248, 88);
		panel_1.add(h_ta_content);

		JLabel lblContent = new JLabel("Content :");
		lblContent.setBounds(20, 164, 46, 14);
		panel_1.add(lblContent);

		JLabel lblNewLabel = new JLabel("number of likes :");
		lblNewLabel.setBounds(278, 194, 99, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("number of Reports :");
		lblNewLabel_1.setBounds(278, 240, 99, 14);
		panel_1.add(lblNewLabel_1);

		h_tf_likes = new JTextField();
		h_tf_likes.setEnabled(false);
		h_tf_likes.setBounds(387, 191, 86, 20);
		panel_1.add(h_tf_likes);
		h_tf_likes.setColumns(10);

		h_tf_reports = new JTextField();
		h_tf_reports.setEnabled(false);
		h_tf_reports.setBounds(387, 237, 86, 20);
		panel_1.add(h_tf_reports);
		h_tf_reports.setColumns(10);
		tablePost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index1 = tablePost.getSelectedRow();
				proxyPost = new PostBusiness();
				List<Post> listPosts;

				// listPosts = proxyPost.getProxy().findReportedPosts();
				postRowSelected = postsmodel.getPost().get(index1);
				System.out.println(index1);
				System.out.println(postRowSelected.toString());
				setPostTextFields(postRowSelected.getContent(), String.valueOf(postRowSelected.getNbrLikes()),
						String.valueOf(postRowSelected.getNbrReport()));

			}
		});
		JButton h_btn_remove = new JButton("Remove post");
		h_btn_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proxyPost = new PostBusiness();
				if (postRowSelected == null) {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "please select a post");
					return;
				}

				try {
					postRowSelected.setIsDeleted(1);
					proxyPost.getProxy().updatePost(postRowSelected);
					// PostDelagate.removePost(postRowSelected);
					System.out.println(postRowSelected.getPostOwner().toString());
					postRowSelected.getPostOwner().setNbrBlock(postRowSelected.getPostOwner().getNbrBlock() + 1);
					UserDelegate.updateUser(postRowSelected.getPostOwner());
					if (postRowSelected.getPostOwner().getNbrBlock() == 3) {
						postRowSelected.getPostOwner().setRole("blocked");
						UserDelegate.updateUser(postRowSelected.getPostOwner());

						service.mail mail = new service.mail();
						try {
							mail.sendEmail(postRowSelected.getPostOwner().getEmail(), "Account Blocked",
									"Your account has been blocked due to violation of posting terms and conditions");

						} catch (MessagingException ex) {

						}
					}
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Post removed !");
					postRowSelected = null;
					tablePost.setModel(postsmodel.postModel());

				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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

			}
		});
		h_btn_remove.setBounds(543, 236, 112, 23);
		panel_1.add(h_btn_remove);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		lblNewLabel_2.setBounds(0, 0, 864, 471);
		panel_1.add(lblNewLabel_2);

		setTextFields();

		/////// ***********Thabet*****//////////////
		JPanel resorts = new JPanel();
		tabbedPane.addTab("Resorts Management", null, resorts, null);

		JLabel lblResortName = new JLabel("Resort name :");
		lblResortName.setBounds(10, 27, 99, 25);
		lblResortName.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		JTextArea resortDescription = new JTextArea();
		resortDescription.setBounds(139, 62, 204, 69);
		resorts.add(resortDescription);

		resortName = new JTextField();
		resortName.setBounds(139, 31, 149, 20);
		resortName.setColumns(10);

		JLabel t_lblDescription = new JLabel("Description :");
		t_lblDescription.setBounds(10, 60, 99, 25);
		t_lblDescription.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));

		JComboBox countryCmb = new JComboBox();
		countryCmb.setModel(new DefaultComboBoxModel(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra",
				"Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan",
				"Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
				"Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso",
				"Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic (CAR)", "Chad",
				"Chile", "China", "Colombia", "Comoros", "Democratic Republic of the Congo", "Republic of the Congo",
				"Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti",
				"Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
				"Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
				"Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary",
				"Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
				"Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
				"Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia",
				"Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania",
				"Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco",
				"Mozambique", "Myanmar (Burma)", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua",
				"Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama",
				"Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania",
				"Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa",
				"San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles",
				"Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
				"South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden",
				"Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga",
				"Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine",
				"United Arab Emirates (UAE)", "United Kingdom (UK)", "United States of America (USA)", "Uruguay",
				"Uzbekistan", "Vanuatu", "Vatican City (Holy See)", "Venezuela", "Vietnam", "Yemen", "Zambia",
				"Zimbabwe" }));
		countryCmb.setBounds(138, 162, 113, 20);
		resorts.add(countryCmb);

		lblAdresREs = new JLabel("Adresse :");
		lblAdresREs.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		lblAdresREs.setBounds(10, 207, 99, 25);
		resorts.add(lblAdresREs);

		JLabel i_lblRating = new JLabel("Country :");
		i_lblRating.setBounds(10, 162, 99, 25);
		i_lblRating.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		JButton addResort = new JButton("Add");
		addResort.setBounds(39, 288, 99, 23);
		addResort.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		addResort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Boolean test = false;
				Resort r = new Resort();

				if ((resortName.getText().length() == 0) || (resortDescription.getText().length() == 0)
						|| (resortLocation.getText().length() == 0)) {
					test = true;
					System.out.println("ffffffffffff");
					if (resortName.getText().length() == 0)
						lblResortName.setForeground(Color.RED);
					if (resortDescription.getText().length() == 0)
						t_lblDescription.setForeground(Color.RED);
					if (resortLocation.getText().length() == 0)
						lblAdresREs.setForeground(Color.RED);

				} else {
					try {

						r.setName(resortName.getText());
						r.setDescription(resortDescription.getText());
						r.setCountry(countryCmb.getSelectedItem().toString());
						r.setLocation(resortLocation.getText());
						ResortBusinessDelegate.addResort(r);
						lblResortName.setForeground(Color.BLACK);
						t_lblDescription.setForeground(Color.BLACK);
						lblAdresREs.setForeground(Color.BLACK);
						resortName.setText("");
						resortDescription.setText("");
						resortLocation.setText("");
						ResortModel resortmodel = new ResortModel();
						resortTable.setModel(resortmodel.getResortModel(null));
						statRes();

					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		resorts.setLayout(null);
		resorts.add(lblResortName);
		resorts.add(resortName);
		resorts.add(t_lblDescription);
		resorts.add(i_lblRating);
		resorts.add(addResort);

		resortLocation = new JTextField();
		resortLocation.setBounds(139, 212, 148, 20);
		resorts.add(resortLocation);
		resortLocation.setColumns(10);

		JLabel i_imgdisplay = new JLabel("");
		i_imgdisplay.setBounds(516, 187, 150, 130);
		i_imgdisplay.setIcon((Icon) img_display);
		resorts.add(i_imgdisplay);

		JScrollPane t_scrollPane = new JScrollPane();

		t_scrollPane.setBounds(456, 47, 329, 253);
		resorts.add(t_scrollPane);

		resortTable = new JTable();
		ResortModel resortModel = new ResortModel();
		resortTable.setModel(resortModel.getResortModel(null));
		t_scrollPane.setViewportView(resortTable);
		resortTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = resortTable.getSelectedRow();
				ResortModel rt;

				try {
					rt = new ResortModel();
					rowSelectedResort = rt.getResortList().get(index);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				resortName.setText(rowSelectedResort.getName());
				resortDescription.setText(rowSelectedResort.getDescription());
				resortLocation.setText(rowSelectedResort.getLocation());

				addResort.setEnabled(false);
			}
		});

		JButton editResort = new JButton("Edit");
		editResort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rowSelectedResort == null) {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Please select a resort to edit");
				} else {
					Resort r = new Resort();
					ResortBusinessDelegate rdb = new ResortBusinessDelegate();
					r.setIdResort(rowSelectedResort.getIdResort());
					r.setName(resortName.getText());
					r.setDescription(resortDescription.getText());
					r.setCountry(countryCmb.getSelectedItem().toString());
					r.setLocation(resortLocation.getText());
					if ((resortName.getText().length() == 0) || (resortDescription.getText().length() == 0)
							|| (resortLocation.getText().length() == 0)) {

						System.out.println("ffffffffffff");
						if (resortName.getText().length() == 0)
							lblResortName.setForeground(Color.RED);
						if (resortDescription.getText().length() == 0)
							t_lblDescription.setForeground(Color.RED);
						if (resortLocation.getText().length() == 0)
							lblAdresREs.setForeground(Color.RED);

					} else {
						try {
							ResortBusinessDelegate.updateResort(r);
							ResortModel resortmodel = new ResortModel();
							resortTable.setModel(resortmodel.getResortModel(null));
							lblResortName.setForeground(Color.BLACK);
							t_lblDescription.setForeground(Color.BLACK);
							lblAdresREs.setForeground(Color.BLACK);
							addResort.setEnabled(true);
							resortName.setText("");
							resortDescription.setText("");
							resortLocation.setText("");
							statRes();
							rowSelectedResort = null;

						} catch (NamingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});

		editResort.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		editResort.setBounds(183, 288, 89, 23);
		resorts.add(editResort);

		JButton removeResort = new JButton("Remove");
		removeResort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rowSelectedResort == null) {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Please select a resort to edit");
				} else {
					Resort r = new Resort();
					ResortBusinessDelegate rdb = new ResortBusinessDelegate();
					r.setIdResort(rowSelectedResort.getIdResort());
					r.setName(resortName.getText());
					r.setDescription(resortDescription.getText());
					r.setCountry(countryCmb.getSelectedItem().toString());
					r.setLocation(resortLocation.getText());
					try {
						ResortBusinessDelegate.removeResort(r);
						ResortModel resortmodel = new ResortModel();
						resortTable.setModel(resortmodel.getResortModel(null));
						addResort.setEnabled(true);
						resortName.setText("");
						resortDescription.setText("");
						resortLocation.setText("");
						rowSelectedResort = null;
						statRes();
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		removeResort.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		removeResort.setBounds(321, 288, 89, 23);
		resorts.add(removeResort);

		searchResort = new JTextField();
		searchResort.setBounds(456, 16, 195, 20);
		resorts.add(searchResort);
		searchResort.setColumns(10);
		
				JLabel i_lblNewLabel_1 = new JLabel("");
				i_lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Iheb\\Desktop\\15755113534_f75fd1636c_k.jpg"));
				i_lblNewLabel_1.setBounds(0, 0, 763, 335);
				resorts.add(i_lblNewLabel_1);
				
				JLabel lblNewLabel_3 = new JLabel("");
				lblNewLabel_3.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
				lblNewLabel_3.setBounds(0, 0, 864, 471);
				resorts.add(lblNewLabel_3);
		searchResort.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				ResortModel rm;
				try {
					rm = new ResortModel();
					resortTable.setModel(rm.getResortModel(searchResort.getText()));
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultTableModel TableModel = (DefaultTableModel) resortTable.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(TableModel);
				resortTable.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(searchResort.getText()));

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		JPanel statPanel = new JPanel();
		tabbedPane.addTab("Statistics", null, statPanel, null);
		statPanel.setLayout(null);

		statCountResort = new JPanel();
		statCountResort.setBounds(10, 0, 504, 257);
		statPanel.add(statCountResort);

		statTrackP = new JPanel();
		statTrackP.setBounds(10, 258, 504, 213);
		statPanel.add(statTrackP);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(0, 0, 870, 471);
		statPanel.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon("src\\main\\resources\\wallpaper skiworld.jpg"));
		StoreModel storeModel = new StoreModel();

	}

	public void statTrackFN() throws NamingException {
		int t1 = 0, t2 = 0;
		String ty1 = "Training", ty2 = "Daily Activities";
		DefaultPieDataset dataset1 = new DefaultPieDataset();
		for (int i = 0; i < PisteDelegate.findAllPistes().size(); i++) {
			if (PisteDelegate.findAllPistes().get(i).getType().equals(ty1))
				t1++;
			else
				t2++;
		}
		dataset1.setValue(ty1, t1);
		dataset1.setValue(ty2, t2);
		JFreeChart chart1 = ChartFactory.createPieChart3D("Tracks per Type", dataset1, true, true, true);

		PiePlot3D p = (PiePlot3D) chart1.getPlot();

		final CategoryItemRenderer renderer = new CustomRenderer(new Paint[] { Color.red, Color.blue, Color.green,
				Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.blue });
		ChartPanel chartp1 = new ChartPanel(chart1);
		chartp1.setBounds(0, 0, 503, 212);
		chartp1.setVisible(true);
		statTrackP.removeAll();
		statTrackP.setLayout(null);

		statTrackP.add(chartp1);
		GridBagLayout gbl_chartp1 = new GridBagLayout();
		gbl_chartp1.columnWidths = new int[] { 0 };
		gbl_chartp1.rowHeights = new int[] { 0 };
		gbl_chartp1.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_chartp1.rowWeights = new double[] { Double.MIN_VALUE };
		chartp1.setLayout(gbl_chartp1);

	}

	public void statRes() throws NamingException {

		int nbc = 0;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<Resort> l1 = new ArrayList<>();
		List<Resort> l2 = new ArrayList<>();
		l1 = ResortBusinessDelegate.findAllResorts();
		l2 = ResortBusinessDelegate.findAllResorts();
		List<Statistic> st = new ArrayList<>();
		for (int i = 0; i < l1.size(); i++) {
			nbc = 0;
			for (int j = 0; j < l2.size(); j++) {

				if (l1.get(i).getCountry().equals(l2.get(j).getCountry())) {
					nbc++;
					l2.get(j).setCountry("hh");
				}
			}
			if (nbc != 0) {
				Statistic sat = new Statistic();
				sat.setY(nbc);
				sat.setX(l1.get(i).getCountry());
				st.add(sat);
			}

		}

		for (int i = 0; i < st.size(); i++) {

			if ((st.get(i).getX() != null) && (st.get(i).getY() != 0))
				dataset.setValue(st.get(i).getY(), " ", st.get(i).getX());

		}
		JFreeChart chart = ChartFactory.createBarChart3D("Resorts per Country", "Countries", "Resorts", dataset,
				PlotOrientation.VERTICAL, true, false, true);
		CategoryPlot catplot = chart.getCategoryPlot();
		final CategoryItemRenderer renderer = new CustomRenderer(new Paint[] { Color.red, Color.blue, Color.green,
				Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.blue });
		chart.setBackgroundPaint(Color.WHITE);
		catplot.setBackgroundPaint(Color.WHITE);
		catplot.setRenderer(renderer);

		statCountResort.setLayout(null);
		ChartPanel chartp = new ChartPanel(chart);
		chartp.setBounds(-11, 0, 515, 262);
		statCountResort.add(chartp);
		GridBagLayout gbl_chartp = new GridBagLayout();
		gbl_chartp.columnWidths = new int[] { 0 };
		gbl_chartp.rowHeights = new int[] { 0 };
		gbl_chartp.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_chartp.rowWeights = new double[] { Double.MIN_VALUE };
		chartp.setLayout(gbl_chartp);
		statCountResort.validate();
	}
}
