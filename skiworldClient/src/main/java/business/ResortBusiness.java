package business;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.ResortCrudEJBRemote;

public class ResortBusiness {

	public ResortCrudEJBRemote getProxy() throws NamingException{

	InitialContext ctx;
	ctx = new InitialContext();
	
	ResortCrudEJBRemote proxy_resort = (ResortCrudEJBRemote) ctx
			.lookup("/SkiWorld-ear/SkiWorld-ejb/ResortCrudEJB!contracts.ResortCrudEJBRemote");
	
	return proxy_resort;
	
	}
}
