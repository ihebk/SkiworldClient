package business;

import java.util.List;

import contracts.HotelCrudEJBRemote;
import entities.Hotel;

public class HotelDelegate {
	
	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote";

	private static HotelCrudEJBRemote getProxy() {
		return (HotelCrudEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static void addHotel(Hotel hotel){
		
		getProxy().addHotel(hotel);
	}

	public static void updateHotel(Hotel hotel){
		getProxy().updateHotel(hotel);
	}

	public static void deleteHotel(int id) {
		getProxy().deleteHotel(id);
	}

	public static Hotel findHotelById(int id){
		return getProxy().findHotelById(id);
	}

	public static Hotel findHotelByLabel(String label){
		return getProxy().findHotelByLabel(label);
		
	}

	public static List<Hotel> findAllHotels(){
		return getProxy().findAllHotels();
		
	}


}
