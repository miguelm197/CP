package estudio;

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
		String avance = currEnt.getAttribute("RA_AVANCE").getValueAsString();
		String coment = currEnt.getAttribute("RA_COMENTARIOS").getValueAsString();
		Double td = (Double) currEnt.getAttribute("RA_TIEMPODISPONIBLE").getValue();

		// Obtengo usuario creador
		User usuarioCreador = currEnt.getCreator();

		// Obtengo jefe de proyecto
		User jefeProyecto = null;
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

		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();

		if (this.getCurrentEnvironment().compareTo("DEFAULT") == 0 && nombreCreador.compareTo("System Administrator") != 0
				&& td.intValue() != 0) {

			this.sendMail(mailUsuarioCreador, "Avance de estudio", "Has tenido el siguiente avance en el proceso "
					+ titulo + ".<br>Avance: " + avance + "<br>Comentarios: " + coment + "<br><br>Saludos,<br>Apia");

			this.sendMail(mailJefeProy, "Avance de estudio de " + nombreCreador,
					"Hola " + nombreJefeProy + ", Le informamos que " + nombreCreador
							+ " ha tenido el siguiente avance en el proceso " + titulo + ".<br>Avance: " + avance
							+ "<br>Comentarios: " + coment + "<br><br>Saludos,<br>Apia");
		}
	}

}
