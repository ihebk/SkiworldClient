package models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import business.ClothBusiness;

import business.StoreBusinessDelegate;
import entities.Clothes;
import entities.Store;

public class ClotheModel {
	
	public List<Clothes> ClotheList;
	public List<Store> StoreList;
	
	
	
	public ClotheModel() throws NamingException{

		ClotheList = ClothBusiness.findAllClothes();
	}
	
	public DefaultTableModel getClothesModel() throws SQLException, IOException, NamingException {
		
		ClotheList = ClothBusiness.findAllClothes();
		String col[] = { "Name", "Descritption", "Price", "Deal", "Type" };
		DefaultTableModel ClotheModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < ClotheList.size(); i++) {
			String Name = ClotheList.get(i).getName();
			String Description = ClotheList.get(i).getDescription();
			float Price = ClotheList.get(i).getPrice();
			float Deal = ClotheList.get(i).getDeal();
			String Type = ClotheList.get(i).getType();
			

			Object[] data = { Name, Description, Price, Deal, Type};
			ClotheModel.addRow(data);
		}
		return ClotheModel;
	}
	
	public final void fillStoreComboBox(JComboBox cbxStore  ) throws NamingException, SQLException, IOException{
		
		StoreList = StoreBusinessDelegate.findAllStores();
		for (int i=0;i<StoreList.size();i++){
			cbxStore.addItem(StoreList.get(i).getName());
			
		}
		}
	
	public final void fillResortComboBox(JComboBox cbxStore  ) throws NamingException, SQLException, IOException{
		
		StoreList = StoreBusinessDelegate.findAllStores();
		for (int i=0;i<StoreList.size();i++){
			cbxStore.addItem(StoreList.get(i).getLocation());
			
		}
		}

	public List<Clothes> getClotheList() {
		return ClotheList;
	}


	
	

}
