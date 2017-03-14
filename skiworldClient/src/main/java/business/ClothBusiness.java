package business;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.ClotheCrudEJBRemote;
import entities.Clothes;

public class ClothBusiness {

	
	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/ClotheCrudEJB!contracts.ClotheCrudEJBRemote";

	private static ClotheCrudEJBRemote getProxy() {
		return (ClotheCrudEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static void addClothe(Clothes Clothe) {
		getProxy().addClothe(Clothe);
	}

	public static List<Clothes> findAllClothes() {
		return getProxy().findAllClothes();

	}

	public static boolean updateClothe(Clothes Clothe) {
		try {
			getProxy().updateClothe(Clothe);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public static void deleteClothe(int id) {
		getProxy().deleteClothe(id);

	}
}
