package GUISkiWorld;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import entities.User;
import exceptions.MoreThanOneResultException;
import exceptions.NoResultFoundException;
import services.UserCrudEJBRemote;

public class Authentification {

	private JFrame frame;
	private JTextField h_tf_usernameAuth;
	private JTextField h_tf_passwordAuth;
	public static User userConnected;
	static Authentification window;

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

		h_tf_passwordAuth = new JTextField();
		h_tf_passwordAuth.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 18));
		h_tf_passwordAuth.setToolTipText("Enter your password");
		h_tf_passwordAuth.setHorizontalAlignment(SwingConstants.LEFT);
		h_tf_passwordAuth.setColumns(10);
		h_tf_passwordAuth.setBounds(278, 274, 276, 43);
		panel.add(h_tf_passwordAuth);

		JButton h_btnRegister = new JButton("Register");
		h_btnRegister.setForeground(new Color(51, 51, 51));
		h_btnRegister.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 16));
		h_btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		h_btnRegister.setBounds(424, 345, 130, 40);
		panel.add(h_btnRegister);

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
				InitialContext ctx;
				User u1 = new User();
				try {

					ctx = new InitialContext();
					UserCrudEJBRemote proxyUser = (UserCrudEJBRemote) ctx
							.lookup("/SkiWorld-ear/SkiWorld-ejb/UserCrudEJB!services.UserCrudEJBRemote");
					u1 = proxyUser.findByUsername(h_tf_usernameAuth.getText());
					System.out.println(u1.getUsername());
					if (!u1.getUsername().equals("")) {
						if (u1.getPassword().equals(h_tf_passwordAuth.getText())) {
							System.out.println("Login successful");
							userConnected = u1;
							// connected c = new connected();
							window.frame.setVisible(false);
							connected.main(null);

						} else {
							System.out.println("Login Failed");
						}
					} else {
						System.out.println("Null");
					}
				} catch (NamingException | MoreThanOneResultException | NoResultFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("User not found");
				}

			}
		});

	}
}
