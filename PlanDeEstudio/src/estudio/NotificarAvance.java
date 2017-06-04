package estudio;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;


// TODO: Auto-generated Javadoc
/**
 * Cada vez que se registre un avance, se ejecuta esta clase, que notifica al creador y al jefe de proyecto
 * de dicho avance.
 */
public class NotificarAvance extends ApiaAbstractClass {

	/* (non-Javadoc)
	 * @see com.dogma.busClass.ApiaAbstractClass#executeClass()
	 */
	@Override
	protected void executeClass() throws BusClassException {
		

		Entity currEnt = this.getCurrentEntity();
		User usuarioCreador = currEnt.getCreator();

		
		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
		String encargado = currEnt.getAttribute("SE_JEFEPROYECTO").getValueAsString();
		
		String avance = currEnt.getAttribute("RA_AVANCE").getValueAsString();
		String coment = currEnt.getAttribute("RA_COMENTARIOS").getValueAsString();
		
		
		String mailUsuarioCreador = usuarioCreador.getEmail();
		Collection<User> usEncargado = this.getGroup(encargado).getUsers();

		String[] EmailCreador = { mailUsuarioCreador };

		
		this.sendMail(EmailCreador, "Avance de estudio", "Has tenido el siguiente avance en el proceso "
					+ titulo + ".<br>Avance: " + avance + "<br>Comentarios: " + coment + "<br><br>Saludos,<br>Apia");
		
		
		
		
		for (User u : usEncargado) {
			String mail = u.getEmail();
			String nombreJefeProy = u.getName();
			String[] mailEnviar = { mail };
			this.sendMail(mailEnviar, "Avance de estudio de " + mailUsuarioCreador,
					"Hola " + nombreJefeProy + ", Le informamos que " + mailUsuarioCreador
							+ " ha tenido el siguiente avance en el proceso " + titulo + ".<br>Avance: " + avance
							+ "<br>Comentarios: " + coment + "<br><br>Saludos,<br>Apia");
	
		}

	}

}
