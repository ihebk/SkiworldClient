package business;

import java.util.List;

import contracts.TransportCrudEJBRemote;
import entities.Transport;

public class TransportDelegate {
	
	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/TransportCrudEJB!contracts.TransportCrudEJBRemote";

	private static TransportCrudEJBRemote getProxy() {
		return (TransportCrudEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static void addTransport(Transport t){
		getProxy().addTransport(t);
	}

	public static void updateTransport(Transport t){
		getProxy().updateTransport(t);
	}

	public static void deleteTransport(int id){
		getProxy().deleteTransport(id);
		
	}

	public static Transport findTransportById(int id){
		return getProxy().findTransportById(id);
	}

	public static Transport findTransportByLabel(String label){
		
		return getProxy().findTransportByLabel(label);
	}

	public static List<Transport> findAllTransport(){
		return getProxy().findAllTransport();
	}
	
	
}
