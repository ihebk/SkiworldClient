package business;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.ResortCrudEJBRemote;
import entities.Resort;

public class ResortBusinessDelegate {

	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/ResortCrudEJB!contracts.ResortCrudEJBRemote";

	private static ResortCrudEJBRemote getProxy() {
		return (ResortCrudEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static void addResort(Resort Resort) {
		getProxy().addResort(Resort);
	}

	public static List<Resort> findAllResorts() {
		return getProxy().findAllResort();

	}

	public static boolean updateResort(Resort Resort) {
		try {
			getProxy().updateResort(Resort);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public static void removeResort(Resort Resort) {
		getProxy().removeResort(Resort);

	}
	public static List<Resort> findResort(String txt){
		return getProxy().findResort(txt);
	}
}

