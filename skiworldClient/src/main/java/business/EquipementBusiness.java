package business;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.EquipementCrudEJBRemote;
import entities.Equipments;





public class EquipementBusiness {
	
	
	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/EquipementCrudEJB!contracts.EquipementCrudEJBRemote";

	private static EquipementCrudEJBRemote getProxy() {
		return (EquipementCrudEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static void addEquipement(Equipments Equipement) {
		getProxy().addEquipement(Equipement);
	}

	public static List<Equipments> findAllEquipements() {
		return getProxy().findAllEquipements();

	}

	public static boolean updateEquipement(Equipments Equipement) {
		try {
			getProxy().updateEquipement(Equipement);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public static void deleteEquipement(int Equipement) {
		getProxy().deleteEquipement(Equipement);

	}

}
