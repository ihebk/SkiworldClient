package models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import business.HotelDelegate;
import business.ResortBusiness;
import entities.Hotel;
import entities.Resort;

public class HotelModel {

	public List<Hotel> hotellist;
	public List<Resort> resortlist;
	ResortBusiness resortBusiness = new ResortBusiness();

	public DefaultTableModel hotelModel() throws SQLException, IOException, NamingException {

		DefaultTableModel hotelModel = null;

		hotellist = HotelDelegate.findAllHotels();

		String col[] = { "Name", "Description", "Rating", "Capacity", "Resort" };
		hotelModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < hotellist.size(); i++) {
			String Name = hotellist.get(i).getName();
			String Description = hotellist.get(i).getDescription();
			float Rating = hotellist.get(i).getRating();
			int Capacity = hotellist.get(i).getCapacity();
			String Resort = hotellist.get(i).getResort().getName();

			Object[] data = { Name, Description, Rating, Capacity, Resort };
			hotelModel.addRow(data);

		}

		return hotelModel;

	}

	public void fillResortComboBox(JComboBox<String> j) throws NamingException, SQLException, IOException {

		resortlist = resortBusiness.getProxy().findAllResorts();
		for (int i = 0; i < resortlist.size(); i++) {
			j.addItem(resortlist.get(i).getName());

		}

	}

	public List<Hotel> getAll() {
		return hotellist;
	}

}
