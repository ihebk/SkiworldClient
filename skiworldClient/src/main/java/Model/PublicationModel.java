package Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import business.EventBusiness;
import business.ResortBusinessDelegate;
import contracts.EventCrudRemote;
import contracts.ResortCrudEJBRemote;
import entities.Event;
import entities.Resort;
//import entities.User;
//import entities.UserEvent;

public class PublicationModel {

	 public List<Event> listeEvents; 
	 public List<Resort> resortlist; 

//	 Event event = new Event();
//	 List<UserEvent> userEvent =event.getUserevent();
//	 User user1 = new User();
	 
	
	
	public DefaultTableModel getEventModel() throws NamingException
	{

	
		listeEvents = EventBusiness.findAllEvents();
	     String col[] = {"Name", "Description", "StartingDate", "EndingDate","Type"};

	        DefaultTableModel tableModel1 = new DefaultTableModel(col, 0);
//	        System.out.println(listeEvents.size());
	        for (int i = 0; i < listeEvents.size(); i++) {
	            String Name = listeEvents.get(i).getName();
	            String Description = listeEvents.get(i).getDescription();
	            Date StartingDate = listeEvents.get(i).getStartDate();
	            Date EndingDate = listeEvents.get(i).getEndDate();
	            String Type = listeEvents.get(i).getType();
	            


	            Object[] data = {Name, Description, StartingDate, EndingDate,Type};
	            tableModel1.addRow(data);}
	        
	        return tableModel1;
	}
	
	public List<Event> getlisteEvents() {
        return listeEvents;
    }
	
	
	public final void fillEventResortComboBox(JComboBox j) throws NamingException, SQLException, IOException{
		
		resortlist = ResortBusinessDelegate.findAllResorts();
		for (int i=0;i<resortlist.size();i++){
		j.addItem(resortlist.get(i).getName());
			
		}
		
	}
	
	
	
	
	
}


