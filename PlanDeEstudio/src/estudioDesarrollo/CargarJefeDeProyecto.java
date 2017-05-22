package estudioDesarrollo;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.PossibleValue;

// TODO: Auto-generated Javadoc
/**
 * Carga los Jefes de Proyecto al combo donde se elige el jefe de proyecto en Apia.
 * Son Federico, Jorge y José.
 */
public class CargarJefeDeProyecto extends ApiaAbstractClass {

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {
		
		//Obtiene entidad y atributo
		Entity currEnt = this.getCurrentEntity();
		Attribute attJefes = currEnt.getAttribute("CAP_JEFEPROYECTO_STR");
		
		//Crea posibles valores
		PossibleValue fede = new PossibleValue("Federico", "Federico");
		PossibleValue jorge = new PossibleValue("Jorge", "Jorge");
		PossibleValue jose = new PossibleValue("José", "José");
		
		//Agrega posibles valores al atributo
		attJefes.addPossibleValues(fede);
		attJefes.addPossibleValues(jorge);
		attJefes.addPossibleValues(jose);
		
	}

}
