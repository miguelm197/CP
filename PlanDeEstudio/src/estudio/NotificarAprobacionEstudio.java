package estudio;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;


/**
 * Notifica al creador, jefe de proyecto y a José que se aprobó la solicitud.
 */
public class NotificarAprobacionEstudio extends ApiaAbstractClass {

	protected void executeClass() throws BusClassException {

		Entity currEnt = this.getCurrentEntity();

		// Obtengo usuario creador de la entidad(solicita)
		User usuarioCreador = currEnt.getCreator();
		// Obtengo jefe de proyecto
		User jefeProyecto = null;
		// this.addMessage(this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO").getValueAsString());
		String valuejefeProy = currEnt.getAttribute("SE_JEFEPROYECTO").getValueAsString();
		if (valuejefeProy.compareTo("José") == 0)
			jefeProyecto = this.getUser("jrussomano");
		else if (valuejefeProy.compareTo("Jorge") == 0)
			jefeProyecto = this.getUser("jartave");
		else if (valuejefeProy.compareTo("Federico") == 0)
			jefeProyecto = this.getUser("froda");

		// Nombre, email de creador y jefe de proyecto
		String nombreCreador = usuarioCreador.getName();
		String[] mailUsuarioCreador = { usuarioCreador.getEmail() };

		String nombreJefeProy = jefeProyecto.getName();
		String[] mailJefeProy = { jefeProyecto.getEmail() };

		String fechaInicio = currEnt.getAttribute("ECS_FECHAINICIO").getValueAsString();
		String comentariosAprob = currEnt.getAttribute("EAS_COMENTARIOS").getValueAsString();

		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();

		if (this.getCurrentEnvironment().compareTo("DEFAULT") == 0 && nombreCreador.compareTo("System Administrator") != 0) {
			this.sendMail(mailUsuarioCreador, "Solicitud de estudio aprobada",
					"Hola " + nombreCreador + ",<br><br>Tu solicitud de estudio del proceso " + titulo
							+ " ha sido aprobada. Comenzarías a estudiar el día " + fechaInicio.substring(0, 10)
							+ ".<br>Comentarios de aprobador: " + comentariosAprob + "<br><br>Saludos,<br>Apia.");

			this.sendMail(mailJefeProy, "Solicitud de estudio aprobada",
					"Hola " + nombreJefeProy + ",<br><br>La solicitud de estudio que solicitó " + nombreCreador
							+ ", correspondiente al proceso " + titulo + " ha sido aprobada.<br>"
							+ " Comenzaría a estudiar el día " + fechaInicio.substring(0, 10)
							+ ".<br>Comentarios de aprobador: " + comentariosAprob + "<br><br>Saludos,<br>Apia.");

			String[] mailJose = { this.getUser("jrussomano").getEmail() };
			this.sendMail(mailJose, "Solicitud de estudio aprobada", "Aprobaste una solicitud de estudio de "
					+ nombreCreador + ", correspondiente al proceso " + titulo + ".<br><br>Saludos,<br>Apia.");
		}

		this.getCurrentTask().complete();
	}
}
