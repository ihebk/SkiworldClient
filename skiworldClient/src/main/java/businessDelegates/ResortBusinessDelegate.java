package businessDelegates;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.ResortCrudRemote;

public class ResortBusinessDelegate {
	public ResortCrudRemote getResortProxy() throws NamingException{
	InitialContext ctx = new InitialContext();
	Object object = ctx.lookup("/SkiWorld-ear/SkiWorld-ejb/ResortCrud!contracts.ResortCrudRemote");
	ResortCrudRemote proxy = (ResortCrudRemote) object;
	return proxy;
	}
}
