package GUISkiWorld;

import java.awt.EventQueue;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
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

import org.apache.regexp.RE;

import business.PostBusiness;
import business.PostDelagate;
import business.UserBusiness;
import business.UserDelegate;
import contracts.UserCrudEJBRemote;
import entities.Post;
import entities.User;
import models.PostsModel;
import models.UsersModel;

public class HomeAdmin {

	private JFrame frame;
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
					window.frame.setVisible(true);
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
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	private void initialize() throws SQLException, IOException, NamingException {
		UsersModel usermodel = new UsersModel();
		PostsModel postsmodel = new PostsModel();
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 684, 361);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 685, 360);
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
				InitialContext ctx;

				try {

					ctx = new InitialContext();
					UserCrudEJBRemote proxyUser = (UserCrudEJBRemote) ctx
							.lookup("/SkiWorld-ear/SkiWorld-ejb/UserCrudEJB!services.UserCrudEJBRemote");
					proxyUser.updateUser(Authentification.userConnected);

				} catch (NamingException e1) {

				}

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

		setTextFields();

	}
}
