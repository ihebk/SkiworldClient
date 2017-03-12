package tableModels;

import java.util.List;

import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;

import businessDelegates.ResortBusinessDelegate;
import businessDelegates.StoreBusinessDelegate;
import entities.Resort;
import entities.Store;

public class StoreModel {
	private List<Store> listStores;

	public StoreModel() throws NamingException {
		StoreBusinessDelegate rbd = new StoreBusinessDelegate();
	listStores =rbd.getStoreProxy().findAllStore();
	}
	public DefaultTableModel getStoreModel(){
		String col[] = { "Name", "Email", "Phone","Resort" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (int i = 0; i < listStores.size(); i++) {
			String nom = listStores.get(i).getName();
			String mail = listStores.get(i).getEmail();
			long tel = listStores.get(i).getPhone();
			String resort = listStores.get(i).getResort().getName();
			Object[] data = { nom, mail, tel , resort};
			tableModel.addRow(data);
		}
		return tableModel;
	}
	
	public List<Store> getStoreList(){
		return listStores;
	}

}
