package estudioTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;

public class AsignarRolIniciante extends ApiaAbstractClass {

	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Entity currEnt = this.getCurrentEntity();
		String comboValue = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR").getValueAsString();
		
		//this.addMessage(comboValue);
		
		if (comboValue.compareTo("3") == 0) {
			com.dogma.busClass.object.Process proc = this.getCurrentProcess();
			String iniciante = proc.getCreator().getLogin();
			//this.addMessage(iniciante);

			proc.setRol("INICIADOR", iniciante);
		}
	}
}
