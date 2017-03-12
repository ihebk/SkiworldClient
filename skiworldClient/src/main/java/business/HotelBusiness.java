package business;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.HotelCrudEJBRemote;
import contracts.ResortCrudEJBRemote;

public class HotelBusiness {
	
	public HotelCrudEJBRemote getProxy() throws NamingException{
		
		InitialContext ctx;
			ctx = new InitialContext();
			HotelCrudEJBRemote proxy_hotel = (HotelCrudEJBRemote) ctx
					.lookup("/SkiWorld-ear/SkiWorld-ejb/HotelCrudEJB!contracts.HotelCrudEJBRemote");
			
			return proxy_hotel;
	}
	
	

}
