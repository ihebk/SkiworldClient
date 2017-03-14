package models;

import java.util.List;

import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;

import business.ResortBusinessDelegate;
import business.StoreBusinessDelegate;
import entities.Resort;
import entities.Store;

public class StoreModel {
	private List<Store> listStores;

	public StoreModel() throws NamingException {

	listStores =StoreBusinessDelegate.findAllStores();
	}
	public DefaultTableModel getStoreModel(){
		String col[] = { "Name", "Email", "Phone","Resort" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (int i = 0; i < listStores.size(); i++) {
			String nom = listStores.get(i).getName();
			String mail = listStores.get(i).getEmail();
			long tel = listStores.get(i).getPhone();
			String resort = listStores.get(i).getResort().getCountry();
			Object[] data = { nom, mail, tel , resort};
			tableModel.addRow(data);
		}
		return tableModel;
	}
	
	public List<Store> getStoreList(){
		return listStores;
	}

}
