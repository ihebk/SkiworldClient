package business;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.UserCrudEJBRemote;

public class UserBusiness {
	
	public UserBusiness(String txt)
	{
		System.out.println(" ");
	}
	public UserBusiness( )
	{
		
	}

	public UserCrudEJBRemote getProxy() throws NamingException {
		InitialContext ctx = new InitialContext();
		UserCrudEJBRemote proxyUser = (UserCrudEJBRemote) ctx
				.lookup("/SkiWorld-ear/SkiWorld-ejb/UserCrudEJB!contracts.UserCrudEJBRemote");

		return proxyUser;
	}

}
