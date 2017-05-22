package estudio;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.PossibleValue;

/**
 * Carga los valores: Aprobado y NO Aprobado, para que se complete la tarea Aprobación.
 */
public class CargarAprobacion extends ApiaAbstractClass {
	@Override
	protected void executeClass() throws BusClassException {
		
		//Obtiene atributo
		Attribute attAprobacion = this.getCurrentEntity().getAttribute("EAS_APROBACION");

		//Crea posibles valores
		PossibleValue pos1 = new PossibleValue("1", "Aprobado");
		PossibleValue pos2 = new PossibleValue("2", "NO Aprobado");

		//Agrega posibles valores al atributo
		attAprobacion.addPossibleValues(pos1);
		attAprobacion.addPossibleValues(pos2);
	}
}
