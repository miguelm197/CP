package estudio;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

import estudio.Helpers;

/**
 * Se ejecuta cuando se cancela un proceso de capacitación. . Se setean los
 * atributos Proceso Finalizado -> Sí y Estado Actual de la capacitación ->
 * Cancelado. Además se envía un mail al creador y al jefe de proyecto del
 * proceso indicando que se canceló el mismo.
 */
public class CancelarCapacitacion extends ApiaAbstractClass {

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {

		// Obtiene entida y atributos
		Entity currEnt = this.getCurrentEntity();
		Attribute attFecha = currEnt.getAttribute("ESTUDIO_FECHA_FIN_STR");
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

		Helpers.setFecha(this, attFecha);
		currEnt.getAttribute("PROCESO_FINALIZADO_EST_STR").setValue("SI");
		currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").setValue("Cancelado");

		if (notificarA) {
			this.sendMail(EmailCreador, "Capacitación cancelada", "Se ha cancelado el proceso " + titulo
					+ " correctamente. " + "<br><br>Gracias por usar Apia.<br>Saludos.");
		}

		if (notificarB) {
			for (User u : usEncargado) {
				String mail = u.getEmail();
				String[] mailEnviar = { mail };
				this.sendMail(mailEnviar, "Capacitación cancelada", "Se ha cancelado correctamente el proceso: "
						+ titulo + "." + "<br><br>Gracias por usar Apia.<br>Saludos.");

			}
		}
	}

}
