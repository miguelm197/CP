package estudio;

import java.util.Collection;
import java.util.Date;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;


// TODO: Auto-generated Javadoc
/**
 * No deja completar el Registro de avance si no está en la fecha correspondiente.
 * Si llegó a la mitad de las horas asignadas notifica al creador y al
 * jefe de proyecto que ya completó la mitad de las horas.
 * Si superó las horas asignadas notifica al creador y al
 * jefe de proyecto que superó las horas asignadas al proceso
 */
public class CompletarRA extends ApiaAbstractClass {
	
	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {
		Entity currEnt = this.getCurrentEntity();

		Attribute attFechaInicio = currEnt.getAttribute("ECS_FECHAINICIO");
		Date fechaInicio = (Date) attFechaInicio.getValue();
		Date fechaActual = new Date();

		if (fechaActual.before(fechaInicio)) {
			if (this.getCurrentEnvironment().compareTo("DEFAULT") == 0) {
				throw new BusClassException(
						"No se puede completar esta tarea antes de la fecha" + " de inicio del estudio.");
			}
		} else {
			// ---------------------------------------------------------------------------
			String encargado = currEnt.getAttribute("SE_JEFEPROYECTO").getValueAsString();
			User usuarioCreador = currEnt.getCreator();
			String mailUsuarioCreador = usuarioCreador.getEmail();
			Collection<User> usEncargado = this.getGroup(encargado).getUsers();
			String[] EmailCreador = { mailUsuarioCreador };
			

			String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
			// ---------------------------------------------------------------------------

			Double horasAsignadas = (Double) currEnt.getAttribute("ECS_TIEMPOESTIMADOESTUDIO").getValue();
			Double mitadHorasAsignadas = horasAsignadas / 2;

			Attribute attHorasEsteAvance = currEnt.getAttribute("P2_RA_HORAS_EMPLEADAS_AVANCE");
			Double horasEsteAvance = (Double) attHorasEsteAvance.getValue();
			
			Attribute attHorasEmpleadas = currEnt.getAttribute("P2_RA_HORAS_TOTALES_EMPLEADAS");
			Double horasEmpleadas = (Double) attHorasEmpleadas.getValue();

			Double horasHastaAhora = horasEmpleadas + horasEsteAvance;

			attHorasEmpleadas.setValue(horasHastaAhora);

			Double cntA = (Double) currEnt.getAttribute("CONTADOR_AUX").getValue();
			int cnt1 = cntA.intValue();
			
			Double cntB = (Double) currEnt.getAttribute("CONTADOR_AUX2").getValue();
			int cnt2 = cntB.intValue();
			
			
			
			
			// -------------------------------------------------------------------------------------------------------
			// -------------------------------------------------------------------------------------------------------

			boolean notificarA = true; // NOTIFICAR AL CREADOR 		 QUE SE SUPERÓ LA CANTIDAD DE DIAS ASIGNADAS
			boolean notificarB = true; // NOTIFICAR AL JEFE DE PROYECTO QUE SE SUPERÓ LA CANTIDAD DE DIAS ASIGNADAS
			boolean notificarC = true; // NOTIFICAR AL CREADOR			 QUE SE FINALIZÓ LA MITAD DE LA CAPACITACIÓN
			boolean notificarD = true; // NOTIFICAR AL JEFE DE PROYECTO QUE SE FINALIZÓ LA MITAD DE LA CAPACITACIÓN

			// -------------------------------------------------------------------------------------------------------
			// -------------------------------------------------------------------------------------------------------
			
			if (horasHastaAhora > horasAsignadas && cnt1 == 0) {

				if (notificarA) {
					this.sendMail(EmailCreador, "Se superó la cantidad de horas asignadas",
							"Se superó la cantidad de horas asignadas al proceso " + titulo + "."
									+ "<br><br>Saludos,<br>Apia.");
				}

				if (notificarB) {
					for (User u : usEncargado) {
						String mail = u.getEmail();
						String[] mailEnviar = { mail };
						this.sendMail(mailEnviar, "Se superó la cantidad de horas asignadas",
								"Se superó la cantidad de horas asignadas al proceso " + titulo + "."
										+ "<br><br>Saludos,<br>Apia.");

					}
				}

				currEnt.getAttribute("CONTADOR_AUX").setValue(1.0);
			}

			else if (horasHastaAhora >= mitadHorasAsignadas && cnt2 == 0) {

				if (notificarC) {
					this.sendMail(EmailCreador, "Mitad de la capacitación finalizada",
							"Según las horas que se han usado en la capacitación, aproximadamente la mitad del proceso "
									+ titulo + " ha finalizado correctamente."
									+ "<br><br>Gracias por usar Apia.<br>Saludos.");
				}

				if (notificarD) {
					for (User u : usEncargado) {
						String mail = u.getEmail();
						String[] mailEnviar = { mail };
						this.sendMail(mailEnviar, "Mitad de la capacitación finalizada",
								"Según las horas que se han usado en la capacitación, aproximadamente la mitad del proceso "
										+ titulo + " ha finalizado correctamente."
										+ "<br><br>Gracias por usar Apia.<br>Saludos.");

					}
				}

				currEnt.getAttribute("CONTADOR_AUX2").setValue(1.0);

			}

		}
	}
}
