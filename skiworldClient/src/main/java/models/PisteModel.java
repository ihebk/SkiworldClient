package models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import business.HotelBusiness;
import business.PisteBusiness;
import business.ResortBusiness;
import entities.Piste;
import entities.Resort;

public class PisteModel {

	public List<Piste> pistelist;
	PisteBusiness pisteBusiness = new PisteBusiness();
	public List<Resort> resortlist;
	ResortBusiness resortBusiness = new ResortBusiness();

	public DefaultTableModel pisteModel() throws SQLException, IOException, NamingException {

		DefaultTableModel pisteModel = null;
		String Resort="";

		pistelist = pisteBusiness.getProxy().findAllPistes();
		resortlist=resortBusiness.getProxy().findAllResorts();

		String col[] = { "Name", "Description", "Type", "Resort" };
		pisteModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < pistelist.size(); i++) {
			String Name = pistelist.get(i).getName();
			String Description = pistelist.get(i).getDescription();
			String Type = pistelist.get(i).getType();
			for (int j = 0; j < resortlist.size(); j++){
				if (resortlist.get(j).getPiste().getIdPiste()== pistelist.get(i).getIdPiste()){
				 Resort = pistelist.get(i).getResort().getName();}
				

			}

			Object[] data = { Name, Description, Type, Resort };
			pisteModel.addRow(data);

		}

		return pisteModel;

	}

public final void fillResortComboBox(JComboBox j  ) throws NamingException, SQLException, IOException{
	
	resortlist = resortBusiness.getProxy().findAllResorts();
	for (int i=0;i<resortlist.size();i++){
	j.addItem(resortlist.get(i).getName());}
}
}
		
		


