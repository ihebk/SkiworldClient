package GUISkiWorld;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.apache.regexp.RE;
import org.joda.time.JodaTimePermission;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import com.toedter.calendar.JDateChooser;

import business.UserBusiness;
import contracts.UserCrudEJBRemote;
import entities.User;

public class Register {

	private JFrame registerFrame;
	private JTextField h_tf_firstname;
	private JTextField h_tf_lastname;
	private JTextField h_tf_phone;
	private JTextField h_tf_email;
	private JTextField h_tf_username;
	private JPasswordField h_tf_password;
	private JPasswordField h_tf_retypePassword;
	private String gender;
	private String h_username;
	private String h_email;
	public static Register window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Register();
					window.registerFrame.setVisible(true);
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

	/**
	 * Create the application.
	 * 
	 * @throws NamingException
	 */
	public Register() throws NamingException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws NamingException
	 */
	private void initialize() {

		registerFrame = new JFrame();
		registerFrame.setBounds(100, 100, 747, 462);
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 731, 423);
		registerFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel h_label1 = new JLabel("Please fill your informations");
		h_label1.setForeground(new Color(51, 51, 51));
		h_label1.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 16));
		h_label1.setBounds(239, 36, 314, 35);
		panel.add(h_label1);

		JButton h_btnRegister = new JButton("Register");
		h_btnRegister.setForeground(new Color(0, 102, 102));
		h_btnRegister.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));

		JLabel h_lbl_lastname = new JLabel("Lastname");
		h_lbl_lastname.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		h_lbl_lastname.setBounds(78, 155, 64, 14);
		panel.add(h_lbl_lastname);

		JLabel h_lbl_firstname = new JLabel("Firstname");
		h_lbl_firstname.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		h_lbl_firstname.setBounds(78, 116, 81, 14);
		panel.add(h_lbl_firstname);
		h_btnRegister.setBounds(315, 310, 89, 23);
		panel.add(h_btnRegister);

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
		h_tf_firstname.setForeground(new Color(0, 0, 0));
		h_tf_firstname.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
		h_tf_firstname.setBounds(169, 114, 128, 20);
		panel.add(h_tf_firstname);
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
		h_tf_lastname.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
		h_tf_lastname.setBounds(169, 152, 128, 20);
		panel.add(h_tf_lastname);
		h_tf_lastname.setColumns(10);

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
		h_tf_phone.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
		h_tf_phone.setBounds(464, 116, 166, 20);
		panel.add(h_tf_phone);
		h_tf_phone.setColumns(10);

		h_tf_email = new JTextField();
		h_tf_email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		h_tf_email.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
		h_tf_email.setBounds(464, 151, 166, 20);
		panel.add(h_tf_email);
		h_tf_email.setColumns(10);

		h_tf_username = new JTextField();
		h_tf_username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		h_tf_username.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
		h_tf_username.setBounds(464, 182, 166, 20);
		panel.add(h_tf_username);
		h_tf_username.setColumns(10);

		JLabel label = new JLabel("");

		JLabel label_1 = new JLabel("");

		JLabel label_2 = new JLabel("");

		JLabel label_3 = new JLabel("");

		JLabel label_4 = new JLabel("");

		JLabel label_5 = new JLabel("");

		JLabel label_6 = new JLabel("");

		JLabel h_lbl_gender = new JLabel("Gender");
		h_lbl_gender.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		h_lbl_gender.setBounds(78, 185, 64, 14);
		panel.add(h_lbl_gender);

		JLabel h_lbl_Birthdate = new JLabel("Birthdate");
		h_lbl_Birthdate.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		h_lbl_Birthdate.setBounds(78, 217, 64, 14);
		panel.add(h_lbl_Birthdate);

		JDateChooser h_dateChooser = new JDateChooser();
		h_dateChooser.setDateFormatString("yyyy-MM-dd");
		h_dateChooser.setBounds(168, 214, 129, 20);
		panel.add(h_dateChooser);

		JComboBox h_comboBox_gender = new JComboBox();
		h_comboBox_gender.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
		h_comboBox_gender.setModel(new DefaultComboBoxModel(new String[] { "Male", "Female" }));
		h_comboBox_gender.setSelectedIndex(0);
		h_comboBox_gender.setBounds(169, 183, 128, 20);
		panel.add(h_comboBox_gender);

		JLabel h_lbl_phone = new JLabel("phone(+xxx)");
		h_lbl_phone.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		h_lbl_phone.setBounds(331, 120, 87, 14);
		panel.add(h_lbl_phone);

		JLabel h_lbl_username = new JLabel("Username(*)");
		h_lbl_username.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		h_lbl_username.setBounds(331, 186, 87, 14);
		panel.add(h_lbl_username);

		JLabel h_lbl_email = new JLabel("Email(*)");
		h_lbl_email.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		h_lbl_email.setBounds(331, 155, 64, 14);
		panel.add(h_lbl_email);

		JLabel h_lbl_password = new JLabel("Password(*)");
		h_lbl_password.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		h_lbl_password.setBounds(331, 217, 87, 14);
		panel.add(h_lbl_password);

		h_tf_password = new JPasswordField();
		h_tf_password.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
		h_tf_password.setBounds(464, 213, 166, 20);
		panel.add(h_tf_password);

		JLabel lblRetypePassword = new JLabel("Retype Password(*)");
		lblRetypePassword.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		lblRetypePassword.setBounds(331, 251, 133, 14);
		panel.add(lblRetypePassword);

		h_tf_retypePassword = new JPasswordField();
		h_tf_retypePassword.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
		h_tf_retypePassword.setBounds(464, 247, 166, 20);
		panel.add(h_tf_retypePassword);

		JLabel lbBackGround = new JLabel("");
		lbBackGround.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
		lbBackGround.setForeground(new Color(0, 0, 0));
		lbBackGround.setIcon(new ImageIcon("src\\main\\resources\\pic.jpg"));
		lbBackGround.setBounds(0, 0, 741, 423);
		panel.add(lbBackGround);

		JLabel h_lbl_age = new JLabel("Age");
		h_lbl_age.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		h_lbl_age.setBounds(78, 186, 46, 14);
		panel.add(h_lbl_age);

		JSpinner h_spinner_age = new JSpinner();
		h_spinner_age.setBounds(196, 183, 45, 20);
		panel.add(h_spinner_age);

		h_btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserBusiness proxy = new UserBusiness();
				// InitialContext ctx;
				try {
					// ctx = new InitialContext();
					// UserCrudEJBRemote proxyUser = (UserCrudEJBRemote) ctx
					// .lookup("/SkiWorld-ear/SkiWorld-ejb/UserCrudEJB!services.UserCrudEJBRemote");
					if ((h_tf_firstname.getText().trim().length() == 0)
							|| (h_tf_lastname.getText().trim().length() == 0)
							|| (h_tf_phone.getText().trim().length() == 0)
							|| (h_tf_username.getText().trim().length() == 0)
							|| (h_tf_email.getText().trim().length() == 0)
							|| (h_tf_password.getText().trim().length() == 0)
							|| (h_tf_retypePassword.getText().trim().length() == 0)) {
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

					if ((h_dateChooser.getDateFormatString().startsWith("B"))) {
						JDialog dialog2 = new JDialog();
						dialog2.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog2, "You should fill in your birthdate");
						return;

					}

					User u = new User();
					u.setFirstname(h_tf_firstname.getText());
					u.setLastname(h_tf_lastname.getText());
					u.setUsername(h_tf_username.getText());
					h_username = h_tf_username.getText();
					u.setEmail(h_tf_email.getText());
					h_email = h_tf_email.getText();

					u.setRole("user");
					u.setAge(Integer.parseInt(h_spinner_age.getValue().toString()));

					try {
						if (h_tf_phone.getText().startsWith(".")) {
							throw new NumberFormatException();
						}
						if (Integer.valueOf(h_tf_phone.getText()) == null)
							;
						if (h_tf_phone.getText().startsWith("+")) {
							throw new NumberFormatException();
						}
						if (h_tf_phone.getText().startsWith("-")) {
							throw new NumberFormatException();
						}

					} catch (NumberFormatException E) {
						JDialog dialog2 = new JDialog();
						dialog2.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog2, "Phone field must contain only digits");
						return;
					}
					if (h_comboBox_gender.getSelectedIndex() == 0) {
						u.setGender("Male");
					} else if (h_comboBox_gender.getSelectedIndex() == 1) {
						u.setGender("Female");
					}
					if (h_tf_phone.getText().trim().length() > 0) {
						u.setPhone(Integer.parseInt(h_tf_phone.getText()));
					}

					if (!CheckMail(h_email)) {

						JDialog dialog2 = new JDialog();
						dialog2.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog2, "Please enter a valid mail");
						return;
					}
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date parsed = new java.util.Date();

					try {

						parsed = format.parse(((JTextField) h_dateChooser.getDateEditor().getUiComponent()).getText());
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
					java.sql.Date sql = new java.sql.Date(parsed.getTime());
					// JodaTimePermission d = new JodaTimePermission();
					LocalDate ld = new LocalDate();
					ld = ld.minusYears(18);

					System.out.println(DateTimeFormat.forPattern("yyyy-MM-dd").print(ld));
					String year = parsed.toString().substring(30, 34);
					String yearMinus = ld.toString().substring(0, 4);
					System.out.println("yearMinus" + yearMinus);
					System.out.println("year" + year);
					System.out.println(parsed.toString());
					String currentYear = ld.toString().substring(0, 4);
					int currentYeatInt = Integer.parseInt(currentYear);
					int yearCalendarInt = Integer.parseInt(year);
					int yearNowInt = Integer.parseInt(yearMinus);
					// int minus = yearCalendarInt - yearNowInt;
					// System.out.println(Math.abs(minus));
					// if (yearCalendarInt > currentYeatInt ) {
					// JDialog dialog2 = new JDialog();
					// dialog2.setAlwaysOnTop(true);
					// JOptionPane.showMessageDialog(dialog2, "Enter a Valid
					// Date");
					// return;
					// }
					if (yearCalendarInt > yearNowInt) {
						JDialog dialog2 = new JDialog();
						dialog2.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog2, "Your are under age");
						return;
					}

					u.setBirthdate(sql);

					System.out.println(sql);
					if ((h_tf_password.getText().equals(h_tf_retypePassword.getText())
							&& h_tf_password.getText().trim().length() != 0)) {
						u.setPassword(h_tf_password.getText());
						if ((proxy.getProxy().checkMailExistance(h_email))
								|| ((proxy.getProxy().checkUsernameExistance(h_username)))) {
							JDialog dialog2 = new JDialog();
							dialog2.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(dialog2, "The email and the username have to be unique");

						} else {
							proxy.getProxy().addUser(u);
							JDialog dialog2 = new JDialog();
							dialog2.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(dialog2,
									"You are registered \n wait for your confirmation as a manager");

							System.out.println(u);
							window.registerFrame.setVisible(false);
							Authentification.main(null);

						}

					}

					else {

						JDialog dialog1 = new JDialog();
						dialog1.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog1, "Please verify the password");

					}

				} catch (NamingException e1) {

					e1.printStackTrace();
				}

			}
		});

	}
}
