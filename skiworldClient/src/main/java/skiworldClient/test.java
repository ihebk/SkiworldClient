package skiworldClient;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;

import contracts.HotelCrudEJBLocal;
import contracts.HotelCrudEJBRemote;
import entities.Hotel;

public class test {
	public List<Hotel> hotellist;
	
	
	public DefaultTableModel all() throws SQLException, IOException {
		InitialContext ctx,ctxx;

		DefaultTableModel tableModel=null;
		try {
			
			ctx = new InitialContext();

			HotelCrudEJBRemote proxy_hotel = (HotelCrudEJBRemote) ctx
					.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote");
//			HotelCrudEJBLocal proxy_hotelLocal = (HotelCrudEJBLocal) ctx
//					.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBLocal");
			hotellist= proxy_hotel.findAllHotels();

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
