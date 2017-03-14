package GUISkiWorld;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import maps.java.StaticMaps;
import model.ClotheModel;
import model.EquipementModel;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import contracts.EquipementCrudEJBRemote;

import javax.swing.event.ChangeEvent;

import entities.Clothes;
import entities.Equipments;
import javax.swing.table.DefaultTableModel;

import business.ClothBusiness;
import business.EquipementBusiness;
import business.StoreBusiness;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class HomeManager {

	private JFrame ManagerGUI;
	private JTextField txtName;
	private JTextField txtDesc;
	private JTextField txtPrice;
	private JTextField txtDeal;
	private JTextField txtType;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField txtNameClth;
	private JTextField txtDescClth;
	private JTextField txtPriceClth;
	private JTextField txtDealClth;
	private JTextField txtTypeClth;
	private StaticMaps ObjStaticMaps = new StaticMaps();

	private JComboBox JCombo_ME_Formato = new JComboBox();
	private JComboBox cbx_lib;
	private JComboBox JCombo_ME_TipoMapa = new JComboBox();
	private JComboBox cbxStore = new JComboBox();
	private JTextField JText_ME_Zoom;
	private JTextField JText_ME_Escala;
	private JLabel JLabel_ME_Imagen = new JLabel("");
	private String imageEqp;
	/******************** Table ***************************/
	private JTable tableEqp;
	private JTable tableClth;
	/******************** Model ****************************/
	EquipementModel equipementModel = new EquipementModel();
	ClotheModel clotheModel = new ClotheModel();
	/******************** Business ***************************/

	
	StoreBusiness storeBusiness = new StoreBusiness();
	/******************* Row **************************/
	public Equipments rowEquipments = null;
	public Clothes rowClothes = null;
	/****************************************************/
	private BufferedImage img_display;
	public static String pathImgEqp;
	public static String pathImgClth;
	public static String pathImgOut;
	public File f;
	private JTextField i_imagepath;
	private JLabel lblImg = new JLabel("");
	private String ImgPath = null;
	private JLabel RequiredFieldName;
	private JLabel RequiredFieldDesc;
	private JLabel RequiredFieldType;
	private JLabel RequiredFieldPrice;
	private JLabel RequiredFieldDeal;
	private JTextField i_imagepathClth;

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
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public HomeManager() throws SQLException, IOException, NamingException {
		initialize();
		tableClth.setModel(clotheModel.getClothesModel());
		tableEqp.setModel(equipementModel.getEquipmentsModel());
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	private void initialize() throws NamingException, SQLException, IOException {
		ManagerGUI = new JFrame();
		ManagerGUI.setBounds(100, 100, 896, 618);
		ManagerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManagerGUI.getContentPane().setLayout(null);

		JPanel header = new JPanel();
		header.setBounds(0, 11, 768, 43);
		ManagerGUI.getContentPane().add(header);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 70, 880, 509);
		ManagerGUI.getContentPane().add(tabbedPane);

		JPanel Store = new JPanel();
		tabbedPane.addTab("Store", null, Store, null);
		Store.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 875, 481);
		Store.add(tabbedPane_1);
		JPanel StoreEquipments = new JPanel();
		tabbedPane_1.addTab("Equipments", null, StoreEquipments, null);
		StoreEquipments.setLayout(null);
		tableEqp = new JTable();
		JLabel RequiredFieldType = new JLabel("Required Field");
		RequiredFieldType.setForeground(Color.RED);
		RequiredFieldType.setBounds(135, 204, 155, 14);
		StoreEquipments.add(RequiredFieldType);

		JLabel RequiredFieldDeal = new JLabel("Required Field");
		RequiredFieldDeal.setForeground(Color.RED);
		RequiredFieldDeal.setBounds(135, 161, 155, 14);
		StoreEquipments.add(RequiredFieldDeal);

		JLabel RequiredFieldName = new JLabel("Required Field");
		RequiredFieldName.setForeground(Color.RED);
		RequiredFieldName.setBounds(135, 30, 155, 14);
		StoreEquipments.add(RequiredFieldName);

		JLabel RequiredFieldDesc = new JLabel("Required Field");
		RequiredFieldDesc.setForeground(Color.RED);
		RequiredFieldDesc.setBounds(135, 74, 155, 14);
		StoreEquipments.add(RequiredFieldDesc);

		JLabel RequiredFieldPrice = new JLabel("Required Field");
		RequiredFieldPrice.setForeground(Color.RED);
		RequiredFieldPrice.setBounds(135, 118, 155, 14);
		StoreEquipments.add(RequiredFieldPrice);
		
		RequiredFieldName.setVisible(false);
		RequiredFieldDesc.setVisible(false);
		RequiredFieldPrice.setVisible(false);
		RequiredFieldType.setVisible(false);
		RequiredFieldDeal.setVisible(false);
		JPanel StoreClothes = new JPanel();
		tabbedPane_1.addTab("Clothes", null, StoreClothes, null);
		StoreClothes.setLayout(null);
		JLabel RequiredFieldNameClth = new JLabel("RequiredField");
		RequiredFieldNameClth.setForeground(Color.RED);
		RequiredFieldNameClth.setBounds(136, 30, 158, 14);
		StoreClothes.add(RequiredFieldNameClth);
		
		JLabel RequiredFieldDescClth = new JLabel("RequiredField");
		RequiredFieldDescClth.setForeground(Color.RED);
		RequiredFieldDescClth.setBounds(136, 83, 158, 14);
		StoreClothes.add(RequiredFieldDescClth);
		
		JLabel RequiredFieldPriceClth = new JLabel("RequiredField");
		RequiredFieldPriceClth.setForeground(Color.RED);
		RequiredFieldPriceClth.setBounds(136, 128, 158, 14);
		StoreClothes.add(RequiredFieldPriceClth);
		
		JLabel RequiredFieldDealClth = new JLabel("RequiredField");
		RequiredFieldDealClth.setForeground(Color.RED);
		RequiredFieldDealClth.setBounds(136, 172, 158, 14);
		StoreClothes.add(RequiredFieldDealClth);
		
		JLabel RequiredFieldTypeClth = new JLabel("RequiredField");
		RequiredFieldTypeClth.setForeground(Color.RED);
		RequiredFieldTypeClth.setBounds(136, 221, 158, 14);
		StoreClothes.add(RequiredFieldTypeClth);
		RequiredFieldNameClth.setVisible(false);
		RequiredFieldDescClth.setVisible(false);
		RequiredFieldPriceClth.setVisible(false);
		RequiredFieldTypeClth.setVisible(false);
		RequiredFieldDealClth.setVisible(false);
		JLabel lblName_1 = new JLabel("Name *");
		lblName_1.setBounds(36, 14, 46, 14);
		StoreClothes.add(lblName_1);

		JLabel lblDescription = new JLabel("Description *");
		lblDescription.setBounds(36, 64, 77, 14);
		StoreClothes.add(lblDescription);

		JLabel lblPrice_1 = new JLabel("Price *");
		lblPrice_1.setBounds(36, 111, 77, 14);
		StoreClothes.add(lblPrice_1);

		JLabel lblDeal_1 = new JLabel("Deal *");
		lblDeal_1.setBounds(36, 156, 77, 14);
		StoreClothes.add(lblDeal_1);

		JLabel lblType_2 = new JLabel("Type *");
		lblType_2.setBounds(36, 205, 77, 14);
		StoreClothes.add(lblType_2);

		txtNameClth = new JTextField();
		txtNameClth.setBounds(136, 11, 158, 20);
		txtNameClth.setColumns(10);
		StoreClothes.add(txtNameClth);

		txtDescClth = new JTextField();
		txtDescClth.setBounds(136, 61, 158, 20);
		txtDescClth.setColumns(10);
		StoreClothes.add(txtDescClth);

		txtPriceClth = new JTextField();
		txtPriceClth.setBounds(136, 108, 158, 20);
		txtPriceClth.setColumns(10);
		StoreClothes.add(txtPriceClth);

		txtDealClth = new JTextField();
		txtDealClth.setBounds(136, 153, 158, 20);
		txtDealClth.setColumns(10);
		StoreClothes.add(txtDealClth);

		txtTypeClth = new JTextField();
		txtTypeClth.setBounds(136, 202, 158, 20);
		txtTypeClth.setColumns(10);
		StoreClothes.add(txtTypeClth);

		JPanel panel = new JPanel();
		panel.setBounds(710, 14, 1, 1);
		panel.setLayout(null);
		StoreClothes.add(panel);

		JLabel label = new JLabel("Name :");
		label.setBounds(31, 14, 46, 14);
		panel.add(label);

		JLabel label_1 = new JLabel("Description :");
		label_1.setBounds(31, 57, 70, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Price :");
		label_2.setBounds(31, 109, 46, 14);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Deal :");
		label_3.setBounds(31, 167, 46, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("Type :");
		label_4.setBounds(31, 220, 46, 14);
		panel.add(label_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(135, 11, 155, 20);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(135, 54, 155, 20);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(135, 106, 155, 20);
		panel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(135, 164, 155, 20);
		panel.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(135, 217, 155, 20);
		panel.add(textField_9);

		JButton button = new JButton("Add");
		button.setBounds(167, 249, 89, 23);
		panel.add(button);
		JComboBox cbxStoreClth = new JComboBox();
		cbxStoreClth.setBounds(136, 246, 158, 20);
		StoreClothes.add(cbxStoreClth);
		clotheModel.fillStoreComboBox(cbxStoreClth);
		JButton btnAddClothFK = new JButton("Add");
		btnAddClothFK.setBounds(363, 419, 89, 23);
		btnAddClothFK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isFull = true;
				if (txtNameClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldNameClth.setVisible(true);
				} else {
					RequiredFieldNameClth.setVisible(false);
				}

				if (txtDescClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDescClth.setVisible(true);
				} else {
					RequiredFieldDescClth.setVisible(false);
				}

				if (txtPriceClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldPriceClth.setVisible(true);
				} else {
					RequiredFieldPriceClth.setVisible(false);
				}

				if (txtTypeClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldTypeClth.setVisible(true);
				} else {
					RequiredFieldTypeClth.setVisible(false);
				}

				if (txtDealClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDealClth.setVisible(true);
				} else {
					RequiredFieldDealClth.setVisible(false);
				}
				if (isFull == true){
					try {
						ClotheModel clotheModel;
						StoreBusiness storeBusiness = new StoreBusiness();
						Clothes Clothe = new Clothes();
						Clothe.setName(txtNameClth.getText());
						Clothe.setDescription(txtDescClth.getText());
						Clothe.setPrice(Float.parseFloat(txtPriceClth.getText()));
						Clothe.setDeal(Float.parseFloat(txtDealClth.getText()));
						Clothe.setType(txtTypeClth.getText());
						File file = new File(pathImgClth);
						byte[] bFile = new byte[(int) file.length()];

						try {
							FileInputStream fileInputStream = new FileInputStream(file);
							fileInputStream.read(bFile);
							fileInputStream.close();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						Clothe.setImage(bFile);
						Clothe.setStore(storeBusiness.getStoreProxy().findAllStore().get(cbxStoreClth.getSelectedIndex()));
						clotheModel = new ClotheModel();
						ClothBusiness.addClothe(Clothe);
						tableClth.setModel(clotheModel.getClothesModel());
						txtNameClth.setText("");
						txtDescClth.setText("");
						txtPriceClth.setText("");
						txtDealClth.setText("");
						txtTypeClth.setText("");
						RequiredFieldNameClth.setVisible(false);
						RequiredFieldDescClth.setVisible(false);
						RequiredFieldPriceClth.setVisible(false);
						RequiredFieldTypeClth.setVisible(false);
						RequiredFieldDealClth.setVisible(false);
					} catch (NamingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Please enter the required fields");

				}

				

			}
		});
		StoreClothes.add(btnAddClothFK);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(363, 14, 497, 394);
		StoreClothes.add(scrollPane_1);
		JLabel lblImgClth = new JLabel("");
		lblImgClth.setBounds(89, 293, 205, 88);
		StoreClothes.add(lblImgClth);
		tableClth = new JTable();
		tableClth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableClth.getSelectedRow();
				ClotheModel clotheModel;
				try {
					clotheModel = new ClotheModel();
					rowClothes = clotheModel.getClotheList().get(index);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FileOutputStream fos;
				try {
					fos = new FileOutputStream("src//main//resources//imgs//clth.jpg");
					fos.write(rowClothes.getImage());
					fos.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ImageIcon imgThisImg1 = new ImageIcon("src//main//resources//imgs//clth.jpg");
				Image image = imgThisImg1.getImage(); // transform it
				Image newimg = image.getScaledInstance(150, 130, java.awt.Image.SCALE_SMOOTH); // scale
																								// it
																								// the
																								// smooth
																								// way
				ImageIcon imgThisImg = new ImageIcon(newimg); // transform
																// it back
				lblImgClth.setIcon(imgThisImg);
				imgThisImg.getImage().flush();
				txtNameClth.setText(rowClothes.getName());
				txtDescClth.setText(rowClothes.getDescription());
				txtPriceClth.setText((Float.toString(rowClothes.getPrice())));
				txtDealClth.setText((Float.toString(rowClothes.getDeal())));
				txtTypeClth.setText(rowClothes.getType());
				RequiredFieldNameClth.setVisible(false);
				RequiredFieldDescClth.setVisible(false);
				RequiredFieldPriceClth.setVisible(false);
				RequiredFieldTypeClth.setVisible(false);
				RequiredFieldDealClth.setVisible(false);
			}
		});
		scrollPane_1.setViewportView(tableClth);

		JLabel lblStore = new JLabel("Store");
		lblStore.setBounds(36, 249, 46, 14);
		StoreClothes.add(lblStore);

		JButton btnEdit_Clothe = new JButton("Edit");
		btnEdit_Clothe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isFull = true;
				if (txtNameClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldNameClth.setVisible(true);
				} else {
					RequiredFieldNameClth.setVisible(false);
				}

				if (txtDescClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDescClth.setVisible(true);
				} else {
					RequiredFieldDescClth.setVisible(false);
				}

				if (txtPriceClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldPriceClth.setVisible(true);
				} else {
					RequiredFieldPriceClth.setVisible(false);
				}

				if (txtTypeClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldTypeClth.setVisible(true);
				} else {
					RequiredFieldTypeClth.setVisible(false);
				}

				if (txtDealClth.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDealClth.setVisible(true);
				} else {
					RequiredFieldDealClth.setVisible(false);
				}
				int index = tableClth.getSelectedRow();
				if (rowClothes != null) {
					ClotheModel clotheModel;
					StoreBusiness storeBusiness = new StoreBusiness();
					Clothes clothes = new Clothes();
					clothes.setIdClothes(rowClothes.getIdClothes());
					clothes.setName(txtNameClth.getText());
					clothes.setDescription(txtDescClth.getText());
					clothes.setPrice(Float.parseFloat(txtPriceClth.getText()));
					clothes.setDeal(Float.parseFloat(txtDealClth.getText()));
					clothes.setType(txtTypeClth.getText());
					 if (isFull == true){
							try {
								clothes.setStore(
										storeBusiness.getStoreProxy().findAllStore().get(cbxStoreClth.getSelectedIndex()));
								clotheModel = new ClotheModel();
								ClothBusiness.updateClothe(clothes);
								tableClth.setModel(clotheModel.getClothesModel());
								JDialog dialog = new JDialog();
								dialog.setAlwaysOnTop(true);
								JOptionPane.showMessageDialog(dialog, "Edit successfully!");
								txtNameClth.setText("");
								txtDescClth.setText("");
								txtPriceClth.setText("");
								txtDealClth.setText("");
								txtTypeClth.setText("");
								RequiredFieldNameClth.setVisible(false);
								RequiredFieldDescClth.setVisible(false);
								RequiredFieldPriceClth.setVisible(false);
								RequiredFieldTypeClth.setVisible(false);
								RequiredFieldDealClth.setVisible(false);
							} catch (NamingException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					 }

				} else {
					JOptionPane.showMessageDialog(null, "Please select a clothe to modify");
				}
			}
		});
		btnEdit_Clothe.setBounds(496, 419, 89, 23);
		StoreClothes.add(btnEdit_Clothe);

		JButton btnDelete_Clothe = new JButton("Delete");
		btnDelete_Clothe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableClth.getSelectedRow();
				if (rowClothes != null) {

				
					try {
					
						ClothBusiness.deleteClothe(rowClothes.getIdClothes());
						tableClth.setModel(clotheModel.getClothesModel());
						JDialog dialog = new JDialog();
						dialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog, "Delete successfully!");
						txtNameClth.setText("");
						txtDescClth.setText("");
						txtPriceClth.setText("");
						txtDealClth.setText("");
						txtTypeClth.setText("");
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
				} else {
					JOptionPane.showMessageDialog(null, "Please select a Clothe to remove");
				}
			}
		});
		btnDelete_Clothe.setBounds(627, 419, 89, 23);
		StoreClothes.add(btnDelete_Clothe);

		JButton btnImageClth = new JButton("image");
		btnImageClth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choix = new JFileChooser();
				choix.showOpenDialog(null);
				f = choix.getSelectedFile();
				pathImgClth = f.getAbsolutePath();
				ImageIcon image = new ImageIcon(pathImgClth);
				Image im = image.getImage();
				Image myImg = im.getScaledInstance(lblImgClth.getWidth(), lblImgClth.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon newImage = new ImageIcon(myImg);
				lblImgClth.setIcon(newImage);
			}
		});
		btnImageClth.setBounds(136, 419, 89, 23);
		StoreClothes.add(btnImageClth);
		
		i_imagepathClth = new JTextField();
		i_imagepathClth.setBounds(36, 388, 258, 20);
		StoreClothes.add(i_imagepathClth);
		i_imagepathClth.setColumns(10);
		

		


		JLabel lblName = new JLabel("Name *");
		lblName.setBounds(31, 14, 46, 14);
		StoreEquipments.add(lblName);

		JLabel lblNewLabel_1 = new JLabel("Description *");
		lblNewLabel_1.setBounds(31, 58, 94, 14);
		StoreEquipments.add(lblNewLabel_1);

		JLabel lblPrice = new JLabel("Price *");
		lblPrice.setBounds(31, 102, 46, 14);
		StoreEquipments.add(lblPrice);

		JLabel lblDeal = new JLabel("Deal *");
		lblDeal.setBounds(31, 146, 46, 14);
		StoreEquipments.add(lblDeal);

		JLabel lblType = new JLabel("Type *");
		lblType.setBounds(31, 189, 46, 14);
		StoreEquipments.add(lblType);

		txtName = new JTextField();
		txtName.setBounds(135, 11, 155, 20);
		StoreEquipments.add(txtName);
		txtName.setColumns(10);

		txtDesc = new JTextField();
		txtDesc.setBounds(135, 55, 155, 20);
		StoreEquipments.add(txtDesc);
		txtDesc.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setBounds(135, 99, 155, 20);
		StoreEquipments.add(txtPrice);
		txtPrice.setColumns(10);

		txtDeal = new JTextField();
		txtDeal.setBounds(135, 143, 155, 20);
		StoreEquipments.add(txtDeal);
		txtDeal.setColumns(10);

		txtType = new JTextField();
		txtType.setBounds(135, 186, 155, 20);
		StoreEquipments.add(txtType);
		txtType.setColumns(10);

		JComboBox cbxStore = new JComboBox();
		cbxStore.setBounds(135, 232, 155, 20);
		StoreEquipments.add(cbxStore);
		// EquipementModel equipementModel = new EquipementModel();
		equipementModel.fillStoreComboBox(cbxStore);

		JButton btnAddEqpFK = new JButton("Add");
		btnAddEqpFK.setBounds(351, 419, 89, 23);
		btnAddEqpFK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isFull = true;
				if (txtName.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldName.setVisible(true);
				} else {
					RequiredFieldName.setVisible(false);
				}

				if (txtDesc.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDesc.setVisible(true);
				} else {
					RequiredFieldDesc.setVisible(false);
				}

				if (txtPrice.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldPrice.setVisible(true);
				} else {
					RequiredFieldPrice.setVisible(false);
				}

				if (txtType.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldType.setVisible(true);
				} else {
					RequiredFieldType.setVisible(false);
				}

				if (txtDeal.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDeal.setVisible(true);
				} else {
					RequiredFieldDeal.setVisible(false);
				}

				if (isFull == true) {
					try {
						EquipementModel equipementModel;
						StoreBusiness storeBusiness = new StoreBusiness();
						Equipments Equipment = new Equipments();
						Equipment.setName(txtName.getText());
						Equipment.setDescription(txtDesc.getText());
						Equipment.setPrice(Float.parseFloat(txtPrice.getText()));
						Equipment.setDeal(Float.parseFloat(txtDeal.getText()));
						Equipment.setType(txtType.getText());
						File file = new File(pathImgEqp);
						byte[] bFile = new byte[(int) file.length()];

						try {
							FileInputStream fileInputStream = new FileInputStream(file);
							fileInputStream.read(bFile);
							fileInputStream.close();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						Equipment.setImage(bFile);
						Equipment.setStore(
								storeBusiness.getStoreProxy().findAllStore().get(cbxStore.getSelectedIndex()));
						equipementModel = new EquipementModel();
						EquipementBusiness.addEquipement(Equipment);
						tableEqp.setModel(equipementModel.getEquipmentsModel());
						JDialog dialog = new JDialog();
						dialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog, "Add successfully!");
						RequiredFieldName.setVisible(false);
						RequiredFieldDesc.setVisible(false);
						RequiredFieldPrice.setVisible(false);
						RequiredFieldType.setVisible(false);
						RequiredFieldDeal.setVisible(false);
						txtName.setText("");
						txtDesc.setText("");
						txtPrice.setText("");
						txtDeal.setText("");
						txtType.setText("");
						lblImg.setText(null);

					} catch (NamingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Please enter the required fields");

				}

			}
		});
		StoreEquipments.add(btnAddEqpFK);

		JScrollPane scrollPaneEqp = new JScrollPane();
		scrollPaneEqp.setBounds(351, 14, 509, 394);
		StoreEquipments.add(scrollPaneEqp);
		JLabel lblImg = new JLabel("");
		lblImg.setIcon((Icon) img_display);
		tableEqp = new JTable();
		tableEqp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableEqp.getSelectedRow();
				EquipementModel equipementModel;
				try {
					equipementModel = new EquipementModel();
					rowEquipments = equipementModel.getEquipmentList().get(index);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FileOutputStream fos;
				try {
					fos = new FileOutputStream("src//main//resources//imgs//eqp.jpg");
					fos.write(rowEquipments.getImage());
					fos.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ImageIcon imgThisImg1 = new ImageIcon("src//main//resources//imgs//eqp.jpg");
				Image image = imgThisImg1.getImage(); // transform it
				Image newimg = image.getScaledInstance(150, 130, java.awt.Image.SCALE_SMOOTH); // scale
																								// it
																								// the
																								// smooth
																								// way
				ImageIcon imgThisImg = new ImageIcon(newimg); // transform
																// it back
				lblImg.setIcon(imgThisImg);
				imgThisImg.getImage().flush();

				txtName.setText(rowEquipments.getName());
				txtDesc.setText(rowEquipments.getDescription());
				txtPrice.setText((Float.toString(rowEquipments.getPrice())));
				txtDeal.setText((Float.toString(rowEquipments.getDeal())));
				txtType.setText(rowEquipments.getType());
				RequiredFieldName.setVisible(false);
				RequiredFieldDesc.setVisible(false);
				RequiredFieldPrice.setVisible(false);
				RequiredFieldType.setVisible(false);
				RequiredFieldDeal.setVisible(false);

			}
		});
		scrollPaneEqp.setViewportView(tableEqp);

		// JLabel lblImg = new JLabel("");
		lblImg.setBounds(31, 263, 259, 106);
		StoreEquipments.add(lblImg);

		JButton btnChooseImage = new JButton("Image");
		btnChooseImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser choix = new JFileChooser();
				choix.showOpenDialog(null);
				f = choix.getSelectedFile();
				pathImgEqp = f.getAbsolutePath();
				ImageIcon image = new ImageIcon(pathImgEqp);
				Image im = image.getImage();
				Image myImg = im.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon newImage = new ImageIcon(myImg);
				lblImg.setIcon(newImage);
			}
		});
		btnChooseImage.setBounds(97, 419, 94, 23);
		StoreEquipments.add(btnChooseImage);

		JLabel lblNewLabel = new JLabel("Store");
		lblNewLabel.setBounds(31, 235, 46, 14);
		StoreEquipments.add(lblNewLabel);

		i_imagepath = new JTextField();
		i_imagepath.setBounds(31, 380, 259, 20);
		StoreEquipments.add(i_imagepath);
		i_imagepath.setColumns(10);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isFull = true;
				if (txtName.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldName.setVisible(true);
				} else {
					RequiredFieldName.setVisible(false);
				}

				if (txtDesc.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDesc.setVisible(true);
				} else {
					RequiredFieldDesc.setVisible(false);
				}

				if (txtPrice.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldPrice.setVisible(true);
				} else {
					RequiredFieldPrice.setVisible(false);
				}

				if (txtType.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldType.setVisible(true);
				} else {
					RequiredFieldType.setVisible(false);
				}

				if (txtDeal.getText().trim().length() == 0) {
					isFull = false;
					RequiredFieldDeal.setVisible(true);
				} else {
					RequiredFieldDeal.setVisible(false);
				}
				int index = tableEqp.getSelectedRow();
				if (rowEquipments != null) {
					EquipementModel equipementModel;
					StoreBusiness storeBusiness = new StoreBusiness();
					Equipments Equipment = new Equipments();
					Equipment.setIdEquipment(rowEquipments.getIdEquipment());
					Equipment.setName(txtName.getText());
					Equipment.setDescription(txtDesc.getText());
					Equipment.setPrice(Float.parseFloat(txtPrice.getText()));
					Equipment.setDeal(Float.parseFloat(txtDeal.getText()));
					Equipment.setType(txtType.getText());
					 if (isFull == true){
							try {
								Equipment.setStore(
										storeBusiness.getStoreProxy().findAllStore().get(cbxStore.getSelectedIndex()));
								equipementModel = new EquipementModel();
								EquipementBusiness.updateEquipement(Equipment);
								tableEqp.setModel(equipementModel.getEquipmentsModel());
								JDialog dialog = new JDialog();
								dialog.setAlwaysOnTop(true);
								JOptionPane.showMessageDialog(dialog, "Edit successfully!");
								txtName.setText("");
								txtDesc.setText("");
								txtPrice.setText("");
								txtDeal.setText("");
								txtType.setText("");
								RequiredFieldName.setVisible(false);
								RequiredFieldDesc.setVisible(false);
								RequiredFieldPrice.setVisible(false);
								RequiredFieldType.setVisible(false);
								RequiredFieldDeal.setVisible(false);
							} catch (NamingException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					 }

				} else {
					JOptionPane.showMessageDialog(null, "Please select an equipement to modify");
				}

			}
		});
		btnEdit.setBounds(467, 419, 89, 23);
		StoreEquipments.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableEqp.getSelectedRow();
				if (rowEquipments != null) {

					try {
						EquipementBusiness.deleteEquipement(rowEquipments.getIdEquipment());
						tableEqp.setModel(equipementModel.getEquipmentsModel());
						JDialog dialog = new JDialog();
						dialog.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(dialog, "Delete successfully!");
						txtName.setText("");
						txtDesc.setText("");
						txtPrice.setText("");
						txtDeal.setText("");
						txtType.setText("");
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
				} else {
					JOptionPane.showMessageDialog(null, "Please select an equipement to remove");
				}

			}
		});
		btnDelete.setBounds(583, 419, 89, 23);
		StoreEquipments.add(btnDelete);

		JPanel Location = new JPanel();
		tabbedPane.addTab("Location", null, Location, null);

		JButton btnDisplayFK = new JButton("Display");
		btnDisplayFK.setBounds(203, 413, 89, 23);
		btnDisplayFK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crearMapa();

				} catch (Exception ex) {
				}
			}

		});
		Location.setLayout(null);
		Location.add(btnDisplayFK);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(409, 11, 456, 459);
		Location.add(scrollPane);

		scrollPane.setViewportView(JLabel_ME_Imagen);

		JLabel lblFormat = new JLabel("Format");
		lblFormat.setBounds(31, 226, 46, 14);
		Location.add(lblFormat);

		JCombo_ME_Formato
				.setModel(new DefaultComboBoxModel(new String[] { "png", "png32", "gif", "jpg", "jpg_baseline" }));
		JCombo_ME_Formato.setBounds(143, 223, 231, 20);
		Location.add(JCombo_ME_Formato);

		JLabel lblResorts = new JLabel("Resorts");
		lblResorts.setBounds(31, 82, 46, 14);
		Location.add(lblResorts);

		cbx_lib = new JComboBox();

		cbx_lib.setBounds(143, 79, 231, 20);
		Location.add(cbx_lib);
		clotheModel.fillResortComboBox(cbx_lib);
		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setBounds(31, 158, 46, 14);
		Location.add(lblType_1);

		// JCombo_ME_TipoMapa.setModel(new DefaultComboBoxModel(new String[]
		// {"RoadMap", "Satellite", "Hybrid", "Ground"}));
		JCombo_ME_TipoMapa = new JComboBox();
		JCombo_ME_TipoMapa
				.setModel(new DefaultComboBoxModel(new String[] { "RoadMap", "Satellite", "Hybrid", "Ground" }));
		JCombo_ME_TipoMapa.setBounds(143, 155, 231, 20);
		Location.add(JCombo_ME_TipoMapa);
		/***************************************************/
		JLabel lblZoom = new JLabel("Zoom");
		lblZoom.setBounds(31, 286, 46, 14);
		Location.add(lblZoom);

		JText_ME_Zoom = new JTextField();
		JText_ME_Zoom.setText("12");
		JText_ME_Zoom.setBounds(343, 283, 33, 20);
		Location.add(JText_ME_Zoom);
		JText_ME_Zoom.setColumns(10);

		JText_ME_Escala = new JTextField();
		JText_ME_Escala.setText("1");
		JText_ME_Escala.setBounds(343, 337, 31, 20);
		Location.add(JText_ME_Escala);
		JText_ME_Escala.setColumns(10);

		JLabel lblEchelle = new JLabel("Scale");
		lblEchelle.setBounds(31, 340, 46, 14);
		Location.add(lblEchelle);

		JSlider JSlider_ME_Escala = new JSlider();
		JSlider_ME_Escala.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JText_ME_Escala.setText(String.valueOf(JSlider_ME_Escala.getValue()));
				try {
					crearMapa();

				} catch (Exception ex) {
				}
			}
		});
		JSlider_ME_Escala.setValue(1);
		JSlider_ME_Escala.setMinimum(1);
		JSlider_ME_Escala.setMaximum(2);
		JSlider_ME_Escala.setBounds(143, 337, 188, 26);
		Location.add(JSlider_ME_Escala);
		/****************************************************/
		JSlider JSlider_ME_Zoom = new JSlider();
		JSlider_ME_Zoom.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JText_ME_Zoom.setText(String.valueOf(JSlider_ME_Zoom.getValue()));
				try {
					crearMapa();

				} catch (Exception ex) {
				}
			}
		});
		JSlider_ME_Zoom.setValue(12);
		JSlider_ME_Zoom.setMinimum(1);
		JSlider_ME_Zoom.setMaximum(20);
		JSlider_ME_Zoom.setBounds(143, 286, 188, 26);
		Location.add(JSlider_ME_Zoom);

	}

	/*********************************************************/
	private StaticMaps.Maptype seleccionarTipoMapa() {
		StaticMaps.Maptype tipoMapa = StaticMaps.Maptype.roadmap;
		switch (JCombo_ME_TipoMapa.getSelectedItem().toString()) {
		case "RoadMap":
			tipoMapa = StaticMaps.Maptype.roadmap;
			break;
		case "Satellite":
			tipoMapa = StaticMaps.Maptype.satellite;
			break;
		case "Hybrid":
			tipoMapa = StaticMaps.Maptype.hybrid;
			break;
		case "Ground":
			tipoMapa = StaticMaps.Maptype.terrain;
			break;
		}

		return tipoMapa;
	}

	/***********************************************************/
	private StaticMaps.Format seleccionarFormato() {
		StaticMaps.Format formato = StaticMaps.Format.png;
		switch (JCombo_ME_Formato.getSelectedItem().toString()) {
		case "png":
			formato = StaticMaps.Format.png;
			break;
		case "png32":
			formato = StaticMaps.Format.png32;
			break;
		case "gif":
			formato = StaticMaps.Format.gif;
			break;
		case "jpg":
			formato = StaticMaps.Format.jpg;
			break;
		case "jpg_baseline":
			formato = StaticMaps.Format.jpg_baseline;
			break;
		}
		return formato;
	}

	/************************************************************/
	private void crearMapa() throws MalformedURLException, UnsupportedEncodingException {
		Image imagenMapa = ObjStaticMaps.getStaticMap(cbx_lib.getSelectedItem().toString(),
				Integer.valueOf(JText_ME_Zoom.getText()), new Dimension(500, 500),
				Integer.valueOf(JText_ME_Escala.getText()), this.seleccionarFormato(), this.seleccionarTipoMapa());
		if (imagenMapa != null) {
			ImageIcon imgIcon = new ImageIcon(imagenMapa);
			Icon iconImage = (Icon) imgIcon;
			JLabel_ME_Imagen.setIcon(iconImage);
		}

	}

	/**********************/
	public void Aff_WESTERN() {

		cbx_lib.addItem("California");
		cbx_lib.addItem("Alaska");
		cbx_lib.addItem("Colorado");
		cbx_lib.addItem("Idaho");
		cbx_lib.addItem("Montana");
		cbx_lib.addItem("Nevada");
		cbx_lib.addItem("New Mexico");
		cbx_lib.addItem("Oregon");
		cbx_lib.addItem("Utah");
		cbx_lib.addItem("Wyoming");

	}

	/**************************/
	public void Aff_CANADA() {

		cbx_lib.addItem("Alberta");
		cbx_lib.addItem("British Columbia");
		cbx_lib.addItem("Quebec");

	}

	/**************************/
	public void Aff_EASTERN() {

		cbx_lib.addItem("Maine");
		cbx_lib.addItem("New Hampshire");
		cbx_lib.addItem("New York");
		cbx_lib.addItem("Vermont");

	}

	/**************************/
	public void Aff_EUROPE() {

		cbx_lib.addItem("Andorra");
		cbx_lib.addItem("Austria");
		cbx_lib.addItem("France");
		cbx_lib.addItem("Germany");
		cbx_lib.addItem("Italy");
		cbx_lib.addItem("Switzerland");

	}

	/**************************/
	public void Aff_AMERICA() {

		cbx_lib.addItem("Argentina");
		cbx_lib.addItem("Chile");

	}

	/**************************/
	public void Aff_ASIA() {

		cbx_lib.addItem("Japan");

	}

}
