package estudio;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

/**
 * Se ejecuta cuando se cancela un proceso de capacitación.
 * . Se setean los atributos Proceso Finalizado -> Sí y Estado Actual de la capacitación -> Cancelado.
 *  Además se envía un mail al creador y al jefe de proyecto del proceso indicando que se canceló el mismo.
 */
public class CancelarCapacitacion extends ApiaAbstractClass {

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {
		
		//Obtiene entida y atributos
		Entity currEnt = this.getCurrentEntity();
		Attribute attFecha = currEnt.getAttribute("ESTUDIO_FECHA_FIN_STR");

		String mail = currEnt.getCreator().getEmail();
		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();

		//Obtiene usuario creador y nombre
		User creador = currEnt.getCreator();
		String nombreCreador = creador.getName();

		// Obtiene jefe de proyecto
		User jefeProyecto = null;
		String valuejefeProy = this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO").getValueAsString();
		if (valuejefeProy.compareTo("José") == 0)
			jefeProyecto = this.getUser("jrussomano");
		else if (valuejefeProy.compareTo("Jorge") == 0)
			jefeProyecto = this.getUser("jartave");
		else if (valuejefeProy.compareTo("Federico") == 0)
			jefeProyecto = this.getUser("froda");

		//Obtiene mail de Jefe de Proyecto
		String mailJefeProy = jefeProyecto.getEmail();

		//Setea los valores y envía mail
		Helpers.setFecha(this, attFecha);
		currEnt.getAttribute("PROCESO_FINALIZADO_EST_STR").setValue("SI");
		currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").setValue("Cancelado");
		Helpers.notificarCancelacionProc(this, nombreCreador, mail, mailJefeProy, titulo);

	}

}
