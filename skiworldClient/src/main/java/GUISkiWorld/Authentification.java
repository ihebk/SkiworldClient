package GUISkiWorld;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import business.UserDelegate;
import entities.User;
import exceptions.MoreThanOneResultException;
import exceptions.NoResultFoundException;

public class Authentification {

	private JFrame frame;
	private JTextField h_tf_usernameAuth;
	public static User userConnected;
	static Authentification window;
	private JPasswordField h_tf_passwordAuth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Authentification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");

		} catch (Exception e) {

		}
		frame = new JFrame();
		frame.setBounds(100, 100, 728, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton h_btnLogin = new JButton("Login");

		h_btnLogin.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 16));
		h_btnLogin.setBackground(UIManager.getColor("Button.light"));
		h_btnLogin.setForeground(new Color(51, 51, 51));
		h_btnLogin.setBounds(280, 345, 130, 40);
		panel.add(h_btnLogin);

		h_tf_usernameAuth = new JTextField();
		h_tf_usernameAuth.setHorizontalAlignment(SwingConstants.LEFT);
		h_tf_usernameAuth.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 18));
		h_tf_usernameAuth.setBounds(278, 213, 276, 43);
		panel.add(h_tf_usernameAuth);
		h_tf_usernameAuth.setColumns(10);

		JButton h_btnRegister = new JButton("Register");
		h_btnRegister.setForeground(new Color(51, 51, 51));
		h_btnRegister.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 16));
		h_btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.frame.setVisible(false);
				Register.main(null);
			}
		});
		h_btnRegister.setBounds(424, 345, 130, 40);
		panel.add(h_btnRegister);

		h_tf_passwordAuth = new JPasswordField();
		h_tf_passwordAuth.setBounds(278, 273, 276, 43);
		panel.add(h_tf_passwordAuth);

		JButton h_btn_recoverPassword = new JButton("Recover password");
		h_btn_recoverPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				if (h_tf_usernameAuth.getText().trim().length() == 0) {
					JDialog dialog2 = new JDialog();
					dialog2.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog2, "Please enter your Username");
					return;
				}
				try {
					user = UserDelegate.findByUsername(h_tf_usernameAuth.getText());
				} catch (MoreThanOneResultException e1) {

				} catch (NoResultFoundException e1) {
					JDialog dialog2 = new JDialog();
					dialog2.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog2, "Please enter a valid username");
					return;
				}
				System.out.println(user.getUsername());

				service.mail mail = new service.mail();
				try {
					mail.sendEmail(user.getEmail(), "Recover Password", "Your Password is :" + user.getPassword());
					JDialog dialog2 = new JDialog();
					dialog2.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog2, "please check you mail to recover your password");
				} catch (MessagingException ex) {

				}
			}
		});
		h_btn_recoverPassword.setForeground(Color.DARK_GRAY);
		h_btn_recoverPassword.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		h_btn_recoverPassword.setBounds(332, 396, 165, 23);
		panel.add(h_btn_recoverPassword);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 18));
		lblNewLabel.setBackground(UIManager.getColor("Button.light"));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Haythem\\Desktop\\authentification3.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 718, 546);
		panel.add(lblNewLabel);

		h_btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {

				User u1 = new User();
				if (h_tf_passwordAuth.getText().trim().length() == 0) {
					JDialog dialog2 = new JDialog();
					dialog2.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog2, "Please enter Your password");
					return;
				}
				if (h_tf_usernameAuth.getText().trim().length() == 0) {
					JDialog dialog2 = new JDialog();
					dialog2.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog2, "Please enter your login");
					return;
				}
				try {

					// ctx = new InitialContext();
					// UserCrudEJBRemote proxyUser = (UserCrudEJBRemote) ctx
					// .lookup("/SkiWorld-ear/SkiWorld-ejb/UserCrudEJB!services.UserCrudEJBRemote");

					u1 = UserDelegate.findByUsername(h_tf_usernameAuth.getText());
					System.out.println(u1.getUsername());

					if (!u1.getUsername().equals("")) {
						if (u1.getPassword().equals(h_tf_passwordAuth.getText())) {
							System.out.println("Login successful");
							userConnected = u1;
							System.out.println(u1);
							// connected c = new connected();
							if (u1.getRole().equals("manager")) {
								System.out.println("manager");
								window.frame.setVisible(false);
								HomeManager.main(null);
							} else if (u1.getRole().equals("admin")) {
								System.out.println("admin");

								window.frame.setVisible(false);
								HomeAdmin.main(null);
							} else {

								JDialog dialog2 = new JDialog();
								dialog2.setAlwaysOnTop(true);
								JOptionPane.showMessageDialog(dialog2, "Your registration is not validated yet");
								return;
							}

						} else {
							System.out.println("Login Failed");
							JDialog dialog2 = new JDialog();
							dialog2.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(dialog2, "Login failed");
							return;

						}
					} else {
						System.out.println("Login Failed");
						JDialog dialog2 = new JDialog();
						dialog2.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog2, "Login failed");
						return;

					}
				} catch (MoreThanOneResultException | NoResultFoundException e1) {

					System.out.println("User not found");
					JDialog dialog2 = new JDialog();
					dialog2.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog2, "Please verify your login and password");
					return;

				}

			}
		});

	}
}
