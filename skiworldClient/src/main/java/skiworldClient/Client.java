package skiworldClient;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.UserRemote;
import entities.User;

public class Client {

	public static void main(String[] args) throws NamingException {
		
		InitialContext ctx = new InitialContext();
		Object objet = ctx.lookup("/SkiWorld-ear/SkiWorld-ejb/UserCrudService!contracts.UserRemote");
		
		UserRemote proxy = (UserRemote) objet;
		User u = new User();
		u.setUsername("test");
		proxy.addUser(u);
	}

}
