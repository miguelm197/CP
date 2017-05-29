package estudioTesting;

import java.util.Collection;

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
		User usuarioCreador = currEnt.getCreator();

		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
		String encargado = currEnt.getAttribute("SE_JEFEPROYECTO").getValueAsString();

		Attribute attFecha = currEnt.getAttribute("ESTUDIO_FECHA_FIN_STR");
		Attribute checkFin = currEnt.getAttribute("RA_FINALIZARESTUDIO");
		Attribute combo = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR");
		String comentarios = currEnt.getAttribute("P2_CR_COMENTARIOS").getValueAsString();
		String estado = currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").getValueAsString();

		String mailUsuarioCreador = usuarioCreador.getEmail();
		Collection<User> usEncargado = this.getGroup(encargado).getUsers();

		String[] EmailCreador = { mailUsuarioCreador };

		if ((estado.compareTo("Registrar avance de estudio") == 0 && checkFin.getValueAsString().compareTo("true") == 0) || (estado.compareTo("Finalizar estudio") == 0)) {
			Helpers.setFecha(this, attFecha);
			currEnt.getAttribute("PROCESO_FINALIZADO_EST_STR").setValue("SI");
			currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").setValue("Finalizado");


			this.sendMail(EmailCreador, "TESTING_Capacitación finalizada", "Ha finalizado el proceso " + titulo
					+ " correctamente. " + "<br><br>Gracias por usar Apia.<br>Saludos.");
 
			for (User u : usEncargado) {
				String mail = u.getEmail();

				String[] mailEnviar = { mail };
				this.sendMail(mailEnviar, "TESTING_Capacitación finalizada", "Ha finalizado correctamente el proceso: "
					+ titulo + "." + "<br><br>Gracias por usar Apia.<br>Saludos.");

			}

		}

		
	}

}
