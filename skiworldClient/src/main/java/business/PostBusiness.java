package business;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.PostCrudEJBRemote;

public class PostBusiness {

	public PostCrudEJBRemote getProxy() throws NamingException {
		InitialContext ctx = new InitialContext();
		PostCrudEJBRemote proxyPost = (PostCrudEJBRemote) ctx
				.lookup("/SkiWorld-ear/SkiWorld-ejb/PostCrudEJB!services.PostCrudEJBRemote");

		return proxyPost;
	}

}
