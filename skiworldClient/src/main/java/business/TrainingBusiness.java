package business;

import java.util.List;

import contracts.TrainingCrudRemote;
import contracts.TrainingCrudRemote;
import entities.Training;

public class TrainingBusiness {
	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/TrainingCrud!contracts.TrainingCrudRemote";

	private static TrainingCrudRemote getProxy() {
		return (TrainingCrudRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static void addTraining(Training Training) {
		getProxy().addTraining(Training);
	}

	public static List<Training> findAllTrainings() {
		return getProxy().findAllTrainings();

	}

	public static boolean updateTraining(Training Training) {
		try {
			getProxy().updateTraining(Training);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public static void deleteTraining(Training Training) {
		getProxy().deleteTraining(Training);

	}
	public static Training  findTrainingByID(int idTraining){
		return getProxy().findTrainingByID(idTraining);
	}

}
