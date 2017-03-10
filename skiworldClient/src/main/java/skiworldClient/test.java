package skiworldClient;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;

import contracts.HotelCrudEJBRemote;

public class test {
	
 

	public DefaultTableModel all() {
		InitialContext ctx;

		DefaultTableModel tableModel = null;
		try {
			ctx = new InitialContext();

			HotelCrudEJBRemote proxy_hotel = (HotelCrudEJBRemote) ctx
					.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote");

			String col[] = { "Name", "Descritption", "Rating", "Capacity" };
			tableModel = new DefaultTableModel(col, 0);
			for (int i = 0; i < proxy_hotel.findAllHotels().size(); i++) {
				String Name = proxy_hotel.findAllHotels().get(i).getName();
				String Description = proxy_hotel.findAllHotels().get(i).getDescription();
				float Rating = proxy_hotel.findAllHotels().get(i).getRating();
				int Capacity = proxy_hotel.findAllHotels().get(i).getCapacity();

				Object[] data = { Name, Description, Rating, Capacity };
				tableModel.addRow(data);
			}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return tableModel;

	}


}
