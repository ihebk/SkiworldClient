package models;

import java.util.List;

import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;

import business.ResortBusinessDelegate;
import entities.Resort;



public class ResortModel {
	private List<Resort> listResorts;
	public ResortModel() throws NamingException {	
	listResorts =ResortBusinessDelegate.findAllResorts();
	}
	public DefaultTableModel getResortModel(String txt){
		String col[] = { "Name", "Country", "Adresse" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		if(txt!=null)
			listResorts =ResortBusinessDelegate.findResort(txt);
		for (int i = 0; i < listResorts.size(); i++) {
			String nom = listResorts.get(i).getName();
			String mail = listResorts.get(i).getCountry();
			String tel = listResorts.get(i).getLocation();

			Object[] data = { nom, mail, tel };
			tableModel.addRow(data);
		}
		return tableModel;
	}
	
	public List<Resort> getResortList(){
		return listResorts;
	}

}
