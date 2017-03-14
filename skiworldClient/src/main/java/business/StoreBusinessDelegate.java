package business;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.StoreCrudRemote;
import entities.Store;

public class StoreBusinessDelegate {

	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/StoreCrud!contracts.StoreCrudRemote";

	private static StoreCrudRemote getProxy() {
		return (StoreCrudRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static void addStore(Store store) {
		getProxy().addStore(store);
	}

	public static List<Store> findAllStores() {
		return getProxy().findAllStore();

	}

	public static boolean updateStore(Store store) {
		try {
			getProxy().updateStore(store);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public static void removeStore(Store store) {
		getProxy().removeStore(store);

	}

}
