package models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import org.jsoup.nodes.Entities.EscapeMode;

import business.EquipementBusiness;
import business.StoreBusinessDelegate;
import entities.Equipments;
import entities.Store;

public class EquipementModel {
	public List<Equipments> EquipmentList;
	public List<Store> StoreList;

	
	public EquipementModel() throws NamingException{
	
		EquipmentList= EquipementBusiness.findAllEquipements();
	}
	public DefaultTableModel getEquipmentsModel() throws SQLException, IOException, NamingException {
	
		EquipmentList = EquipementBusiness.findAllEquipements();
		String col[] = { "Name", "Descritption", "Price", "Deal", "Type" };
		DefaultTableModel EquipmentModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < EquipmentList.size(); i++) {
			String Name = EquipmentList.get(i).getName();
			String Description = EquipmentList.get(i).getDescription();
			float Price = EquipmentList.get(i).getPrice();
			float Deal = EquipmentList.get(i).getDeal();
			String Type = EquipmentList.get(i).getType();
			

			Object[] data = { Name, Description, Price, Deal, Type};
			EquipmentModel.addRow(data);
		}
		return EquipmentModel;
	}

	public final void fillStoreComboBox(JComboBox cbxStore  ) throws NamingException, SQLException, IOException{
		
		StoreList = StoreBusinessDelegate.findAllStores();
		for (int i=0;i<StoreList.size();i++){
			cbxStore.addItem(StoreList.get(i).getName());
			
		}
		
	}

	

	public List<Equipments> getEquipmentList() {
		return EquipmentList;
	}
}
