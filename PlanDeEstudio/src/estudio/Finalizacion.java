package estudio;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;


// TODO: Auto-generated Javadoc
/**
 * Se ejecuta cuando finaliza un proceso de capacitación en Apia. Se setean 
 * los atributos Proceso Finalizado -> Sí y Estado Actual de la capacitación -> Finalizado. 
 * Además se envía un mail al creador y al jefe de proyecto indicando que finalizó el proceso.
 */
public class Finalizacion extends ApiaAbstractClass {
	
	/* (non-Javadoc)
	 * @see com.dogma.busClass.ApiaAbstractClass#executeClass()
	 */
	@Override
	protected void executeClass() throws BusClassException {

		Entity currEnt = this.getCurrentEntity();
		Attribute attFecha = currEnt.getAttribute("ESTUDIO_FECHA_FIN_STR");
		Attribute checkFin = currEnt.getAttribute("RA_FINALIZARESTUDIO");

		String estado = currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").getValueAsString();
		String mail = currEnt.getCreator().getEmail();
		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();

		User creador = currEnt.getCreator();
		String nombreCreador = creador.getName();
		
		// Obtengo jefe de proyecto
		User jefeProyecto = null;
		// this.addMessage(this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO").getValueAsString());
		String valuejefeProy = this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO").getValueAsString();
		if (valuejefeProy.compareTo("José") == 0)
			jefeProyecto = this.getUser("jrussomano");
		else if (valuejefeProy.compareTo("Jorge") == 0)
			jefeProyecto = this.getUser("jartave");
		else if (valuejefeProy.compareTo("Federico") == 0)
			jefeProyecto = this.getUser("froda");

		String mailJefeProy = jefeProyecto.getEmail();
		
		if ((estado.compareTo("Registrar avance de estudio") == 0 && checkFin.getValueAsString().compareTo("true") == 0)
				|| (estado.compareTo("Finalizar estudio") == 0)) {
			Helpers.setFecha(this, attFecha);
			currEnt.getAttribute("PROCESO_FINALIZADO_EST_STR").setValue("SI");
			currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").setValue("Finalizado");
			Helpers.notificarFinalizacionProc(this, nombreCreador, mail, mailJefeProy, titulo);
		}

	}

}
