package estudioTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

public class FinalizacionCR extends ApiaAbstractClass{
	
	protected void executeClass() throws BusClassException {

		Entity currEnt = this.getCurrentEntity();
		Attribute attFecha = currEnt.getAttribute("ESTUDIO_FECHA_FIN_STR");
		Attribute combo = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR");
		String comentarios = currEnt.getAttribute("P2_CR_COMENTARIOS").getValueAsString();

		String estado = currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").getValueAsString();
		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();

		User creador = currEnt.getCreator();
		String nombreCreador = creador.getName();
		String mail = this.getUser("mmerelli").getEmail();
		
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

		String mailJefeProy = this.getUser("mmerelli").getEmail();
		
		if ((estado.compareTo("Completar o rechazar solicitud") == 0 && combo.getValueAsString().compareTo("2") == 0)) {
			Helpers.setFecha(this, attFecha);
			currEnt.getAttribute("PROCESO_FINALIZADO_EST_STR").setValue("SI");
			currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").setValue("Rechazado");
			Helpers.notificarFinalizacionCR(this, nombreCreador, mail, mailJefeProy, titulo, comentarios);
		}

	}
}
