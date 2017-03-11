package tableModels;

import java.util.List;

import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;

import businessDelegates.ResortBusinessDelegate;
import entities.Resort;

public class ResortModel {
	private List<Resort> listResorts;

	public ResortModel() throws NamingException {
	ResortBusinessDelegate rbd = new ResortBusinessDelegate();
	listResorts =rbd.getResortProxy().findAllResort();
	}
	public DefaultTableModel getResortModel(){
		String col[] = { "Nom", "Email", "Téléphone" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		for (int i = 0; i < listResorts.size(); i++) {
			String nom = listResorts.get(i).getName();
			String mail = listResorts.get(i).getCountry();
			String tel = listResorts.get(i).getLocation();

			Object[] data = { nom, mail, tel };
			tableModel.addRow(data);
		}
		return tableModel;
	}
	
	

}
