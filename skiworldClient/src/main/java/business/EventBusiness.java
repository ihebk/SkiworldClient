package business;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import contracts.EventCrudRemote;
import entities.Event;

public class EventBusiness {

	
	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/EventCrud!contracts.EventCrudRemote";

	private static EventCrudRemote getProxy() {
		return (EventCrudRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static void addEvent(Event Event) {
		getProxy().addEvent(Event);
	}

	public static List<Event> findAllEvents() {
		return getProxy().findAllEvents();

	}

	public static boolean updateEvent(Event Event) {
		try {
			getProxy().updateEvent(Event);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public static void deleteEvent(Event Event) {
		getProxy().deleteEvent(Event);

	}
	public static Event findEventByID(int idEvent) {
		return getProxy().findEventByID(idEvent);
	}
	
}
