package estudioTesting;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

public class NotificarAvance extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		

		Entity currEnt = this.getCurrentEntity();
		User usuarioCreador = currEnt.getCreator();
		String nombreUsuarioCreador = usuarioCreador.getName();

		
		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
		String fechaInicio = currEnt.getAttribute("ECS_FECHAINICIO").getValueAsString();
		String comentariosAprob = currEnt.getAttribute("EAS_COMENTARIOS").getValueAsString();
		String encargado = currEnt.getAttribute("SE_JEFEPROYECTO").getValueAsString();
		
		String avance = currEnt.getAttribute("RA_AVANCE").getValueAsString();
		String coment = currEnt.getAttribute("RA_COMENTARIOS").getValueAsString();
		
		
		String mailUsuarioCreador = usuarioCreador.getEmail();
		Collection<User> usEncargado = this.getGroup(encargado).getUsers();

		String[] EmailCreador = { mailUsuarioCreador };

		
		this.sendMail(EmailCreador, "TESTING_Avance de estudio", "Has tenido el siguiente avance en el proceso "
					+ titulo + ".<br>Avance: " + avance + "<br>Comentarios: " + coment + "<br><br>Saludos,<br>Apia");
		
		
		
		
		for (User u : usEncargado) {
			String mail = u.getEmail();
			String nombreJefeProy = u.getName();
			String[] mailEnviar = { mail };
			this.sendMail(mailEnviar, "TESTING_Avance de estudio de " + mailUsuarioCreador,
					"Hola " + nombreJefeProy + ", Le informamos que " + mailUsuarioCreador
							+ " ha tenido el siguiente avance en el proceso " + titulo + ".<br>Avance: " + avance
							+ "<br>Comentarios: " + coment + "<br><br>Saludos,<br>Apia");
	
		}

	}

}
