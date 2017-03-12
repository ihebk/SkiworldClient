package business;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.PisteCrudEJBRemote;

public class PisteBusiness {
	
	public PisteCrudEJBRemote getProxy() throws NamingException{

		InitialContext ctx;
		ctx = new InitialContext();
		
		PisteCrudEJBRemote proxy_piste = (PisteCrudEJBRemote) ctx
				.lookup("/SkiWorld-ear/SkiWorld-ejb/PisteCrudEJB!contracts.PisteCrudEJBRemote");
		
		return proxy_piste;
		
		}

}
