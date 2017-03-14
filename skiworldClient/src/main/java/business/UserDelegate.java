package business;

import java.util.List;

import contracts.UserCrudEJBRemote;
import entities.User;
import exceptions.MoreThanOneResultException;
import exceptions.NoResultFoundException;

public class UserDelegate {

	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/UserCrudEJB!contracts.UserCrudEJBRemote";

	private static UserCrudEJBRemote getProxy() {
		return (UserCrudEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static void addUser(User user) {
		getProxy().addUser(user);
	}

	public static List<User> findAllUsers() {
		return getProxy().findAllUsers();

	}

	public static boolean updateUser(User user) {
		try {
			getProxy().updateUser(user);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public static void deleteUser(User user) {
		getProxy().deleteUser(user);

	}

	public static User findById(int idUser) {

		return getProxy().findById(idUser);
	}

	public static User findByUsername(String username) throws MoreThanOneResultException, NoResultFoundException {

		return getProxy().findByUsername(username);
	}

	public static boolean doCheckMailExistance(String email) {
		try {
			getProxy().checkMailExistance(email);
			System.out.println("l9it mail");
			return true;
		} catch (javax.persistence.NoResultException e) {
			return false;
		}
	}

	public static boolean doCheckUsernameExistance(String username) {

		try {
			getProxy().checkUsernameExistance(username);
			System.out.println("l9it username");
			return true;
		} catch (javax.persistence.NoResultException e) {
			return false;
		}

	}

}
