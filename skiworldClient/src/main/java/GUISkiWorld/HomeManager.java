package GUISkiWorld;

import java.awt.Color;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

	private void partageFacebook(String m, String url) throws FileNotFoundException {

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
	 */
	private void initialize() throws NamingException, SQLException, IOException {

		ManagerGUI = new JFrame();
		ManagerGUI.setBounds(100, 100, 784, 459);
		ManagerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManagerGUI.getContentPane().setLayout(null);

		JPanel header = new JPanel();
		header.setBounds(0, 11, 768, 43);
		ManagerGUI.getContentPane().add(header);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 70, 768, 339);
		ManagerGUI.getContentPane().add(tabbedPane);

		JPanel Trainingpanel = new JPanel();
		Trainingpanel.setLayout(null);
		tabbedPane.addTab("TRAINING", null, Trainingpanel, null);

		TrainingName = new JTextField();
		TrainingName.setColumns(10);
		TrainingName.setBounds(198, 23, 244, 35);
		Trainingpanel.add(TrainingName);

		JTextPane txtpnTrainingName = new JTextPane();
		txtpnTrainingName.setText("TRAINING NAME");
		txtpnTrainingName.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		txtpnTrainingName.setBounds(30, 23, 139, 25);
		Trainingpanel.add(txtpnTrainingName);

		JTextArea TrainingDescription = new JTextArea();
		TrainingDescription.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		TrainingDescription.setBounds(198, 69, 244, 70);
		Trainingpanel.add(TrainingDescription);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("DESCRIPTION");
		textPane_1.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		textPane_1.setBounds(40, 74, 111, 25);
		Trainingpanel.add(textPane_1);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("STARTING DATE");
		textPane_2.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		textPane_2.setBounds(40, 150, 121, 25);
		Trainingpanel.add(textPane_2);

		JDateChooser TrainingStartingDate = new JDateChooser();
		TrainingStartingDate.setDateFormatString("yyyy-MM-dd");
		TrainingStartingDate.setBounds(220, 150, 200, 25);
		Trainingpanel.add(TrainingStartingDate);

		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("ENDING DATE");
		textPane_3.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		textPane_3.setBounds(440, 150, 111, 25);
		Trainingpanel.add(textPane_3);

		JDateChooser TrainingEndingDate = new JDateChooser();
		TrainingEndingDate.setDateFormatString("yyyy-MM-dd");
		TrainingEndingDate.setBounds(553, 150, 200, 25);
		Trainingpanel.add(TrainingEndingDate);

		JComboBox TrainingResort = new JComboBox();
		TrainingResort.setBounds(553, 218, 158, 20);
		Trainingpanel.add(TrainingResort);

		JTextPane ResortCombo = new JTextPane();
		ResortCombo.setText("RESORT");
		ResortCombo.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		ResortCombo.setBounds(453, 213, 74, 25);
		Trainingpanel.add(ResortCombo);

		JComboBox TrainingType = new JComboBox();
		TrainingType.setModel(new DefaultComboBoxModel(new String[] { "BEGINNER", "EXTREME ", "FREESTYLE" }));
		TrainingType.setBounds(220, 213, 200, 20);
		Trainingpanel.add(TrainingType);

		JTextPane TrainingT = new JTextPane();
		TrainingT.setText("TYPE");
		TrainingT.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		TrainingT.setBounds(85, 213, 51, 25);
		Trainingpanel.add(TrainingT);

		JPanel panel = new JPanel();
		panel.setLayout(null);
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

		TrainingTable = new JTable();
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

		JButton TrainingShare = new JButton("Share on Facebook");
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
				}

			}
		});
		TrainingShare.setBounds(536, 272, 135, 27);
		panel.add(TrainingShare);

		JPanel Eventspanel = new JPanel();
		tabbedPane.addTab("EVENTS", null, Eventspanel, null);
		Eventspanel.setLayout(null);

		EventName = new JTextField();
		EventName.setBounds(198, 23, 244, 35);
		Eventspanel.add(EventName);
		EventName.setColumns(10);

		JTextPane txtpnEventName = new JTextPane();
		txtpnEventName.setFont(new Font("Source Sans Pro", Font.ITALIC, 18));
		txtpnEventName.setText("EVENT NAME");
		txtpnEventName.setBounds(58, 23, 111, 25);
		Eventspanel.add(txtpnEventName);

		JTextArea EventDescription = new JTextArea();
		EventDescription.setBounds(198, 69, 244, 70);
		Eventspanel.add(EventDescription);

		JTextPane txtpnDescription = new JTextPane();
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
		trainingModel.fillTrainingResortComboBox(TrainingResort);
		JButton AddTraining = new JButton("ADD TRAINING");
		AddTraining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			
				try {
					
					Training training = new Training();				
					Resort resort = new Resort();
					resort = ResortBusinessDelegate.findResortByLabel(TrainingResort.getSelectedItem().toString());
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

						parsedT = format.parse(((JTextField) dateStart.getDateEditor().getUiComponent()).getText());
						parsedT2 = format2.parse(((JTextField) dateEnd.getDateEditor().getUiComponent()).getText());

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
		AddTraining.setBounds(253, 265, 131, 23);
		Trainingpanel.add(AddTraining);

		// pubicationModel.fillEventResortComboBox
		pubicationModel.fillEventResortComboBox(EventResort);
		EventTable = new JTable();

		JButton EventAddPicture = new JButton("ADD PICTURE");
		EventAddPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser ch = new JFileChooser();
				ch.showOpenDialog(null);
				File f = ch.getSelectedFile();
				EventImage = f.getAbsolutePath();

				EventPicturePath.setText(EventImage);

			}
		});
		EventAddPicture.setBounds(463, 29, 104, 23);
		Eventspanel.add(EventAddPicture);

		EventPicturePath = new JTextField();
		EventPicturePath.setBounds(584, 30, 142, 20);
		Eventspanel.add(EventPicturePath);
		EventPicturePath.setColumns(10);

		JButton btnAddEvent = new JButton("ADD EVENT");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

		
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
		btnAddEvent.setBounds(572, 197, 89, 23);
		Eventspanel.add(btnAddEvent);

		EventTable.setBounds(10, 49, 399, 198);

		EventTable.setFont(new Font("Arial", Font.BOLD, 12));
		EventTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		AllEventspanel.add(EventTable);
		JButton UpdateEvent = new JButton("UPDATE");
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
		UpdateEvent.setBounds(10, 270, 111, 30);
		AllEventspanel.add(UpdateEvent);
		JLabel EventPictureShow = new JLabel("");
		EventPictureShow.setBounds(469, 206, 227, 105);
		AllEventspanel.add(EventPictureShow);

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

				String	TrainingNamePdf  =RowSelectedTraining.getName().toString();
				String	TrainingDescriptionPdf  =RowSelectedTraining.getDescription().toString();
				String	TrainingStartPdf  ="24-03-2017";
				String	TrainingeEndPdf  ="25-03-2017";
					CreatePdf(TrainingNamePdf, TrainingDescriptionPdf, TrainingStartPdf, TrainingeEndPdf);
			}
			}});
		btnNewButton.setBounds(367, 274, 135, 23);
		panel.add(btnNewButton);

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
				}

			}
		});
		EventShare.setBounds(252, 272, 135, 27);
		AllEventspanel.add(EventShare);

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

	private void CreatePdf(String TrainingNamePdf,String TrainingDescriptionPdf,String TrainingStartingPdf ,String TrainingEndingPdf) {
		try {

			String fileName = "TrainingFile.pdf"; 
		


			PDDocument doc = new PDDocument();
			PDPage page = new PDPage();
			PDImageXObject pdImage = PDImageXObject.createFromFile("H:/ski-icon-web1.png",doc);
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
	        content.drawString("TRAINING NAME : " +TrainingNamePdf);
	        content.endText();

	        content.beginText();
	        content.setFont(PDType1Font.TIMES_ROMAN, 16);
	        content.moveTextPositionByAmount(80,650);
	        content.drawString("TRAINING DESCRIPTION : " +TrainingDescriptionPdf);
	        content.endText();
	        
	        content.beginText();
	        content.setFont(PDType1Font.TIMES_ROMAN, 16);
	        content.moveTextPositionByAmount(80,600);
	        content.drawString("STARTING DATE : " +TrainingStartingPdf);
	        content.endText();
	        
	        content.beginText();
	        content.setFont(PDType1Font.TIMES_ROMAN, 16);
	        content.moveTextPositionByAmount(80,550);
	        content.drawString("ENDING DATE : " +TrainingEndingPdf);
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
