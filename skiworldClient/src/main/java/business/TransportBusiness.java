package business;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.TransportCrudEJBRemote;


public class TransportBusiness {
	
	public TransportCrudEJBRemote getProxy() throws NamingException{

		InitialContext ctx;
		ctx = new InitialContext();
		
		TransportCrudEJBRemote proxy_transport = (TransportCrudEJBRemote) ctx
				.lookup("/SkiWorld-ear/SkiWorld-ejb/TransportCrudEJB!contracts.TransportCrudEJBRemote");
		
		return proxy_transport;
		
		}

}
