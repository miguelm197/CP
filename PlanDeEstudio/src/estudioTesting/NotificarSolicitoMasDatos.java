package estudioTesting;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

public class NotificarSolicitoMasDatos extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Entity currEnt = this.getCurrentEntity();
		Attribute combo = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR");
		String comentarios = currEnt.getAttribute("P2_CR_COMENTARIOS2").getValueAsString();
		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
		
		String encargado = currEnt.getAttribute("SE_JEFEPROYECTO").getValueAsString();
		User usuarioCreador = currEnt.getCreator();
		String mailUsuarioCreador = usuarioCreador.getEmail();
		Collection<User> usEncargado = this.getGroup(encargado).getUsers();
		String[] EmailCreador = { mailUsuarioCreador };

		// -------------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------------------------------------------------

		boolean notificarA = true; // NOTIFICAR AL CREADOR
		boolean notificarB = true; // NOTIFICAR AL JEFE DE PROYECTO

		// -------------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------------------------------------------------

		if (combo.getValueAsString().compareTo("3") == 0) {

			if (notificarA) {
				this.sendMail(EmailCreador, "TESTING_Se solicitaron más datos",
						"Se ha solicitado que ingreses/modifiques datos de la solicitud de capacitación que has realizado, correspondiente al proceso "
								+ titulo + ".<br>Comentarios del jefe de proyecto: " + comentarios
								+ "<br>Tiene pendiente la tarea en Apia.<br><br>Muchas gracias.<br>Saludos,<br>Apia.");
			}

			if (notificarB) {
				for (User u : usEncargado) {
					String mail = u.getEmail();
					String[] mailEnviar = { mail };
					this.sendMail(mailEnviar, "TESTING_Solicitaste más datos",
							"Has solicitado que se ingresen/modifiquen los datos de la solicitud de capacitación correspondiente al proceso: "
									+ titulo + "." + "<br><br>Gracias por usar Apia.<br>Saludos.");

				}
			}
			
		}
	}

}
