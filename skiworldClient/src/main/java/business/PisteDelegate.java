package business;

import java.util.List;

import contracts.PisteCrudEJBRemote;
import entities.Piste;

public class PisteDelegate {
	
	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/PisteCrudEJB!contracts.PisteCrudEJBRemote";

	private static PisteCrudEJBRemote getProxy() {
		return (PisteCrudEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}
	
	
	public static void addPiste(Piste piste){
		getProxy().addPiste(piste);
	}

	public static void updatePiste(Piste piste){
		getProxy().updatePiste(piste);
	}

	public static void deletePiste(int id) {
		getProxy().deletePiste(id);
	}

	public static Piste findPisteById(int id){
		return getProxy().findPisteById(id);
	}

	public static Piste findPisteByLabel(String label){
		return getProxy().findPisteByLabel(label);
	}

	public static List<Piste> findAllPistes(){
		return getProxy().findAllPistes();
		
	}

}
