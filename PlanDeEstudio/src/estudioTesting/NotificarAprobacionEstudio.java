package estudioTesting;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Process;
import com.dogma.busClass.object.User;

public class NotificarAprobacionEstudio extends ApiaAbstractClass {

	protected void executeClass() throws BusClassException {


		
		Entity currEnt = this.getCurrentEntity();
		User usuarioCreador = currEnt.getCreator();
		String nombreUsuarioCreador = usuarioCreador.getName();

		
		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
		String fechaInicio = currEnt.getAttribute("ECS_FECHAINICIO").getValueAsString();
		String comentariosAprob = currEnt.getAttribute("EAS_COMENTARIOS").getValueAsString();
		String encargado = currEnt.getAttribute("SE_JEFEPROYECTO").getValueAsString();
		
		String mailUsuarioCreador = usuarioCreador.getEmail();
		Collection<User> usEncargado = this.getGroup(encargado).getUsers();

		String[] EmailCreador = { mailUsuarioCreador };

		
		this.sendMail(EmailCreador, "TESTING_Solicitud de estudio aprobada",
				"Hola " + nombreUsuarioCreador + ",<br><br>Tu solicitud de estudio del proceso " + titulo
				+ " ha sido aprobada. Comenzarías a estudiar el día " + fechaInicio.substring(0, 10)
				+ ".<br>Comentarios de aprobador: " + comentariosAprob + "<br><br>Saludos,<br>Apia.");
		
		
		
		
		for (User u : usEncargado) {
			String mail = u.getEmail();
			String nombreJefeProy = u.getName();
			String[] mailEnviar = { mail };
			this.sendMail(mailEnviar, "TESTING_Solicitud de estudio aprobada",
					"Hola " + nombreJefeProy + ",<br><br>La solicitud de estudio que solicitó " + nombreUsuarioCreador
					+ ", correspondiente al proceso " + titulo + " ha sido aprobada.<br>"
					+ " Comenzaría a estudiar el día " + fechaInicio.substring(0, 10)
					+ ".<br>Comentarios de aprobador: " + comentariosAprob + "<br><br>Saludos,<br>Apia.");
	
		}


		this.getCurrentTask().complete();
		
		
	}
}
