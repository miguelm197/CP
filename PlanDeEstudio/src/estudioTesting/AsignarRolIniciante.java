package estudioTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Process;

public class AsignarRolIniciante extends ApiaAbstractClass {

	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
	

		Process proc = this.getCurrentProcess();
		String iniciante = proc.getCreator().getLogin();

		proc.setRol("INICIADOR", iniciante);

	}
}
