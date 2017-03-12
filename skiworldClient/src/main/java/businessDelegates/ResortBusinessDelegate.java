package businessDelegates;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.ResortCrudEJBRemote;

public class ResortBusinessDelegate {
	public ResortCrudEJBRemote getResortProxy() throws NamingException{
	InitialContext ctx = new InitialContext();
	Object object = ctx.lookup("/SkiWorld-ear/SkiWorld-ejb/ResortCrudEJB!contracts.ResortCrudEJBRemote");
	ResortCrudEJBRemote proxy = (ResortCrudEJBRemote) object;
	return proxy;
	}
}
