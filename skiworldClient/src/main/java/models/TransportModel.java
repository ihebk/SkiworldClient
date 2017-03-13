package models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import business.HotelBusiness;
import business.ResortBusiness;
import business.TransportBusiness;
import entities.Hotel;
import entities.Resort;
import entities.Transport;

public class TransportModel {

	public List<Resort> resortlist;
	public List<Transport> transporttlist;
	TransportBusiness trBusiness = new TransportBusiness();
	ResortBusiness resortBusiness = new ResortBusiness();

	public DefaultTableModel transportModel() throws SQLException, IOException, NamingException {

		DefaultTableModel transportModel = null;

		transporttlist = trBusiness.getProxy().findAllTransport();

		String col[] = { "Type", "Description", "Price", "Capacity", "Resort" };
		transportModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < transporttlist.size(); i++) {
			String Type = transporttlist.get(i).getType();
			String Description = transporttlist.get(i).getDescription();
			float Price = transporttlist.get(i).getBookingPrice();
			int Capacity = transporttlist.get(i).getCapacity();
			String Resort = transporttlist.get(i).getResort().getName();

			Object[] data = { Type, Description, Price, Capacity, Resort };
			transportModel.addRow(data);

		}

		return transportModel;

	}

	public void fillResortComboBox(JComboBox<String> j) throws NamingException, SQLException, IOException {

		resortlist = resortBusiness.getProxy().findAllResorts();
		for (int i = 0; i < resortlist.size(); i++) {
			j.addItem(resortlist.get(i).getName());

		}

	}

	public List<Transport> getAll() {
		return transporttlist;
	}

}
