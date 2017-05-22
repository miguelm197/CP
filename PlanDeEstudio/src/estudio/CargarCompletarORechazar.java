package estudio;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.PossibleValue;


// TODO: Auto-generated Javadoc
/**
 * Carga los valores correspondientes, para que se complete la tarea Completar o rechazar.
 * �stos son: 1, Perfecto. Se completa la solicitud de capacitaci�n.
 * 2, Se rechaza la solicitud de capacitaci�n.
 * 3, Solicitar m�s datos al usuario.
 */
public class CargarCompletarORechazar extends ApiaAbstractClass {

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {
		
		//Obtiene entidad y atributo
		Entity currEnt = this.getCurrentEntity();
		Attribute attCompletarORechazar = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR");

		//Crea posibles valores
		PossibleValue pos1 = new PossibleValue("1", "Perfecto. Se completa la solicitud de capacitaci�n.");
		PossibleValue pos2 = new PossibleValue("2", "Se rechaza la solicitud de capacitaci�n.");
		PossibleValue pos3 = new PossibleValue("3", "Solicitar m�s datos al usuario.");

		//Agrega posibles valores al atributo
		attCompletarORechazar.addPossibleValues(pos1);
		attCompletarORechazar.addPossibleValues(pos2);
		attCompletarORechazar.addPossibleValues(pos3);
		
	}

}
