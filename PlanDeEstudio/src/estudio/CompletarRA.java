package estudio;

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
			User creador = currEnt.getCreator();
			String nombreCreador = creador.getName();
			String mailCreador = creador.getEmail();
			// Obtengo jefe de proyecto
			User jefeProyecto = null;
			// this.addMessage(this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO").getValueAsString());
			String valuejefeProy = this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO").getValueAsString();
			if (valuejefeProy.compareTo("José") == 0)
				jefeProyecto = this.getUser("jrussomano");
			else if (valuejefeProy.compareTo("Jorge") == 0)
				jefeProyecto = this.getUser("jartave");
			else if (valuejefeProy.compareTo("Federico") == 0)
				jefeProyecto = this.getUser("froda");

			String mailJefeProy = jefeProyecto.getEmail();

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
			
			if (horasHastaAhora > horasAsignadas && cnt1 == 0) {
				Helpers.notificarSuperoHoras(this, nombreCreador, mailCreador, mailJefeProy, titulo);
				currEnt.getAttribute("CONTADOR_AUX").setValue(1.0);
			}
			else if (horasHastaAhora >= mitadHorasAsignadas && cnt2 == 0) {
				Helpers.notificarMitadHoras(this, nombreCreador, mailCreador, mailJefeProy, titulo);
				currEnt.getAttribute("CONTADOR_AUX2").setValue(1.0);
			}

		}
	}
}
