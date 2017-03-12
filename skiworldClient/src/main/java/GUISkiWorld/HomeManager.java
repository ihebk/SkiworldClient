package GUISkiWorld;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.Border;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import businessDelegates.ResortBusinessDelegate;
import businessDelegates.StoreBusinessDelegate;
import entities.Resort;
import entities.Store;
import tableModels.ResortModel;
import tableModels.StoreModel;

import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class HomeManager {

	private JFrame ManagerGUI;
	private JTextField storeName;
	private JTextField storeLocation;
	private JTextField phoneStore;
	private JTextField emailStore;
	private JComboBox<String> resortStore;
	public Store rowSelectedStore;
	public List<Resort> listStore;
	private JTable storeTable;
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
	 * @throws NamingException 
	 */
	public HomeManager() throws NamingException {
		initialize();
		fillCbResort();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws NamingException 
	 */
	private void initialize() throws NamingException {
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
		JPanel Stores = new JPanel();
		Stores.setLayout(null);
		tabbedPane.addTab("Stores Management", null, Stores, null);

		JTextArea storeDescription = new JTextArea();
		storeDescription.setBounds(139, 62, 204, 69);
		Stores.add(storeDescription);
		
		storeTable = new JTable();
	
		
		
		JLabel lblStoreName = new JLabel("Store name :");
		lblStoreName.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblStoreName.setBounds(10, 27, 99, 25);
		Stores.add(lblStoreName);

		storeName = new JTextField();
		storeName.setColumns(10);
		storeName.setBounds(139, 31, 149, 20);
		Stores.add(storeName);

		JLabel lblStoreDesc = new JLabel("Description :");
		lblStoreDesc.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblStoreDesc.setBounds(10, 60, 99, 25);
		Stores.add(lblStoreDesc);

		JButton addStore = new JButton("Add");
		addStore.setFont(new Font("Dialog", Font.PLAIN, 16));
		addStore.setBounds(39, 288, 99, 23);
		Stores.add(addStore);
		addStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreBusinessDelegate rdb = new StoreBusinessDelegate();
				ResortBusinessDelegate res = new ResortBusinessDelegate();
				Store r = new Store();
				r.setName(storeName.getText());
				r.setDescription(storeDescription.getText());
				r.setPhone(Long.parseLong(phoneStore.getText()));
				r.setLocation(storeLocation.getText());
				r.setEmail(emailStore.getText());
				try {
					r.setResort(res.getResortProxy().findAllResort().get(resortStore.getSelectedIndex()));
				} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				try {
					rdb.getStoreProxy().addStore(r);
					StoreModel storeModel = new StoreModel();
					storeTable.setModel(storeModel.getStoreModel());
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		storeLocation = new JTextField();
		storeLocation.setColumns(10);
		storeLocation.setBounds(139, 212, 148, 20);
		Stores.add(storeLocation);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(516, 187, 150, 130);
		Stores.add(label_3);

		JScrollPane storeScroll = new JScrollPane();
		storeScroll.setBounds(424, 11, 329, 253);
		Stores.add(storeScroll);
		storeScroll.setViewportView(storeTable);
		StoreModel storeModel = new StoreModel();
		storeTable.setModel(storeModel.getStoreModel());
		JButton editStore = new JButton("Edit");
		editStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		editStore.setFont(new Font("Dialog", Font.PLAIN, 16));
		editStore.setBounds(183, 288, 89, 23);
		Stores.add(editStore);

		JButton removeStore = new JButton("Remove");
		removeStore.setFont(new Font("Dialog", Font.PLAIN, 16));
		removeStore.setBounds(321, 288, 89, 23);
		Stores.add(removeStore);

		JLabel label_4 = new JLabel("Adresse :");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_4.setBounds(10, 207, 99, 25);
		Stores.add(label_4);

		JLabel label_5 = new JLabel("");
		label_5.setBounds(0, 0, 763, 316);
		Stores.add(label_5);
		
		JLabel lblPhoneStore = new JLabel("Phone");
		lblPhoneStore.setBounds(10, 153, 89, 14);
		Stores.add(lblPhoneStore);
		
		JLabel lblEmailStore = new JLabel("Email :");
		lblEmailStore.setBounds(10, 178, 46, 14);
		Stores.add(lblEmailStore);
		
		phoneStore = new JTextField();
		phoneStore.setBounds(139, 150, 89, 20);
		Stores.add(phoneStore);
		phoneStore.setColumns(10);
		
		emailStore = new JTextField();
		emailStore.setBounds(139, 181, 86, 20);
		Stores.add(emailStore);
		emailStore.setColumns(10);
		
	    resortStore = new JComboBox();
		resortStore.setBounds(315, 150, 99, 20);
		Stores.add(resortStore);
		resortStore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				try {
//					//fillCbResort();
//				} catch (NamingException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
		});
		JLabel lblResort_Store = new JLabel("Resort");
		lblResort_Store.setBounds(253, 153, 51, 14);
		Stores.add(lblResort_Store);
		Stores.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { storeDescription, lblStoreName, storeName, lblStoreDesc,
						addStore, storeLocation, label_3, storeScroll, editStore, removeStore, label_4, label_5 }));
		storeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = storeTable.getSelectedRow();
				StoreModel rt;
				try {
					rt = new StoreModel();
					rowSelectedStore = rt.getStoreList().get(index);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				storeName.setText(rowSelectedStore.getName());
				storeDescription.setText(rowSelectedStore.getDescription());
				storeLocation.setText(rowSelectedStore.getLocation());
				phoneStore.setText(Long.toString(rowSelectedStore.getPhone()));
				emailStore.setText(rowSelectedStore.getEmail());
				addStore.setEnabled(false);
			}
		});
		editStore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rowSelectedStore == null) {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Please select a Store to edit");
				} else {
					Store r = new Store();
					StoreBusinessDelegate rdb = new StoreBusinessDelegate();
					r.setIdStore(rowSelectedStore.getIdStore());
					r.setName(storeName.getText());
					r.setDescription(storeDescription.getText());
					r.setResort(rowSelectedStore.getResort());
					r.setLocation(storeLocation.getText());
					r.setEmail(emailStore.getText());
					r.setPhone(Long.parseLong(phoneStore.getText()));
					Border border = BorderFactory.createLineBorder(Color.red);
					try {
						if((r.getDescription()==null) ||(r.getName()==null)||(r.getLocation()==null)
								||(r.getEmail()==null)){
							if(r.getName()==null){
								lblStoreName.setText("<html> <font color = RED >* Titre du livre :</font></html>");
							}
						}else
						rdb.getStoreProxy().updateStore(r);
						StoreModel storemodel = new StoreModel();
						storeTable.setModel(storemodel.getStoreModel());
						addStore.setEnabled(true);
						storeName.setText("");
						storeDescription.setText("");
						storeLocation.setText("");
						phoneStore.setText("");
						emailStore.setText("");
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		removeStore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rowSelectedStore == null) {
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Please select a Store to remove");
				} else {
					Store r = new Store();
					StoreBusinessDelegate rdb = new StoreBusinessDelegate();
					r.setIdStore(rowSelectedStore.getIdStore());
					r=rowSelectedStore;
					try {
						rdb.getStoreProxy().removeStore(r);
						StoreModel storemodel = new StoreModel();
						storeTable.setModel(storemodel.getStoreModel());
						addStore.setEnabled(true);
						storeName.setText("");
						storeDescription.setText("");
						storeLocation.setText("");
						phoneStore.setText("");
						emailStore.setText("");
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
	}
	public void fillCbResort() throws NamingException{
		ResortBusinessDelegate bdr = new ResortBusinessDelegate();
		List<Resort> list = new ArrayList<>();
		list= bdr.getResortProxy().findAllResort();
		for(Resort name: list)
			resortStore.addItem(name.getName());	
	}
}
