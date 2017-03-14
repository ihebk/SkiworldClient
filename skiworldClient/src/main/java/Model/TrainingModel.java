package Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import business.ResortBusinessDelegate;
import business.TrainingBusiness;
import contracts.ResortCrudEJBRemote;
import contracts.TrainingCrudRemote;
import entities.Resort;
import entities.Training;

public class TrainingModel {

	 public List<Training> listeTrainings; 
	 public List<Resort> resortlist; 


//	    public PublicationModel() throws NamingException {
//	    	
//				
//
//	    }
	
	
	public DefaultTableModel getTrainingModel() throws NamingException
	{

	
	
		listeTrainings = TrainingBusiness.findAllTrainings();
	     String col[] = {"Name", "Description", "StartingDate", "EndingDate","Duration"};

	        DefaultTableModel tableModel1 = new DefaultTableModel(col, 0);
//	        System.out.println(listeEvents.size());
	        for (int i = 0; i < listeTrainings.size(); i++) {
	            String Name = listeTrainings.get(i).getName();
	            String Description = listeTrainings.get(i).getDescription();
	            Date StartingDate = listeTrainings.get(i).getStartDate();
	            Date EndingDate = listeTrainings.get(i).getEndDate();
	            String Type = listeTrainings.get(i).getType();
	            


	            Object[] data = {Name, Description, StartingDate, EndingDate,Type};
	            tableModel1.addRow(data);}
	        
	        return tableModel1;
	}
	
	public List<Training> getlisteEvents() {
        return listeTrainings;
    }
	
	
	public final void fillTrainingResortComboBox(JComboBox j) throws NamingException, SQLException, IOException{
	
		resortlist = ResortBusinessDelegate.findAllResorts();
		for (int i=0;i<resortlist.size();i++){
		j.addItem(resortlist.get(i).getName());
			
		}
		
	}
	
	
}


