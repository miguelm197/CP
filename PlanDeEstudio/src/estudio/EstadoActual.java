package estudio;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

import estudio.Helpers;

// TODO: Auto-generated Javadoc
/**
 * Seg�n la tarea actual setea el atributo que indica en qu� tarea se encuentra
 * el proceso actualmente. Luego de ello, notifica al usuario correspondiente
 * (enviando un mail) que tiene la tarea para completar.
 */
public class EstadoActual extends ApiaAbstractClass {

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub

		Entity currEnt = this.getCurrentEntity(); // Obtengo entidad actual
		String tareaActual = this.getCurrentTask().getTaskTitle();

		// Defino el estado de la tarea (por donde anda el proceso)
		currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").setValue(tareaActual);

		User creador = currEnt.getCreator();
		String nombreCreador = creador.getName();
		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();

		if (tareaActual.equals("Completar o rechazar solicitud") || tareaActual.equals("Evaluar y completar solicitud")
				|| tareaActual.equals("Finalizar estudio")) {
			String valuejefeProy = currEnt.getAttribute("SE_JEFEPROYECTO").getValueAsString();
			Collection<User> use = this.getGroup(valuejefeProy).getUsers();
			for (User u : use) {
				String mail = u.getEmail();

				Helpers.notificarTareaPendiente(this, nombreCreador, mail, titulo);
			}
		} else {
			if (tareaActual.equals("Completar datos de solicitud de cap.")
					|| tareaActual.equals("Registrar avance de estudio")) {
				String mail = creador.getEmail();

				Helpers.notificarTareaPendiente(this, nombreCreador, mail, titulo);
			} else {
				Collection<User> us = this.getCurrentTask().getGroup().getUsers();

				for (User u : us) {
					String mail = u.getEmail();

					Helpers.notificarTareaPendiente(this, nombreCreador, mail, titulo);
				}
			}
		}
	}

}
