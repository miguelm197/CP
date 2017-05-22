package estudioDesarrollo;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.PossibleValue;

// TODO: Auto-generated Javadoc
/**
 * Carga los Mecanismos de Evaluación de la Capacitaciín al combo donde se elige el mecanismo a utilizar.
 * Son Prueba Final, Sin Prueba y Otros.
 */
public class CargarMecanismoEvaluacion extends ApiaAbstractClass {

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {
			
		
		//Obtiene entidad y atributo
		Entity currEnt = this.getCurrentEntity();
		Attribute attMec = currEnt.getAttribute("CAP_MECANISMOEVALUACION_STR");
		
		//Crea posibles valores
		PossibleValue mec1 = new PossibleValue("Prueba Final", "Prueba Final");
		PossibleValue mec2 = new PossibleValue("Sin Prueba", "Sin Prueba");
		PossibleValue mec3 = new PossibleValue("Otro", "Otro");
		
		//Agrega posibles valores al atributo
		attMec.addPossibleValues(mec1);
		attMec.addPossibleValues(mec2);
		attMec.addPossibleValues(mec3);
		
	}

}
