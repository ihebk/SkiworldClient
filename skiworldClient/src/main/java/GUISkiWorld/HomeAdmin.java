package GUISkiWorld;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Paint;
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
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;
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
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import entities.Resort;
import entities.Store;
import models.ResortModel;
import models.StoreModel;
import statUtil.CustomRenderer;
import statUtil.Statistic;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import business.PisteBusiness;
import business.ResortBusinessDelegate;
import business.StoreBusinessDelegate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;

public class HomeAdmin {

	private JFrame ManagerGUI;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeAdmin window = new HomeAdmin();
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
	public HomeAdmin() throws NamingException, SQLException, IOException {
		initialize();
		statTrackFN();
		statRes();
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
		ManagerGUI.setBounds(300, 300, 1100, 700);
		ManagerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManagerGUI.getContentPane().setLayout(null);
		ManagerGUI.getContentPane().setLayout(null);

		// SMS.main(null, "+21624056027", "hello");
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 76, 1074, 469);
		ManagerGUI.getContentPane().add(tabbedPane);

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
							resortTable.setModel(resortmodel.getResortModel());
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

		t_scrollPane.setBounds(424, 11, 329, 253);
		resorts.add(t_scrollPane);

		resortTable = new JTable();
		ResortModel resortModel = new ResortModel();
		resortTable.setModel(resortModel.getResortModel());
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
								resortTable.setModel(resortmodel.getResortModel());
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
							resortTable.setModel(resortmodel.getResortModel());
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

		JLabel i_lblNewLabel_1 = new JLabel("");
		i_lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Iheb\\Desktop\\15755113534_f75fd1636c_k.jpg"));
		i_lblNewLabel_1.setBounds(0, 0, 763, 335);
		resorts.add(i_lblNewLabel_1);

		JPanel statPanel = new JPanel();
		tabbedPane.addTab("Statistics", null, statPanel, null);
		statPanel.setLayout(null);

		statCountResort = new JPanel();
		statCountResort.setBounds(0, 5, 504, 482);
		statPanel.add(statCountResort);

		statTrackP = new JPanel();
		statTrackP.setBounds(502, 5, 557, 358);
		statPanel.add(statTrackP);
		StoreModel storeModel = new StoreModel();
		JPanel panel = new JPanel();
		JLabel i_label = new JLabel("New label");
		i_label.setIcon(new ImageIcon(
				"C:\\Users\\Iheb\\Desktop\\Ski Freestyle Wallpaper High Definition 61942 5975 Wallpaper  Cool ....jpg"));
		i_label.setBounds(0, 0, 768, 420);
		ManagerGUI.getContentPane().add(i_label);

	}

	public void statTrackFN() throws NamingException {
		PisteBusiness pb = new PisteBusiness();
		int t1 = 0, t2 = 0;
		String ty1 = "Training", ty2 = "Daily Activities";
		DefaultPieDataset dataset1 = new DefaultPieDataset();
		for (int i = 0; i < pb.getProxy().findAllPistes().size(); i++) {
			if (pb.getProxy().findAllPistes().get(i).getType().equals(ty1))
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
		chartp1.setBounds(23, 11, 524, 336);
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
		List<Statistic>st = new ArrayList<>();
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
		chartp.setBounds(-11, 11, 515, 335);
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
