package businessDelegates;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.ResortCrudEJBRemote;
import contracts.StoreCrudRemote;

public class StoreBusinessDelegate {
	public StoreCrudRemote getStoreProxy() throws NamingException{
		InitialContext ctx = new InitialContext();
		Object object = ctx.lookup("/SkiWorld-ear/SkiWorld-ejb/StoreCrud!contracts.StoreCrudRemote");
		StoreCrudRemote proxy = (StoreCrudRemote) object;
		return proxy;
		}

}
