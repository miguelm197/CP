package estudio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Identifier;
import com.dogma.busClass.object.User;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.EntityFilter;

// TODO: Auto-generated Javadoc
/**
 * Esta clase se ejecuta con el Scheduler.
 * Verifica los procesos vigentes. Si terminó el tiempo notifica al creador y jefe de proyecto
 * que terminó el tiempo.
 * Si llegó a mitad calendario (según Fecha de inicio y fin) notifica al creador y jefe de proyecto
 * que se llegó a mitad calendario.
 * Desde que corre el tiempo para realizar la capacitación, cada 10 días y 5 días antes de terminar notifica
 * al creador que debe registrar el avance.
 */
public class SetTiempoDisponibleScheduler extends ApiaAbstractClass {

	/* (non-Javadoc)
	 * @see com.dogma.busClass.ApiaAbstractClass#executeClass()
	 */
	@Override
	protected void executeClass() throws BusClassException {

		EntityFilter ef = new EntityFilter("Finalizado", 2, EntityFilter.COLUMN_FILTER_NOT_LIKE);
		EntityFilter ef2 = new EntityFilter("Rechazado", 2, EntityFilter.COLUMN_FILTER_NOT_LIKE);
		EntityFilter ef3 = new EntityFilter("Cancelado", 2, EntityFilter.COLUMN_FILTER_NOT_LIKE);
		ArrayList<EntityFilter> colEf = new ArrayList<>();
		colEf.add(ef);
		colEf.add(ef2);
		colEf.add(ef3);

		Collection<Identifier> entidades = this.findEntities("PLAN_ESTUDIO", colEf);
		int nEnt = entidades.size();
		// this.addMessage("Nro. de entidades encontradas: " + nEnt);

		if (nEnt > 0) {
			Date fechaActual = new Date();

			for (Identifier id : entidades) {
				Entity ent = this.getEntity("PLAN_ESTUDIO", id.getPrefix(), id.getNumber(), id.getPostfix());
				Attribute attFechaInicio = ent.getAttribute("ECS_FECHAINICIO");
				Attribute attFechaFin = ent.getAttribute("ECS_FECHAFIN");

				if (attFechaFin.getValueAsString() != null && attFechaFin.getValueAsString() != "") {
					Date fechaInicio = (Date) attFechaInicio.getValue();
					Date fechaFin = (Date) attFechaFin.getValue();

					// this.addMessage("TD antes
					// "+ent.getAttribute("RA_TIEMPODISPONIBLE").getValueAsString());
					ent.getAttribute("RA_TIEMPODISPONIBLE").setValue(Helpers.diferenciaEnHoras(fechaFin, fechaActual));
					// this.addMessage("TD después
					// "+ent.getAttribute("RA_TIEMPODISPONIBLE").getValueAsString());

					// Obtengo usuario creador
					User usuarioCreador = ent.getCreator();
					// Nombre, email de creador
					String nombreCreador = usuarioCreador.getName();
					String mailCreador = usuarioCreador.getEmail();
					String[] mailUsuarioCreador = { mailCreador };

					// Obtengo jefe de proyecto
					User jefeProyecto = null;
					String valuejefeProy = ent.getAttribute("SE_JEFEPROYECTO").getValueAsString();
					if (valuejefeProy.compareTo("José") == 0)
						jefeProyecto = this.getUser("jrussomano");
					else if (valuejefeProy.compareTo("Jorge") == 0)
						jefeProyecto = this.getUser("jartave");
					else if (valuejefeProy.compareTo("Federico") == 0)
						jefeProyecto = this.getUser("froda");

					String nombreJefeProy = jefeProyecto.getName();
					String mailJefeP = jefeProyecto.getEmail();
					String[] mailJefeProy = { jefeProyecto.getEmail() };

					String titulo = ent.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
					Double td = (Double) ent.getAttribute("RA_TIEMPODISPONIBLE").getValue();

					Double mitadInicioFin = (Double) Helpers.diferenciaEnHoras(fechaFin, fechaInicio)/2;
					Date fechaMitad = Helpers.sumarRestarHorasFecha(fechaInicio, mitadInicioFin.intValue());
					
					Attribute auxMitadCal = ent.getAttribute("AUXMITADCAL");
					Attribute auxTiempoTerminado = ent.getAttribute("AUXTIEMPOTERMINADO");
					
					
					if (this.getCurrentEnvironment().compareTo("DEFAULT") == 0 && nombreCreador.compareTo("System Administrator") != 0) {
						if (td == 0 && auxTiempoTerminado.getValueAsString().compareTo("true") != 0) {
							this.sendMail(mailJefeProy, "Tiempo terminado para realizar capacitación",
									"Hola " + nombreJefeProy + ",<br><br>Le informamos que ha terminado el tiempo de "
											+ nombreCreador
											+ " para realizar su capacitación, correspondiente al proceso: " + titulo
											+ ".<br><br>Saludos,<br>Apia.");
							this.sendMail(mailUsuarioCreador, "Tiempo terminado para realizar capacitación",
									"Le informamos "
											+ "que ha terminado el tiempo para realizar su capacitación, correspondiente al proceso "
											+ titulo + ".<br><br>Saludos,<br>Apia.");
							auxTiempoTerminado.setValue("true");
						}
						
						else if(fechaActual.compareTo(fechaMitad) >= 0 && auxMitadCal.getValueAsString().compareTo("true") != 0){
							Helpers.notificarMitadCalendario(this, nombreCreador, mailCreador, mailJefeP, titulo);
							auxMitadCal.setValue("true");
						}

						else if (td % 240 == 0 || td == 120) {
							if (fechaActual.compareTo(fechaInicio) >= 0) {
								this.sendMail(mailUsuarioCreador, "Debe completar el avance en Apia",
										"Le informamos "
												+ "que debe ingresar a Apia y completar el avance de su estudio, correspondiente al proceso "
												+ titulo + ".<br><br>Muchas gracias.<br>Saludos,<br>Apia.");
							}
						}
					}
				}
			}

		}
	}

}
