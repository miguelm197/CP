package estudio;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;


// TODO: Auto-generated Javadoc
/**
 * Se ejecuta cuando se rechaza la capacitación en la tarea Completar o Rechazar solicitud.
 * Se setean 
 * los atributos Proceso Finalizado -> Sí y Estado Actual de la capacitación -> Rechazado. 
 * Además se envía un mail al creador y al jefe de proyecto indicando que se rechazó el proceso.
 */
public class FinalizacionCR extends ApiaAbstractClass{
	
	/* (non-Javadoc)
	 * @see com.dogma.busClass.ApiaAbstractClass#executeClass()
	 */
	protected void executeClass() throws BusClassException {

		Entity currEnt = this.getCurrentEntity();
		Attribute attFecha = currEnt.getAttribute("ESTUDIO_FECHA_FIN_STR");
		Attribute combo = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR");
		String comentarios = currEnt.getAttribute("P2_CR_COMENTARIOS").getValueAsString();

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
		
		if ((estado.compareTo("Completar o rechazar solicitud") == 0 && combo.getValueAsString().compareTo("2") == 0)) {
			Helpers.setFecha(this, attFecha);
			currEnt.getAttribute("PROCESO_FINALIZADO_EST_STR").setValue("SI");
			currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").setValue("Rechazado");
			Helpers.notificarFinalizacionCR(this, nombreCreador, mail, mailJefeProy, titulo, comentarios);
		}

	}
}
