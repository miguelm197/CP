package estudio;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;

/**
 * Cuando el jefe de proyecto solicita m�s datos, asigna el rol Iniciador al
 * creador del proceso de capacitaci�n.
 * 
 */
public class AsignarRolIniciante extends ApiaAbstractClass {

	/** 
	 * Ejecuta la clase
	 */
	protected void executeClass() throws BusClassException {

	/*	// Obtiene entidad

		Entity currEnt = this.getCurrentEntity();

		// Obtiene atributo combo

		String comboValue = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR").getValueAsString();

		// Si en el combo se seleccion� la opci�n 3 (Solicitar m�s datos al
		// usuario)
		// se setea el rol INICIADOR al creador del proceso

		if (comboValue.compareTo("3") == 0) {
			com.dogma.busClass.object.Process proc = this.getCurrentProcess();
			String iniciante = proc.getCreator().getLogin();
			proc.setRol("INICIADOR", iniciante);
		}*/
	}
}
