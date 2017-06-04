package estudio;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

import estudio.Helpers;


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
		User usuarioCreador = currEnt.getCreator();

		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
		String encargado = currEnt.getAttribute("SE_JEFEPROYECTO").getValueAsString();

		Attribute attFecha = currEnt.getAttribute("ESTUDIO_FECHA_FIN_STR");
		Attribute combo = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR");
		String comentarios = currEnt.getAttribute("P2_CR_COMENTARIOS").getValueAsString();
		String estado = currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").getValueAsString();

		String mailUsuarioCreador = usuarioCreador.getEmail();
		Collection<User> usEncargado = this.getGroup(encargado).getUsers();

		String[] EmailCreador = { mailUsuarioCreador };

		if ((estado.compareTo("Completar o rechazar solicitud") == 0 && combo.getValueAsString().compareTo("2") == 0)) {
			Helpers.setFecha(this, attFecha);
			currEnt.getAttribute("PROCESO_FINALIZADO_EST_STR").setValue("SI");
			currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").setValue("Rechazado");


			this.sendMail(EmailCreador, "Capacitación rechazada",
					"Se rechazó tu solicitud de capacitación correspondiente al proceso " + titulo
							+ ".<br>Comentarios: " + comentarios + "<br><br>Gracias por usar Apia.<br>Saludos.");

			for (User u : usEncargado) {
				String mail = u.getEmail();

				String[] mailEnviar = { mail };
				this.sendMail(mailEnviar, "Capacitación rechazada",
						"Se rechazó tu solicitud de capacitación correspondiente al proceso " + titulo
								+ ".<br>Comentarios: " + comentarios + "<br><br>Gracias por usar Apia.<br>Saludos.");

			}

		}
	}
}
